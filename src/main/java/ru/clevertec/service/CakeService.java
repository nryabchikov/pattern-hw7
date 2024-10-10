package ru.clevertec.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.domain.Cake;
import ru.clevertec.entity.CakeEntity;
import ru.clevertec.exception.CakeNotFoundException;
import ru.clevertec.mapper.CakeMapper;
import ru.clevertec.repository.CakeRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CakeService {
    private final CakeRepository cakeRepository;
    private final CakeMapper cakeMapper;

    public List<Cake> getCakes() {
        List<CakeEntity> cakeEntities = cakeRepository.getCakes();
        return cakeMapper.entitiesToDomains(cakeEntities);
    }

    public Cake getCakeById(UUID cakeId) {
        CakeEntity cakeEntity = cakeRepository.getCakeById(cakeId).orElseThrow(() ->
                CakeNotFoundException.byCakeId(cakeId));
        return cakeMapper.entityToDomain(cakeEntity);
    }

    public Cake create(Cake cake) {
        CakeEntity cakeEntity = cakeRepository.create(cakeMapper.domainToEntity(cake));
        return cakeMapper.entityToDomain(cakeEntity);
    }

    public Cake update(UUID cakeId, Cake cake) {
        CakeEntity updatedCakeEntity = cakeRepository.update(cakeId, cakeMapper.domainToEntity(cake));
        if (updatedCakeEntity != null) {
            return cakeMapper.entityToDomain(updatedCakeEntity);
        } else {
            throw CakeNotFoundException.byCakeId(cakeId);
        }
    }

    public void delete(UUID cakeId) {
        cakeRepository.delete(cakeId);
    }
}
