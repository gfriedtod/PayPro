package com.example.paypromodulith.authentication.domain.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TokenDetails {
    private final UUID id;

    private final String firstName;

    public TokenDetails(UUID id, String firstName){
        this.id =id;
        this.firstName = firstName;

    }
}
