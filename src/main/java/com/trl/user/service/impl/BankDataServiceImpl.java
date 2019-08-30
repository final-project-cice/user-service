package com.trl.user.service.impl;

import com.trl.user.controller.dto.BankDataDTO;
import com.trl.user.controller.dto.UserDTO;
import com.trl.user.exceptions.UserIdIsNullException;
import com.trl.user.exceptions.UserIsNullException;
import com.trl.user.exceptions.UserNotHaveBankDataException;
import com.trl.user.exceptions.UserWithIdNotExistException;
import com.trl.user.repository.BankDataRepository;
import com.trl.user.repository.UserRepository;
import com.trl.user.repository.entity.BankDataEntity;
import com.trl.user.repository.entity.UserEntity;
import com.trl.user.service.BankDataService;
import com.trl.user.service.converter.ConverterBankData;
import com.trl.user.service.converter.ConverterUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

/**
 *
 */
@Service
public class BankDataServiceImpl implements BankDataService {

    private static final Logger LOG = LoggerFactory.getLogger(BankDataServiceImpl.class);

    private final BankDataRepository bankDataRepository;
    private final UserRepository userRepository;

    public BankDataServiceImpl(BankDataRepository bankDataRepository, UserRepository userRepository) {
        this.bankDataRepository = bankDataRepository;
        this.userRepository = userRepository;
    }

    /**
     * @param bankDataDTO
     * @return
     */
    @Override
    public BankDataDTO create(BankDataDTO bankDataDTO) throws UserWithIdNotExistException, UserIdIsNullException, UserIsNullException {

        BankDataDTO bankDataResult = null;

        LOG.debug("************ create() ---> idUser = " + bankDataDTO.getUser() + " ---> bankDataDTO = " + bankDataDTO);

        // TODO: Nose si es necesario comprobar de null todos parametros que se van utilizar en meethodo. Es que se compica mucho la lectura ce mtodo.

        if (bankDataDTO.getUser() != null) {

            if (bankDataDTO.getUser().getId() != null && bankDataDTO.getUser().getId() != 0) {

                Optional<UserEntity> userFromRepositoryById = userRepository.findById(bankDataDTO.getUser().getId());

                if (userFromRepositoryById.isPresent()) {

                    BankDataEntity savedBankData = bankDataRepository.save(ConverterBankData.mapDTOToEntity(bankDataDTO));

                    LOG.debug("************ create() ---> savedBankData = " + savedBankData);

                    bankDataResult = ConverterBankData.mapEntityToDTO(savedBankData);

                } else {
                    throw new UserWithIdNotExistException("User with this id = '" + bankDataDTO.getUser().getId() + "' not exist.");
                }
            } else {
                throw new UserIdIsNullException("The parameter 'user' that is passed, contains value 'userId'. Value 'userId' equal NULL or ZERO. Not allowed parameters NULL or ZERO.");
            }
        } else {
            throw new UserIsNullException("The parameter 'user' that is passed, equal NULL. Not allowed parameter NULL.");
        }

        LOG.debug("************ create() ---> bankDataResult = " + bankDataResult);

        return bankDataResult;
    }

    @Override
    public Set<BankDataDTO> findByUser(UserDTO userDTO) {

        Set<BankDataDTO> bankDataSetResult = null;

        LOG.debug("************ findByUser() ---> userDTO = " + userDTO);

        Set<BankDataEntity> usersFromRepositoryById = bankDataRepository.findByUser(ConverterUser.mapDTOToEntity(userDTO));

        LOG.debug("************ findByUser() ---> usersFromRepositoryById = " + usersFromRepositoryById);

        bankDataSetResult = ConverterBankData.mapSetEntityToSetDTO(usersFromRepositoryById);

        LOG.debug("************ findByUser() ---> bankDataSetResult  = " + bankDataSetResult);

        return bankDataSetResult;
    }

    /**
     * @param userDTO
     * @return
     * @throws UserNotHaveBankDataException
     */
    @Transactional
    @Override
    public Boolean deleteByUser(UserDTO userDTO) throws UserNotHaveBankDataException {

        boolean isDeletedBankData = false;

        LOG.debug("************ deleteByUser() ---> userDTO = " + userDTO);

        UserEntity userEntity = ConverterUser.mapDTOToEntity(userDTO);

        Set<BankDataEntity> bankDataFromRepositoryByUser = bankDataRepository.findByUser(userEntity);

        LOG.debug("************ deleteByUser() ---> bankDataFromRepositoryByUser = " + bankDataFromRepositoryByUser);

        if (!bankDataFromRepositoryByUser.isEmpty()) {
            bankDataRepository.deleteByUser(userEntity);
            isDeletedBankData = true;
        } else {
            LOG.debug("************ deleteByUser() ---> this user = '" + userDTO + "' not have bankData");
            throw new UserNotHaveBankDataException("This user = '" + userDTO + "' not have bankData");
        }

        LOG.debug("************ deleteByUser() ---> isDeletedBankData = " + isDeletedBankData);

        return isDeletedBankData;
    }

}