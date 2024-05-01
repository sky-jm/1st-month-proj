package ch.jakubma.cokedispenser.DispenserDb.service;

import ch.jakubma.cokedispenser.DispenserDb.model.Drink;
import ch.jakubma.cokedispenser.DispenserDb.repository.DrinkRepository;
import ch.jakubma.cokedispenser.DispenserDb.service.DrinkService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DrinkServiceImpl implements DrinkService {
    private final DrinkRepository drinkRepository;

    @Autowired
    public DrinkServiceImpl(final DrinkRepository DrinkRepository) {
        this.drinkRepository = DrinkRepository;
    }

    @Override
    public List<Drink> findAll() {
        return (List<Drink>) this.drinkRepository.findAll();
    }

    @Override
    public List<Drink> search(String query) {
        return this.drinkRepository.findByNameContainingIgnoreCase(query);
    }

    @Override
    public Optional<Drink> findById(Long id) {
        return this.drinkRepository.findById(id);
    }

    @Override
    public Drink save(Drink file) {
        return this.drinkRepository.save(file);
    }

    @Override
    public void delete(Drink drink) {
        this.drinkRepository.delete(drink);
    }
}
