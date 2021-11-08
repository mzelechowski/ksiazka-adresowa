package pl.mzelechowski;

import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class MenuProvider {
    CustomFileProvider cfp = new CustomFileProvider();

    public void menu() {
        Scanner in = new Scanner(System.in);
        int m=100;
        while(m!=0){
            headerPrinter();
            try {
                System.out.println();
                m = Integer.parseInt(in.nextLine());
                menuExecutor(m);
            } catch (NumberFormatException e) {
                System.out.println("Podaj wybor w postaci liczby całkowietej.");
                m=100;
            }
        }
    }

    public void  menuExecutor(int m){
        switch (m){
            case 1:
                System.out.println("wyswietlam liste kontaktow");
                cfp.printByPerson(cfp.readPhoneBookByPerson());
                break;
            case 2:
                System.out.println("Dodaje nową  pozycję");
                cfp.readPhoneBookByPerson();
                cfp.addNewRecord(new Person().createPerson(), StandardOpenOption.APPEND);
                break;
            case 3:
                System.out.println("Usuwam pozycje");
                cfp.removeRecord();
                break;
            case 4:
                System.out.println("Wskaz sciezke i nazwe pliku");
                break;
            case 0:
                System.out.println("Wybrałeś 0 - kończę program Miłego dnia. Bye");
                break;

            default:
                System.out.printf("Nie ma takiej opcji. Podaj jeszcze raz co chesz zrobic: ");
                break;
        }
    }


    public void headerPrinter(){
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Wyswietl wspisy");
        System.out.println("     2. Dodaj kontakt");
        System.out.println("     3. Usun kontakt");
        System.out.println("     4. Zmien sciezke i nazwe pliku ksiazki adresowej");
        System.out.println("     0. Koniec");

        System.out.printf("     Dokonaj wyboru Neo: " );
    }
}
