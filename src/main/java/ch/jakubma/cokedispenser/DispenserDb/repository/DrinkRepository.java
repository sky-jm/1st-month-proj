package ch.jakubma.cokedispenser.DispenserDb.repository;

import ch.jakubma.cokedispenser.DispenserDb.model.Drink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkRepository extends CrudRepository<Drink, Long> {
    List<Drink> findByNameContainingIgnoreCase(String query);
    Optional<Drink> findByName(String drinkName);
}
