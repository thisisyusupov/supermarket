package com.example.supermarket.service;

import com.example.supermarket.dto.SellDto;
import com.example.supermarket.dto.SellItemDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface SellService {

    Long save(SellDto sellDto);

    @Transactional(readOnly = true)
    SellDto get(Long id);

    List<SellDto> getList();

    String delete(Long id);

    ResultData<SellDto> getList(RequestFilter filter);
}
