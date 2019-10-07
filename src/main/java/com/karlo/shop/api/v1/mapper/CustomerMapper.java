package com.karlo.shop.api.v1.mapper;

import com.karlo.shop.api.v1.model.CustomerDTO;
import com.karlo.shop.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOtoCustomer(CustomerDTO customerDTO);
}
