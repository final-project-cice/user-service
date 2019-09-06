package com.trl.user.service.impl;

import com.trl.user.controller.dto.AddressDTO;
import com.trl.user.controller.dto.UserDTO;
import com.trl.user.exceptions.*;
import com.trl.user.repository.AddressRepository;
import com.trl.user.repository.UserRepository;
import com.trl.user.repository.entity.AddressEntity;
import com.trl.user.repository.entity.UserEntity;
import com.trl.user.service.AddressService;
import com.trl.user.service.converter.ConverterAddress;
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
public class AddressServiceImpl implements AddressService {

    private static final Logger LOG = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    /**
     * @param addressDTO
     * @return
     */
    @Override
    public AddressDTO create(AddressDTO addressDTO) throws UserWithIdNotExistException, UserIdIsNullException, UserIsNullException {
        AddressDTO addressResult = null;

        LOG.debug("************ create() ---> idUser = " + addressDTO.getUser() + " ---> addressDTO = " + addressDTO);

        // TODO: Nose si es necesario comprobar de null todos parametros que se van utilizar en meethodo. Es que se compica mucho la lectura ce mtodo.

        if (addressDTO.getUser() != null) {

            if (addressDTO.getUser().getId() != null && addressDTO.getUser().getId() != 0) {

                Optional<UserEntity> userFromRepositoryById = userRepository.findById(addressDTO.getUser().getId());

                if (userFromRepositoryById.isPresent()) {

                    AddressEntity savedAddress = addressRepository.save(ConverterAddress.mapDTOToEntity(addressDTO));

                    LOG.debug("************ create() ---> savedAddress = " + savedAddress);

                    addressResult = ConverterAddress.mapEntityToDTO(savedAddress);

                } else {
                    throw new UserWithIdNotExistException("User with this id = '" + addressDTO.getUser().getId() + "' not exist.");
                }
            } else {
                throw new UserIdIsNullException("The parameter 'user' that is passed, contains value 'userId'. Value 'userId' equal NULL or ZERO. Not allowed parameters NULL or ZERO.");
            }
        } else {
            throw new UserIsNullException("The parameter 'user' that is passed, equal NULL. Not allowed parameter NULL.");
        }

        LOG.debug("************ create() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * @param id
     * @param country
     * @return
     */
    @Transactional
    @Override
    public AddressDTO updateCountry(Long id, String country) throws AddressNotExistException {
        AddressDTO addressResult = null;

        LOG.debug("************ updateCountry() ---> id = " + id + " ---> country = " + country);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        LOG.debug("************ updateCountry() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            AddressEntity addressEntityUpdate = addressFromRepositoryById.get();
            if (country != null
                    && (!country.equals(""))
                    && (!country.equals(addressEntityUpdate.getCountry()))) {

                addressRepository.updateCountry(id, country);

                Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(id);

                LOG.debug("************ updateCountry() ---> savedAddressFromRepository = " + savedAddressFromRepository);

                addressResult = ConverterAddress.mapEntityToDTO(savedAddressFromRepository.get());
            }
        } else {
            LOG.debug("************ updateCountry() ---> address with this id = '" + id + "' not exist.");
            throw new AddressNotExistException("Address with this id = '" + id + "' not exist.");
        }

        LOG.debug("************ updateCountry() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * @param id
     * @param city
     * @return
     */
    @Transactional
    @Override
    public AddressDTO updateCity(Long id, String city) throws AddressNotExistException {
        AddressDTO addressResult = null;

        LOG.debug("************ updateCity() ---> id = " + id + " ---> city = " + city);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        LOG.debug("************ updateCity() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            AddressEntity addressEntityUpdate = addressFromRepositoryById.get();
            if (city != null
                    && (!city.equals(""))
                    && (!city.equals(addressEntityUpdate.getCity()))) {

                addressRepository.updateCity(id, city);

                Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(id);

                LOG.debug("************ updateCity() ---> savedAddressFromRepository = " + savedAddressFromRepository);

                addressResult = ConverterAddress.mapEntityToDTO(savedAddressFromRepository.get());
            }
        } else {
            LOG.debug("************ updateCity() ---> address with this id = '" + id + "' not exist.");
            throw new AddressNotExistException("Address with this id = '" + id + "' not exist.");
        }

        LOG.debug("************ updateCity() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * @param id
     * @param street
     * @return
     */
    @Transactional
    @Override
    public AddressDTO updateStreet(Long id, String street) throws AddressNotExistException {
        AddressDTO addressResult = null;

        LOG.debug("************ updateStreet() ---> id = " + id + " ---> street = " + street);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        LOG.debug("************ updateStreet() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            AddressEntity addressEntityUpdate = addressFromRepositoryById.get();
            if (street != null
                    && (!street.equals(""))
                    && (!street.equals(addressEntityUpdate.getStreet()))) {

                addressRepository.updateStreet(id, street);

                Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(id);

                LOG.debug("************ updateStreet() ---> savedAddressFromRepository = " + savedAddressFromRepository);

                addressResult = ConverterAddress.mapEntityToDTO(savedAddressFromRepository.get());
            }
        } else {
            LOG.debug("************ updateStreet() ---> address with this id = '" + id + "' not exist.");
            throw new AddressNotExistException("Address with this id = '" + id + "' not exist.");
        }

        LOG.debug("************ updateStreet() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * @param id
     * @param houseNumber
     * @return
     */
    @Transactional
    @Override
    public AddressDTO updateHouseNumber(Long id, String houseNumber) throws AddressNotExistException {
        AddressDTO addressResult = null;

        LOG.debug("************ updateHouseNumber() ---> id = " + id + " ---> houseNumber = " + houseNumber);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        LOG.debug("************ updateHouseNumber() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            AddressEntity addressEntityUpdate = addressFromRepositoryById.get();
            if (houseNumber != null
                    && (!houseNumber.equals(""))
                    && (!houseNumber.equals(addressEntityUpdate.getHouseNumber()))) {

                addressRepository.updateHouseNumber(id, houseNumber);

                Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(id);

                LOG.debug("************ updateHouseNumber() ---> savedAddressFromRepository = " + savedAddressFromRepository);

                addressResult = ConverterAddress.mapEntityToDTO(savedAddressFromRepository.get());
            }
        } else {
            LOG.debug("************ updateHouseNumber() ---> address with this id = '" + id + "' not exist.");
            throw new AddressNotExistException("Address with this id = '" + id + "' not exist.");
        }

        LOG.debug("************ updateHouseNumber() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * @param id
     * @param postCode
     * @return
     */
    @Transactional
    @Override
    public AddressDTO updatePostCode(Long id, Integer postCode) throws AddressNotExistException {
        AddressDTO addressResult = null;

        LOG.debug("************ updatePostCode() ---> id = " + id + " ---> postCode = " + postCode);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        LOG.debug("************ updatePostCode() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            AddressEntity addressEntityUpdate = addressFromRepositoryById.get();
            if (postCode != null
                    && (!postCode.equals(addressEntityUpdate.getPostcode()))) {

                addressRepository.updatePostcode(id, postCode);

                Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(id);

                LOG.debug("************ updatePostCode() ---> savedAddressFromRepository = " + savedAddressFromRepository);

                addressResult = ConverterAddress.mapEntityToDTO(savedAddressFromRepository.get());
            }
        } else {
            LOG.debug("************ updatePostCode() ---> address with this id = '" + id + "' not exist.");
            throw new AddressNotExistException("Address with this id = '" + id + "' not exist.");
        }

        LOG.debug("************ updatePostCode() ---> addressResult = " + addressResult);

        return addressResult;
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
     */
    @Transactional
    @Override
    public Boolean deleteByUser(UserDTO userDTO) throws UserNotHaveAddressException {
        boolean isDeletedAddress = false;

        LOG.debug("************ deleteByUser() ---> userDTO = " + userDTO);

        UserEntity userEntity = ConverterUser.mapDTOToEntity(userDTO);

        Set<AddressEntity> addressFromRepositoryByUser = addressRepository.findByUser(userEntity);

        LOG.debug("************ deleteByUser() ---> addressFromRepositoryById = " + addressFromRepositoryByUser);

        if (!addressFromRepositoryByUser.isEmpty()) {
            addressRepository.deleteByUser(userEntity);
            isDeletedAddress = true;
        } else {
            LOG.debug("************ deleteByUser() ---> user with this id = '" + userDTO + "' not exist.");
            throw new UserNotHaveAddressException("This user = '" + userDTO + "' not have address.");
        }

        LOG.debug("************ deleteByUser() ---> isDeletedAddress = " + isDeletedAddress);

        return isDeletedAddress;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AddressDTO findById(Long id) throws AddressNotExistException {
        AddressDTO addressResult = null;

        LOG.debug("************ findById() ---> id = " + id);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        LOG.debug("************ findById() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            addressResult = ConverterAddress.mapEntityToDTO(addressFromRepositoryById.get());
        } else {
            LOG.debug("************ findById() ---> address with this id = '" + id + "' not exist.");
            throw new AddressNotExistException("Address with this id = '" + id + "' not exist.");
        }

        LOG.debug("************ findById() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * @param country
     * @return
     */
    @Override
    public Set<AddressDTO> findByCountry(String country) throws AddressNotExistException {
        Set<AddressDTO> addressSetResult = null;

        LOG.debug("************ findByCountry() ---> country = " + country);

        Set<AddressEntity> addressSetFromRepositoryByCountry = addressRepository.findByCountry(country);

        LOG.debug("************ findByCountry() ---> addressSetFromRepositoryByCountry = " + addressSetFromRepositoryByCountry);

        if (!addressSetFromRepositoryByCountry.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByCountry);
        } else {
            LOG.debug("************ findByCountry() ---> address with this name country = '" + country + "' not exist.");
            throw new AddressNotExistException("Address with this name country = '" + country + "' not exist.");
        }

        LOG.debug("************ findByCountry() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

    /**
     * @param city
     * @return
     */
    @Override
    public Set<AddressDTO> findByCity(String city) throws AddressNotExistException {
        Set<AddressDTO> addressSetResult = null;

        LOG.debug("************ findByCity() ---> city = " + city);

        Set<AddressEntity> addressSetFromRepositoryByCity = addressRepository.findByCity(city);

        LOG.debug("************ findByCity() ---> addressSetFromRepositoryByCity = " + addressSetFromRepositoryByCity);

        if (!addressSetFromRepositoryByCity.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByCity);
        } else {
            LOG.debug("************ findByCity() ---> address with this name city = '" + city + "' not exist.");
            throw new AddressNotExistException("Address with this name city = '" + city + "' not exist.");
        }

        LOG.debug("************ findByCity() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

    /**
     * @param street
     * @return
     */
    @Override
    public Set<AddressDTO> findByStreet(String street) throws AddressNotExistException {
        Set<AddressDTO> addressSetResult = null;

        LOG.debug("************ findByStreet() ---> street = " + street);

        Set<AddressEntity> addressSetFromRepositoryByStreet = addressRepository.findByStreet(street);

        LOG.debug("************ findByStreet() ---> addressSetFromRepositoryByStreet = " + addressSetFromRepositoryByStreet);

        if (!addressSetFromRepositoryByStreet.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByStreet);
        } else {
            LOG.debug("************ findByStreet() ---> address with this name street = '" + street + "' not exist.");
            throw new AddressNotExistException("Address with this name street = '" + street + "' not exist.");
        }

        LOG.debug("************ findByStreet() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

    /**
     * @param houseNumber
     * @return
     */
    @Override
    public Set<AddressDTO> findByHouseNumber(String houseNumber) throws AddressNotExistException {
        Set<AddressDTO> addressSetResult = null;

        LOG.debug("************ findByHouseNumber() ---> houseNumber = " + houseNumber);

        Set<AddressEntity> addressSetFromRepositoryByHouseNumber = addressRepository.findByHouseNumber(houseNumber);

        LOG.debug("************ findByHouseNumber() ---> addressSetFromRepositoryByHouseNumber = " + addressSetFromRepositoryByHouseNumber);

        if (!addressSetFromRepositoryByHouseNumber.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByHouseNumber);
        } else {
            LOG.debug("************ findByHouseNumber() ---> address with this house number = '" + houseNumber + "' not exist.");
            throw new AddressNotExistException("Address with this house number = '" + houseNumber + "' not exist.");
        }

        LOG.debug("************ findByHouseNumber() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

    /**
     * @param postcode
     * @return
     */
    @Override
    public Set<AddressDTO> findByPostcode(Integer postcode) throws AddressNotExistException {
        Set<AddressDTO> addressSetResult = null;

        LOG.debug("************ findByPostcode() ---> postcode = " + postcode);

        Set<AddressEntity> addressSetFromRepositoryByPostcode = addressRepository.findByPostcode(postcode);

        LOG.debug("************ findByPostcode() ---> addressSetFromRepositoryByPostcode = " + addressSetFromRepositoryByPostcode);

        if (!addressSetFromRepositoryByPostcode.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByPostcode);
        } else {
            LOG.debug("************ findByPostcode() ---> address with this postcode = '" + postcode + "' not exist.");
            throw new AddressNotExistException("Address with this postcode = '" + postcode + "' not exist.");
        }

        LOG.debug("************ findByPostcode() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public Set<AddressDTO> findByUser(UserDTO user) throws AddressNotExistException {
        Set<AddressDTO> addressSetResult = null;

        LOG.debug("************ findByUser() ---> user = " + user);

        Set<AddressEntity> addressSetFromRepositoryByUser = addressRepository.findByUser(ConverterUser.mapDTOToEntity(user));

        LOG.debug("************ findByUser() ---> addressSetFromRepositoryByUser = " + addressSetFromRepositoryByUser);

        if (!addressSetFromRepositoryByUser.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByUser);
        } else {
            LOG.debug("************ findByUser() ---> address with this user id = '" + user.getId() + "' not exist.");
            throw new AddressNotExistException("Address with this user id = '" + user.getId() + "' not exist.");
        }

        LOG.debug("************ findByUser() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

}