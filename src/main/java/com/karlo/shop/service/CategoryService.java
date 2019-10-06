package com.karlo.shop.service;

import com.karlo.shop.api.v1.model.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    List<CategoryDTO> getCategories();

    CategoryDTO getCategoryByName(String name);
}
