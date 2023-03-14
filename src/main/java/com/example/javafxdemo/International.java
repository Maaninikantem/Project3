package com.example.javafxdemo;

/**
 * This class defines the international students, and determines if they are studyAbroad or not.
 * @author Michael Mankiewicz, Maanini Kantem
 */
public class International extends NonResident {

    private boolean isStudyAbroad;

    /**
     * This is the constructor of the international student, taking in the same parameters as it's super class and the boolean
     * @param profile, the profile of the student, their name and dob
     * @param major, the major of the student
     * @param creditCompleted, the amount of cresits the student has finished
     * @param isStudyAbroad, to see if student is part of the studyAbroad program or not
     */
    public International(Profile profile, Major major, int creditCompleted, boolean isStudyAbroad) {

        super(profile, major, creditCompleted);
        this.isStudyAbroad = isStudyAbroad;
    }

    /**
     * This method determines the amoutn of tuition every student owes based on the amount of enrolled credits
     * @param credits based on the credits and status, the amount of money the student owes is determined
     * @return the amount of tuition the student owes
     */
    public double tuitionDue(int credits){
        double FULLTIMERATE = 29737;
        double PARTTIMEPC = 966;
        double UNIFEE = 3268;
        double OVERTIME = 16;
        double FULLTIME = 12;
        double PARTTIME = 3;
        double HEALTHINS = 2650;

        double tuitionDue = 0;
        if(credits > OVERTIME){
            double extraCredits = credits - OVERTIME;
            tuitionDue = FULLTIMERATE + UNIFEE + HEALTHINS + (PARTTIMEPC * extraCredits);
        }else if(credits >= FULLTIME){
            tuitionDue = FULLTIMERATE + UNIFEE + HEALTHINS;
        }else if(credits >= PARTTIME){
            tuitionDue = UNIFEE + HEALTHINS;
        }
        return tuitionDue;
    }

    /**
     * This method represents the amount due as a string
     * @return the student's profile, major, and stading along with their status and tuition due
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
        if(isStudyAbroad){
            return getProfile() + " (" + getMajor().getMajorCode()+ " " + getMajor().getMajorName() + " " + getMajor().getSchoolName() + ") " + "credits completed: " + getCreditCompleted() + " (" + standing + ") (international:study abroad)";
        } else{
            return getProfile() + " (" + getMajor().getMajorCode()+ " " + getMajor().getMajorName() + " " + getMajor().getSchoolName() + ") " + "credits completed: " + getCreditCompleted() + " (" + standing + ") (international)";
        }
    }

    /**
     * This is a helper method to determine if the student is studyAbroad or not.
     * @return the string method that represent the status of the student
     */
    public String enrollString(){
        String studyAbroad = "";
        if(isStudyAbroad){
            studyAbroad = "study abroad";
        }
        return getProfile() + " (" + "International student" + studyAbroad +")";
    }

    /**
     * This method validates the credits the student is enrolled in, based on if its valid or not
     * @param creditsEnrolled determining if the credit input is valid
     * @return true if it is valid, false if not
     */
    @Override
    public boolean isValid(int creditsEnrolled){
        if(isStudyAbroad){
            if(creditsEnrolled >= 3 && creditsEnrolled <= 12){
                return true;
            }
            System.out.println("(International studentstudy abroad) " + creditsEnrolled +": invalid credits hours.");
            return false;
        } else {
            if(creditsEnrolled >= 12 && creditsEnrolled <= 24){
                return true;
            }
            System.out.println("(International student) " + creditsEnrolled +": invalid credits hours.");
            return false;
        }
    }

    /*

    public static void main(String[] args){
        International student6 = new International(new Profile("Chimi", "Changa", new Date("9/2/1978")), Major.MATH, 15, true);
        International student7 = new International(new Profile("Bob", "Dillan", new Date("9/2/1978")), Major.CS, 22, false);

        System.out.println(student6.tuitionDue(12));
        System.out.println(student6.tuitionDue(3));
        System.out.println(student6.tuitionDue(17));
        System.out.println(student7.tuitionDue(12));
        System.out.println(student7.tuitionDue(3));
        System.out.println(student7.tuitionDue(17));
    }
    */

}
