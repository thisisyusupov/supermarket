package com.example.supermarket.controller;

import com.example.supermarket.dto.CompanyDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.CompanyEntity;
import com.example.supermarket.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/company/")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("list")
    ResponseEntity<ResultData<CompanyDto>>getList(@RequestBody RequestFilter filter) {
        return ResponseEntity.ok().body( companyService.getList(filter));
    }

    @GetMapping("add/{name}")
    List<CompanyEntity> save(@PathVariable("id") String name) {
        return companyService.save(name);
    }

    @PostMapping("save")
    ResponseEntity<?> save(@RequestBody CompanyDto companyDto) {
        companyService.save(companyDto);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("get/{id}")
    CompanyDto get(@PathVariable("id") Long id) {
        return companyService.get(id);
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity<?> delete(@PathVariable ("id") Long id) {
        return ResponseEntity.ok().body(companyService.delete(id));
    }
}
