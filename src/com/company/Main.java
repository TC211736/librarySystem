package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class EraserThread implements Runnable {

    private boolean stop;

    public EraserThread(String prompt) {
        System.out.println(prompt);
    }

    public void run() {
        stop = true;
        while (stop) {
            System.out.print("\010*");
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

class PasswordField {
    public static String readPassword(String prompt) {
        EraserThread et = new EraserThread(prompt);
        Thread mask = new Thread(et);
        mask.start();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String password = "";

        try {
            password = in.readLine();
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        et.stopMasking();
        return password;
    }
}


// Code for setting up my FileNameFilter which I may need in future

//class MyFilenameFilter implements FilenameFilter {
//    String fileName;
//
//    public MyFilenameFilter(String fileName) {
//
//        this.fileName = fileName.toLowerCase();
//    }
//
//    @Override
//    public boolean accept(File dir, String name) {
//
//        return name.toLowerCase().startsWith(fileName);
//    }
//}

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
        String username = userName();
        String[] userInfo = null;
        Scanner fileReader = new Scanner(username + ".txt");
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();
            userInfo = data.split(", ");
        }
        return userInfo;

    }

    private static String userName() {
        String username = getInput("Please enter your username.");
        return username;
    }

    private static boolean password() {
        boolean validPassword = false;
        String[] userInfo = readLoginFile();
        String actualPassword = userInfo[0];
        while (!validPassword) {
            String password = PasswordField.readPassword("Please enter your Password");
            if (password.equals(actualPassword)) {
                validPassword = true;
                System.out.println("Welcome" + userInfo[0]);
            } else {
                System.out.println("Password Incorrect");
            }
        }
        return true;
    }


    private static void writeBookInfo() {
        try {
            int bookNum = numberOfBooks();
            for (int i = 0; i < bookNum; i++) {
                String bookInformation = bookInformation();
                String[] parts = bookInformation.split(", ");
                File bookInfo = new File(parts[0] + ".txt");
                FileWriter myWriter = new FileWriter(bookInfo);
                myWriter.write(bookInformation);
                myWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private static String bookInformation() {
        String bookName = getInput("What is the title of the book?");
        String ISBN = getInput("What is the ISBN Number of the book?");
        String author = getInput("Who is the author of this book?");
        String genre = getInput("What is the genre of this book?");
        libraryBookNames.add(bookName + ", " + ISBN + ", " + author + ", " + genre);
        return (bookName + ", " + ISBN + ", " + author + ", " + genre);
    }

    private static Object bookInfoObj() {
        String BI = bookInformation();
        String[] parts = BI.split(", ");
        int ISBN = Integer.parseInt(parts[1]);
        bookInformation bookInfo = new bookInformation(parts[0], parts[2], ISBN, parts[3]);
        return bookInfo;
    }


    private static int numberOfBooks() {
        int bookNum = getInteger("How many books would you like to add?");
        return bookNum;
    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    private static int getInteger(String prompt) {
        System.out.println(prompt);
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

//    Code for my FileNameFilter which I may need to use in future

//    private static void findUserInfo() {
//        String name = userName();
//        File file = new File("W10Desktop");
//        File[] listFiles = file.listFiles(new MyFilenameFilter(name)); //idk man figure it out on the way home future me
//        System.out.println(listFiles);
//    }

    public static void main(String[] args) {
        String logOrReg = getInput("Would you like to login or register?");
        switch (logOrReg) {
            case "register":
                loginFile();
                break;
            case "login":
                boolean validLogin = password();
                if (validLogin == true) {
                    break;
                }
        }

        String userInput = getInput("What would you like to do?");
        switch (userInput) { //create a menu system so you can perform lots of commands here
            case "add book":
                writeBookInfo();
            case "delete book":
        }

    }
}
