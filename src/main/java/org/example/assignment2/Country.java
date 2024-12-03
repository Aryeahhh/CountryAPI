package org.example.assignment2.model;

import java.util.List;
import java.util.Map;

public class Country {
    private Name name;
    private List<String> tld;
    private String cca2;
    private String ccn3;
    private String cca3;
    private String cioc;
    private boolean independent;
    private String status;
    private boolean unMember;
    private Map<String, Currency> currencies;
    private Idd idd;
    private List<String> capital;
    private List<String> altSpellings;
    private String region;
    private String subregion;
    private Map<String, String> languages;
    private Map<String, Translation> translations;
    private List<Double> latlng;
    private boolean landlocked;
    private List<String> borders;
    private double area;
    private Demonyms demonyms;
    private String flag;
    private Maps maps;
    private long population;
    private Map<String, Double> gini;
    private String fifa;
    private Car car;
    private List<String> timezones;
    private List<String> continents;
    private Flags flags;
    private CoatOfArms coatOfArms;
    private String startOfWeek;
    private CapitalInfo capitalInfo;
    private PostalCode postalCode;

    // Getters and setters for all fields

    public static class Name {
        private String common;
        private String official;
        private Map<String, NativeName> nativeName;

        // Getters and setters
    }

    public static class NativeName {
        private String official;
        private String common;

        // Getters and setters
    }

    public static class Currency {
        private String name;
        private String symbol;

        // Getters and setters
    }

    public static class Idd {
        private String root;
        private List<String> suffixes;

        // Getters and setters
    }

    public static class Translation {
        private String official;
        private String common;

        // Getters and setters
    }

    public static class Demonyms {
        private Gender eng;
        private Gender fra;

        // Getters and setters

        public static class Gender {
            private String f;
            private String m;

            // Getters and setters
        }
    }

    public static class Maps {
        private String googleMaps;
        private String openStreetMaps;

        // Getters and setters
    }

    public static class Car {
        private List<String> signs;
        private String side;

        // Getters and setters
    }

    public static class Flags {
        private String png;
        private String svg;
        private String alt;

        // Getters and setters
    }

    public static class CoatOfArms {
        private String png;
        private String svg;

        // Getters and setters
    }

    public static class CapitalInfo {
        private List<Double> latlng;

        // Getters and setters
    }

    public static class PostalCode {
        private String format;
        private String regex;

        // Getters and setters
    }
}