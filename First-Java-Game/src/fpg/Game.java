package fpg;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.*; // for colors
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;
import javax.imageio.*;

/*
 * 
 * FOR CODE YOU HAVE TO TYPE IN THE ECLIPSE INPUT AREA NVM FIXED IT
 * HAVE TO EDIT FILE LOCATION FOR PROGRAM TO WORK
 * Creates a rouge-like game where you can traverse through areas whilst either avoiding or battling enemies to reach the stairs
 * The goal is to go on for as long as your heart desires.
 * 
 * @mansoor zafar
 * @2022-06-12
 */
public class Game {
	 // instance variables
	 ChoiceHandler cHandler = new ChoiceHandler(); // creates the variable for choice handler 
	 GUI gui = new GUI();  // creates the variable for the GUI
	 VisibilityManager vm = new VisibilityManager(gui); // creates the variable for the visibility manager
	 Card c = new Card(); // creates a variable for cards
	 String choice1 = new String(); // creates a string to store the player choices
	 String choice2 = new String(); // creates a string to store the 2nd player choice
	 static int Score; // stores the score
	 static int pCounter = 0; // stores how many moves the player made in the over-world
	 static boolean ciel = false; // code for dev-mode
	 Links ll = new Links(); // links variable 
	 Scanner scan = new Scanner(System.in); // scanner variable
	 static int points = 0; // point integer
	 Random random = new Random();
	 Box box = new Box();
	 int tempor = 0; // variable for a constant that won’t change
	 File file = new File("Sign.txt"); // makes the file
	 
	 //CODE IS CIEL
	// creates new game
	public static void main(String[] args) {
		Score = 0; // sets the score to 0
		new Game(); // creates a new instance of the game
	}
	/*
	 * Input: n/a
	 * Inside: creates GUI & shows the title screen
	 * Output: the title screen of the game
	 */
	
	public Game() {
		if(TempTest.temport == 0) {
			new signUp();
		}
		else {
			gui.createGUI(cHandler); // creates the GUI and puts in the choice handler
			vm.showTitleScreen(); // shows the title screen
		}
	}
	
	
	public  class ChoiceHandler implements ActionListener {
	//Takes in all of the choices from the buttons pressed & implements ActionListener to tell which Button was pressed 
		/*
		 * Input: ActionEvent e --> means the system is informed a button was pressed 
		 * Inside: a switch statement to determine what the desired action should be
		 * Output: The result of the specific button pressed
		 */
		public void actionPerformed(ActionEvent e) {
			String choice = e.getActionCommand(); // sets the choice to a string to use the swtich statement
			switch(choice) { 
			
			case "ia": int num = random.nextInt(4) + 1;  // if the user wants to increase atk it increases the atk
			// of one card randomly
			if(points < 10 && ciel == false) {
				System.out.println("Not enough points...");
				break;
			}
			if(num == 1){
				gui.card1.setAttack(gui.card1.getAttack() + 1); // increase first card atk by 1
				//System.out.println("I worked !"); 
				points = points - 10; gui.pointLabel.setText("Points : " + points);  break;
			}
			if(num == 2){
				gui.card2.setAttack(gui.card2.getAttack() + 1); // increase 2nd card atk by 1
				//System.out.println("I worked !"); 
				points = points - 10; gui.pointLabel.setText("Points : " + points); break;
			}
			if(num == 3){
				gui.card3.setAttack(gui.card3.getAttack() + 1); // increase 3rd card atk by 1
				//System.out.println("I worked !"); 
				points = points - 10;gui.pointLabel.setText("Points : " + points);  break;
			}
			if(num == 4){
				gui.card4.setAttack(gui.card4.getAttack() + 1); // increase 4th card atk by 1 
				//System.out.println("I worked !"); 
				points = points - 10; gui.pointLabel.setText("Points : " + points); break;
			}
			
			case "is":  
			if(points < 5 && ciel == false) {
				System.out.println("Not enough points...");break;
			}
			box.speed += 1;
			if(box.speed >= 25){box.speed = 25;} 
			gui.pointLabel.setText("Points : " + points); points = points - 5;
			//System.out.println("I worked !"); 
			break;
			// if they press this, this will increase the speed
			
			case "imh": 
			if(points < 20 && ciel == false) {
				System.out.println("Not enough points...");break;
			}
			points = points - 20; gui.pointLabel.setText("Points : " + points);
			gui.health += 1; if (gui.health >= 75){gui.health = 75;} 
			//System.out.println("I worked !"); 
			break;
			// if they press this then the health of the player is increased by 75
			
			case "ih": int nume = random.nextInt(2) + 1; // picks one of the two cards randomly
			if(points < 3 && ciel == false) {
				System.out.println("Not enough points..."); 
				break;
			}
			//if the user wants to increase card heal it increases the heal by 1
				if(nume == 1){
					gui.card5.setHp(gui.card5.getHp() + 1,0); // increase 5th card heal by 1
					//System.out.println("I worked !"); 
					points = points - 3; gui.pointLabel.setText("Points : " + points);
					break;
				}
				if(nume == 2){
					gui.card6.setHp(gui.card6.getHp() + 1,0); // increase 6th card heal by 1
					//System.out.println("I worked !"); 
					points = points - 3; gui.pointLabel.setText("Points : " + points);
					break;
				}
			
			// will check the users choice
			case "shop": vm.showShopScreen(); break; 
			//if the user pressed the button "shop" --> go from title screen to title screen --> stop/break
			
			case "code": if(gui.jtf.getText().equalsIgnoreCase("ciel") ) {ciel = true; gui.codeButton.setBackground(Color.green);} 
			if(gui.jtf.getText().equalsIgnoreCase("off")) {ciel = false; gui.codeButton.setBackground(Color.black);} break; 
			// if the user pressed the button "code" --> take in input --> if its the code --> dev-mode is turned on and it breaks.
			
			case "instruction": vm.showInstructionsScreen(); break;
			//if instructions is pressed --> show the instruction screen --> stop/break

			case "back": vm.showTitleScreen(); break;
			//if "back" is pressed --> go from instructions to title screen --> stop/break
			
			case "retry": gui.window.setVisible(false); new Game(); break;
			//if "retry" is pressed --> go from game over to title screen by creating a new instance of the game --> stop/break
			//---------------------------------
			case "start": 
				//if "start is pressed --> the score is added to a linked list --> checks if "ciel" is true or false --> "
				/*
				 * --> start button is pressed
				 * --> score is added to linked list
				 * --> checks if ciel is true or false
				 * --> if true: set player health to basically immortal
				 * --> if false: ignore above
				 * --> Show the main game
				 * --> make a new instance of the new Game
				 * --> break/stop
				 */
				ll.insert(Score);
				Score = 0;
				if(ciel == true) {
				gui.health = 999999999; }
				vm.showMainGame(); new GameOne();
				break;
			//---------------------------------
			case "cardUno": 
				/*
				 * --> check if the first card slot is empty & check if cool-down of card 1 is 0
				 * --> if true:
				 * --> set the text of the card to card 1's attack & cool-down
				 * --> change the background of the first card to orange
				 * --> set cool-down of card 1 to 3
				 * --> set the choice1 to card 1
				 * --> stop/break
				 * --> if false:
				 * --> check if the 2nd player card is empty
				 * --> if yes:
				 * --> do the same as if card 1 was empty but choice2 would = card1 
				 * --> if no: check if card is on cool-down 
				 * --> if yes: print "card is on cool-down"
				 * --> stop/break
				 * --> if no: print "card is already picked" 
				 * --> stop/break 
				 */
				//---------------------------------
			if(gui.checkEmptyCard1() == true && gui.card1.getCooldown() == 0){
				gui.setCardOneText(gui.card1.getAttack(), gui.card1.getCooldown()); 
				gui.cardOne.setBackground(Color.orange); gui.card1.setCooldown(3); 
				choice1 = choice; break;
				}
				//---------------------------------
			else{
				if(gui.checkEmptyCard2() == true && gui.card1.getCooldown() == 0){
					gui.setCardThreeText(gui.card1.getAttack(), gui.card1.getCooldown());
					gui.cardThree.setBackground(Color.orange); gui.card1.setCooldown(3);
					choice2 = choice; break;
					}
					//---------------------------------
					else{
						//---------------------------------
						if(gui.card1.getCooldown() < 3 && gui.card1.getCooldown() != 0) {
							System.out.println("Card is on cooldown");
							break;
						}
						//---------------------------------
						else {
							System.out.println("Card already picked");
							break;
						}
					}
				}
			//----------------------------------------------------------------------	
			case "cardDos":
				/*
				 * Same thing as card1 but just different attack & cool-down
				 */
				//---------------------------------
			if(gui.checkEmptyCard1() == true && gui.card2.getCooldown() == 0){
				gui.setCardOneText(gui.card2.getAttack(), gui.card2.getCooldown()); 
				gui.cardOne.setBackground(Color.orange); gui.card2.setCooldown(2);
				choice1 = choice; break; }
			//---------------------------------
			else{
				//---------------------------------
				if(gui.checkEmptyCard2() == true && gui.card2.getCooldown() == 0){
					gui.setCardThreeText(gui.card2.getAttack(), gui.card2.getCooldown());
					gui.cardThree.setBackground(Color.orange); gui.card2.setCooldown(2);
					choice2 = choice; break; }
				//---------------------------------
				else{
					//---------------------------------
					if(gui.card2.getCooldown() < 2 && gui.card2.getCooldown() != 0) {
						System.out.println("Card is on cooldown");
						break;
					}
					//---------------------------------
					else {
						System.out.println("Card already picked");
						break;
					}
					//---------------------------------
				}
			}
			//-------------------------------------------------------------------------
			case "cardTres": 
				/*
				 * Same thing as card1 but just different attack & cool-down
				 */
				//---------------------------------
			if(gui.checkEmptyCard1() == true && gui.card3.getCooldown() == 0){
				gui.setCardOneText(gui.card3.getAttack(), gui.card3.getCooldown()); 
				gui.cardOne.setBackground(Color.orange); gui.card3.setCooldown(1);
				choice1 = choice; break;
			}
			//---------------------------------
			else{
				//---------------------------------
				if(gui.checkEmptyCard2() == true && gui.card3.getCooldown() == 0){
					gui.setCardThreeText(gui.card3.getAttack(), gui.card3.getCooldown());
					gui.cardThree.setBackground(Color.orange); gui.card3.setCooldown(1);
					choice2 = choice; break;
				}
				//---------------------------------
				else{
					//---------------------------------
					if(gui.card3.getCooldown() < 1 && gui.card3.getCooldown() != 0) {
						System.out.println("Card is on cooldown");
						break;
					}
					//---------------------------------
					else {
						System.out.println("Card already picked");
						break;
					}
					//---------------------------------
				}
			}
			//----------------------------------------------------------------------
			case "cardCuatro": 
				/*
				 * Same thing as card1 but just different attack & cool-down
				 */
				//---------------------------------
			if(gui.checkEmptyCard1() == true && gui.card4.getCooldown() == 0){
				gui.setCardOneText(gui.card4.getAttack(), gui.card4.getCooldown()); 
				gui.cardOne.setBackground(Color.orange); gui.card4.setCooldown(5);
				choice1 = choice; break;   }
				//---------------------------------
			else{
				//---------------------------------
				if(gui.checkEmptyCard2() == true && gui.card4.getCooldown() == 0){
					gui.setCardThreeText(gui.card4.getAttack(), gui.card4.getCooldown());
					gui.cardThree.setBackground(Color.orange); gui.card4.setCooldown(5);
					choice2 = choice; break;
				}
				//---------------------------------
				else{
					//---------------------------------
					if(gui.card4.getCooldown() < 2 && gui.card4.getCooldown() != 0) {
						System.out.println("Card is on cooldown");
						break;
					}
					//---------------------------------
					else {
						System.out.println("Card already picked");
						break;
					}
					//---------------------------------
				}
			}
			//--------------------------------------------------------------------
			case "cardCinco":
				/*
				 * Same thing as card1 but now it's a heal card & different cool-down
				 */
				//---------------------------------
			if(gui.checkEmptyCard1() == true && gui.card5.getCooldown() == 0){
				gui.setCardOneTextH(gui.card5.getHp(), gui.card5.getCooldown()); 
				gui.cardOne.setBackground(Color.orange); gui.card5.setCooldown(4);
				choice1 = choice; break;
			}
			//---------------------------------
			else{
				//---------------------------------
				if(gui.checkEmptyCard2() == true && gui.card5.getCooldown() == 0){
					gui.setCardThreeTextH(gui.card5.getHp(), gui.card5.getCooldown());
					gui.cardThree.setBackground(Color.orange); gui.card5.setCooldown(4);
					choice2 = choice; break;
				}
				//---------------------------------
				else{
					//---------------------------------
					if(gui.card5.getCooldown() < 4 && gui.card5.getCooldown() != 0) {
						System.out.println("Card is on cooldown");
						break;
					}
					//---------------------------------
					else {
						System.out.println("Card already picked");
						break;
					}
					//---------------------------------
				}
			}
			//------------------------------------------------------------------------
			case "cardSeis": 
				/*
				 * Same thing as card1 but now it's a heal card & different cool-down
				 */
				//---------------------------------
			if(gui.checkEmptyCard1() == true && gui.card6.getCooldown() == 0){
				gui.setCardOneTextH(gui.card6.getHp(), gui.card6.getCooldown()); 
				gui.cardOne.setBackground(Color.orange); gui.card6.setCooldown(2);
				choice1 = choice; break;
			}
			//---------------------------------
			else{
				//---------------------------------
				if(gui.checkEmptyCard2() == true && gui.card6.getCooldown() == 0){
					gui.setCardThreeTextH(gui.card6.getHp(), gui.card6.getCooldown());
					gui.cardThree.setBackground(Color.orange); gui.card6.setCooldown(2);
					choice2 = choice; break;
				}
				//---------------------------------
				else{
					//---------------------------------
					if(gui.card6.getCooldown() < 2 && gui.card6.getCooldown() != 0) {
						System.out.println("Card is on cooldown");
						
						break;
					}
					//---------------------------------
					else {
						System.out.println("Card already picked");
						break;
					}
					//---------------------------------
				}
			}
			//------------------------------------------------------------------------
			case "go": if(gui.checkEmptyCard2() == false && gui.checkEmptyCard1() == false) {
				//If "go" was pressed --> make sure both cards have something in them
				//Else print "pick two cards before continuing" --> stop/break
				/*
				 * --> Checks player choice --> Enemy Choice --> Player 2nd Choice --> Enemy 2nd Choice
				 * --> Check what choice 1 is  
				 * --> Deal damage respective to choice or Heal according to respective choice
				 * --> print out either "Enemy took " x amount of damage or "Healed " x amount of health
				 * --> check if player or enemy health is equal to 0
				 * --> if Enemy health = 0 first
				 * --> go back the main game in the over-world
				 * --> if player health = 0 first
				 * --> go to game over screen
				 * --> if "ciel" is active, then enemy deals 0 damage, you one shot any enemy & heal all health back
				 * --> after all cards were checked
				 * --> check both player & enemy health
				 * --> lower all cool-down of cards whose cool-down's are not = to 0 
				 */
				// check card 1 choice
				switch(choice1) {
				// check first card choice
				//---------------------------------
				case "cardUno": // if card 2
				System.out.println("Enemy took " + gui.card1.getAttack() + " Damage!");
				gui.damageHPE(gui.card1.getAttack());
				//---------------------------------
				if(gui.getHPE() <= 0) {
					vm.showMainGame(); new GameOne(); // MEEEEE
					break;
				}
				//---------------------------------
				else {
					gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
					gui.card1.setCooldown(3);
					gui.c1TextV2.setText("cd :" + gui.card1.getCooldown());
					break;
				}
				//---------------------------------
				case "cardDos": 
				System.out.println("Enemy took " + gui.card2.getAttack() + " Damage!");
				gui.damageHPE(gui.card2.getAttack());
				//---------------------------------
				if(gui.getHPE() <= 0) {
					vm.showMainGame(); new GameOne();
					break;
				}
				//---------------------------------
				else {
					gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
					gui.card2.setCooldown(2);
					gui.c2TextV2.setText("cd : " + gui.card2.getCooldown());
					break;
					}
				//---------------------------------
				//---------------------------------
				case "cardTres": 
					System.out.println("Enemy took " + gui.card3.getAttack() + " Damage!");
					gui.damageHPE(gui.card3.getAttack());
					//---------------------------------
					if(gui.getHPE() <= 0) {
						vm.showMainGame(); new GameOne();
						break;
					}
					//---------------------------------
					else {
						gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
						gui.card3.setCooldown(1);
						gui.c3TextV2.setText("cd : " + gui.card3.getCooldown());
						break;
					}
				//---------------------------------
				case "cardCuatro": 
					System.out.println("Enemy took " + gui.card4.getAttack() + " Damage!");
					gui.damageHPE(gui.card4.getAttack());
					if(gui.getHPE() <= 0 ) {
						vm.showMainGame(); new GameOne();
						break;
					}
					//---------------------------------
					else {
						gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
						gui.card4.setCooldown(5);
						gui.c4TextV2.setText("cd : " + gui.card4.getCooldown()); break;
					}
					//---------------------------------
				//---------------------------------
				case "cardCinco":
					gui.hpLabel.setText("HP : " + gui.healHP(gui.card5.getHp())); 
					System.out.println("healed"); gui.card5.setCooldown(4); 
					gui.c5TextV2.setText("cd :" + gui.card5.getCooldown());
					break;
				//---------------------------------
				case "cardSeis": 
					gui.hpLabel.setText("HP : " + gui.healHP(gui.card6.getHp())); 
					System.out.println("healed"); gui.card6.setCooldown(2); 
					gui.c6TextV2.setText("cd :" + gui.card6.getCooldown());
					break;
				}
				//---------------------------------------------------------------------
				//enemy turn
				switch(gui.Echoice1) {
				//---------------------------------
				case "cardUno":
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display();  break;
					}
					if(ciel == true) {
						gui.damageHP(0);
					}
					else {
						System.out.println("Player took " + gui.card1.getAttack() + " Damage!");
						gui.damageHP(gui.card1.getAttack());
					}
					//---------------------------------
					if(gui.getHP() <= 0) {
						if(ciel == true) {
							gui.healHP(9000000);
							break;
						}
						else{
							vm.showGameOver(); // MEEEEE
							ll.insert(Score);
							ll.display();
						break;
						}
					}
					//---------------------------------
					else {
						gui.hpLabel.setText("Your HP : " + gui.getHP());
						break;
					}
					//---------------------------------
				//---------------------------------
				case "cardDos": 
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); break;
					}
					if(ciel == true) {
						gui.damageHP(0);
					}
					else {
						System.out.println("Player took " + gui.card2.getAttack() + " Damage!");
						gui.damageHP(gui.card2.getAttack());
					}
					//---------------------------------
					if(gui.getHP() <= 0) {
						vm.showGameOver(); // MEEEEE
						ll.insert(Score);
						ll.display();
						break;
					}
					//---------------------------------
					else {
						gui.hpLabel.setText("Your HP : " + gui.getHP());
						break;
					}
					//---------------------------------
				//---------------------------------
				case "cardTres": 
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); break;
					}
					if(ciel == true) {
						gui.damageHP(0);
					}
					else {
					System.out.println("Player took " + gui.card3.getAttack() + " Damage!");
					gui.damageHP(gui.card3.getAttack());
					}
					//---------------------------------
					if(gui.getHP() <= 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); // MEEEEE
						break;
					}
					//---------------------------------
					else {
						gui.hpLabel.setText("Your HP : " + gui.getHP());
						break;
					}
					//---------------------------------
				//---------------------------------
				case "cardCuatro": 
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); break;
					}
					if(ciel == true) {
						gui.damageHP(0);
					}
					else {
					System.out.println("Player took " + gui.card4.getAttack() + " Damage!");
					gui.damageHP(gui.card4.getAttack());
					}
					//---------------------------------
					if(gui.getHP() <= 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); // MEEEEE
						break;
					}
					//---------------------------------
					else {
						gui.hpLabel.setText("Your HP : " + gui.getHP());
						break;
					}
					//---------------------------------
				//---------------------------------
				case "cardCinco": 
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); break;
					}
					if(ciel == true) {
						gui.healHPE(0);
					}
					else {
					System.out.println("Enemy healed " + gui.card5.getHp() + " HP!");
					gui.healHPE(gui.card5.getHp());
					gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
					}
					//---------------------------------
				//---------------------------------
				case "cardSeis": 
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); break;
					}
					if(ciel == true) {
						gui.healHPE(0);
					}
					else {
					System.out.println("Enemy healed " + gui.card6.getHp() + " HP!");
					gui.healHPE(gui.card6.getHp());
					gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
					}
				//---------------------------------
				}
				//-----------------------------------------------------------------
				// check card 2 choice / player turn
				switch(choice2) {
				case "cardUno": 
					if(gui.getHPE() <= 0) {
						break;
					}
					System.out.println("Enemy took " + gui.card1.getAttack() + " Damage!");
					gui.damageHPE(gui.card1.getAttack());
					//---------------------------------
					if(gui.getHPE() <= 0) {
						vm.showMainGame(); new GameOne(); // MEEEEE
						break;
					}
					//---------------------------------
					else {
						gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
						gui.card1.setCooldown(3);
						gui.c1TextV2.setText("cd :" + gui.card1.getCooldown());
						break;
					}
					//---------------------------------
					case "cardDos": 
					if(gui.getHPE() <= 0) {
							break;
					}
					System.out.println("Enemy took " + gui.card2.getAttack() + " Damage!");
					gui.damageHPE(gui.card2.getAttack());
					//---------------------------------
					if(gui.getHPE() <= 0) {
						vm.showMainGame(); new GameOne();
						break;
					}
					//---------------------------------
					else {
						gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
						gui.card2.setCooldown(2);
						gui.c2TextV2.setText("cd : " + gui.card2.getCooldown());
						break;
						}
					//---------------------------------
					//---------------------------------
					case "cardTres": 
						if(gui.getHPE() <= 0) {
							break;
						}
						System.out.println("Enemy took " + gui.card3.getAttack() + " Damage!");
						gui.damageHPE(gui.card3.getAttack());
						//---------------------------------
						if(gui.getHPE() <= 0) {
							vm.showMainGame(); new GameOne();
							break;
						}
						//---------------------------------
						else {
							gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
							gui.card3.setCooldown(1);
							gui.c3TextV2.setText("cd : " + gui.card3.getCooldown());
							break;
						}
					//---------------------------------
					case "cardCuatro": 
						if(gui.getHPE() <= 0) {
							break;
						}
						System.out.println("Enemy took " + gui.card4.getAttack() + " Damage!");
						gui.damageHPE(gui.card4.getAttack());
						if(gui.getHPE() <= 0 ) {
							vm.showMainGame(); new GameOne();
							break;
						}
						//---------------------------------
						else {
							gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
							gui.card4.setCooldown(5);
							gui.c4TextV2.setText("cd : " + gui.card4.getCooldown()); break;
						}
						//---------------------------------
					//---------------------------------
					case "cardCinco":
						if(gui.getHPE() <= 0) {
							break;
						}
						gui.hpLabel.setText("HP : " + gui.healHP(gui.card5.getHp())); 
						System.out.println("healed"); gui.card5.setCooldown(4); 
						gui.c5TextV2.setText("cd :" + gui.card5.getCooldown());
						break;
					//---------------------------------
					case "cardSeis": 
						if(gui.getHPE() <= 0) {
							break;
						}
						gui.hpLabel.setText("HP : " + gui.healHP(gui.card6.getHp())); 
						System.out.println("healed"); gui.card6.setCooldown(2); 
						gui.c6TextV2.setText("cd :" + gui.card6.getCooldown());
						break;
				}
				//-------------------------------------------------------------------------
				//Enemy turn
				switch(gui.Echoice2) {
				case "cardUno":
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); break;
					}
					if(ciel == true) {
						gui.damageHP(0);
					}
					else {
					System.out.println("Player took " + gui.card1.getAttack() + " Damage!");
					gui.damageHP(gui.card1.getAttack());
					}
					//---------------------------------
					if(gui.getHP() <= 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); // MEEEEE
						break;
					}
					//---------------------------------
					else {
						gui.hpLabel.setText("Your HP : " + gui.getHP());
						break;
					}
					//---------------------------------
				//---------------------------------
				case "cardDos": 
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); break;
					}
					if(ciel == true) {
						gui.damageHP(0);
					}
					else {
					System.out.println("Player took " + gui.card2.getAttack() + " Damage!");
					gui.damageHP(gui.card2.getAttack());
					}
					//---------------------------------
					if(gui.getHP() <= 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); // MEEEEE
						break;
					}
					//---------------------------------
					else {
						gui.hpLabel.setText("Your HP : " + gui.getHP());
						break;
					}
					//---------------------------------
				//---------------------------------
				case "cardTres":
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); break;
					}
					if(ciel == true) {
						gui.damageHP(0);
					}
					else {
					System.out.println("Player took " + gui.card3.getAttack() + " Damage!");
					gui.damageHP(gui.card3.getAttack());
					}
					//---------------------------------
					if(gui.getHP() <= 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); // MEEEEE
						break;
					}
					//---------------------------------
					else {
						gui.hpLabel.setText("Your HP : " + gui.getHP());
						break;
					}
					//---------------------------------
				//---------------------------------
				case "cardCuatro": 
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); break;
					}
					if(ciel == true) {
						gui.damageHP(0);
					}
					else {
					System.out.println("Player took " + gui.card4.getAttack() + " Damage!");
					gui.damageHP(gui.card4.getAttack());
					}
					//---------------------------------
					if(gui.getHP() <= 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); // MEEEEE
						break;
					}
					//---------------------------------
					else {
						gui.hpLabel.setText("Your HP : " + gui.getHP());
						break;
					}
					//---------------------------------
				//---------------------------------
				case "cardCinco": 
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); break;
					}
					if(ciel == true) {
						gui.healHPE(0);
					}
					else {
					System.out.println("Enemy healed " + gui.card5.getHp() + " HP!");
					gui.healHPE(gui.card5.getHp());
					gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
					}
					//---------------------------------
				//---------------------------------
				case "cardSeis": 
					if(gui.getHP() == 0) {
						vm.showGameOver(); ll.insert(Score);
						ll.display(); break;
					}
					if(ciel == true) {
						gui.healHPE(0);
					}
					else {
					System.out.println("Enemy healed " + gui.card6.getHp() + " HP!");
					gui.healHPE(gui.card6.getHp());
					gui.ehpLabel.setText("Enemy HP : " + gui.getHPE());
					}
				//---------------------------------
					
				}
				//--------------------------------------------------------------------------
				// decrease cards on cd & after all turns
				
				if(gui.getHP() == 0) {
					vm.showGameOver(); ll.insert(Score);
					ll.display(); break;
				}
				
				if(gui.getHPE() == 0) {
					vm.showMainGame(); new GameOne(); break;
				}
				
				//card 1 change cd
				if(gui.card1.getCooldown() != 0) {
					gui.card1.setCooldown(gui.card1.getCooldown()-1);
					gui.c1TextV2.setText("cd :" + gui.card1.getCooldown());
				}
				//---------------------------------
				//card 2 change cd
				if(gui.card2.getCooldown() != 0) {
					gui.card2.setCooldown(gui.card2.getCooldown()-1);
					gui.c2TextV2.setText("cd :" + gui.card2.getCooldown());
				}
				//---------------------------------
				//card 3 change cd
				if(gui.card3.getCooldown() != 0) {
					gui.card3.setCooldown(gui.card3.getCooldown()-1);
					gui.c3TextV2.setText("cd :" + gui.card3.getCooldown());
				}
				//---------------------------------
				//card 4 change cd
				if(gui.card4.getCooldown() != 0) {
					gui.card4.setCooldown(gui.card4.getCooldown()-1);
					gui.c4TextV2.setText("cd :" + gui.card4.getCooldown());
				}
				//---------------------------------
				//card 5 change cd
				if(gui.card5.getCooldown() != 0) {
					gui.card5.setCooldown(gui.card5.getCooldown()-1);
					gui.c5TextV2.setText("cd :" + gui.card5.getCooldown());
				}
				//---------------------------------
				//card 6 change cd
				if(gui.card6.getCooldown() != 0) {
					gui.card6.setCooldown(gui.card6.getCooldown()-1);
					gui.c6TextV2.setText("cd :" + gui.card6.getCooldown());
				}
				//---------------------------------
				gui.setCardOneTextBlank();
				gui.cardOne.setBackground(Color.white);
				gui.setECards();
				
				
				gui.setCardThreeTextBlank();
				gui.cardThree.setBackground(Color.white);
				
			}
			//---------------------------------
			else {
				System.out.println("Pick Two Cards Before Continuing...");
				break;
				}
			//---------------------------------
			}
			/*
			 * Extra check on player & enemy health
			 */
			if(gui.getHPE() <= 0 && gui.getHP() <= 0) {
				vm.showGameOver(); 
			}
			
			if(gui.getHP() == 0) {
				vm.showGameOver(); 
			}
			
			if(gui.getHPE() == 0) {
				vm.showMainGame(); new GameOne();
			}
		}
	}
	public class GameOne extends JFrame{
		
		/**
		 * Instance Variables
		 */
		Image image; // image variable
		Graphics graphics; // graphic variable
		Box player; // player variable
		Box enemy; // enemy variable
		boolean e1IsDrawn = false; // enemy is drawn boolean variable
		Box enemy2; // 2nd enemy variable
		boolean e2IsDrawn = false; // 2nd enemy is drawn boolean variable
		Box enemy3; // 3rd enemy variable
		boolean e3IsDrawn = false; // 3rd enemy is drawn boolean variable
		boolean gameOver; // gameOver variable
		Box stairs; // stairs variable
		boolean sIsDrawn = false; // stairs is drawn boolean variable
		Box wall1; // wall variable
		Box wall2; // wall variable
		Box wall3; // wall variable
		Box wall4; // wall variable
		Random rand = new Random(); // random number generator variable
		int counter = 1; // counter variable
		int ran = rand.nextInt(4) + 1; // random number between 1-4
		boolean battleDone1 = false; // checks if battle against enemy 1 was done
		boolean battleDone2 = false; // checks if battle against enemy 2 was done
		boolean battleDone3 = false; // checks if battle against enemy 3 was done
		Box BossEnemy; // boss enemy variable
		boolean bossIsDrawn = false; // boss is drawn boolean variable
		boolean battleDoneBoss = false; // checks if battle against boss was done
		boolean temp = false; // temporary variable to ensure playerCounter
		int height = 950; 
		int width = 1895;
		BufferedImage i;
		File f;
		/*
		 * --> if player health is 0 
		 * --> show game over
		 * --> else:
		 * --> create a new player
		 * --> if "ciel" is true
		 * --> set player speed to 25
		 * --> else: do nothing
		 * --> place & create the first enemy 
		 * --> place & create the boss enemy
		 * --> place & create enemy 2 & 3
		 * --> create the walls
		 * --> set gameOver to false
		 * --> place & create the stairs
		 * --> set the background of the frame to black
		 * --> set close operation (makes it so you can press the x to close it)
		 * --> set the bounds / size
		 * --> make it visible 
		 * --> add a key listener to recognize a key pressed
		 * 
		 */
		GameOne(){
			
			if(gui.getHP() <= 0){
				this.setVisible(false);
				vm.showGameOver();
			}
			if(gui.window != null) {
				this.setVisible(false);
			}
			
			player = new Box(400,300,50,50,Color.blue);
			if(ciel == true) {
				player.speed = 25;
			}	
			
			placeEnemy();
			placeBossEnemy();
			enemy2 = new Box(400,100,50,50,Color.red);
			enemy3 = new Box(400,600,50,50,Color.red);
			wall1 = new Box(0,0,1000,1,Color.black);
			wall2 = new Box(0,0,1,800,Color.black);
			wall3 = new Box(1000,0,1,800,Color.black);
			wall4 = new Box(0,800,1000,1,Color.black);
			gameOver = false;
			placeStairs();
			ran = rand.nextInt(4) + 1; // random number between 1-4
			this.setBackground(Color.BLACK);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setBounds(0,0,1000,800);
			this.setVisible(true);
			this.addKeyListener(new AL());
			//System.out.println("I REPEAT");
		}
		
		/*
		 * Input: Graphic g
		 * Inside: --> do a thread sleep to allow for a couple second rest 
		 * --> check if player health is 0
		 * --> create the image 
		 * --> draw on top of the image
		 * --> draw the player on the image
		 * --> Dependent on the random number, a certain room will show
		 * --> 1: draw the enemy --> move the enemy --> Set stairs visible boolean to false --> set enemy visible boolean to true 
		 * --> 1: make a if statement so the enemy can only move when the player moves 10 times first --> check collisions 
		 * --> 2: same thing as room 1 but add an extra enemy
		 * --> 3: same thing as room 1 & 2 but add an extra enemy
		 * --> 4: same thing as room 3 but add the stairs
		 * --> if the score is 2 & room is 4: print room 4 + boss enemy 
		 */
		public void paint(Graphics g) {
			//g.clearRect(0, 0, 20000, 20000);
			try {
				Thread.sleep(1000/30);
			}
			catch(Exception e) {
			}
			//System.out.println("ME TOO");
			if(gui.getHP() <= 0){
				this.setVisible(false);
				vm.showGameOver();
			}
			image = createImage(this.getWidth(),this.getHeight());
			graphics = image.getGraphics();
			g.drawImage(image,0,0,this);
			graphics.setColor(Color.green);
			player.draw(g);
			if(ran == 3){
				if(temp == false) {
					pCounter = 0;
				}
				temp = true;
				if(pCounter >= 10) {
					moveAll();
					if(pCounter == 25) {
						//pCounter = 0;
					}
				}
				enemy.draw(g); enemy2.draw(g); enemy3.draw(g);
				e1IsDrawn = true;
				e2IsDrawn = true; 
				e3IsDrawn = true;
				sIsDrawn = false;
				checkCollision();
			}
			else if(ran == 2){
				if(temp == false) {
					pCounter = 0;
				}
				temp = true;
				if(pCounter >= 10) {
					moveAll();
					if(pCounter == 25) {
						//pCounter = 0;
					}
				}
				enemy.draw(g); enemy2.draw(g);
				e1IsDrawn = true;
				e2IsDrawn = true;
				sIsDrawn = false;
				checkCollision();
			}
			else if(ran == 1){
				if(temp == false) {
					pCounter = 0;
				}
				temp = true;
				if(pCounter >= 10) {
					moveAll();
					if(pCounter == 25) {
						//pCounter = 0;
					}
				}
				enemy.draw(g);
				e1IsDrawn = true;
				sIsDrawn = false;
				checkCollision();
			}
			
			else if(ran == 4 && Score >= 2) {
				if(pCounter >= 10) {
					moveAll();
					if(pCounter == 25) {
						//pCounter = 0;
					}
				}
				enemy.draw(g); enemy2.draw(g); enemy3.draw(g); stairs.draw(g); BossEnemy.draw(g);
				e1IsDrawn = true;
				e2IsDrawn = true; 
				e3IsDrawn = true;
				bossIsDrawn = true;
				sIsDrawn = true;
				checkCollision();
			}
			
			else if(ran == 4){
				moveAll();
				enemy.draw(g); enemy2.draw(g); enemy3.draw(g); stairs.draw(g); 
				e1IsDrawn = true;
				e2IsDrawn = true; 
				e3IsDrawn = true;
				sIsDrawn = true;
				checkCollision();
			}
		}
		
		/*
		 * if enemy x position is less than player x position --> enemy x position increases
		 * else: enemy x position decreases
		 * 
		 * if enemy y position is less than player y position --> enemy y position increases\
		 * else: enemy y position decreases
		 * 
		 * if score is greater or equal to 2
		 * x position is increased or decreased by 2
		 * y position is increased or decreased by 2
		 */
		public void eMove1(){
			int x, y;
			//x = rand.nextInt(1000); y = rand.nextInt(800);
			//x = player.x - 100;
			//y = player.y;
			
			if(enemy.x < player.x) {
				x = enemy.x + 5;
			}
			else{
				x = enemy.x - 5;
			}
			if(enemy.y < player.y) {
				y = enemy.y + 5;
			}
			else {
				y = enemy.y - 5;
			}
			
			if(Score >= 2) {
				if(enemy.x < player.x) {
					x = enemy.x + 8;
				}
				else {
					x = enemy.x - 8;
				}
				if(enemy.y < player.y) {
					y = enemy.y + 8;
				}
				else {
					y = enemy.y - 8;
				}
			}
			
			enemy.x = x; enemy.y = y;
			
		}
		
		/*
		 * same thing as eMove1 but minus the score is greater than or equal to 2
		 */
		public void eMove2(){
			
			int x, y;
			
			if(enemy2.x < player.x) {
				x = enemy2.x - 3;
			}
			else {
				x = enemy2.x + 2;
			}
			if(enemy2.y < player.y) {
				y = enemy2.y - 3;
			}
			else {
				y = enemy2.y + 2;
			}
			enemy2.x = x; enemy2.y = y;
		}
		/*
		 * same thing as eMove1() but by 2 and if score is equal or greater than 2 by 3
		 */
		public void eMove3(){
			
			int x, y;
			if(enemy3.x < player.x) {
				x = enemy3.x + 7;
			}
			else {
				x = enemy3.x - 7;
			}
			if(enemy3.y < player.y) {
				y = enemy3.y + 7;
			}
			else {
				y = enemy3.y - 7;
			}
			
			if(Score >= 2) {
				if(enemy3.x < player.x) {
					x = enemy3.x + 9;
				}
				else {
					x = enemy3.x - 9;
				}
				if(enemy3.y < player.y) {
					y = enemy3.y + 9;
				}
				else {
					y = enemy3.y - 9;
				}
			}
			
			enemy3.x = x; enemy3.y = y;
			
		}
		
		/*
		 * Same thing as eMove1() but by 4
		 */
		public void eMove4(){
			
			int x, y;
			if(BossEnemy.x < player.x) {
				x = BossEnemy.x + 10;
			}
			else {
				x = BossEnemy.x - 10;
			}
			if(BossEnemy.y < player.y) {
				y = BossEnemy.y + 10;
			}
			else {
				y = BossEnemy.y - 10;
			}
			
			BossEnemy.x = x; BossEnemy.y = y;
			
		}
		
		/*
		 * Makes two int's x & y
		 * --> Assigned a random area within the map
		 * --> placed in there with the color of light gray
		 */
		public void placeStairs() {
			int x, y;
			x = rand.nextInt(950); y = rand.nextInt(750);
			stairs = new Box(x,y,25,25,Color.LIGHT_GRAY); // normal is 800,600
		}
		
		/*
		 * Makes two int's x & y
		 * --> Assigned a random area within the map
		 * --> placed in there with the color of light gray
		 */
		public void placeEnemy() {
			int x, y;
			x = rand.nextInt(950); y = rand.nextInt(750);
			enemy = new Box(x,y,50,50,Color.red);
		}
		
		/*
		 * Makes two int's x & y
		 * --> Assigned a random area within the map
		 * --> placed in there with the color of light gray
		 */
		public void placeBossEnemy() {
			int x, y;
			x = rand.nextInt(950); y = rand.nextInt(750);
			BossEnemy = new Box(x,y,50,50,Color.red);
		}
		
		/*
		 * --> 1: check if player health is 0 or less
		 * --> if yes: show game over 
		 * --> if no: ignore
		 * 
		 * --> 2: check if player intersects with any enemy & stairs
		 * --> Stairs take priority 
		 * --> Increase score & place stairs and make go to the next level / area
		 * 
		 * --> 3: check if player intersects with the first enemy & if the enemy is drawn
		 * --> set the current frame to invisible
		 * --> set battleDone1 to true
		 * --> set the enemy Health to 10
		 * --> create the GUI
		 * --> go from the game to the battle screen
		 * 
		 * --> 4: if player intersects stairs
		 * --> increase Score
		 * --> place stairs
		 * --> go to the next area/map
		 * 
		 * --> 5: if player intersects with 2nd enemy && if enemy2 is drawn
		 * --> Same thing as first enemy but health = 20
		 * 
		 * --> 6: check if player intersects with boss enemy && if boss enemy is drawn
		 * --> Same thing as first enemy but set health = 40
		 * 
		 * --> 7: if player intersects with 3rd enemy && if enemy3 is drawn
		 * --> Same thing as first enemy  but health = 30
		 * 
		 * --> 8: If player intersects a wall
		 * --> go the next area by creating a new room/game instance
		 * --> set the player counter to 0
		 * 
		 * --> 9: if enemy 2 intersects the wall
		 * --> spawn by enemy 1 & go back 10 in x & 15 in y
		 */
		public void checkCollision() {
			if(gui.getHP() <= 0){
				this.setVisible(false);
				gui.window.setVisible(true);
				vm.showGameOver();
			}
			
			//If player collides with an enemy and the stairs ... Stairs take priority
			if(player.intersects(enemy) && player.intersects(stairs) || player.intersects(enemy2) && player.intersects(stairs) || player.intersects(enemy3) && player.intersects(stairs)) {
				this.setVisible(false);
				Score++;
				placeStairs();
				new GameOne();
			}
			
			if(player.intersects(enemy) && e1IsDrawn == true || enemy.intersects(player) && e1IsDrawn == true) {
				this.setVisible(false);
				battleDone1 = true;
				gui.setEnemyHP(10);
				gui.ehealth = 10;
				gameOver = true;
				gui.createGUI(cHandler);
				gui.window.setVisible(true);
				vm.titleToBattle();
				points += 1;
				// set to what happens when gave over
				//vm.showBattleScreen
			}
			
			if(player.intersects(stairs) && sIsDrawn == true){
				Score++;
				placeStairs();
				this.setVisible(false);
				new GameOne();
			}
			
			if(player.intersects(enemy2) && e2IsDrawn == true || enemy2.intersects(player) && e2IsDrawn == true){
				this.setVisible(false);
				battleDone2 = true;
				gui.setEnemyHP(20);
				gui.ehealth = 20;
				gameOver = true;
				gui.createGUI(cHandler);
				gui.window.setVisible(true);
				vm.titleToBattle();
				points += 2;
			}
			
			if(player.intersects(BossEnemy) && bossIsDrawn == true || BossEnemy.intersects(player) && bossIsDrawn == true){
				this.setVisible(false);
				battleDoneBoss = true;
				gui.setEnemyHP(40);
				gui.ehealth = 40;
				gameOver = true;
				gui.createGUI(cHandler);
				vm.titleToBattle();
				gui.window.setVisible(true);
				points += 4;
			}
			
			if(player.intersects(enemy3) && e3IsDrawn == true || enemy3.intersects(player) && e3IsDrawn == true){
				this.setVisible(false);
				battleDone3 = true;
				gui.setEnemyHP(30);
				gui.ehealth = 30;
				gameOver = true;
				gui.createGUI(cHandler);
				vm.titleToBattle();
				gui.window.setVisible(true);
				points += 3;
			}
			
			if(player.intersects(wall1) || player.intersects(wall2) || player.intersects(wall3) || player.intersects(wall4)){
				this.setVisible(false);
				temp = true;
				pCounter = 0;
				new GameOne();
				pCounter = 0;
				temp = true;
			}
			
			
			if(enemy2.intersects(wall1) || enemy2.intersects(wall2) || enemy2.intersects(wall3) || enemy2.intersects(wall4)) {
				enemy2.x = enemy.x - 10;
				enemy2.y = enemy.y + 15;
			}
			
		}
		
		/*
		 * make the game frame invisible
		 * set the main GUI screen visible
		 * show the title screen
		 */
		public void escape(){
			this.setVisible(false);
			gui.window.setVisible(true);
			vm.showTitleScreen();
		}
		
		/*
		 * if the player has made over 10 moves:
		 * --> move all enemies and repaint
		 */
		public void moveAll() {
			if(pCounter >= 10) {
				eMove1();
				eMove2();
				eMove3();
				eMove4();
				repaint();
			}
		}
		
		/*
		 * Class AL: Purpose: Player Movement
		 */
		public class AL extends KeyAdapter{
			//Receives the keyboard input
			/*
			 * Input: KeyEvent e / key pressed
			 * Inside: passes the key pressed to the player 
			 * --> increases the player movement counter /pCounter
			 * --> checks if they press escape 
			 * --> yes: run escape 
			 * --> no: do nothing
			 * --> the repaint
			 * Output: n/a
			 */
			public void keyPressed(KeyEvent e) {
				//System.out.println("ME THREE");
				player.keyPressed(e);
				pCounter++;
				checkCollision();
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
					escape();
				}
				repaint();
				
				//THIS IS RECURSION SO IM FREE FROM IT LMAOOOO
			}
		
		}
	}
	
	/*
	 * This class makes a sign up area
	 */
	public class signUp extends JFrame implements ActionListener {
		
		JTextField untf = new JTextField(); // user name text field
		JPasswordField ptf = new JPasswordField(); // password text field
		JLabel unL = new JLabel("User Name:"); // user name label
		JLabel pL = new JLabel("Password:"); // password label
		JButton suB = new JButton("Sign Up"); // sign up button
		JLabel b = new JLabel(); // b = blank
		FileWriter fw;
		
		/*
		 * Makes the frame & adds all the labels,buttons fields
		 */
		signUp(){
			
			GridLayout g1 = new GridLayout(); // makes a new layout
			g1.setColumns(2); // sets the columns to 2
			g1.setRows(3); // sets rows to 3
			
			this.setLayout(g1); // sets the layout of the frame to the made layout
			
			suB.addActionListener(this); // adds the action listener to take in mouse input
			
			this.add(unL); // adds the user name label
			this.add(untf); // adds the user name text field
			this.add(pL); // adds the password label
			this.add(ptf); // adds the password password field
			this.add(b); // adds a blank space for Aesthetics
			this.add(suB); // adds the signup button
			untf.setFont(gui.normalFont);
			ptf.setFont(gui.normalFont);
			
			this.setBackground(Color.white); // sets frame background to white
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes it so you can close the window
			this.setSize(300,200); // sets the  size
			this.setVisible(true); // makes it visible
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == suB.getActionCommand()) {
				// if the action done is the sign up button
				try {
					fw = new FileWriter(file); // make a file writer
					BufferedWriter bw = new BufferedWriter(fw); // make the buffered reader to write in the file
					bw.write(unL.getText() + " : " + untf.getText()); // write the username + the username writer
					bw.write("\n" + pL.getText() + " : " + ptf.getText()); // write the password + password written
					bw.close(); // close the writer
					TempTest.temport = 10; // set the temport value to 10
					this.setVisible(false); // make it invisible
					if(this.isVisible() == false) { 
						// if the screen is invisible
						this.setVisible(false); // make sure its invisible
						gui.createGUI(cHandler); // creates the GUI and puts in the choice handler
						vm.showTitleScreen(); // shows the title screen
					}
					
				}
				catch(Exception ef) {
					System.out.println(ef); // print out the error
				}
			}
		}
	}
	
}

