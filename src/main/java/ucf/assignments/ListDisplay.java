package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */

import javafx.scene.control.CheckMenuItem;

import java.util.ArrayList;
import java.util.Comparator;

//This class manages the appearance of the TodoList items.
public class ListDisplay {

    //This method populates the displayArray according to the view options selected in teh GUI menu.
    public ArrayList<Item> displayItems(ToDoList userList,CheckMenuItem completed, CheckMenuItem uncompleted) {
        boolean viewCompleted = completed.isSelected();
        boolean viewUncompleted = uncompleted.isSelected();
        //Checks menu selection status to return the appropriate array to display.
        if(viewCompleted&&!viewUncompleted){
            return displayCompleted(userList);
        }
        if(viewUncompleted&&!viewCompleted){
            return displayUncompleted(userList);
        }
        return userList.getItems();
    }

    //This method creates a sublist of uncompleted items in the display array.
    public ArrayList<Item> displayUncompleted(ToDoList ul) {
        ArrayList<Item> viewItems = new ArrayList<>(ul.getItems());
        viewItems.removeIf(checkView -> checkView.completeStatus);
        return viewItems;

    }

    //This method creates a sublist of completed items in the display array.
    public ArrayList<Item> displayCompleted(ToDoList ul) {
        ArrayList<Item> viewItems = new ArrayList<>(ul.getItems());
        viewItems.removeIf(checkView -> !checkView.completeStatus);
        return viewItems;
    }

    //This method sorts the ToDoList according to the due date.
    public ArrayList<Item> sortDisplayArray(ToDoList ul) {
        ArrayList<Item> sortItems = ul.getItems();
        //define a comparator for dueDate
        Comparator<Item> byDueDate = Comparator.comparing(o -> o.dueDate);
        //sort displayArray by dueDate
        sortItems.sort(byDueDate);
        return sortItems;
    }

}
