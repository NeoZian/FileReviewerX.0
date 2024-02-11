package com.example.reviewerx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class tabClass {

    private SimpleIntegerProperty tableNo;
    private SimpleStringProperty tabConverted;
    private SimpleStringProperty tabEdited;

//    public tabClass(int tableNo, String tabConverted, String tabEdited) {
//        this.tableNo = new SimpleIntegerProperty(tableNo);
//        this.tabConverted = new SimpleStringProperty(tabConverted);
//        this.tabEdited = new SimpleStringProperty(tabEdited);
//    }

    ///


    public tabClass(int tableNo, String tabConverted, String tabEdited) {
        this.tableNo = new SimpleIntegerProperty(tableNo);
        this.tabConverted = new SimpleStringProperty(tabConverted);
        this.tabEdited = new SimpleStringProperty(tabEdited);
    }

    public int getTableNo() {
        return tableNo.get();
    }

    public void setTableNo(int tableNo) {
        this.tableNo = new SimpleIntegerProperty(tableNo);
    }

    public String getTabConverted() {
        return tabConverted.get();
    }

    public void setTabConverted(String tabConverted) {
        this.tabConverted =new SimpleStringProperty(tabConverted);
    }

    public String getTabEdited() {
        return tabEdited.get();
    }

    public void setTabEdited(String tabEdited) {
        this.tabEdited = new SimpleStringProperty(tabEdited);
    }

}
