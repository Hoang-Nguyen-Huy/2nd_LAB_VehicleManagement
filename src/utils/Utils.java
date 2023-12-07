/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;
import controllers.Validation;
import controllers.VehicleList;

/**
 *
 * @author Nguyen Huy Hoang SE180435
 * 
 */
public class Utils {

    public static String getString(String welcome) {
        boolean check = true;
        String result = "";
        do {
            Scanner sc = new Scanner(System.in);
            System.out.print(welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println("Input text!!!");
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getInt() {
        boolean check = true;
        int number = 0;
        do {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter your choices: ");
                number = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Input number!!!");
            }
        } while (check);
        return number;
    }

    public static boolean confirmYesNo(String welcome) {
        boolean result = true;
        String confirm = Utils.getString(welcome);
        if ("Y".equalsIgnoreCase(confirm)) {
            result = false;
        }
        return result;
    }
    
    public static String inpId(VehicleList list) {
        String inp = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("ID format Vxxx. Eg: V001");
        do {
            System.out.print("Enter id: ");
            inp = sc.nextLine();
        } while(!Validation.checkId(inp, list));
        return inp;
    }
    
    public static String inpName() {
        String inp = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter name: ");
            inp = sc.nextLine();
        } while(!Validation.checkName(inp));
        return inp;
    }
    
    public static String inpColorBrandType() {
        String inp = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter: ");
            inp = sc.nextLine();
        } while(!Validation.checkColorBrandType(inp));
        return inp;
    }
    
    public static float inpPrice() {
        String inp = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter price: ");
            inp = sc.nextLine();
        } while(!Validation.checkPrice(inp));
        return Float.parseFloat(inp);
    }
    
    public static int inpYear() {
        String inp = "";
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter production year: ");
            inp = sc.nextLine();
        } while(!Validation.checkYear(inp));
        return Integer.parseInt(inp);
    }
    
    
    
    public static String upName() {
        Scanner sc = new Scanner(System.in);
        String name;
        do {
            System.out.print("Enter new name: ");
            name = sc.nextLine();
        } while(!Validation.checkUpName(name));
        return name;
    }
    
    public static String upColorBrandType() {
        Scanner sc = new Scanner(System.in);
        String inp;
        do {
            System.out.print(" Enter: ");
            inp = sc.nextLine();
        } while(!Validation.checkUpColorBrandType(inp));
        return inp;
    }
    
    public static float upPrice() {
        String inp;
        boolean empty = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter new price: ");
            inp = sc.nextLine();
            if (inp.isEmpty()) {
                empty = true;
                break;
            }
        } while(!Validation.checkPrice(inp));
        
        if (empty) {
            return 0;
        }
        return Float.parseFloat(inp);
    }
    
    public static int upYear() {
        String inp;
        Scanner sc = new Scanner(System.in);
        boolean empty = false;
        do {
            System.out.print("Enter new year: ");
            inp = sc.nextLine();
            if (inp.isEmpty()) {
                empty = true;
                break;
            }
        } while(!Validation.checkYear(inp));
        if (empty) {
            return 0;
        }
        return Integer.parseInt(inp);
    }
}
