package com.trl.userservice.service;

import com.trl.userservice.controller.dto.UserDTO;
import com.trl.userservice.exceptions.UserWithEmailExistException;
import com.trl.userservice.exceptions.UserWithIdNotExistException;

import java.time.LocalDate;
import java.util.Set;

public interface UserService {

    UserDTO create(UserDTO userDTO) throws UserWithEmailExistException;


    UserDTO updateFirstName(Long id, String firstName) throws UserWithIdNotExistException;

    UserDTO updateLastName(Long id, String lastName) throws UserWithIdNotExistException;

    UserDTO updateEmail(Long id, String email) throws UserWithIdNotExistException, UserWithEmailExistException;

    UserDTO updatePassword(Long id, String password) throws UserWithIdNotExistException;

    UserDTO updateBirthday(Long id, LocalDate birthday) throws UserWithIdNotExistException;


    Boolean delete(Long id) throws UserWithIdNotExistException;


    UserDTO findById(Long id);

    Set<UserDTO> findByFirstName(String firstName);

    Set<UserDTO> findByLastName(String lastName);

    UserDTO findByEmail(String email);

    Set<UserDTO> findByFirstNameAndLastName(String firstName, String lastName);

    Set<UserDTO> findByBirthday(LocalDate birthday);

    Set<UserDTO> findAll();

}