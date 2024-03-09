import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Drzewo drzewo = new Drzewo();


        try
        {
            Scanner fs = new Scanner(new File("InTest1.txt"));
            while (fs.hasNextInt()) {
                drzewo.insert1(fs.nextInt());
            }
            fs.close();
        }catch(FileNotFoundException e){
            return;
        }


//        losoweliczby(drzewo, 10);
        drzewo.zapisz1("OutTest3.txt");

        int w;
        Scanner sc = new Scanner(System.in);
        boolean a= false;
        while (!a) {
            System.out.println("1. Zapisz elementy drzewa do pliku.");
            System.out.println("2. Podaj wagę i poziom elementu.");
            System.out.println("3. Dodaj element do drzewa.");
            System.out.println("4. Usuń element z drzewa.");
            System.out.println("5. Wypisz elementy drzewa.");
            System.out.println("6. Wyjdź.");
            System.out.println("Wybierz opcje: ");
            w = sc.nextInt();
            switch (w) {
                case 1:
                    drzewo.zapisz1("OutTest3.txt");
                    System.out.println("Zapisano.");
                    break;
                case 2:
                    System.out.println("Podaj element ");
                    int element = sc.nextInt();
                    System.out.println("Waga: " + (drzewo.search1(element).wywazenie)+ ", poziom: " +drzewo.poziom1(element));
                    break;
                case 3:
                    System.out.print("Podaj element ");
                    drzewo.insert1(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Podaj element ");
                    drzewo.delete1(sc.nextInt());
                    break;
                case 5:
                    drzewo.KLP1();
                    break;
                case 6:
                    a = true;
                    break;
                default:
                    System.out.println("Błąd");
            }
        }
        sc.close();
    }

    public static void losoweliczby(Drzewo drzewo, int ile) throws IOException {
        FileWriter fw = new FileWriter("OutTest3.txt");
        Random r = new Random();
        for (int i = 0; i < ile; i++) {
            int number = r.nextInt(100) + 1;
            drzewo.insert1(number);
            fw.write(number + " ");
        }
        fw.close();
    }
}