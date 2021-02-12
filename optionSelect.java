//Basic Game Application
//Version 2
//Keyboard Input

//Adds keyboard control of the astronaut

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;

//Sounds

//Keyboard and Mouse
import java.awt.event.*;

//*******************************************************************************
// Class Definition Section

public class optionSelect {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too

   //Declare the variables needed for the graphics
   public JFrame frame, optionSelectFrame;
   public Canvas canvas;
   public JPanel panel, optionSelectPanel;
   public JRadioButton option1, option2, option3, option4;
   public JButton send;
   public String answer1, answer2, answer3, answer4;

   public BufferStrategy bufferStrategy;


   // Main method definition
   // This is the code that runs first and automatically
   public static void main(String[] args) {
      optionSelect myApp = new optionSelect();   //creates a new instance of the game
   }

   public optionSelect() {
      answer1 = "HEY THERE!";
      answer2 = "WHAT?";
      answer3 = "NO WAY";
      answer4 = "GET OUT";
      optionPanel();
   }

   private void optionPanel() {

      optionSelectPanel = new JPanel();
      optionSelectFrame = new JFrame("Option Frame");
//      optionSelectFrame.setSize(500, 5);
      optionSelectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      optionSelectFrame.add(optionSelectPanel);

      option1 = new JRadioButton(answer1);
      option2 = new JRadioButton(answer2);
      option3 = new JRadioButton(answer3);
      option4 = new JRadioButton(answer4);
      send = new JButton("Enter");

      option1.setBounds(0, 0, 300, 20);
      option1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (option1.isSelected()){
               System.out.println("Option1");
               option2.setSelected(false);
               option3.setSelected(false);
               option4.setSelected(false);
            }
         }
      });
      option2.setBounds(0, 20, 300, 20);
      option2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (option2.isSelected()){
               System.out.println("Option2");
               option1.setSelected(false);
               option3.setSelected(false);
               option4.setSelected(false);
            }
         }
      });
      option3.setBounds(0, 40, 300, 20);
      option3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (option3.isSelected()){
               System.out.println("Option3");
               option1.setSelected(false);
               option2.setSelected(false);
               option4.setSelected(false);
            }
         }
      });
      option4.setBounds(0, 60, 300, 20);
      option4.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (option4.isSelected()){
               System.out.println("Option4");
               option1.setSelected(false);
               option2.setSelected(false);
               option3.setSelected(false);
            }
         }
      });
      send.setBounds(0, 100, 300, 20);
      send.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            if (option1.isSelected()){
               System.out.println("Option one has been selected");
            }
            else if(option2.isSelected()){
               System.out.println("Option two has been selected");
            }
            else if(option3.isSelected()){
               System.out.println("Option three has been selected");
            }
            else if(option4.isSelected()){
               System.out.println("Option four has been selected");
            }
            else{
               System.out.println("Nothing was selected");
            }
         }
      });

      optionSelectPanel.add(option1);
      optionSelectPanel.add(option2);
      optionSelectPanel.add(option3);
      optionSelectPanel.add(option4);
      optionSelectPanel.add(send);

      optionSelectFrame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      optionSelectFrame.setResizable(true);   //makes it so the frame cannot be resized
      optionSelectFrame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

      System.out.println("DONE graphic setup");
   }
}
