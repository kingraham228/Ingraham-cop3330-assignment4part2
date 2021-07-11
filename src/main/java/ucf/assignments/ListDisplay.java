package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */

import javafx.scene.control.CheckMenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//This class manages the appearance of the TodoList items.
public class ListDisplay {
    private ArrayList<Item> displayArray = new ArrayList<>();

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
        displayArray = userList.getItems();
        return displayArray;
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

    public ArrayList<Item> sortDisplayArray(ToDoList ul) {
        ArrayList<Item> sortItems = ul.getItems();
        //define a comparator for dueDate
        Comparator byDueDate = new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.dueDate.compareTo(o2.dueDate);
            }
        };
        //sort displayArray with Collections.sort on dueDate
        Collections.sort(sortItems,byDueDate);
        return sortItems;
    }

}
