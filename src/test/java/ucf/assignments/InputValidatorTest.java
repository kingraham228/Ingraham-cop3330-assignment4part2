package ucf.assignments;
/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Kate Ingraham
 */
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    @Test
    @DisplayName("Description alert test: Valid")
    //General valid description test
    void checkDescription_valid() {
        InputValidator iv = new InputValidator();
        String test = "Test description.";
        boolean actual = iv.checkDescription(test);
        boolean expected = true;

        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("Description alert test: too long")
    //Trying text that is too long
    void checkDescription_notvalidlong() {
        InputValidator iv = new InputValidator();
        String test = """
                As I remember, Adam, it was upon this fashion
                bequeathed me by will but poor a thousand crowns,
                and, as thou sayest, charged my brother, on his
                blessing, to breed me well: and there begins my
                sadness. My brother Jaques he keeps at school, and
                report speaks goldenly of his profit: for my part,
                he keeps me rustically at home, or, to speak more
                properly, stays me here at home unkept; for call you
                that keeping for a gentleman of my birth, that
                differs not from the stalling of an ox? His horses
                are bred better; for, besides that they are fair
                with their feeding, they are taught their manage,
                and to that end riders dearly hired: but I, his
                brother, gain nothing under him but growth; for the
                which his animals on his dunghills are as much
                bound to him as I. Besides this nothing that he so
                plentifully gives me, the something that nature gave
                me his countenance seems to take from me: he lets
                me feed with his hinds, bars me the place of a
                brother, and, as much as in him lies, mines my
                gentility with my education. This is it, Adam, that
                grieves me; and the spirit of my father, which I
                think is within me, begins to mutiny against this
                servitude: I will no longer endure it, though yet I
                know no wise remedy how to avoid it.""";
        boolean actual = iv.checkDescription(test);
        boolean expected = false;

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Description alert test: too short")
    //Trying an empty string
    void checkDescription_notvalidshort() {
        InputValidator iv = new InputValidator();
        String test = "";
        boolean actual = iv.checkDescription(test);
        boolean expected = false;

        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check month 0")
    //Trying a month that is too small
    void checkMonth_0() {
        InputValidator iv = new InputValidator();
        int month = 0;
        boolean actual = iv.checkMonth(month);
        boolean expected = false;
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Check month 6")
    //Try a valid month
    void checkMonth_06() {
        InputValidator iv = new InputValidator();
        int month = 6;
        boolean actual = iv.checkMonth(month);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check month 27")
    //Try a month that is too big
    void checkMonth_27() {
        InputValidator iv = new InputValidator();
        int month = 27;
        boolean actual = iv.checkMonth(month);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check Leap year 1996")
    //Try a valid non-special leap year
    void checkLeapYear_1996() {
        InputValidator iv = new InputValidator();
        int year = 1996;
        boolean actual = iv.checkLeapYear(year);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check Leap year 1700")
    //Try a non 400 divisible non-leap year
    void checkLeapYear_1700() {
        InputValidator iv = new InputValidator();
        int year = 1700;
        boolean actual = iv.checkLeapYear(year);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check Leap year 2000")
    //Try a 400 divisible leap year
    void checkLeapYear_2000() {
        InputValidator iv = new InputValidator();
        int year = 2000;
        boolean actual = iv.checkLeapYear(year);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check Leap year 1995")
    //Try a non-leap year
    void checkLeapYear_1995() {
        InputValidator iv = new InputValidator();
        int year = 1995;
        boolean actual = iv.checkLeapYear(year);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check February 29 not leap year")
    //Try Feb special case with 29
    void checkDay_feb29() {
        InputValidator iv = new InputValidator();
        int month = 2;
        int day = 29;
        int year = 1700;
        boolean actual = iv.checkDay(month,day,year);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check February 29 leap year")
    //Try Feb special case with a leap year
    void checkDay_feb29leap() {
        InputValidator iv = new InputValidator();
        int month = 2;
        int day = 29;
        int year = 2000;
        boolean actual = iv.checkDay(month,day,year);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check Sep. 31")
    //Try valid 31 day month
    void checkDay_sep31() {
        InputValidator iv = new InputValidator();
        int month = 9;
        int day = 31;
        int year = 2021;
        boolean actual = iv.checkDay(month,day,year);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check July 9")
    //Try another valid month
    void checkDay_79() {
        InputValidator iv = new InputValidator();
        int month = 7;
        int day = 9;
        int year = 2021;
        boolean actual = iv.checkDay(month,day,year);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check 12/31")
    //Try largest valid month/day combo
    void checkDay_1231() {
        InputValidator iv = new InputValidator();
        int month = 12;
        int day = 31;
        int year = 2021;
        boolean actual = iv.checkDay(month,day,year);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Check 0")
    //Try non-valid 0 day
    void checkDay_0() {
        InputValidator iv = new InputValidator();
        int month = 1;
        int day = 0;
        int year = 2021;
        boolean actual = iv.checkDay(month,day,year);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Valid date check")
    //Try all the Feb special cases
    void checkDate_valid() {
        InputValidator iv = new InputValidator();
        String date = "2000-02-29";
        boolean actual = iv.checkDate(date);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("Date check 2/2 non leap")
    //Try non-leap Feb special case
    void checkDate_nonleap() {
        InputValidator iv = new InputValidator();
        String date = "1800-02-29";
        boolean actual = iv.checkDate(date);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("out of format date")
    //Try non-formatted date
    void checkDate_outFormat() {
        InputValidator iv = new InputValidator();
        String date = "2/29/2000";
        boolean actual = iv.checkDate(date);
        boolean expected = false;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("valid oct")
    //Try double-digit month and day
    void checkDate_validOct() {
        InputValidator iv = new InputValidator();
        String date = "2021-10-30";
        boolean actual = iv.checkDate(date);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("valid Jan")
    //Try smallest month and day
    void checkDate_validJan() {
        InputValidator iv = new InputValidator();
        String date = "2021-01-01";
        boolean actual = iv.checkDate(date);
        boolean expected = true;
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("not valid april")
    //Try a non-valid for 30 day month
    void checkDate_nvApril() {
        InputValidator iv = new InputValidator();
        String date = "2023-04-31";
        boolean actual = iv.checkDate(date);
        boolean expected = false;
        assertEquals(expected,actual);
    }
}