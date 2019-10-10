package com.karlo.shop.service;

import com.karlo.shop.api.v1.mapper.VendorMapper;
import com.karlo.shop.api.v1.model.VendorDTO;
import com.karlo.shop.api.v1.model.VendorListDTO;
import com.karlo.shop.domain.Vendor;
import com.karlo.shop.repository.VendorRepository;
import com.karlo.shop.service.impl.VendorServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class VendorServiceImplTest {

    public static final String NAME_1 = "Vendor";
    public static final long ID_1 = 1L;
    public static final String NAME_2 = "VendorSecond";
    public static final long ID_2 = 2L;

    @Mock
    VendorRepository vendorRepository;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }

    @Test
    public void getVendorById() throws Exception {
        Vendor vendor = getFirstVendor();

        given(vendorRepository.findById(vendor.getId())).willReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getById(1L);

        then(vendorRepository).should(times(1)).findById(anyLong());

        assertThat(vendorDTO.getName(), is(equalTo(NAME_1)));
    }

    @Test
    public void getAllVendors() throws Exception {
        List<Vendor> vendors = Arrays.asList(getFirstVendor(),getSecondVendor());
        given(vendorRepository.findAll()).willReturn(vendors);

        VendorListDTO vendorListDTO = vendorService.getAllVendors();

        then(vendorRepository).should(times(1)).findAll();
        assertThat(vendorListDTO.getVendors().size(), is(equalTo(2)));
    }

    @Test
    public void createNewVendor() throws Exception {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME_1);

        Vendor vendor = getFirstVendor();

        given(vendorRepository.save(Mockito.any(Vendor.class))).willReturn(vendor);

        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO);

        then(vendorRepository).should().save(Mockito.any(Vendor.class));
    }


    private Vendor getSecondVendor() {
        Vendor vendor = new Vendor();
        vendor.setId(ID_2);
        vendor.setName(NAME_2);

        return vendor;
    }

    private Vendor getFirstVendor() {
        Vendor vendor = new Vendor();
        vendor.setId(ID_1);
        vendor.setName(NAME_1);

        return vendor;
    }
}
