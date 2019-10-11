package com.karlo.shop.bootstrap;

import com.karlo.shop.domain.Category;
import com.karlo.shop.domain.Customer;
import com.karlo.shop.domain.Vendor;
import com.karlo.shop.repository.CategoryRepository;
import com.karlo.shop.repository.CustomerRepository;
import com.karlo.shop.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;

public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCustomers();
        loadVendors();
        loadCategories();
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Vendor1");
        vendorRepository.save(vendor1);

        Vendor vendor2 = new Vendor();
        vendor2.setName("Vendor2");
        vendorRepository.save(vendor2);
    }

    private void loadCategories() {
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
