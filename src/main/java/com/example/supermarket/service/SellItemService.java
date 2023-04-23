package com.example.supermarket.service;

import com.example.supermarket.dto.SellItemDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SellItemService {
    List<SellItemDto> getList();

    void save(SellItemDto sellItemDto);

    SellItemDto get(Long id);

    ResultData<SellItemDto> getList(RequestFilter  filter);

    String delete(Long id);
}
