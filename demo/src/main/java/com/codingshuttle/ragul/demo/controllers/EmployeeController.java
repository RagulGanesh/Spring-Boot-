package com.codingshuttle.ragul.demo.controllers;

import com.codingshuttle.ragul.demo.dto.EmployeeDTO;
import com.codingshuttle.ragul.demo.entities.EmployeeEntity;
import com.codingshuttle.ragul.demo.repositories.EmployeeRepository;
import com.codingshuttle.ragul.demo.services.EmployeeService;
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

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //@GetMapping("/employees/{employeeId}")
    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable(name = "employeeId") Long id){
        //return new EmployeeDTO(id,"Ragul","ragul@gmail.com",3, LocalDate.of(2025,1,26),true);
        return employeeService.getEmployeeById(id);
    }

    //@GetMapping(path = "/employees")
    @GetMapping(path = "")
    public List<EmployeeDTO> getALlEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        //return "Hi age " + age + " " + sortBy;
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO inputEmployeeDTO){
        //return inputEmployeeDTO;
        return employeeService.creteEmployee(inputEmployeeDTO);
    }

    @PutMapping
    public String putEmployee(){
        return "Hello from put request";
    }
}
