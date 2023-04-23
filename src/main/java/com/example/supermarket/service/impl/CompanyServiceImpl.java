package com.example.supermarket.service.impl;

import com.example.supermarket.dto.CompanyDto;
import com.example.supermarket.dto.PurchaseDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.CompanyEntity;
import com.example.supermarket.model.PurchaseEntity;
import com.example.supermarket.repositotry.CompanyRepository;
import com.example.supermarket.service.CompanyService;
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
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public void save(CompanyDto companyDto) {

        CompanyEntity company;
        if(companyDto.getId() != null){
            Optional<CompanyEntity> companyOpt = companyRepository.findById(companyDto.getId());
            company = companyOpt.orElseGet(CompanyEntity::new);
        }else {
            company = new CompanyEntity();
        }

        company.setName(companyDto.getName());
        company.setTin(companyDto.getTin());
        company.setBankAccount(companyDto.getBankAccount());
        company.setAdress(companyDto.getAdress());
        company.setEmail(companyDto.getEmail());
        companyRepository.save(company);
    }

    @Override
    public List<CompanyEntity> save(String name) {
        return null;
    }

    @Override
    public ResultData<CompanyDto> getList(RequestFilter filter) {

        Page<CompanyEntity> companyList = companyRepository.findAll(
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

        List<CompanyDto> companyDtos = companyList.stream().map(CompanyEntity::asDto).collect(Collectors.toList());

        return new ResultData<>(companyDtos, companyList.getTotalElements());
    }

    private List<CompanyEntity> getCompanyList() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDto get(Long id) {
        Optional<CompanyEntity> company = companyRepository.findById(id);
        return company.get().asDto();
    }

    @Override
    @Transactional
    public CompanyDto getByName(String name){
        CompanyEntity company  = companyRepository.findByName(name);
        return company.asDto();
    }

    @Override
    public List<CompanyEntity> getList() {
        return companyRepository.findAll();
    }
    @Override
    @Transactional
    public String delete(Long id){
        Optional<CompanyEntity> company = companyRepository.findById(id);
        if(company.isEmpty()) return "Not found";

        companyRepository.delete(company.get());
        return "Success";
    }
}
