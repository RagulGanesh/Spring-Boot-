package com.codingshuttle.ragul.demo.controllers;

import com.codingshuttle.ragul.demo.dto.EmployeeDTO;
import com.codingshuttle.ragul.demo.entities.EmployeeEntity;
import com.codingshuttle.ragul.demo.repositories.EmployeeRepository;
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

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    //@GetMapping("/employees/{employeeId}")
    @GetMapping("/{employeeId}")
    public EmployeeEntity getEmployeeById(@PathVariable(name = "employeeId") Long id){
        //return new EmployeeDTO(id,"Ragul","ragul@gmail.com",3, LocalDate.of(2025,1,26),true);
        return employeeRepository.findById(id).orElse(null);
    }

    //@GetMapping(path = "/employees")
    @GetMapping(path = "")
    public List<EmployeeEntity> getALlEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        //return "Hi age " + age + " " + sortBy;
        return employeeRepository.findAll();
    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity inputEmployeeDTO){
        //return inputEmployeeDTO;
        return employeeRepository.save(inputEmployeeDTO);
    }

    @PutMapping
    public String putEmployee(){
        return "Hello from put request";
    }
}
