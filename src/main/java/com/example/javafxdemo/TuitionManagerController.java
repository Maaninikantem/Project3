package com.example.javafxdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.time.format.DateTimeFormatter;

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
                switch(i) {
                    case CS_INDEX:
                        return Major.CS;
                    case ITI_INDEX:
                        return Major.ITI;
                    case BAIT_INDEX:
                        return Major.BAIT;
                    case EE_INDEX:
                        return Major.EE;
                    case MATH_INDEX:
                        return Major.MATH;
                    default:
                        return Major.CS; //default, should never execute though
                }
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

}