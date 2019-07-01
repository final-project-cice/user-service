package com.trl.users.controller;

import com.trl.users.controller.dto.BankDataDTO;
import com.trl.users.exceptions.ExceptionSomeParametersPassedToMethodEqualNull;
import com.trl.users.exceptions.ExceptionUserIdIsNull;
import com.trl.users.exceptions.ExceptionUserWithIdNotExist;
import com.trl.users.service.BankDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/user/bankData")
public class BankDataResource {

    private final BankDataService bankDataService;

    /**
     *
     */
    @PostMapping(path = "/create")
    public ResponseEntity<BankDataDTO> create(@RequestBody BankDataDTO bankData) {
        ResponseEntity<BankDataDTO> response = null;

        log.debug("************ create() ---> bankData = " + bankData);

        BankDataDTO resultService = null;

        try {
            resultService = bankDataService.create(bankData);
        } catch (ExceptionSomeParametersPassedToMethodEqualNull exceptionSomeParametersPassedToMethodEqualNull) {
            log.error("************ create() ---> Parameter bankData equal to value NULL. BankData = '" + bankData + "'. Parameters that are null are not allowed.", exceptionSomeParametersPassedToMethodEqualNull);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            // TODO: No lo se como puedo pasar el mensage que indica que email ya existe. Y tambien no lo se si esta exception es correcta para este caso.

        } catch (ExceptionUserWithIdNotExist exceptionUserWithIdNotExist) {
            log.error("************ create() ---> user with this id = '" + bankData.getUserId().getId() + "' not exist.", exceptionUserWithIdNotExist);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            // TODO: No lo se como puedo pasar el mensage que indica que email ya existe. Y tambien no lo se si esta exception es correcta para este caso.
        } catch (ExceptionUserIdIsNull exceptionUserIdIsNull) {
            log.error("************ create() ---> bankData not have user id = '" + bankData.getUserId().getId() + "'. User id is NULL", exceptionUserIdIsNull);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            // TODO: No lo se como puedo pasar el mensage que indica que email ya existe. Y tambien no lo se si esta exception es correcta para este caso.
        }

        log.debug("************ create() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ create() ---> response = " + response);

        return response;
    }

}