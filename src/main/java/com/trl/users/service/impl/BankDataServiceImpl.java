package com.trl.users.service.impl;

import com.trl.users.controller.dto.BankDataDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.ExceptionSomeParametersPassedToMethodEqualNull;
import com.trl.users.exceptions.ExceptionUserIdIsNull;
import com.trl.users.exceptions.ExceptionUserNotHaveBankData;
import com.trl.users.exceptions.ExceptionUserWithIdNotExist;
import com.trl.users.repository.BankDataRepository;
import com.trl.users.repository.UserRepository;
import com.trl.users.repository.entity.BankDataEntity;
import com.trl.users.repository.entity.UserEntity;
import com.trl.users.service.BankDataService;
import com.trl.users.service.converter.ConverterBankData;
import com.trl.users.service.converter.ConverterUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 *
 */
@AllArgsConstructor
@Slf4j
@Service
public class BankDataServiceImpl implements BankDataService {

    private final BankDataRepository bankDataRepository;
    private final UserRepository userRepository;

    /**
     * @param bankDataDTO
     * @return
     */
    @Override
    public BankDataDTO create(BankDataDTO bankDataDTO) throws ExceptionSomeParametersPassedToMethodEqualNull, ExceptionUserWithIdNotExist, ExceptionUserIdIsNull {

        BankDataDTO bankDataResult = null;

        log.debug("************ create() ---> idUser = " + bankDataDTO.getUserId() + " ---> bankDataDTO = " + bankDataDTO);

        if (bankDataDTO != null) {

            if (bankDataDTO.getUserId().getId() != null && bankDataDTO.getUserId().getId() != 0) {

                Optional<UserEntity> userFromRepositoryById = userRepository.findById(bankDataDTO.getUserId().getId());

                if (userFromRepositoryById.isPresent()) {

                    BankDataEntity savedBankData = bankDataRepository.save(ConverterBankData.mapDTOToEntity(bankDataDTO));

                    log.debug("************ create() ---> savedBankData = " + savedBankData);

                    bankDataResult = ConverterBankData.mapEntityToDTO(savedBankData);

                } else {
                    throw new ExceptionUserWithIdNotExist("User with this id = '" + bankDataDTO.getUserId().getId() + "' not exist.");
                }
            } else {
                throw new ExceptionUserIdIsNull("The parameter that is passed 'bankDataDTO' to the method is NULL.");
            }

        } else {
            throw new ExceptionSomeParametersPassedToMethodEqualNull("The parameter that is passed to the method is NULL.");
        }

        log.debug("************ create() ---> bankDataResult = " + bankDataResult);

        return bankDataResult;
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