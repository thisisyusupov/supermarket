package com.example.supermarket.service;

import com.example.supermarket.dto.ProductDto;
import com.example.supermarket.dto.PurchaseDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.ProductEntity;

import java.util.List;

public interface ProductService {
    void save(ProductDto productTypeDto);

    ProductDto get(Long id);

    ProductDto getByName(String name);

    List<ProductEntity> getList();

    ResultData<ProductDto> getList(RequestFilter filter);

    String delete(Long id);
}
