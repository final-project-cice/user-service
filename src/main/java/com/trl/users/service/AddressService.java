package com.trl.users.service;

import com.trl.users.controller.dto.AddressDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.*;

import java.util.Set;

public interface AddressService {

    AddressDTO create(AddressDTO addressDTO) throws UserWithIdNotExistException, UserIdIsNullException, UserIsNullException;


    AddressDTO updateCountry(Long id, String country) throws AddressNotExistException;

    AddressDTO updateCity(Long id, String city) throws AddressNotExistException;

    AddressDTO updateStreet(Long id, String street) throws AddressNotExistException;

    AddressDTO updateHouseNumber(Long id, String houseNumber) throws AddressNotExistException;

    AddressDTO updatePostCode(Long id, Long postCode) throws AddressNotExistException;


    Boolean deleteById(Long id);

    Boolean deleteByUser(UserDTO userDTO) throws UserWithIdNotExistException, UserNotHaveAddressException;


    AddressDTO findById(Long id) throws AddressNotExistException;

    Set<AddressDTO> findByCountry(String country) throws AddressNotExistException;

    Set<AddressDTO> findByCity(String city) throws AddressNotExistException;

    Set<AddressDTO> findByStreet(String street) throws AddressNotExistException;

    Set<AddressDTO> findByHouseNumber(String houseNumber) throws AddressNotExistException;

    Set<AddressDTO> findByPostcode(Long postCode) throws AddressNotExistException;

    Set<AddressDTO> findByUser(UserDTO userDTO) throws AddressNotExistException;

}
