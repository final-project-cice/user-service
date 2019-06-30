package com.trl.users.service;

import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.ExceptionUserWithEmailExist;
import com.trl.users.exceptions.ExceptionUserWithIdNotExist;

import java.util.Date;
import java.util.Set;

public interface UserService {

    UserDTO create(UserDTO userDTO) throws ExceptionUserWithEmailExist;


    UserDTO updateFirstName(Long id, String firstName) throws ExceptionUserWithIdNotExist;

    UserDTO updateLastName(Long id, String lastName) throws ExceptionUserWithIdNotExist;

    UserDTO updateEmail(Long id, String email) throws ExceptionUserWithIdNotExist, ExceptionUserWithEmailExist;

    UserDTO updatePassword(Long id, String password) throws ExceptionUserWithIdNotExist;

    UserDTO updateBirthday(Long id, Date birthday) throws ExceptionUserWithIdNotExist;


    Boolean delete(Long id) throws ExceptionUserWithIdNotExist;


    UserDTO findById(Long id);

    Set<UserDTO> findByFirstName(String firstName);

    Set<UserDTO> findByLastName(String lastName);

    UserDTO findByEmail(String email);

    Set<UserDTO> findByFirstNameAndLastName(String firstName, String lastName);

    Set<UserDTO> findByBirthday(Date birthday);

    Set<UserDTO> findAll();

}