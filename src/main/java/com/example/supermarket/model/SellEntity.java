package com.example.supermarket.model;

import com.example.supermarket.dto.SellDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "db_sell")
@Getter
@Setter
public class SellEntity extends BaseEntity{
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id")
    private BranchEntity branch;

    @OneToMany(mappedBy = "sell", fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
    @OrderBy("id asc ")
    private Set<SellItemEntity > sellItems;

    @Column(name="amount", columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal amount = BigDecimal.ZERO;

    public SellDto asDto() {
        SellDto sellDto = new SellDto();
        sellDto.setId(getId());
        sellDto.setDate(getDate());
        sellDto.setBranch(getBranch().asDto());
        sellDto.setAmount(getAmount());
        for (SellItemEntity sellItemEntity : getSellItems()) {
            sellDto.getSellItems().add(sellItemEntity.asDto());
        }
        return sellDto;
    }
}
