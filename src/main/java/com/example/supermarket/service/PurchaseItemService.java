package com.example.supermarket.service;

import com.example.supermarket.dto.PurchaseDto;
import com.example.supermarket.dto.PurchaseItemDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;

import java.util.List;

public interface PurchaseItemService {
    List<PurchaseItemDto> getList();

    void save(PurchaseItemDto purchaseItemDto);

    PurchaseItemDto get(Long id);

    ResultData<PurchaseItemDto> getList(RequestFilter filter);

    Object delete(Long id);
}
