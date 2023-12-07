package view;

import model.I_List;
import model.I_Menu;
import controllers.Menu;
import controllers.VehicleList;

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
public class VehicleManagement {

    public static void main(String args[]) {
        I_Menu menu = new Menu();
        menu.addItem("==========Vehicle Management==========");
        menu.addItem("      0. Add new Vehicle.");
        menu.addItem("      1. Check exist Vehicle.");
        menu.addItem("      2. Update a Vehicle.");
        menu.addItem("      3. Remove a Vehicle.");
        menu.addItem("      4. Find Vehicle.");
        menu.addItem("      5. Print Vehicle list.");
        menu.addItem("      6. Save data to file.");
        menu.addItem("      7. Quit.");
        int choice;
        boolean cont = true;
        I_List list = new VehicleList();
        while(cont) {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 0:
                    list.add();
                    break;
                case 1:
                    list.checkExist();
                    break;
                case 2:
                    list.update();
                    break;  
                case 3:
                    list.delete();
                    break;
                case 4:
                    list.find();
                    break;
                case 6:
                    list.saveToFile();
                    list.removeAll();
                    break;
                case 7:
                    cont = menu.confirmYesNo("Do you want to quit?(Y/N)");
                    break;
                case 5:
                    list.print();
                    break;
                default:
                    System.out.println("Please enter number from 0 to 7");
                    break;
            }
        }
    }
}
