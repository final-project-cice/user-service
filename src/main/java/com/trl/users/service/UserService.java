package com.trl.users.service;

import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.UserWithEmailExistException;
import com.trl.users.exceptions.UserWithIdNotExistException;

import java.util.Date;
import java.util.Set;

public interface UserService {

    UserDTO create(UserDTO userDTO) throws UserWithEmailExistException;


    UserDTO updateFirstName(Long id, String firstName) throws UserWithIdNotExistException;

    UserDTO updateLastName(Long id, String lastName) throws UserWithIdNotExistException;

    UserDTO updateEmail(Long id, String email) throws UserWithIdNotExistException, UserWithEmailExistException;

    UserDTO updatePassword(Long id, String password) throws UserWithIdNotExistException;

    UserDTO updateBirthday(Long id, Date birthday) throws UserWithIdNotExistException;


    Boolean delete(Long id) throws UserWithIdNotExistException;


    UserDTO findById(Long id);

    Set<UserDTO> findByFirstName(String firstName);

    Set<UserDTO> findByLastName(String lastName);

    UserDTO findByEmail(String email);

    Set<UserDTO> findByFirstNameAndLastName(String firstName, String lastName);

    Set<UserDTO> findByBirthday(Date birthday);

    Set<UserDTO> findAll();

}