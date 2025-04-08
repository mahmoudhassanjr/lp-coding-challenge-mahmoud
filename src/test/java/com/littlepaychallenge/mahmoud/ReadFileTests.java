package com.littlepaychallenge.mahmoud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.littlepaychallenge.mahmoud.model.Tap;
import com.littlepaychallenge.mahmoud.service.FileService;


public class ReadFileTests {
    @Test
    public void readFileOfSixTaps(){
        FileService fileService = new FileService();
        String fileName = "six-taps-test.csv";
        ArrayList<Tap> taps = fileService.readFile(fileName);  

        assertTrue(taps.size() == 6);
    }

    @Test
    public void readEmptyFile(){
        FileService fileService = new FileService();
        String fileName = "empty-taps-file.csv";
        ArrayList<Tap> taps = fileService.readFile(fileName);
        assertTrue(taps.size() == 0);
    }

    @Test
    public void readFileWithSingleTap() {
        FileService fileService = new FileService();
        String fileName = "single-tap-test.csv";
        ArrayList<Tap> taps = fileService.readFile(fileName);
        assertEquals(1, taps.size());
        assertNotNull(taps.get(0)); 
    }

    @Test
    public void readFileWithInvalidFileName() {
        FileService fileService = new FileService();
        String fileName = "nonexistent-file.csv";
        ArrayList<Tap> taps = fileService.readFile(fileName);
        assertTrue(taps.isEmpty());
    }

    @Test
    public void readFileWithHeaderOnly() {
        FileService fileService = new FileService();
        String fileName = "header-only-taps.csv";
        ArrayList<Tap> taps = fileService.readFile(fileName);
        assertEquals(0, taps.size());
    }

    @Test
    public void readFileWithLargeNumberOfTaps() {
        FileService fileService = new FileService();
        String fileName = "large-taps-test.csv"; //1000 taps (yay TreeMaps)
        ArrayList<Tap> taps = fileService.readFile(fileName);
        assertEquals(1000, taps.size());
    }
}
