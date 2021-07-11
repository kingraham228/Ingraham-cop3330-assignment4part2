package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */

//This class represents each item object in the To Do list
public class Item {
    public String description;
    public String dueDate;
    public boolean completeStatus;

    //This constructor creates an item with default stats as false (incomplete)
    public Item(String description, String dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        completeStatus = false;
    }

    //This constructor allows for setting all states when editing an item.
    public Item(String description, String dueDate, boolean completeStatus) {
        this.description = description;
        this.dueDate = dueDate;
        this.completeStatus = completeStatus;
    }
}
