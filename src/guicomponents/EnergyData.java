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
public class EnergyData {
    
    // Declaring class variables
    private BMRdata bmrData;
    private PALdata palData;
    
    // Constructor to initilize member variables
    public EnergyData(BMRdata b, PALdata p){
        bmrData = b;
        palData = p;
    }
    
    // Setter method to set BMRdata
    public void setBMRdata(BMRdata b){
        bmrData = b;
    }
    
    // Getter method to get BMRdata
    public BMRdata getBMRdata(){
        return bmrData;
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
        return String.format("%s   %s   \n", getBMRdata(),getPALdata());
     }
    
}
