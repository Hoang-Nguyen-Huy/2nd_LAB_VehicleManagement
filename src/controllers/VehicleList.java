package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nguyen Huy Hoang SE180435
 * 
 */
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.I_List;
import model.I_Menu;
import model.Vehicle;
import utils.Utils;

public class VehicleList extends ArrayList<Vehicle> implements I_List {

    @Override
    public void add() {
        
        boolean isAdding = true;
        while(isAdding) {
            String id = Utils.inpId(this); 
            String name = Utils.inpName();

            System.out.print("Color. ");
            String color = Utils.inpColorBrandType();
            float price = Utils.inpPrice();

            System.out.print("Brand. ");
            String brand = Utils.inpColorBrandType();

            System.out.print("Type. ");
            String type = Utils.inpColorBrandType();

            int productYear = Utils.inpYear();

            Vehicle vehicle = new Vehicle(id, name, color, price, brand, type, productYear);
            this.add(vehicle);

            System.out.println("Added successfully");
            System.out.println("You should saved to file before doing others stuff!!!");
            
            I_Menu menu = new Menu();
            isAdding = menu.confirmYesNo("Do you want to go back to main menu? (Y/N)");
        }
     
    }

    @Override
    public void delete () {
        String inp = checkExistence();
        if (!inp.equals("")) {
            ArrayList<Vehicle> printOut = readFromFile();
            for (Vehicle vehicleUp : printOut) {
                if (inp.equals(vehicleUp.getIdVehicle())) {
                    System.out.println(vehicleUp.toString());
                }
            }
            
            System.out.println("Exist Vehicle");
            
            I_Menu menu = new Menu();
            boolean choice = menu.confirmYesNo("Are you sure that you want to delete vehicle " + inp + "?(Y/N)");
            if (choice) {
                return;
            }
            
            ArrayList<Vehicle> res = readFromFile();
            
            Iterator<Vehicle> iterator = res.iterator();
            while (iterator.hasNext()) {
                Vehicle vehicle = iterator.next();
                if (inp.equals(vehicle.getIdVehicle())) {
                    iterator.remove(); // Use iterator to remove the element
                }
            }
            
            try (FileOutputStream fileOutputStream = new FileOutputStream("src\\data\\Vehicle.dat");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            
                objectOutputStream.writeObject(res);
                System.out.println("Remove successfully!!!");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to remove !!!");
            }
            
        } else {
            System.out.println("Vehicle does not exist in file!!!");
        }
    }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);
        String inp = checkExistence();
       
        if (!inp.equals("")) {
            ArrayList<Vehicle> printOut = readFromFile();
            for (Vehicle vehicleUp : printOut) {
                if (inp.equals(vehicleUp.getIdVehicle())) {
                    System.out.println(vehicleUp.toString());
                }
            }
            
            System.out.println("Vehicle Found");
            System.out.println("Leave blank if you don't want to update");
            
            String name = Utils.upName();
            
            System.out.print("New color. ");
            String color = Utils.upColorBrandType();
            
            float price = Utils.upPrice();
                        
            System.out.print("New brand. ");
            String brand = Utils.upColorBrandType();
            
            System.out.print("New type: ");
            String type = Utils.upColorBrandType();
            
            int year = Utils.upYear();
            
            ArrayList<Vehicle> check = readFromFile();
            for (Vehicle vehicle : check) {
                if (inp.equals(vehicle.getIdVehicle())) {
                    if (!name.isEmpty()) {
                        vehicle.setNameVehicle(name);
                    }
                    if (!color.isEmpty()) {
                        vehicle.setColorVehicle(color);
                    }
                    if (price != 0) {
                        vehicle.setPriceVehicle(price);
                    }
                    if (!brand.isEmpty()) {
                        vehicle.setBrandVehicle(brand);
                    }
                    if (!type.isEmpty()) {
                        vehicle.setTypeVehicle(type);
                    }
                    if (year != 0) {
                        vehicle.setProductYear(year);
                    }
                }
            }
            
            try (FileOutputStream fileOutputStream = new FileOutputStream("src\\data\\Vehicle.dat");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            
                objectOutputStream.writeObject(check);
                System.out.println("Update successfully!!!");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to update!!!");
            }
            
        } else {
            System.out.println("Vehicle does not exist in file!!!");
        }  
    }
 
    @Override
    public void find() {
        boolean isRunning = true;
        I_Menu menu = new Menu();
        menu.addItem("==========Find Vehicle==========");
        menu.addItem("      1. Find by name.");
        menu.addItem("      2. Find by id");
        menu.addItem("      [Others]. Back to main menu");
        int choice;
        while(isRunning) {
            menu.showMenu();
            choice = menu.getChoice();  
            switch(choice) {
                case 1:
                    findByName();
                    break;
                case 2:
                    findById();
                    break;
                default:
                    isRunning = false;
                    break;
            }
        }
    }
    
    @Override
    public void checkExist() {
        if (checkExistence().equals("")) {
            System.out.println("Vehicle has not been saved to file or has not been added to list!!!");
        } else {
            System.out.println("Vehicle exist!!!!");
        }
    }
       
    @Override
    public void saveToFile() {
        ArrayList<Vehicle> list = readFromFile();
        list.addAll(this);
        
        try (FileOutputStream fileOutputStream = new FileOutputStream("src\\data\\Vehicle.dat");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            
            objectOutputStream.writeObject(list);
            System.out.println("Saved to file successfully!!!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save to file!!!");
        }
    }

    @Override
    public void print() {
        boolean isRunning = true;
        I_Menu menu = new Menu();
        menu.addItem("========Vehicle List========");
        menu.addItem("      1. Print all list.");
        menu.addItem("      2. Print all list descending by price.");
        menu.addItem("      [Others]. Back to main menu");
        int choice;
        while(isRunning) {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    output();
                    break;
                case 2:
                    sort();
                    break;
                default:
                    isRunning = false;
                    break;
            }
        }
    }
    
    @Override
    public void removeAll() {
        this.clear();
    }
    
    public String checkExistence() {  
        boolean input = true;
        String inp = "";
        Scanner sc = new Scanner(System.in);
        while(input) {
            System.out.print("Enter id that you want to check: ");
            inp = sc.nextLine();
            String pattern = "^V\\d{3}$";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(inp);
            if (inp.isEmpty()) {
                System.out.println("ID can't be empty");
                input = true;
            } else if (!matcher.matches()) {
                System.out.println("ID is not valid");
                input = true;
            } else {
                input = false;
            }
        }
        
        boolean flag = false;
        for (Vehicle vhc : this) {
            if (inp.equals(vhc.getIdVehicle())) {
                flag = true;
            }
        }
        
        if (!Validation.checkDuplicate(inp, this)) {
            return inp;
        } else if (Validation.checkDuplicate(inp, this) && flag) {
            return "";
        }
        return "";
    }
    
    public void findById() {
        String inp = checkExistence();
        ArrayList<Vehicle> listById = readFromFile();
        if (!inp.equals("")) {
            for (Vehicle vhc : listById) {
                if (inp.equals(vhc.getIdVehicle())) {
                    System.out.println(vhc.toString());
                }
            }
        } else {
            System.out.println("No vehicle found!!!");
        }
    }
    
    public void findByName() {
        ArrayList<Vehicle> findByName = new ArrayList<>();
        ArrayList<Vehicle> list = readFromFile();
        
        String name = Utils.inpName();
        
        for (Vehicle vhc : list) {
            if (vhc.getNameVehicle().contains(name)) {
                findByName.add(vhc);
            }
        }
        if (findByName.isEmpty()) {
            System.out.println("No vehicle found!!!");
            return;
        }
        
        Collections.sort(findByName, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle v1, Vehicle v2) {
                return v2.getNameVehicle().compareTo(v1.getNameVehicle());
            }
        });
        
        for (Vehicle vehicle : findByName) {
            System.out.println(vehicle.toString());
        }
    }
    
    public ArrayList<Vehicle> readFromFile() {
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        
        try (FileInputStream fileInputStream = new FileInputStream("src\\data\\Vehicle.dat");
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            
            vehicleList = (ArrayList<Vehicle>)objectInputStream.readObject();
            
        } catch (EOFException errorEof) {
            
        }
        catch (FileNotFoundException e) {
            // tao file
        } catch (IOException | ClassNotFoundException error) {
            error.printStackTrace();
        }
        return vehicleList;
    }
    
    public void output() {
        ArrayList<Vehicle> list = readFromFile();
        if (list.isEmpty()) {
            System.out.println("Empty file");
            return;
        }
        
        Collections.sort(list, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle v1, Vehicle v2) {
                return v1.getIdVehicle().compareTo(v2.getIdVehicle());
            }
        });
        
        for (Vehicle vehicle : list) {
            System.out.println(vehicle.toString());
        }
    }
    
    public void sort() {
        ArrayList<Vehicle> list = readFromFile();
        if (list.isEmpty()) {
            System.out.println("Empty file");
            return;
        }
        Collections.sort(list, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle vehicle1, Vehicle vehicle2) {
                return Float.compare(vehicle2.getPriceVehicle(), vehicle1.getPriceVehicle());
            }
        });
        
        for (Vehicle vehicle : list) {
            System.out.println(vehicle.toString());
        }
    }
    
}
