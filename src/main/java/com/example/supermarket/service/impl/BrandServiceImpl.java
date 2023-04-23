package com.example.supermarket.service.impl;

import com.example.supermarket.dto.BrandDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.BrandEntity;
import com.example.supermarket.repositotry.BrandRepository;
import com.example.supermarket.service.BrandService;
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
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    @Transactional
    public void save(BrandDto brandDto) {
        BrandEntity brand;
        if(brandDto.getId() != null){
            Optional<BrandEntity> brandOpt = brandRepository.findById(brandDto.getId());
            brand = brandOpt.orElseGet(BrandEntity::new);
        } else {
            brand = new BrandEntity();
        }
        brand.setName(brandDto.getName());
        brandRepository.save(brand);
    }

    @Override
    @Transactional(readOnly = true)
    public BrandDto get(Long id) {
        Optional<BrandEntity> brand = brandRepository.findById(id);
        return brand.get().asDto();
    }

    @Override
    public ResultData<BrandDto> getList(RequestFilter filter) {

        Page<BrandEntity> brandList = brandRepository.findAll(
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

        List<BrandDto> brandDtos = brandList.stream().map(BrandEntity::asDto).collect(Collectors.toList());

        return new ResultData<>(brandDtos, brandList.getTotalElements());
    }

    @Override
    @Transactional
    public BrandDto getByName(String name) {
        BrandEntity brand = brandRepository.findByName(name);
        return brand.asDto();
    }

    @Override
    public List<BrandEntity> getList() {
        return brandRepository.findAll();
    }

    @Override
    @Transactional
    public String delete(Long id) {
        Optional<BrandEntity> brand = brandRepository.findById(id);
        if(brand.isEmpty()) return "Not found";

        brandRepository.delete(brand.get());
        return "Success";
    }
}
