package com.trl.userservice.service;

import com.trl.userservice.controller.dto.BankDataDTO;
import com.trl.userservice.controller.dto.UserDTO;
import com.trl.userservice.exceptions.UserIdIsNullException;
import com.trl.userservice.exceptions.UserIsNullException;
import com.trl.userservice.exceptions.UserNotHaveBankDataException;
import com.trl.userservice.exceptions.UserWithIdNotExistException;

import java.util.Set;

public interface BankDataService {

    BankDataDTO create(BankDataDTO bankDataDTO) throws UserWithIdNotExistException, UserIdIsNullException, UserIsNullException;

    Boolean deleteByUser(UserDTO userDTO) throws UserWithIdNotExistException, UserNotHaveBankDataException;

    Set<BankDataDTO> findByUser(UserDTO userDTO);

}