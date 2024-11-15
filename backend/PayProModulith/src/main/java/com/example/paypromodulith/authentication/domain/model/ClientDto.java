package com.example.paypromodulith.authentication.domain.model;

import com.example.paypromodulith.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Client}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto implements Serializable {
    private String adminemail;
    private String useremail;
    private String adminpassword;
    private String userpassword;
}