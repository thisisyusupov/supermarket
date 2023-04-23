package com.example.supermarket.controller;

import com.example.supermarket.dto.ProductDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product/")
public class ProductController {
    private final ProductService productService;

    @PostMapping("list")
    ResponseEntity<ResultData<ProductDto>> getList(@RequestBody RequestFilter filter) {
        return ResponseEntity.ok().body(productService.getList(filter));
    }

    @PostMapping("save")
    ResponseEntity<?> save(@RequestBody ProductDto productDto) {
        productService.save(productDto);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("get/{id}")
    ProductDto get(@PathVariable("id") Long id) {
        return productService.get(id);
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(productService.delete(id));
    }
}
