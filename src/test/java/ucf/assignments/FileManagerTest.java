package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    @Test
    @DisplayName("Open List Test")
    void openOneList() {
        FileManager fm = new FileManager();
        String filepath = "mytodolist.txt";
        ArrayList<Item> test = fm.openOneList(Path.of(filepath));
        String actual = test.get(0).description;
        String expected = "This is a test item.   ";
        assertEquals(expected,actual);
    }


    @Test
    @DisplayName("Test Save List")
    void saveOneList() {
        FileManager fm = new FileManager();
        //Create testing TodoList
        ToDoList test = new ToDoList();
        String description = "This is a test item.";
        String dueDate = "2021-07-01";
        test.addItem(description,dueDate);
        //add a second item
        description = "Test item two.";
        dueDate = "2021-07-10";
        test.addItem(description,dueDate);

        String filepath = "mytodolist.txt";

        fm.saveOneList(test.getItems(),filepath);
        //visually check the txt file output
    }

    @Test
    @DisplayName("Parsing Test One")
    void parseFileData() {
        FileManager fm = new FileManager();
        ArrayList<String> testFileData = new ArrayList<>();
        testFileData.add("Description: This is a test item.   Due Date: 2021-07-01   Status: uncompleted");
        testFileData.add("Description: Test item two.   Due Date: 2021-07-10   Status: uncompleted");

        ArrayList<Item> fileItems = fm.parseFileData(testFileData);

        String actual = fileItems.get(0).description;
        String expected = "This is a test item.   ";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Parsing Test Two")
    void parseFileData_two() {
        FileManager fm = new FileManager();
        ArrayList<String> testFileData = new ArrayList<>();
        testFileData.add("Description: This is a test item.   Due Date: 2021-07-01   Status: uncompleted");
        testFileData.add("Description: Test item two.   Due Date: 2021-07-10   Status: uncompleted");

        ArrayList<Item> fileItems = fm.parseFileData(testFileData);

        String actual = fileItems.get(1).description;
        String expected = "Test item two.   ";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Parsing Test Due Date")
    void parseFileData_dueDate() {
        FileManager fm = new FileManager();
        ArrayList<String> testFileData = new ArrayList<>();
        testFileData.add("Description: This is a test item.   Due Date: 2021-07-01   Status: uncompleted");
        testFileData.add("Description: Test item two.   Due Date: 2021-07-10   Status: uncompleted");

        ArrayList<Item> fileItems = fm.parseFileData(testFileData);

        String actual = fileItems.get(1).dueDate;
        String expected = "2021-07-10";

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Parsing Test Status")
    void parseFileData_Status() {
        FileManager fm = new FileManager();
        ArrayList<String> testFileData = new ArrayList<>();
        testFileData.add("Description: This is a test item.   Due Date: 2021-07-01   Status: uncompleted");
        testFileData.add("Description: Test item two.   Due Date: 2021-07-10   Status: uncompleted");

        ArrayList<Item> fileItems = fm.parseFileData(testFileData);

        boolean actual = fileItems.get(1).completeStatus;
        boolean expected = false;

        assertEquals(expected,actual);
    }
}