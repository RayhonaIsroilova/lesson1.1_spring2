package uz.pdp.lesson1_task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    @NotNull(message = "corpName bo'sh   bo'lmasligi kere")
    private String corpName;

    @NotNull(message = "directorName bo'sh   bo'lmasligi kere")
    private String directorName;

    @NotNull(message = "addressId bo'sh   bo'lmasligi kere")
    private Integer addressId;
}
