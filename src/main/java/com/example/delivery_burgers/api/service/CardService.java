package com.example.delivery_burgers.api.service;

import com.example.delivery_burgers.api.dto.CardDto;
import com.example.delivery_burgers.api.dto.OrderDto;
import com.example.delivery_burgers.api.dto.StatusOrderDto;
import com.example.delivery_burgers.api.mapper.CardMapper;
import com.example.delivery_burgers.store.entity.BurgerEntity;
import com.example.delivery_burgers.store.entity.CardEntity;
import com.example.delivery_burgers.store.entity.CustomerEntity;
import com.example.delivery_burgers.store.entity.OrderEntity;
import com.example.delivery_burgers.store.repository.CardRepository;
import com.example.delivery_burgers.store.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;
    private final CardMapper cardMapper;

    public CardDto createCard(Long customerId, String nameOwner, String cardNumber, LocalDateTime expiryDate, String cvv) {
        CardEntity cardEntity = CardEntity.builder()
                .nameOwner(nameOwner)
                .number(cardNumber)
                .expiryDate(expiryDate)
                .balance(50)
                .cvv(cvv)
                .build();
        cardRepository.save(cardEntity);
        linkCardToCustomer(customerId, cardEntity.getId());
        return cardMapper.toDto(cardEntity);
    }

    private void linkCardToCustomer(Long customerId, Long cardId) {
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("customer not found"));
        CardEntity card = cardRepository.findById(cardId).orElseThrow(() -> new IllegalArgumentException("card not found"));
        customer.getCards().add(card);
        cardRepository.save(card);
    }

}
