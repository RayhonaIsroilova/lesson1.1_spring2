package uz.pdp.lesson1_task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson1_task1.payload.ApiResponse;
import uz.pdp.lesson1_task1.payload.DepartmentDTO;
import uz.pdp.lesson1_task1.payload.WorkerDTO;
import uz.pdp.lesson1_task1.service.DepartmentService;
import uz.pdp.lesson1_task1.service.WorkerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService service;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        return ResponseEntity.ok(service.getId(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid  @RequestBody WorkerDTO dto){
        ApiResponse apiResponse = service.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id,@RequestBody WorkerDTO dto){
        ApiResponse apiResponse = service.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = service.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202: 409).body(apiResponse);
    }
}
