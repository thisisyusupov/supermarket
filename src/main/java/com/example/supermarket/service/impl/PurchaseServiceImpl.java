package com.example.supermarket.service.impl;

import com.example.supermarket.dto.PurchaseDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.PurchaseEntity;
import com.example.supermarket.repositotry.BranchRepository;
import com.example.supermarket.repositotry.CompanyRepository;
import com.example.supermarket.repositotry.PurchaseRepository;
import com.example.supermarket.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    private final BranchRepository branchRepository;

    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public Long save(PurchaseDto purchaseDto) {
        PurchaseEntity purchase;
        if (purchaseDto.getId() == null) {
            purchase = new PurchaseEntity();
        } else {
            purchase = purchaseRepository.findById(purchaseDto.getId()).orElseThrow();
        }
        purchase.setDate(purchaseDto.getDate());
        purchase.setBranch(branchRepository.findById(purchaseDto.getBranch().getId()).orElseThrow());
        purchase.setCompany(companyRepository.findById(purchaseDto.getCompany().getId()).orElseThrow());
        purchase.setAmount(purchaseDto.getAmount());
        purchaseRepository.save(purchase);

        return purchase.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseDto get(Long id) {
        return purchaseRepository.findById(id).orElseThrow().asDto();
    }

    @Override
    public ResultData<PurchaseDto> getList(RequestFilter filter) {

        Page<PurchaseEntity> purchaseList = purchaseRepository.findAll(
                PageRequest.of(
                        filter.getPage().getPageNumber() - 1,
                        filter.getPage().getSize(),
                        Sort.by(
                                Sort.Direction.valueOf(
                                        filter.getPage().getSort().getDirection().toUpperCase()
                                ),
                                filter.getPage().getSort().getColumn()
                        )
                )
        );

        List<PurchaseDto> purchaseDtos = purchaseList.stream().map(PurchaseEntity::asDto).collect(Collectors.toList());

        return new ResultData<>(purchaseDtos, purchaseList.getTotalElements());
    }

    @Override
    @Transactional
    public String delete(Long id) {
        Optional<PurchaseEntity> purchase = purchaseRepository.findById(id);
        if (purchase.isEmpty()) return "Not found";

        purchaseRepository.delete(purchase.get());
        return "Success";
    }
}
