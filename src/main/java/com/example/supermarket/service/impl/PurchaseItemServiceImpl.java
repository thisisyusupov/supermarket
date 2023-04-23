package com.example.supermarket.service.impl;

import com.example.supermarket.dto.PurchaseItemDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.PurchaseItemEntity;
import com.example.supermarket.repositotry.*;
import com.example.supermarket.service.PurchaseItemService;
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
public class PurchaseItemServiceImpl implements PurchaseItemService {
    private final PurchaseItemRepository purchaseItemRepository;

    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final PurchaseRepository purchaseRepository;

    @Override
    public List<PurchaseItemDto> getList() {
        return purchaseItemRepository.findAll().stream().map(PurchaseItemEntity::asDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(PurchaseItemDto purchaseItemDto) {
        PurchaseItemEntity purchaseItem;
        if (purchaseItemDto.getId() == null){
            purchaseItem = new PurchaseItemEntity();
            purchaseItem.setPurchase(purchaseRepository.findById(purchaseItemDto.getPurchase().getId()).orElseThrow());
        } else {
            purchaseItem = purchaseItemRepository.findById(purchaseItemDto.getId()).orElseThrow();
        }

        purchaseItem.setProduct(productRepository.findById(purchaseItemDto.getProduct().getId()).orElseThrow());
        purchaseItem.setBrand(brandRepository.findById(purchaseItemDto.getBrand().getId()).orElseThrow());
        purchaseItem.setPrice(purchaseItemDto.getPrice());
        purchaseItem.setQty(purchaseItemDto.getQty());
        purchaseItem.setType(purchaseItemDto.getType());
//        purchaseItem.setUm(purchaseItem.getUm());

        purchaseItemRepository.save(purchaseItem);
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseItemDto get(Long id) {
        return purchaseItemRepository.findById(id).orElseThrow().asDto();
    }

    @Override
    public ResultData<PurchaseItemDto> getList(RequestFilter filter) {
        Page<PurchaseItemEntity> purchaseItemList = purchaseItemRepository.findAll(
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
        List<PurchaseItemDto> purchaseItemDtos = purchaseItemList.stream().map(PurchaseItemEntity::asDto).collect(Collectors.toList());
        return new ResultData<>(purchaseItemDtos, purchaseItemList.getTotalElements());
    }

    @Override
    @Transactional
    public Object delete(Long id) {
        Optional<PurchaseItemEntity> purchaseItem = purchaseItemRepository.findById(id);
        if(purchaseItem.isEmpty()) return "Not found";

        purchaseItemRepository.delete(purchaseItem.get());
        return "Success";
    }
}
