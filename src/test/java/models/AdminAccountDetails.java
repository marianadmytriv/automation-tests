package models;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class AdminAccountDetails {
    private String webEmail;
    private String accountManagerName;
    private String password;
    private String passwordRepeat;
    private String phone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminAccountDetails that = (AdminAccountDetails) o;
        return Objects.equals(webEmail, that.webEmail) &&
                Objects.equals(accountManagerName, that.accountManagerName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(passwordRepeat, that.passwordRepeat) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(webEmail, accountManagerName, password, passwordRepeat, phone);
    }
}
