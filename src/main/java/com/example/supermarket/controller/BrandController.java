package com.example.supermarket.controller;

import com.example.supermarket.dto.BrandDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/brand/")
public class BrandController {
    private final BrandService brandService;

    @PostMapping("list")
    ResponseEntity<ResultData<BrandDto>> getList(@RequestBody RequestFilter filter) {
        return ResponseEntity.ok().body( brandService.getList(filter));
    }

    @PostMapping("save")
    ResponseEntity<?> save(@RequestBody BrandDto brandDto) {
        brandService.save(brandDto);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("get/{id}")
    BrandDto get(@PathVariable("id") Long id) {
        return brandService.get(id);
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(brandService.delete(id));
    }
}
