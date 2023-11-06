package com.tomer.blogger.controllers;


import com.tomer.blogger.payloads.ApiResponse;
import com.tomer.blogger.payloads.CategoryDTO;
import com.tomer.blogger.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cate")
public class CategoryController {


    @Autowired
    private CategoryService service;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO user) {
        return new ResponseEntity<>(service.createCategory(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO user, @PathVariable Integer id) {
        return new ResponseEntity<>(service.updateCategory(user, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getCategory(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(service.getAllCategories());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id) {
        service.removeCategory(id);
        return ResponseEntity.ok(new ApiResponse("Category Deleted Successfully", 202));
    }
}
