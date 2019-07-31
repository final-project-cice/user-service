package com.trl.users.service;

import com.trl.users.controller.dto.BankDataDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.*;

import java.util.Set;

public interface BankDataService {

    BankDataDTO create(BankDataDTO bankDataDTO) throws UserWithIdNotExistException, UserIdIsNullException, UserIsNullException;

    Boolean deleteByUser(UserDTO userDTO) throws UserWithIdNotExistException, UserNotHaveBankDataException;

    Set<BankDataDTO> findByUser(UserDTO userDTO);

}