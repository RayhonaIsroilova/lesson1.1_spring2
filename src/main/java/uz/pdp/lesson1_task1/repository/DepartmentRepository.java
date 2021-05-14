package uz.pdp.lesson1_task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.lesson1_task1.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
