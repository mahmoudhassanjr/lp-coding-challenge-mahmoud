package com.littlepaychallenge.mahmoud.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;

import com.littlepaychallenge.mahmoud.constants.Constants;
import com.littlepaychallenge.mahmoud.model.Tap;
import com.littlepaychallenge.mahmoud.model.Trip;

public class FileService {

    public ArrayList<Tap> readFile(String fileName) {
        ArrayList<Tap> taps = new ArrayList<>();

        try {
            String path = "files/input/" + fileName;
            InputStream inputStream = FileService.class.getClassLoader().getResourceAsStream(path);
            if (inputStream == null) {
                throw new FileNotFoundException("File not found in resources: " + path);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] splitLines = line.split(",");

                String id = splitLines[0];
                String dateTimeUTC = splitLines[1];
                String tapType = splitLines[2];
                String stopId = splitLines[3];
                String companyId = splitLines[4];
                String busId = splitLines[5];
                String pan = splitLines[6];

                System.out.println("ID: " + id + " Datetime: " + dateTimeUTC + " Tap type: " + tapType + " Stop ID: " + stopId + " Company ID: " + companyId + " Bus ID: " + busId + " PAN: " + pan);
                Tap newTap = new Tap(id, dateTimeUTC, tapType, stopId, companyId, busId, pan);
                taps.add(newTap);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return taps;
    }

    public void writeToFile(TreeMap<String, Trip> tripRepository, String fileName) {
        try {
            String path = "src/main/resources/files/output/" + fileName;
            String headers = Constants.OUTPUT_FILE_HEADERS;
            
            File file = new File(path);
            boolean dirsCreated = file.getParentFile().mkdirs();
            System.out.println(dirsCreated);
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(headers);
                
                for (Trip trip : tripRepository.values()) {
                    writer.write(trip.toString());
                }
                writer.flush();
            }
            
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
