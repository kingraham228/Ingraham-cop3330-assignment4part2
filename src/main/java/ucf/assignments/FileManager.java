package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */




import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileManager {


    //This method saves the ToDoList to a text file.
    public void saveOneList(ArrayList<Item> listItems, String filePath){
        try(Formatter output = new Formatter(filePath)){
            output.format("To Do: %n");
            output.format("-------------------------%n");
            for(int i=0; i< listItems.size(); i++){
                String description = listItems.get(i).description;
                String dueDate = listItems.get(i).dueDate;
                String status = "";

                if(listItems.get(i).completeStatus ==false){
                    status = "uncompleted";
                }else{
                    status = "completed";
                }
                String item = String.format("Description: %s   Due Date: %s   Status: %s%n",description,dueDate,status);
                output.format(item);
            }
        }catch(SecurityException | FileNotFoundException | FormatterClosedException e){
            e.printStackTrace();
        }
    }


    public ArrayList<Item> openOneList(Path filename){
        ArrayList<String> fileData = new ArrayList<>();
        try(Scanner input = new Scanner(filename)){
            while (input.hasNext()) {
                String nextString = input.nextLine();
                fileData.add(nextString);
            }
       }catch (IOException | NoSuchElementException | IllegalStateException e){
           e.printStackTrace();
       }
        ArrayList<Item> fileItems = parseFileData(fileData);

        return fileItems;
    }

    public ArrayList<Item> parseFileData(ArrayList<String> fileData){
       ArrayList<Item> fileItems = new ArrayList<>();
        for(int i=0; i<fileData.size(); i++){
            String analyze = fileData.get(i);
            String [] pieces = analyze.split("Description: ");
            if(pieces.length>1){
                String description = pieces[1];
                pieces = description.split("Due Date: ");
                description = pieces[0];
                String dueDate = pieces[1];
                pieces = dueDate.split("Status: ");
                dueDate = pieces[0];
                String srStatus = pieces[1];
                boolean status = false;
                if(srStatus.equalsIgnoreCase("true")){
                    status = true;
                }
                Item parseItem = new Item(description,dueDate,status);
                fileItems.add(parseItem);
            }

        }
        return fileItems;
    }


}
