package com.example.supermarket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BranchDto extends BaseDto{
    private String name;
    private String tin;
    private String adress;
    private String bankAccount;
}
