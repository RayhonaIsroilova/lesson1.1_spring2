package uz.pdp.lesson1_task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    @NotNull(message = "name bo'sh   bo'lmasligi kere")
    private String name;

    @NotNull(message = "companyId bo'sh   bo'lmasligi kere")
    private Integer companyId;
}
