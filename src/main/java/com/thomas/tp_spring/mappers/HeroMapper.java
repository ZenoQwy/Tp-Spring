package com.thomas.tp_spring.mappers;

import com.thomas.tp_spring.model.hero.Hero;
import com.thomas.tp_spring.model.hero.HeroDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HeroMapper {

    HeroMapper INSTANCE = Mappers.getMapper(HeroMapper.class);

    HeroDTO heroToHeroDTO(Hero hero);
    Hero heroDTOToHero(HeroDTO heroDTO);
}
