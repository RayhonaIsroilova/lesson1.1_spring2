package uz.pdp.lesson1_task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lesson1_task1.payload.AddressDTO;
import uz.pdp.lesson1_task1.payload.ApiResponse;
import uz.pdp.lesson1_task1.service.AddressService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(addressService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id){
        return ResponseEntity.ok(addressService.getId(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid  @RequestBody AddressDTO addressDTO){
        ApiResponse apiResponse = addressService.add(addressDTO);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @PathVariable Integer id,@RequestBody AddressDTO addressDTO){
        ApiResponse apiResponse = addressService.edit(id,addressDTO);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()? 202: 409).body(apiResponse);
    }
}
