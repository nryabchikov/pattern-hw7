package ru.clevertec.factory;

import ru.clevertec.common.CakeType;
import ru.clevertec.domain.Cake;

public interface CakeFactory {
    Cake createCake(String title, CakeType cakeType);
}
