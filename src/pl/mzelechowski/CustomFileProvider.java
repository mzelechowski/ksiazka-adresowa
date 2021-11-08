package pl.mzelechowski;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomFileProvider {
    private static String path = "C:\\Mike\\ksiazka-adresowa\\src\\storage\\";
    private static String fileName = "ksiazka-adresowa.txt";

//    public void readFile() {
//        BufferedReader bufferedReader;
//        try {
//            FileReader fileReader = new FileReader(path + fileName);
//            bufferedReader = new BufferedReader(fileReader);
//            String line = bufferedReader.readLine();
//            while (line != null) {
//                System.out.println(line.replace(";", " "));
//                line = bufferedReader.readLine();
//            }
//        } catch (IOException e) {
//            System.out.println("Nie znaleziono pliku");
//        }
//        WaitForAnyKey();
//    }

    public List<Person> readPhoneBookByPerson() {
        BufferedReader bufferedReader;
        List<Person> output = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(path + fileName));
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] values = line.split(";");
                Person person = new Person();
                person.setId(Integer.parseInt(values[0].trim()));
                person.setName(values[1].trim());
                person.setSurName(values[2].trim());
                person.setPhoneNumber(values[3].trim());
                Person.setCOUNTER(Integer.parseInt(values[0].trim()));
                output.add(person);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public void printByPerson(List<Person> persons) {
        for (Person p : persons) {
            System.out.println(p.getId() + " " + p.getName() + " " + p.getSurName() + " " + p.getPhoneNumber());
        }
        WaitForAnyKey();
    }

    public void removeRecord() {
        printByPerson(readPhoneBookByPerson());
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj ID recordu do usunieca: ");
        int id = -1;
        do {
            try {
                if ((id = Integer.parseInt(scanner.nextLine())) > 0) {
                    System.out.println(id);
                } else {
                    System.out.print("Podaj ID recordu jako liczbę większą od zera: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Podaj ID recordu jako liczbę całkowitą: ");
            }
        } while (id <= 0);
        List<Person> persons = readPhoneBookByPerson();
        int index = -1;
        int counter = 0;
        for (Person p : persons) {
            if (p.getId() == id) {
                index = counter;
            }
            counter++;
        }
        if (index > -1) {
            persons.remove(index);
            Path path = Paths.get(this.path + this.fileName);
            try {
                Files.write(path, "".getBytes());
            } catch (IOException e) {
                System.out.print("Nie znaleziono pliku");
            }
            for (Person p : persons) {
                try {
                    String sb = p.getId() + ";" + p.getName() + ";" + p.getSurName() + ";" + p.getPhoneNumber() + ";" + "\r\n";
                    Files.write(path, sb.getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    System.out.println("Nie znaleziono pliku");
                }
            }
        }
    }

    public void addNewRecord(Person record, OpenOption openOption) {
        Path path = Paths.get(this.path + this.fileName);
        String sb = record.getId() + ";" + record.getName() + ";" + record.getSurName() + ";" + record.getPhoneNumber() + ";" + "\r\n";
        try {
            Files.write(path, sb.getBytes(), openOption);
        } catch (IOException e) {
            System.out.println("Nie znaleziono pliku");
        }
    }

    void WaitForAnyKey() {
        System.out.println("\nPress any key to continue . . . ");
        new Scanner(System.in).nextLine();
    }
}
