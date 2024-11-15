package com.example.paypromodulith.authentication.application.input;

import com.example.paypromodulith.authentication.domain.model.ClientDto;

public interface ClientInput {
    ClientDto ambiguousSearch(String name);
}
