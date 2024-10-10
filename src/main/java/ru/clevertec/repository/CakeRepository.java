package ru.clevertec.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.clevertec.common.CakeType;
import ru.clevertec.entity.CakeEntity;
import ru.clevertec.factory.CakeEntityFactory;
import ru.clevertec.factory.CakeEntityFactoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CakeRepository {
    private final CakeEntityFactory cakeEntityFactory = new CakeEntityFactoryImpl();

    private List<CakeEntity> db = List.of(
            cakeEntityFactory.createCakeEntity("cake1", CakeType.BIG),
            cakeEntityFactory.createCakeEntity("cake2", CakeType.BIG),
            cakeEntityFactory.createCakeEntity("cake3", CakeType.BIG)
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
