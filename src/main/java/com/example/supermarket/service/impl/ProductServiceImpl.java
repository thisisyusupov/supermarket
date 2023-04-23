package com.example.supermarket.service.impl;

import com.example.supermarket.dto.ProductDto;
import com.example.supermarket.dto.PurchaseDto;
import com.example.supermarket.dto.common.RequestFilter;
import com.example.supermarket.dto.common.ResultData;
import com.example.supermarket.model.ProductEntity;
import com.example.supermarket.model.PurchaseEntity;
import com.example.supermarket.repositotry.ProductRepository;
import com.example.supermarket.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void save(ProductDto productDto) {
        ProductEntity product;
        if (productDto.getId() != null){
            Optional<ProductEntity> productOpt = productRepository.findById(productDto.getId());
            product = productOpt.orElseGet(ProductEntity:: new);
        }else {
            product = new ProductEntity();
        }
        product.setName(productDto.getName());
        productRepository.save(product);
    }

    @Override
    public ProductDto get(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        return product.get().asDto();
    }

    @Override
    public ProductDto getByName(String name) {
        ProductEntity product = productRepository.findByName(name);
        return product.asDto();
    }

    @Override
    public List<ProductEntity> getList() {
        return productRepository.findAll();
    }

    @Override
    public ResultData<ProductDto> getList(RequestFilter filter) {

        Page<ProductEntity> productList = productRepository.findAll(
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

        List<ProductDto> productDtos = productList.stream().map(ProductEntity::asDto).collect(Collectors.toList());

        return new ResultData<>(productDtos, productList.getTotalElements());
    }

    @Override
    public String delete(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()) return "Not found";

        productRepository.delete(product.get());
        return "Success";
    }
}
