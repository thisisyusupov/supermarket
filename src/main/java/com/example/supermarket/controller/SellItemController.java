package com.example.supermarket.controller;

import com.example.supermarket.dto.SellItemDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.service.SellItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sellItem/")
@RequiredArgsConstructor
public class SellItemController {
    private final SellItemService sellItemService;

    @PostMapping("list")
    ResponseEntity<ResultData<SellItemDto>> getList(@RequestBody RequestFilter filter) {
        return ResponseEntity.ok().body( sellItemService.getList(filter));
    }

    @PostMapping("save")
    ResponseEntity<?> save(@RequestBody SellItemDto sellItemDto){
        sellItemService.save(sellItemDto);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("{id}")
    SellItemDto get(@PathVariable("id")Long id){
        return sellItemService.get(id);
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(sellItemService.delete(id));
    }
}
