package ru.clevertec.factory;

import ru.clevertec.common.CakeType;
import ru.clevertec.dto.CakeDTO;

import java.time.OffsetDateTime;
import java.util.UUID;

public class CakeDTOFactoryImpl implements CakeDTOFactory {

    @Override
    public CakeDTO createCakeDTO(String title, CakeType cakeType) {
        return new CakeDTO(UUID.randomUUID(), title, cakeType, OffsetDateTime.now());
    }
}
