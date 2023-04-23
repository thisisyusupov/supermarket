package com.example.supermarket.controller;

import com.example.supermarket.dto.PurchaseDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/purchase/")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;

    @PostMapping("list")
    ResponseEntity<ResultData<PurchaseDto>> getList(@RequestBody RequestFilter filter) {
        return ResponseEntity.ok().body(purchaseService.getList(filter));
    }

    @PostMapping("save")
    ResponseEntity<Long> save(@RequestBody PurchaseDto purchaseDto) {
        return ResponseEntity.ok().body(purchaseService.save(purchaseDto));
    }

    @GetMapping("{id}")
    PurchaseDto get(@PathVariable("id") Long id) {
        return purchaseService.get(id);
    }
    @DeleteMapping("{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(purchaseService.delete(id));
    }
}
