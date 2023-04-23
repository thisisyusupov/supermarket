package com.example.supermarket.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SellItemDto extends BaseDto{
    private SellDto sell;
    private ProductDto product;
    private BrandDto brand;
    private String type;
    //    private UnitMeasurement um;
    private BigDecimal qty = BigDecimal.ZERO;
    private BigDecimal price = BigDecimal.ZERO;
}
