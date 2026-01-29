package com.codingshuttle.ragul.demo.dto;

import com.codingshuttle.ragul.demo.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

public class EmployeeDTO {

    private Long id;
    @NotNull(message = "Required field in Employee : Name")
    @Size(min = 3, max = 10, message = "Number of characters in name should be in the range : [3:10]")
    private String name;

    @NotNull(message = "Age of employee cannot be null")
    @NotBlank(message = "Email of employee cannot be blank")
    @Email(message = "Email should be a valid email")
    private String email;

    @Max(value = 80, message = "Age of Employee cannot be greater than 80")
    @Min(value = 18, message = "Age of Employee cannot be less than 18")
    private Integer age;

    @NotBlank(message = "Role of employee cannot be blank")
    //@Pattern(regexp = "^(ADMIN|USER)$",message = "Role must be Admin / User")
    @EmployeeRoleValidation
    private String role; //Admin/User

    @PastOrPresent(message = "Joining date cannot be in the future")
    private LocalDate dateOfJoining;
    //@JsonProperty("isActive")

    @DecimalMin(value = "10000.50")
    @DecimalMax(value = "100000.50")
    Double salary;

    @AssertTrue
    private Boolean isActive;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.dateOfJoining = dateOfJoining;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
