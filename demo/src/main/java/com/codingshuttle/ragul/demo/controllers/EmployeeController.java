package com.codingshuttle.ragul.demo.controllers;

import com.codingshuttle.ragul.demo.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

//    @GetMapping(path = "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret Message";
//    }

    //@GetMapping("/employees/{employeeId}")
    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        return new EmployeeDTO(id,"Ragul","ragul@gmail.com",3, LocalDate.of(2025,1,26),true);
    }

    //@GetMapping(path = "/employees")
    @GetMapping(path = "")
    public String getALlEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "Hi age " + age + " " + sortBy;
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO inputEmployeeDTO){
        inputEmployeeDTO.setId(100L);
        return inputEmployeeDTO;
    }

    @PutMapping
    public String putEmployee(){
        return "Hello from put request";
    }
}
