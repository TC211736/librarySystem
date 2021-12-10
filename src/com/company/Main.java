package com.company;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Integer.parseInt;


class EraserThread implements Runnable {

    private boolean stop;

    public EraserThread(String prompt) {
        System.out.println(prompt);
    }

    public void run() {
        stop = true;
        while (stop) {
            System.out.println("/010*");
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopMasking() {
        this.stop = false;
    }
}


class MyFilenameFilter implements FilenameFilter {
    String initials;

    public MyFilenameFilter(String initials) {
        this.initials;
    }

    public boolean accept(File dir, String name) {
        return name.startsWith(initials);
    }
}
public class Main {

    private static ArrayList libraryBookNames = new ArrayList();


    private static String register() {
        boolean samePassword = false;
        String username = getInput("Please create a username.");
        String password = null;
        while (samePassword == false) {
            password = getInput("Please create a password.");
            String password2 = getInput("Please confirm your password.");
            if (password.equals(password2)) {
                samePassword = true;
            }
        }
        return (username + ", " + password);

    }

    private static void loginFile() {
        try {
            String register = register();
            String[] parts = register.split(",");
            File login = new File(parts[0] + ".txt");
            FileWriter myWriter = new FileWriter(login);
            myWriter.write(register);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private static String[] readLoginFile() {
        Scanner myReader = new Scanner()
    }

    private static boolean logMeIn() {
        System.out.println("Please enter your username.");

    }

    public static String readPassword(String prompt) {
        EraserThread et = new EraserThread(prompt);
        Thread mask = new Thread(et);
        mask.start();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String password = "";

        try {
            password = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        et.stopMasking();
        return password;

    }

    private static String bookInformation() {
        String bookName = getInput("What is the title of the book?");
        String ISBN = getInput("What is the ISBN Number of the book?");
        String author = getInput("Who is the author of this book?");
        String genre = getInput("What is the genre of this book?");
        libraryBookNames.add(bookName + ", " + ISBN + ", " + author + ", " + genre);
        return (bookName + ", " + ISBN + ", " + author + ", " + genre);
    }

    private static void writeBookInfo() {
        try {
            String bookInformation = bookInformation();
            String[] parts = bookInformation.split(",");
            File bookInfo = new File(parts[0] + ".txt");
            FileWriter myWriter = new FileWriter(bookInfo);
            myWriter.write(bookInformation);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }


    private static String getInput(String prompt) {
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);

        return input.nextLine();
    }

    private static int numberOfBooks() {
        String s = getInput("How many books would you like to add?");
        int bookNum = parseInt(s);
        return bookNum;
    }

    public static void main(String[] args) {
        String logOrReg = getInput("Would you like to login or register?");
        switch (logOrReg) {
            case "register":
                loginFile();
                break;
            case "login":
                logMeIn();
                break;
        }

        String userInput = getInput("What would you like to do?");
        switch (userInput) { //create a menu system so you can perform lots of commands here

        }

    }
}
