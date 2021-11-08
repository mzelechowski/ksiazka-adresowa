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

    public void readFile() {
        BufferedReader bufferedReader;
        try {
            FileReader fileReader = new FileReader(path + fileName);
            bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                System.out.println(line.replace(";"," "));
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Nie znaleziono pliku");
        }
        WaitForAnyKey();
    }
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
            e.printStackTrace(); //drukuje stack błędu
        }
        return output;
    }

    public void readRecord(List<Person> persons){
        for(Person p:persons){
            System.out.println(p.getId()+" "+ p.getName()+" "+ p.getSurName()+" "+p.getPhoneNumber());
        }
        WaitForAnyKey();
    }

    public void addNewRecord(Person record){
        readPhoneBookByPerson();
        Path path = Paths.get(this.path+this.fileName);
        String sb=record.getId()+";"+record.getName()+";"+record.getSurName()+";"+record.getPhoneNumber()+";"+"\r\n";
        try {
            Files.write(path, sb.getBytes(), StandardOpenOption.APPEND);
        }catch(IOException e){
            System.out.println("Nie znaleziono pliku");
        }
    }


    void WaitForAnyKey()
    {
        System.out.println("\nPress any key to continue . . . ");
        new Scanner(System.in).nextLine();
    }


}
