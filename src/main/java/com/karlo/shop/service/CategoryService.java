package com.karlo.shop.service;

import com.karlo.shop.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getCategories();

    CategoryDTO getCategoryByName(String name);
}
