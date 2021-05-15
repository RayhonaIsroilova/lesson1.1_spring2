package uz.pdp.lesson1_task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    @NotNull(message = "street bo'sh bo'lmasligi kere")
    private String street;

    @NotNull(message = "homeNumber bo'sh bo'lmasligi kere")
    private String homeNumber;


}
