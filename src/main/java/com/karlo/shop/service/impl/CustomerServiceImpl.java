package com.karlo.shop.service.impl;

import com.karlo.shop.api.v1.mapper.CustomerMapper;
import com.karlo.shop.api.v1.model.CustomerDTO;
import com.karlo.shop.domain.Customer;
import com.karlo.shop.repository.CustomerRepository;
import com.karlo.shop.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl("/api/v1/customer" + customer.getId());
                    return customerDTO;
                }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        return saveAndReturnCustomerDTO(customerMapper.customerDTOToCustomer(customerDTO));
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
        customer.setId(id);

        return saveAndReturnCustomerDTO(customer);
    }

    private CustomerDTO saveAndReturnCustomerDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO dtoToReturn = customerMapper.customerToCustomerDTO(customer);

        dtoToReturn.setCustomerUrl("/api/v1/customer/" + savedCustomer.getId());

        return dtoToReturn;
    }
}
