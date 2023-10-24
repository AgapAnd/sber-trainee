package ru.agapov.sber;

import ru.agapov.sber.models.City;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = readCatalog(new File("src/resources/sber-catalog.csv"));
        extractData(scan).forEach(System.out::println);
    }

    public static Scanner readCatalog(File file) throws IOException {
        Scanner scan = new Scanner(file);
        return scan;
    }
    public static List<City> extractData(Scanner scan) {
        List<City> cities = new ArrayList<>();
        while (scan.hasNextLine()) {
            String[] array = scan.nextLine().split(";",0);
            String foundation;
            if (array.length == 6)
                foundation = array[5];
            else
                foundation = "\u001B[33mнет данных\u001B[0m";

            City city = new City(
                        Integer.parseInt(array[0])
                        , array[1]
                        , array[2]
                        , array[3]
                        , Long.parseLong(array[4])
                        , foundation);
                cities.add(city);
        }
        return cities;
    }
}
