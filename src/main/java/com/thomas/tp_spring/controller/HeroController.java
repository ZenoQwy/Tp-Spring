package com.thomas.tp_spring.controller;

import com.thomas.tp_spring.model.Hero;
import com.thomas.tp_spring.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/heroes")
public class HeroController {

    @Autowired
    private HeroService heroService;

    @GetMapping
    public List<Hero> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    @PostMapping
    public Hero addHero(@RequestBody Hero hero) {
        return heroService.addHero(hero);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable Long id) {
        Optional<Hero> hero = heroService.getHeroById(id);
        return hero.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/random")
    public Hero getRandomHero() {
        return heroService.getRandomHero();
    }

    @GetMapping("/search")
    public List<Hero> searchHeroes(@RequestParam String name) {
        return heroService.searchHeroesByName(name);
    }
}
