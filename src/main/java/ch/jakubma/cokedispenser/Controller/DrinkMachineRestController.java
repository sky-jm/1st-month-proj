package ch.jakubma.cokedispenser.Controller;

import ch.jakubma.cokedispenser.DispenserDb.model.Drink;
import ch.jakubma.cokedispenser.DispenserDb.service.DrinkService;
import ch.jakubma.cokedispenser.DispenserMain.DispenserMachine;
import ch.jakubma.cokedispenser.Payment.PaymentStrategy;
import ch.jakubma.cokedispenser.Payment.PaymentStrategyFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("vendingmachine")
public class DrinkMachineRestController {
    private final DrinkService drinkService;
    private final DispenserMachine dispenserMachine;

    public DrinkMachineRestController(DrinkService drinkService) {
        this.drinkService = drinkService;
        this.dispenserMachine = DispenserMachine.getInstance();
        System.err.println("DrinkMachineRestController is ready, ");
    }

    @GetMapping(path="/getDrinks", produces="application/json")
    public List<Drink> getAvailableDrinks() {
        return drinkService.findAll();
    }

    @PostMapping(path="/selectDrink", produces="application/json")
    public ResponseEntity<String> selectDrink(@RequestBody String id) throws JsonProcessingException {
        System.out.println("You chose %s".formatted(id));
        Optional<Drink> drink = drinkService.findById(Long.parseLong(id));
        var json = prepareGenericAnswerMap();

        if (drink.isPresent()) {
            dispenserMachine.setSelectedDrink(drink.get());
            json.put("status", "ok");
            json.put("data", drink.get());
        } else {
            json.put("status", "error");
        }


        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(json);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonString);
    }

    @PostMapping(path="/addCashBalance", produces="application/json")
    public ResponseEntity<String> addCashBalance(@RequestBody String coin) throws JsonProcessingException {
        System.out.println("You chose %s, adding to cash balance".formatted(coin));

        boolean result = dispenserMachine.addCurrentCashBalance(Integer.parseInt(coin));
        var json = prepareGenericAnswerMap();
        if (!result) {
            json.put("status", "error");
        }
        json.put("data", result);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(json);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonString);
    }

    @PostMapping(path="/pay", produces="application/json")
    public ResponseEntity<String> payNow(@RequestBody String paymentType) throws JsonProcessingException {
        System.err.printf("You chose %s%n", paymentType);

        PaymentStrategy usedStrategy = PaymentStrategyFactory.createPaymentStrategy(paymentType);

        // Prepare answer map
        var json = prepareGenericAnswerMap();

        if (dispenserMachine.getSelectedDrink() == null) { // Guard pattern
            json.put("status", "fail");
            json.put("data", "You have not selected a drink.");
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(mapToString(json));
        }

        Optional<Drink> drinkExists = drinkService.findById(dispenserMachine.getSelectedDrink().getId());

        if (!drinkExists.isPresent()) { // Guard pattern
            json.put("status", "fail");
            json.put("data", "This drink does not exist");
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(mapToString(json));
        }

        Drink realDrink = drinkExists.get();
        // TODO: Anti-pattern in a pattern presentation alert: How to get rid of currentBalance in the pay call
        // while preserving both the PaymentStrategyFactory & the PaymentStrategy patterns?

        if(realDrink.getBottleCount() > 0) {
            int priceOfDrink = realDrink.getPrice();

            // Try to pay using the user's desired strategy
            boolean paymentResult = usedStrategy.pay(priceOfDrink, dispenserMachine.getCurrentCashBalance());

            if (paymentResult) {
                json.put("status", "ok");
                json.put("data", "paid");
                dispenserMachine.setCurrentCashBalance(dispenserMachine.getCurrentCashBalance() - priceOfDrink);
                json.replace("cashBalance", dispenserMachine.getCurrentCashBalance());
                realDrink.setBottleCount(realDrink.getBottleCount() - 1);
                drinkService.save(realDrink);
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(mapToString(json));
            } else {
                json.put("status", "fail");
                json.put("data", "Insufficient balance"); // TODO: Lol, how do you know this from just a boolean false :-D
                return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(mapToString(json));
            }
        } else {
            json.put("status", "fail"); // Not enough bottles left
            json.put("data", "This bottle is no longer available!");
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(mapToString(json));
        }
    }

    @GetMapping("/returnChange")
    public ResponseEntity<String> returnChange() throws JsonProcessingException {
        HashMap<Integer, Integer> returnedChange = dispenserMachine.returnChange();

        var json = prepareGenericAnswerMap();
        json.put("status", "ok");
        json.put("data", returnedChange);

        System.out.println(json);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(json);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonString);
    }


    public Map<String, Object> prepareGenericAnswerMap() {
        // TODO: Would fix if i had the time :|
        Map<String, Object> json = new HashMap<>();
        json.put("cashBalance", dispenserMachine.getCurrentCashBalance());
        json.put("selectedDrink", dispenserMachine.getSelectedDrink());

        return json;
    }

    public String mapToString(Map<String, Object> json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(json);
        return jsonString;
    }

}
