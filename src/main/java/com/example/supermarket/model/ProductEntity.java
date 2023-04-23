package com.example.supermarket.model;

import com.example.supermarket.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    public ProductDto asDto(){
        ProductDto dto = new ProductDto();
        dto.setId(getId());
        dto.setName(getName());
        return dto;
    }
}
