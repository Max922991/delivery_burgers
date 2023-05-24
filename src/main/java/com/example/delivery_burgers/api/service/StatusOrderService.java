package com.example.delivery_burgers.api.service;

import com.example.delivery_burgers.api.exceptions.BadRequestException;
import com.example.delivery_burgers.store.entity.StatusOrderEntity;
import com.example.delivery_burgers.store.repository.StatusOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusOrderService {
    private final StatusOrderRepository statusOrderRepository;

    public void save(StatusOrderEntity entity) {
        statusOrderRepository.save(entity);
    }

    public StatusOrderEntity getStatusByName(String name) {
        return statusOrderRepository.findByName(name)
                .orElseThrow(() -> new BadRequestException("Status not found"));
    }

    public StatusOrderEntity getStatusOrderEntityByIdOrElseThrow(Long id) {
        return statusOrderRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Status not found"));
    }
}
