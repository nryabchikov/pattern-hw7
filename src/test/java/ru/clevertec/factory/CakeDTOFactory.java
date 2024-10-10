package ru.clevertec.factory;

import ru.clevertec.common.CakeType;
import ru.clevertec.dto.CakeDTO;

public interface CakeDTOFactory {
    CakeDTO createCakeDTO(String title, CakeType cakeType);
}
