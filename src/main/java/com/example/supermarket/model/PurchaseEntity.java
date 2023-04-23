package com.example.supermarket.model;

import com.example.supermarket.dto.PurchaseDto;
import com.example.supermarket.dto.PurchaseItemDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "db_purchase")
@Getter
@Setter
public class PurchaseEntity extends BaseEntity{

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @OrderBy("id asc ")
    private Set<PurchaseItemEntity > purchaseItems;

    @Column(name="amount", columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal amount = BigDecimal.ZERO;

    public PurchaseDto asDto() {
        PurchaseDto purchaseDto = new PurchaseDto();
        purchaseDto.setId(getId());
        purchaseDto.setDate(getDate());
        purchaseDto.setBranch(getBranch().asDto());
        purchaseDto.setCompany(getCompany().asDto());
        purchaseDto.setAmount(getAmount());
        for (PurchaseItemEntity purchaseItemEntity : getPurchaseItems()) {
            purchaseDto.getPurchaseItems().add(purchaseItemEntity.asDto());
        }
        return purchaseDto;
    }
}
