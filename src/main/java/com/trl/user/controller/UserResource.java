package com.trl.user.controller;

import com.trl.user.controller.dto.UserDTO;
import com.trl.user.controller.model.TwoGenericValuesDetailsRequestModel;
import com.trl.user.exceptions.UserWithEmailExistException;
import com.trl.user.exceptions.UserWithIdNotExistException;
import com.trl.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/user")
public class UserResource {

    private final UserService userService;

    /**
     *
     */
    @PostMapping(path = "/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {
        ResponseEntity<UserDTO> response = null;

        log.debug("************ create() ---> user = " + user);

        UserDTO resultService = null;

        try {
            resultService = userService.create(user);
            log.debug("************ create() ---> resultService = " + resultService);
        } catch (UserWithEmailExistException userWithEmailExistException) {
            log.error("************ create() ---> user with this email = '" + user.getEmail() + "' exist.", userWithEmailExistException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            // TODO: No lo se como puedo pasar el mensage que indica que email ya existe. Y tambien no lo se si esta exception es correcta para este caso.
        }

        response = ResponseEntity.ok(resultService);

        log.debug("************ create() ---> response = " + response);

        return response;
    }


    /**
     *
     */
    @PostMapping(path = "/update/firstName/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateFirstName(@PathVariable Long id, @RequestBody String firstName) {
        ResponseEntity<UserDTO> response = null;

        log.debug("************ updateFirstName() ---> id = " + id + " ---> firstName = " + firstName);

        UserDTO resultService = null;

        try {
            resultService = userService.updateFirstName(id, firstName);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            log.error("User with this id = '" + id + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ updateFirstName() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ updateFirstName() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/lastName/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateLastName(@PathVariable Long id, @RequestBody String lastName) {
        ResponseEntity<UserDTO> response = null;

        log.debug("************ updateLastName() ---> id = " + id + " ---> lastName = " + lastName);

        UserDTO resultService = null;

        try {
            resultService = userService.updateLastName(id, lastName);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            log.error("User with this id = '" + id + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ updateLastName() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ updateLastName() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/email/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateEmail(@PathVariable Long id, @RequestBody String email) {
        ResponseEntity<UserDTO> response = null;

        log.debug("************ updateEmail() ---> id = " + id + " ---> email = " + email);

        UserDTO resultService = null;

        try {
            resultService = userService.updateEmail(id, email);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            log.error("User with this id = '" + id + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        } catch (UserWithEmailExistException userWithEmailExistException) {
            log.error("************ create() ---> user with this email = '" + email + "' exist.", userWithEmailExistException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            // TODO: No lo se como puedo pasar el mensage que indica que email ya existe. Y tambien no lo se si esta exception es correcta para este caso.
        }

        log.debug("************ updateEmail() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ updateEmail() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/password/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updatePassword(@PathVariable Long id, @RequestBody String password) {
        ResponseEntity<UserDTO> response = null;

        log.debug("************ updatePassword() ---> id = " + id + " ---> password = " + password);

        UserDTO resultService = null;

        try {
            resultService = userService.updatePassword(id, password);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            log.error("User with this id = '" + id + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ updatePassword() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ updatePassword() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/birthday/{id}")
    public ResponseEntity<UserDTO> updateBirthday(@PathVariable Long id, @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate birthday) {
        ResponseEntity<UserDTO> response = null;

        log.debug("************ updateBirthday() ---> id = " + id + " ---> birthday = " + birthday);

        UserDTO resultService = null;

        try {
            resultService = userService.updateBirthday(id, birthday);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            log.error("User with this id = '" + id + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ updateBirthday() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        log.debug("************ updateBirthday() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long userId) {
        ResponseEntity<Boolean> response = null;

        log.debug("************ delete() ---> user = " + userId);

        Boolean isDeleteUser = false;

        try {
            isDeleteUser = userService.delete(userId);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            log.error("User with this id = '" + userId + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        }

        log.debug("************ delete() ---> isDeleteUser = " + isDeleteUser);

        response = ResponseEntity.ok(isDeleteUser);

        log.debug("************ delete() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        ResponseEntity<UserDTO> response = null;

        log.debug("************ findById() ---> id = " + id);

        UserDTO resultService = userService.findById(id);

        log.debug("************ findById() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        log.debug("************ findById() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findByFirstName", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserDTO>> findByFirstName(@RequestBody String firstName) {
        ResponseEntity<Set<UserDTO>> response = null;

        log.debug("************ findByFirstName() ---> firstName = " + firstName);

        Set<UserDTO> resultService = userService.findByFirstName(firstName);

        log.debug("************ findByFirstName() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        log.debug("************ findByFirstName() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findByLastName", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserDTO>> findByLastName(@RequestBody String lastName) {
        ResponseEntity<Set<UserDTO>> response = null;

        log.debug("************ findByLastName() ---> lastName = " + lastName);

        Set<UserDTO> resultService = userService.findByLastName(lastName);

        log.debug("************ findByLastName() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        log.debug("************ findByLastName() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findByEmail", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findByEmail(@RequestBody String email) {
        ResponseEntity<UserDTO> response = null;

        log.debug("************ findByEmail() ---> email = " + email);

        UserDTO resultService = userService.findByEmail(email);

        log.debug("************ findByEmail() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        log.debug("************ findByEmail() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findByFirstNameAndLastName")
    public ResponseEntity<Set<UserDTO>> findByFirstNameAndLastName(@RequestBody TwoGenericValuesDetailsRequestModel<String, String> model) {
        ResponseEntity<Set<UserDTO>> response = null;

        log.debug("************ findByFirstNameAndLastName() ---> firstName = " + model.getFirstValue() + " ---> lastName = " + model.getSecondValue());

        Set<UserDTO> resultService = userService.findByFirstNameAndLastName(model.getFirstValue(), model.getSecondValue());

        log.debug("************ findByFirstNameAndLastName() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        log.debug("************ findByFirstNameAndLastName() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findByBirthday")
    public ResponseEntity<Set<UserDTO>> findByBirthday(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate birthday) {
        ResponseEntity<Set<UserDTO>> response = null;

        log.debug("************ findByBirthday() ---> birthday = " + birthday);

        Set<UserDTO> resultService = userService.findByBirthday(birthday);

        log.debug("************ findByBirthday() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        log.debug("************ findByBirthday() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findAll")
    public ResponseEntity<Set<UserDTO>> findAll() {
        ResponseEntity<Set<UserDTO>> response = null;

        Set<UserDTO> resultService = userService.findAll();

        log.debug("************ findAll() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        log.debug("************ findAll() ---> response = " + response);

        return response;
    }

}
