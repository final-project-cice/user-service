package com.trl.user.controller;

import com.trl.user.controller.dto.BankDataDTO;
import com.trl.user.controller.dto.UserDTO;
import com.trl.user.exceptions.UserIdIsNullException;
import com.trl.user.exceptions.UserIsNullException;
import com.trl.user.exceptions.UserWithIdNotExistException;
import com.trl.user.service.BankDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "/user/bankData")
public class BankDataResource {

    private static final Logger LOG = LoggerFactory.getLogger(BankDataResource.class);

    private final BankDataService bankDataService;

    public BankDataResource(BankDataService bankDataService) {
        this.bankDataService = bankDataService;
    }

    /**
     *
     */
    @PostMapping(path = "/create")
    public ResponseEntity create(@RequestBody BankDataDTO bankData) {
        ResponseEntity response = null;

        LOG.debug("************ create() ---> bankData = " + bankData);

        BankDataDTO resultService = null;

        // TODO: Anotacion para todo catch. No estoy seguro que HttpStaus es mejor usarlo.
        try {
            resultService = bankDataService.create(bankData);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            LOG.error("************ create() ---> user with this id = '" + bankData.getUser().getId() + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(userWithIdNotExistException.getMessage());
        } catch (UserIdIsNullException userIdIsNullException) {
            LOG.error("************ create() ---> bankData not have assign value user >>> 'userId'. 'userId' = '" + bankData.getUser().getId() + "'. Variable 'userId' not allowed assign values NULL or ZERO.", userIdIsNullException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(userIdIsNullException.getMessage());
        } catch (UserIsNullException userIsNullException) {
            LOG.error("************ create() ---> bankData not have assign value to the user. User = '" + bankData.getUser() + "'.", userIsNullException);
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
    @PostMapping(path = "/findByUser")
    public ResponseEntity<Set<BankDataDTO>> findByUser(@RequestBody UserDTO user) {
        ResponseEntity<Set<BankDataDTO>> response = null;

        LOG.debug("************ findByUser() ---> userId = " + user);

        Set<BankDataDTO> resultService = null;

        resultService = bankDataService.findByUser(user);

        LOG.debug("************ findByUser() ---> resultService = " + resultService);

        response = (!resultService.isEmpty()) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        LOG.debug("************ findByUser() ---> response = " + response);

        return response;
    }

}