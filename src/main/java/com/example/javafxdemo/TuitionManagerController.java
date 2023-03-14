package com.example.javafxdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TuitionManagerController {
    @FXML
    private Label myLabel;

    @FXML
    public void testClickAdd (ActionEvent e){
        myLabel.setText("added!");
    }


}