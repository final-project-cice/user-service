package com.trl.userservice.utils;

import com.trl.userservice.controller.dto.AddressDTO;
import com.trl.userservice.exceptions.AddressNotExistException;
import com.trl.userservice.exceptions.IllegalValueException;
import com.trl.userservice.repository.AddressRepository;
import com.trl.userservice.repository.entity.AddressEntity;
import com.trl.userservice.service.converter.AddressConverter;
import com.trl.userservice.service.impl.AddressServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.deleteWhitespace;

import java.util.List;
import java.util.Optional;

/**
 * This class contains methods utils for {@literal AddressDTO}.
 *
 * @author Tsyupryk Roman
 */
@Component
public class AddressUtils {

    private static final Logger LOG = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;

    public AddressUtils(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Check fields of object {@literal AddressDTO}.
     *
     * @param address must not be equal to {@literal null}.
     * @throws IllegalValueException If some field of {@literal AddressDTO} contains illegal value.
     */
    public void checkFieldsOfAddress(AddressDTO address) {
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

    /**
     * Check exist {@literal AddressDTO} by {@code addressId}.
     *
     * @param addressId must not be equal to {@literal null}, and {@code addressId} must be greater than zero.
     * @return the {@literal AddressDTO}.
     * @throws AddressNotExistException If {@literal AddressDTO} not exist with the {@code addressId}.
     */
    public AddressDTO checkExistsAddress(Long addressId) {

        Optional<AddressEntity> addressFromRepository = addressRepository.findById(addressId);

        if (!addressFromRepository.isPresent()) {
            LOG.debug("************ checkExistsAddressByAddressId() ---> " + "Address with this addressId = " + addressId + " not exist.");
            throw new AddressNotExistException("Address with this addressId = " + addressId + " not exist.");
        }

        return new AddressConverter().mapEntityToDTO(addressFromRepository.get());
    }

    /**
     * Check exist {@literal AddressDTOs} by {@code userId}.
     *
     * @param userId must not be equal to {@literal null}, and {@code userId} must be greater than zero.
     * @return the {@literal List<AddressDTO>}.
     * @throws AddressNotExistException If {@literal AddressDTOs} not exist with the {@code userId}.
     */
    public List<AddressDTO> checkExistsAddressesByUserId(Long userId) {

        List<AddressEntity> addressesFromRepository = addressRepository.findByUserId(userId);

        if (addressesFromRepository.isEmpty()) {
            LOG.debug("************ checkExistsAddressesByUserId() ---> Addresses with this userId = " + userId + " not exist.");
            throw new AddressNotExistException("Addresses with this userId = " + userId + " not exist.");
        }

        return new AddressConverter().mapListEntityToListDTO(addressesFromRepository);
    }
}