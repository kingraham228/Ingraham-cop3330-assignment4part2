package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */

import java.util.ArrayList;

//This class manages the items in the To Do list.
public class ToDoList {
    private ArrayList<Item> items = new ArrayList<>();

    //This method add an item to the items ArrayList
    public void addItem(String description, String dueDate) {
        Item addItem = new Item(description, dueDate);
        items.add(addItem);
    }

    //This method removes an item from the list.
    public void removeItem(int index) {
        items.remove(index);
    }

    //This method replaces the selected item with the new edited information.
    public void editItem(String description, String dueDate, int index, boolean status) {
        Item editItem = new Item(description, dueDate, status);
        items.set(index, editItem);
    }

    //This method sets the boolean completeStatus as true for the given index in the items ArrayList
    public void markComplete(int index) {
        Item mark = items.get(index);
        mark.completeStatus = true;
        items.set(index, mark);
    }

    //This method adds an existing item list to the current list
    public void addArrayList(ArrayList<Item> add){
        items.addAll(add);
    }

    //This method removes all items from the ToDolist.
    public void clearItems() {
        items.clear();
    }

    //This method makes the items list accessible to other classes.
    public ArrayList<Item> getItems() {
        return items;
    }

}
