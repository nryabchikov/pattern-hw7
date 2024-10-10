package ru.clevertec.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.domain.Cake;
import ru.clevertec.dto.CakeDTO;
import ru.clevertec.entity.CakeEntity;
import ru.clevertec.util.TestData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CakeMapperTest {
    @Mock
    CakeMapper cakeMapper;

    @Test
    void shouldMapEntityToDomain() {
        //given
        Cake cake = TestData.generateCake();
        CakeEntity cakeEntity = TestData.generateCakeEntity();

        when(cakeMapper.entityToDomain(cakeEntity))
                .thenReturn(cake);

        //when
        Cake actualCake = cakeMapper.entityToDomain(cakeEntity);

        //then
        assertEquals(cake, actualCake);
    }

    @Test
    void shouldMapDomainToEntity() {
        //given
        Cake cake = TestData.generateCake();
        CakeEntity cakeEntity = TestData.generateCakeEntity();

        when(cakeMapper.domainToEntity(cake))
                .thenReturn(cakeEntity);

        //when
        CakeEntity actualCakeEntity = cakeMapper.domainToEntity(cake);

        //then
        assertEquals(cakeEntity, actualCakeEntity);
    }

    @Test
    void shouldMapDomainToDto() {
        //given
        Cake cake = TestData.generateCake();
        CakeDTO cakeDTO = TestData.generateCakeDto();

        when(cakeMapper.domainToDto(cake))
                .thenReturn(cakeDTO);

        //when
        CakeDTO actualCakeDto = cakeMapper.domainToDto(cake);

        //then
        assertEquals(cakeDTO, actualCakeDto);
    }

    @Test
    void shouldMapDtoToDomain() {
        //given
        Cake cake = TestData.generateCake();
        CakeDTO cakeDTO = TestData.generateCakeDto();

        when(cakeMapper.dtoToDomain(cakeDTO))
                .thenReturn(cake);

        //when
        Cake actualCake = cakeMapper.dtoToDomain(cakeDTO);

        //then
        assertEquals(cake, actualCake);
    }

    @Test
    void shouldMapEntitiesToDomains() {
        //given
        ArrayList<Cake> cakes = TestData.generateCakes();
        ArrayList<CakeEntity> cakeEntities = TestData.generateCakeEntities();

        when(cakeMapper.entitiesToDomains(cakeEntities))
                .thenReturn(cakes);

        //when
        List<Cake> actualCakes = cakeMapper.entitiesToDomains(cakeEntities);

        //then
        assertEquals(cakes, actualCakes);
    }

    @Test
    void shouldMapDomainsToDtos() {
        //given
        ArrayList<Cake> cakes = TestData.generateCakes();
        ArrayList<CakeDTO> cakeDTOS = TestData.generateCakeDtos();

        when(cakeMapper.domainsToDtos(cakes))
                .thenReturn(cakeDTOS);

        //when
        List<CakeDTO> actualCakeDtos = cakeMapper.domainsToDtos(cakes);

        //then
        assertEquals(cakeDTOS, actualCakeDtos);
    }
}