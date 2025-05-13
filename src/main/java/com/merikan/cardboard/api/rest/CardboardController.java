package com.merikan.cardboard.api.rest;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.merikan.cardboard.model.Cardboard;
import com.merikan.cardboard.services.CardboardService;
import com.merikan.cardboard.services.Item;

@RestController
@RequestMapping("/api/cardboard")
public class CardboardController {
    private final CardboardService service;

    public CardboardController(CardboardService service) {
        this.service = service;
    }

    @PostMapping(value = "/match", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> match(@RequestBody List<Item> items) {
        Cardboard box = service.findMatchingCardboardBox(items);
        if (Objects.isNull(box)) {
            return new ResponseEntity<>("Upphämtning krävs", new HttpHeaders(), HttpStatus.BAD_REQUEST);
            // throw new NoMatchException("Upphämtning krävs");
        }
        return new ResponseEntity<>(box.getId().toString(), new HttpHeaders(), HttpStatus.OK);
    }

}
