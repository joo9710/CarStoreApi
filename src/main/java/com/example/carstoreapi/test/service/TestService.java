package com.example.carstoreapi.test.service;

import com.example.carstoreapi.test.model.TestDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    public static List<TestDTO> getTestList() {
        List<TestDTO> testList = new ArrayList<TestDTO>();
        testList.add(new TestDTO(1,"name2"));
        testList.add(new TestDTO(2,"name3"));
        testList.add(new TestDTO(3,"name4"));
        return testList;
    }

}
