package uz.pdp.lesson1_task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson1_task1.entity.Company;
import uz.pdp.lesson1_task1.entity.Department;
import uz.pdp.lesson1_task1.payload.ApiResponse;
import uz.pdp.lesson1_task1.payload.CompanyDTO;
import uz.pdp.lesson1_task1.payload.DepartmentDTO;
import uz.pdp.lesson1_task1.repository.AddressRepository;
import uz.pdp.lesson1_task1.repository.CompanyRepository;
import uz.pdp.lesson1_task1.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository repository;

    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getAll() {
        return repository.findAll();
    }


    public Department getId(Integer id) {
        Optional<Department> byId = repository.findById(id);
        return byId.orElseGet(Department::new);
    }


    public ApiResponse add(DepartmentDTO dto) {
        if (repository.existsByName(dto.getName()))
            return new ApiResponse("There are this name", false);
        Department department = new Department();
        department.setName(department.getName());
        department.setCompany(new Company(dto.getCompanyId()));
        repository.save(department);
        return new ApiResponse("Saved successfully", true);
    }


    public ApiResponse edit(Integer id, DepartmentDTO dto) {
        if (repository.existsByNameAndIdNot(dto.getName(), id))
            return new ApiResponse("There are this name", false);
        Optional<Department> byId = repository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Id not found", false);
        Department department = byId.get();
        department.setName(department.getName());
        department.setCompany(new Company(dto.getCompanyId()));
        repository.save(department);
        return new ApiResponse("Edited successfully", true);
    }


    public ApiResponse delete(Integer id) {
        Optional<Department> byId = repository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("This id not found", false);
        repository.delete(byId.get());
        return new ApiResponse("Delete successfully", true);
    }
}
