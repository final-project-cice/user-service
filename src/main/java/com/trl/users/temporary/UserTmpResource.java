package com.trl.users.temporary;

import com.trl.users.controller.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/usertmp")
public class UserTmpResource {

    @PostMapping(path = "/create")
    public ResponseEntity<UserDTO> create(@RequestBody UserTmp user) {
        ResponseEntity<UserDTO> response = null;

        log.debug("************ create() ---> user = " + user);

        log.debug("************ create() ---> user.getBirthday().getClass() = " + user.getBirthday().getClass());

        return response;
    }

}
