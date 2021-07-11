package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */

import javafx.scene.control.CheckMenuItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListDisplayTest {


    @Test
    @DisplayName("Test Sort First element")
    void sortDisplayArray_first_element() {
        ListDisplay listDis = new ListDisplay();
        //Create a test TodoList
        ToDoList test = new ToDoList();
        String description = "Item One.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);
        test.markComplete(0);
        //add a second item
        description = "Item Two.";
        dueDate = "2021-07-10";
        test.addItem(description,dueDate);
        //add a third item
        description = "Item Three.";
        dueDate = "2022-08-20";
        test.addItem(description,dueDate);
        //add a fourth item
        description = "Item four.";
        dueDate = "1999-08-20";
        test.addItem(description,dueDate);

        //sort the list
        ArrayList<Item> sorted = listDis.sortDisplayArray(test);

        //Test first element
        String actual = sorted.get(0).dueDate;
        String expected = "1999-08-20";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Sort Test Last element")
    void sortDisplayArray_last_element() {
        ListDisplay listDis = new ListDisplay();
        //Create a test TodoList
        ToDoList test = new ToDoList();
        String description = "Item One.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);
        test.markComplete(0);
        //add a second item
        description = "Item Two.";
        dueDate = "2021-07-10";
        test.addItem(description,dueDate);
        //add a third item
        description = "Item Three.";
        dueDate = "2022-08-20";
        test.addItem(description,dueDate);
        //add a fourth item
        description = "Item four.";
        dueDate = "1999-08-20";
        test.addItem(description,dueDate);

        //sort the list
        ArrayList<Item> sorted = listDis.sortDisplayArray(test);

        //Test last element
        String actual = sorted.get((sorted.size()-1)).dueDate;
        String expected = "2022-08-20";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Sort Test Mid element1")
    void sortDisplayArray_mid_element1() {
        ListDisplay listDis = new ListDisplay();
        //Create a test TodoList
        ToDoList test = new ToDoList();
        String description = "Item One.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);
        test.markComplete(0);
        //add a second item
        description = "Item Two.";
        dueDate = "2021-07-10";
        test.addItem(description,dueDate);
        //add a third item
        description = "Item Three.";
        dueDate = "2022-08-20";
        test.addItem(description,dueDate);
        //add a fourth item
        description = "Item four.";
        dueDate = "1999-08-20";
        test.addItem(description,dueDate);

        //sort the list
        ArrayList<Item> sorted = listDis.sortDisplayArray(test);

        //Test mid element1
        String actual = sorted.get(1).dueDate;
        String expected = "2021-07-01";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Sort Test Mid element2")
    void sortDisplayArray_mid_element2() {
        ListDisplay listDis = new ListDisplay();
        //Create a test TodoList
        ToDoList test = new ToDoList();
        String description = "Item One.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);
        test.markComplete(0);
        //add a second item
        description = "Item Two.";
        dueDate = "2021-07-10";
        test.addItem(description,dueDate);
        //add a third item
        description = "Item Three.";
        dueDate = "2022-08-20";
        test.addItem(description,dueDate);
        //add a fourth item
        description = "Item four.";
        dueDate = "1999-08-20";
        test.addItem(description,dueDate);

        //sort the list
        ArrayList<Item> sorted = listDis.sortDisplayArray(test);

        //Test mid element2
        String actual = sorted.get(2).dueDate;
        String expected = "2021-07-10";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Display All")
    //Test displaying all items
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

        //manually set menu selections
        CheckMenuItem completed = new CheckMenuItem();
        completed.setSelected(true);
        CheckMenuItem uncompleted = new CheckMenuItem();
        uncompleted.setSelected(false);

        ArrayList<Item> displayArray = listDis.displayItems(test,completed,uncompleted);

        //test first item
        String actual = displayArray.get(0).description;
        String expected = "Completed Item.";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Display Uncompleted")
    //Test displaying uncompleted items
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

        //Test first item
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
    //Test display completed items
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
        //Test first item
        String actual = displayArray.get(0).description;
        String expected = "Completed Item.";

        assertEquals(expected,actual);

        int act2 = displayArray.size();
        //List should contain only one item
        int expect2 = 1;

        assertEquals(expect2,act2);
    }

}