package com.karlo.shop.bootstrap;

import com.karlo.shop.domain.Category;
import com.karlo.shop.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;

public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category vegetables = new Category();
        fruits.setName("Vegetables");

        Category socks = new Category();
        fruits.setName("Socks");

        categoryRepository.save(fruits);
        categoryRepository.save(vegetables);
        categoryRepository.save(socks);
    }
}
