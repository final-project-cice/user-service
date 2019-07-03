package com.trl.users.controller;

import com.trl.users.controller.dto.AddressDTO;
import com.trl.users.controller.model.OneGenericValueDetailsRequestModel;
import com.trl.users.exceptions.ExceptionAddressNotExist;
import com.trl.users.exceptions.ExceptionUserIdIsNull;
import com.trl.users.exceptions.ExceptionUserIsNull;
import com.trl.users.exceptions.ExceptionUserWithIdNotExist;
import com.trl.users.service.impl.AddressServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/user/address")
public class AddressResource {

    private final AddressServiceImpl addressService;

    @PostMapping(path = "/create")
    public ResponseEntity create(@RequestBody AddressDTO address) {
        ResponseEntity response = null;

        log.debug("************ create() ---> address = " + address);

        AddressDTO resultService = null;

        // TODO: Anotacion para todo catch. No estoy seguro que HttpStaus es mejor usarlo.
        try {
            resultService = addressService.create(address);
        } catch (ExceptionUserWithIdNotExist exceptionUserWithIdNotExist) {
            log.error("************ create() ---> user with this id = '" + address.getUser().getId() + "' not exist.", exceptionUserWithIdNotExist);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exceptionUserWithIdNotExist.getMessage());
        } catch (ExceptionUserIdIsNull exceptionUserIdIsNull) {
            log.error("************ create() ---> address not have assign value user >>> 'userId'. 'userId' = '" + address.getUser().getId() + "'. Variable 'userId' not allowed assign values NULL or ZERO.", exceptionUserIdIsNull);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exceptionUserIdIsNull.getMessage());
        } catch (ExceptionUserIsNull exceptionUserIsNull) {
            log.error("************ create() ---> address not have assign value to the user. User = '" + address.getUser() + "'.", exceptionUserIsNull);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(exceptionUserIsNull.getMessage());
        }

        log.debug("************ create() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ create() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/country/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> updateCountry(@PathVariable Long id, @RequestBody String country) {
        ResponseEntity<AddressDTO> response = null;

        log.debug("************ updateCountry() ---> id = " + id + " ---> country = " + country);

        AddressDTO resultService = null;

        try {
            resultService = addressService.updateCountry(id, country);
        } catch (ExceptionAddressNotExist exceptionAddressNotExist) {
            log.error("Address with this id = '" + id + "' not exist.", exceptionAddressNotExist);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ updateCountry() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ updateCountry() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/city/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> updateCity(@PathVariable Long id, @RequestBody String city) {
        ResponseEntity<AddressDTO> response = null;

        log.debug("************ updateCity() ---> id = " + id + " ---> city = " + city);

        AddressDTO resultService = null;

        try {
            resultService = addressService.updateCity(id, city);
        } catch (ExceptionAddressNotExist exceptionAddressNotExist) {
            log.error("Address with this id = '" + id + "' not exist.", exceptionAddressNotExist);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ updateCity() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ updateCity() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/street/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> updateStreet(@PathVariable Long id, @RequestBody String street) {
        ResponseEntity<AddressDTO> response = null;

        log.debug("************ updateStreet() ---> id = " + id + " ---> street = " + street);

        AddressDTO resultService = null;

        try {
            resultService = addressService.updateStreet(id, street);
        } catch (ExceptionAddressNotExist exceptionAddressNotExist) {
            log.error("Address with this id = '" + id + "' not exist.", exceptionAddressNotExist);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ updateStreet() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ updateStreet() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/houseNumber/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> updateHouseNumber(@PathVariable Long id, @RequestBody String houseNumber) {
        ResponseEntity<AddressDTO> response = null;

        log.debug("************ updateHouseNumber() ---> id = " + id + " ---> houseNumber = " + houseNumber);

        AddressDTO resultService = null;

        try {
            resultService = addressService.updateHouseNumber(id, houseNumber);
        } catch (ExceptionAddressNotExist exceptionAddressNotExist) {
            log.error("Address with this id = '" + id + "' not exist.", exceptionAddressNotExist);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ updateHouseNumber() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ updateHouseNumber() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/update/postcode/{id}")
    public ResponseEntity<AddressDTO> updatePostcode(@PathVariable Long id, @RequestBody OneGenericValueDetailsRequestModel<Long> postcode) {
        ResponseEntity<AddressDTO> response = null;

        log.debug("************ updatePostcode() ---> id = " + id + " ---> postcode = " + postcode.getValue());

        AddressDTO resultService = null;

        try {
            resultService = addressService.updatePostCode(id, postcode.getValue());
        } catch (ExceptionAddressNotExist exceptionAddressNotExist) {
            log.error("Address with this id = '" + id + "' not exist.", exceptionAddressNotExist);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ updatePostcode() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ updatePostcode() ---> response = " + response);

        return response;
    }

//    AddressDTO updateCountry(Long id, String country);
//
//    AddressDTO updateCity(Long id, String city);
//
//    AddressDTO updateStreet(Long id, String street);
//
//    AddressDTO updateHouseNumber(Long id, String houseNumber);
//
//    AddressDTO updatePostCode(Long id, String postCode);
//
//
//    Boolean deleteById(Long id);
//
//    Boolean deleteByUser(UserDTO userDTO) throws ExceptionUserWithIdNotExist, ExceptionUserNotHaveAddress;
//
//
//    AddressDTO findById(Long id);
//
//    Set<AddressDTO> findByCountry(String country);
//
//    Set<AddressDTO> findByCity(String city);
//
//    Set<AddressDTO> findByStreet(String street);
//
//    Set<AddressDTO> findByHouseNumber(String houseNumber);
//
//    Set<AddressDTO> findByPostCode(String postCode);
//
//    Set<AddressDTO> findByUser(UserDTO userDTO);

}
