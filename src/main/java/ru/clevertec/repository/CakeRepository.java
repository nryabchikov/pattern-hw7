package ru.clevertec.repository;

import org.springframework.stereotype.Repository;
import ru.clevertec.common.CakeType;
import ru.clevertec.entity.CakeEntity;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CakeRepository {
    private static List<CakeEntity> db = List.of(
        new CakeEntity(UUID.randomUUID(), "cake1", CakeType.BIG, OffsetDateTime.now()),
        new CakeEntity(UUID.randomUUID(), "cake2", CakeType.BIG, OffsetDateTime.now()),
        new CakeEntity(UUID.randomUUID(), "cake3", CakeType.SMALL, OffsetDateTime.now())
    );
    public List<CakeEntity> getCakes() {
        return db;
    }

    public Optional<CakeEntity> getCakeById(UUID cakeId) {
        return db.stream()
                .filter(cakeEntity -> cakeEntity.getId().equals(cakeId))
                .findAny();
    }

    public CakeEntity create(CakeEntity cakeEntity) {
        db.add(cakeEntity);
        return cakeEntity;
    }

    public CakeEntity update(UUID cakeId, CakeEntity updatedCakeEntity) {
        Optional<CakeEntity> cake = getCakeById(cakeId);

        if (cake.isPresent()) {
            CakeEntity existingCake = cake.get();
            existingCake.setTitle(updatedCakeEntity.getTitle());
            existingCake.setCakeType(updatedCakeEntity.getCakeType());
            existingCake.setExpiredPeriod(updatedCakeEntity.getExpiredPeriod());
            return existingCake;
        }
        return null;
    }

    public void delete(UUID cakeId) {
        getCakeById(cakeId)
                .ifPresent(db::remove);
    }
}
