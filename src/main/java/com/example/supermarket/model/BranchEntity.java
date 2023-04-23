package com.example.supermarket.model;

import com.example.supermarket.dto.BranchDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "db_branch")
@Getter
@Setter
public class BranchEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "tin")
    private String tin;

    @Column(name = "adress")
    private String adress;

    @Column(name = "bank_account")
    private String bankAccount;

    public BranchDto asDto() {
        BranchDto dto = new BranchDto();
        dto.setId(getId());
        dto.setName(getName());
        dto.setTin(getTin());
        dto.setAdress(getAdress());
        dto.setBankAccount(getBankAccount());
        return dto;
    }
}
