package com.example.supermarket.service;

import com.example.supermarket.dto.PurchaseDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import org.springframework.stereotype.Service;

@Service
public interface PurchaseService {
    Long save(PurchaseDto purchaseDto);

    PurchaseDto get(Long id);

    String delete(Long id);

    ResultData<PurchaseDto> getList(RequestFilter filter);

}
