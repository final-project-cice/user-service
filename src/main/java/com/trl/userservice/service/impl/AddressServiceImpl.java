package com.trl.userservice.service.impl;

import com.trl.userservice.controller.dto.AddressDTO;
import com.trl.userservice.exceptions.*;
import com.trl.userservice.repository.AddressRepository;
import com.trl.userservice.repository.entity.AddressEntity;
import com.trl.userservice.service.AddressService;
import com.trl.userservice.service.converter.AddressConverter;
import com.trl.userservice.utils.AddressUtils;
import com.trl.userservice.utils.UserUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

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
    private final AddressUtils addressUtils;
    private final AddressConverter addressConverter;
    private final UserUtils userUtils;

    public AddressServiceImpl(AddressRepository addressRepository, AddressUtils addressUtils, AddressConverter addressConverter, UserUtils userUtils) {
        this.addressRepository = addressRepository;
        this.addressUtils = addressUtils;
        this.addressConverter = addressConverter;
        this.userUtils = userUtils;
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

        addressUtils.checkFieldsOfAddress(address);

        LOG.debug("************ add() ---> userId = " + userId);
        userUtils.checkExistsUser(userId);

        // TODO: Find information. How can these two lines of code be done better.
        Long generatedId = addressRepository.count() + 1;
        addressRepository.add(generatedId, address.getCountry(), address.getCity(), address.getStreet(), address.getHouseNumber(), address.getPostcode(), userId);

        Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(generatedId);

        addressResult = addressConverter.mapEntityToDTO(savedAddressFromRepository.get());

        LOG.debug("************ add() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * Retrieves the {@literal AddressDTO} by this {@code addresId}.
     *
     * @param addressId must not be equal to {@literal null}, and {@code addressId} must be greater than zero.
     * @return the {@literal AddressDTO} with the given {@code addressId}.
     * @throws IllegalArgumentException In case the given {@code addressId} is {@literal null} or if {@code addressId} is equal or less zero.
     * @throws DataNotFoundException    In case if {@literal AddressDTO} not exist with this {@code addressId}.
     */
    @Override
    public AddressDTO getByAddressId(Long addressId) {
        AddressDTO addressResult = null;

        if ((addressId == null) || (addressId <= 0)) {
            LOG.debug("************ getByAddressId() ---> " + EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
            throw new IllegalArgumentException(EXCEPTION_MESSAGE_ILLEGAL_ARGUMENTS);
        }

        LOG.debug("************ getByAddressId() ---> addressId = " + addressId);

        Optional<AddressEntity> addressFromRepositoryByAddressId = addressRepository.findById(addressId);
        LOG.debug("************ getByAddressId() ---> " +
                "addressFromRepositoryByAddressId = " + addressFromRepositoryByAddressId);

        if (!addressFromRepositoryByAddressId.isPresent()) {
            LOG.debug("************ getByAddressId() ---> " +
                    format(EXCEPTION_MESSAGE_ADDRESS_BY_ADDRESS_ID_NOT_EXIST, addressId));
            throw new DataNotFoundException(
                    format(EXCEPTION_MESSAGE_ADDRESS_BY_ADDRESS_ID_NOT_EXIST, addressId));
        }

        addressResult = addressConverter.mapEntityToDTO(addressFromRepositoryByAddressId.get());

        LOG.debug("************ getByAddressId() ---> addressResult = " + addressResult);

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

        userUtils.checkExistsUser(userId);

        Page<AddressEntity> pageOfAddressesByUserId = addressRepository.getPageOfAddressesByUserId(userId, PageRequest.of(startPage, pageSize));
        LOG.debug("************ getPageOfAddressesByUserId() ---> pageOfAddressesFromRepositoryByUserId " + pageOfAddressesByUserId);

        if (pageOfAddressesByUserId.isEmpty()) {
            LOG.debug("************ getPageOfAddressesByUserId() ---> " + format(EXCEPTION_MESSAGE_ADDRESSES_BY_USER_ID_NOT_EXIST, userId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_ADDRESSES_BY_USER_ID_NOT_EXIST, userId));
        }

        pageOfAddressesResult = addressConverter.mapPageEntityToPageDTO(pageOfAddressesByUserId);
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

        userUtils.checkExistsUser(userId);

        Page<AddressEntity> pageOfAddressesByUserId = addressRepository.getPageOfAddressesByUserId(userId, PageRequest.of(startPage, pageSize, Sort.by(sortOrder)));
        LOG.debug("************ getPageOfSortedAddressesByUserId() ---> pageOfAddressesFromRepositoryByUserId = " + pageOfAddressesByUserId);

        if (pageOfAddressesByUserId.isEmpty()) {
            LOG.debug("************ getPageOfSortedAddressesByUserId() ---> " + format(EXCEPTION_MESSAGE_ADDRESSES_BY_USER_ID_NOT_EXIST, userId));
            throw new DataNotFoundException(format(EXCEPTION_MESSAGE_ADDRESSES_BY_USER_ID_NOT_EXIST, userId));
        }

        pageOfAddressesResult = addressConverter.mapPageEntityToPageDTO(pageOfAddressesByUserId);
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

        AddressDTO addressToBeUpdated = addressUtils.checkExistsAddress(addressId);

        // TODO: Finish this method.
        addressResult = addressToBeUpdated;

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

        AddressDTO addressToBeDeleted = addressUtils.checkExistsAddress(addressId);

        addressRepository.deleteById(addressId);

        addressResult = addressToBeDeleted;

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

        userUtils.checkExistsUser(userId);

        List<AddressDTO> addressesToBeDeleted = addressUtils.checkExistsAddressesByUserId(userId);

        addressRepository.deleteAllAddressesByUserId(userId);

        addressesResult = addressesToBeDeleted;

        LOG.debug("************ deleteAllAddressesByUserId() ---> " + "Deleted addresses = " + addressesResult);

        return addressesResult;
    }
}