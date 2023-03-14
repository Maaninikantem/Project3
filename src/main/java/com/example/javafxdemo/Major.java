package com.example.javafxdemo;

/**
 This class lists all the possible Majors, and defines the order of those majors.
 @author Michael Mankiewicz, Maanini Kantem
 */
public enum Major implements Comparable<Major>{
    BAIT("33:136", "BAIT", "RBS"),
    CS("01:198", "CS", "SAS"),
    MATH("01:640", "MATH", "SAS"),
    ITI("04:547", "ITI", "SC&I"),
    EE("14:332", "EE", "SOE");

    private final String majorCode;
    private final String majorName;
    private final String schoolName;
    /**
     This constructor defines a major as a majorName, schoolName, and majorCode.
     @param majorCode the major code.
     @param majorName the major name.
     @param schoolName the school name.
     */
    Major(String majorCode, String majorName, String schoolName) {
        this.majorCode = majorCode;
        this.majorName = majorName;
        this.schoolName = schoolName;
    }
    /**
     This method retrieves the major code.
     @return the major code.
     */
    public String getMajorCode() {
        return majorCode;
    }
    /**
     This method retrieves the major name.
     @return the major name.
     */
    public String getMajorName() {
        return majorName;
    }
    /**
     This method retrieves the school name.
     @return the school name.
     */
    public String getSchoolName(){
        return schoolName;
    }

}
