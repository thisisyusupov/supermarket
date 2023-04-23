package com.example.supermarket.controller;

import com.example.supermarket.dto.SellDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.service.SellService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sell/")
@RequiredArgsConstructor
public class SellController {
    private final SellService sellService;

    @PostMapping("list")
    ResponseEntity<ResultData<SellDto>> getList(@RequestBody RequestFilter filter){
        return ResponseEntity.ok().body(sellService.getList(filter)) ;
    }

    @PostMapping("save")
    ResponseEntity<Long> save(@RequestBody SellDto sellDto) {
        return ResponseEntity.ok().body(sellService.save(sellDto));
    }

    @GetMapping("{id}")
    SellDto get(@PathVariable("id")Long id){
        return sellService.get(id);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> delete(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(sellService.delete(id));
    }
}
