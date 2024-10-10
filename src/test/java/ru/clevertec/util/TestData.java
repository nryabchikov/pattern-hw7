package ru.clevertec.util;

import ru.clevertec.common.CakeType;
import ru.clevertec.domain.Cake;
import ru.clevertec.dto.CakeDTO;
import ru.clevertec.entity.CakeEntity;
import ru.clevertec.factory.CakeDTOFactory;
import ru.clevertec.factory.CakeDTOFactoryImpl;
import ru.clevertec.factory.CakeEntityFactory;
import ru.clevertec.factory.CakeEntityFactoryImpl;
import ru.clevertec.factory.CakeFactory;
import ru.clevertec.factory.CakeFactoryImpl;

import java.util.ArrayList;
import java.util.Arrays;


public class TestData {
    private static final CakeFactory cakeFactory = new CakeFactoryImpl();
    private static final CakeDTOFactory cakeDTOFactory = new CakeDTOFactoryImpl();
    private static final CakeEntityFactory cakeEntityFactory = new CakeEntityFactoryImpl();

    public static CakeEntity generateCakeEntity() {
        return cakeEntityFactory.createCakeEntity("cake1", CakeType.BIG);
    }

    public static Cake generateCake() {
        return cakeFactory.createCake("cake2", CakeType.BIG);
    }

    public static CakeDTO generateCakeDto() {
        return cakeDTOFactory.createCakeDTO("cake3", CakeType.SMALL);
    }

    public static ArrayList<Cake> generateCakes() {
        return new ArrayList<>(Arrays.asList(
                cakeFactory.createCake("cake1", CakeType.BIG),
                cakeFactory.createCake("cake2", CakeType.BIG),
                cakeFactory.createCake("cake3", CakeType.SMALL))
        );
    }

    public static ArrayList<CakeEntity> generateCakeEntities() {
        return new ArrayList<>(Arrays.asList(
                cakeEntityFactory.createCakeEntity("cake4", CakeType.BIG),
                cakeEntityFactory.createCakeEntity("cake5", CakeType.BIG),
                cakeEntityFactory.createCakeEntity("cake6", CakeType.SMALL))
        );
    }

    public static ArrayList<CakeDTO> generateCakeDtos() {
        return new ArrayList<>(Arrays.asList(
                cakeDTOFactory.createCakeDTO("cake1", CakeType.BIG),
                cakeDTOFactory.createCakeDTO("cake2", CakeType.BIG),
                cakeDTOFactory.createCakeDTO("cake3", CakeType.SMALL))
        );
    }
}
