package com.example.paypromodulith.authentication.application.output;

import com.example.paypromodulith.authentication.domain.model.ClientDto;

public interface ClientOutput {
    ClientDto ambiguousSearch(String name);
}
