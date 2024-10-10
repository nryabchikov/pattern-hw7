package ru.clevertec.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.domain.Cake;
import ru.clevertec.mapper.CakeMapperImpl;
import ru.clevertec.service.CakeService;
import ru.clevertec.util.TestData;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(CakeMapperImpl.class)
@WebMvcTest(CakeController.class)
class CakeControllerTest {

    @MockBean
    private CakeService cakeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldFindAll() throws Exception {
        //given
        ArrayList<Cake> cakes = TestData.generateCakes();

        when(cakeService.getCakes())
                .thenReturn(cakes);

        //when, then
        mockMvc.perform(get("/api/v1/cakes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3));
    }


    @Test
    void shouldFindById() throws Exception {
        //given
        Cake cake = TestData.generateCake();
        UUID cakeId = cake.getId();

        when(cakeService.getCakeById(cakeId))
                .thenReturn(cake);

        //when, then
        mockMvc.perform(get("/api/v1/cakes/{cakeId}", cakeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cakeId.toString()))
                .andExpect(jsonPath("$.title").value(cake.getTitle()))
                .andExpect(jsonPath("$.cakeType").value(cake.getCakeType()))
                .andExpect(jsonPath("$.expiredPeriod").value(cake.getExpiredPeriod()));

    }

    @Test
    void shouldCreate() throws Exception {
        //given
        Cake cake = TestData.generateCake();

        when(cakeService.create(cake))
                .thenReturn(cake);

        //when, then
        mockMvc.perform(post("/api/v1/cakes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cake)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cake.getId().toString()))
                .andExpect(jsonPath("$.title").value(cake.getTitle()))
                .andExpect(jsonPath("$.cakeType").value(cake.getCakeType()))
                .andExpect(jsonPath("$.expiredPeriod").value(cake.getExpiredPeriod()));
    }

    @Test
    void shouldUpdate() throws Exception {
        // given
        Cake cake = TestData.generateCake();
        UUID cakeId = UUID.randomUUID();

        when(cakeService.update(cakeId, cake))
                .thenReturn(cake);

        // when, then
        mockMvc.perform(put("/api/v1/cakes/{cakeId}", cakeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cake)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(cake.getId().toString()))
                .andExpect(jsonPath("$.title").value(cake.getTitle()))
                .andExpect(jsonPath("$.cakeType").value(cake.getCakeType()))
                .andExpect(jsonPath("$.expiredPeriod").value(cake.getExpiredPeriod()));
    }


    @Test
    void shouldDelete() throws Exception {
        //given
        UUID cakeId = UUID.randomUUID();

        //when, then
        mockMvc.perform(delete("/api/v1/cakes/{cakeId}", cakeId))
                .andExpect(status().isOk());
    }
}
