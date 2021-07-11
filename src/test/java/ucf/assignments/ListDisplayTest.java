package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckMenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListDisplayTest {


    @Test
    void sortDisplayArray_first_element() {
        //create a new ListDisplay
        //set displayArray equal to an unsorted list of items
        //call sortDisplayArray()
        //set expected string to the expected first sorted element of the displayArray
        //set the actual string to the value of displayArray[0]
        //assert that expected matches actual
    }

    @Test
    void sortDisplayArray_last_element() {
        //create a new ListDisplay
        //set displayArray equal to an unsorted list of items
        //call sortDisplayArray()
        //set expected string to the expected last sorted element of the displayArray
        //set the actual string to the value of displayArray[arraysize-1]
        //assert that expected matches actual
    }

    @Test
    @DisplayName("Display All")
    void displayItems() {
        ListDisplay listDis = new ListDisplay();
        //Create a test TodoList
        ToDoList test = new ToDoList();
        String description = "Completed Item.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);
        test.markComplete(0);
        //add a second item
        description = "Uncompleted Item.";
        dueDate = "2021-07-10";
        test.addItem(description,dueDate);

        CheckMenuItem completed = new CheckMenuItem();
        completed.setSelected(true);
        CheckMenuItem uncompleted = new CheckMenuItem();
        uncompleted.setSelected(false);

        ArrayList<Item> displayArray = listDis.displayItems(test,completed,uncompleted);

        String actual = displayArray.get(0).description;
        String expected = "Completed Item.";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Display Uncompleted")
    void displayUncompleted() {
        ListDisplay listDis = new ListDisplay();
        //Create a test TodoList
        ToDoList test = new ToDoList();
        String description = "Completed Item.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);
        test.markComplete(0);
        //add a second item
        description = "Uncompleted Item.";
        dueDate = "2021-07-10";
        test.addItem(description,dueDate);

        ArrayList<Item> displayArray = listDis.displayUncompleted(test);

        String actual = displayArray.get(0).description;
        String expected = "Uncompleted Item.";

        assertEquals(expected,actual);

        int act2 = displayArray.size();
        //List should contain only one item
        int expect2 = 1;

        assertEquals(expect2,act2);
    }

    @Test
    @DisplayName("Display Completed")
    void displayCompleted() {
        ListDisplay listDis = new ListDisplay();
        //Create a test TodoList
        ToDoList test = new ToDoList();
        String description = "Completed Item.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);
        test.markComplete(0);
        //add a second item
        description = "Uncompleted Item.";
        dueDate = "2021-07-10";
        test.addItem(description,dueDate);

        ArrayList<Item> displayArray = listDis.displayCompleted(test);

        String actual = displayArray.get(0).description;
        String expected = "Completed Item.";

        assertEquals(expected,actual);

        int act2 = displayArray.size();
        //List should contain only one item
        int expect2 = 1;

        assertEquals(expect2,act2);
    }
}