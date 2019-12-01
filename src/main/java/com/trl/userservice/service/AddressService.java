package com.trl.userservice.service;

import com.trl.userservice.controller.dto.AddressDTO;

import org.springframework.data.domain.Page;

import java.util.List;

public interface AddressService {

    AddressDTO add(Long userId, AddressDTO address);


    AddressDTO getByAddressId(Long addressId);

    Page<AddressDTO> getPageOfAddressesByUserId(Long userId, Integer startPage, Integer pageSize);

    Page<AddressDTO> getPageOfSortedAddressesByUserId(Long userId, Integer startPage, Integer pageSize, String sortOrder);


    AddressDTO updateByAddressId(Long addressId, AddressDTO address);


    AddressDTO deleteByAddressId(Long addressId);

    List<AddressDTO> deleteAllAddressesByUserId(Long userId);
}