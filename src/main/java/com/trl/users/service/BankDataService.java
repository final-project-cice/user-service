package com.trl.users.service;

import com.trl.users.controller.dto.BankDataDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.ExceptionUserNotHaveBankData;
import com.trl.users.exceptions.ExceptionUserWithIdNotExist;

import java.util.Set;

public interface BankDataService {

    BankDataDTO create(BankDataDTO bankDataDTO);


    Boolean deleteById(Long id);

    Boolean deleteByUser(UserDTO userDTO) throws ExceptionUserWithIdNotExist, ExceptionUserNotHaveBankData;


    BankDataDTO findById(Long id);

    Set<BankDataDTO> findByUser(UserDTO userDTO);

}