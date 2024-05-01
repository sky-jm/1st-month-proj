package ch.jakubma.cokedispenser.Controller;

import ch.jakubma.cokedispenser.DispenserDb.repository.DrinkRepository;
import ch.jakubma.cokedispenser.DispenserDb.service.DrinkService;
import ch.jakubma.cokedispenser.DispenserDb.service.DrinkServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DrinkMachineController {
    private final DrinkService drinkService;

    public DrinkMachineController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("drinks", drinkService.findAll());
        return "index";
    }
}
