package com.example.supermarket.controller;

import com.example.supermarket.dto.PurchaseItemDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.service.PurchaseItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/purchaseItem/")
@RequiredArgsConstructor
public class PurchaseItemController {
    private final PurchaseItemService purchaseItemService;

    @PostMapping("list")
    ResponseEntity<ResultData<PurchaseItemDto>> getList(@RequestBody RequestFilter filter) {
        return ResponseEntity.ok().body(purchaseItemService.getList(filter));
    }

    @PostMapping("save")
    ResponseEntity<?> save(@RequestBody PurchaseItemDto purchaseItemDto) {
        purchaseItemService.save(purchaseItemDto);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("{id}")
    PurchaseItemDto get(@PathVariable("id") Long id) {
        return purchaseItemService.get(id);
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(purchaseItemService.delete(id));
    }
}
