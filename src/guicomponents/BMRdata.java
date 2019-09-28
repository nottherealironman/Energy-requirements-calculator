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
public class BMRdata {
    // Declaring class variables
    private String ageGroup;
    private String gender;
    private double height;
    private double weight;
    private double bmr;
    
    // Creating constructor to initilize class variables
    public BMRdata( String gen, String age, double ht, double wt, double b){
        gender = gen;
        ageGroup = age;
        height = ht;
        weight = wt;
        bmr = b;
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
    
    // Setter method for weight
    public void setWeight(double wt){
        weight = wt;
    }
    
    // Getter method for weight
    public double getWeight(){
        return weight;
    }
    
    // Setter method for BMR
    public void setBmr(double b){
        bmr = b;
    }
    
    // Getter method for BMR
    public double getBmr(){
        return bmr;
    }
    
    public String toString()
    {
       return String.format("%s   %s   %.2f  %.2f  %.2f\n", getAgeGroup(),getGender(), getHeight(), getWeight(), getBmr());
    }
}
