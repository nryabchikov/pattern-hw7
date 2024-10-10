package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.domain.Cake;
import ru.clevertec.dto.CakeDTO;
import ru.clevertec.entity.CakeEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CakeMapper {
    List<Cake> entitiesToDomains(List<CakeEntity> cakes);
    Cake entityToDomain(CakeEntity cakes);
    CakeEntity domainToEntity(Cake cake);

    CakeDTO domainToDto(Cake cake);
    List<CakeDTO> domainsToDtos(List<Cake> cakes);
    Cake dtoToDomain(CakeDTO cakeDTO);
}
