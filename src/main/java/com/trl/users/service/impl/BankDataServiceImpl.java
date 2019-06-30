package com.trl.users.service.impl;

import com.trl.users.controller.dto.BankDataDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.ExceptionUserNotHaveBankData;
import com.trl.users.repository.BankDataRepository;
import com.trl.users.repository.entity.BankDataEntity;
import com.trl.users.repository.entity.UserEntity;
import com.trl.users.service.BankDataService;
import com.trl.users.service.converter.ConverterUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 *
 */
@AllArgsConstructor
@Slf4j
@Service
public class BankDataServiceImpl implements BankDataService {

    private final BankDataRepository bankDataRepository;

    /**
     * @param bankDataDTO
     * @return
     */
    @Override
    public BankDataDTO create(BankDataDTO bankDataDTO) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteById(Long id) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     * @param userDTO
     * @return
     * @throws ExceptionUserNotHaveBankData
     */
    @Transactional
    @Override
    public Boolean deleteByUser(UserDTO userDTO) throws ExceptionUserNotHaveBankData {

        boolean isDeletedBankData = false;

        log.debug("************ deleteByUser() ---> userDTO = " + userDTO);

        UserEntity userEntity = ConverterUser.mapDTOToEntity(userDTO);

        List<BankDataEntity> bankDataFromRepositoryByUser = bankDataRepository.findByUser(userEntity);

        log.debug("************ deleteByUser() ---> bankDataFromRepositoryByUser = " + bankDataFromRepositoryByUser);

        if (!bankDataFromRepositoryByUser.isEmpty()) {
            bankDataRepository.deleteByUser(userEntity);
            isDeletedBankData = true;
        } else {
            log.debug("************ deleteByUser() ---> this user = '" + userDTO + "' not have bankData");
            throw new ExceptionUserNotHaveBankData("This user = '" + userDTO + "' not have bankData");
        }

        log.debug("************ deleteByUser() ---> isDeletedBankData = " + isDeletedBankData);

        return isDeletedBankData;
    }

    @Override
    public BankDataDTO findById(Long id) {

        // TODO: Terminar este metodo.

        return null;
    }

    /**
     *
     */
    @Override
    public Set<BankDataDTO> findByUser(UserDTO userDTO) {

        // TODO: Terminar este metodo.

        return null;
    }

}