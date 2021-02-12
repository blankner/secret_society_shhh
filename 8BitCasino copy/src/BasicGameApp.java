//Basic Game Application
//Version 2
//Keyboard Input

//Adds keyboard control of the astronaut

//Blake Ankner

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//Sounds

//Keyboard and Mouse
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseListener, MouseMotionListener, ActionListener {

    //Variable Definition Section
    //Declare the variables used in the program
    //You can set their initial values too

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;

    //Declare the variables needed for the graphics
    public static JFrame frame;
    public Canvas canvas;
    public JPanel panel;

    public BufferStrategy bufferStrategy;

    public static Hero currentPlayer;

    public Image mainScreenPic;
    public Image mainScreenHighlightPic;
    public Image upCharacter;
    public Image downCharacter;
    public Image leftCharacter;
    public Image rightCharacter;
    public Image background;
    public Image highlightedChair;

    public int mouseX, mouseY;
    public int chairIntersectedX, chairIntersectedY;

    public Rectangle[] noGoRectangles;
    public Rectangle chairExample; // this is thje rect that is a pseudo way to make the chairs highlight only on intersection
    public Rectangle[] chairBlackJack;
    public Rectangle[] chairSlots;
    public Rectangle[] chairPoker;
    public Rectangle[] chairStore;
    public Rectangle[] chairPathFinder;
    public Rectangle[] chairSolitare;
    public Rectangle rugRectangle;
    public Rectangle mainScreenDooorRect;
    public Rectangle logOutRec;

    public int[][] recs = {
            {0, 145, 98, 432},
            {0, 21, 401, 60},
            {302, 145, 102, 432},
            {602, 147, 97, 429},
            {902, 145, 98, 431},
            {600, 0, 400, 81},
            {24, 579, 50, 60},
            {324, 580, 50, 60},
            {626, 578, 48, 60},
            {928, 578, 47, 59},
    };
    //   Black Jack Chair Values
    public int[][] blackJackChairRecs = {
            {108, 222, 35, 39},
            {108, 284, 35, 39},
            {108, 408, 35, 39},
            {108, 470, 35, 39},
            {108, 532, 35, 39},
    };
    //   Slots Chair Values
    public int[][] slotsChairRecs = {
            {258, 222, 35, 39},
            {258, 285, 35, 39},
            {258, 344, 35, 39},
            {258, 408, 35, 39},
            {258, 532, 35, 39},
    };
    //   Poker Chair Values
    public int[][] pokerChairRecs = {
            {408, 222, 35, 39},
            {408, 284, 35, 39},
            {408, 347, 35, 39},
            {408, 409, 35, 39},
            {408, 531, 35, 39},
    };
    //   Store Chair Values
    public int[][] storeChairRecs = {
            {557, 222, 35, 39},
            {557, 408, 35, 39},
            {557, 470, 35, 39},
            {557, 532, 35, 39},
    };
    //   PathFinder Chair Values
    public int[][] pathFinderChairRecs = {
            {709, 222, 35, 39},
            {709, 284, 35, 39},
            {709, 347, 35, 39},
            {709, 470, 35, 39},
            {709, 532, 35, 39},
    };
    //   Solitare Chair Values
    public int[][] solitareChairRecs = {
            {858, 284, 35, 39},
            {858, 347, 35, 39},
            {858, 409, 35, 39},
            {858, 532, 35, 39},
    };

    public boolean highlightThisChair;
    public static boolean mainScreen;
    public boolean startTheGame;
    public boolean mainScreenKey;
    public boolean highlightMainDoors;
    public boolean blackJack;
    public boolean slots;
    public boolean poker;
    public boolean store;
    public boolean pathFinder;
    public boolean solitare;
    public static boolean logOut;

    //   variables for saving to file
    public static String name;
    public String password;
    public static String money;

    //   Variables for Sign In layout
    private static JLabel userLabel, passwordLabel, usernameSignUpLabel, passwordSignUpLabel, passwordCheckSignUpLabel, succesLabel, unsucsessfulLabel, accountCreated, checkPasswords, pickNewUserName;
    private static JTextField userText, usernameSignUp, passwordSignUp, passwordCheckSignUP;
    private static JPasswordField passwordText;
    private static JButton button, goToSignUPButton, tryAgainButton, signUpButton;
    private static JPanel panelLog, panelSignOrLog, panelSignUp;
    private static JFrame frameLog, frameSignOrLog, frameSignUp;

    //   Variables for reading Record:
    public static String[] character;
    public static ArrayList<String> usernames = new ArrayList<String>();
    public static ArrayList<String> passwords = new ArrayList<String>();
    public static ArrayList<String> moneys = new ArrayList<String>();
    public static ArrayList<User> players = new ArrayList<User>();

//    Smile Game:

    public Image back;
    public Image smile;
    public Image meh;
    public Image angry;
    public Image empty;
    public Image correct;
    public Image wrong;

    public int gameState;
    public int answerCounter;
    public int correctness;

    public boolean angryBool, mehBool, smileBool;
    public boolean correctBool;

    public String smileQuestion;


//   Variables for searching Record

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp myApp = new BasicGameApp();   //creates a new instance of the game
        new Thread(myApp).start(); //creates a threads & starts up the code in the run( ) method

        panelLog = new JPanel();
        frameLog = new JFrame("Login");
        frameLog.setSize(350, 165);
        frameLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLog.add(panelLog);

        panelLog.setLayout(null);

//      Text "user" label
        userLabel = new JLabel("User:");
        userLabel.setBounds(10, 20, 80, 25);
        panelLog.add(userLabel);
//      Place to type username
        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panelLog.add(userText);
//      Text "password" label
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panelLog.add(passwordLabel);
//      Place to type Password
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panelLog.add(passwordText);
//      Button to "Login"
        button = new JButton("Go");
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = passwordText.getText();
                int userNumber = -1;
                userNumber = searchRecord(user);
                if (userNumber > -1 && password.equals(players.get(userNumber).password)) {
//               System.out.println(user);
                    unsucsessfulLabel.setText("");
                    succesLabel.setText("Login succesful! Welcome " + user);
                    frameLog.setVisible(false);
                    frame.setVisible(true);
                    name = user;
                    currentPlayer.name = players.get(userNumber).name;
                    currentPlayer.password = players.get(userNumber).password;
                    currentPlayer.money = players.get(userNumber).money;
                    logOut = false;
                    mainScreen = false;
                }
//             if the username hasn't been seen before
                else if (userNumber < 0) {
                    frameLog.setVisible(false); //turn off logIn frame
                    frameSignOrLog.setVisible(true);
                } else {
                    succesLabel.setText("");
                    unsucsessfulLabel.setText("Incorrect try again.");
                }
            }
        });
        panelLog.add(button);
//      Succesful Login Label
        succesLabel = new JLabel("");
        succesLabel.setBounds(10, 110, 300, 25);
        panelLog.add(succesLabel);
//      Unsuccessful Login Label
        unsucsessfulLabel = new JLabel("");
        unsucsessfulLabel.setBounds(10, 110, 300, 25);
        panelLog.add(unsucsessfulLabel);


//      Sign up form:
        panelSignOrLog = new JPanel();
        frameSignOrLog = new JFrame("Sign Up?");
        frameSignOrLog.setSize(350, 120);
        frameSignOrLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSignOrLog.add(panelSignOrLog);

        panelSignOrLog.setLayout(null);
//      Sign up button
        goToSignUPButton = new JButton("Sign up");
        goToSignUPButton.setBounds(10, 10, 159, 75);
        panelSignOrLog.add(goToSignUPButton);
        goToSignUPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Sign up")) { //if Sign up button pressed
                    System.out.println("Sign up");
                    frameSignOrLog.setVisible(false);
                    frameSignUp.setVisible(true); //show sign up frame
                }
            }
        });
//      Try again button
        tryAgainButton = new JButton("Try again");
        tryAgainButton.setBounds(170, 10, 160, 75);
        panelSignOrLog.add(tryAgainButton);
        tryAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Try again");
                frameSignOrLog.setVisible(false); //turn off Sign Up or Try again button frame
                frameLog.setVisible(true); //go back to log In frame
            }
        });

        frameLog.setVisible(true);
        frameSignOrLog.setVisible(false);

//      Sign Up page
        panelSignUp = new JPanel();
        frameSignUp = new JFrame("Sign Up");
        frameSignUp.setSize(350, 185);
        frameSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameSignUp.add(panelSignUp);

        panelSignUp.setLayout(null);
//      Text "user" label
        usernameSignUpLabel = new JLabel("User:");
        usernameSignUpLabel.setBounds(10, 20, 80, 25);
        panelSignUp.add(usernameSignUpLabel);
//      Place to type username
        usernameSignUp = new JTextField(20);
        usernameSignUp.setBounds(100, 20, 165, 25);
        panelSignUp.add(usernameSignUp);
//      Text "password" label
        passwordSignUpLabel = new JLabel("Password:");
        passwordSignUpLabel.setBounds(10, 50, 80, 25);
        panelSignUp.add(passwordSignUpLabel);
//      Place to type Password
        passwordSignUp = new JTextField(20);
        passwordSignUp.setBounds(100, 50, 165, 25);
        panelSignUp.add(passwordSignUp);
//      Text "password" label
        passwordCheckSignUpLabel = new JLabel("Confirm Password:");
        passwordCheckSignUpLabel.setBounds(10, 80, 80, 25);
        panelSignUp.add(passwordCheckSignUpLabel);
//      Place to re-type Password
        passwordCheckSignUP = new JTextField(20);
        passwordCheckSignUP.setBounds(100, 80, 165, 25);
        panelSignUp.add(passwordCheckSignUP);
//      Button to "Submit"
        signUpButton = new JButton("Submit");
        signUpButton.setBounds(10, 110, 80, 25);
//      labels for creating new account:
        accountCreated = new JLabel("");
        accountCreated.setBounds(10, 135, 300, 25);
        panelSignUp.add(accountCreated);
        checkPasswords = new JLabel("");
        checkPasswords.setBounds(10, 135, 300, 25);
        panelSignUp.add(checkPasswords);
        pickNewUserName = new JLabel("");
        pickNewUserName.setBounds(10, 135, 300, 25);
        panelSignUp.add(pickNewUserName);
//      signUp action Listener
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usernameSignUp.getText();
                String password = passwordSignUp.getText();
                String passwordCheck = passwordCheckSignUP.getText();
                int userNumber = -1;
                userNumber = searchRecord(user);
                if (e.getActionCommand().equals("Submit")) {
                    if (userNumber < 0 && password.equals(passwordCheck) && !user.equals("")) {
                        System.out.println("Submit --> Cleared for new account");
                        accountCreated.setText("Congradulations on your new account!");
                        checkPasswords.setText("");
                        pickNewUserName.setText("");
                        players.add(new User(user.trim(), password.trim(), 500));
                        saveRecord(user, password, "500", "/Users/blakeankner/Desktop/programming/computer_programming/8BitCasino/Casino Data.csv");
                        frameSignUp.setVisible(false);
                        frameLog.setVisible(true);
                    } else if (userNumber > 0 || user.equals("")) {
                        System.out.println("Please chose new username");
                        accountCreated.setText("");
                        checkPasswords.setText("");
                        pickNewUserName.setText("Please chose a new user name.");
                    } else if (!password.equals(passwordCheck) || ((password.equals("")) || passwordCheck.equals(""))) {
                        System.out.println("Please check over the passwords.");
                        accountCreated.setText("");
                        checkPasswords.setText("Check your passwords again.");
                        pickNewUserName.setText("");
                    }
                }
            }
        });
        panelSignUp.add(signUpButton);

        frameSignUp.setVisible(false);

    }


    public BasicGameApp() {

        setUpGraphics();
        canvas.addKeyListener(this);
        canvas.addMouseListener(this);

        mainScreenPic = Toolkit.getDefaultToolkit().getImage("casinoMainScreen.png");
        mainScreenHighlightPic = Toolkit.getDefaultToolkit().getImage("casinoMainScreenDoorSelect.png");
        upCharacter = Toolkit.getDefaultToolkit().getImage("upCharacter.png");
        downCharacter = Toolkit.getDefaultToolkit().getImage("downCharacter.png");
        leftCharacter = Toolkit.getDefaultToolkit().getImage("leftCharacter.png");
        rightCharacter = Toolkit.getDefaultToolkit().getImage("rightCharacter.png");
        background = Toolkit.getDefaultToolkit().getImage("casinoLobby.png");
        highlightedChair = Toolkit.getDefaultToolkit().getImage("selectedChair.png");

        currentPlayer = new Hero(475, 640, 5, 5);

        highlightThisChair = false;
        mainScreen = false;
        highlightMainDoors = false;
        blackJack = false;
        slots = false;
        poker = false;
        store = false;
        pathFinder = false;
        solitare = false;
        logOut = false;

//      Rectangles:
//      Assign Rectangles value from 2D Array
        noGoRectangles = new Rectangle[recs.length];
        chairExample = new Rectangle(406, 347, 38, 39);
        for (int i = 0; i < recs.length; i++) {
            noGoRectangles[i] = new Rectangle(recs[i][0], recs[i][1], recs[i][2], recs[i][3]);
        }
//      Assign Rectangles value from 2D Array
        chairBlackJack = new Rectangle[blackJackChairRecs.length];
        for (int i = 0; i < blackJackChairRecs.length; i++) {
            chairBlackJack[i] = new Rectangle(blackJackChairRecs[i][0], blackJackChairRecs[i][1], blackJackChairRecs[i][2], blackJackChairRecs[i][3]);
        }
//      Assign Rectangles value from 2D Array
        chairSlots = new Rectangle[slotsChairRecs.length];
        for (int i = 0; i < slotsChairRecs.length; i++) {
            chairSlots[i] = new Rectangle(slotsChairRecs[i][0], slotsChairRecs[i][1], slotsChairRecs[i][2], slotsChairRecs[i][3]);
        }
//      Assign Rectangles value from 2D Array
        chairPoker = new Rectangle[pokerChairRecs.length];
        for (int i = 0; i < pokerChairRecs.length; i++) {
            chairPoker[i] = new Rectangle(pokerChairRecs[i][0], pokerChairRecs[i][1], pokerChairRecs[i][2], pokerChairRecs[i][3]);
        }
//      Assign Rectangles value from 2D Array
        chairStore = new Rectangle[storeChairRecs.length];
        for (int i = 0; i < storeChairRecs.length; i++) {
            chairStore[i] = new Rectangle(storeChairRecs[i][0], storeChairRecs[i][1], storeChairRecs[i][2], storeChairRecs[i][3]);
        }
//      Assign Rectangles value from 2D Array
        chairPathFinder = new Rectangle[pathFinderChairRecs.length];
        for (int i = 0; i < pathFinderChairRecs.length; i++) {
            chairPathFinder[i] = new Rectangle(pathFinderChairRecs[i][0], pathFinderChairRecs[i][1], pathFinderChairRecs[i][2], pathFinderChairRecs[i][3]);
        }
//      Assign Rectangles value from 2D Array
        chairSolitare = new Rectangle[solitareChairRecs.length];
        for (int i = 0; i < solitareChairRecs.length; i++) {
            chairSolitare[i] = new Rectangle(solitareChairRecs[i][0], solitareChairRecs[i][1], solitareChairRecs[i][2], solitareChairRecs[i][3]);
        }

        rugRectangle = new Rectangle(450, 640, 100, 60);
        mainScreenDooorRect = new Rectangle(262, 351, 471, 151);
        logOutRec = new Rectangle(890, 13, 100, 20);

        readRecordBySplit("/Users/blakeankner/Desktop/programming/computer_programming/8BitCasino/Casino Data.csv");

//        Smile:
        back = Toolkit.getDefaultToolkit().getImage("back.jpg");
        smile = Toolkit.getDefaultToolkit().getImage("smile.png");
        meh = Toolkit.getDefaultToolkit().getImage("meh.png");
        angry = Toolkit.getDefaultToolkit().getImage("angry.png");
        empty = Toolkit.getDefaultToolkit().getImage("empty.png");
        correct = Toolkit.getDefaultToolkit().getImage("correct.png");
        wrong = Toolkit.getDefaultToolkit().getImage("wrong.png");

        angryBool = true;

        answerCounter = 0;
  }

    //paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //      mainScreens:
        if (mainScreen && !mainScreenDooorRect.contains(mouseX, mouseY)) {
            g.drawImage(mainScreenPic, 0, 0, 1000, 700, null);
            g.setColor(Color.gray);
            g.setFont(new Font("IMPACT", Font.BOLD, 20));
            g.drawString("Welcome to 8Bit Casino! Click the doors to enter.", 290, 630);
            g.fillRect(890, 13, 100, 20);
            g.setColor(Color.white);
            g.drawString("Log Out", 910, 30);
        }
        if (mainScreenDooorRect.contains(mouseX, mouseY) && mainScreen) {
            g.drawImage(mainScreenHighlightPic, 0, 0, 1000, 700, null);
            g.setColor(Color.gray);
            g.setFont(new Font("IMPACT", Font.BOLD, 20));
            g.drawString("Welcome to 8Bit Casino! Click the doors to enter.", 290, 630);
            g.fillRect(890, 13, 100, 20);
            g.setColor(Color.white);
            g.drawString("Log Out", 910, 30);
        }

//      Image Drawing -- Character
        if (!mainScreen && gameState ==0) {
            g.drawImage(background, 0, 0, 1000, 700, null);
            g.setColor(Color.white);
            g.setFont(new Font("IMPACT", Font.BOLD, 20));
            g.drawString("Name: ", 3, 45);
            g.setColor(Color.lightGray);
            g.drawString(currentPlayer.name, 70, 45);
            g.setColor(Color.white);
            g.drawString("Money: ", 210, 45);
            g.setColor(Color.green);
            g.drawString("$" + currentPlayer.money, 288, 45);


            if (highlightThisChair) {
                g.drawImage(highlightedChair, chairIntersectedX, chairIntersectedY, 38, 39, null);
            }
            if (!currentPlayer.up && !currentPlayer.down && !currentPlayer.left && !currentPlayer.right) {
                g.drawImage(downCharacter, currentPlayer.xpos, currentPlayer.ypos, currentPlayer.width, currentPlayer.height, null);
            }
            if (currentPlayer.up) {
                g.drawImage(upCharacter, currentPlayer.xpos, currentPlayer.ypos, currentPlayer.width, currentPlayer.height, null);
            }
            if (currentPlayer.down) {
                g.drawImage(downCharacter, currentPlayer.xpos, currentPlayer.ypos, currentPlayer.width, currentPlayer.height, null);
            }
            if (currentPlayer.left) {
                g.drawImage(leftCharacter, currentPlayer.xpos, currentPlayer.ypos, currentPlayer.width, currentPlayer.height, null);
            }
            if (currentPlayer.right) {
                g.drawImage(rightCharacter, currentPlayer.xpos, currentPlayer.ypos, currentPlayer.width, currentPlayer.height, null);
            }
        }

//        Smile Game:
        if (gameState == 1){
            g.drawImage(back, 0, 0, 1000, 700, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("IMPACT", Font.BOLD, 20));
            smileQuestion = "HEY BABY!";
            g.drawString(smileQuestion, 25, 670);
            if (angryBool){
                g.drawImage(angry, 250, 25, 500, 500, null);
            }
            if (mehBool){
                g.drawImage(meh, 250, 25, 500, 500, null);
            }
            if (smileBool){
                g.drawImage(smile, 250, 25, 500, 500, null);
            }
            g.drawImage(empty, 150, 550, 70, 70, null);
            g.drawImage(empty, 300, 550, 70, 70, null);
            g.drawImage(empty, 450, 550, 70, 70, null);
            g.drawImage(empty, 600, 550, 70, 70, null);
            g.drawImage(empty, 750, 550, 70, 70, null);

            if (answerCounter == 6 && correctness >= 3){
                angryBool = false;
                mehBool = true;
            }
            if (answerCounter == 12 && correctness >= 6){
                mehBool = false;
                smileBool = true;
            }

            if (correctBool){
                correctness++;
                if (answerCounter ==1){
                    g.drawImage(correct, 150, 600, 70, 70, null);
                }
                else if(answerCounter ==2){
                    g.drawImage(correct, 300, 600, 70, 70, null);
                }
                else if(answerCounter ==3){
                    g.drawImage(correct, 450, 600, 70, 70, null);
                }
                else if(answerCounter ==4){
                    g.drawImage(correct, 600, 600, 70, 70, null);
                }
                else if(answerCounter ==5){
                    g.drawImage(correct, 750, 600, 70, 70, null);
                }
                else if(answerCounter ==6){
                    g.drawImage(empty, 150, 550, 70, 70, null);
                    g.drawImage(empty, 300, 550, 70, 70, null);
                    g.drawImage(empty, 450, 550, 70, 70, null);
                    g.drawImage(empty, 600, 550, 70, 70, null);
                    g.drawImage(empty, 750, 550, 70, 70, null);
                }
                if (answerCounter ==7){
                    g.drawImage(correct, 150, 600, 70, 70, null);
                }
                else if(answerCounter ==8){
                    g.drawImage(correct, 300, 600, 70, 70, null);
                }
                else if(answerCounter ==9){
                    g.drawImage(correct, 450, 600, 70, 70, null);
                }
                else if(answerCounter ==10){
                    g.drawImage(correct, 600, 600, 70, 70, null);
                }
                else if(answerCounter ==11){
                    g.drawImage(correct, 750, 600, 70, 70, null);
                }
                else if(answerCounter ==12){
                    g.drawImage(empty, 150, 550, 70, 70, null);
                    g.drawImage(empty, 300, 550, 70, 70, null);
                    g.drawImage(empty, 450, 550, 70, 70, null);
                    g.drawImage(empty, 600, 550, 70, 70, null);
                    g.drawImage(empty, 750, 550, 70, 70, null);
                }
                if (answerCounter ==13){
                    g.drawImage(correct, 150, 600, 70, 70, null);
                }
                else if(answerCounter ==14){
                    g.drawImage(correct, 300, 600, 70, 70, null);
                }
                else if(answerCounter ==15){
                    g.drawImage(correct, 450, 600, 70, 70, null);
                }
                else if(answerCounter ==16){
                    g.drawImage(correct, 600, 600, 70, 70, null);
                }
                else if(answerCounter ==17){
                    g.drawImage(correct, 750, 600, 70, 70, null);
                }
                else if(answerCounter ==18) {
                    g.drawImage(empty, 150, 550, 70, 70, null);
                    g.drawImage(empty, 300, 550, 70, 70, null);
                    g.drawImage(empty, 450, 550, 70, 70, null);
                    g.drawImage(empty, 600, 550, 70, 70, null);
                    g.drawImage(empty, 750, 550, 70, 70, null);
                }
                correctBool = false;
            }
            if (!correctBool) {
                if (answerCounter == 1) {
                    g.drawImage(wrong, 150, 600, 70, 70, null);
                } else if (answerCounter == 2) {
                    g.drawImage(wrong, 300, 600, 70, 70, null);
                } else if (answerCounter == 3) {
                    g.drawImage(wrong, 450, 600, 70, 70, null);
                } else if (answerCounter == 4) {
                    g.drawImage(wrong, 600, 600, 70, 70, null);
                } else if (answerCounter == 5) {
                    g.drawImage(wrong, 750, 600, 70, 70, null);
                } else if (answerCounter == 6) {
                    g.drawImage(empty, 150, 550, 70, 70, null);
                    g.drawImage(empty, 300, 550, 70, 70, null);
                    g.drawImage(empty, 450, 550, 70, 70, null);
                    g.drawImage(empty, 600, 550, 70, 70, null);
                    g.drawImage(empty, 750, 550, 70, 70, null);
                }
                if (answerCounter == 7) {
                    g.drawImage(wrong, 150, 600, 70, 70, null);
                } else if (answerCounter == 8) {
                    g.drawImage(wrong, 300, 600, 70, 70, null);
                } else if (answerCounter == 9) {
                    g.drawImage(wrong, 450, 600, 70, 70, null);
                } else if (answerCounter == 10) {
                    g.drawImage(wrong, 600, 600, 70, 70, null);
                } else if (answerCounter == 11) {
                    g.drawImage(wrong, 750, 600, 70, 70, null);
                } else if (answerCounter == 12) {
                    g.drawImage(empty, 150, 550, 70, 70, null);
                    g.drawImage(empty, 300, 550, 70, 70, null);
                    g.drawImage(empty, 450, 550, 70, 70, null);
                    g.drawImage(empty, 600, 550, 70, 70, null);
                    g.drawImage(empty, 750, 550, 70, 70, null);
                }
                if (answerCounter == 13) {
                    g.drawImage(wrong, 150, 600, 70, 70, null);
                } else if (answerCounter == 14) {
                    g.drawImage(wrong, 300, 600, 70, 70, null);
                } else if (answerCounter == 15) {
                    g.drawImage(wrong, 450, 600, 70, 70, null);
                } else if (answerCounter == 16) {
                    g.drawImage(wrong, 600, 600, 70, 70, null);
                } else if (answerCounter == 17) {
                    g.drawImage(wrong, 750, 600, 70, 70, null);
                } else if (answerCounter == 18) {
                    g.drawImage(empty, 150, 550, 70, 70, null);
                    g.drawImage(empty, 300, 550, 70, 70, null);
                    g.drawImage(empty, 450, 550, 70, 70, null);
                    g.drawImage(empty, 600, 550, 70, 70, null);
                    g.drawImage(empty, 750, 550, 70, 70, null);
                }
            }
            correctness = 12;
            if (correctness >= 9){
                currentPlayer.money = currentPlayer.money + 100;
                System.out.println("Money for Tim: " + currentPlayer.money);
            }
            answerCounter = 18;
            if (answerCounter == 18){
                saveRecord(currentPlayer.name, currentPlayer.password, String.valueOf(currentPlayer.money), "/Users/blakeankner/Desktop/programming/computer_programming/8BitCasino/Casino Data.csv" );
                gameState = 0;
            }
        }

        //leave these two lines of code as the last lines of the render( ) method
        g.dispose();
        bufferStrategy.show();
    }

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {

        //for the moment we will loop things forever.
        while (true) {

            moveThings();  //move all the game objects
            checkIntersections();
            render();  // paint the graphics
            pause(20); // sleep for 10 ms

//            If log out
            if (logOut) {
                frame.setVisible(false);
                passwordText.setText("");
                userText.setText("");
                succesLabel.setText("");
                frameLog.setVisible(true);
                logOut = false;
            }
        }
    }

    public static void readRecordBySplit(String filePath) {
        {
            String line = "";
            String splitBy = ",";
            try {
//parsing a CSV file into BufferedReader class constructor
                BufferedReader br = new BufferedReader(new FileReader(filePath));
                while ((line = br.readLine()) != null)   //returns a Boolean value
                {
                    character = line.split(splitBy);    // use comma as separator
                    players.add(new User(character[0], character[1], parseInt(character[2])));
//               System.out.println("Character [PlayerName=" + character[0] + ", Password=" + character[1] + ", Money=" + character[2] + "]");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//      for (User u: players){
//         System.out.println(u.name + " " + u.password + " " + u.money);
//      }
    }

    public static void readRecord(String filePath) {
        try {
            //parsing a CSV file into Scanner class constructor
            Scanner sc = new Scanner(new File(filePath));
            sc.useDelimiter(",");   //sets the delimiter pattern
            while (sc.hasNext())  //returns a boolean value
            {
                System.out.print(sc.next());  //find and returns the next complete token from this scanner
            }
            sc.close();  //closes the scanner
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void saveRecord(String name, String password, String money, String filePath) {

        try {
            FileWriter fw = new FileWriter(filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(name + "," + password + "," + money);
            pw.flush();
            pw.close();
        } catch (Exception E) {
        }
    }

    public static int searchRecord(String pName) {
        int counter = 0;
        int index = -1;
        for (User u : players) {
            if (u.name.equals(pName)) {
                index = counter;
            }
            counter++;
        }
        return index;
    }

    public void moveThings() {
        currentPlayer.move();
    }

    public void checkIntersections() {

//      Boundaries for Caino Lobby
        for (int i = 0; i < recs.length; i++) {
            if (currentPlayer.rec.intersects(noGoRectangles[i])) {
                if (currentPlayer.right) {
                    currentPlayer.xpos = currentPlayer.xpos - 5;
                }
                if (currentPlayer.left) {
                    currentPlayer.xpos = currentPlayer.xpos + 5;
                }
                if (currentPlayer.down) {
                    currentPlayer.ypos = currentPlayer.ypos - 5;
                }
                if (currentPlayer.up) {
                    currentPlayer.ypos = currentPlayer.ypos + 5;
                }
            }
        }
//      this is to turn off the chair highlight
        if (!currentPlayer.rec.intersects(chairExample)) {
            highlightThisChair = false;
        }
//      Black Jack Chair intersections
        for (int i = 0; i < blackJackChairRecs.length; i++) {
            if (currentPlayer.rec.intersects(chairBlackJack[i]) && !mainScreen) {
                chairIntersectedX = blackJackChairRecs[i][0];
                chairIntersectedY = blackJackChairRecs[i][1];
                highlightThisChair = true;
            }
//         if (!Blake.rec.intersects(chairBlackJack[i])){
//            highlightThisChair = false;
//         }
        }
//     Slots Chair intersections
        for (int i = 0; i < slotsChairRecs.length; i++) {
            if (currentPlayer.rec.intersects(chairSlots[i]) && !mainScreen) {
                chairIntersectedX = slotsChairRecs[i][0];
                chairIntersectedY = slotsChairRecs[i][1];
                highlightThisChair = true;
            }
        }
//      Poker Chair intersections
        for (int i = 0; i < pokerChairRecs.length; i++) {
            if (currentPlayer.rec.intersects(chairPoker[i]) && !mainScreen) {
                chairIntersectedX = pokerChairRecs[i][0];
                chairIntersectedY = pokerChairRecs[i][1];
                highlightThisChair = true;
            }
        }
//      Store Chair intersections
        for (int i = 0; i < storeChairRecs.length; i++) {
            if (currentPlayer.rec.intersects(chairStore[i]) && !mainScreen) {
                chairIntersectedX = storeChairRecs[i][0];
                chairIntersectedY = storeChairRecs[i][1];
                highlightThisChair = true;
            }
        }
//      PathFinder Chair intersections
        for (int i = 0; i < pathFinderChairRecs.length; i++) {
            if (currentPlayer.rec.intersects(chairPathFinder[i]) && !mainScreen) {
                chairIntersectedX = pathFinderChairRecs[i][0];
                chairIntersectedY = pathFinderChairRecs[i][1];
                highlightThisChair = true;
            }
        }
//      Solitare Chair intersections
        for (int i = 0; i < solitareChairRecs.length; i++) {
            if (currentPlayer.rec.intersects(chairSolitare[i]) && !mainScreen) {
                chairIntersectedX = solitareChairRecs[i][0];
                chairIntersectedY = solitareChairRecs[i][1];
                highlightThisChair = true;
            }
        }


//      Enter and exit through the rug
        if (rugRectangle.contains(currentPlayer.xpos, currentPlayer.ypos) && mainScreenKey) {
            mainScreen = true;
        }
    }

    //Graphics setup method
    private void setUpGraphics() {
        frame = new JFrame("8Bit Casino");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(false);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        char key = e.getKeyChar(); //gets the character of the key pressed
        int keyCode = e.getKeyCode(); //gets the keyCode (an integer) of the key pressed

        if (keyCode == KeyEvent.VK_W) { //move up
            currentPlayer.up = true;
//         System.out.println("UP");
        }
        if (keyCode == KeyEvent.VK_S) { //move down
            currentPlayer.down = true;
//         System.out.println("DOWN");
        }
        if (keyCode == KeyEvent.VK_A) { //move left
            currentPlayer.left = true;
//         System.out.println("LEFT");
        }
        if (keyCode == KeyEvent.VK_D) { //move right
            currentPlayer.right = true;
//         System.out.println("RIGHT");
        }
        if (keyCode == KeyEvent.VK_ENTER) { //press enter key when on the rug to go too casino main screen
            mainScreenKey = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar(); //gets the character of the key pressed
        int keyCode = e.getKeyCode(); //gets the keyCode (an integer) of the key pressed

        if (keyCode == KeyEvent.VK_W) {
            currentPlayer.up = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            currentPlayer.down = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            currentPlayer.left = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            currentPlayer.right = false;
        }
        if (keyCode == KeyEvent.VK_ENTER) {
            mainScreenKey = false;
        }
    }


    public void mouseClicked(MouseEvent e) {
        int x, y;
        x = e.getX();
        y = e.getY();

        mouseX = x;
        mouseY = y;
        System.out.println("Mouse Clicked at " + x + ", " + y);

//      When ontop of a chair & it is highlighted, when clicked, teleport into that game:
//      Black Jack
        for (int i = 0; i < blackJackChairRecs.length; i++) {
            if (chairBlackJack[i].contains(mouseX, mouseY) && highlightThisChair) {
                gameState = 1;
                System.out.println("game state 1");
            }
        }
//      Slots
        for (int i = 0; i < slotsChairRecs.length; i++) {
            if (chairSlots[i].contains(mouseX, mouseY) && highlightThisChair) {
                System.out.println("SLOTS TIME");
            }
        }
//      Poker
        for (int i = 0; i < pokerChairRecs.length; i++) {
            if (chairPoker[i].contains(mouseX, mouseY) && highlightThisChair) {
                System.out.println("POKER TIME");
            }
        }
//      Store
        for (int i = 0; i < storeChairRecs.length; i++) {
            if (chairStore[i].contains(mouseX, mouseY) && highlightThisChair) {
                System.out.println("STORE TIME");
            }
        }
//      Path Finder
        for (int i = 0; i < pathFinderChairRecs.length; i++) {
            if (chairPathFinder[i].contains(mouseX, mouseY) && highlightThisChair) {
                System.out.println("PATH FINDER TIME");
            }
        }
//      Solitare
        for (int i = 0; i < solitareChairRecs.length; i++) {
            if (chairSolitare
                    [i].contains(mouseX, mouseY) && highlightThisChair) {
                System.out.println("SOLITARE TIME");
            }
        }

//      Casino Main Screen doors clicked go into lobby
        if (mainScreenDooorRect.contains(mouseX, mouseY) && mainScreen) {
            mainScreen = false;
        }

//      Log Out
        if (logOutRec.contains(mouseX, mouseY) && mainScreen) {
            logOut = true;
            System.out.println("Log Out");
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        if (user.equals("Blake") && password.equals("hey")) {
            succesLabel.setText("Login succesful! Welcome " + user);
            frame.setVisible(true);
        } else {
            succesLabel.setText("");
            unsucsessfulLabel.setText("Incorrect try again.");
        }
    }
}