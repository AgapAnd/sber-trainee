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
        readCatalog().forEach(System.out::println);
    }

    public static List<City> readCatalog() throws IOException {
        List<City> cities = new ArrayList<>();
        Scanner scan = new Scanner(new File("src/resources/sber-catalog.csv"));
        while (scan.hasNextLine()) {

            String[] array;
            array = scan.nextLine().split(";");
            City city = new City(
                    Integer.parseInt(array[0])
                    ,array[1]
                    ,array[2]
                    ,array[3]
                    ,Long.parseLong(array[4])
                    ,array[5]);
            cities.add(city);
        }
        return cities;

    }
}
