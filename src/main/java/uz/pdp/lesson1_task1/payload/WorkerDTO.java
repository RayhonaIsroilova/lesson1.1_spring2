package uz.pdp.lesson1_task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lesson1_task1.entity.Address;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDTO {

    @NotNull(message = "name bo'sh   bo'lmasligi kere")
    private String name;

    @NotNull(message = "phoneNumber bo'sh bo'lmasligi kere")
    private String phoneNumber;

    @NotNull(message = "addressId bo'sh bo'lmasligi kere")
    private Integer addressId;

    @NotNull(message = "departmentId bo'sh   bo'lmasligi kere")
    private Integer departmentId;
}
