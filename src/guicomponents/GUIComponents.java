/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guicomponents;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays; 
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.net.*;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author acer
 */
public class GUIComponents extends JFrame {
    
    private final JLabel lblUsername; // displays username
    private final JLabel lblPassword; // displays password 
    private final JLabel lblHeight; // displays height
    private final JLabel lblWeight; // displays weight 
    private final JLabel lblGender; // displays gender 
    private final JLabel lblAgeGroup; // displays age
    private final JLabel lblPALDescription; // displays PAL description 
    private final JLabel lblPALNumber; // displays PAL number 
    
    private final JTextField txtUsername; // displays textfield for username
    private final JPasswordField txtPassword; // displays passwordfield for password
    private final JButton btnLogin;
    private final JTextField txtHeight; // displays textfield for height
    private final JTextField txtWeight; // displays textfield for weight
    private final JRadioButton rdoMale; // displays radio button for male
    private final JRadioButton rdoFemale; // displays radio button for female
    private final ButtonGroup rdoGroup;
    private final JComboBox<String> cmbAgeGroup;
    private static final String[] ageGroup = new String[]{}; 
    
    private final JComboBox<String> cmbPALDescription;
    private static final String[] PALDescription = new String[]{}; 
    private final JTextField txtPALNumber; // displays textfield for PAL number
    
    private final JTextArea displayArea;
    
    private final JButton btnLoad; // display Load button
    private final JButton btnDisplay; // display Display button
    private final JButton btnClear; // display Clear button
    private final JButton btnQuit; // display Quitbutton
    
    // Declaring arraylist of BMRdata, PALdata and EnergyData type
    ArrayList<BMRdata> bmr;
    ArrayList<PALdata> pal;
    ArrayList<EnergyData> energy;
    
    public GUIComponents(){
        super("Energy Requirements Calculator"); // Setting title for GUI
        
        //Creating top, middle and botom panels
        JPanel topPanel = new JPanel(new BorderLayout(10,10)); 
        JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 40));
        JPanel bottomPanel = new JPanel();
        
        // Creating three sub panel within top panel
        JPanel topPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,0,20));
        JPanel topPanel2 = new JPanel();
        JPanel topPanel3 = new JPanel();
        
        // Creating label and text fields
        lblUsername = new JLabel("Username:");
        txtUsername = new JTextField(10);
        
        lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField(10);
        
        btnLogin = new JButton("Log In"); //Login button
        
        lblHeight = new JLabel("Height (m):");
        txtHeight = new JTextField(10);
        txtHeight.setEditable(false); // Making height textfield disabled before login
        
        lblWeight = new JLabel("Weight (kg):");
        txtWeight = new JTextField(10);
        txtWeight.setEditable(false); // Making weight textfield disabled
        
        lblGender = new JLabel("Gender:");
        rdoMale = new JRadioButton("Male", false); 
        rdoMale.setActionCommand(rdoMale.getText());
        rdoMale.setEnabled(false); // Making radio button disabled before login
        rdoFemale = new JRadioButton("Female", false);
        rdoFemale.setActionCommand(rdoFemale.getText());
        rdoFemale.setEnabled(false); // Making radio button disabled before login
        rdoGroup = new ButtonGroup(); 
        rdoGroup.add(rdoMale);
        rdoGroup.add(rdoFemale);
        
        // Creating Age group combo box
        lblAgeGroup = new JLabel("Age Group:");
        cmbAgeGroup = new JComboBox<String>(ageGroup);
        
        // Creating PAL description combo box
        lblPALDescription = new JLabel("PAL Description:");
        cmbPALDescription = new JComboBox<String>(PALDescription);
        
        // Creating PAL number textfield
        lblPALNumber = new JLabel("PAL Number");
        txtPALNumber = new JTextField(10);
        txtPALNumber.setEditable(false);
        
        // Creating display area
        displayArea = new JTextArea(20,60);
        displayArea.setEditable(false);
        
        // Creating command buttons
        btnLoad = new JButton("Load Data"); 
        btnDisplay = new JButton("Display Energy"); 
        btnClear = new JButton("Clear Display"); 
        btnQuit = new JButton("Quit");
        
        // Making buttons disabled before login
        btnLoad.setEnabled(false);
        btnDisplay.setEnabled(false);
        btnClear.setEnabled(false);
        
        // Adding label to panels
        topPanel1.add(lblUsername);
        topPanel1.add(txtUsername);
        topPanel1.add(lblPassword);
        topPanel1.add(txtPassword);
        topPanel1.add(btnLogin);
        
        topPanel2.add(lblHeight);
        topPanel2.add(txtHeight);
        topPanel2.add(lblGender);
        topPanel2.add(rdoMale);
        topPanel2.add(rdoFemale);
        topPanel2.add(lblAgeGroup);
        topPanel2.add(cmbAgeGroup);
        topPanel2.add(lblPALDescription);
        topPanel2.add(cmbPALDescription);
        
        topPanel3.add(lblWeight);
        topPanel3.add(txtWeight);
        topPanel3.add(lblPALNumber);
        topPanel3.add(txtPALNumber);
        
        // Adding sub panel in top panel
        topPanel.add(topPanel1, "North");
        topPanel.add(topPanel2, "Center");
        topPanel.add(topPanel3, "South");
        
        middlePanel.add(displayArea);
        bottomPanel.add(btnLoad);
        bottomPanel.add(btnDisplay);
        bottomPanel.add(btnClear);
        bottomPanel.add(btnQuit);
        
        // Add top, middle and bottom panel to Frame
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Register event listener
        btnLogin.addActionListener(new LoginBtnHandler());
        txtHeight.addActionListener(new HeightBoxHandler());
        cmbPALDescription.addItemListener(new PALDescriptionHandler());
        btnLoad.addActionListener(new LoadBtnHandler());
        btnDisplay.addActionListener(new DisplayBtnHandler());
        btnClear.addActionListener(new ClearBtnHandler());
        btnQuit.addActionListener(new QuitBtnHandler());
    }
    
    // Event listner for Login button
    private class LoginBtnHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String txtUserNameVal = txtUsername.getText(); // Get the username value from text field
            char[] txtPasswordVal = txtPassword.getPassword(); // Get the password value from text field
            
            // Check if username and password matches with the default values
            if(validateLogin(txtUserNameVal, txtPasswordVal)){
                enableInputAfterLogin();
                displaySuccessMessage("Login Successfull","Login success");
            }
            else if(txtUserNameVal.length() == 0 || txtPasswordVal.length == 0){
                // Display error message if username and password is empty
                displayErrorMessage("Please, enter both username and password","Login Error message");
            }
            else{
                // Display error message if username and password does not match
                displayErrorMessage("Invalid login credentials","Login Error message");
            }
        }
    }
    
    // Event listner for Height text
    private class HeightBoxHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                Double txtHeightVal;
                Double expectedWeight;
                
                txtHeightVal = Double.parseDouble(txtHeight.getText()); // Converting height read from input box to double type
                if(txtHeightVal >= 1.5 && txtHeightVal <= 2.0)
                {
                    expectedWeight = getExpectedWeight(txtHeightVal); // Fetching weight for the corresponding height from file
                    // Setting the weight in text box to corresponding weight read from file
                    txtWeight.setText(Double.toString(expectedWeight));
                }
                else{
                    // Display error if value out of range
                    displayErrorMessage("Please, enter value within the range (1.5 - 2.0)","Input error message");
                }
            }
            catch(NumberFormatException  ex){
                // Show error message if user enter non-numeric value for height
                displayErrorMessage("Please, enter numeric values","Input Error message");
            }
        }
    }
    
    // Evenet handler for PAL Description combo box
    private class PALDescriptionHandler implements ItemListener{
        @Override
        public void itemStateChanged(ItemEvent event){
            // Get the current selected item
            if(event.getStateChange() == ItemEvent.SELECTED){
                Object item = event.getItem();
                PALdata pal = new PALdata(item.toString());
                // Set the PAL number textfield for the corresponding PAL description
                txtPALNumber.setText(pal.getPal().getValue());
            }
        }
    } 
    
    // Event listner for Load button
    private class LoadBtnHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            readFromFile(); // Method to read data from file
            loadComboBoxes(); // Method to load values on combo box with data read from file
        }    
    }
    
    // Event listner for Display button
    private class DisplayBtnHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                // Declaring local variables
                String userName;
                Double inputHeight;
                String inputGender;
                String inputAgeGroup;
                String palDescription;
                Double expectedWeight;
                Double bmr;

                userName = txtUsername.getText(); // Get the username value from textfield
                inputHeight = Double.parseDouble(txtHeight.getText()); // Get the height value from textfield and typecast it to double type
                inputGender = rdoGroup.getSelection().getActionCommand(); // Get the value of checked radio button
                inputAgeGroup = cmbAgeGroup.getSelectedItem().toString(); // Get the value of selected age group from combo box
                palDescription = cmbPALDescription.getSelectedItem().toString(); // Get the value of Selected PAL from combo box
                expectedWeight = getExpectedWeight(inputHeight); // Get the weight for corresponding height with method 
                bmr = getBMR(inputAgeGroup,inputHeight,inputGender); // Get the BMR value for corresponding height, age group and gender with mehtod

                // Creating a BMRdata object with values entered by the user for comparision with the data from file
                BMRdata bmrObj = new BMRdata(inputGender,inputAgeGroup,inputHeight,expectedWeight,bmr); 

                /* All the data read from file are stored as objects of arraylist of EnergyData class 
                So we need to loop through the arraylist object to search for the desired record */
                for(EnergyData en: energy){

                    /* Checking if the input entered by the user matches with record from the file
                    As we have stored age group, height, weight, gender and bmr in BMRdata class and 
                    PAL description and energy needs in PALdata class, 
                    we need to compare the user entered input against the record in both of the class 
                    */
                    if(compareBMRdataObjects(en.getBMRdata(),bmrObj) && en.getPALdata().getPal().getDescription().equals(palDescription)){
                        
                        // Create object of UserEnergyNeeds class with matched records from file
                        UserEnergyNeeds userEnergyNeeds = new UserEnergyNeeds(userName,inputGender,inputAgeGroup,inputHeight,expectedWeight,bmr,en.getPALdata());
                        
                        // Set the text area with the records read from file
                        String displayText = String.format("\n %s \n %s \n %s \n %s \n %s \n %s \n %s \n %s",
                            "Username: "+userEnergyNeeds.getUserName(),
                            "Energy requirement: "+userEnergyNeeds.getPALdata().getEnergy()+" Mega joules",
                            "Age group: "+userEnergyNeeds.getAgeGroup(),
                            "Gender: "+userEnergyNeeds.getGender(),
                            "BMR: "+userEnergyNeeds.getBmr(),
                            "PAL description: "+userEnergyNeeds.getPALdata().getPal().getDescription(),
                            "PAL number: "+userEnergyNeeds.getPALdata().getPal().getValue(),
                            "Expected average weight: "+userEnergyNeeds.getExpectedWeight()+" kg");
                        
                        // Text formatting in textarea field
                        displayArea.setText(displayText);
                        Font font = displayArea.getFont();
                        displayArea.setFont(font.deriveFont(14.0f));
                        break;
                    }

                }
            }
            catch(Exception ex){
                // Display error message if user did not enter inputs for all fields
                displayErrorMessage("Please, enter input for all required fields","Input error message");
            }
        }
    }
    
    // Event handler for Clear button
    private class ClearBtnHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                txtHeight.setText(""); // clear Height textfield
                txtWeight.setText(""); // clear Weight textfield
                txtPALNumber.setText(""); // clear PAL number textfield
                cmbAgeGroup.setSelectedIndex(0); // Set Age group combobox to default value (i.e, first value of the list)
                rdoGroup.clearSelection(); // Clear the radio button selection
                cmbPALDescription.setSelectedIndex(0); // Set PAL description combobox to default value (i.e, first value of the list)
                displayArea.setText(""); // Clear textarea
            }
            catch(Exception ex){
                // Bypass all types of exception occured when clearing 
            }
        }
    }
    
    // Event handler for Quit button
    private class QuitBtnHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            // Exit the application
            System.exit(0);
        }
    }
    
    // Method to validate username and password
    private boolean validateLogin(String inpUserName, char[] inpPassword){
        boolean isCorrect = true;
        boolean isCorrectUsername = true;
        boolean isCorrectPassword = true;
        String correctUsername = "Smith"; // Default Username for login
        
        // Checking if User entered username equals to default username
        if(!inpUserName.equals(correctUsername)){
            isCorrectUsername = false;
        }
        
        char[] correctPassword = {'M','e','l','b','o','u','r','n','e'};  // Default password for login
        
        // Checking if User entered password equals to default password
        if(inpPassword.length != correctPassword.length){
            isCorrectPassword = false;
        }
        else{
            // Comparing if user entered passwords match the default password
            isCorrectPassword = Arrays.equals(inpPassword, correctPassword);
        }
        
        // if both username and password is correct then return true
        isCorrect = (isCorrectUsername && isCorrectPassword) ? true: false;
        
        return isCorrect;
    }
    
    // Method to display error message on dialog box
    private void displayErrorMessage(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    } 
    
    // Method to display error message on dialog box
    private void displaySuccessMessage(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    } 
    
    // Method to enable the input fields and buttons after successfull login
    private void enableInputAfterLogin(){
        txtHeight.setEditable(true);
        rdoMale.setEnabled(true);
        rdoFemale.setEnabled(true);
        btnLoad.setEnabled(true);
        btnLoad.setEnabled(true);
        btnDisplay.setEnabled(true);
        btnClear.setEnabled(true);
    }
    
    // Method to return weight corresponding to the weight
    private double getExpectedWeight(double height){
        for(BMRdata b: bmr){
            /* If height entered in input box matches the height from the file
            then return weight corresponding to the height from file*/ 
            if(b.getHeight() ==  height){
                return b.getWeight();
            }
        }
        return 0;
    }
    
    // Method to return BMR corresponding to the age group and height 
    private double getBMR(String age, double height, String gender){
        for(BMRdata b: bmr){
            // Comparing if age, height and gender matches with records from file
            if(b.getAgeGroup().equals(age) && b.getHeight() ==  height && b.getGender().equals(gender)){
                // Return corresponding BMR of the record 
                return b.getBmr();
            }
        }
        return 0;
    }
    
    // Method to compare two BMRdata objects
    private boolean compareBMRdataObjects(BMRdata obj1, BMRdata obj2){
        boolean flag = false;
                
        // Compare all the member fields of BMRdata object with each other
        if(obj1.getGender().equals(obj2.getGender()) && obj1.getAgeGroup().equals(obj2.getAgeGroup()) &&
                obj1.getHeight() == obj2.getHeight() && obj1.getWeight() == obj2.getWeight() &&
                obj1.getBmr() == obj2.getBmr()){
            // Return true if comparision is successfull
            flag = true; 
        }
        
        return flag;
    }
    
    // Method to read data from file
    private void readFromFile(){
        try{
                /* Defining arraylist while loading data from file 
                to avoid populating arraylist everytime when Load data button is clicked*/
                bmr = new ArrayList<BMRdata>();
                pal = new ArrayList<PALdata>();
                energy = new ArrayList<EnergyData>();
            
                URL url = getClass().getResource("COIT20256Ass1Data.csv"); // Fetching the relative path of the file
                Scanner scanner = new Scanner(new File(url.getPath())); // Loading the file in Scanner object
                
                // Object and local variable declaration
                int counter = 0; // counter is used to track the line number of file
                BMRdata bmrObjMale;
                BMRdata bmrObjFemale;
                PALdata palObjMale;
                PALdata palObjFemale;
                
                // Read records of file line by line
                while(scanner.hasNextLine()){
                    // Counter denotes the line number of the file and should be incremented after each iteration
                    counter++; 
                    
                    String line = scanner.nextLine(); // Read the current line and advance the pointer to next line
                    String[] fields = line.split(","); // Store the values of current line in an array 
                    
                    // Skip the first two lines of the file which contains header
                    if(counter == 1 || counter == 2){
                        continue;
                    }
                    
                    /*-------- FOR MALE start--------*/
                    /* Creating object of BMRdata 
                    Index 0,1,2,3 of fields array denotes the age group, height, weight and BMR value of male
                    */
                    bmrObjMale = new BMRdata("Male",fields[0], Double.parseDouble(fields[1]), Double.parseDouble(fields[2]), Double.parseDouble(fields[3]));
                    // Storing the object in an arraylist
                    bmr.add(bmrObjMale);
                    
                    int m = 4; // Index 4 to 9 contains the energy needs of Male for corresponding PAL description
                    // Looping through PAL values
                    for(PAL p: PAL.values()){
                        // Creating objects of PALdata
                        palObjMale = new PALdata(p.getDescription(),Float.parseFloat(fields[m]));
                        // Storing the object of PALdata in arraylist
                        pal.add(palObjMale);
                        
                        // Storing object of EnergyData in arraylist
                        energy.add(new EnergyData(bmrObjMale, palObjMale));
                        m++;
                    }
                    /*-------- FOR MALE end--------*/
                    
                    /*-------- FOR FEMALE start--------*/
                    /* Creating object of BMRdata 
                    Index 0,1,2,10 of fields array denotes the age group, height, weight and BMR value of Female
                    */
                    bmrObjFemale = new BMRdata("Female",fields[0], Double.parseDouble(fields[1]), Double.parseDouble(fields[2]), Double.parseDouble(fields[10]));
                    // Storing the object in an arraylist
                    bmr.add(bmrObjFemale);
                    
                    int f = 11; // Index 11 to 16 contains the energy needs of Female for corresponding PAL description
                    // Looping through PAL values
                    for(PAL p: PAL.values()){
                        // Creating objects of PALdata
                        palObjFemale = new PALdata(p.getDescription(),Float.parseFloat(fields[f]));
                        // Storing the object of PALdata in arraylist
                        pal.add(palObjFemale);
                        
                        // Storing object of EnergyData in arraylist
                        energy.add(new EnergyData(bmrObjFemale, palObjFemale));
                        f++;
                    }
                    /*-------- FOR FEMALE end--------*/
                }
                System.out.println(energy.size());
                scanner.close();   
            }
            // Exception handling with different exception types
            catch (NullPointerException e){
                displayErrorMessage("Error in loading file. Please, include the file in the same hierarchy as class files","File error message");
            }
            catch (FileNotFoundException e){
                displayErrorMessage("File not found","File error message");
            }
            catch(Exception e){
                displayErrorMessage("File could not be processed","File error message");
            }
    }

    // Method to load age group and PAL description combo boxes
    private void loadComboBoxes(){
        /* Reseting combo box before loading 
        to avoid loading values multiple times if user click Load button multiple times*/
        cmbAgeGroup.removeAllItems();
        cmbPALDescription.removeAllItems();
        
        // Populating PAL description from PAL class
        for (PAL temp: PAL.values()){
            cmbPALDescription.addItem(temp.getDescription());
        }
        
        // Creating age arraylist to hold all age group read from file
        ArrayList<String> age = new ArrayList<String>();
        
        for(BMRdata b: bmr){
            // Storing age group read from file in arraylist
            age.add(b.getAgeGroup());
        }
        
        /* Since there are duplicate age group in the list, 
        I have used HashSet to filter the unique age group*/
        Set<String> uniqueAgeList = new HashSet<String>(age);
        // Populating unique age group read from file
        for(String a: uniqueAgeList){
            cmbAgeGroup.addItem(a);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        GUIComponents gui = new GUIComponents();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(750,480); // Setting the size of GUI window
        gui.setVisible(true); // Setting the visibility of GUI

    }
    
}
