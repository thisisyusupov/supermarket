package com.example.supermarket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto extends BaseDto{
    private String name;
    private String tin;
    private String adress;
    private String email;
    private String bankAccount;
}
