package com.example.reviewerx;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class RootController implements Initializable {

        @FXML
        private Button btnCheck;

        @FXML
        private Button btnConverted;

        @FXML
        private Button btnEdited;

        @FXML
        private Button btnSubmit;

        @FXML
        private Button btnViewinTable;

        @FXML
        private TableColumn<tabClass, String> colConverted;

        @FXML
        private TableColumn<tabClass, String> colEdited;

        @FXML
        private TableColumn<tabClass, String> colNo;

        @FXML
        private TextField inputCon;

        @FXML
        private TextField upConverted;

        @FXML
        private TextField upEdited;

        @FXML
        private TextField inputEdt;

        @FXML
        private TextField inputNo;


        @FXML
        private TableView<tabClass> tableView;

        //declaring global variables
        File pathConverted;
        File pathEdited;
        String strPathCon;
        String strPathEdt;
        FileChooser fc = new FileChooser();


        ArrayList<String> convertedAL;
        ArrayList<String> editedAL;
        int maxSize;
        int minSize;
        int conAlSize;
        int edtAlSize;
        ObservableList<tabClass> curList;

        boolean isChecked = false;
        int falseRow = 0;
        @FXML
        void acbtnCheck(ActionEvent event) {
                if(isChecked){
                        falseRow++;
                }

                ObservableList<tabClass> curL = tableView.getItems();
                int tracker = 0;
                for (int i = falseRow; i <curL.size() ; i++) {
                        //tableView.refresh();
                        tabClass item1 = curL.get(i);
                        String colDataCon = item1.getTabConverted();
                        String colDataEdtd = item1.getTabEdited();
                        //System.out.println(colDataCon+" "+colDataEdtd);
                        if(colDataCon.equals(colDataEdtd)){
                                tracker=i;
                                continue;
                        }
                        else {
                                falseRow = i;
                                isChecked =true;
                                System.out.println(falseRow);
                                System.out.println(colDataCon+" "+colDataEdtd);
                                break;
                        }


//                        if(colDataCon != colDataEdtd){
//                                falseRow = i;
//                                isChecked =true;
//                                System.out.println(falseRow);
//                                System.out.println(colDataCon+" "+colDataEdtd);
//                                break;
//
//                        }
                }
                if (tracker == curL.size()-1){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Good to Go!");
                        alert.setHeaderText("No Issues Found!");
                        alert.setContentText("The document appears to be suitable for filing.");
                        alert.showAndWait();
                }
                else {
                        tableView.getFocusModel().focus(falseRow);
                        tableView.getSelectionModel().select(falseRow);
                        tableView.getSelectionModel().focus(falseRow);
                        tableView.scrollTo(falseRow);
                }





        }

        @FXML
        void acbtnConverted(ActionEvent event) {

                //setting title for the chooser
                fc.setTitle("Choose .htm");

                //setting initial directory
                fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Downloads"));

                //specifying the file types
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTM Files", "*.htm"));

                //null case handling
                File file = fc.showOpenDialog(null); //shows a new opener

                if (file != null){
                        strPathCon = file.getAbsolutePath();
                        pathConverted = new File(strPathCon);

                       // taConverted.appendText(file.getAbsolutePath());  //returns the file path as a string to the display
                       // upConverted.setText(String.valueOf(pathConverted));
                        upConverted.setText(strPathCon);
                }else {
                        System.out.println("Invalid file");
                }

        }

        @FXML
        void acbtnEdited(ActionEvent event) {

                //setting title for the chooser
                fc.setTitle("Choose .htm");

                //setting initial directory
                fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\Downloads"));

                //specifying the file types
                fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("HTM Files", "*.htm"));

                //null case handling
                File file = fc.showOpenDialog(null); //shows a new opener

                if (file != null){
                        strPathEdt = file.getAbsolutePath();
                        pathEdited = new File(strPathEdt);
                        upEdited.setText(strPathEdt);
                }else {
                        System.out.println("Invalid file");
                }

        }

        @FXML
        void acbtnSubmit(ActionEvent event) {

                ObservableList<tabClass> curList = tableView.getItems();
                int curData = Integer.parseInt(inputNo.getText());

                for (tabClass data: curList){
                        if (data.getTableNo()==curData){
                                data.setTabEdited(inputEdt.getText());
                                tableView.setItems(curList);
                                tableView.refresh();
                        }
                }

        }

        @FXML
        private ProgressBar proBar;

//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new
//                KeyValue(proBar.progressProperty(),1)));


        //radio buttons
        @FXML
        private ToggleGroup bToggle;
        @FXML
        private RadioButton radFinal;

        @FXML
        private RadioButton radPPS;
        @FXML
        void acbtnViewinTable(ActionEvent event) throws IOException {

                if (radFinal.isSelected()){
                        System.out.println("I am Final");
                }
                else {
                        System.out.println("I am PPS");
                }


               // proBar.setProgress(0.1);

                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new
                        KeyValue(proBar.progressProperty(),1)));

                proBar.setVisible(true);
                timeline.play();

                //converting to string using JSOUP

                Document docConverted = Jsoup.parse(pathConverted, "UTF-8", "");
                Document docEdited = Jsoup.parse(pathEdited, "UTF-8", "");
                String textConverted = Jsoup.parse(pathConverted,"ISO-8859-1").select("body").text();
                String textEdited = Jsoup.parse(pathEdited,"ISO-8859-1").select("body").text();


              //  proBar.setProgress(0.3);

                //to ArrayList
                ArrayList<String> con= new ArrayList<>(Arrays.asList(textConverted.split("\\s* \\s*")));
                ArrayList<String> ed= new ArrayList<>(Arrays.asList(textEdited.split("\\s* \\s*")));

                convertedAL = con;
                editedAL = ed;

                if(radPPS.isSelected()){
                        Collections.sort(convertedAL);
                        Collections.sort(editedAL);
                }

                conAlSize = convertedAL.size();
                edtAlSize = editedAL.size();
                maxSize = (conAlSize > edtAlSize) ? conAlSize : edtAlSize;  //might through issues
                minSize = (conAlSize < edtAlSize) ? conAlSize : edtAlSize;





                lblConvertedTotal.setText(String.valueOf(conAlSize));
                lblEditedTotal.setText(String.valueOf(edtAlSize));

                  //sort
//                Collections.sort(convertedAL);
//                Collections.sort(editedAL);

                System.out.println(convertedAL);
                System.out.println(editedAL);
                System.out.println(conAlSize);
                System.out.println(edtAlSize);

                //creating object
//                Helper help = new Helper();
//                help.tableViewer(convertedAL,editedAL);

                //connecting colum names with the tabClass

                colNo.setCellValueFactory(new PropertyValueFactory<>("TableNo"));
                colConverted.setCellValueFactory(new PropertyValueFactory<>("TabConverted"));
                colEdited.setCellValueFactory(new PropertyValueFactory<>("TabEdited"));
              //  proBar.setProgress(0.4);

                //setting items
              //  tableView.setItems(curList);  //viewer is an observable list

//                //
//                for (int i = 0; i < maxSize; i++) {
//                        tableView.setItems(viewer());
//                }

                //single col selection
                tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                tableView.getSelectionModel().setCellSelectionEnabled(true);

            //    tableView.setItems(viewer());

                                //connecting colum names with the tabClass

//                colNo.setCellValueFactory(new PropertyValueFactory<>("TableNo"));
//                colConverted.setCellValueFactory(new PropertyValueFactory<>("TabConverted"));
//                colEdited.setCellValueFactory(new PropertyValueFactory<>("TabEdited"));

                //setting items
              //  tableView.setItems(curList);  //viewer is an observable list

//                //
//                for (int i = 0; i < maxSize; i++) {
//                        tableView.setItems(viewer());
//                }

             //   proBar.setProgress(0.5);





                tableView.setItems(viewer());
                timeline.setOnFinished(e -> proBar.setVisible(false));

               // tableView.setItems(curList);

             //   proBar.setProgress(99.99);
             //   proBar.setVisible(false);

                //warning
                if (conAlSize != edtAlSize){

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning!");
                        alert.setHeaderText("Mismatch Found");
                        alert.setContentText("The size of the files is not same!");
                        alert.showAndWait();

                        //sound
//                        String soundFile = "src/main/resources/warning.mp3";
//                        Media sound = new Media(new File(soundFile).toURI().toString());
//                        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//                        mediaPlayer.play();
                }



        }


        @FXML
        void selectedCol(MouseEvent event) {

                tabClass selected = tableView.getSelectionModel().getSelectedItem();
                inputNo.setText(String.valueOf(Integer.valueOf(selected.getTableNo())));
                inputCon.setText(String.valueOf(selected.getTabConverted()));
                inputEdt.setText(String.valueOf(selected.getTabEdited()));



        }

        @FXML
        private Label lblConvertedTotal;

        @FXML
        private Label lblEditedTotal;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

                proBar.setVisible(false);
                lblConvertedTotal.setText("0000");
                lblEditedTotal.setText("0000");

//                //connecting colum names with the tabClass
//
//                colNo.setCellValueFactory(new PropertyValueFactory<>("TableNo"));
//                colConverted.setCellValueFactory(new PropertyValueFactory<>("TabConverted"));
//                colEdited.setCellValueFactory(new PropertyValueFactory<>("TabEdited"));
//
//                //setting items
//                tableView.setItems(curList);  //viewer is an observable list
//
////                //
////                for (int i = 0; i < maxSize; i++) {
////                        tableView.setItems(viewer());
////                }
//
//                //single col selection
//                tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
//                tableView.getSelectionModel().setCellSelectionEnabled(true);
//
//                tableView.setItems(viewer());

        }

        ObservableList viewer(){

                curList = FXCollections.observableArrayList();

                maxSize = (conAlSize > edtAlSize) ? conAlSize : edtAlSize;  //might through issues

                int j =0 ;
                for (int i = 1; i <minSize ; i++) {

                     //   curList.add(new tabClass(i,convertedAL.get(j), );
                       // curList.add(new tabClass(i,convertedAL.get(j),editedAL.get(j)));
                        curList.add(new tabClass(i,convertedAL.get(j),editedAL.get(j)));
                        j++;

                }
                tableView.setItems(curList);
               // proBar.setVisible(false);

                return curList;



        }

        @FXML
        private Button btnDualTab;

        @FXML
        void acbtnDualTab(ActionEvent event) {

                try {
                        // create a new stage
                        Stage newStage = new Stage();
                        newStage.setTitle("New Window");
                        // load the FXML file for the new window
                        Parent root = FXMLLoader.load(getClass().getResource("dualTab.fxml"));
                        newStage.setScene(new Scene(root));
                        newStage.show();
                } catch (IOException e) {
                        e.printStackTrace();
                }

        }



}
