package com.example.springboot.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.dto.ProductRecordDto;
import com.example.springboot.exceptions.NotFoundException;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;

@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;

  public ProductModel save(ProductRecordDto productRecordDto) {
    var productModel = new ProductModel();
    BeanUtils.copyProperties(productRecordDto, productModel);
    return productRepository.save(productModel);
  }

  public List<ProductModel> list() {
    return productRepository.findAll();
  }

  public ProductModel findById(UUID id) throws NotFoundException {
    Optional<ProductModel> product = productRepository.findById(id);
    if (product.isEmpty()) {
      throw new NotFoundException("Product not found!");
    }

    return product.get();
  }

  public ProductModel update(UUID id, ProductRecordDto productRecordDto) throws NotFoundException {
    var productModel = findById(id);
    BeanUtils.copyProperties(productRecordDto, productModel);
    return productRepository.save(productModel);
  }

  public void delete(UUID id) throws NotFoundException {
    var productModel = findById(id);
    productRepository.delete(productModel);
  }
}
