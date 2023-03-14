package com.example.javafxdemo;

/**
 This class defines that a profile is a last name, first name, and date of birth.
 A profile is a unique identifier for a student.
 @author Michael Mankiewicz, Maanini Kantem
 */
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob;
    /**
     This constructor defines a profile as a first name, last name, and date of birth.
     @param lname the last name of the student.
     @param fname the first name of the student.
     @param dob the date of birth of the student
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }
    /**
     This method converts this profile into a String.
     @return the profile as a String.
     */
    @Override
    public String toString(){
        return fname + " " + lname + " " + dob.toString();
    }
    /**
     This method determines if two profiles are equal.
     @return true if both profiles have the same last name, first name, and date of birth, case-insensitive.
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Profile otherProfile){
            return lname.equalsIgnoreCase(otherProfile.lname) && fname.equalsIgnoreCase(otherProfile.fname) && dob.equals(otherProfile.dob);
        }
        return false;
    }
    /**
     This method determines if one profile is greater than another.
     order of precedence is last name, first name, and then date of birth.
     @return 1 if greater, -1 is less than, 0 if equal.
     */
    @Override
    public int compareTo(Profile otherProfile){
        if(lname.compareToIgnoreCase(otherProfile.lname) > 0){
            return 1;
        } else if(lname.compareToIgnoreCase(otherProfile.lname) < 0){
            return -1;
        } else {

            if(fname.compareToIgnoreCase(otherProfile.fname) > 0){
                return 1;
            } else if(fname.compareToIgnoreCase(otherProfile.fname) < 0){
                return -1;
            } else {

                if(dob.compareTo(otherProfile.dob) > 0){
                    return 1;
                } else if(dob.compareTo(otherProfile.dob) < 0){
                    return -1;
                }
            }
        }
        return 0;
    }

}
