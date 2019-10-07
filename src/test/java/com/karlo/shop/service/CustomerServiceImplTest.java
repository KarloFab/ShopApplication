package com.karlo.shop.service;

import com.karlo.shop.api.v1.mapper.CustomerMapper;
import com.karlo.shop.api.v1.model.CustomerDTO;
import com.karlo.shop.domain.Customer;
import com.karlo.shop.repository.CustomerRepository;
import com.karlo.shop.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    public static final String NAME = "Joe";
    public static final String LAST_NAME = "Joey";

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void getAllCustomers() throws Exception{

        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(3, customerDTOS.size());
    }

    @Test
    public void getCustomerById() throws Exception{
        Customer customer = new Customer();
        customer.setFirstName(NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(customer));

        CustomerDTO customerDTO = customerService.getCustomerById(anyLong());

        assertEquals(customerDTO.getFirstName(), NAME);
        assertEquals(customerDTO.getLastName(), LAST_NAME);
    }

}
