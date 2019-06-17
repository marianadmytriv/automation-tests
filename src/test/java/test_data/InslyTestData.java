package test_data;


import enums.DescribeYourself;

import java.util.HashMap;
import java.util.Map;

import static enums.CompanyProfile.*;
import static enums.CompanyProfile.OTHER;
import static enums.DescribeYourself.*;
import static enums.NumberOfEmployees.*;

public final class InslyTestData {

    private InslyTestData() {
    }

    public static final String DOWNLOADS_PATH = System.getProperty("user.home") + "\\Downloads\\";
    public static final String INSLY_URL = "https://signup.int.staging.insly.training";


    public static Map<Integer, String> companyProfile = new HashMap<>();

    static {
        companyProfile.put(1, INSURANCE_AGENCY.getValue());
        companyProfile.put(2, INSURANCE_BROKERAGE_COMPANY.getValue());
        companyProfile.put(3, INSURANCE_COMPANY.getValue());
        companyProfile.put(4, SOFTWARE_DEVELOPMENT_COMPANY.getValue());
        companyProfile.put(5, OTHER.getValue());
    }

    public static Map<Integer, String> numberOfEmployees = new HashMap<>();

    static {
        numberOfEmployees.put(1, ONE_EMPLOYEE.getValue());
        numberOfEmployees.put(2, TEN_EMPLOYEES.getValue());
        numberOfEmployees.put(3, TWENTY_EMPLOYEES.getValue());
        numberOfEmployees.put(4, THIRTY_EMPLOYEES.getValue());
        numberOfEmployees.put(5, FOURTY_EMPLOYEES.getValue());
    }

    public static Map<Integer, String> describeYourSelf = new HashMap<>();

    static {
        describeYourSelf.put(1, CEO.getValue());
        describeYourSelf.put(2, SPECIALIST.getValue());
        describeYourSelf.put(3, TECH.getValue());
        describeYourSelf.put(4, DescribeYourself.OTHER.getValue());
    }

}