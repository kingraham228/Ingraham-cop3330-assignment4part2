# Help instructions
## Here is a list of how to access each required function :
1. The application shall manage a single list of items
    - The list shall have the capacity to store at least 100 unique items
    >*This feautre is handled by the ToDoList class where items are stored in an ArrayList which has a capacity well above 100 items.*

2. An item shall have a description
    - A description shall be between 1 and 256 characters in length
    
    >*Descriptions are a required element of items that are added to lists or edited. The InputValidataor function checkDescription() ensures that the character length requirements are followed.*
    
3. An item shall have a due date
    - A due date shall be a valid date within the Gregorian Calendar
    - A due date shall be displayed to users in the format: YYYY-MM-DD
    
    >*Due dates are another required element when items are added or editied. The InputValidator function checkDueDate() ensures that the date is valid within the Gregorian Calendar and in the specified foramt.*

4. A user shall be able to add a new item to the list

   >*This function is accessed by clicking the "Add Item" button.*
  
5. A user shall be able to remove an item from the list

    >*This function is accessed by clicking the "Delete Item" button.*

6. A user shall be able to clear the list of all items

    >*This function is accessed by clicking the "Clear List" button.*
    
7. A user shall be able to edit the description of an item within the list

    >*This function is accessed by clicking the "Edit Item" button.*
    
8. A user shall be able to edit the due date of an item within the list

    >*This function is accessed by clicking the "Edit Item" button.*
    
9. A user shall be able to mark an item in the list as either complete or incomplete

    >This function is accessed by clicking the "Edit Item" button. Additionally, the user can also use the "Complete Item" button to mark an item complete in a faster, more easily accessed way.*
    
10. A user shall be able to display all of the existing items in the list

    >*This function is accessed by clicking the "View" menu and selecting both the "Display Uncompleted Items" and "Display Completed Items" options or by leaving both menu options unchecked which is the default state.*
    
11. A user shall be able to display only the incomplete items in the list

    >*This function is accessed by clicking the "View" menu and selecting the "Display Uncompleted Items" option.*
    
12. A user shall be able to display only the completed items in the list

   >*This function is accessed by clicking the "View" menu and selecting the "Display Completed Items" option.*

13. A user shall be able to save the list (and all of its items) to external storage

    >*This function is accessed by clicking the "File" menu and selecting the "Save List" option. Note that the program is designed to save a .txt file. If you change the name of the file, please remember to add the .txt extension to avoid errors in opening the file.*
    
14. A user shall be able to load a list (and all of its items) from external storage

    >*This function is accessed by clicking the "File" menu and selecting the "Open List" option. Note that the program is designed to open .txt files. Attempting to open other file formats will result in an error.*

15. The developer shall provide a help screen with directions on how to use the application.
    - The help screen shall describe how to execute each behavioral requirement provided by the application (e.g. add an item, remove an item, edit an item, etc)
    - This help screen shall be provided as either a dedicated window within the application, or a markdown file called `readme.md` on your GitHub repository for the project.)
    
     >*This function is accessed by clicking the "Help" menu and selecting the "Instructions" option. That option provides a link to this page.*
    
16. The help screen shall include a dedication to "Rey"

     >*This function is accessed by clicking the "Help" menu and selecting the "Dedication" option.*
