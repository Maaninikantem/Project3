package com.example.javafxdemo;

import java.util.Calendar;
/**
 This class defines a date.
 @author Michael Mankiewicz, Maanini Kantem
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int JAN = 1;
    public static final int FEB = 2;
    public static final int MAR = 3;
    public static final int APR = 4;
    public static final int MAY = 5;
    public static final int JUN = 6;
    public static final int JUL = 7;
    public static final int AUG = 8;
    public static final int SEP = 9;
    public static final int OCT = 10;
    public static final int NOV = 11;
    public static final int DEC = 12;
    public static final int DAYS_31 = 31;
    public static final int DAYS_30 = 30;
    public static final int DAYS_28 = 28;
    public static final int DAYS_29 = 29;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    /**
     This is constructor defines a date as a day, month, and year
     @param date the date as a string
     */
    public Date(String date) {
        String[] parts = date.split("/");
        this.month = Integer.parseInt(parts[0]);
        this.day = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);
    }
    /**
     This method determines if a student is at least 16 years old relative to the current date
     @return true if student is 16 years old or greater
     */
    public boolean isAtleastSixteen(){
        Calendar calendar = Calendar.getInstance();

        int minimumAge = 16;

        if(year + minimumAge > calendar.get(Calendar.YEAR)){
            return false;
        } else if(year + minimumAge < calendar.get(Calendar.YEAR)){
            return true;
        } else{
            if(month > calendar.get(Calendar.MONTH) + 1){
                return false;
            } else if(month < calendar.get(Calendar.MONTH) + 1){
                return true;
            } else{
                if(day > calendar.get(Calendar.DAY_OF_MONTH)){
                    return false;
                } else if(day < calendar.get(Calendar.DAY_OF_MONTH)){
                    return true;
                }
            }
        }
        return true;
    }
    /**
     This method determines if a date is a valid date on the Calendar
     @return true if date is a possible date on the Calendar
     */
    public boolean isValid() {
        if (month < JAN || month > DEC) {
            return false;
        }

        int daysInMonth = 0;

        switch (month) {
            case JAN, MAR, MAY, JUL, AUG, OCT, DEC -> daysInMonth = DAYS_31;
            case APR, JUN, SEP, NOV -> daysInMonth = DAYS_30;
            case FEB -> {
                if (isLeapYear()) {
                    daysInMonth = DAYS_29;
                } else {
                    daysInMonth = DAYS_28;
                }
            }
        }
        return day >= 1 && day <= daysInMonth;
    }
    /**
     This method determines if a year is considered a leap year
     @return true if date is a leap year
     */
    private boolean isLeapYear() {
        if (year % QUADRENNIAL == 0) {
            if (year % CENTENNIAL == 0) {
                return year % QUATERCENTENNIAL == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    /**
     This method converts the date into a string.
     @return String version of date.
     */
    @Override
    public String toString() {
        return this.month + "/" + this.day + "/" + this.year;
    }
    /**
     This method determines if two dates are equal.
     @param obj an instance of Date.
     @return true if two dates refer to the same time. (same day, month, and year)
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Date otherDate) {
            return year == otherDate.year && month == otherDate.month && day == otherDate.day;
        }
        return false;
    }
    /**
     This method compares if a date is greater than another.
     @param otherDate the date that this date will compare to.
     @return 1 if this date occurs after otherDate, -1 if this date occurs before, and 0 if they are equal.
     */
    @Override
    public int compareTo(Date otherDate) {
        if (year > otherDate.year) {
            return 1;
        } else if (year < otherDate.year) {
            return -1;
        } else {
            if (month > otherDate.month) {
                return 1;
            } else if (month < otherDate.month) {
                return -1;
            } else {
                if (day > otherDate.day) {
                    return 1;
                } else if (day < otherDate.day) {
                    return -1;
                }
            }
        }
        return 0;
    }

    /**
    public static void main(String[] args){
        Date date1 = new Date("1/5/2001");
        Date date2 = new Date("1/5/2008");
        Date date3 = new Date("1/5/2999");
        Date date4 = new Date("1/5/-1");
        Date date5 = new Date("-1/5/1999");
        Date date6 = new Date("40/3/2002");
        Date date7 = new Date("3/260/2002");

        System.out.println("date1 is valid: " + date1.isValid());
        System.out.println("date2 is valid: " + date2.isValid());
        System.out.println("date3 is valid: " + date3.isValid());
        System.out.println("date4 is valid: " + date4.isValid());
        System.out.println("date5 is valid: " + date5.isValid());
        System.out.println("date5 is valid: " + date6.isValid());
        System.out.println("date5 is valid: " + date7.isValid());
    }
     **/
}


