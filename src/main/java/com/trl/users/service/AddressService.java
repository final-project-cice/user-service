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

    Set<AddressDTO> findByCountry(String country) throws ExceptionAddressNotExist;

    Set<AddressDTO> findByCity(String city) throws ExceptionAddressNotExist;

    Set<AddressDTO> findByStreet(String street) throws ExceptionAddressNotExist;

    Set<AddressDTO> findByHouseNumber(String houseNumber) throws ExceptionAddressNotExist;

    Set<AddressDTO> findByPostcode(Long postCode) throws ExceptionAddressNotExist;

    Set<AddressDTO> findByUser(UserDTO userDTO) throws ExceptionAddressNotExist;

}
