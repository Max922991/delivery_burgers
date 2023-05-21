package com.example.delivery_burgers.store.repository;

import com.example.delivery_burgers.store.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<CardEntity, Long> {

    Optional<CardEntity> findByNumber(String cardNumber);
}
