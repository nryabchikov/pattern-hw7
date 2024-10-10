package ru.clevertec.factory;

import ru.clevertec.common.CakeType;
import ru.clevertec.domain.Cake;

import java.time.OffsetDateTime;
import java.util.UUID;

public class CakeFactoryImpl implements CakeFactory {

    @Override
    public Cake createCake(String title, CakeType cakeType) {
        return new Cake(UUID.randomUUID(), title, cakeType, OffsetDateTime.now());
    }
}
