package com.example.supermarket.model;

import com.example.supermarket.dto.BrandDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "db_brand")
@Getter
@Setter
public class BrandEntity extends BaseEntity{
    @Column(name = "name", unique = true)
    private String name;

    public BrandDto asDto(){
        BrandDto dto = new BrandDto();
        dto.setId(getId());
        dto.setName(getName());
        return dto;
    }
}
