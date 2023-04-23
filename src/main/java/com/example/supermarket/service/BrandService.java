package com.example.supermarket.service;

import com.example.supermarket.dto.BrandDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.BrandEntity;

import java.util.List;

public interface BrandService {

    void save(BrandDto brandDto);

    BrandDto get(Long id);

    ResultData<BrandDto> getList(RequestFilter filter);

    BrandDto getByName(String name);

    List<BrandEntity> getList();

    String delete(Long id);

}
