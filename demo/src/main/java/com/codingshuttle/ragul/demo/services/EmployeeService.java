package com.codingshuttle.ragul.demo.services;

import com.codingshuttle.ragul.demo.dto.EmployeeDTO;
import com.codingshuttle.ragul.demo.entities.EmployeeEntity;
import com.codingshuttle.ragul.demo.repositories.EmployeeRepository;
import org.h2.engine.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import javax.management.modelmbean.ModelMBean;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity =  employeeRepository.findById(id).orElse(null);
        //ModelMapper modelMapper = new ModelMapper();
        if(employeeEntity == null) return null;
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
        return employeeEntities.stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO creteEmployee(EmployeeDTO inputEmployeeDTO) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployeeDTO,EmployeeEntity.class);
        EmployeeEntity employeeEntity =  employeeRepository.save(toSaveEntity);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(EmployeeDTO employeeDTO, Long id) {
        EmployeeEntity employeeEntity;
        EmployeeEntity toSaveEntity;
        if(employeeRepository.findById(id).isPresent()){
            employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
            employeeEntity.setId(id);
            toSaveEntity = employeeRepository.save(employeeEntity);
        }else{
            employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
            toSaveEntity = employeeRepository.save(employeeEntity);
        }

        return modelMapper.map(toSaveEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exist = employeeRepository.existsById(employeeId);
        if(!exist) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public boolean isExistsByEmployeeById(Long employeeId){
        return employeeRepository.existsById(employeeId);
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists = isExistsByEmployeeById(employeeId);
        if(!exists) return null;
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field,value)->{
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class,field);
            assert fieldToBeUpdated != null;
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated,employeeEntity,value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
