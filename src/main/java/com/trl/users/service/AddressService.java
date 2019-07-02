package com.trl.users.service;

import com.trl.users.controller.dto.AddressDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.ExceptionUserIdIsNull;
import com.trl.users.exceptions.ExceptionUserIsNull;
import com.trl.users.exceptions.ExceptionUserNotHaveAddress;
import com.trl.users.exceptions.ExceptionUserWithIdNotExist;

import java.util.Set;

public interface AddressService {

    AddressDTO create(AddressDTO addressDTO) throws ExceptionUserWithIdNotExist, ExceptionUserIdIsNull, ExceptionUserIsNull;


    AddressDTO updateCountry(Long id, String country);

    AddressDTO updateCity(Long id, String city);

    AddressDTO updateStreet(Long id, String street);

    AddressDTO updateHouseNumber(Long id, String houseNumber);

    AddressDTO updatePostCode(Long id, String postCode);


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
