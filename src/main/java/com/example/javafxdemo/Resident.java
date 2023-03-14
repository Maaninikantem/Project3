package com.example.javafxdemo;

/**
 * This class extends from the student class and determines the tuition the resident student owes
 * @author Michael Mankiewicz, Maanini Kantem
 */
public class Resident extends Student {

    private int scholarship;

    /**
     * this is the constructor for the resident student based on the parameters
     * @param profile, represents the student's name and dob
     * @param major represents the major
     * @param creditCompleted represents the credits the student finished so far
     */

    public Resident(Profile profile, Major major, int creditCompleted) {
        super(profile, major, creditCompleted);
        scholarship = 0;
    }

    /**
     * This method determines the tuition due from the resident student based on the amount of credits they're enrolled in
     * @param credits the amount of currently enrolled credits to determine what student owes for the term
     * @return the amount of tuition due
     */
    public double tuitionDue(int credits){
        double FULLTIMERATE = 12536;
        double PARTTIMEPC = 404;
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
        return tuitionDue - scholarship;
    }


    /**
     * this method is used to set the scholarship to award the student, which will be deducted from the final amount due
     * @param scholarship the amount to be awarded
     */
    public void setScholarship(int scholarship){
        this.scholarship = scholarship;
    }

    /**
     * This method is used to determine if the student is resident or not, true if they are a resident and false if not
     * @return expected to return true since this is the resident class
     */
    public boolean isResident() {
        return true;
    }

    /**
     * This method is used to represent the resident student in a string form
     * @return the string form of the resident student
     */
    public String enrollString(){
        return getProfile() + " (" + "Resident" + ")";
    }

    /**
     * This method returns the string format of the resident with their tuition due
     * @return the string representing the student's profile, major, standing and school
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
        return getProfile() + " (" + getMajor().getMajorCode()+ " " + getMajor().getMajorName() + " " + getMajor().getSchoolName() + ") " + "credits completed: " + getCreditCompleted() + " (" + standing + ") (resident)";
    }

}
