package com.karlo.shop.api.v1.mapper;


import com.karlo.shop.api.v1.model.CategoryDTO;
import com.karlo.shop.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "Joe";
    public static final long ID = 1L;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(Long.valueOf(1L), categoryDTO.getId());
        assertEquals("Joe", categoryDTO.getName());
    }
}
