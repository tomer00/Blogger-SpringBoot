package com.tomer.blogger.services;


import com.tomer.blogger.exceptions.ResourceNotFoundException;
import com.tomer.blogger.modals.Category;
import com.tomer.blogger.payloads.CategoryDTO;
import com.tomer.blogger.repoaitories.RepoCategory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CateServiceImpl implements CategoryService {


    @Autowired
    private RepoCategory repo;

    @Autowired
    private ModelMapper mapper;


    @Override
    public CategoryDTO createCategory(CategoryDTO cate) {
        var save = repo.save(mapper.map(cate, Category.class));
        return mapper.map(save,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO cate, Integer id) {
        var caetT = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", " ID ", id.toString()));

        caetT.setCategoryDes(cate.getCategoryDes());
        caetT.setCategoryTitle(cate.getCategoryTitle());

        repo.save(caetT);

        return cate;
    }

    @Override
    public CategoryDTO getCategory(Integer id) {
        return mapper.map(
                repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", " ID ", id.toString()))
                , CategoryDTO.class
        );
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return repo.findAll().stream().map((i) -> mapper.map(i, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void removeCategory(Integer id) {
        repo.deleteById(id);
    }
}
