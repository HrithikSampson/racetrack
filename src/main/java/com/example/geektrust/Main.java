package com.example.geektrust;

import com.example.geektrust.config.ApplicationContainer;
import com.example.geektrust.controller.RacetrackController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContainer container = new ApplicationContainer();
        RacetrackController controller = container.getController();
        
        Scanner scanner = new Scanner(System.in);
        String line;
        
        while (scanner.hasNextLine() && !(line = scanner.nextLine().trim()).isEmpty()) {
            String result = controller.processCommand(line);
            System.out.println(result);
        }
        
        scanner.close();
    }
}
