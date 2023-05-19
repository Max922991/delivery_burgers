package com.example.delivery_burgers.service;

import com.example.delivery_burgers.dto.IngredientDto;
import com.example.delivery_burgers.mapper.IngredientMapper;
import com.example.delivery_burgers.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public boolean deleteById(long id) {
        var findOrder = ingredientRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("ingredient is not exists"));
        return findOrder != null;
    }

    public IngredientDto getById(long id) {
        return ingredientRepository.findById(id)
                .map(ingredient -> ingredientMapper.toDto(ingredient))
                .orElseThrow(() -> new NoSuchElementException("Ingredient with id " + id + " not found"));
    }

    public List<IngredientDto> getdAll() {
        return ingredientRepository.findAll().stream()
                .map(ingredient -> ingredientMapper.toDto(ingredient))
                .collect(Collectors.toList());
    }
}
