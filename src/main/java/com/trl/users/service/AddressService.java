package com.trl.users.service;

import com.trl.users.controller.dto.AddressDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.*;

import java.util.Set;

public interface AddressService {

    AddressDTO create(AddressDTO addressDTO) throws ExceptionUserWithIdNotExist, ExceptionUserIdIsNull, ExceptionUserIsNull;


    AddressDTO updateCountry(Long id, String country) throws ExceptionAddressNotExist;

    AddressDTO updateCity(Long id, String city) throws ExceptionAddressNotExist;

    AddressDTO updateStreet(Long id, String street) throws ExceptionAddressNotExist;

    AddressDTO updateHouseNumber(Long id, String houseNumber) throws ExceptionAddressNotExist;

    AddressDTO updatePostCode(Long id, Long postCode) throws ExceptionAddressNotExist;


    Boolean deleteById(Long id);

    Boolean deleteByUser(UserDTO userDTO) throws ExceptionUserWithIdNotExist, ExceptionUserNotHaveAddress;


    AddressDTO findById(Long id) throws ExceptionAddressNotExist;

    Set<AddressDTO> findByCountry(String country);

    Set<AddressDTO> findByCity(String city);

    Set<AddressDTO> findByStreet(String street);

    Set<AddressDTO> findByHouseNumber(String houseNumber);

    Set<AddressDTO> findByPostCode(String postCode);

    Set<AddressDTO> findByUser(UserDTO userDTO);

}
