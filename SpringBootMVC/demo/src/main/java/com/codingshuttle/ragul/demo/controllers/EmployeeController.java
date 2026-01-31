package com.codingshuttle.ragul.demo.controllers;

import com.codingshuttle.ragul.demo.dto.EmployeeDTO;
import com.codingshuttle.ragul.demo.entities.EmployeeEntity;
import com.codingshuttle.ragul.demo.exceptions.ResourceNotFoundException;
import com.codingshuttle.ragul.demo.repositories.EmployeeRepository;
import com.codingshuttle.ragul.demo.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name = "employeeId") Long id){
        //return new EmployeeDTO(id,"Ragul","ragul@gmail.com",3, LocalDate.of(2025,1,26),true);\
        EmployeeDTO employeeDTO =  employeeService.getEmployeeById(id);
        //if(employeeDTO == null) return ResponseEntity.notFound().build();
        if(employeeDTO == null){
            throw new ResourceNotFoundException("Employee was not found with id : "+id);
        }
        return ResponseEntity.ok(employeeDTO);
    }

//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException exception){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
//    }

    //@GetMapping(path = "/employees")
    @GetMapping(path = "")
    public ResponseEntity<List<EmployeeDTO>> getALlEmployees(@RequestParam(required = false, name = "inputAge") Integer age,
                                                @RequestParam(required = false) String sortBy){
        //return "Hi age " + age + " " + sortBy;
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody @Valid EmployeeDTO inputEmployeeDTO){
        //return inputEmployeeDTO;
        EmployeeDTO employeeDTO = employeeService.creteEmployee(inputEmployeeDTO);
        return new ResponseEntity<>(employeeDTO, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployeeById(employeeDTO,employeeId));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String,Object> updates,@PathVariable Long employeeId){
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId,updates);
        if(employeeDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}
