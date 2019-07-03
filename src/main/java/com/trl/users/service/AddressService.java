package com.trl.users.service;

import com.trl.users.controller.dto.AddressDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.*;

import java.util.Set;

public interface AddressService {

    AddressDTO create(AddressDTO addressDTO) throws ExceptionUserWithIdNotExist, ExceptionUserIdIsNull, ExceptionUserIsNull;


    AddressDTO updateCountry(Long id, String country) throws ExceptionAddressWithIdNotExist;

    AddressDTO updateCity(Long id, String city) throws ExceptionAddressWithIdNotExist;

    AddressDTO updateStreet(Long id, String street) throws ExceptionAddressWithIdNotExist;

    AddressDTO updateHouseNumber(Long id, String houseNumber) throws ExceptionAddressWithIdNotExist;

    AddressDTO updatePostCode(Long id, Long postCode) throws ExceptionAddressWithIdNotExist;


    Boolean deleteById(Long id);

    Boolean deleteByUser(UserDTO userDTO) throws ExceptionUserWithIdNotExist, ExceptionUserNotHaveAddress;


    AddressDTO findById(Long id);

    Set<AddressDTO> findByCountry(String country);

    Set<AddressDTO> findByCity(String city);

    Set<AddressDTO> findByStreet(String street);

    Set<AddressDTO> findByHouseNumber(String houseNumber);

    Set<AddressDTO> findByPostCode(String postCode);

    Set<AddressDTO> findByUser(UserDTO userDTO);

}
