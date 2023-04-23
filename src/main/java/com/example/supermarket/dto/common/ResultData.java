package com.example.supermarket.dto.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultData<T> {
    private List<T> data = new ArrayList<>();
    private Long total = 0L;

    public ResultData(List<T> data, Long total) {
        this.data = data;
        this.total = total;
    }
}
