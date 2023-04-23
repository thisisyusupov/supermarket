package com.example.supermarket.service.impl;

import com.example.supermarket.dto.BranchDto;
import com.example.supermarket.dto.PurchaseDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.BranchEntity;
import com.example.supermarket.model.PurchaseEntity;
import com.example.supermarket.repositotry.BranchRepository;
import com.example.supermarket.service.BranchService;
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
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;

    @Override
    @Transactional
    public void save(BranchDto branchDto) {

        BranchEntity branch;
        if (branchDto.getId() != null){
            Optional<BranchEntity> branchOpt = branchRepository.findById(branchDto.getId());
            branch = branchOpt.orElseGet(BranchEntity::new);
        } else {
            branch = new BranchEntity();
        }

        branch.setName(branchDto.getName());
        branch.setAdress(branchDto.getAdress());
        branch.setTin(branchDto.getTin());
        branch.setBankAccount(branchDto.getBankAccount());
        branchRepository.save(branch);
    }

    @Override
    @Transactional(readOnly = true)
    public BranchDto get(Long id) {
        Optional<BranchEntity> branch = branchRepository.findById(id);
        return branch.get().asDto();
    }

    @Override
    @Transactional
    public BranchDto getByName(String name){
        BranchEntity branch  = branchRepository.findByName(name);
        return branch.asDto();
    }

    @Override
    public List<BranchEntity> getList() {
        return branchRepository.findAll();
    }

    @Override
    public ResultData<BranchDto> getList(RequestFilter filter) {

            Page<BranchEntity> branchList = branchRepository.findAll(
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

            List<BranchDto> branchDtos = branchList.stream().map(BranchEntity::asDto).collect(Collectors.toList());

            return new ResultData<>(branchDtos, branchList.getTotalElements());
        }

    @Override
    @Transactional
    public String delete(Long id) {
        Optional<BranchEntity> branch = branchRepository.findById(id);
        if (branch.isEmpty()) return "Not found";

        branchRepository.delete(branch.get());
        return "Success";
    }
}
