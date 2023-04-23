package com.example.supermarket.dto;

//import com.example.supermarket.enums.UnitMeasurement;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseItemDto extends BaseDto{
    private PurchaseDto purchase;
    private ProductDto product;
    private BrandDto brand;
    private String type;
//    private UnitMeasurement um;
    private BigDecimal qty = BigDecimal.ZERO;
    private BigDecimal price = BigDecimal.ZERO;

}
