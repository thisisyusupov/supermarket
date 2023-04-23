package com.example.supermarket.service;

import com.example.supermarket.dto.CompanyDto;
import com.example.supermarket.dto.PurchaseDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.CompanyEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CompanyService {
    @Transactional
    CompanyDto getByName(String name);
    List<CompanyEntity> getList();

    void save(CompanyDto companyDto);

    List<CompanyEntity> save(String name);

    ResultData<CompanyDto> getList(RequestFilter filter);

    CompanyDto get(Long id);

    String delete(Long id);
}
