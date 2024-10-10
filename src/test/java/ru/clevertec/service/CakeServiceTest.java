package ru.clevertec.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.domain.Cake;
import ru.clevertec.entity.CakeEntity;
import ru.clevertec.exception.CakeNotFoundException;
import ru.clevertec.mapper.CakeMapper;
import ru.clevertec.repository.CakeRepository;
import ru.clevertec.util.TestData;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CakeServiceTest {

    @Mock
    private CakeRepository cakeRepository;
    @Mock
    private CakeMapper cakeMapper;
    @InjectMocks
    private CakeService cakeService;

    @Test
    void shouldGetCakes() {
        //given
        List<CakeEntity> cakeEntities = TestData.generateCakeEntities();

        List<Cake> cakes = TestData.generateCakes();

        when(cakeRepository.getCakes())
                .thenReturn(cakeEntities);
        when(cakeMapper.entitiesToDomains(cakeEntities))
                .thenReturn(cakes);

        //when
        List<Cake> actualCakes = cakeService.getCakes();

        //then
        assertFalse(actualCakes.isEmpty());
    }

    @Test
    void shouldGetCakeById() {
        //given
        UUID cakeId = UUID.randomUUID();

        CakeEntity cakeEntity = TestData.generateCakeEntity();
        Cake cake = TestData.generateCake();

        when(cakeRepository.getCakeById(cakeId))
                .thenReturn(Optional.of(cakeEntity));
        when(cakeMapper.entityToDomain(cakeEntity))
                .thenReturn(cake);

        //when
        Cake actualCake = cakeService.getCakeById(cakeId);

        //then
        assertEquals(cake, actualCake);
    }

    @Test
    void shouldNotGetCakeById_whenCakeNotFound() {
        //given
        UUID cakeId = UUID.randomUUID();

        when(cakeRepository.getCakeById(cakeId))
                .thenReturn(Optional.empty());

        //when, then
        assertThrows(
                CakeNotFoundException.class,
                () -> cakeService.getCakeById(cakeId)
        );
    }

    @Test
    void shouldCreate() {
        //given
        CakeEntity cakeEntity = TestData.generateCakeEntity();
        Cake cake = TestData.generateCake();

        when(cakeMapper.domainToEntity(cake))
                .thenReturn(cakeEntity);
        when(cakeRepository.create(cakeEntity))
                .thenReturn(cakeEntity);
        when(cakeMapper.entityToDomain(cakeEntity))
                .thenReturn(cake);

        //when
        Cake actualCake = cakeService.create(cake);

        //then
        assertEquals(cake, actualCake);
    }

    @Test
    void shouldUpdate() {
        //given
        UUID cakeId = UUID.randomUUID();

        Cake cake = TestData.generateCake();
        CakeEntity cakeEntity = TestData.generateCakeEntity();

        when(cakeMapper.domainToEntity(cake))
                .thenReturn(cakeEntity);
        when(cakeRepository.update(cakeId, cakeEntity))
                .thenReturn(cakeEntity);
        when(cakeMapper.entityToDomain(cakeEntity))
                .thenReturn(cake);

        //when
        Cake updatedCake = cakeService.update(cakeId, cake);

        //then
        assertEquals(cake, updatedCake);
    }

    @Test
    void shouldNotUpdate_whenCakeNotFound() {
        //given
        UUID cakeId = UUID.randomUUID();

        Cake cake = TestData.generateCake();
        CakeEntity cakeEntity = TestData.generateCakeEntity();

        when(cakeMapper.domainToEntity(cake))
                .thenReturn(cakeEntity);
        doThrow(CakeNotFoundException.class)
                .when(cakeRepository).update(cakeId, cakeEntity);

        //when, then
        assertThrows(
            CakeNotFoundException.class,
                () -> cakeService.update(cakeId, cake)
        );
    }

    @Test
    void shouldDeleteById() {
        //given
        UUID cakeId = UUID.randomUUID();

        //when
        cakeService.delete(cakeId);

        //then
        verify(cakeRepository).delete(cakeId);
    }

    @Test
    void shouldNotDeleteById_whenCakeNotFound() {
        //given
        UUID cakeId = UUID.randomUUID();

        doThrow(CakeNotFoundException.class)
                .when(cakeRepository).delete(cakeId);

        //when, then
        assertThrows(
                CakeNotFoundException.class,
                () -> cakeService.delete(cakeId)
        );
    }
}