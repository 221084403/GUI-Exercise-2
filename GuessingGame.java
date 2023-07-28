/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.ui;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author MNCEDISI
 */
public class GuessingGame extends JFrame
{
    private JPanel headingPNL;
    private JPanel usernameAndGuessGridPNL;
    private JPanel usernameAndGuessPNL;
    private JPanel displayAreaPNL;
    private JPanel areaAndButtonPNL;
    private JPanel buttonPNL;
    private JPanel mainPNL;
    
    private JLabel headingLBL;
    private JLabel usernameLBL;
    private JLabel guessNumLBL;

    private JTextField usernameTxtFLD;
    private JTextField guessTxtFLD;
   
    private JTextArea area;
    
    private JScrollPane scrollSLP;
    
    private JButton guessBTN;
    private JButton stopBTN;
    private JButton exitBTN;
    
    private Random random;
    
    private ArrayList<Integer> computerNum;
    private ArrayList<Integer> userGuessedNum;
    private int correctGuess;
    
    public GuessingGame()
    {
        setTitle("Guessing Game app");
        setSize(400, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
       
        //Creating a random
        random = new Random();
        
        //Creating lists
        computerNum = new ArrayList<>();
        userGuessedNum = new ArrayList<>();
        
        //Creating a panel
        headingPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usernameAndGuessGridPNL = new JPanel(new GridLayout(2, 1 ,5  ,5));
        usernameAndGuessPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
        displayAreaPNL = new JPanel(new FlowLayout(FlowLayout.LEFT));
        areaAndButtonPNL = new JPanel(new GridLayout(2, 1 , 1 ,1));
        buttonPNL = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPNL = new JPanel(new FlowLayout());
        
        //Creating a label
        headingLBL = new JLabel("Guessing Game");
        headingLBL.setFont(new Font(null, Font.BOLD, 30));
        headingLBL.setForeground(Color.GREEN);
        
        usernameLBL = new JLabel("Username :");
        guessNumLBL = new JLabel("Guess a number [1-5] :");
        
        //Creating a text field
        usernameTxtFLD = new JTextField(10);
        guessTxtFLD = new JTextField(10);
        
        //Creating a text area
        area = new JTextArea(8, 30);
        area.setEditable(false);
        
        //Creating a scrollpane
        scrollSLP = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS , JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        //Creating a button
        guessBTN = new JButton("GUESS");
        guessBTN.addActionListener(new GuessBTN());
        
        stopBTN = new JButton("STOP");
        stopBTN.addActionListener(new StopBTN());
       
        exitBTN = new JButton("EXIT");
        exitBTN.addActionListener(new exitBTN());
        
        //Adding to components
        
        headingPNL.add(headingLBL);
        
        usernameAndGuessGridPNL.add(usernameLBL);
        usernameAndGuessGridPNL.add(usernameTxtFLD);
        usernameAndGuessGridPNL.add(guessNumLBL);
        usernameAndGuessGridPNL.add(guessTxtFLD);
        
        usernameAndGuessPNL.add(usernameAndGuessGridPNL);
        
        displayAreaPNL.add(scrollSLP);
        
        buttonPNL.add(guessBTN);
        buttonPNL.add(stopBTN);
        buttonPNL.add(exitBTN);
        
        areaAndButtonPNL.add(displayAreaPNL);
        areaAndButtonPNL.add(buttonPNL);
        
        mainPNL.add(headingPNL , BorderLayout.NORTH);
        mainPNL.add(usernameAndGuessPNL , BorderLayout.CENTER);
        mainPNL.add(areaAndButtonPNL , BorderLayout.SOUTH);
        
        add(mainPNL , BorderLayout.CENTER);
  
        setVisible(true);
    }

    private static class exitBTN implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            System.exit(0);
        }
    }

    private class StopBTN implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            DecimalFormat dec = new DecimalFormat("#.00");
            
            String userName = usernameTxtFLD.getText();
            String computerList = getComputerList();
            String userGuessList = getUserGuessList();
            
            double total = userGuessedNum.size();
            int wrongGuess = (int)total - correctGuess;
            
            double percentage = (correctGuess / total)*100.0;
            
            String output = "Username                :"+userName+"\n"+
                            "Correct Guesses    :"+correctGuess+"\n"+
                            "Wrong Guesses     :"+wrongGuess+"\n"+
                            "Percentage              :"+dec.format(percentage)+"%\n\n"+
                            "Computer Numbers : ["+computerList+"]\n"+
                            userName+" Guess numbers : ["+userGuessList+"]\n";
            
            area.setText(output);
        }

        private String getComputerList() 
        {
            String storeNumbers = "";
            
            for (Integer d : computerNum)
                storeNumbers+=d+",";
            
            return  storeNumbers;
        }

        private String getUserGuessList() 
        {
            String storeNumbers = "";
            
            for (Integer d : userGuessedNum)
                storeNumbers+=d+",";
            
            return  storeNumbers;
        }

    }

    private class GuessBTN implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            int generateNum = random.nextInt(5)+1;
            int userNum = Integer.parseInt(guessTxtFLD.getText());
            
            String result = "Wrong";
            
            if(userNum == generateNum && userNum<=5)
            {
                correctGuess++;
                result = "Correct";
            }
            
            if(userNum<=5)
            {
                computerNum.add(generateNum);
                userGuessedNum.add(userNum);
            }
            else
                if(userNum>5)
                    result = "Guess number must not be more than 5";
            
            area.setText(result);
        }
    }
}
