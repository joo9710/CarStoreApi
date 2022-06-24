package com.example.carstoreapi.test.controller;

import com.example.carstoreapi.test.model.TestDTO;
import com.example.carstoreapi.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/test")
@RestController
@Slf4j

public class TestController {

    @GetMapping(value = "/")
    public List<TestDTO> getTestList() throws Exception {

        List<TestDTO> testList = TestService.getTestList();
        return testList;

    }

}


