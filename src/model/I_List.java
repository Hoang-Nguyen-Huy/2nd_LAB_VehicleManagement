package model;

/* Interface for a group of objects
 */

/**
 *
 * @author Nguyen Huy Hoang SE180435
 */
public interface I_List {
  // Find the position of element which has code equal parameter coe
  // add new element( input from scanner) to I_List
  void add(); 
  // Input the code wanna remove
  void delete();
  // input the code want to update, after that update other information--> use set method
  void update();
  // sort list use Collections.sort(this, new Comparator<Clock>()..., sort based price or make
  void find();
  
  void checkExist();
  
  void saveToFile();
  
  void print();
  
  void removeAll();
}
