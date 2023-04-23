package com.example.supermarket.service.impl;

import com.example.supermarket.dto.SellDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.SellEntity;
import com.example.supermarket.repositotry.BranchRepository;
import com.example.supermarket.repositotry.SellRepository;
import com.example.supermarket.service.SellService;
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
public class SellServiceImpl implements SellService {

    private final SellRepository sellRepository;

    private final BranchRepository branchRepository;


    @Override
    @Transactional
    public Long save(SellDto sellDto) {
        SellEntity sell;
        if (sellDto.getId() == null) {
            sell = new SellEntity();
        } else {
            sell = sellRepository.findById(sellDto.getId()).orElseThrow();
        }

        sell.setDate(sellDto.getDate());
        sell.setBranch(branchRepository.findById(sellDto.getBranch().getId()).orElseThrow());
        sell.setAmount(sellDto.getAmount());
        sellRepository.save(sell);

        return sell.getId();
    }


    @Override
    @Transactional(readOnly = true)
    public SellDto get(Long id) {
        return sellRepository.findById(id).orElseThrow().asDto();
    }

    @Override
    public ResultData<SellDto> getList(RequestFilter filter) {
        Page<SellEntity> sellList = sellRepository.findAll((
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
        ));

        List<SellDto> sellDtos = sellList.stream().map(SellEntity::asDto).collect(Collectors.toList());
        return new ResultData<>(sellDtos, sellList.getTotalElements());
    }

    @Override
    public List<SellDto> getList() {
        return sellRepository.findAll().stream().map(SellEntity::asDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public String delete(Long id) {
        Optional<SellEntity> sell = sellRepository.findById(id);
        if (sell.isEmpty()) return "Not found";
        sellRepository.delete(sell.get());
        return "Success";
    }
}
