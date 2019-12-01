package com.trl.userservice.service.impl;

import com.trl.userservice.controller.dto.AddressDTO;
import com.trl.userservice.exceptions.*;
import com.trl.userservice.repository.AddressRepository;
import com.trl.userservice.repository.UserRepository;
import com.trl.userservice.repository.entity.AddressEntity;
import com.trl.userservice.service.AddressService;

import static com.trl.userservice.service.converter.AddressConverter.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.util.List;
import java.util.Optional;

/**
 * This class is designed to implementation methods of {@code AddressService}.
 *
 * @author Tsyupryk Roman
 */
@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger LOG = LoggerFactory.getLogger(AddressServiceImpl.class);
    private static final String EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS = "One of parameters is illegal. Parameters must be " +
            "not equals to null, and parameters must be greater that zero. Check the parameter that are passed to the method.";
    private static final String EXCEPTION_MESSAGE_ADDRESS_BY_ADDRESS_ID_NOT_EXIST = "Address with this addressId = %s not exist.";
    private static final String EXCEPTION_MESSAGE_ADDRESSES_BY_USER_ID_NOT_EXIST = "Addresses with this userId = %s not exist.";

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    /**
     * Add the {@literal AddressDTO} by this {@code userId}.
     *
     * @param userId  must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @param address must not be equal to {@literal null}.
     * @return {@literal AddressDTO} this address to be saved.
     * @throws IllegalArgumentException in case the given {@code userId} is {@literal null}
     *                                  or if {@code userId} is equal or less zero.
     *                                  And if {@code address} is equals to {@literal null}.
     * @throws IllegalValueException    in case if one of the fields of {@code address} is equals {@literal null}, or less zero.
     * @throws UserNotExistException    in case if user not exist by {@code userId}.
     */
    @Override
    public AddressDTO add(Long userId, AddressDTO address) {
        AddressDTO addressResult = null;

        if ((userId == null) || (userId <= 0) || (address == null)) {
            LOG.debug("************ add() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ add() ---> userId = " + userId + " ---> address = " + address);

        checkParametersAddress(address);

        LOG.debug("************ add() ---> userId = " + userId);
        checkExistsUserById(userId);

        // TODO: Find information. How can these two lines of code be done better.
        Long generatedId = addressRepository.count() + 1;
        addressRepository.add(generatedId, address.getCountry(), address.getCity(), address.getStreet(), address.getHouseNumber(), address.getPostcode(), userId);

        Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(generatedId);

        addressResult = mapEntityToDTO(savedAddressFromRepository.get());

        LOG.debug("************ add() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * Retrieves the {@literal AddressDTO} by this {@code addresId}.
     *
     * @param addressId must not be equal to {@literal null}, and {@code addressId} must be greater than zero.
     * @return the {@literal AdddressDTO} with the given {@code addressId}.
     * @throws IllegalArgumentException In case the given {@code addressId} is {@literal null} or if {@code addressId} is equal or less zero.
     * @throws DataNotFoundException    In case if {@literal AddressDTO} not exist with this {@code addressId}.
     */
    @Override
    public AddressDTO getByAddressId(Long addressId) {
        AddressDTO addressResult = null;

        if ((addressId == null) || (addressId <= 0)) {
            LOG.debug("************ getByAddressId(() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getByAddressId(() ---> addressId = " + addressId);

        Optional<AddressEntity> addressFromRepositoryByAddressId = addressRepository.findById(addressId);
        LOG.debug("************ getByAddressId(() ---> " +
                "addressFromRepositoryByAddressId = " + addressFromRepositoryByAddressId);

        if (addressFromRepositoryByAddressId.isEmpty()) {
            LOG.debug("************ getByAddressId(() ---> " +
                    format(EXCEPTION_MESSAGE_ADDRESS_BY_ADDRESS_ID_NOT_EXIST, addressId));
            throw new DataNotFoundException(
                    format(EXCEPTION_MESSAGE_ADDRESS_BY_ADDRESS_ID_NOT_EXIST, addressId));
        }

        addressResult = mapEntityToDTO(addressFromRepositoryByAddressId.get());

        LOG.debug("************ getByAddressId(() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * Retrieve page of {@literal AddressDTOs} by this {@code userId}.
     *
     * @param userId    must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @return the {@literal Page<AddressDTO>} with the given {@code userId}.
     * @throws IllegalArgumentException in case the given {@code userId} is {@literal null} or if {@code userId} is equal or less zero.
     * @throws UserNotExistException    in case if user with this {@literal userId} not exist.
     * @throws DataNotFoundException    in case if {@literal Page<AddressDTO>} not exist with this {@code userId}.
     */
    @Override
    public Page<AddressDTO> getPageOfAddressesByUserId(Long userId, Integer startPage, Integer pageSize) {
        Page<AddressDTO> pageOfAddressesResult = null;

        if ((userId == null) || (userId <= 0)) {
            LOG.debug("************ getPageOfAddressesByUserId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getPageOfAddressesByUserId() ---> userId = " + userId + " ---> startPage = " + startPage + " ---> pageSize = " + pageSize);

        checkExistsUserById(userId);

        Page<AddressEntity> pageOfAddressesByUserId = addressRepository.getPageOfAddressesByUserId(userId, PageRequest.of(startPage, pageSize));
        LOG.debug("************ getPageOfAddressesByUserId() ---> pageOfAddressesFromRepositoryByUserId " + pageOfAddressesByUserId);

        if (pageOfAddressesByUserId.isEmpty()) {
            LOG.debug("************ getPageOfAddressesByUserId() ---> " + format(EXCEPTION_MESSAGE_ADDRESSES_BY_USER_ID_NOT_EXIST, userId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_ADDRESSES_BY_USER_ID_NOT_EXIST, userId));
        }

        pageOfAddressesResult = mapPageEntityToPageDTO(pageOfAddressesByUserId);
        LOG.debug("************ getPageOfAddressesByUserId() ---> pageOfAddressesResult = " + pageOfAddressesResult);

        return pageOfAddressesResult;
    }

    /**
     * Retrieve page of sorted {@literal AddressDTOs} by this {@code userId}.
     *
     * @param userId    must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @param startPage zero-based page index, must not be negative.
     * @param pageSize  the size of the page to be returned, must be greater than 0.
     * @param sortOrder the value by which the sorted AddressDTOs will be. Must not be {@literal null}.
     * @return the {@literal Page<AddressDTO>} with the given {@code userId}.
     * @throws IllegalArgumentException in case the given {@code userId} is {@literal null} or if {@code userId} is equal or less zero.
     * @throws UserNotExistException    in case if user with this {@literal userId} not exist.
     * @throws DataNotFoundException    in case if {@literal Page<AddressDTO>} not exist with this {@code userId}.
     */
    @Override
    public Page<AddressDTO> getPageOfSortedAddressesByUserId(Long userId, Integer startPage, Integer pageSize, String sortOrder) {
        Page<AddressDTO> pageOfAddressesResult = null;

        if ((userId == null) || (userId <= 0)) {
            LOG.debug("************ getPageOfSortedAddressesByUserId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getPageOfSortedAddressesByUserId() ---> userId = " + userId + " ---> startPage = " + startPage
                + " ---> pageSize = " + pageSize + " ---> sortOrder = " + sortOrder);

        checkExistsUserById(userId);

        Page<AddressEntity> pageOfAddressesByUserId = addressRepository.getPageOfAddressesByUserId(userId, PageRequest.of(startPage, pageSize, Sort.by(sortOrder)));
        LOG.debug("************ getPageOfSortedAddressesByUserId() ---> pageOfAddressesFromRepositoryByUserId = " + pageOfAddressesByUserId);

        if (pageOfAddressesByUserId.isEmpty()) {
            LOG.debug("************ getPageOfSortedAddressesByUserId() ---> " + format(EXCEPTION_MESSAGE_ADDRESSES_BY_USER_ID_NOT_EXIST, userId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_ADDRESSES_BY_USER_ID_NOT_EXIST, userId));
        }

        pageOfAddressesResult = mapPageEntityToPageDTO(pageOfAddressesByUserId);
        LOG.debug("************ getPageOfSortedAddressesByUserId() ---> pageOfAddressesResult = " + pageOfAddressesResult);

        return pageOfAddressesResult;
    }

    /**
     * Update the {@literal AddressDTO} by this {@code addressId}.
     *
     * @param addressId must not be {@literal null}, and {@code addressId} must be greater than zero.
     * @param address   must not be {@literal null}.
     * @return the {@literal AddressDTO} this address to be updated.
     * @throws IllegalArgumentException in case the given {@code addressId} is {@literal null}
     *                                  or if {@code addressId} is equal or less zero.
     *                                  And if {@code address} is equals to {@literal null}.
     * @throws DataNotFoundException    in case if {@literal AddressDTO} not exist by {@code addressId}.
     * @throws TheSameValueException    in case if source value field is equals to current value field.
     */
    @Override
    public AddressDTO updateByAddressId(Long addressId, AddressDTO address) {
        AddressDTO addressResult = null;

        if ((addressId == null) || (addressId <= 0) || (address == null)) {
            LOG.debug("************ updateByAddressId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ updateByAddressId() ---> addressId = " + addressId + " ---> address = " + address);

        AddressEntity addressToBeUpdated = checkExistsAddressByAddressId(addressId);

        // TODO: Finish this method.
        addressResult = mapEntityToDTO(addressToBeUpdated);

        LOG.debug("************ updateByAddressId() ---> " + "Updated addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * Delete the {@literal AddressDTO} with the given {@code addressId}.
     *
     * @param addressId must not be equal to {@literal null}, and {@code addressId} must be greater than zero.
     * @return {@literal AddressDTO} this address to be deleted.
     * @throws IllegalArgumentException In case if the given {@code addressId} is {@literal null}, and if {@code addressId} is equal or less zero.
     * @throws AddressNotExistException If address not exist with the {@code addressId}.
     */
    @Override
    public AddressDTO deleteByAddressId(Long addressId) {
        AddressDTO addressResult = null;

        if ((addressId == null) || (addressId <= 0)) {
            LOG.debug("************ deleteByAddressId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteByAddressId() ---> addressId = " + addressId);

        AddressEntity addressToBeDeleted = checkExistsAddressByAddressId(addressId);

        addressRepository.deleteById(addressId);

        addressResult = mapEntityToDTO(addressToBeDeleted);

        LOG.debug("************ deleteByAddressId() ---> " + "Deleted address = " + addressResult);

        return addressResult;
    }

    /**
     * Delete all addresses by {@code userId}.
     *
     * @param userId must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @return {@literal List<AddressDTO>} this list of addresses to be deleted.
     * @throws IllegalArgumentException In case if the given {@code userId} is {@literal null}, and if {@code userId} is equal or less zero.
     * @throws UserNotExistException    If user not exist with the {@code userId}.
     * @throws AddressNotExistException If addresses not exist with the {@code userId}.
     */
    @Override
    public List<AddressDTO> deleteAllAddressesByUserId(Long userId) {
        List<AddressDTO> addressesResult = null;

        if ((userId == null) || (userId <= 0)) {
            LOG.debug("************ deleteAllAddressesByUserId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ deleteAllAddressesByUserId() ---> userId = " + userId);

        checkExistsUserById(userId);

        List<AddressEntity> addressesToBeDeleted = checkExistsAddressesByUserId(userId);

        addressRepository.deleteAllAddressesByUserId(userId);

        addressesResult = mapListEntityToListDTO(addressesToBeDeleted);

        LOG.debug("************ deleteAllAddressesByUserId() ---> " + "Deleted addresses = " + addressesResult);

        return addressesResult;
    }

    private void checkParametersAddress(AddressDTO address) {
        String message = "Field %s, check the field that it has the 'address' parameter.";

        if (address.getCountry() == null) {
            LOG.debug("************ add() ---> " + format(message, "'country' not be equals to null"));
            throw new IllegalValueException(format(message, "'country' not be equals to null"));
        } else if ((deleteWhitespace(address.getCountry()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'country' is empty"));
            throw new IllegalValueException(format(message, "'country' is empty"));
        }

        if (address.getCity() == null) {
            LOG.debug("************ add() ---> " + format(message, "'city' not be equals to null"));
            throw new IllegalValueException(format(message, "'city' not be equals to null"));
        } else if ((deleteWhitespace(address.getCity()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'city' is empty"));
            throw new IllegalValueException(format(message, "'city' is empty"));
        }

        if (address.getStreet() == null) {
            LOG.debug("************ add() ---> " + format(message, "'street' not be equals to null"));
            throw new IllegalValueException(format(message, "'street' not be equals to null"));
        } else if ((deleteWhitespace(address.getStreet()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'street' is empty"));
            throw new IllegalValueException(format(message, "'street' is empty"));
        }

        if (address.getHouseNumber() == null) {
            LOG.debug("************ add() ---> " + format(message, "'houseNumber' not be equals to null"));
            throw new IllegalValueException(format(message, "'houseNumber' not be equals to null"));
        } else if ((deleteWhitespace(address.getHouseNumber()).isEmpty())) {
            LOG.debug("************ add() ---> " + format(message, "'houseNumber' is empty"));
            throw new IllegalValueException(format(message, "'houseNumber' is empty"));
        }

        if (address.getPostcode() == null) {
            LOG.debug("************ add() ---> " + format(message, "'postcode' not be equals to null"));
            throw new IllegalValueException(format(message, "'postcode' not be equals to null"));
        } else if (address.getPostcode() <= 0) {
            LOG.debug("************ add() ---> " + format(message, "'postcode' must be greater that zero"));
            throw new IllegalValueException(format(message, "'postcode' must be greater that zero"));
        }
    }

    private void checkExistsUserById(Long userId) {
        if (!userRepository.existsById(userId)) {
            LOG.debug("************ checkExistsUserById() ---> " + "User with this id = " + userId + " not exist.");
            throw new UserNotExistException("User with this id = " + userId + " not exist.");
        }
    }

    private AddressEntity checkExistsAddressByAddressId(Long addressId) {

        Optional<AddressEntity> addressFromRepository = addressRepository.findById(addressId);

        if (addressFromRepository.isEmpty()) {
            LOG.debug("************ checkExistsAddressByAddressId() ---> " + "Address with this addressId = " + addressId + " not exist.");
            throw new AddressNotExistException("Address with this addressId = " + addressId + " not exist.");
        }

        return addressFromRepository.get();
    }

    private List<AddressEntity> checkExistsAddressesByUserId(Long userId) {

        List<AddressEntity> addressesFromRepository = addressRepository.findByUserId(userId);

        if (addressesFromRepository.isEmpty()) {
            LOG.debug("************ checkExistsAddressesByUserId() ---> Addresses with this userId = " + userId + " not exist.");
            throw new AddressNotExistException("Addresses with this userId = " + userId + " not exist.");
        }

        return addressesFromRepository;
    }

}