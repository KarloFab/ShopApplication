package com.karlo.shop.bootstrap;

import com.karlo.shop.domain.Category;
import com.karlo.shop.domain.Customer;
import com.karlo.shop.repository.CategoryRepository;
import com.karlo.shop.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;

public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();

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

    private void loadCustomers() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("John");
        customer.setLastName("Johhny");
        customerRepository.save(customer);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Bob");
        customer2.setLastName("Bobby");
        customerRepository.save(customer2);
    }
}
