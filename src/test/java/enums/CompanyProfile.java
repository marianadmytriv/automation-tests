package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum CompanyProfile {
    INSURANCE_AGENCY("IA", "Insurance Agency"),
    INSURANCE_BROKERAGE_COMPANY("IBC", "Insurance Brokerage Company"),
    INSURANCE_COMPANY("IC", "Insurance Company"),
    SOFTWARE_DEVELOPMENT_COMPANY("SDC", "Software Development Company"),
    OTHER("O", "Other");

    private String value;
    private String name;
}


