package com.example.supermarket.model;

import com.example.supermarket.dto.CompanyDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "db_company")
@Getter
@Setter
public class CompanyEntity extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "tin")
    private String tin;

    @Column(name = "adress")
    private String adress;

    @Column(name = "email")
    private String email;

    @Column(name = "bank_account")
    private String bankAccount;

    public CompanyDto asDto() {
        CompanyDto dto = new CompanyDto();
        dto.setId(getId());
        dto.setName(getName());
        dto.setName(getName());
        dto.setTin(getTin());
        dto.setAdress(getAdress());
        dto.setEmail(getEmail());
        dto.setBankAccount(getBankAccount());
        return dto;
    }
}
