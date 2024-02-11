//package com.example.reviewerx;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.Initializable;
//import javafx.scene.control.SelectionMode;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.ResourceBundle;
//
//public class Helper extends RootController implements Initializable {
//
//    ArrayList convertedAL;
//    ArrayList editedAL;
//    int maxSize;
//    int conAlSize;
//    int edtAlSize;
//
//    ObservableList curList;
//
//    public void tableViewer(ArrayList convertedAL,ArrayList editedAL){
//        conAlSize = convertedAL.size();
//        edtAlSize = editedAL.size();
//        this.convertedAL = convertedAL;
//        this.editedAL = editedAL;
//
//    }
//
//
//
//    public void initialize() {
//
//        //connecting colum names with the tabClass
//
//        colNo.setCellValueFactory(new PropertyValueFactory<>("TableNo"));
//        colConverted.setCellValueFactory(new PropertyValueFactory<>("TabConverted"));
//        colEdited.setCellValueFactory(new PropertyValueFactory<>("TabEdited"));
//
//        //setting items
//        tableView.setItems(viewer());  //viewer is an observable list
//
//        //
//        for (int i = 0; i < maxSize; i++) {
//            tableView.setItems(viewer());
//       }
//
//        //single col selection
//        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//        tableView.getSelectionModel().setCellSelectionEnabled(true);
//    }
//
//    ObservableList viewer(){
//
//        curList = FXCollections.observableArrayList();
//
//        maxSize = (conAlSize > edtAlSize) ? conAlSize : edtAlSize;  //might through issues
//
//        int j =0 ;
//        for (int i = 1; i <maxSize ; i++) {
//
//            curList.add(new tabClass(i, (String) convertedAL.get(j), editedAL.get(j).toString()));
//            j++;
//
//        }
//        return curList;
//
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//
//    }
//}
