/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicomponents;

/**
 *
 * @author acer
 */
public abstract class User {
    // Declaring class variables
    private String userName;
    private String gender;
    private String ageGroup;
    private double height;
    
    /* Constructor to initilize member variables of abstract class
    It will be initilize from sub class
    */
    public User(String uName, String gen, String age, double ht){
        userName = uName;
        gender = gen;
        ageGroup = age;
        height = ht;
    }
    
    // Setter method for username
    public void setUserName(String uName){
        userName = uName;
    }
    
    // Getter method for username
    public String getUserName(){
        return userName;
    }
    
    // Setter method for gender
    public void setGender(String gen){
        gender = gen;
    }
    
    // Getter method for gender
    public String getGender(){
        return gender;
    }
    
    // Setter method for age group
    public void setAgeGroup(String age){
        ageGroup = age;
    }
    
    // Getter method for age group
    public String getAgeGroup(){
        return ageGroup;
    }
    
    // Setter method for height
    public void setHeight(double ht){
        height = ht;
    }
    
    // Getter method for height
    public double getHeight(){
        return height;
    }
    
    @Override
    public String toString()
    {
      return String.format( "%s %s %s %.2f", 
         getUserName(), getGender(), getAgeGroup(), getHeight() );
    } // end method toString
}
