package com.thomas.tp_spring.services;

import com.thomas.tp_spring.model.hero.Hero;
import com.thomas.tp_spring.model.hero.HeroDTO;
import com.thomas.tp_spring.repository.HeroRepository;
import com.thomas.tp_spring.mappers.HeroMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class HeroService {

    @Autowired
    private HeroRepository heroRepository;

    private final HeroMapper heroMapper = HeroMapper.INSTANCE;

    public List<HeroDTO> getAllHeroes() {
        List<Hero> heroes = heroRepository.findAll();
        return heroes.stream()
                .map(heroMapper::heroToHeroDTO)
                .collect(Collectors.toList());
    }

    public HeroDTO addHero(Hero hero) {
        Hero savedHero = heroRepository.save(hero);
        return heroMapper.heroToHeroDTO(savedHero);
    }

    public Optional<HeroDTO> getHeroById(Long id) {
        Optional<Hero> hero = heroRepository.findById(id);
        return hero.map(heroMapper::heroToHeroDTO);
    }

    // Retourne un héros aléatoire sous forme de HeroDTO
    public HeroDTO getRandomHero() {
        List<Hero> heroes = heroRepository.findAll();
        if (heroes.isEmpty()) {
            return null;
        }
        Random random = new Random();
        Hero randomHero = heroes.get(random.nextInt(heroes.size()));
        return heroMapper.heroToHeroDTO(randomHero);
    }

    public List<HeroDTO> searchHeroesByName(String name) {
        List<Hero> heroes = heroRepository.findByNameContaining(name);
        return heroes.stream()
                .map(heroMapper::heroToHeroDTO)
                .collect(Collectors.toList());
    }
}
