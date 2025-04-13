package com.thomas.tp_spring.controller;

import com.thomas.tp_spring.model.hero.Hero;
import com.thomas.tp_spring.model.hero.HeroDTO;
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

    // Récupérer tous les héros sous forme de HeroDTO
    @GetMapping
    public List<HeroDTO> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    // Ajouter un héros et retourner HeroDTO
    @PostMapping
    public HeroDTO addHero(@RequestBody Hero hero) {
        return heroService.addHero(hero);  // Le service retourne maintenant HeroDTO
    }

    // Récupérer un héros par son ID sous forme de HeroDTO
    @GetMapping("/{id}")
    public ResponseEntity<HeroDTO> getHeroById(@PathVariable Long id) {
        Optional<HeroDTO> hero = heroService.getHeroById(id);
        return hero.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Récupérer un héros aléatoire sous forme de HeroDTO
    @GetMapping("/random")
    public ResponseEntity<HeroDTO> getRandomHero() {
        HeroDTO hero = heroService.getRandomHero();
        return hero != null ? ResponseEntity.ok(hero) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Recherche des héros par leur nom, retourne une liste de HeroDTO
    @GetMapping("/search")
    public List<HeroDTO> searchHeroes(@RequestParam String name) {
        return heroService.searchHeroesByName(name);
    }
}
