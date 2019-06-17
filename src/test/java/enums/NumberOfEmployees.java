package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NumberOfEmployees {
    ONE_EMPLOYEE("1", "1-5"),
    TEN_EMPLOYEES("10", "6-10"),
    TWENTY_EMPLOYEES("20", "11-100"),
    THIRTY_EMPLOYEES("30", "101-500"),
    FOURTY_EMPLOYEES("40", "500 -");

    private String value;
    private String name;
}
