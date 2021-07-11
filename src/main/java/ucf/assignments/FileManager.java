package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
//This class manages file functions such as saving, opening, and parsing files.
public class FileManager {

    InputValidator iv = new InputValidator();

    //This method saves the ToDoList to a text file.
    public void saveOneList(ArrayList<Item> listItems, String filePath) {
        try (Formatter output = new Formatter(filePath)) {
            output.format("To Do: %n");
            output.format("-------------------------%n");
            //print each item in the list to the file
            for (Item listItem : listItems) {
                String description = listItem.description;
                String dueDate = listItem.dueDate;
                String status;

                //convert status booleans into readable strings
                if (!listItem.completeStatus) {
                    status = "uncompleted";
                } else {
                    status = "completed";
                }
                String item = String.format("Description: %s  Due Date: %s  Status: %s%n", description, dueDate, status);
                output.format(item);
            }
        } catch (SecurityException | FileNotFoundException | FormatterClosedException e) {
            e.printStackTrace();
        }
    }

    //This method opens a list from a file and adds it to the current ToDoList.
    public ArrayList<Item> openOneList(Path filename) {
        ArrayList<String> fileData = new ArrayList<>();
        try (Scanner input = new Scanner(filename)) {
            while (input.hasNext()) {
                String nextString = input.nextLine();
                fileData.add(nextString);
            }
        } catch (IOException | NoSuchElementException | IllegalStateException e) {
            e.printStackTrace();
        }
        //return item array data received from sending the string arraylist to teh parseFileData method
        return parseFileData(fileData);
    }

    //This method parses data from a .txt file to create ToDoList items.
    public ArrayList<Item> parseFileData(ArrayList<String> fileData) {
        ArrayList<Item> fileItems = new ArrayList<>();
        //For each item in the string array, split the string into the item components
        for (String analyze : fileData) {
            String[] pieces = analyze.split("Description: ");
            if (pieces.length > 1) {
                String description = pieces[1];
                pieces = description.split("Due Date: ");
                description = pieces[0];
                String dueDate = pieces[1];
                pieces = dueDate.split("Status: ");
                dueDate = pieces[0];

                // Adjust for spacing issue with dueDate by removing extra spaces
                if (dueDate.length() > 10) {
                    char[] dueDateChars = dueDate.toCharArray();
                    StringBuilder trimDate = new StringBuilder();
                    for (char dueDateChar : dueDateChars) {
                        if (dueDateChar != ' ') {
                            trimDate.append(dueDateChar);
                        }
                    }
                    dueDate = String.valueOf(trimDate);
                }
                String srStatus = pieces[1];
                //Convert status strings to boolean values
                boolean status = srStatus.equalsIgnoreCase("completed");
                //Validate description and dueDate requirements
                boolean desValid = iv.checkDescription(description);
                boolean dateValid = iv.checkDate(dueDate);
                if (desValid && dateValid) {
                    Item parseItem = new Item(description, dueDate, status);
                    fileItems.add(parseItem);
                }
            }

        }
        return fileItems;
    }


}
