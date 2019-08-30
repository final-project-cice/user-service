package com.trl.user.controller;

import com.trl.user.controller.dto.UserDTO;
import com.trl.user.controller.model.TwoGenericValuesDetailsRequestModel;
import com.trl.user.exceptions.UserWithEmailExistException;
import com.trl.user.exceptions.UserWithIdNotExistException;
import com.trl.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping(path = "/user")
public class UserResource {

    private static final Logger LOG = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     *
     */
    @PostMapping(path = "/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO user) {
        ResponseEntity<UserDTO> response = null;

        LOG.debug("************ create() ---> user = " + user);

        UserDTO resultService = null;

        try {
            resultService = userService.create(user);
            LOG.debug("************ create() ---> resultService = " + resultService);
        } catch (UserWithEmailExistException userWithEmailExistException) {
            LOG.error("************ create() ---> user with this email = '" + user.getEmail() + "' exist.", userWithEmailExistException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            // TODO: No lo se como puedo pasar el mensage que indica que email ya existe. Y tambien no lo se si esta exception es correcta para este caso.
        }

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ create() ---> response = " + response);

        return response;
    }


    /**
     *
     */
    @PostMapping(path = "/update/firstName/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateFirstName(@PathVariable Long id, @RequestBody String firstName) {
        ResponseEntity<UserDTO> response = null;

        LOG.debug("************ updateFirstName() ---> id = " + id + " ---> firstName = " + firstName);

        UserDTO resultService = null;

        try {
            resultService = userService.updateFirstName(id, firstName);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            LOG.error("User with this id = '" + id + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ updateFirstName() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ updateFirstName() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/lastName/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateLastName(@PathVariable Long id, @RequestBody String lastName) {
        ResponseEntity<UserDTO> response = null;

        LOG.debug("************ updateLastName() ---> id = " + id + " ---> lastName = " + lastName);

        UserDTO resultService = null;

        try {
            resultService = userService.updateLastName(id, lastName);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            LOG.error("User with this id = '" + id + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ updateLastName() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ updateLastName() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/email/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateEmail(@PathVariable Long id, @RequestBody String email) {
        ResponseEntity<UserDTO> response = null;

        LOG.debug("************ updateEmail() ---> id = " + id + " ---> email = " + email);

        UserDTO resultService = null;

        try {
            resultService = userService.updateEmail(id, email);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            LOG.error("User with this id = '" + id + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        } catch (UserWithEmailExistException userWithEmailExistException) {
            LOG.error("************ create() ---> user with this email = '" + email + "' exist.", userWithEmailExistException);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            // TODO: No lo se como puedo pasar el mensage que indica que email ya existe. Y tambien no lo se si esta exception es correcta para este caso.
        }

        LOG.debug("************ updateEmail() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ updateEmail() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/password/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updatePassword(@PathVariable Long id, @RequestBody String password) {
        ResponseEntity<UserDTO> response = null;

        LOG.debug("************ updatePassword() ---> id = " + id + " ---> password = " + password);

        UserDTO resultService = null;

        try {
            resultService = userService.updatePassword(id, password);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            LOG.error("User with this id = '" + id + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ updatePassword() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ updatePassword() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @PostMapping(path = "/update/birthday/{id}")
    public ResponseEntity<UserDTO> updateBirthday(@PathVariable Long id, @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate birthday) {
        ResponseEntity<UserDTO> response = null;

        LOG.debug("************ updateBirthday() ---> id = " + id + " ---> birthday = " + birthday);

        UserDTO resultService = null;

        try {
            resultService = userService.updateBirthday(id, birthday);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            LOG.error("User with this id = '" + id + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ updateBirthday() ---> resultService = " + resultService);

        response = ResponseEntity.ok(resultService);

        LOG.debug("************ updateBirthday() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @DeleteMapping(path = "/delete/{userId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long userId) {
        ResponseEntity<Boolean> response = null;

        LOG.debug("************ delete() ---> user = " + userId);

        Boolean isDeleteUser = false;

        try {
            isDeleteUser = userService.delete(userId);
        } catch (UserWithIdNotExistException userWithIdNotExistException) {
            LOG.error("User with this id = '" + userId + "' not exist.", userWithIdNotExistException);
            return ResponseEntity.notFound().build();
        }

        LOG.debug("************ delete() ---> isDeleteUser = " + isDeleteUser);

        response = ResponseEntity.ok(isDeleteUser);

        LOG.debug("************ delete() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        ResponseEntity<UserDTO> response = null;

        LOG.debug("************ findById() ---> id = " + id);

        UserDTO resultService = userService.findById(id);

        LOG.debug("************ findById() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        LOG.debug("************ findById() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findByFirstName", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserDTO>> findByFirstName(@RequestBody String firstName) {
        ResponseEntity<Set<UserDTO>> response = null;

        LOG.debug("************ findByFirstName() ---> firstName = " + firstName);

        Set<UserDTO> resultService = userService.findByFirstName(firstName);

        LOG.debug("************ findByFirstName() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        LOG.debug("************ findByFirstName() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findByLastName", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserDTO>> findByLastName(@RequestBody String lastName) {
        ResponseEntity<Set<UserDTO>> response = null;

        LOG.debug("************ findByLastName() ---> lastName = " + lastName);

        Set<UserDTO> resultService = userService.findByLastName(lastName);

        LOG.debug("************ findByLastName() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        LOG.debug("************ findByLastName() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findByEmail", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> findByEmail(@RequestBody String email) {
        ResponseEntity<UserDTO> response = null;

        LOG.debug("************ findByEmail() ---> email = " + email);

        UserDTO resultService = userService.findByEmail(email);

        LOG.debug("************ findByEmail() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        LOG.debug("************ findByEmail() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findByFirstNameAndLastName")
    public ResponseEntity<Set<UserDTO>> findByFirstNameAndLastName(@RequestBody TwoGenericValuesDetailsRequestModel<String, String> model) {
        ResponseEntity<Set<UserDTO>> response = null;

        LOG.debug("************ findByFirstNameAndLastName() ---> firstName = " + model.getFirstValue() + " ---> lastName = " + model.getSecondValue());

        Set<UserDTO> resultService = userService.findByFirstNameAndLastName(model.getFirstValue(), model.getSecondValue());

        LOG.debug("************ findByFirstNameAndLastName() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        LOG.debug("************ findByFirstNameAndLastName() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findByBirthday")
    public ResponseEntity<Set<UserDTO>> findByBirthday(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate birthday) {
        ResponseEntity<Set<UserDTO>> response = null;

        LOG.debug("************ findByBirthday() ---> birthday = " + birthday);

        Set<UserDTO> resultService = userService.findByBirthday(birthday);

        LOG.debug("************ findByBirthday() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        LOG.debug("************ findByBirthday() ---> response = " + response);

        return response;
    }

    /**
     *
     */
    @GetMapping(path = "/findAll")
    public ResponseEntity<Set<UserDTO>> findAll() {
        ResponseEntity<Set<UserDTO>> response = null;

        Set<UserDTO> resultService = userService.findAll();

        LOG.debug("************ findAll() ---> resultService = " + resultService);

        response = (resultService != null) ? ResponseEntity.ok(resultService) : ResponseEntity.notFound().build();

        LOG.debug("************ findAll() ---> response = " + response);

        return response;
    }

}
