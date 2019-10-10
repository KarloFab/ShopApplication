package com.karlo.shop.service;

import com.karlo.shop.api.v1.model.VendorDTO;
import com.karlo.shop.api.v1.model.VendorListDTO;
import org.springframework.stereotype.Service;

@Service
public interface VendorService {

    VendorDTO getById(Long id);

    VendorListDTO getAllVendors();

    VendorDTO createNewVendor(VendorDTO vendorDTO);

    VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

    void deleteVendorById(Long id);
}
