package com.company;
import com.company.bookBorrowing.*;
import java.util.Scanner;
import java.util.ArrayList;

public class bookBorrowing {

    private static String getString(String prompt) {
        try{
            System.out.println(prompt);
            Scanner input = new Scanner(System.in);
            return input.nextLine();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    private static int getInteger(String prompt) {
        try{
            System.out.println(prompt);
            Scanner input = new Scanner(System.in);
            return input.nextInt();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }


}
