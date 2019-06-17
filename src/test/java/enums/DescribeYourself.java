package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DescribeYourself {
    CEO("ceo", "I am the CEO of the company"),
    TECH("specialist", "I am a specialist in our company"),
    SPECIALIST("tech", "I am a tech guy"),
    OTHER("other", "I am doing something else");

    private String value;
    private String name;

}
