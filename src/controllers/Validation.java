/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Vehicle;

/**
 *
 * @author Nguyen Huy Hoang SE180435
 * 
 */
public class Validation {
    
    public static boolean checkId(String inp, VehicleList list) {
        if (inp.isEmpty()) {
            System.out.println("ID can't be empty");
            return false;
        }
        
        String pattern = "^V\\d{3}$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(inp);
        if (!matcher.matches()) {
            System.out.println("ID is not valid");
            return false;
        }
        
        for (Vehicle vhc : list) {
            if (inp.equals(vhc.getIdVehicle())) {
                System.out.println("ID must not be duplicated");
                return false;
            }
        }
        
        if (!checkDuplicate(inp, list)) {
            System.out.println("ID must not be duplicated");
            return false;
        }
        return true;
    }
    
    public static boolean checkName(String inp) {
        if (inp.isEmpty()) {
            System.out.println("This can not be empty!!!");
            return false;
        }
        if (!inp.matches("^[a-zA-Z0-9\\s]*$")) {
            System.out.println("This can only contain letters, numbers, and spaces");
            return false;
        }
        return true;
    }

    public static boolean checkColorBrandType(String inp) {
        if (inp.isEmpty()) {
            System.out.println("This can not be empty!!!");
            return false;
        }
        if (inp.matches("^.*[0-9!@#$%^&*(){}_+-=*/.<>?|].*")) {
            System.out.println("This can only contain characters");
            return false;
        }
        
        return true;
    }
    
    public static boolean checkPrice(String inp) {
        try {
            Float.parseFloat(inp);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number");
            return false;
        }
    }
    
    public static boolean checkYear(String inp) {
        try {
            int year = Integer.parseInt(inp);
            return year >= 1800 && year <= 2024;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean checkDuplicate(String inp, VehicleList list) {
        ArrayList<Vehicle> vehicleList = list.readFromFile();
        for (Vehicle v : vehicleList) {
            if (inp.equals(v.getIdVehicle())) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean checkUpName(String inp) {
        if (!inp.matches("^[a-zA-Z0-9\\s]*$")) {
            System.out.println("This can only contain letters, numbers, and spaces");
            return false;
        }
        return true;
    }
    
    public static boolean checkUpColorBrandType(String inp) {
        if (inp.matches("^.*[0-9!@#$%^&*(){}_+-=*/.<>?|].*")) {
            System.out.println("This can only contain characters");
            return false;
        }
        
        return true;
    }
    
    
  
}
