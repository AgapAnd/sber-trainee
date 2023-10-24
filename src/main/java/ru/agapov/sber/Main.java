package ru.agapov.sber;

import ru.agapov.sber.models.City;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = readCatalog(new File("sber-catalog.csv"));
        List<City> cities = extractData(scan);
//        printCities(cities);
//        printCitiesSorted(cities);
//        printCityWithGreatestPopulation(cities);
        countCitiesInRegions(cities);
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
                foundation = "нет данных";

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

    public static void printCities(List<City> cities) {
        cities.forEach(System.out::println);
    }

    public static void printCitiesSorted(List<City> cities) {
        System.out.println("Сортировка по названию города:");
        cities.stream().sorted(Comparator.comparing(City::getName)).forEach(System.out::println);
        System.out.println("\nСортировка по региону и в каждом регионе - по названию города:");
        cities.stream().sorted(Comparator.comparing(City::getDistrict).thenComparing(City::getName))
                .forEach(System.out::println);
    }

    public static void printCityWithGreatestPopulation(List<City> cities) {
        City cityPop = cities.stream()
                .sorted(Comparator.comparing(City::getPopulation).reversed())
                .collect(Collectors.toList())
                .get(0);
        System.out.println(cityPop.getId() + " - " + cityPop.getPopulation());

    }

    public static void countCitiesInRegions(List<City> cities) {
        Map<String, Integer> map = new HashMap<>();
        for (City city : cities) {
            String region = city.getRegion();
            map.computeIfPresent(region,(key,value) -> value + 1);
            map.putIfAbsent(region,1);
//            if (map.get(region) != null)
//                map.put(region,map.get(region) + 1);
//            else {
//                map.put(region, 1);
//            }
        }
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }
}
