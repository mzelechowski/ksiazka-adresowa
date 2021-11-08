package pl.mzelechowski;

import java.util.Scanner;

public class Person {
    private int id;
    private String name;
    private String surName;
    private String phoneNumber;
    private static int COUNTER=0;

    public Person() {
    }

    public Person(String name, String surName, String phoneNumber) {
        COUNTER+=1;
        this.id=COUNTER;
        this.name = name;
        this.surName = surName;
        this.phoneNumber = phoneNumber;
    }

    public static int getCOUNTER() {
        return COUNTER;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static void setCOUNTER(int COUNTER) {
        Person.COUNTER = COUNTER;
    }

    public Person createPerson(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj imię: ");
        String name=scanner.nextLine();
        System.out.println("Podaj nazwisko: ");
        String surName=scanner.nextLine();
        System.out.println("Podaj numer telefonu: ");
        String phoneNumber= scanner.nextLine();
        return new Person(name,surName,phoneNumber);
    }
}
