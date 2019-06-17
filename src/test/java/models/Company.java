package models;

import interfaces.Copyable;
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
public class Company implements Copyable{
    private String companyName;
    private String country;
    private String inslyAddress;
    private String companyProfile;
    private String numberOfEmployees;
    private String describeYourself;

    @Override
    public Copyable copy() {
        return new Company(
                companyName,
                country,
                inslyAddress,
                companyProfile,
                numberOfEmployees,
                describeYourself
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(companyName, company.companyName) &&
                Objects.equals(country, company.country) &&
                Objects.equals(inslyAddress, company.inslyAddress) &&
                Objects.equals(companyProfile, company.companyProfile) &&
                Objects.equals(numberOfEmployees, company.numberOfEmployees) &&
                Objects.equals(describeYourself, company.describeYourself);
    }

    @Override
    public int hashCode() {

        return Objects.hash(companyName, country, inslyAddress, companyProfile, numberOfEmployees, describeYourself);
    }
}
