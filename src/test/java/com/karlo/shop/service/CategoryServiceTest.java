package com.karlo.shop.service;

import com.karlo.shop.api.v1.mapper.CategoryMapper;
import com.karlo.shop.api.v1.model.CategoryDTO;
import com.karlo.shop.domain.Category;
import com.karlo.shop.repository.CategoryRepository;
import com.karlo.shop.service.impl.CategoryServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategories() throws Exception{

        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOS = categoryService.getCategories();

        assertEquals(3, categoryDTOS.size());
    }

    @Test
    public void getCategoryByName() throws Exception{
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        Assert.assertEquals(Long.valueOf(ID), categoryDTO.getId());
        Assert.assertEquals(NAME, categoryDTO.getName());
    }
}
