package uz.pdp.lesson1_task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.lesson1_task1.entity.Address;
import uz.pdp.lesson1_task1.entity.Company;
@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByCorpName(String corpName);
    boolean existsByCorpNameAndIdNot(String corpName, Integer id);


}
