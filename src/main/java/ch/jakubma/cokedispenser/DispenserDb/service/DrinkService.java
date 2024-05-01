package ch.jakubma.cokedispenser.DispenserDb.service;

import ch.jakubma.cokedispenser.DispenserDb.model.Drink;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface DrinkService {
    List<Drink> findAll();

    List<Drink> search(String query);

    Optional<Drink> findById(Long id);

    Drink save(Drink file);

    void delete(Drink file);
}