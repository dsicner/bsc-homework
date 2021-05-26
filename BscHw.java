package com.sicnedal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BscHw {

    public static HashMap<String, Double> example = new HashMap<>();

    public static LinkedHashMap<String, Double> records = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

//        saveData(3.552, "16000");
//        saveData(6.552, "19000");
//        saveData(7.552, "16000");
//        saveData(8.552, "16000");
//        saveData(6.552, "14000");
//        saveData(2.195, "19000");
//
//        sortAndPrintData();


        readData();

    }

    public static void readData() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String quit = "quit";
        String stringLine = "";

        try {

            while ((stringLine = br.readLine()) != quit) {

                System.out.println(stringLine);
                String[] values = stringLine.split("\\s");

                if (values.length != 2) {
                    sortAndPrintData();
                    System.err.println("Invalid number of arguments");
                    System.exit(1);
                }

                //System.out.println(values[0]);
                //System.out.println(values[1]);

                double weight = Double.parseDouble(values[0]);
                String postalCode = values[1];

                saveData(weight, postalCode);

                //System.out.println(values[0].getClass());
                //System.out.println(values.length);

                sortAndPrintData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveData(double weight, String postalCode) {

        if (records.containsKey(postalCode)) {
            records.put(postalCode, records.get(postalCode) + weight);
        } else {
            records.put(postalCode, weight);
        }

    }

    public static void sortAndPrintData() {

        NumberFormat formatter = new DecimalFormat("#.###");

        LinkedHashMap<String, Double> reverseMap = new LinkedHashMap<>();

        records.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseMap.put(x.getKey(), x.getValue()));

        for (Map.Entry<String, Double> entry : records.entrySet()) {
            System.out.println(entry.getKey() + " " + formatter.format(entry.getValue()));
        }

    }
}
