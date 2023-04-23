package com.example.supermarket.controller;

import com.example.supermarket.dto.BranchDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.BranchEntity;
import com.example.supermarket.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping ("api/branch/")
public class BranchController {
    private final BranchService branchService;

    @PostMapping("list")
    ResponseEntity<ResultData<BranchDto>> getList(@RequestBody RequestFilter filter) {
        return ResponseEntity.ok().body( branchService.getList(filter));
    }

    @PostMapping("save")
    ResponseEntity<?> save(@RequestBody BranchDto branchDto) {
        branchService.save(branchDto);
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("get/{id}")
    BranchDto get(@PathVariable ("id") Long id) {
        return branchService.get(id);
    }

    @DeleteMapping("delete/{id}")
    ResponseEntity<?> delete(@PathVariable ("id") Long id){
        return ResponseEntity.ok().body(branchService.delete(id));
    }
}
