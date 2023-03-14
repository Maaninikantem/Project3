package com.example.javafxdemo;

/**
 * This class extends student and determines the status of students based on their status
 * this class is extended by Tristate and international
 * @author Michael Mankiewicz, Maanini Kantem
 */
public class NonResident extends Student {

    /**
     * This is the constructor of the non-resident student, based on their parameters listed
     * @param profile the profile of the student
     * @param major the major of the student
     * @param creditCompleted the amount of credits the student finished
     */
    public NonResident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
    }

    /**
     * This method determines the tuition due from the non-resident student based on the amount of credits they're enrolled in
     * @param credits, the amount credits the student is enrolled in
     * @return the tuition due
     */
    public double tuitionDue(int credits){
        double FULLTIMERATE = 29737;
        double PARTTIMEPC = 966;
        double UNIFEE = 3268;
        double PERCENT = 0.8;
        double OVERTIME = 16;
        double FULLTIME = 12;
        double PARTTIME = 3;

        double tuitionDue = 0;
        if(credits > OVERTIME){
            double extraCredits = credits - OVERTIME;
            tuitionDue = FULLTIMERATE + UNIFEE + (PARTTIMEPC * extraCredits);
        }else if(credits >= FULLTIME){
            tuitionDue = FULLTIMERATE + UNIFEE;
        }else if(credits >= PARTTIME){
            tuitionDue = (credits * PARTTIMEPC) + (PERCENT * UNIFEE);
        }
        return tuitionDue;
    }

    /**
     * This method is used to determine if the student is a resident or not, default being false for non-resident
     * @return boolean false because the student in this class is non-resident
     */
    public boolean isResident() {
        return false;
    }

    /**
     * This method is a helper method to help us get the non-resident student as the string form
     * @return the string representation of profile of the non-resident
     */
    public String enrollString(){
        return getProfile() + " (" + "Non-Resident" + ")";
    }

    /**
     * This method represents the amount due as a string, along with the string representation of the student object
     * @return the string representation of student with their status and tuition due
     */
    @Override
    public String toString(){
        String standing;

        if(getCreditCompleted() < 30){
            standing = "Freshman";
        } else if(getCreditCompleted() < 60){
            standing = "Sophomore";
        } else if(getCreditCompleted() < 90){
            standing = "Junior";
        } else {
            standing = "Senior";
        }
        return getProfile() + " (" + getMajor().getMajorCode()+ " " + getMajor().getMajorName() + " " + getMajor().getSchoolName() + ") " + "credits completed: " + getCreditCompleted() + " (" + standing + ") (non-resident)";
    }
}
