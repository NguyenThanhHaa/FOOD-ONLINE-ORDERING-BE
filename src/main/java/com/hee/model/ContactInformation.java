package com.hee.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ContactInformation {
    private String email;

    private String mobile;

    private String facebook;

    private String instagram;
}
