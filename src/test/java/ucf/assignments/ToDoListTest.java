package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    @Test
    @DisplayName("Add Item Test")
    void addItem() {
        ToDoList test = new ToDoList();

        String description = "This is a test item.";
        String dueDate = "2021-07-01";

        test.addItem(description,dueDate);
        ArrayList<Item> setItems = test.getItems();

        String actual = setItems.get(0).description+setItems.get(0).dueDate;
        String expected = "This is a test item.2021-07-01";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Edit Item Test")
    void editItem() {
        ToDoList test = new ToDoList();
        String description = "This is a test item.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);
        int index = 0;

        test.editItem("Edited test item.", "2022-04-15", index,true);

        String actual = test.getItems().get(index).description+
                test.getItems().get(index).dueDate+
                test.getItems().get(index).completeStatus;


        String expected = "Edited test item.2022-04-15true";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Remove Item Test")
    void removeItem() {
        ToDoList test = new ToDoList();
        String description = "This is a test item.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);
        int index = 0;
        
        test.removeItem(index);
        ArrayList<Item> actual = test.getItems();
        //Removing the only item should make the list empty
        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Status change test")
    void markComplete() {
        ToDoList test = new ToDoList();
        test.addItem("test item","2021-10-21");
        int index = 0;

        test.markComplete(index);

        boolean actual = test.getItems().get(index).completeStatus;
        boolean expected = true;

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Clear List Test")
    void clearItems() {
        ToDoList test = new ToDoList();
        String description = "This is a test item.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);
        //add a second item
        description = "Test item two.";
        dueDate = "2021-07-10";
        test.addItem(description,dueDate);

        test.clearItems();
        ArrayList<Item> actual = test.getItems();
        //clearing the list should make the list empty
        assertTrue(actual.isEmpty());
    }

    @Test
    @DisplayName("Add Array Test")
    void addArrayList() {
        ToDoList test = new ToDoList();
        String description = "This is a test item.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);

        //Array to add
        ArrayList<Item> addItem = new ArrayList<>();
        Item test1 = new Item("add 1 ","2022-10-03", false);
        addItem.add(test1);

        //add arraylist to existing list
        test.addArrayList(addItem);

        String actual = test.getItems().get(1).description;
        String expected = "add 1 ";

        assertEquals(expected,actual);
    }
}