package ru.clevertec.factory;

import ru.clevertec.common.CakeType;
import ru.clevertec.entity.CakeEntity;

public interface CakeEntityFactory {
    CakeEntity createCakeEntity(String title, CakeType cakeType);
}
