package com.tomer.blogger.services;

import com.tomer.blogger.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO user);

    CategoryDTO updateCategory(CategoryDTO user, Integer id);

    CategoryDTO getCategory(Integer id);

    List<CategoryDTO> getAllCategories();

    void removeCategory(Integer id);
}
