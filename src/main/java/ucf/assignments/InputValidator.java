package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */

import javax.print.DocFlavor;
import java.util.Arrays;

public class InputValidator {

    //This method checks to make sure that the due date for the item is entered in YYYY-MM-DD format.
    public boolean checkDate(String date) {
        char[] dateArray = date.toCharArray();
        if (dateArray.length != 10) {
            return false;
        } else {
            //Validate hyphens
            if (dateArray[4] == '-' && dateArray[7] == '-') {
                //Validate the year. This will accept any year, even far in the past or future.
                StringBuilder strYear= new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    if (!Character.isDigit(dateArray[i])) {
                        return false;
                    }
                    strYear.append(dateArray[i]);
                }
                int year = Integer.parseInt(strYear.toString());
                //Validate the month
                StringBuilder strMonth = new StringBuilder();
                for (int i = 5; i < 7; i++) {
                    if (!Character.isDigit(dateArray[i])) {
                        return false;
                    }
                    strMonth.append(dateArray[i]);
                }
                int month = Integer.parseInt(String.valueOf(strMonth));
                boolean valid = checkMonth(month);
                if (valid) {
                    //Validate the day
                    StringBuilder strDay = new StringBuilder();
                    for (int i = 8; i < 10; i++) {
                        if (!Character.isDigit(dateArray[i])) {
                            return false;
                        }
                        strDay.append(dateArray[i]);
                    }
                    int day = Integer.parseInt(String.valueOf(strDay));
                    valid = checkDay(month, day, year);
                    return valid;

                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    //This method checks that the day is valid within the Gregorian calendar.
    public boolean checkDay(int month, int day, int year) {
        if (day > 0 && day <= 31) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    return true;
                case 2:
                    boolean leapyear = checkLeapYear(year);
                    if (leapyear) {
                        return day <= 29;
                    } else {
                        return day <= 28;
                    }
                case 4:
                case 6:
                case 9:
                case 11:
                    return day <= 30;
            }
        }
        return false;
    }

    //This method checks to make sure that the description is between 1 and 256 characters.
    public boolean checkDescription(String description) {
        char[] desArray = description.toCharArray();
        return desArray.length > 1 && desArray.length < 256;
    }

    //This method checks that the month falls between 1 and 12.
    public boolean checkMonth(int month) {
        return month > 0 && month <= 12;
    }

    //This method checks if the year is a leap year
    public boolean checkLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        } else {
            return year % 4 == 0 && year % 100 == 0 && year % 400 == 0;
        }
    }

}
