package com.example.supermarket.service;

import com.example.supermarket.dto.BranchDto;
import com.example.supermarket.dto.PurchaseDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.BranchEntity;

import java.util.List;

public interface BranchService {
    void save(BranchDto branchDto);

    BranchDto get(Long id);

    BranchDto getByName(String name);

    List<BranchEntity> getList();

    ResultData<BranchDto> getList(RequestFilter filter);

    String delete(Long id);
}
