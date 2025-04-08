package com.littlepaychallenge.mahmoud;

import org.junit.Test;

import com.littlepaychallenge.mahmoud.controller.MainController;


public class AppTest 
{
     @Test
    public void runFullApplication()
    {
        MainController.initiateTapProcessing();
    }
}
