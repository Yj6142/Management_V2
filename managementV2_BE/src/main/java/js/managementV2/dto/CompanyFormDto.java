package js.managementV2.dto;

import lombok.Data;

import java.util.Currency;

@Data
public class CompanyFormDto {

    private String name;
    private Currency currency;
    private float defaultDiscount;

}
