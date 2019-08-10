package com.trl.user.service;

import com.trl.user.controller.dto.BankDataDTO;
import com.trl.user.controller.dto.UserDTO;
import com.trl.user.exceptions.UserIdIsNullException;
import com.trl.user.exceptions.UserIsNullException;
import com.trl.user.exceptions.UserNotHaveBankDataException;
import com.trl.user.exceptions.UserWithIdNotExistException;

import java.util.Set;

public interface BankDataService {

    BankDataDTO create(BankDataDTO bankDataDTO) throws UserWithIdNotExistException, UserIdIsNullException, UserIsNullException;

    Boolean deleteByUser(UserDTO userDTO) throws UserWithIdNotExistException, UserNotHaveBankDataException;

    Set<BankDataDTO> findByUser(UserDTO userDTO);

}