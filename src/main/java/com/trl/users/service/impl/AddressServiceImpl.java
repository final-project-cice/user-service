package com.trl.users.service.impl;

import com.trl.users.controller.dto.AddressDTO;
import com.trl.users.controller.dto.UserDTO;
import com.trl.users.exceptions.*;
import com.trl.users.repository.AddressRepository;
import com.trl.users.repository.UserRepository;
import com.trl.users.repository.entity.AddressEntity;
import com.trl.users.repository.entity.UserEntity;
import com.trl.users.service.AddressService;
import com.trl.users.service.converter.ConverterAddress;
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
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    /**
     * @param addressDTO
     * @return
     */
    @Override
    public AddressDTO create(AddressDTO addressDTO) throws ExceptionUserWithIdNotExist, ExceptionUserIdIsNull, ExceptionUserIsNull {
        AddressDTO addressResult = null;

        log.debug("************ create() ---> idUser = " + addressDTO.getUser() + " ---> addressDTO = " + addressDTO);

        // TODO: Nose si es necesario comprobar de null todos parametros que se van utilizar en meethodo. Es que se compica mucho la lectura ce mtodo.

        if (addressDTO.getUser() != null) {

            if (addressDTO.getUser().getId() != null && addressDTO.getUser().getId() != 0) {

                Optional<UserEntity> userFromRepositoryById = userRepository.findById(addressDTO.getUser().getId());

                if (userFromRepositoryById.isPresent()) {

                    AddressEntity savedAddress = addressRepository.save(ConverterAddress.mapDTOToEntity(addressDTO));

                    log.debug("************ create() ---> savedAddress = " + savedAddress);

                    addressResult = ConverterAddress.mapEntityToDTO(savedAddress);

                } else {
                    throw new ExceptionUserWithIdNotExist("User with this id = '" + addressDTO.getUser().getId() + "' not exist.");
                }
            } else {
                throw new ExceptionUserIdIsNull("The parameter 'user' that is passed, contains value 'userId'. Value 'userId' equal NULL or ZERO. Not allowed parameters NULL or ZERO.");
            }
        } else {
            throw new ExceptionUserIsNull("The parameter 'user' that is passed, equal NULL. Not allowed parameter NULL.");
        }

        log.debug("************ create() ---> addressResult = " + addressResult);

        return addressResult ;
    }

    /**
     * @param id
     * @param country
     * @return
     */
    @Transactional
    @Override
    public AddressDTO updateCountry(Long id, String country) throws ExceptionAddressNotExist {
        AddressDTO addressResult = null;

        log.debug("************ updateCountry() ---> id = " + id + " ---> country = " + country);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        log.debug("************ updateCountry() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            AddressEntity addressEntityUpdate = addressFromRepositoryById.get();
            if (country != null
                    && (!country.equals(""))
                    && (!country.equals(addressEntityUpdate.getCountry()))) {

                addressRepository.updateCountry(id,country);

                Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(id);

                log.debug("************ updateCountry() ---> savedAddressFromRepository = " + savedAddressFromRepository);

                addressResult = ConverterAddress.mapEntityToDTO(savedAddressFromRepository.get());
            }
        } else {
            log.debug("************ updateCountry() ---> address with this id = '" + id + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this id = '" + id + "' not exist.");
        }

        log.debug("************ updateCountry() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * @param id
     * @param city
     * @return
     */
    @Transactional
    @Override
    public AddressDTO updateCity(Long id, String city) throws ExceptionAddressNotExist {
        AddressDTO addressResult = null;

        log.debug("************ updateCity() ---> id = " + id + " ---> city = " + city);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        log.debug("************ updateCity() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            AddressEntity addressEntityUpdate = addressFromRepositoryById.get();
            if (city != null
                    && (!city.equals(""))
                    && (!city.equals(addressEntityUpdate.getCity()))) {

                addressRepository.updateCity(id,city);

                Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(id);

                log.debug("************ updateCity() ---> savedAddressFromRepository = " + savedAddressFromRepository);

                addressResult = ConverterAddress.mapEntityToDTO(savedAddressFromRepository.get());
            }
        } else {
            log.debug("************ updateCity() ---> address with this id = '" + id + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this id = '" + id + "' not exist.");
        }

        log.debug("************ updateCity() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * @param id
     * @param street
     * @return
     */
    @Transactional
    @Override
    public AddressDTO updateStreet(Long id, String street) throws ExceptionAddressNotExist {
        AddressDTO addressResult = null;

        log.debug("************ updateStreet() ---> id = " + id + " ---> street = " + street);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        log.debug("************ updateStreet() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            AddressEntity addressEntityUpdate = addressFromRepositoryById.get();
            if (street != null
                    && (!street.equals(""))
                    && (!street.equals(addressEntityUpdate.getStreet()))) {

                addressRepository.updateStreet(id, street);

                Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(id);

                log.debug("************ updateStreet() ---> savedAddressFromRepository = " + savedAddressFromRepository);

                addressResult = ConverterAddress.mapEntityToDTO(savedAddressFromRepository.get());
            }
        } else {
            log.debug("************ updateStreet() ---> address with this id = '" + id + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this id = '" + id + "' not exist.");
        }

        log.debug("************ updateStreet() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * @param id
     * @param houseNumber
     * @return
     */
    @Transactional
    @Override
    public AddressDTO updateHouseNumber(Long id, String houseNumber) throws ExceptionAddressNotExist {
        AddressDTO addressResult = null;

        log.debug("************ updateHouseNumber() ---> id = " + id + " ---> houseNumber = " + houseNumber);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        log.debug("************ updateHouseNumber() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            AddressEntity addressEntityUpdate = addressFromRepositoryById.get();
            if (houseNumber != null
                    && (!houseNumber.equals(""))
                    && (!houseNumber.equals(addressEntityUpdate.getHouseNumber()))) {

                addressRepository.updateHouseNumber(id, houseNumber);

                Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(id);

                log.debug("************ updateHouseNumber() ---> savedAddressFromRepository = " + savedAddressFromRepository);

                addressResult = ConverterAddress.mapEntityToDTO(savedAddressFromRepository.get());
            }
        } else {
            log.debug("************ updateHouseNumber() ---> address with this id = '" + id + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this id = '" + id + "' not exist.");
        }

        log.debug("************ updateHouseNumber() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * @param id
     * @param postCode
     * @return
     */
    @Transactional
    @Override
    public AddressDTO updatePostCode(Long id, Long postCode) throws ExceptionAddressNotExist {
        AddressDTO addressResult = null;

        log.debug("************ updatePostCode() ---> id = " + id + " ---> postCode = " + postCode);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        log.debug("************ updatePostCode() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            AddressEntity addressEntityUpdate = addressFromRepositoryById.get();
            if (postCode != null
                    && (!postCode.equals(addressEntityUpdate.getPostcode()))) {

                addressRepository.updatePostcode(id, postCode);

                Optional<AddressEntity> savedAddressFromRepository = addressRepository.findById(id);

                log.debug("************ updatePostCode() ---> savedAddressFromRepository = " + savedAddressFromRepository);

                addressResult = ConverterAddress.mapEntityToDTO(savedAddressFromRepository.get());
            }
        } else {
            log.debug("************ updatePostCode() ---> address with this id = '" + id + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this id = '" + id + "' not exist.");
        }

        log.debug("************ updatePostCode() ---> addressResult = " + addressResult);

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
    public Boolean deleteByUser(UserDTO userDTO) throws ExceptionUserNotHaveAddress {
        boolean isDeletedAddress = false;

        log.debug("************ deleteByUser() ---> userDTO = " + userDTO);

        UserEntity userEntity = ConverterUser.mapDTOToEntity(userDTO);

        Set<AddressEntity> addressFromRepositoryByUser = addressRepository.findByUser(userEntity);

        log.debug("************ deleteByUser() ---> addressFromRepositoryById = " + addressFromRepositoryByUser);

        if (!addressFromRepositoryByUser.isEmpty()) {
            addressRepository.deleteByUser(userEntity);
            isDeletedAddress = true;
        } else {
            log.debug("************ deleteByUser() ---> user with this id = '" + userDTO + "' not exist.");
            throw new ExceptionUserNotHaveAddress("This user = '" + userDTO + "' not have address.");
        }

        log.debug("************ deleteByUser() ---> isDeletedAddress = " + isDeletedAddress);

        return isDeletedAddress;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AddressDTO findById(Long id) throws ExceptionAddressNotExist {
        AddressDTO addressResult = null;

        log.debug("************ findById() ---> id = " + id);

        Optional<AddressEntity> addressFromRepositoryById = addressRepository.findById(id);

        log.debug("************ findById() ---> addressFromRepositoryById = " + addressFromRepositoryById);

        if (addressFromRepositoryById.isPresent()) {
            addressResult = ConverterAddress.mapEntityToDTO(addressFromRepositoryById.get());
        } else {
            log.debug("************ findById() ---> address with this id = '" + id + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this id = '" + id + "' not exist.");
        }

        log.debug("************ findById() ---> addressResult = " + addressResult);

        return addressResult;
    }

    /**
     * @param country
     * @return
     */
    @Override
    public Set<AddressDTO> findByCountry(String country) throws ExceptionAddressNotExist {
        Set<AddressDTO> addressSetResult = null;

        log.debug("************ findByCountry() ---> country = " + country);

        Set<AddressEntity> addressSetFromRepositoryByCountry = addressRepository.findByCountry(country);

        log.debug("************ findByCountry() ---> addressSetFromRepositoryByCountry = " + addressSetFromRepositoryByCountry);

        if (!addressSetFromRepositoryByCountry.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByCountry);
        } else {
            log.debug("************ findByCountry() ---> address with this name country = '" + country + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this name country = '" + country + "' not exist.");
        }

        log.debug("************ findByCountry() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

    /**
     * @param city
     * @return
     */
    @Override
    public Set<AddressDTO> findByCity(String city) throws ExceptionAddressNotExist {
        Set<AddressDTO> addressSetResult = null;

        log.debug("************ findByCity() ---> city = " + city);

        Set<AddressEntity> addressSetFromRepositoryByCity = addressRepository.findByCity(city);

        log.debug("************ findByCity() ---> addressSetFromRepositoryByCity = " + addressSetFromRepositoryByCity);

        if (!addressSetFromRepositoryByCity.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByCity);
        } else {
            log.debug("************ findByCity() ---> address with this name city = '" + city + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this name city = '" + city + "' not exist.");
        }

        log.debug("************ findByCity() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

    /**
     * @param street
     * @return
     */
    @Override
    public Set<AddressDTO> findByStreet(String street) throws ExceptionAddressNotExist {
        Set<AddressDTO> addressSetResult = null;

        log.debug("************ findByStreet() ---> street = " + street);

        Set<AddressEntity> addressSetFromRepositoryByStreet = addressRepository.findByStreet(street);

        log.debug("************ findByStreet() ---> addressSetFromRepositoryByStreet = " + addressSetFromRepositoryByStreet);

        if (!addressSetFromRepositoryByStreet.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByStreet);
        } else {
            log.debug("************ findByStreet() ---> address with this name street = '" + street + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this name street = '" + street + "' not exist.");
        }

        log.debug("************ findByStreet() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

    /**
     * @param houseNumber
     * @return
     */
    @Override
    public Set<AddressDTO> findByHouseNumber(String houseNumber) throws ExceptionAddressNotExist {
        Set<AddressDTO> addressSetResult = null;

        log.debug("************ findByHouseNumber() ---> houseNumber = " + houseNumber);

        Set<AddressEntity> addressSetFromRepositoryByHouseNumber = addressRepository.findByHouseNumber(houseNumber);

        log.debug("************ findByHouseNumber() ---> addressSetFromRepositoryByHouseNumber = " + addressSetFromRepositoryByHouseNumber);

        if (!addressSetFromRepositoryByHouseNumber.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByHouseNumber);
        } else {
            log.debug("************ findByHouseNumber() ---> address with this house number = '" + houseNumber + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this house number = '" + houseNumber + "' not exist.");
        }

        log.debug("************ findByHouseNumber() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

    /**
     * @param postcode
     * @return
     */
    @Override
    public Set<AddressDTO> findByPostcode(Long postcode) throws ExceptionAddressNotExist {
        Set<AddressDTO> addressSetResult = null;

        log.debug("************ findByPostcode() ---> postcode = " + postcode);

        Set<AddressEntity> addressSetFromRepositoryByPostcode = addressRepository.findByPostcode(postcode);

        log.debug("************ findByPostcode() ---> addressSetFromRepositoryByPostcode = " + addressSetFromRepositoryByPostcode);

        if (!addressSetFromRepositoryByPostcode.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByPostcode);
        } else {
            log.debug("************ findByPostcode() ---> address with this postcode = '" + postcode + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this postcode = '" + postcode + "' not exist.");
        }

        log.debug("************ findByPostcode() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

    /**
     * @param user
     * @return
     */
    @Override
    public Set<AddressDTO> findByUser(UserDTO user) throws ExceptionAddressNotExist {
        Set<AddressDTO> addressSetResult = null;

        log.debug("************ findByUser() ---> user = " + user);

        Set<AddressEntity> addressSetFromRepositoryByUser = addressRepository.findByUser(ConverterUser.mapDTOToEntity(user));

        log.debug("************ findByUser() ---> addressSetFromRepositoryByUser = " + addressSetFromRepositoryByUser);

        if (!addressSetFromRepositoryByUser.isEmpty()) {
            addressSetResult = ConverterAddress.mapSetEntityToSetDTO(addressSetFromRepositoryByUser);
        } else {
            log.debug("************ findByUser() ---> address with this user id = '" + user.getId() + "' not exist.");
            throw new ExceptionAddressNotExist("Address with this user id = '" + user.getId() + "' not exist.");
        }

        log.debug("************ findByUser() ---> addressSetResult = " + addressSetResult);

        return addressSetResult;
    }

}