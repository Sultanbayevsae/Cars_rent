package org.example.server.mapper;

import org.example.server.dto.RentalDTO;
import org.example.server.entity.Rental;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RentalMapper {
    RentalDTO toDTO(Rental rental);
    Rental toEntity(RentalDTO dto);
}
