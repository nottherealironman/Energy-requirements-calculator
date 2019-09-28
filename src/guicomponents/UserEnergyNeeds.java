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
public class UserEnergyNeeds extends User{
    
    // Declaring class variables
    private double expectedWeight;
    private double bmr;
    private PALdata palData;
    
    // Constructor to initilize class variables and super class variables
    public UserEnergyNeeds(String uName, String gen, String age, double ht, double wt, double b, PALdata p){
        super(uName, gen, age, ht);
        expectedWeight = wt;
        bmr = b;
        palData = p;
    }
    
    // Setter method to set weight 
    public void setExpectedWeight(double wt){
        expectedWeight = wt;
    }
    
    // Getter method to get weight
    public double getExpectedWeight(){
        return expectedWeight;
    }
    
    // Setter method to set BMR
    public void setBmr(double b){
        bmr = b;
    }
    
    // Getter method to get BMR
    public double getBmr(){
        return bmr;
    }
    
    // Setter method to set PALdata
    public void setPALdata(PALdata p){
        palData = p;
    }
    
    // Getter method to get PALdata
    public PALdata getPALdata(){
        return palData;
    }
    
    @Override
    public String toString()
    {
      return String.format( "%s %s %s %.2f %.2f %.2f %s", 
         getUserName(), getGender(), getAgeGroup(), getHeight(), getExpectedWeight(), getBmr(), getPALdata());
    } // end method toString
}
