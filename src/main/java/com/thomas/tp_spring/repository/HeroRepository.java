package com.thomas.tp_spring.repository;
import com.thomas.tp_spring.model.Hero;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findByNameContaining(String name);
}
