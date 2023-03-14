package com.example.javafxdemo;

import java.text.DecimalFormat;

/**
 * This class defines the students that are being enrolled for classes
 * @author Michael Mankiewicz, Maanini Kantem
 */
public class EnrollStudent {
    private Profile profile;
    private int creditsEnrolled;

    /**
     * This is the constructor for every student who is to be enrolled in the system
     * @param profile, each student is accessed by the profile which contains their full name and Dob
     * @param creditsEnrolled, also they are registered by the amount of credits they are enrolled in the current semester
     */
    public EnrollStudent(Profile profile, int creditsEnrolled){
        this.profile = profile;
        this.creditsEnrolled = creditsEnrolled;
    }

    /**
     * This constructor only accesses the students by the profile
     * @param profile each student is accessed by the profile which contains their full name and Dob
     */
    public EnrollStudent(Profile profile){
        this.profile = profile;
    }

    /**
     * this method is used to get the student's number of creditsEnrolled for the semester
     * @return the amount of credits the student is currently enrolled in
     */
    public int getCreditsEnrolled() {
        return creditsEnrolled;
    }

    /**
     * this method is used to set the student's number of creditsEnrolled for the semester
     */
    public void setCreditsEnrolled(int newCredits) {
        this.creditsEnrolled = newCredits;
    }

    /**
     * this method is used to help access the profile of the student being enrolled
     * @return the profile of the student
     */
    public Profile getProfile(){
        return profile;
    }

    /**
     * This method returns true if both the enrollStudent objects match, false if not
     * @param obj of the enrollStudent instance
     * @return boolean, true or false based on the match
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof EnrollStudent otherEnrollStudent){
            return profile.equals(otherEnrollStudent.profile) && creditsEnrolled == otherEnrollStudent.creditsEnrolled;
        }
        return false;
    }

    /**
     * this method is used to convert the enrollStudent object to be represented as a string
     * @return the string version of the enrollStudent object
     */
    @Override
    public String toString(){
        return profile.toString() + " enrolled " + creditsEnrolled + " credits";
    }

    /**
     * This method is used to print the tuition each enrolled student owes, based on their status
     * @param roster for all the students from the roster, that are currently enrolled
     * @return the string representaiton of the student object and the tuition amount they owe
     */
    public String printTuition(Roster roster){
        DecimalFormat df = new DecimalFormat("##,###.00");
        Student student = roster.getStudent(profile);
        return  student.enrollString() + " enrolled: " + creditsEnrolled + " credits: tuition due: $" + df.format(student.tuitionDue(creditsEnrolled));
    }

}
