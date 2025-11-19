package fpg;
import fpg.Game.ChoiceHandler; 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.*;
import java.util.Random;
import javax.swing.*;

/*
 * Creates a GUI for the game specifically the starting menu, battle screen, game over, game win & instructions
 * 
 * @mansoor zafar
 * @2022-06-12
 */
public class GUI{
	//Instance Variables
	
	JFrame window; // for the main frame
	
	JTextField jtf;
	
	JPanel titleNamePanel, startButtonPanel,GameOverPanel, cardOne, cardTwo, gameWinPanel, backButtonPanel, 
	cardThree, cardFour, hpPanel, ehpPanel, mainGamePanel, retryButtonPanel, scorePanel, InstructionButtonPanel, pointPanel, 
	codeButtonPanel, highestScorePanel, shopButtonPanel, shopButtonsPanel; // all of the panels for the frame
	
	JLabel titleNameLabel,GameOverLabel, c1TextV1, c1TextV2, c2TextV1, c2TextV2, startButtonLabel, 
	c3TextV1, c3TextV2, c4TextV1, c4TextV2, c5TextV1, c5TextV2, c6TextV1, c6TextV2, backLabel,signupLabel,
	cardOneTextV1, cardOneTextV2, cardTwoTextV1, cardTwoTextV2, cardThreeTextV1, scoreLabel, InstructionLabel,
	cardThreeTextV2, cardFourTextV1, cardFourTextV2, hpLabel, goLabel, ehpLabel, gameWinLabel, retryButtonLabel,
	ILabel1, ILabel2, ILabel3, ILabel4, ILabel5, ILabel6, ILabel7, ILabel8, highestScoreLabel, pointLabel, shopButtonLabel; // all of the text 
	
	JButton startButton, c1, c2, c3, c4, c5, c6, goButton, retryButton, InstructionButton, backButton,
	codeButton, shopButton, shopButtons; // buttons to press
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);  // font for titles
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 25); // font for anything else besides card and titles
	Font cardFont = new Font("Times New Roman", Font.PLAIN, 15); // font for cards
	Card card1 = new Card(); // 7 atk 3 cd // first card
	Card card2 = new Card(); // 5 atk 2 cd // second card
	Card card3 = new Card(); // 3 atk 1 cd // third card
	Card card4 = new Card(); // 12 atk 5 cd // fourth card
	Card card5 = new Card(); // 5 heal 4 cd  // fifth card
	Card card6 = new Card(); // 3 heal 2 cd  // sixth card
	Random rand = new Random(); // random number generator
	int health = 50; // set player health to 50
	String Echoice1; // enemy choice 1
	String Echoice2; // enemy choice 2
	int ehealth = 100; // temporary enemy health
	int ehealthMax; // the max health an enemy can have
	Font iFont = new Font("Times New Roman", Font.PLAIN, 20); // font
	File f = new File("C://Users//mansoor//Desktop"); // file?
	Links ll = new Links(); // link
	
	
	/*
	 * REFERENCE COMMANDS: THESE ARE REPEATED IN THE ENTIRE GUI CLASS
	 * .setSize() / setBounds() --> Sets the size or bounds
	 * .setBackground(Color.~~~) / setForeground(Color.~~~) --> sets either the background or foreground to the color chosen
	 * .setFont() --> sets the font of the text to the one chosen
	 * .setFocusPainted() --> removes or adds the blue ring around a button
	 * .add() --> adds the panel/button/label, etc to the window/panel/button etc
	 * .addActionListener(~~~) --> sends action happened to cHandler/ whatever is the parameter 
	 * .setActionCommand() --> sets the name for the action command 
	 * .setLayout() --> sets the layout
	 * 
	 * 
	 * Input: takes in choice handler
	 * Inside: 1: create window and sets up basic properties 
	 * --> 2: sets up the title 
	 * --> 3: sets up the start button 
	 * --> 4: sets up the first card and first card text 
	 * --> 5: creates two colors, light red and light green
	 * --> 6: same process as 4 for cards 1-6
	 * --> 7: sets up the boxes for the player choice 
	 * --> 8: sets up the boxes for the enemy 
	 * --> 9: picks the enemies cards
	 * --> 10: sets up the health label
	 * --> 11: sets up the enemy health label
	 * --> 12: sets up the go button & panel
	 * --> 13: sets up the game over panel
	 * --> 14: sets up game win panel
	 * --> 15: sets up retry button & panel
	 * --> 16: sets up instruction button & panel
	 * --> 17: sets up back button
	 * --> 18: sets up the instructions 1-8
	 * --> 19: creates the score panel
	 * --> 20: makes the highest score panel
	 * --> 21: find the highest score in the links
	 * --> 22: sets up the code button for developer-mode/"ciel"
	 * --> 23: set up points panel 
	 * --> 24: make a shop menu
	 * --> 25: make the shop items
	 * --> 26: add everything to the main window/frame
	 * --> 27: make the window/frame visible 
	 * Output: the GUI
	 */
	public void createGUI(ChoiceHandler cHandler) {
		//Window
		window = new JFrame();
		window.setSize(1000,800);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		
		//Title Name Panel
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100,100,800,200);
		titleNamePanel.setBackground(Color.black);
		
		//Title Name Label
		titleNameLabel = new JLabel("C I E L");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);
		
		titleNamePanel.add(titleNameLabel);
		
		//Start Button Panel
		
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(300,400,400,100);
		startButtonPanel.setBackground(Color.black);
		
		//Start Button
		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(normalFont);
		startButton.setFocusPainted(false);
		startButton.addActionListener(cHandler);
		startButton.setActionCommand("start");
		
		startButtonPanel.add(startButton);
		
		//Card Boxes
		
		//First card
		card1.makeCard(7, 0, 0, 0); 
		
		if(Game.ciel == true) {
			card1.setAttack(999999);
		}
		Color VERY_LIGHT_RED = new Color(255,102,102);
		Color VERY_LIGHT_GREEN = new Color(102,255,102);
		c1 = new JButton();
		c1.setBounds(25, 600, 75, 75);
		c1.setBackground(VERY_LIGHT_RED);
		c1.setLayout(new BorderLayout());
		c1TextV1 = new JLabel("atk: " + card1.getAttack());
		c1TextV1.setFont(cardFont);
		c1TextV2 = new JLabel("cd: " + card1.getCooldown());
		c1TextV2.setFont(cardFont);
		c1.add(BorderLayout.NORTH,c1TextV1);
		c1.add(BorderLayout.SOUTH,c1TextV2);
		
		c1.setOpaque(true);
		c1.setForeground(Color.cyan);
		c1.setFont(cardFont);
		c1.setFocusPainted(false);
		c1.addActionListener(cHandler);
		c1.setActionCommand("cardUno");
		
		//2nd Card
		card2.makeCard(5,0,0,0); // att 5 cd 2 
		if(Game.ciel == true) {
			card2.setAttack(999999);
		}
		
		c2 = new JButton();
		c2.setBounds(125, 600, 75, 75);
		c2.setBackground(VERY_LIGHT_RED);
		c2.setLayout(new BorderLayout());
		c2TextV1 = new JLabel("atk: " + card2.getAttack());
		c2TextV1.setFont(cardFont);
		c2TextV2 = new JLabel("cd: " + card2.getCooldown());
		c2TextV2.setFont(cardFont);
		c2.add(BorderLayout.NORTH,c2TextV1);
		c2.add(BorderLayout.SOUTH,c2TextV2);
		
		c2.setOpaque(true);
		c2.setForeground(Color.cyan);
		c2.setFont(cardFont);
		c2.setFocusPainted(false);
		c2.addActionListener(cHandler);
		c2.setActionCommand("cardDos");
		
		//3rd Card
		card3.makeCard(3, 0, 0, 0);
		if(Game.ciel == true) {
			card3.setAttack(999999);
		}
		
		c3 = new JButton();
		c3.setBounds(225, 600, 75, 75);
		c3.setBackground(VERY_LIGHT_RED);
		c3.setLayout(new BorderLayout());
		c3TextV1 = new JLabel("atk: " + card3.getAttack());
		c3TextV1.setFont(cardFont);
		c3TextV2 = new JLabel("cd: " + card3.getCooldown());
		c3TextV2.setFont(cardFont);
		c3.add(BorderLayout.NORTH,c3TextV1);
		c3.add(BorderLayout.SOUTH,c3TextV2);
		
		c3.setOpaque(true);
		c3.setForeground(Color.cyan);
		c3.setFont(cardFont);
		c3.setFocusPainted(false);
		c3.addActionListener(cHandler);
		c3.setActionCommand("cardTres");
		
		//4th Card
		card4.makeCard(12, 0, 0, 0);
		if(Game.ciel == true) {
			card4.setAttack(999999);
		}
		
		c4 = new JButton();
		c4.setBounds(325, 600, 75, 75);
		c4.setBackground(VERY_LIGHT_RED);
		c4.setLayout(new BorderLayout());
		c4TextV1 = new JLabel("atk: " + card4.getAttack());
		c4TextV1.setFont(cardFont);
		c4TextV2 = new JLabel("cd: " + card4.getCooldown());
		c4TextV2.setFont(cardFont);
		c4.add(BorderLayout.NORTH,c4TextV1);
		c4.add(BorderLayout.SOUTH,c4TextV2);
		
		c4.setOpaque(true);
		c4.setForeground(Color.cyan);
		c4.setFont(cardFont);
		c4.setFocusPainted(false);
		c4.addActionListener(cHandler);
		c4.setActionCommand("cardCuatro");
		
		//5th Card
		
		card5.makeCard(0, 0, 0, 5);
		if(Game.ciel == true) {
			card5.setHp(999999,0);
		}
		
		c5 = new JButton();
		c5.setBounds(425, 600, 75, 75);
		c5.setLayout(new BorderLayout());
		c5.setBackground(VERY_LIGHT_GREEN);
		c5TextV1 = new JLabel("heal: " + card5.getHp());
		c5TextV1.setFont(cardFont);
		c5TextV2 = new JLabel("cd: " + card5.getCooldown());
		c5TextV2.setFont(cardFont);
		c5.add(BorderLayout.NORTH,c5TextV1);
		c5.add(BorderLayout.SOUTH,c5TextV2);
		
		c5.setOpaque(true);
		c5.setForeground(Color.cyan);
		c5.setFont(cardFont);
		c5.setFocusPainted(false);
		c5.addActionListener(cHandler);
		c5.setActionCommand("cardCinco");
		
		//6th Card
		card6.makeCard(0, 0, 0, 3);
		if(Game.ciel == true) {
			card6.setHp(999999,0);
		}
		
		c6 = new JButton();
		c6.setBounds(525, 600, 75, 75);
		c6.setBackground(VERY_LIGHT_GREEN);
		c6.setLayout(new BorderLayout());
		c6TextV1 = new JLabel("heal: " + card6.getHp());
		c6TextV1.setFont(cardFont);
		c6TextV2 = new JLabel("cd: " + card6.getCooldown());
		c6TextV2.setFont(cardFont);
		c6.add(BorderLayout.NORTH,c6TextV1);
		c6.add(BorderLayout.SOUTH,c6TextV2);
		
		c6.setOpaque(true);
		c6.setForeground(Color.cyan);
		c6.setFont(cardFont);
		c6.setFocusPainted(false);
		c6.addActionListener(cHandler);
		c6.setActionCommand("cardSeis");
		
		//card one player choice
		cardOne = new JPanel();
		cardOne.setBounds(25,500,75,75);
		cardOne.setBackground(Color.white);
		cardOne.setLayout(new BorderLayout());
		cardOneTextV1 = new JLabel();
		cardOneTextV1.setFont(normalFont);
		cardOneTextV2 = new JLabel();
		cardOneTextV2.setFont(normalFont);
		cardOne.add(BorderLayout.NORTH,cardOneTextV1);
		cardOne.add(BorderLayout.SOUTH,cardOneTextV2);
		cardOne.setOpaque(true);

		
		//Card two enemy choice
		
		cardTwo = new JPanel();
		cardTwo.setBounds(115,500,75,75);
		cardTwo.setBackground(Color.white);
		cardTwo.setLayout(new BorderLayout());
		cardTwoTextV1 = new JLabel();
		cardTwoTextV1.setFont(normalFont);
		cardTwoTextV1.setForeground(Color.red);
		cardTwoTextV2 = new JLabel();
		cardTwoTextV2.setFont(normalFont);
		cardTwoTextV2.setForeground(Color.red);
		
		int ran1 = rand.nextInt(6) + 1;

		switch(ran1){
		case 1: cardTwoTextV1.setText("atk: " + card1.getAttack()); 
		cardTwoTextV2.setText("cd: " + card1.getCooldown()); 
		Echoice1 = "cardUno"; break;
		case 2: cardTwoTextV1.setText("atk: " + card2.getAttack());
		cardTwoTextV2.setText("cd: " + card2.getCooldown());
		Echoice1 = "cardDos"; break; 
		case 3: cardTwoTextV1.setText("atk: " + card3.getAttack());
		cardTwoTextV2.setText("cd: " + card3.getCooldown()); 
		Echoice1 = "cardTres"; break; 
		case 4: cardTwoTextV1.setText("atk: " + card4.getAttack());
		cardTwoTextV2.setText("cd: " + card4.getCooldown()); 
		Echoice1 = "cardCuatro"; break; 
		case 5: cardTwoTextV1.setText("heal: " + card5.getHp());
		cardTwoTextV2.setText("cd: " + card5.getCooldown()); 
		Echoice1 = "cardCinco"; break; 
		case 6: cardTwoTextV1.setText("heal: " + card6.getHp());
		cardTwoTextV2.setText("cd: " + card6.getCooldown()); 
		Echoice1 = "cardSeis"; break;
		}
		
		cardTwo.add(BorderLayout.NORTH,cardTwoTextV1);
		cardTwo.add(BorderLayout.SOUTH,cardTwoTextV2);
		cardTwo.setOpaque(true);
		cardTwo.setForeground(Color.red);
		
		//Card three player choice
		cardThree = new JPanel();
		cardThree.setBounds(225,500,75,75);
		cardThree.setBackground(Color.white);
		cardThree.setLayout(new BorderLayout());
		cardThreeTextV1 = new JLabel();
		cardThreeTextV1.setFont(normalFont);
		cardThreeTextV2 = new JLabel();
		cardThreeTextV2.setFont(normalFont);
		cardThree.add(BorderLayout.NORTH,cardThreeTextV1);
		cardThree.add(BorderLayout.SOUTH,cardThreeTextV2);
		cardThree.setOpaque(true);
		
		//Card four enemy choice
		cardFour = new JPanel();
		cardFour.setBounds(325,500,75,75);
		cardFour.setBackground(Color.white);
		cardFour.setLayout(new BorderLayout());
		cardFourTextV1 = new JLabel();
		cardFourTextV1.setFont(normalFont);
		cardFourTextV1.setForeground(Color.red);
		cardFourTextV2 = new JLabel();
		cardFourTextV2.setFont(normalFont);
		cardFourTextV2.setForeground(Color.red);
		
		int ran2 = rand.nextInt(6) + 1;
			
		if(ran2 == ran1){
			while(ran2 == ran1){
				ran2 = rand.nextInt(6) + 1;
			}
		}
		
		switch(ran2){
		case 1: cardFourTextV1.setText("atk: " + card1.getAttack()); 
		cardFourTextV2.setText("cd: " + card1.getCooldown()); 
		Echoice2 = "cardUno"; break;
		case 2: cardFourTextV1.setText("atk: " + card2.getAttack());
		cardFourTextV2.setText("cd: " + card2.getCooldown()); 
		Echoice2 = "cardDos"; break; 
		case 3: cardFourTextV1.setText("atk: " + card3.getAttack());
		cardFourTextV2.setText("cd: " + card3.getCooldown()); 
		Echoice2 = "cardTres"; break; 
		case 4: cardFourTextV1.setText("atk: " + card4.getAttack());
		cardFourTextV2.setText("cd: " + card4.getCooldown()); 
		Echoice2 = "cardCuatro"; break; 
		case 5: cardFourTextV1.setText("heal: " + card5.getHp());
		cardFourTextV2.setText("cd: " + card5.getCooldown()); 
		Echoice2 = "cardCinco"; break; 
		case 6: cardFourTextV1.setText("heal: " + card6.getHp());
		cardFourTextV2.setText("cd: " + card6.getCooldown()); 
		Echoice2 = "cardSeis"; break;
		}
		
		cardFour.add(BorderLayout.NORTH,cardFourTextV1);
		cardFour.add(BorderLayout.SOUTH,cardFourTextV2);
		cardFour.setOpaque(true);
		cardFour.setForeground(Color.red);
		
		//Hp label
		
		hpPanel = new JPanel();
		hpPanel.setBounds(840,50,200,100);
		hpPanel.setBackground(Color.black);
		hpPanel.setLayout(new BorderLayout());
		hpLabel = new JLabel();
		hpLabel.setFont(normalFont);
		hpLabel.setForeground(Color.red);
		hpLabel.setText("Your HP : " + getHP());
		
		hpPanel.add(BorderLayout.CENTER,hpLabel);
		hpPanel.setOpaque(true);
		hpPanel.setForeground(Color.red);
		
		//enemy hp label & panel
		
		ehpPanel = new JPanel();
		ehpPanel.setBounds(0,0,200,75);
		ehpPanel.setBackground(Color.black);
		ehpPanel.setLayout(new BorderLayout());
		ehpLabel = new JLabel();
		ehpLabel.setFont(normalFont);
		ehpLabel.setForeground(Color.red);
		ehpLabel.setText("Enemy HP : " + getHPE());
		
		ehpPanel.add(BorderLayout.NORTH,ehpLabel);
		ehpPanel.setOpaque(true);
		ehpPanel.setForeground(Color.red);
		
		// "go" for battle button
		
		goButton = new JButton();
		goButton.setBounds(750, 600, 125, 50);
		goButton.setLayout(new BorderLayout());
		goLabel = new JLabel("Go");
		goLabel.setFont(normalFont);
		goButton.add(BorderLayout.CENTER,goLabel);
		
		goButton.setOpaque(true);
		goButton.setForeground(Color.cyan);
		goButton.setFont(cardFont);
		goButton.setFocusPainted(false);
		goButton.addActionListener(cHandler);
		goButton.setActionCommand("go");
		
		//Game Over Panel
		
		GameOverPanel = new JPanel();
		GameOverPanel.setBounds(100,100,800,200);
		GameOverPanel.setBackground(Color.black);
				
		//Game Win Label
		GameOverLabel = new JLabel("u suck LMFAOOO");
		GameOverLabel.setForeground(Color.white);
		GameOverLabel.setFont(titleFont);		
		
		//Add label to panel
		GameOverPanel.add(GameOverLabel);
		
		//Game Win Panel
		
		gameWinPanel = new JPanel();
		gameWinPanel.setBounds(100,100,800,600);
		gameWinPanel.setBackground(Color.black);
				
		//Game Win Label
		gameWinLabel = new JLabel("goated?!");
		gameWinLabel.setForeground(Color.white);
		gameWinLabel.setFont(titleFont);		
		
		//Add label to panel
		gameWinPanel.add(gameWinLabel);
		
		
		//Adding retry Button
		
		//Start Button Panel
		retryButtonPanel = new JPanel();
		retryButtonPanel.setBounds(300,400,400,100);
		retryButtonPanel.setBackground(Color.black);
				
				//retry Button
		retryButton = new JButton("Retry?");
		retryButton.setBackground(Color.black);
		retryButton.setForeground(Color.white);
		retryButton.setFont(normalFont);
		retryButton.setFocusPainted(false);
		retryButton.addActionListener(cHandler);
		retryButton.setActionCommand("retry");
				
		retryButtonPanel.add(retryButton);
		
		
		// Instruction Button
		
		InstructionButtonPanel = new JPanel();
		InstructionButtonPanel.setBounds(-20,700,200,100);
		InstructionButtonPanel.setBackground(Color.black);
		
		//Instruction Button
		InstructionButton = new JButton("Instructions");
		InstructionButton.setBackground(Color.black);
		InstructionButton.setForeground(Color.white);
		InstructionButton.setFont(normalFont);
		InstructionButton.setFocusPainted(false);
		InstructionButton.addActionListener(cHandler);
		InstructionButton.setActionCommand("instruction");
		
		InstructionButtonPanel.add(InstructionButton);
		
		// Instruction Button
		
		backButtonPanel = new JPanel();
		backButtonPanel.setBounds(-50,700,200,100);
		backButtonPanel.setBackground(Color.black);
				
		//Instruction Button
		backButton = new JButton("Back");
		backButton.setBackground(Color.black);
		backButton.setForeground(Color.white);
		backButton.setFont(normalFont);
		backButton.setFocusPainted(false);
		backButton.addActionListener(cHandler);
		backButton.setActionCommand("back");
				
		backButtonPanel.add(backButton);
		
		// ADDING THE INSTRUCTION LABELS
		
		ILabel1 = new JLabel();
		ILabel1.setBounds(3,0,900,50);
		ILabel1.setText("In this game you will be traversing rooms to reach the stairs.");
		ILabel1.setForeground(Color.white);
		ILabel1.setFont(iFont);
		
		ILabel2 = new JLabel();
		ILabel2.setBounds(3,50,900,50);
		ILabel2.setText("Each time you reach the stairs your score increases by 1.");
		ILabel2.setForeground(Color.white);
		ILabel2.setFont(iFont);
		
		ILabel3 = new JLabel();
		ILabel3.setBounds(3,100,900,50);
		ILabel3.setText("If you encounter an enemy, you will have to combat it causing the room to change.");
		ILabel3.setForeground(Color.white);
		ILabel3.setFont(iFont);
		
		ILabel4 = new JLabel();
		ILabel4.setBounds(3,150,900,50);
		ILabel4.setText("The game goes on until your HP reaches 0. You can press Escape during exploration to return to the title Screen");
		ILabel4.setForeground(Color.white);
		ILabel4.setFont(iFont);
		
		ILabel5 = new JLabel();
		ILabel5.setBounds(3,200,900,50);
		ILabel5.setText("In combat, you will have the option of choosing certain cards while seeing what the opponent picked.");
		ILabel5.setForeground(Color.white);
		ILabel5.setFont(iFont);
		
		ILabel6 = new JLabel();
		ILabel6.setBounds(3,250,900,50);
		ILabel6.setText("After choosing that card, the card will go on cooldown, preventing you from using it again.");
		ILabel6.setForeground(Color.white);
		ILabel6.setFont(iFont);
		
		ILabel7 = new JLabel();
		ILabel7.setBounds(3,300,900,50);
		ILabel7.setText("Once the cooldown reaches 0, you can use it again.");
		ILabel7.setForeground(Color.white);
		ILabel7.setFont(iFont);
		
		ILabel8 = new JLabel();
		ILabel8.setBounds(3,350,900,50);
		ILabel8.setText("Use the arrow keys to move : UP, DOWN, LEFT, RIGHT OR W, A, S, D");
		ILabel8.setForeground(Color.white);
		ILabel8.setFont(iFont);
		
		//Score
		
		//SCORE Panel
		scorePanel = new JPanel();
		scorePanel.setBounds(870,0,100,100);
		scorePanel.setBackground(Color.black);
		
		//SCORELabel
		scoreLabel = new JLabel("Score : " + Game.Score);
		scoreLabel.setForeground(Color.white);
		scoreLabel.setFont(normalFont);
		
		scorePanel.add(scoreLabel);
		
		// highest score panel
		
		//highestScorePanel
		
		highestScorePanel = new JPanel();
		highestScorePanel.setBounds(780,720,200,100);
		highestScorePanel.setBackground(Color.black);
		
		//HIGHEST SCORE Label
		ll.insert(Game.Score);
		ll.findHighestScore();
		highestScoreLabel = new JLabel("Highest Score : " + ll.getHighestScore());
		highestScoreLabel.setForeground(Color.white);
		highestScoreLabel.setFont(normalFont);
		highestScorePanel.setOpaque(true);
		highestScorePanel.add(highestScoreLabel);
		
		
		//CODE 
		
		codeButtonPanel = new JPanel();
		codeButtonPanel.setBounds(-50,0,200,100);
		codeButtonPanel.setBackground(Color.black);
		
		//Start Button
		codeButton = new JButton("code?");
		codeButton.setBackground(Color.black);
		codeButton.setForeground(Color.white);
		codeButton.setFont(normalFont);
		codeButton.setFocusPainted(false);
		codeButton.addActionListener(cHandler);
		codeButton.setActionCommand("code");
		
		codeButtonPanel.add(codeButton);
		
		
		//points Panel
		
		pointPanel = new JPanel();
		pointPanel.setBounds(780,690,200,200);
		pointPanel.setBackground(Color.black);
				
		//HIGHEST SCORE Label
		pointLabel = new JLabel("Points : " + Game.points);
		pointLabel.setForeground(Color.white);
		pointLabel.setFont(normalFont);
		pointPanel.setOpaque(true);
		pointPanel.add(pointLabel);
		
		//Shop panel
		shopButtonPanel = new JPanel();
		shopButtonPanel.setBounds(-50,650,200,100);
		shopButtonPanel.setBackground(Color.black);
		
		//Shop Button
		shopButton = new JButton("Shop");
		shopButton.setBackground(Color.black);
		shopButton.setForeground(Color.white);
		shopButton.setFont(normalFont);
		shopButton.setFocusPainted(false);
		shopButton.addActionListener(cHandler);
		shopButton.setActionCommand("shop");
		
		shopButtonPanel.add(shopButton);
		
		
		//SHOP OPTIONS
		
		JButton[][] shopButtons = {{new JButton("Increase atk (10 points)"), new JButton("Increase spd (5 points)")}, 
				{new JButton("Increase max hp (20 points)"), new JButton("Increase heal (3 points)")}};
		
		shopButtonsPanel = new JPanel();
		shopButtonsPanel.setBounds(70,50,800,600);
		shopButtonsPanel.setBackground(Color.black);
		shopButtonsPanel.setLayout(new GridLayout(2,2));
		shopButtons[0][0].setBackground(Color.black);
		shopButtons[0][0].setForeground(Color.white);
		shopButtons[0][0].setFont(normalFont);
		shopButtons[0][0].setFocusPainted(false);
		shopButtons[0][0].setActionCommand("ia");
		shopButtons[0][0].addActionListener(cHandler);
		
		shopButtons[0][1].setBackground(Color.black);
		shopButtons[0][1].setForeground(Color.white);
		shopButtons[0][1].setFont(normalFont);
		shopButtons[0][1].setFocusPainted(false);
		shopButtons[0][1].setActionCommand("is");
		shopButtons[0][1].addActionListener(cHandler);
		
		shopButtons[1][0].setBackground(Color.black);
		shopButtons[1][0].setForeground(Color.white);
		shopButtons[1][0].setFont(normalFont);
		shopButtons[1][0].setFocusPainted(false);
		shopButtons[1][0].setActionCommand("imh");
		shopButtons[1][0].addActionListener(cHandler);
		
		shopButtons[1][1].setBackground(Color.black);
		shopButtons[1][1].setForeground(Color.white);
		shopButtons[1][1].setFont(normalFont);
		shopButtons[1][1].setFocusPainted(false);
		shopButtons[1][1].setActionCommand("ih");
		shopButtons[1][1].addActionListener(cHandler);
		
		shopButtonsPanel.add(shopButtons[0][0]);
		shopButtonsPanel.add(shopButtons[0][1]);
		shopButtonsPanel.add(shopButtons[1][0]);
		shopButtonsPanel.add(shopButtons[1][1]);
		
		jtf = new JTextField();
		jtf.setBackground(Color.white);
		jtf.setBounds(5,50,90,25);
		
		
		//Adding windows
		window.add(titleNamePanel);
		window.add(startButtonPanel);
		window.add(c1);
		window.add(c2);
		window.add(c3);
		window.add(c4);
		window.add(c5);
		window.add(c6);
		window.add(GameOverPanel);
		window.add(cardOne);
		window.add(cardTwo);
		window.add(cardThree);
		window.add(cardFour);
		window.add(hpPanel);
		window.add(goButton);
		window.add(ehpPanel);
		window.add(gameWinPanel);
		window.add(retryButtonPanel);
		window.add(scorePanel);
		window.add(InstructionButtonPanel);
		window.add(backButtonPanel);
		window.add(ILabel1);
		window.add(ILabel2);
		window.add(ILabel3);
		window.add(ILabel4);
		window.add(ILabel5);
		window.add(ILabel6);
		window.add(ILabel7);
		window.add(ILabel8);
		window.add(codeButtonPanel);
		window.add(highestScorePanel);
		window.add(pointPanel);
		window.add(shopButtonPanel);
		window.add(shopButtonsPanel);
		window.add(jtf);
		
		//Making Visible
		window.setVisible(true);
	}	
	
	/*
	 * Input: integer x & integer y
	 * Inside: set the card text to attack + x
	 * Output: set the card text to cool-down + y
	 */
	// for atk
	public void setCardOneText(int x, int y) {
		cardOneTextV1.setText("atk: " + x);
		cardOneTextV2.setText("cd: " + y);
	}
	
	/*
	 * Input: integer x & integer y
	 * Inside: set the card text to attack + x
	 * Output: set the card text to cool-down + y
	 */
	
	/*
	 * Input: integer x & integer y
	 * Inside: set the card text to attack + x
	 * Output: set the card text to cool-down + y
	 */
	// for hp
	public void setCardOneTextH(int x, int y) {
		cardOneTextV1.setText("hp: " + x);
		cardOneTextV2.setText("cd: " + y);
	}
	
	
	public void setCardOneTextBlank() {
		cardOneTextV1.setText("");
		cardOneTextV2.setText("");
	}
	
	public void setCardThreeTextBlank() {
		cardThreeTextV1.setText("");
		cardThreeTextV2.setText("");
	}
	
	/*
	 * Input: integer x & integer y
	 * Inside: set the card text to attack + x
	 * Output: set the card text to cool-down + y
	 */
	// for atk crd 3
	public void setCardThreeText(int x, int y) {
		cardThreeTextV1.setText("atk: " + x);
		cardThreeTextV2.setText("cd: " + y);
	}
	
	/*
	 * Input: integer x & integer y
	 * Inside: set the card text to attack + x
	 * Output: set the card text to cool-down + y
	 */
		// for hp
	public void setCardThreeTextH(int x, int y) {
		cardThreeTextV1.setText("hp: " + x);
		cardThreeTextV2.setText("cd: " + y);
	}
	
	/*
	 * Inside: n/a
	 * Input: check if the background is white
	 * Outside: returns true or false
	 */
	public boolean checkEmptyCard1() {
		// use a case statement to see all possibilities of x
		if(cardOne.getBackground() == Color.white) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/*
	 * Inside: n/a
	 * Input: check if the background is white
	 * Outside: returns true or false
	 */
	public boolean checkEmptyCard2(){
		if(cardThree.getBackground() == Color.white) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * Input: integer x 
	 * Inside: heal the player by x amount of hp
	 * Output: the health of the player
	 */
	//PLAYER HEAL HP
	
	public int healHP(int x) {
		if((health + x)> 50 && Game.ciel == false) {
			// if the developer mode is false && if the player + x is > 50 change it to 50 exact
			health = 50;
			return health;
		}
		else {
			health = health + x;
			return health;
		}
	}
	
	/*
	 * Input: take in integer x
	 * Inside: set health to health minus x
	 * Output: health
	 */
	//PLAYER TAKE DMG
	
	public int damageHP(int x) {
		health = health - x;
		return health;
	}
	
	/*
	 * Input: n/a
	 * Inside: returns the health of the player 
	 * Output: health of player
	 */
	//PLAYER GET HP
	
	public int getHP() {
		return health;
	}
	
	/*
	 * Inside: integer x
	 * Input: set the max enemy health to x
	 * Output: n/a
	 */
	//ENEMY HEAL HP
	
	public void setEnemyHP(int x){
		ehealthMax = x;
	}
	
	/*
	 * Inside: integer x
	 * Input: add the x to enemy health
	 * Output: returns the enemy health
	 */
	public int healHPE(int x) {
		if((ehealth + x)> ehealthMax ) {
			// if the enemy health + x is greater than the max health then set it to max health
			ehealth = ehealthMax;
			return ehealth;
		}
		else {
			ehealth = ehealth + x;
			return ehealth;
		}
	}
	
	/*
	 * Input: integer x
	 * Inside: set the enemy health to enemy health minus x
	 * Output: return the enemy health
	 */
	//ENEMY TAKE DAMAGE
	
	public int damageHPE(int x) {
		ehealth = ehealth - x;
		return ehealth;
	}
	
	/*
	 * Input: n/a
	 * Inside: returns the enemy health
	 * Output: enemy health
	 */
	//ENEMY GET HEALTH
	
	public int getHPE() {
		return ehealth;
	}
	
	/*
	 * Input: n/a
	 * Inside: uses a switch statement to the random numbers to a specific card 
	 * Output: setting the enemy cards
	 */
	public void setECards() {
		int ran1 = rand.nextInt(6) + 1; // takes a number from 1-6

		switch(ran1){
		case 1: cardTwoTextV1.setText("atk: " + card1.getAttack()); 
		cardTwoTextV2.setText("cd: " + card1.getCooldown()); 
		Echoice1 = "cardUno"; break; // if 1 then set enemy first card to card 1
		case 2: cardTwoTextV1.setText("atk: " + card2.getAttack());
		cardTwoTextV2.setText("cd: " + card2.getCooldown());
		Echoice1 = "cardDos"; break; // if 2 then set enemy first card to card 2
		case 3: cardTwoTextV1.setText("atk: " + card3.getAttack());
		cardTwoTextV2.setText("cd: " + card3.getCooldown()); 
		Echoice1 = "cardTres"; break; // if 3 then set enemy first card to card 3
		case 4: cardTwoTextV1.setText("atk: " + card4.getAttack());
		cardTwoTextV2.setText("cd: " + card4.getCooldown()); 
		Echoice1 = "cardCuatro"; break; // if 4 then set enemy first card to card 4
		case 5: cardTwoTextV1.setText("heal: " + card5.getHp());
		cardTwoTextV2.setText("cd: " + card5.getCooldown()); 
		Echoice1 = "cardCinco"; break; // if 5 then set enemy first card to card 5
		case 6: cardTwoTextV1.setText("heal: " + card6.getHp());
		cardTwoTextV2.setText("cd: " + card6.getCooldown()); 
		Echoice1 = "cardSeis"; break; // if 6 then set enemy first card to card 6
		}
		//take in a random number 1-6
		int ran2 = rand.nextInt(6) + 1;
		
		if(ran2 == ran1){
			while(ran2 == ran1){
				ran2 = rand.nextInt(6) + 1; // makes sure the first random number & second is not the same
			}
		}
		
		switch(ran2){
		case 1: cardFourTextV1.setText("atk: " + card1.getAttack()); 
		cardFourTextV2.setText("cd: " + card1.getCooldown()); 
		Echoice2 = "cardUno"; break; // if 1 then set enemy first card to card 1
		case 2: cardFourTextV1.setText("atk: " + card2.getAttack());
		cardFourTextV2.setText("cd: " + card2.getCooldown()); 
		Echoice2 = "cardDos"; break; // if 2 then set enemy first card to card 2
		case 3: cardFourTextV1.setText("atk: " + card3.getAttack());
		cardFourTextV2.setText("cd: " + card3.getCooldown()); 
		Echoice2 = "cardTres"; break; // if 3 then set enemy first card to card 3
		case 4: cardFourTextV1.setText("atk: " + card4.getAttack());
		cardFourTextV2.setText("cd: " + card4.getCooldown()); 
		Echoice2 = "cardCuatro"; break; // if 4 then set enemy first card to card 4
		case 5: cardFourTextV1.setText("heal: " + card5.getHp());
		cardFourTextV2.setText("cd: " + card5.getCooldown()); 
		Echoice2 = "cardCinco"; break; // if 5 then set enemy first card to card 5
		case 6: cardFourTextV1.setText("heal: " + card6.getHp());
		cardFourTextV2.setText("cd: " + card6.getCooldown()); 
		Echoice2 = "cardSeis"; break; // if 6 then set enemy first card to card 6
		}
	}
	
}




