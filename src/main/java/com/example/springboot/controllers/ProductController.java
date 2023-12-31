package com.example.springboot.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.ProductRecordDto;
import com.example.springboot.exceptions.ApiException;
import com.example.springboot.models.ProductModel;
import com.example.springboot.services.ProductService;

import jakarta.validation.Valid;

@RestController
public class ProductController {
  @Autowired
  ProductService productService;

  @PostMapping("/products")
  public ResponseEntity<ProductModel> save(@RequestBody @Valid ProductRecordDto productRecordDto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productRecordDto));
  }

  @GetMapping("/products")
  public ResponseEntity<List<ProductModel>> getAll() {
    List<ProductModel> productsList = productService.list();
    if (!productsList.isEmpty()) {
      for (ProductModel product : productsList) {
        UUID id = product.getIdProduct();
        product.add(linkTo(methodOn(ProductController.class).getOne(id)).withSelfRel());
      }
    }
    return ResponseEntity.status(HttpStatus.OK).body(productsList);
  }

  @GetMapping("/products/{id}")
  public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id) {
    try {
      var product = productService.findById(id);
      product.add(linkTo(methodOn(ProductController.class).getAll()).withRel("Product List"));
      return ResponseEntity.status(HttpStatus.OK).body(product);
    } catch (ApiException e) {
      return ResponseEntity.status(e.getStatusCode()).body(e.getBody());
    }
  }

  @PutMapping("/products/{id}")
  public ResponseEntity<Object> put(@RequestBody @Valid ProductRecordDto productRecordDto,
      @PathVariable(value = "id") UUID id) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, productRecordDto));
    } catch (ApiException e) {
      return ResponseEntity.status(e.getStatusCode()).body(e.getBody());
    }
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id) {
    try {
      productService.delete(id);
      var body = new HashMap<String, String>();
      body.put("message", "Succesfully deleted product!");
      return ResponseEntity.status(HttpStatus.OK).body(body);
    } catch (ApiException e) {
      return ResponseEntity.status(e.getStatusCode()).body(e.getBody());
    }
  }
}
