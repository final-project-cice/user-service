package com.trl.userservice.service.impl;

import com.trl.userservice.controller.dto.AddressDTO;
import com.trl.userservice.controller.dto.BankDataDTO;
import com.trl.userservice.controller.dto.UserDTO;
import com.trl.userservice.exceptions.DataNotFoundException;
import com.trl.userservice.exceptions.IllegalValueException;
import com.trl.userservice.exceptions.TheSameValueException;
import com.trl.userservice.exceptions.UserNotExistException;
import com.trl.userservice.repository.AddressRepository;
import com.trl.userservice.repository.BankDataRepository;
import com.trl.userservice.repository.UserRepository;
import com.trl.userservice.repository.entity.UserEntity;
import com.trl.userservice.service.UserService;
import com.trl.userservice.service.converter.UserConverter;
import com.trl.userservice.utils.AddressUtils;
import com.trl.userservice.utils.BankDataUtils;
import com.trl.userservice.utils.UserUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

import java.util.Optional;

/**
 * This class is designed to implementation methods of {@code UserService}.
 *
 * @author Tsyupryk Roman
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS = "One of parameters is illegal. Parameters must be " +
            "not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.";
    private static final String EXCEPTION_MESSAGE_USER_BY_USER_ID_NOT_EXIST = "User with this userId = %s not exist.";
    private static final String EXCEPTION_MESSAGE_USERS_NOT_EXIST = "Users not exists.";

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final UserUtils userUtils;
    private final AddressRepository addressRepository;
    private final AddressUtils addressUtils;
    private final BankDataRepository bankDataRepository;
    private final BankDataUtils bankDataUtils;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, UserUtils userUtils, AddressRepository addressRepository, AddressUtils addressUtils, BankDataRepository bankDataRepository, BankDataUtils bankDataUtils) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.userUtils = userUtils;
        this.addressRepository = addressRepository;
        this.addressUtils = addressUtils;
        this.bankDataRepository = bankDataRepository;
        this.bankDataUtils = bankDataUtils;
    }

    /**
     * Add the {@literal UserDTO}.
     *
     * @param user must not be {@literal null}.
     * @return The {@literal UserDTO}.
     * @throws IllegalArgumentException in case the given {@code user} is {@literal null},
     * @throws IllegalValueException    in case if one of the parameter fields is {@literal null}
     */
    @Override
    public UserDTO add(UserDTO user) {
        UserDTO userResult = null;

        if (user == null) {
            LOG.debug("************ add() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        userUtils.checkFieldsOfUser(user);

        if (!user.getBankData().isEmpty()) {
            user.getBankData().forEach(bankDataUtils::checkFieldsOfBankData);
        }

        if (!user.getAddress().isEmpty()) {
            user.getAddress().forEach(addressUtils::checkFieldsOfAddress);
        }

        // TODO: Find information. How can these lines of code be done better.
        // TODO: This is a very bad solution. I don't have time to find the best solution.
        Long generatedUserId = userRepository.count() + 1;
        userRepository.add(generatedUserId, user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail(), user.getPassword(), user.getBirthday());

        if ((user.getAddress() != null) && (!user.getAddress().isEmpty())) {
            for (AddressDTO address : user.getAddress()) {
                Long generatedAddressId = addressRepository.count() + 1;
                addressRepository.add(generatedAddressId, address.getCountry(), address.getCity(), address.getStreet(), address.getHouseNumber(), address.getPostcode(), generatedUserId);
            }
        }

        if ((user.getBankData() != null) && (!user.getBankData().isEmpty())) {
            for (BankDataDTO bankData : user.getBankData()) {
                Long generatedBankDataId = bankDataRepository.count() + 1;
                bankDataRepository.add(generatedBankDataId, bankData.getBankAccountNumber(), bankData.getDateOfExpiry(), bankData.getCvi(), generatedUserId);
            }
        }


        Optional<UserEntity> savedUser = userRepository.findById(generatedUserId);

        LOG.debug("************ add() ---> savedUser = " + savedUser);

        userResult = userConverter.mapEntityToDTO(savedUser.get());

        LOG.debug("************ add() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     * Retrieves the {@literal UserDTO} by this {@code userId}.
     *
     * @param userId must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @return the {@literal UserDTO} with the given {@code userId}.
     * @throws IllegalArgumentException In case the given {@code userId} is {@literal null} or if {@code userId} is equal or less zero.
     * @throws DataNotFoundException    In case if {@literal UserDTO} not exist with this {@code userId}.
     */
    @Override
    public UserDTO getById(Long userId) {
        UserDTO userResult = null;

        if ((userId == null) || (userId <= 0)) {
            LOG.debug("************ getById(() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getById(() ---> userId = " + userId);

        Optional<UserEntity> userFromRepositoryById = userRepository.findById(userId);
        LOG.debug("************ getById(() ---> " +
                "userFromRepositoryByUserId = " + userFromRepositoryById);

        if (!userFromRepositoryById.isPresent()) {
            LOG.debug("************ getById(() ---> " +
                    format(EXCEPTION_MESSAGE_USER_BY_USER_ID_NOT_EXIST, userId));
            throw new DataNotFoundException(
                    format(EXCEPTION_MESSAGE_USER_BY_USER_ID_NOT_EXIST, userId));
        }

        userResult = userConverter.mapEntityToDTO(userFromRepositoryById.get());

        LOG.debug("************ getById(() ---> userResult = " + userResult);

        return userResult;
    }

    /**
     * Retrieve Page of {@literal UserDTOs}.
     *
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal Page<UserDTO>}.
     * @throws DataNotFoundException in case if {@literal Page<BankDataDTO>} not exist with this {@code userId}.
     */
    @Override
    public Page<UserDTO> getPageOfUsers(Integer startPage, Integer pageSize) {
        Page<UserDTO> userPageResult = null;

        LOG.debug("************ getPageOfUsers() ---> startPage = " + startPage + " ---> pageSize = " + pageSize);

        Page<UserEntity> usersFromRepository = userRepository.findAll(PageRequest.of(startPage, pageSize));
        LOG.debug("************ getPageOfUsers() ---> usersFromRepository = " + usersFromRepository);

        if (usersFromRepository.isEmpty()) {
            LOG.debug("************ getPageOfUsers() ---> " + EXCEPTION_MESSAGE_USERS_NOT_EXIST);
            throw new DataNotFoundException(EXCEPTION_MESSAGE_USERS_NOT_EXIST);
        }

        userPageResult = userConverter.mapPageEntityToPageDTO(usersFromRepository);
        LOG.debug("************ getPageOfUsers() ---> userPageResult = " + userPageResult);

        return userPageResult;
    }

    /**
     * Retrieve Page of sorted {@literal UserDTOs}.
     *
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @param sortOrder the value by which the sorted AddressDTOs will be. Must not be {@literal null}.
     * @return the {@literal Page<UserDTO>}.
     * @throws DataNotFoundException in case if {@literal Page<BankDataDTO>} not exist with this {@code userId}.
     */
    @Override
    public Page<UserDTO> getPageOfSortedUsers(Integer startPage, Integer pageSize, String sortOrder) {
        Page<UserDTO> userPageResult = null;

        LOG.debug("************ getPageOfSortedUsers() ---> startPage = " + startPage + " ---> pageSize = " + pageSize + " ---> sortOrder = " + sortOrder);

        Page<UserEntity> usersFromRepository = userRepository.findAll(PageRequest.of(startPage, pageSize, Sort.by(sortOrder)));
        LOG.debug("************ getPageOfSortedUsers() ---> usersFromRepository = " + usersFromRepository);

        if (usersFromRepository.isEmpty()) {
            LOG.debug("************ getPageOfSortedUsers() ---> " + EXCEPTION_MESSAGE_USERS_NOT_EXIST);
            throw new DataNotFoundException(EXCEPTION_MESSAGE_USERS_NOT_EXIST);
        }

        userPageResult = userConverter.mapPageEntityToPageDTO(usersFromRepository);
        LOG.debug("************ getPageOfSortedUsers() ---> userPageResult = " + userPageResult);

        return userPageResult;
    }

    /**
     * Update the {@literal UserDTO} by this {@code userId}.
     *
     * @param userId must not be {@literal null}, and {@code userId} must be greater than zero.
     * @param user   must not be {@literal null}.
     * @return the {@literal UserDTO} this user to be updated.
     * @throws IllegalArgumentException in case the given {@code userId} is {@literal null}
     *                                  or if {@code userId} is equal or less zero.
     *                                  And if {@code user} is equals to {@literal null}.
     * @throws DataNotFoundException    in case if {@literal UserDTO} not exist by {@code userId}.
     * @throws TheSameValueException    in case if source value field is equals to current value field.
     */
    @Override
    public UserDTO updateById(Long userId, UserDTO user) {
        UserDTO userResult = null;

        if ((userId == null) || (userId <= 0) || (user == null)) {
            LOG.debug("************ updateByUserId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ updateByUserId() ---> userId = " + userId + " ---> user = " + user);

        userUtils.checkExistsUser(userId);

        // TODO: Finish this method.
        userResult = userConverter.mapEntityToDTO(userRepository.findById(userId).get());

        LOG.debug("************ updateByUserId() ---> " + "Updated userResult = " + userResult);

        return userResult;
    }

    /**
     * Delete the {@literal UserDTO} with the given {@code userId}.
     *
     * @param userId must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @return {@literal UserDTO} this user to be deleted.
     * @throws IllegalArgumentException In case if the given {@code userId} is {@literal null}, and if {@code userId} is equal or less zero.
     * @throws UserNotExistException    If user not exist with the {@code userId}.
     */
    @Override
    public UserDTO deleteById(Long userId) {
        UserDTO userResult = null;

        if ((userId == null) || (userId <= 0)) {
            LOG.debug("************ deleteById() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteById() ---> userId = " + userId);

        userUtils.checkExistsUser(userId);

        userResult = userConverter.mapEntityToDTO(userRepository.findById(userId).get());

        // TODO: Check this. Is maybe bug.
        addressRepository.deleteById(userId);

        LOG.debug("************ deleteById() ---> " + "Deleted user = " + userResult);

        return userResult;
    }
}
