package ru.clevertec.util;

import ru.clevertec.common.CakeType;
import ru.clevertec.domain.Cake;
import ru.clevertec.dto.CakeDTO;
import ru.clevertec.entity.CakeEntity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class TestData {
    public static CakeEntity generateCakeEntity() {
        return new CakeEntity().setId(UUID.randomUUID());
    }

    public static Cake generateCake() {
        return new Cake().setId(UUID.randomUUID());
    }

    public static CakeDTO generateCakeDto() {
        return new CakeDTO().setId(UUID.randomUUID());
    }

    public static ArrayList<Cake> generateCakes() {
        return new ArrayList<>(Arrays.asList(
                new Cake(UUID.randomUUID(), "cake1", CakeType.BIG, OffsetDateTime.now()),
                new Cake(UUID.randomUUID(), "cake2", CakeType.BIG, OffsetDateTime.now()),
                new Cake(UUID.randomUUID(), "cake3", CakeType.SMALL, OffsetDateTime.now()))
        );
    }

    public static ArrayList<CakeEntity> generateCakeEntities() {
        return new ArrayList<>(Arrays.asList(
                new CakeEntity(UUID.randomUUID(), "cake4", CakeType.BIG, OffsetDateTime.now()),
                new CakeEntity(UUID.randomUUID(), "cake5", CakeType.BIG, OffsetDateTime.now()),
                new CakeEntity(UUID.randomUUID(), "cake6", CakeType.SMALL, OffsetDateTime.now()))
        );
    }

    public static ArrayList<CakeDTO> generateCakeDtos() {
        return new ArrayList<>(Arrays.asList(
                new CakeDTO(UUID.randomUUID(), "cake1", CakeType.BIG, OffsetDateTime.now()),
                new CakeDTO(UUID.randomUUID(), "cake2", CakeType.BIG, OffsetDateTime.now()),
                new CakeDTO(UUID.randomUUID(), "cake3", CakeType.SMALL, OffsetDateTime.now()))
        );
    }
}
