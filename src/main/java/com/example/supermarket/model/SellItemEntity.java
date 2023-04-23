package com.example.supermarket.model;

import com.example.supermarket.dto.SellItemDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "db_sell_item")
@Getter
@Setter
public class SellItemEntity extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @Column(name = "type")
    private String type;

    @Column(name="qty", nullable = false, columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal qty = BigDecimal.ZERO;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "um", nullable = false)
//    private UnitMeasurement um;

    @Column(name="price", nullable = false, columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal price = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sell_id")
    private SellEntity sell;

    public SellItemDto asDto(){
        SellItemDto dto = new SellItemDto();
        dto.setId(getId());
        dto.setProduct(getProduct().asDto());
        dto.setBrand(getBrand().asDto());
        dto.setType(getType());
//        dto.setUm(getUm());
        dto.setQty(getQty());
        dto.setPrice(getPrice());
        return dto;
    }
}
