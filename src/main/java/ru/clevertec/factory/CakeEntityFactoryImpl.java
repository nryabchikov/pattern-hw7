package ru.clevertec.factory;

import ru.clevertec.common.CakeType;
import ru.clevertec.entity.CakeEntity;

import java.time.OffsetDateTime;
import java.util.UUID;

public class CakeEntityFactoryImpl implements CakeEntityFactory {

    @Override
    public CakeEntity createCakeEntity(String title, CakeType cakeType) {
        return new CakeEntity(UUID.randomUUID(), title, cakeType, OffsetDateTime.now());
    }
}
