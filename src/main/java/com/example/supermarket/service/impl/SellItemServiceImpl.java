package com.example.supermarket.service.impl;

import com.example.supermarket.dto.SellItemDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.SellItemEntity;
import com.example.supermarket.repositotry.BrandRepository;
import com.example.supermarket.repositotry.ProductRepository;
import com.example.supermarket.repositotry.SellItemRepository;
import com.example.supermarket.repositotry.SellRepository;
import com.example.supermarket.service.SellItemService;
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
public class SellItemServiceImpl implements SellItemService {

    private final SellItemRepository sellItemRepository;

    private final ProductRepository productRepository;

    private final BrandRepository brandRepository;

    private final SellRepository sellRepository;

    @Override
    public List<SellItemDto> getList() {
        return sellItemRepository.findAll().stream().map(SellItemEntity::asDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void save(SellItemDto sellItemDto) {
        SellItemEntity sellItem;
        if (sellItemDto.getId() == null) {
            sellItem = new SellItemEntity();
            sellItem.setSell(sellRepository.findById(sellItemDto.getSell().getId()).orElseThrow());
        } else {
            sellItem = sellItemRepository.findById(sellItemDto.getId()).orElseThrow();
        }
        sellItem.setProduct(productRepository.findById(sellItemDto.getProduct().getId()).orElseThrow());
        sellItem.setBrand(brandRepository.findById(sellItemDto.getBrand().getId()).orElseThrow());
        sellItem.setPrice(sellItemDto.getPrice());
        sellItem.setQty(sellItemDto.getQty());
        sellItem.setType(sellItemDto.getType());

        sellItemRepository.save(sellItem);
    }

    @Override
    @Transactional(readOnly = true)
    public SellItemDto get(Long id) {
        return sellItemRepository.findById(id).orElseThrow().asDto();
    }

    @Override
    public ResultData<SellItemDto> getList(RequestFilter filter) {
        Page<SellItemEntity> sellItemList  = sellItemRepository.findAll(
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

        List<SellItemDto> sellItemDtos = sellItemList.stream().map(SellItemEntity::asDto).collect(Collectors.toList());
        return new ResultData<>(sellItemDtos, sellItemList.getTotalElements());
    }

    @Override
    @Transactional
    public String delete(Long id) {
        Optional<SellItemEntity> sellItem = sellItemRepository.findById(id);
        if (sellItem.isEmpty()) return "Not found";
        sellItemRepository.delete(sellItem.get());
        return "Success";
    }
}
