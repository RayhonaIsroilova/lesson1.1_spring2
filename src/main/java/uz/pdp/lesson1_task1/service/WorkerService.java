package uz.pdp.lesson1_task1.service;

import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson1_task1.entity.Address;
import uz.pdp.lesson1_task1.entity.Department;
import uz.pdp.lesson1_task1.entity.Worker;
import uz.pdp.lesson1_task1.payload.ApiResponse;
import uz.pdp.lesson1_task1.payload.DepartmentDTO;
import uz.pdp.lesson1_task1.payload.WorkerDTO;
import uz.pdp.lesson1_task1.repository.AddressRepository;
import uz.pdp.lesson1_task1.repository.CompanyRepository;
import uz.pdp.lesson1_task1.repository.DepartmentRepository;
import uz.pdp.lesson1_task1.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    WorkerRepository repository;

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Worker> getAll() {
        return repository.findAll();
    }


    public Worker getId(Integer id) {
        Optional<Worker> byId = repository.findById(id);
        return byId.orElseGet(Worker::new);
    }


    public ApiResponse add(WorkerDTO dto) {
        if (repository.existsByPhoneNumber(dto.getPhoneNumber()))
            return new ApiResponse("There are this phoneNumber", false);
        Worker worker = new Worker();
        worker.setName(dto.getName());
        worker.setPhoneNumber(dto.getPhoneNumber());
        worker.getAddress().setId(dto.getAddressId());
        worker.getDepartment().setId(dto.getDepartmentId());
        repository.save(worker);
        return new ApiResponse("Saved successfully", true);
    }


    public ApiResponse edit(Integer id, WorkerDTO dto) {
        if (repository.existsByPhoneNumberAndIdNot(dto.getPhoneNumber(),id))
            return new ApiResponse("There are this name", false);
        Optional<Worker> byId = repository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Id not found", false);
        Worker worker = byId.get();
        worker.setName(dto.getName());
        worker.setPhoneNumber(dto.getPhoneNumber());
        worker.getAddress().setId(dto.getAddressId());
        worker.getDepartment().setId(dto.getDepartmentId());
        repository.save(worker);
        return new ApiResponse("Edited successfully", true);
    }


    public ApiResponse delete(Integer id) {
        Optional<Worker> byId = repository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("This id not found", false);
        addressRepository.deleteById(byId.get().getAddress().getId());
        repository.deleteById(id);
        return new ApiResponse("Delete successfully", true);
    }
}
