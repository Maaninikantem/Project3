package com.example.javafxdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class TuitionManagerController {

    @FXML
    private TextField rosterFirstName;
    @FXML
    private TextField rosterLastName;
    @FXML
    private TextField creditsCompleted;
    @FXML
    private DatePicker rosterDOB;
    @FXML
    private HBox majorBox;
    @FXML
    private RadioButton BAIT;
    @FXML
    private RadioButton CS;
    @FXML
    private RadioButton ITI;
    @FXML
    private RadioButton EE;
    @FXML
    private RadioButton MATH;
    @FXML
    private RadioButton resident;
    @FXML
    private RadioButton nonResident;
    @FXML
    private RadioButton triState;
    @FXML
    private RadioButton NY;
    @FXML
    private RadioButton CT;
    @FXML
    private RadioButton international;
    @FXML
    private CheckBox studyAbroad;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button changeButton;
    @FXML
    private Button loadButton;
    @FXML
    private TextField enrollFirstName;
    @FXML
    private TextField enrollLastName;
    @FXML
    private DatePicker enrollDOB;
    @FXML
    private TextField enrollCreds;
    @FXML
    private TextField schFirstName;
    @FXML
    private TextField schLastName;
    @FXML
    private DatePicker schDate;
    @FXML
    private TextField scholarship;
    @FXML
    private Button enroll;
    @FXML
    private Button drop;
    @FXML
    private HBox states;
    @FXML
    private TextArea displayArea;
    private static final int BAIT_INDEX = 0;
    private static final int CS_INDEX = 1;
    private static final int MATH_INDEX = 2;
    private static final int EE_INDEX = 3;
    private static final int ITI_INDEX = 4;
    private static final int CT_INDEX = 0;
    private static final int NY_INDEX = 1;

    Roster roster = new Roster();
    Enrollment enrollment = new Enrollment();

    @FXML
    void toggleStatus(ActionEvent e) {
        if (resident.isSelected()) {
            // If the student is resident, disable all non-resident options
            international.setDisable(true);
            triState.setDisable(true);
            studyAbroad.setDisable(true);
            CT.setDisable(true);
            NY.setDisable(true);
        } else if (nonResident.isSelected()) {
            // If the student is non-resident, enable all non-resident options
            international.setDisable(false);
            triState.setDisable(false);
            // Disable tristate options and study abroad options depending on the selected radio button
            if (international.isSelected()) {
                CT.setDisable(true);
                NY.setDisable(true);
                studyAbroad.setDisable(false);
            } else if (triState.isSelected()) {
                studyAbroad.setDisable(true);
                CT.setDisable(false);
                NY.setDisable(false);
            }
        }
    }
    @FXML
    private void addStudent(){
        String firstName = rosterFirstName.getText();
        String lastName = rosterLastName.getText();
        Date date = new Date(rosterDOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        Major major = getMajorFromRadioButton();
        int creds;
        try {
            creds = Integer.parseInt(creditsCompleted.getText());
        } catch (NumberFormatException e) {
            displayArea.setText("Credits completed invalid: not an integer!");
            return;
        }
        Profile profile = new Profile(firstName, lastName, date);
        if(roster.contains(profile)){
            displayArea.setText(profile + " is already in the roster.");
            return;
        }
        if(resident.isSelected()){
            Resident newResident = new Resident(profile, major, creds);
            roster.add(newResident);
        }
        else if(nonResident.isSelected()){
            NonResident newNonResident = new NonResident(profile, major, creds);
            roster.add(newNonResident);
        }
        else if(triState.isSelected()){
            String state = getStateFromRadioButton();
            TriState newTriState = new TriState(profile, major, creds, state);
            roster.add(newTriState);
        }
        else if(international.isSelected()){
            Boolean studyAb = studyAbroad.isSelected();
            International newInternational = new International(profile, major, creds, studyAb);
            roster.add(newInternational);
        }
        displayArea.setText(profile + " added to the roster.");
    }

    @FXML
    private void removeStudent(){
        String firstName = rosterFirstName.getText();
        String lastName = rosterLastName.getText();
        Date date = new Date(rosterDOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        Profile profile = new Profile(firstName, lastName, date);
        Student studentToRemove = roster.getStudent(profile);

        if(roster.remove(studentToRemove)){
            displayArea.setText(profile + " removed from the roster.");
        } else {
            displayArea.setText(profile + " is not in the roster.");
        }
    }



    private String getStateFromRadioButton() {
        int i = 0;
        for(Node child : states.getChildren()) {
            RadioButton radioButton = (RadioButton) child;
            if(radioButton.isSelected()) {
                switch(i) {
                    case CT_INDEX:
                        return "CT";
                    case NY_INDEX:
                        return "NY";
                    default:
                        return "CT"; //default, should never execute though
                }
            }
            i++;
        }
        return "CT"; //should never execute either
    }

    private Major getMajorFromRadioButton() {
        int i = 0;
        for(Node child : majorBox.getChildren()) {
            RadioButton radioButton = (RadioButton) child;
            if(radioButton.isSelected()) {
                return switch (i) {
                    case CS_INDEX -> Major.CS;
                    case ITI_INDEX -> Major.ITI;
                    case BAIT_INDEX -> Major.BAIT;
                    case EE_INDEX -> Major.EE;
                    case MATH_INDEX -> Major.MATH;
                    default -> Major.CS; //default, should never execute though
                };
            }
            i++;
        }
        return Major.CS; //should never execute either
    }

    @FXML
    private void printRoster(){
        displayArea.setText(roster.print());
    }

    @FXML
    private void printBySchool(){
        displayArea.setText(roster.printBySchoolMajor());
    }
    @FXML
    private void printByStanding(){
        displayArea.setText(roster.printByStanding());
    }

    @FXML
    private void printInRBS(){
        displayArea.setText(roster.printInSchool("RBS"));
    }

    @FXML
    private void printInSAS(){
        displayArea.setText(roster.printInSchool("SAS"));
    }

    @FXML
    private void printInSCII(){
        displayArea.setText(roster.printInSchool("SC&I"));
    }

    @FXML
    private void printInSOE(){
        displayArea.setText(roster.printInSchool("SOE"));
    }

    @FXML
    private void changeMajor(){
        String firstName = rosterFirstName.getText();
        String lastName = rosterLastName.getText();
        Date date = new Date(rosterDOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        Major major = getMajorFromRadioButton();

        Profile profile = new Profile(firstName, lastName, date);
        if(!roster.changeMajor(profile, major)){
            displayArea.setText("Can't find the given student");
            return;
        }
        displayArea.setText("Major changed Successfully!");
    }

    @FXML
    private void enrollStudent(){
        String firstName = enrollFirstName.getText();
        String lastName = enrollLastName.getText();
        Date date = new Date(enrollDOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        int creds;
        try {
            creds = Integer.parseInt(enrollCreds.getText());
        } catch (NumberFormatException e) {
            displayArea.setText("Credits completed invalid: not an integer!");
            return;
        }

        Profile profile = new Profile(firstName, lastName, date);
        EnrollStudent enrollStudent = new EnrollStudent(profile, creds);


        displayArea.setText(enrollment.add(enrollStudent));
    }

    @FXML
    private void dropStudent(){
        String firstName = enrollFirstName.getText();
        String lastName = enrollLastName.getText();
        Date date = new Date(enrollDOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        Profile profile = new Profile(firstName, lastName, date);

        if(!enrollment.contains(profile)){
            displayArea.setText("the student is not in enrollment. ");
            return;
        }
        enrollment.remove(enrollment.getEnrollStudent(profile));
        displayArea.setText(profile + "removed from enrollment. ");
    }

    @FXML
    private void applyScholarship(){
        String firstName = schFirstName.getText();
        String lastName = schLastName.getText();
        Date date = new Date(schDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        int scholar;
        try {
            scholar = Integer.parseInt(scholarship.getText());
        } catch (NumberFormatException e) {
            displayArea.setText("Amount entered invalid: not an integer!");
            return;
        }

        Profile profile = new Profile(firstName, lastName, date);
        if(!roster.contains(profile)){
            displayArea.setText("This profile doesn't exist in the roster. ");
            return;
        }
        if(roster.getStudent(profile) instanceof Resident resident){
            resident.setScholarship(scholar);
            displayArea.setText("Successfully Applied Scholarship");
        }
        else{
            displayArea.setText("Cannot apply scholarship to a non-resident");
        }
    }

    @FXML
    private void printEnrollment(){
        displayArea.setText(enrollment.print());
    }

    @FXML
    private void printTuitionDue(){
        displayArea.setText(enrollment.printTuition(roster));
    }

    @FXML
    private void semEnd(){
        displayArea.setText(enrollment.semesterEnd(roster));
    }

    @FXML
    public void loadStudents() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("studentList.txt"));
        displayArea.setText("");
        while(scanner.hasNextLine())
        {
            String studentLine = scanner.nextLine();
            String[] studentParameters = studentLine.split(",");
            String firstName = studentParameters[1];
            String lastName = studentParameters[2];
            Date date = new Date(studentParameters[3]);
            Major major;
            try {
                major = Major.valueOf(studentParameters[4].toUpperCase());
            } catch (IllegalArgumentException e) {
                displayArea.setText(displayArea.getText() + "Major code invalid: " + studentParameters[4].toUpperCase());
                return;
            }
            int creds;
            try {
                creds = Integer.parseInt(studentParameters[5]);
            } catch (NumberFormatException e) {
                displayArea.setText(displayArea.getText() + "Credits completed invalid: not an integer!");
                return;
            }
            Profile profile = new Profile(firstName, lastName, date);
            if(roster.contains(profile)){
                displayArea.setText(displayArea.getText() + profile + " is already in the roster.");
                return;
            }
            switch (studentParameters[0]) {
                case "R" -> {
                    Resident newResident = new Resident(profile, major, creds);
                    roster.add(newResident);
                }
                case "N" -> {
                    NonResident newNonResident = new NonResident(profile, major, creds);
                    roster.add(newNonResident);
                }
                case "T" -> {
                    String state = getStateFromRadioButton();
                    TriState newTriState = new TriState(profile, major, creds, state);
                    roster.add(newTriState);
                }
                case "I" -> {
                    boolean studyAb = studyAbroad.isSelected();
                    International newInternational = new International(profile, major, creds, studyAb);
                    roster.add(newInternational);
                }
            }
            displayArea.setText(displayArea.getText() + "\n" + profile + " added to the roster.");
        }
        displayArea.setText(displayArea.getText() + "\n" + "All students loaded to the roster.");
    }
}