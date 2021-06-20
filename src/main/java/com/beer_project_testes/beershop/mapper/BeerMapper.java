package com.beer_project_testes.beershop.mapper;

import com.beer_project_testes.beershop.dto.BeerDTO;
import com.beer_project_testes.beershop.entity.Beer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BeerMapper {

    BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);

    Beer toModel(BeerDTO beerDTO);

    BeerDTO toDTO(Beer beer);
}
