package com.example.delivery_burgers.api.controller;

import com.example.delivery_burgers.api.dto.CardDto;
import com.example.delivery_burgers.api.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardController {
    private final CardService cardService;

    @PostMapping("/create{customer_id}")
    public CardDto createCard(@PathVariable("customer_id") Long customerId,
                              @RequestParam("name_owner") String nameOwner,
                              @RequestParam("card_number") String cardNumber,
                              @RequestParam("expiry_date") LocalDateTime expiryDate, @RequestParam("cvv") String cvv) {
        return cardService.createCard(customerId, nameOwner, cardNumber,
                expiryDate, cvv);
    }
}
