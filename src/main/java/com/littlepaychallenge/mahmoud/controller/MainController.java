package com.littlepaychallenge.mahmoud.controller;

import java.util.ArrayList;

import com.littlepaychallenge.mahmoud.model.Tap;
import com.littlepaychallenge.mahmoud.service.FileService;

public class MainController {
    
    public static void initiateTapProcessing(){
        ArrayList<Tap> taps = readFile();

        for(Tap tap : taps){
            System.out.println("Tap: " + tap.toString());
        }
    }

    private static ArrayList<Tap> readFile(){
        FileService fileService = new FileService();

        ArrayList<Tap> taps = fileService.readFile("taps.csv");

        return taps;
    }
}
