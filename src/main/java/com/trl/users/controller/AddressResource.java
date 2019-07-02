package com.trl.users.controller;

import com.trl.users.controller.dto.AddressDTO;
import com.trl.users.exceptions.ExceptionUserIdIsNull;
import com.trl.users.exceptions.ExceptionUserIsNull;
import com.trl.users.exceptions.ExceptionUserWithIdNotExist;
import com.trl.users.service.impl.AddressServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
