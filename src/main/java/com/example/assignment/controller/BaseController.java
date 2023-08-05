package com.example.assignment.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping(value = "/intuit/assignment/v1")
public class BaseController {

    @GetMapping(value = "/controller/name")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> getControllerName() {
        return ResponseEntity.ok(this.getClass().getSimpleName());
    }

}
