package ch.jakubma.cokedispenser.DispenserDb;

import ch.jakubma.cokedispenser.DispenserDb.model.Drink;
import ch.jakubma.cokedispenser.DispenserDb.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final DrinkRepository repository;

    @Autowired
    public DatabaseLoader(DrinkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Drink("CocaCola", 50, 2));
        this.repository.save(new Drink("Sprite", 43, 20));
        this.repository.save(new Drink("SpriteZero", 24, 0));
        this.repository.save(new Drink("CocaColaZero", 24, 20));
        System.out.println("Database initialized!");
    }
}