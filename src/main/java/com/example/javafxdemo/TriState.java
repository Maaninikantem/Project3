package com.example.javafxdemo;

/**
 * This class contains information about tri-state students including the state that they live in (NY or CT)
 * it also gives them a discounted tuition rate.
 */
public class TriState extends NonResident {

    private String state;

    public TriState(Profile profile, Major major, int creditCompleted, String state) {

        super(profile, major, creditCompleted);
        this.state = state;
    }

    /**
     * This method determines the tuition due from the tri-state student based on the amount of credits they're enrolled in
     * @param credits based on the credits they are enrolled in
     * @return the amount of tuition due
     */
    public double tuitionDue(int credits){
        double FULLTIMERATE = 29737;
        double PARTTIMEPC = 966;
        double UNIFEE = 3268;
        double PERCENT = 0.8;
        double OVERTIME = 16;
        double FULLTIME = 12;
        double PARTTIME = 3;
        double discount = 0;


        if(state.equals("NY")){
            discount = 4000;
        } else if(state.equals("CT")){
            discount = 5000;
        }
        
        double tuitionDue = 0;
        if(credits > OVERTIME){
            double extraCredits = credits - OVERTIME;
            tuitionDue = FULLTIMERATE + UNIFEE + (PARTTIMEPC * extraCredits) - discount;
        }else if(credits >= FULLTIME){
            tuitionDue = FULLTIMERATE + UNIFEE - discount;
        }else if(credits >= PARTTIME){
            tuitionDue = (credits * PARTTIMEPC) + (PERCENT * UNIFEE);
        }
        return tuitionDue;
    }

    /**
     * This is a toString method to represent the students in the output
     * @return the students profile, major, school, standing, and their resident status
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
        return getProfile() + " (" + getMajor().getMajorCode()+ " " + getMajor().getMajorName() + " " + getMajor().getSchoolName() + ") " + "credits completed: " + getCreditCompleted() + " (" + standing + ") (tri-state: " + state + ")";
    }

    /**
     * This method is used to return the tri-state student's information in the string form
     * @return profile of the tri-state student and the state they're from
     */
    public String enrollString(){
        return getProfile() + " (" + "Tri-state " + state +")";
    }
}