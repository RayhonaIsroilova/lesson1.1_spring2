package uz.pdp.lesson1_task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.lesson1_task1.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    boolean existsByStreetAndHomeNumber(String street, String homeNumber);
    boolean existsByHomeNumberAndIdNot(String homeNumber, Integer id);
}
