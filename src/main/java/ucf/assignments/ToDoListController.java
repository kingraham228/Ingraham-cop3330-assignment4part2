package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Pair;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

//This class manages user interaction with the GUI.
public class ToDoListController {
    //This attribute is the view selection menu item for uncompleted items
    @FXML
    public CheckMenuItem mCheckUncompleted;
    //This attribute is the view selection menu item for uncompleted items
    @FXML
    public CheckMenuItem mCheckCompleted;
    //This attribute is the ListView in the GUI displaying the To Do List items
    @FXML
    public ListView<String> listView;


    //ToDoList userList manages items in the ToDoList
    ToDoList userList = new ToDoList();

    //FileManger fileM manages saving and loading To external storage
    FileManager fileM = new FileManager();

    //ListDisplay manages the visible items
    ListDisplay listDis = new ListDisplay();

    //InputValidator checks item descriptions and due dates for proper formatting and validity.
    InputValidator inputV = new InputValidator();

    //This method formats the ToDoList item data for the GUI ListView
    public void updateListView(ArrayList<Item> displayArray) {
        listView.getItems().clear();
        for (Item value : displayArray) {
            String description = value.description;
            String dueDate = value.dueDate;
            String status;

            //Translate boolean completeStatus into more readable strings uncompleted or completed
            if (!value.completeStatus) {
                status = "uncompleted";
            } else {
                status = "completed";
            }

            String item = String.format("Description: %s  Due Date: %s  Status: %s", description, dueDate, status);
            listView.getItems().add(item);
        }
    }

    //This method initiates loading in a ToDoList from a file when the user selects the "Open" menu option
    @FXML
    public void mLoadListClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open List");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Path filePath = Path.of(file.getPath());
            ArrayList<Item> fileItems = fileM.openOneList(filePath);
            userList.addArrayList(fileItems);
            //Send error alert if the parser did not add any items.
            if (fileItems.size() < 1) {
                Alert fileLoad = new Alert(Alert.AlertType.ERROR);
                fileLoad.setContentText("To open a file you must choose a .txt file formatted so that there is one item per line. Each line should include the labels Description:, Due Date:, and Status: to open into the list.");
                fileLoad.show();
            }
        }
        ArrayList<Item> view = listDis.displayItems(userList, mCheckCompleted, mCheckUncompleted);
        updateListView(view);
    }

    //This method launches a filechooser to get the filename to save the todoList when the menu item is selected.
    @FXML
    public void mSaveListClicked() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save List");
        fileChooser.setInitialFileName("mytodolist.txt");
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            String filePath = file.getPath();
            fileM.saveOneList(userList.getItems(), filePath);
        }

    }


    //This method updates the items list when the user clicks the "Display Uncompleted" menu item
    @FXML
    public void mDisplayUncompletedClicked() {
        ToDoList preserve = userList;
        ArrayList<Item> view = listDis.displayItems(preserve, mCheckCompleted, mCheckUncompleted);
        updateListView(view);
    }

    //This method updates the items list when the user clicks the "Display Completed" menu item
    @FXML
    public void mDisplayCompletedClicked() {
        ToDoList preserve = userList;
        ArrayList<Item> view = listDis.displayItems(preserve, mCheckCompleted, mCheckUncompleted);
        updateListView(view);
    }

    //This method updates the item list when the user clicks the "Sort Items by Due Date" menu option
    @FXML
    public void mSortDueDateClicked() {
        ArrayList<Item> view = listDis.sortDisplayArray(userList);
        updateListView(view);
    }

    //This method adds an item to the ToDoList when the user clicks the Add Item button
    @FXML
    public void bAddItemClicked() {
        //open a text input dialogue to get user item input
        //code adapted from https://stackoverflow.com/questions/31556373/javafx-dialog-with-2-input-fields
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add Item");

        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 150, 10, 10));

        //ask the user to input the new description and due date
        TextField description = new TextField();
        TextField dueDate = new TextField();
        dueDate.setPromptText("YYYY-MM-DD");

        gridPane.add(new Label("Item Description:"), 0, 0);
        gridPane.add(description, 1, 0);
        gridPane.add(new Label("Due Date:"), 0, 1);
        gridPane.add(dueDate, 1, 1);

        dialog.getDialogPane().setContent(gridPane);

        Platform.runLater(description::requestFocus);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(description.getText(), dueDate.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        String strDueDate;
        String strDescription;

        if (result.isPresent()) {
            strDescription = description.getText();
            strDueDate = dueDate.getText();

            //call checkDescription() to make sure that the description is between 1 and  256 characters
            boolean valid = inputV.checkDescription(strDescription);
            //send an alert if there is a description violation
            if (!valid) {
                Alert des = new Alert(Alert.AlertType.ERROR);
                des.setContentText("Item descriptions must be between 1 and 256 characters.");
                des.show();
            } else {
                //call checkDate() to make sure that user input for due date is valid
                valid = inputV.checkDate(strDueDate);
                if (valid) {
                    userList.addItem(strDescription, strDueDate);

                    ArrayList<Item> view = listDis.displayItems(userList, mCheckCompleted, mCheckUncompleted);
                    updateListView(view);
                } else {
                    //send an alert if there is a due date violation
                    Alert date = new Alert(Alert.AlertType.ERROR);
                    date.setContentText("Due dates must be valid within the Gregorian Calendar and in the format YYYY-MM-DD (Year-Month-Day).");
                    date.show();
                }
            }
        }
    }

    //This method opens a dialogue box for the user to edit all attributes of an item.
    @FXML
    public void bEditItemClicked() {
        //Add size check to avoid errors if the user clicks the edit button before there are any items in the list.
        if(userList.getItems().size()>0){
        int index = listView.getSelectionModel().getSelectedIndex();

            //open a text input dialogue
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Edit Item");

            ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);
            gridPane.setPadding(new Insets(20, 150, 10, 10));

            //ask the user to input the new description and due date
            TextField description = new TextField();
            TextField dueDate = new TextField();
            //Prefill with existing description and due date
            description.setPromptText(userList.getItems().get(index).description);
            dueDate.setPromptText(userList.getItems().get(index).dueDate);
            CheckBox status = new CheckBox();
            //prefill checkbox according to existing status
            status.setSelected(userList.getItems().get(index).completeStatus);

            gridPane.add(new Label("Item Description:"), 0, 0);
            gridPane.add(description, 1, 0);
            gridPane.add(new Label("Due Date:"), 0, 1);
            gridPane.add(dueDate, 1, 1);
            gridPane.add(new Label("Complete: "), 0, 2);
            gridPane.add(status, 1, 2);

            dialog.getDialogPane().setContent(gridPane);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(description.getText(), dueDate.getText());
                }
                return null;
            });

            Optional<Pair<String, String>> result = dialog.showAndWait();

            String strDueDate;
            String strDescription;
            boolean complete;

            if (result.isPresent()) {
                strDescription = description.getText();
                strDueDate = dueDate.getText();
                complete = status.selectedProperty().getValue();

                //call checkDescription() to make sure that the description is between 1 and  256 characters
                boolean valid = inputV.checkDescription(strDescription);
                if (!valid) {
                    //Send an alert if there is a description violation
                    Alert des = new Alert(Alert.AlertType.ERROR);
                    des.setContentText("Item descriptions must be between 1 and 256 characters.");
                    des.show();
                } else {
                    valid = inputV.checkDate(strDueDate);
                    if (valid) {
                        userList.editItem(strDescription, strDueDate, index, complete);

                        ArrayList<Item> view = listDis.displayItems(userList, mCheckCompleted, mCheckUncompleted);
                        updateListView(view);
                    } else {
                        //Send an alert if there is a due date violation
                        Alert date = new Alert(Alert.AlertType.ERROR);
                        date.setContentText("Due dates must be valid within the Gregorian Calendar and in the format YYYY-MM-DD (Year-Month-Day).");
                        date.show();
                    }
                }
            }
        }else{
            //Send an alert if the user tries to edit before the list has items
            Alert emptyList = new Alert(Alert.AlertType.ERROR);
            emptyList.setContentText("You must have an item in the list before you can edit an item.\n" +
                    "Click the \"Add Item\" button to add an item.");
            emptyList.show();
        }
    }

    //This method removes an item from the ToDoList when the "Delete Item" button is pressed.
    @FXML
    public void bDeleteItemClicked() {
        //Check size to avoid errors
        if(userList.getItems().size()>0) {
            int index = listView.getSelectionModel().getSelectedIndex();
            userList.removeItem(index);
            ArrayList<Item> view = listDis.displayItems(userList, mCheckCompleted, mCheckUncompleted);
            updateListView(view);
        } else{
            //Send an alert if the user tries to delete an item before the list has items
            Alert emptyList = new Alert(Alert.AlertType.ERROR);
            emptyList.setContentText("You must have an item in the list before you can delete an item.\n" +
                    "Click the \"Add Item\" button to add an item.");
            emptyList.show();
        }
    }

    //This method marks a ToDoList item as complete when the user clicks the "Complete Item" button
    @FXML
    public void bCompleteItemClicked() {
        //Check size to avoid errors
        if(userList.getItems().size()>0) {
            int index = listView.getSelectionModel().getSelectedIndex();
            userList.markComplete(index);
            ArrayList<Item> view = listDis.displayItems(userList, mCheckCompleted, mCheckUncompleted);
            updateListView(view);
        } else{
            //Send an alert if the user tries to complete an item before the list has items
            Alert emptyList = new Alert(Alert.AlertType.ERROR);
            emptyList.setContentText("You must have an item in the list before you can complete an item.\n" +
                    "Click the \"Add Item\" button to add an item.");
            emptyList.show();
        }
    }

    //This method clears all items in the list when the "Clear List" button is clicked.
    @FXML
    public void bClearList() {
        userList.clearItems();
        ArrayList<Item> view = listDis.displayItems(userList, mCheckCompleted, mCheckUncompleted);
        updateListView(view);
    }

    //This method displays the dedication when the dedication menu item is clicked.
    @FXML
    public void mDedication() {
        Alert dedication = new Alert(Alert.AlertType.NONE, "Dedicated to Rey.", ButtonType.OK);
        dedication.setTitle("Dedication");
        dedication.show();
    }

    //This method displays the link to the README file if the Instructions menu item is clicked.
    @FXML
    public void mInstructions() {
        Alert dedication = new Alert(Alert.AlertType.NONE, "Please see full instructions on how to use this application at:\n" +
                "https://github.com/kingraham228/Ingraham-cop3330-assignment4part2#readme", ButtonType.OK);
        dedication.setTitle("Instructions");
        dedication.show();
    }
}
