package com.karlo.shop.service.impl;

import com.karlo.shop.api.v1.mapper.VendorMapper;
import com.karlo.shop.api.v1.model.VendorDTO;
import com.karlo.shop.api.v1.model.VendorListDTO;
import com.karlo.shop.controller.VendorController;
import com.karlo.shop.domain.Vendor;
import com.karlo.shop.repository.VendorRepository;
import com.karlo.shop.service.VendorService;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public VendorDTO getById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorUrl(id));

                    return vendorDTO;
                })
                .orElseThrow(RuntimeException::new);
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + "/" + id;
    }

    @Override
    public VendorListDTO getAllVendors() {
        List<VendorDTO> vendorDTOs = vendorRepository
                .findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                   return vendorDTO;
                })
                .collect(toList());

        return new VendorListDTO(vendorDTOs);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO vendorToReturn = vendorMapper.vendorToVendorDTO(savedVendor);
        vendorToReturn.setVendorUrl(getVendorUrl(vendor.getId()));

        return vendorToReturn;
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendorToSave = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendorToSave.setId(id);

        return saveAndReturnDTO(vendorToSave);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    if(vendorDTO.getName() != null){
                        vendor.setName(vendorDTO.getName());
                    }

                    return saveAndReturnDTO(vendor);
                }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
