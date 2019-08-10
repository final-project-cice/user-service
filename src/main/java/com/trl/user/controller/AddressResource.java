package com.trl.user.controller;

import com.trl.user.controller.dto.AddressDTO;
import com.trl.user.controller.dto.UserDTO;
import com.trl.user.controller.model.OneGenericValueDetailsRequestModel;
import com.trl.user.exceptions.AddressNotExistException;
import com.trl.user.exceptions.UserIdIsNullException;
import com.trl.user.exceptions.UserIsNullException;
import com.trl.user.exceptions.UserWithIdNotExistException;
import com.trl.user.service.impl.AddressServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

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
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            log.error("************ create() ---> user with this id = '" + address.getUser().getId() + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(userWithIdNotExistException.getMessage());
        } catch (UserIdIsNullException userIdIsNullException) {
            log.error("************ create() ---> address not have assign value user >>> 'userId'. 'userId' = '" + address.getUser().getId() + "'. Variable 'userId' not allowed assign values NULL or ZERO.", userIdIsNullException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(userIdIsNullException.getMessage());
        } catch (UserIsNullException userIsNullException) {
            log.error("************ create() ---> address not have assign value to the user. User = '" + address.getUser() + "'.", userIsNullException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(userIsNullException.getMessage());
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
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
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
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
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
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
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
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
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
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ updatePostcode() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ updatePostcode() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findById/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {
        ResponseEntity<AddressDTO> response = null;

        log.debug("************ findById() ---> id = " + id);

        AddressDTO resultService = null;

        try {
            resultService = addressService.findById(id);
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ findById() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ findById() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByCountry", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AddressDTO>> findByCountry(@RequestBody String country) {
        ResponseEntity<Set<AddressDTO>> response = null;

        log.debug("************ findByCountry() ---> country = " + country);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByCountry(country);
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this name country = '" + country + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ findByCountry() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ findByCountry() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByCity", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AddressDTO>> findByCity(@RequestBody String city) {
        ResponseEntity<Set<AddressDTO>> response = null;

        log.debug("************ findByCity() ---> city = " + city);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByCity(city);
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this name city = '" + city + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ findByCity() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ findByCity() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByStreet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AddressDTO>> findByStreet(@RequestBody String street) {
        ResponseEntity<Set<AddressDTO>> response = null;

        log.debug("************ findByStreet() ---> street = " + street);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByStreet(street);
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this name street = '" + street + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ findByStreet() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ findByStreet() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByHouseNumber", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AddressDTO>> findByHouseNumber(@RequestBody String houseNumber) {
        ResponseEntity<Set<AddressDTO>> response = null;

        log.debug("************ findByHouseNumber() ---> houseNumber = " + houseNumber);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByHouseNumber(houseNumber);
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this house number = '" + houseNumber + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ findByHouseNumber() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ findByHouseNumber() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByPostcode")
    public ResponseEntity<Set<AddressDTO>> findByPostcode(@RequestBody OneGenericValueDetailsRequestModel<Long> postcode) {
        ResponseEntity<Set<AddressDTO>> response = null;

        log.debug("************ findByPostcode() ---> postcode = " + postcode);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByPostcode(postcode.getValue());
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this postcode = '" + postcode.getValue() + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ findByPostcode() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ findByPostcode() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByUser")
    public ResponseEntity<Set<AddressDTO>> findByUser(@RequestBody UserDTO user) {
        ResponseEntity<Set<AddressDTO>> response = null;

        log.debug("************ findByUser() ---> user = " + user);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByUser(user);
        } catch (AddressNotExistException addressNotExistException) {
            log.error("Address with this user id = '" + user.getId() + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ findByUser() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ findByUser() ---> response = " + response);

        return response;
    }

}
