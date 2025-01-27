package com.develop.controller;

import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    @PostMapping(value = "/{userId}")
    public String retrieveCurrentBalance(@PathVariable("userId") String userId) {
        return null;
    }
}
