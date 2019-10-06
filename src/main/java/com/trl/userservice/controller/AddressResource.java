package com.trl.userservice.controller;

import com.trl.userservice.controller.dto.AddressDTO;
import com.trl.userservice.controller.dto.UserDTO;
import com.trl.userservice.controller.model.OneGenericValueDetailsRequestModel;
import com.trl.userservice.exceptions.AddressNotExistException;
import com.trl.userservice.exceptions.UserIdIsNullException;
import com.trl.userservice.exceptions.UserIsNullException;
import com.trl.userservice.exceptions.UserWithIdNotExistException;
import com.trl.userservice.service.impl.AddressServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/user/address")
public class AddressResource {

    private static final Logger LOG = LoggerFactory.getLogger(AddressResource.class);

    private final AddressServiceImpl addressService;

    public AddressResource(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity create(@RequestBody AddressDTO address) {
        ResponseEntity response = null;

        LOG.debug("************ create() ---> address = " + address);

        AddressDTO resultService = null;

        // TODO: Anotacion para todo catch. No estoy seguro que HttpStaus es mejor usarlo.
        try {
            resultService = addressService.create(address);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            LOG.error("************ create() ---> user with this id = '" + address.getUser().getId() + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(userWithIdNotExistException.getMessage());
        } catch (UserIdIsNullException userIdIsNullException) {
            LOG.error("************ create() ---> address not have assign value user >>> 'userId'. 'userId' = '" + address.getUser().getId() + "'. Variable 'userId' not allowed assign values NULL or ZERO.", userIdIsNullException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(userIdIsNullException.getMessage());
        } catch (UserIsNullException userIsNullException) {
            LOG.error("************ create() ---> address not have assign value to the user. User = '" + address.getUser() + "'.", userIsNullException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(userIsNullException.getMessage());
        }

        LOG.debug("************ create() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ create() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/country/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> updateCountry(@PathVariable Long id, @RequestBody String country) {
        ResponseEntity<AddressDTO> response = null;

        LOG.debug("************ updateCountry() ---> id = " + id + " ---> country = " + country);

        AddressDTO resultService = null;

        try {
            resultService = addressService.updateCountry(id, country);
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ updateCountry() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ updateCountry() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/city/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> updateCity(@PathVariable Long id, @RequestBody String city) {
        ResponseEntity<AddressDTO> response = null;

        LOG.debug("************ updateCity() ---> id = " + id + " ---> city = " + city);

        AddressDTO resultService = null;

        try {
            resultService = addressService.updateCity(id, city);
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ updateCity() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ updateCity() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/street/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> updateStreet(@PathVariable Long id, @RequestBody String street) {
        ResponseEntity<AddressDTO> response = null;

        LOG.debug("************ updateStreet() ---> id = " + id + " ---> street = " + street);

        AddressDTO resultService = null;

        try {
            resultService = addressService.updateStreet(id, street);
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ updateStreet() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ updateStreet() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/houseNumber/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AddressDTO> updateHouseNumber(@PathVariable Long id, @RequestBody String houseNumber) {
        ResponseEntity<AddressDTO> response = null;

        LOG.debug("************ updateHouseNumber() ---> id = " + id + " ---> houseNumber = " + houseNumber);

        AddressDTO resultService = null;

        try {
            resultService = addressService.updateHouseNumber(id, houseNumber);
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ updateHouseNumber() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ updateHouseNumber() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/update/postcode/{id}")
    public ResponseEntity<AddressDTO> updatePostcode(@PathVariable Long id, @RequestBody OneGenericValueDetailsRequestModel<Integer> postcode) {
        ResponseEntity<AddressDTO> response = null;

        LOG.debug("************ updatePostcode() ---> id = " + id + " ---> postcode = " + postcode.getValue());

        AddressDTO resultService = null;

        try {
            resultService = addressService.updatePostCode(id, postcode.getValue());
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ updatePostcode() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ updatePostcode() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findById/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long id) {
        ResponseEntity<AddressDTO> response = null;

        LOG.debug("************ findById() ---> id = " + id);

        AddressDTO resultService = null;

        try {
            resultService = addressService.findById(id);
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this id = '" + id + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ findById() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ findById() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByCountry", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AddressDTO>> findByCountry(@RequestBody String country) {
        ResponseEntity<Set<AddressDTO>> response = null;

        LOG.debug("************ findByCountry() ---> country = " + country);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByCountry(country);
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this name country = '" + country + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ findByCountry() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ findByCountry() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByCity", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AddressDTO>> findByCity(@RequestBody String city) {
        ResponseEntity<Set<AddressDTO>> response = null;

        LOG.debug("************ findByCity() ---> city = " + city);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByCity(city);
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this name city = '" + city + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ findByCity() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ findByCity() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByStreet", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AddressDTO>> findByStreet(@RequestBody String street) {
        ResponseEntity<Set<AddressDTO>> response = null;

        LOG.debug("************ findByStreet() ---> street = " + street);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByStreet(street);
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this name street = '" + street + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ findByStreet() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ findByStreet() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByHouseNumber", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<AddressDTO>> findByHouseNumber(@RequestBody String houseNumber) {
        ResponseEntity<Set<AddressDTO>> response = null;

        LOG.debug("************ findByHouseNumber() ---> houseNumber = " + houseNumber);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByHouseNumber(houseNumber);
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this house number = '" + houseNumber + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ findByHouseNumber() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ findByHouseNumber() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByPostcode")
    public ResponseEntity<Set<AddressDTO>> findByPostcode(@RequestBody OneGenericValueDetailsRequestModel<Integer> postcode) {
        ResponseEntity<Set<AddressDTO>> response = null;

        LOG.debug("************ findByPostcode() ---> postcode = " + postcode);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByPostcode(postcode.getValue());
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this postcode = '" + postcode.getValue() + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ findByPostcode() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ findByPostcode() ---> response = " + response);

        return response;
    }

    @PostMapping(path = "/findByUser")
    public ResponseEntity<Set<AddressDTO>> findByUser(@RequestBody UserDTO user) {
        ResponseEntity<Set<AddressDTO>> response = null;

        LOG.debug("************ findByUser() ---> user = " + user);

        Set<AddressDTO> resultService = null;

        try {
            resultService = addressService.findByUser(user);
        } catch (AddressNotExistException addressNotExistException) {
            LOG.error("Address with this user id = '" + user.getId() + "' not exist.", addressNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ findByUser() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ findByUser() ---> response = " + response);

        return response;
    }

}
