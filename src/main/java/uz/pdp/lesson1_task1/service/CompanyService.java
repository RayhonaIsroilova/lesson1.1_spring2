package uz.pdp.lesson1_task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.lesson1_task1.entity.Address;
import uz.pdp.lesson1_task1.entity.Company;
import uz.pdp.lesson1_task1.payload.AddressDTO;
import uz.pdp.lesson1_task1.payload.ApiResponse;
import uz.pdp.lesson1_task1.payload.CompanyDTO;
import uz.pdp.lesson1_task1.repository.AddressRepository;
import uz.pdp.lesson1_task1.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository repository;


    public List<Company> getAll(){
        return repository.findAll();
    }


    public Company getId(Integer id){
        Optional<Company> byId = repository.findById(id);
        return byId.orElseGet(Company::new);
    }

   @Autowired
   AddressRepository addressRepository;

    public ApiResponse add(CompanyDTO companyDTO){
        if (repository.existsByCorpName(companyDTO.getCorpName()))
            return new ApiResponse("There are this corpName",false);
        Company company = new Company();
        company.setCorpName(companyDTO.getCorpName());
        company.setDirectorName(company.getDirectorName());
        company.setAddress(addressRepository.getOne(companyDTO.getAddressId()));
        repository.save(company);
        return new ApiResponse("Saved successfully",true);
    }


    public ApiResponse edit(Integer id,CompanyDTO companyDTO){
        if (repository.existsByCorpNameAndIdNot(companyDTO.getCorpName(), id))
            return new ApiResponse("There are this corpName",false);
        Optional<Company> byId = repository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Id not found",false);
        Company company = byId.get();
        company.setCorpName(companyDTO.getCorpName());
        company.setDirectorName(company.getDirectorName());
        company.setAddress(addressRepository.getOne(companyDTO.getAddressId()));
        repository.save(company);
        return new ApiResponse("Edited successfully",true);
    }


    public ApiResponse delete(Integer id){
        Optional<Company> byId = repository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("This id not found",false);
        repository.deleteById(byId.get().getAddress().getId());
        repository.delete(byId.get());
        return new ApiResponse("Delete successfully",true);
    }
}