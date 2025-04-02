package com.thomas.tp_spring.services;

import com.thomas.tp_spring.model.Hero;
import com.thomas.tp_spring.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    public List<Hero> getAllHeroes() {
        return heroRepository.findAll();
    }

    public Hero addHero(Hero hero) {
        return heroRepository.save(hero);
    }

    public Optional<Hero> getHeroById(Long id) {
        return heroRepository.findById(id);
    }

    public Hero getRandomHero() {
        List<Hero> heroes = heroRepository.findAll();
        if (heroes.isEmpty()) {
            return null;  // Si aucun héros n'est trouvé
        }
        Random random = new Random();
        return heroes.get(random.nextInt(heroes.size()));
    }

    public List<Hero> searchHeroesByName(String name) {
        return heroRepository.findByNameContaining(name);
    }
}
