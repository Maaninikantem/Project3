package com.example.javafxdemo;

/**
 This class defines that a student is a profile, major, and credits completed.
 @author Michael Mankiewicz, Maanini Kantem
 */
public abstract class Student implements Comparable<Student> {
    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;

    /**
     * This method validates the amount of credits the students are enrolled in upon the input
     * @param creditsEnrolled based on the credits the student is enrolled in
     * @return true if the credits are valid, false if not
     */
    public boolean isValid(int creditsEnrolled) {
        String nameOfClass = this.getClass().getName();
        String errorClassName = "";
        switch(nameOfClass){
            case "NonResident":
                errorClassName = "Non-Resident";
                break;
            case "Resident":
                errorClassName = "Resident";
                break;
            case "TriState":
                errorClassName = "Tristate";
                break;
        }
        if(creditsEnrolled >= 3 && creditsEnrolled <= 24){
            return true;
        }
        System.out.println("(" + errorClassName + ") " + creditsEnrolled +": invalid credit hours.");
        return false;
    }
    public abstract double tuitionDue(int creditsEnrolled); //polymorphism
    public abstract boolean isResident();
    public abstract String enrollString();
    /**
     This method constructs a student only using a profile as well as a major and credits completed.
     @param profile the student's profile.
     @param major the student's major.
     @param creditCompleted the student's credits completed.
     */
    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }
    /**
     This method constructs a student only using a profile.
     For searching purposes.
     @param profile the student's profile.
     */
    public Student(Profile profile) {
        this.profile = profile;
    }
    /**
     This method gets the student's profile.
     @return the student's profile.
     */
    public Profile getProfile() {
        return profile;
    }
    /**
     This method gets the major.
     @return the student's major.
     */
    public Major getMajor() {
        return major;
    }
    /**
     This method sets the major to whatever is passed in.
     @param newMajor the major to change to.
     */
    public void setMajor(Major newMajor){
        this.major = newMajor;
    }
    /**
     This method gets the credits completed.
     @return credits completed.
     */
    public void addCredits(int moreCredits){
        this.creditCompleted = creditCompleted + moreCredits;
    }

    /**
     * This method returns the amount of credits completed by the student
     * @return the credits completed by the student
     */
    public int getCreditCompleted() {
        return creditCompleted;
    }

    /**
     This method determines if student is equal to another.
     Profiles are students' unique identifiers.
     @return true of profiles are equal.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Student otherStudent){
            return profile.equals(otherStudent.profile);
        }
       return false;
    }
    /**
     This method determines if one student is greater than another.
     order of precedence is profile, major, and then credits completed.
     @return 1 if greater, -1 is less than, 0 if equal.
     */
    @Override
    public int compareTo(Student otherStudent){
        if(profile.compareTo(otherStudent.profile) > 0){
            return 1;
        } else if(profile.compareTo(otherStudent.profile) < 0){
            return -1;
        } else {

            if(major.compareTo(otherStudent.major) > 0){
                return 1;
            } else if(major.compareTo(otherStudent.major) < 0){
                return -1;
            } else {

                if(creditCompleted > otherStudent.creditCompleted){
                    return 1;
                } else if(creditCompleted < otherStudent.creditCompleted){
                    return -1;
                }
            }
        }
        return 0;
    }
    /*public static void main(String[] args){
        Date date1 = new Date("1/5/1999");
        Profile profile1 = new Profile("Saget", "Bob", date1);
        Student student1 = new Student(profile1);

        Date date2 = new Date("1/6/1999");
        Profile profile2 = new Profile("Saget", "Sally", date2);
        Student student2 = new Student(profile2);

        System.out.println(student1.compareTo(student2));
    }*/
}
