package com.karlo.shop.api.v1.mapper;

import com.karlo.shop.api.v1.model.CategoryDTO;
import com.karlo.shop.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);
}
