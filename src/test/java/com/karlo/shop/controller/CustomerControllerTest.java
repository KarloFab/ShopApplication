package com.karlo.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karlo.shop.api.v1.model.CustomerDTO;
import com.karlo.shop.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.karlo.shop.controller.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final Long ID = 1L;
    public static final String NAME = "Joe";
    public static final String LAST_NAME = "Joey";
    public static final String CUSTOMER_URL = "/api/v1/customers/";

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(NAME);
        customerDTO.setLastName(LAST_NAME);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstName("Bob");
        customerDTO2.setLastName("Bobby");

        List<CustomerDTO> customers = Arrays.asList(customerDTO, customerDTO2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers",hasSize(2)));

    }

    @Test
    public void getCustomerById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(NAME);
        customerDTO.setLastName(LAST_NAME);

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo(NAME)))
                .andExpect(jsonPath("$.lastName",equalTo(LAST_NAME)));
    }

    @Test
    public void createCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(NAME);
        customerDTO.setLastName(LAST_NAME);
        customerDTO.setCustomerUrl(CUSTOMER_URL);

        CustomerDTO dtoToReturn = new CustomerDTO();
        dtoToReturn.setFirstName(customerDTO.getFirstName());
        dtoToReturn.setLastName(customerDTO.getLastName());
        dtoToReturn.setCustomerUrl(CUSTOMER_URL);

        when(customerService.createCustomer(customerDTO)).thenReturn(dtoToReturn);

        mockMvc.perform(post(CUSTOMER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dtoToReturn)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_URL)));
    }

    @Test
    public void updateCustomer() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(NAME);
        customerDTO.setLastName(LAST_NAME);
        customerDTO.setCustomerUrl(CUSTOMER_URL);

        CustomerDTO dtoToReturn = new CustomerDTO();
        dtoToReturn.setFirstName(customerDTO.getFirstName());
        dtoToReturn.setLastName(customerDTO.getLastName());
        dtoToReturn.setCustomerUrl(CUSTOMER_URL);

        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(dtoToReturn);

        mockMvc.perform(put(CUSTOMER_URL + "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(dtoToReturn)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(CUSTOMER_URL)));
    }
}
