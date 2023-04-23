package com.example.supermarket.dto;

import com.example.supermarket.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {
    private Long id;
    private Status status = Status.ACTIVE;
}
