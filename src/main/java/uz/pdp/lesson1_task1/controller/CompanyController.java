package uz.pdp.lesson1_task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson1_task1.payload.AddressDTO;
import uz.pdp.lesson1_task1.payload.ApiResponse;
import uz.pdp.lesson1_task1.payload.CompanyDTO;
import uz.pdp.lesson1_task1.service.AddressService;
import uz.pdp.lesson1_task1.service.CompanyService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService service;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        return ResponseEntity.ok(service.getId(id));
    }

    @PostMapping
    public HttpEntity<?> add(@Valid  @RequestBody CompanyDTO dto){
        ApiResponse apiResponse = service.add(dto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @PathVariable Integer id,@RequestBody CompanyDTO dto){
        ApiResponse apiResponse = service.edit(id,dto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = service.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202: 409).body(apiResponse);
    }
}
