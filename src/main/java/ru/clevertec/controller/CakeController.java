package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.domain.Cake;
import ru.clevertec.dto.CakeDTO;
import ru.clevertec.mapper.CakeMapper;
import ru.clevertec.service.CakeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CakeController {
    private final CakeService cakeService;
    private final CakeMapper cakeMapper;

    @GetMapping("/api/v1/cakes")
    public List<CakeDTO> findAll() {
        List<Cake> cakes = cakeService.getCakes();
        return cakeMapper.domainsToDtos(cakes);
    }

    @GetMapping("/api/v1/cakes/{cakeId}")
    public CakeDTO findById(@PathVariable UUID cakeId) {
        Cake cake = cakeService.getCakeById(cakeId);
        return cakeMapper.domainToDto(cake);
    }

    @PostMapping("/api/v1/cakes")
    public CakeDTO create(@RequestBody CakeDTO cakeDTO) {
        Cake cake = cakeService.create(cakeMapper.dtoToDomain(cakeDTO));
        return cakeMapper.domainToDto(cake);
    }

    @PutMapping("/api/v1/cakes/{cakeId}")
    public CakeDTO update(@PathVariable UUID cakeId, @RequestBody CakeDTO cakeDTO) {
        Cake cake = cakeService.update(cakeId, cakeMapper.dtoToDomain(cakeDTO));
        return cakeMapper.domainToDto(cake);
    }

    @DeleteMapping("/api/v1/cakes/{cakeId}")
    public void delete(@PathVariable UUID cakeId) {
        cakeService.delete(cakeId);
    }
}
