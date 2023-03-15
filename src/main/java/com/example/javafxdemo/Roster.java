package com.example.javafxdemo;

/**
 This class is responsible for executing basic commands to manipulate or display a roster.
 @author Michael Mankiewicz, Maanini Kantem
 */
public class Roster {
    private Student[] roster;
    private int size;
    /**
     This method will change a student's major in the roster.
     @param student the student to change the major for.
     @param newMajor the major to change to.
     @return true if the student exists.
     */
    public boolean changeMajor(Student student, Major newMajor){
        if (contains(student)){
            int studentIndex = find(student);
            roster[studentIndex].setMajor(newMajor);
            return true;
        }
        return false;
    }
    /**
     This method will find the index of where the student is in the roster.
     @param student the student to search for.
     @return the index of the student in the roster array.
     */
    private int find(Student student) {
        for(int i = 0; i< size; i++){
            if(roster[i].equals(student)){
                return i;
            }
        }
        return -1;
    } //search the given student in roster
    /**
     This method will increase the size of the roster by 4.
     */
    private void grow() {
        int rosterIncrement = 4;
        int newCapacity = roster.length + rosterIncrement;
        Student[] newRoster = new Student[newCapacity];
        for(int i = 0; i < size; i++){
            newRoster[i] = roster[i];
        }
        roster = newRoster;
    } //increase the array capacity by 4
    /**
     This method will add a new student element into the roster.
     @param student the student to add.
     @return false if student already in roster.
     */
    public boolean add(Student student){
        if(contains(student)){
            return false;
        }
        int rosterIncrement = 4;
        if(roster == null){
            roster = new Student[rosterIncrement];
        }
        if(size >= roster.length){
            grow();
        }
        roster[size] = student;
        size++;
        return true;
    } //add student to end of array
    /**
     This method will remove a student from the roster.
     @param student the student to remove.
     @return false if student not in roster.
     */
    public boolean remove(Student student){
        if(!contains(student)){
            return false;
        }
        for(int i = 0; i < size; i++){
            if(roster[i].equals(student)){
                for(int j = 0; j < (size - i - 1); j++){
                    roster[i + j] = roster[i + j + 1];
                }
                roster[size - 1] = null;
                size--;
            }
        }
        return true;
    }

    /**
     * This method is used to find the index of the student based on the profile
     * @param profile, the student's profile
     * @return the index of the student in the roster
     */
    public Student getStudent(Profile profile){
        int studentIndex = findProfile(profile);
        if(studentIndex == -1){
            return null;
        }
        return roster[studentIndex];
    }

    /**
     * This method is used to find the profile of the student in the roster
     * @param profile based on the profile of the student
     * @return index of the profile is found, -1 if not found
     */
    private int findProfile(Profile profile) {
        int NOT_FOUND = -1;
        for(int i = 0; i< size; i++){
            if(roster[i].getProfile().equals(profile)){
                return i;
            }
        }
        return NOT_FOUND;
    }
    //maintain the order after remove
    /**
     This method will determine if a student is already in the roster.
     @param student the student to search for.
     @return true if student in roster.
     */
    public boolean contains(Student student){
        int NOT_FOUND = -1;
        return find(student) != NOT_FOUND;
    } //if the student is in roster

    /**
     * This method determines if the profile is in the roster or not using the findProfile method
     * @param profile finding the profile given
     * @return false it not found, true if found
     */
    public boolean contains(Profile profile){
        int NOT_FOUND = -1;
        return findProfile(profile) != NOT_FOUND;
    }
    /**
     This method will sort the roster by last name, first name, and date of birth.
     @param schoolFilter which school to sort. if equal to "all" then all schools will be sorted.
     */
    void sortDefault(String schoolFilter) {
        // One by one move boundary of unsorted subarray
        int min = 0;
        for (int i = 0; i < size-1; i++)
        {
            if(!roster[i].getMajor().getSchoolName().equals(schoolFilter) && !schoolFilter.equals("all")){
                continue;
            }
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < size; j++){
                if(!roster[j].getMajor().getSchoolName().equals(schoolFilter) && !schoolFilter.equals("all")){
                    continue;
                }
                if (roster[j].compareTo(roster[min_idx]) < min)
                    min_idx = j;
            }
            Student temp = roster[min_idx];
            roster[min_idx] = roster[i];
            roster[i] = temp;
        }
    }
    /**
     This method will determine if a student's academic standing is greater or less than another.
     @param student1 the student to compare from.
     @param student2 the student to compare to.
     @return 1 if a student has a great academic standing, -1 if less, and 0 if the same.
     */
    int compareStanding(Student student1, Student student2){

        int standing1;
        if(student1.getCreditCompleted() < 30){
            standing1 = 1;
        } else if(student1.getCreditCompleted() < 60){
            standing1 = 2;
        } else if(student1.getCreditCompleted() < 90){
            standing1 = 3;
        } else {
            standing1 = 4;
        }

        int standing2;
        if(student2.getCreditCompleted() < 30){
            standing2 = 1;
        } else if(student2.getCreditCompleted() < 60){
            standing2 = 2;
        } else if(student2.getCreditCompleted() < 90){
            standing2 = 3;
        } else {
            standing2 = 4;
        }

        if(standing1 > standing2){
            return 1;
        } else if (standing1 < standing2){
            return -1;
        }
        return 0;
    }
    /**
     This method will sort the roster by academic standing only.
     */
    void sortStanding() {
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < size-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < size; j++){

                if (compareStanding(roster[j],roster[min_idx]) < 0)
                    min_idx = j;
            }
            // Swap the found minimum element with the first
            // element
            Student temp = roster[min_idx];
            roster[min_idx] = roster[i];
            roster[i] = temp;
        }
    }
    /**
     This method will sort the roster by school and major.
     */
    void sortSchool() {
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < size-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < size; j++){

                if (roster[j].getMajor().compareTo(roster[min_idx].getMajor()) < 0)
                    min_idx = j;
            }
            // Swap the found minimum element with the first
            // element
            Student temp = roster[min_idx];
            roster[min_idx] = roster[i];
            roster[i] = temp;
        }
    }
    /**
     This method will display the roster in last name, first name, and DOB order.
     */
    public String print () {
        //sorting by last name first name, DOB
        String schoolFilter = "all";
        sortDefault(schoolFilter);

        String string = "";
        if(roster != null) {
            for (int i = 0; i < size; i++) {
                string += roster[i] + "\n";
            }
        } else {
            return "Student roster is empty!";
        }

        if(!string.equals("")){
            string = string.substring(0, string.length() - 1) ;
        } else {
            string = "";
        }
        String headline = "* Student roster sorted by last name, first name, DOB **";
        //System.out.println(string);
        String footer = "* end of roster **";
        return headline + "\n" + string + "\n" + footer + "\n";
    } //print roster sorted by profiles
    /**
     This method will display the roster in school and major order.
     */
    public String printBySchoolMajor() {
        sortSchool();
        String string = "";
        if(roster != null) {
            for (int i = 0; i < size; i++) {
                string += roster[i] + "\n";
            }
        } else {
            return "Student roster is empty!";
        }
        if(!string.equals("")){
            string = string.substring(0, string.length() - 1) ;
        } else {
            string = "";
        }
        String header = "* Student roster sorted by school, major **";
        String footer = "* end of roster **";

        return header + "\n" + string + "\n" + footer + "\n";

    } //print roster sorted by school major
    /**
     This method will display the roster in increasing academic standing order.
     */
    public void printByStanding() {
        sortStanding();
        String string = "";
        if(roster != null) {
            for (int i = 0; i < size; i++) {
                string += roster[i] + "\n";
            }
        } else {
            System.out.println("Student roster is empty!");
            return;
        }
        if(!string.equals("")){
            string = string.substring(0, string.length() - 1) ;
        } else {
            string = "";
        }
        System.out.println("* Student roster sorted by standing **");
        System.out.println(string);
        System.out.println("* end of roster **");
    } //print roster sorted by standing
    /**
     This method will list the students in a specific school in last name, first name, and DOB order.
     @param schoolName the school to display students from.
     */
    public void printInSchool(String schoolName){
        sortDefault(schoolName);

        String string = "";
        if(roster != null) {
            for (int i = 0; i < size; i++) {
                if (roster[i].getMajor().getSchoolName().equals(schoolName)) {
                    string += roster[i] + "\n";
                }
            }
        } else {
            System.out.println("Student roster is empty!");
            return;
        }

        if(!string.equals("")){
            string = string.substring(0, string.length() - 1) ;
        } else {
            System.out.println("School doesn't exist: " + schoolName);
            return;
        }
        System.out.println("* Students in " + schoolName + " **");
        System.out.println(string);
        System.out.println("* end of list **");
    }
    /*
    public static void main(String[] args){
        Roster roster = new Roster();
        Resident student1 = new Resident(new Profile("John", "Doe", new Date("9/2/2001")), Major.BAIT, 10);
        Resident student2 = new Resident(new Profile("John", "Doe", new Date("9/2/2002")), Major.CS, 30);
        Resident student3 = new Resident(new Profile("Max", "Doe", new Date("9/2/1999")), Major.ITI, 13);
        Resident student4 = new Resident(new Profile("Luke", "Doe", new Date("9/2/1988")), Major.EE, 23);
        Resident student5 = new Resident(new Profile("Bob", "Doe", new Date("9/2/1978")), Major.MATH, 25);
        International student6 = new International(new Profile("Chimi", "Changa", new Date("9/2/1978")), Major.MATH, 15, true);
        International student7 = new International(new Profile("Bob", "Dillan", new Date("9/2/1978")), Major.CS, 22, false);
        NonResident student8 = new NonResident(new Profile("Mike", "Griffin", new Date("9/2/1978")), Major.ITI, 8);
        NonResident student9 = new TriState(new Profile("Shmelly", "Griffin", new Date("9/2/1978")), Major.EE, 12, "NY");
        NonResident student10 = new TriState(new Profile("Lois", "Griffin", new Date("9/2/1978")), Major.BAIT, 88, "CT");
        roster.add(student1);
        roster.add(student2);
        roster.add(student3);
        roster.add(student4);
        roster.add(student5);
        roster.add(student6);
        roster.add(student7);
        roster.add(student8);
        roster.add(student9);
        roster.add(student10);
        roster.add(student10);
        System.out.println("finished adding students. current roster: " + roster);
        roster.remove(student10);
        roster.remove(student10);
        roster.remove(student9);
        roster.remove(student8);
        roster.remove(student7);
        roster.remove(student6);
        roster.remove(student5);
        roster.remove(student4);
        roster.remove(student3);
        roster.remove(student2);
        roster.remove(student1);
        System.out.println("finished removing students. current roster: " + roster);
    }
    */
     
}
