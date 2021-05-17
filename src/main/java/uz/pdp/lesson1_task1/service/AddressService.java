package uz.pdp.lesson1_task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson1_task1.entity.Address;
import uz.pdp.lesson1_task1.payload.AddressDTO;
import uz.pdp.lesson1_task1.payload.ApiResponse;
import uz.pdp.lesson1_task1.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    /**
     * GET ADDRESS BY LIST
     * @return Address
     */
    public List<Address> getAll(){
        return addressRepository.findAll();
    }

    /**
     * GET ADDRESS BY ID
     * @param id INTEGER
     * @return Address
     */
    public Address getId(Integer id){
        Optional<Address> byId = addressRepository.findById(id);
        return byId.orElseGet(Address::new);
    }

    /**
     * ADD ADDRESS TO DATABASE
     * @param addressDTO AddressDTO
     * @return ApiResponse
     */
    public ApiResponse add(AddressDTO addressDTO){
        if (addressRepository.existsByStreetAndHomeNumber(addressDTO.getStreet(),addressDTO.getHomeNumber()))
            return new ApiResponse("There are this home number",false);
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setHomeNumber(addressDTO.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Saved successfully",true);
    }

    /**
     * EDITING ADDRESS
     * @param id Integer
     * @param addressDTO AddressDTO
     * @return ApiResponse
     */
    public ApiResponse edit(Integer id,AddressDTO addressDTO){
        if (addressRepository.existsByHomeNumberAndIdNot(addressDTO.getHomeNumber(),id))
            return new ApiResponse("There are this address",false);
        if (addressRepository.existsByStreetAndHomeNumber(addressDTO.getStreet(),addressDTO.getHomeNumber()))
            return new ApiResponse("There are this home number",false);
        Optional<Address> byId = addressRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("This address not found",false);
        Address address = byId.get();
        address.setStreet(addressDTO.getStreet());
        address.setHomeNumber(addressDTO.getHomeNumber());
        addressRepository.save(address);
        return new ApiResponse("Edited successfully",true);
    }

    /**
     * DELETE ADDRESS BY ID
     * @param id INTEGER
     * @return ApiResponse
     */
    public ApiResponse delete(Integer id){
        Optional<Address> byId = addressRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("This id not found",false);
        addressRepository.delete(byId.get());
        return new ApiResponse("Delete successfully",true);
    }
}
