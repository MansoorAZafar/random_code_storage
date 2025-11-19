package fpg;
import fpg.GUI;

/*
 * Sets certain scenes visible at specific times
 * 
 * @mansoor zafar 
 * @2022-06-12
 */
public class VisibilityManager {
	//Instance Variable
	GUI gui;
	
	/*
	 * Input: GUI 
	 * Inside: set the "gui" equal to the "graphic"
	 * Output: gui as graphic
	 */
	public VisibilityManager(GUI graphic) {
		gui = graphic;
		
	}
	
	/*
	 * Reference commands:
	 * .setVisible() --> makes it so you can either see it or not
	 * 
	 * 
	 * Input: n/a
	 * Inside: sets everything except the items needed for the title to invisible
	 * Output: the title screen
	 */
	public void showTitleScreen() {
		
		//Show title Screen
		gui.titleNamePanel.setVisible(true); // set the title name to visible
		gui.startButtonPanel.setVisible(true); // set the start button to visible
		gui.c1.setVisible(false); // set the card 1 to invisible
		gui.c2.setVisible(false); // set the card 2 to invisible
		gui.c3.setVisible(false); // set the card 3 to invisible 
		gui.c4.setVisible(false); // invisible
		gui.c5.setVisible(false); // invisible
		gui.c6.setVisible(false); // invisible
		gui.GameOverPanel.setVisible(false); // invisible
		gui.cardOne.setVisible(false); // invisible
		gui.cardTwo.setVisible(false); // invisible
		gui.cardThree.setVisible(false); // invisible
		gui.cardFour.setVisible(false); // invisible
		gui.hpPanel.setVisible(false); // invisible
		gui.goButton.setVisible(false); // invisible
		gui.ehpPanel.setVisible(false); // invisible
		gui.gameWinPanel.setVisible(false); // invisible
		gui.retryButtonPanel.setVisible(false); // invisible
		gui.scorePanel.setVisible(true); // VISIBLE 
		gui.InstructionButtonPanel.setVisible(true); // VISIBLE
 		gui.backButtonPanel.setVisible(false); // invisible
		gui.ILabel1.setVisible(false); // invisible
		gui.ILabel2.setVisible(false); // invisible
		gui.ILabel3.setVisible(false); // invisible
		gui.ILabel4.setVisible(false); // invisible
		gui.ILabel5.setVisible(false); // invisible
		gui.ILabel6.setVisible(false); // invisible
		gui.ILabel7.setVisible(false); // invisible
		gui.ILabel8.setVisible(false); // invisible
		gui.codeButtonPanel.setVisible(true); // VISIBLE
		gui.highestScorePanel.setVisible(true); // VISIBLE
		gui.pointPanel.setVisible(true); // VISIBLE
		gui.shopButtonPanel.setVisible(true); // VISIBLE
		gui.shopButtonsPanel.setVisible(false); // Invisible
		gui.jtf.setVisible(true); // VISIBLE
		//Hide anything else
		
	}
	
	/*
	 * Same thing as showTitleScreen but for the main game
	 */
	public void showMainGame() {
		
		gui.titleNamePanel.setVisible(false); // invisible
		gui.startButtonPanel.setVisible(false); // invisible
		gui.c1.setVisible(false); // invisible
		gui.c2.setVisible(false); // invisible
		gui.c3.setVisible(false); // invisible
		gui.c4.setVisible(false); // invisible
		gui.c5.setVisible(false); // invisible
		gui.c6.setVisible(false); // invisible
		gui.GameOverPanel.setVisible(false); // invisible
		gui.cardOne.setVisible(false); // invisible
		gui.cardTwo.setVisible(false); // invisible
		gui.cardThree.setVisible(false); // invisible
		gui.cardFour.setVisible(false); // invisible
		gui.hpPanel.setVisible(false); // invisible
		gui.goButton.setVisible(false); // invisible
		gui.ehpPanel.setVisible(false); // invisible
		gui.gameWinPanel.setVisible(false); // invisible
		gui.window.setVisible(false); // invisible
		gui.retryButtonPanel.setVisible(false); // invisible
		gui.scorePanel.setVisible(false); // invisible
		gui.InstructionButtonPanel.setVisible(false); // invisible
		gui.backButtonPanel.setVisible(false); // invisible
		gui.ILabel1.setVisible(false); // invisible
		gui.ILabel2.setVisible(false); // invisible
		gui.ILabel3.setVisible(false); // invisible
		gui.ILabel4.setVisible(false); // invisible
		gui.ILabel5.setVisible(false); // invisible
		gui.ILabel6.setVisible(false); // invisible
		gui.ILabel7.setVisible(false); // invisible
		gui.ILabel8.setVisible(false); // invisible
		gui.codeButtonPanel.setVisible(false); // invisible
		gui.highestScorePanel.setVisible(false); // invisible
		gui.pointPanel.setVisible(false); // invisible
		gui.shopButtonPanel.setVisible(false); // invisible
		gui.shopButtonsPanel.setVisible(false); // Invisible
		gui.jtf.setVisible(false); // invisible
		
	}
	
	/*
	 * Same thing as showTitleScreen() but for the battle menu
	 */
	public void titleToBattle() {

		//Show title Screen
		gui.titleNamePanel.setVisible(false); // invisible
		gui.startButtonPanel.setVisible(false); // invisible
		gui.c1.setVisible(true); // VISIBLE
		gui.c2.setVisible(true); // VISIBLE
		gui.c3.setVisible(true); // VISIBLE
		gui.c4.setVisible(true); // VISIBLE
		gui.c5.setVisible(true); // VISIBLE
		gui.c6.setVisible(true); // VISIBLE
		gui.GameOverPanel.setVisible(false); // invisible
		gui.GameOverLabel.setVisible(false); // invisible
		gui.cardOne.setVisible(true); // VISIBLE
		gui.cardTwo.setVisible(true); // VISIBLE
		gui.cardThree.setVisible(true); // VISIBLE
		gui.cardFour.setVisible(true); // VISIBLE
		gui.hpPanel.setVisible(true); // VISIBLE
		gui.goButton.setVisible(true); // VISIBLE
		gui.ehpPanel.setVisible(true); // VISIBLE
		gui.gameWinPanel.setVisible(false); // invisible
		gui.retryButtonPanel.setVisible(false); // invisible
		gui.scorePanel.setVisible(false); // invisible
		gui.InstructionButtonPanel.setVisible(false); // invisible
		gui.backButtonPanel.setVisible(false); // invisible
		gui.ILabel1.setVisible(false); // invisible
		gui.ILabel2.setVisible(false); // invisible
		gui.ILabel3.setVisible(false); // invisible
		gui.ILabel4.setVisible(false); // invisible
		gui.ILabel5.setVisible(false); // invisible
		gui.ILabel6.setVisible(false); // invisible
		gui.ILabel7.setVisible(false); // invisible
		gui.ILabel8.setVisible(false); // invisible
		gui.codeButtonPanel.setVisible(false); // invisible
		gui.highestScorePanel.setVisible(false); // invisible
		gui.pointPanel.setVisible(false); // invisible
		gui.shopButtonPanel.setVisible(false); // invisible
		gui.shopButtonsPanel.setVisible(false); // Invisible
		gui.jtf.setVisible(false); // invisible
		//Hide anything else
		
	}
	
	/*
	 * Same thing as showTitleScreen but for game over
	 */
	public void showGameOver() {
		gui.titleNamePanel.setVisible(false); // invisible
		gui.startButtonPanel.setVisible(false); // invisible
		gui.c1.setVisible(false); // invisible
		gui.c2.setVisible(false); // invisible
		gui.c3.setVisible(false); // invisible
		gui.c4.setVisible(false); // invisible
		gui.c5.setVisible(false); // invisible
		gui.c6.setVisible(false); // invisible
		gui.GameOverPanel.setVisible(true); // VISIBLE
		gui.GameOverLabel.setVisible(true); // VISIBLE
		gui.cardOne.setVisible(false); // invisible
		gui.cardTwo.setVisible(false); // invisible
		gui.cardThree.setVisible(false); // invisible
		gui.cardFour.setVisible(false); // invisible
		gui.hpPanel.setVisible(false); // invisible
		gui.goButton.setVisible(false); // invisible
		gui.ehpPanel.setVisible(false); // invisible
		gui.gameWinPanel.setVisible(false); // invisible
		gui.retryButtonPanel.setVisible(true); // VISIBLE
		gui.scorePanel.setVisible(false); // invisible
		gui.InstructionButtonPanel.setVisible(false); // invisible
		gui.backButtonPanel.setVisible(false); // invisible
		gui.ILabel1.setVisible(false); // invisible
		gui.ILabel2.setVisible(false); // invisible
		gui.ILabel3.setVisible(false); // invisible
		gui.ILabel4.setVisible(false); // invisible
		gui.ILabel5.setVisible(false); // invisible
		gui.ILabel6.setVisible(false); // invisible
		gui.ILabel7.setVisible(false); // invisible
		gui.ILabel8.setVisible(false); // invisible
		gui.codeButtonPanel.setVisible(false); // invisible
		gui.highestScorePanel.setVisible(false); // invisible
		gui.pointPanel.setVisible(false); // invisible
		gui.shopButtonPanel.setVisible(false); // invisible
		gui.shopButtonsPanel.setVisible(false); // Invisible
		gui.jtf.setVisible(false); // invisible
		
	}
	
	/*
	 * Same thing as showTitleScreen but for game win
	 */
	public void showGameWinScreen() {
		gui.titleNamePanel.setVisible(false); // invisible
		gui.startButtonPanel.setVisible(false); // invisible
		gui.c1.setVisible(false); // invisible
		gui.c2.setVisible(false); // invisible
		gui.c3.setVisible(false); // invisible
		gui.c4.setVisible(false); // invisible
		gui.c5.setVisible(false); // invisible
		gui.c6.setVisible(false); // invisible
		gui.GameOverPanel.setVisible(false); // invisible
		gui.cardOne.setVisible(false); // invisible
		gui.cardTwo.setVisible(false); // invisible
		gui.cardThree.setVisible(false); // invisible
		gui.cardFour.setVisible(false); // invisible
		gui.hpPanel.setVisible(false); // invisible
		gui.goButton.setVisible(false); // invisible
		gui.ehpPanel.setVisible(false); // invisible
		gui.gameWinPanel.setVisible(true); // VISIBLE
		gui.retryButtonPanel.setVisible(false); // invisible
		gui.scorePanel.setVisible(false); // invisible
		gui.InstructionButtonPanel.setVisible(false); // invisible
		gui.backButtonPanel.setVisible(false); // invisible
		gui.ILabel1.setVisible(false); // invisible
		gui.ILabel2.setVisible(false); // invisible
		gui.ILabel3.setVisible(false); // invisible
		gui.ILabel4.setVisible(false); // invisible
		gui.ILabel5.setVisible(false); // invisible
		gui.ILabel6.setVisible(false); // invisible
		gui.ILabel7.setVisible(false); // invisible
		gui.ILabel8.setVisible(false); // invisible
		gui.codeButtonPanel.setVisible(false); // invisible
		gui.highestScorePanel.setVisible(false); // invisible
		gui.pointPanel.setVisible(false); // invisible
		gui.shopButtonPanel.setVisible(false); // invisible
		gui.shopButtonsPanel.setVisible(false); // Invisible
		gui.jtf.setVisible(false); // invisible
		
	}
	
	/*
	 * Same thing as showTitleScreen but for the instructions
	 */
	public void showInstructionsScreen(){
		
		gui.titleNamePanel.setVisible(false); // invisible
		gui.startButtonPanel.setVisible(false); // invisible
		gui.c1.setVisible(false); // invisible
		gui.c2.setVisible(false); // invisible
		gui.c3.setVisible(false); // invisible
		gui.c4.setVisible(false); // invisible
		gui.c5.setVisible(false); // invisible
		gui.c6.setVisible(false); // invisible
		gui.GameOverPanel.setVisible(false); // invisible
		gui.cardOne.setVisible(false); // invisible
		gui.cardTwo.setVisible(false); // invisible
		gui.cardThree.setVisible(false); // invisible
		gui.cardFour.setVisible(false); // invisible
		gui.hpPanel.setVisible(false); // invisible
		gui.goButton.setVisible(false); // invisible
		gui.ehpPanel.setVisible(false); // invisible
		gui.gameWinPanel.setVisible(false); // invisible
		gui.retryButtonPanel.setVisible(false); // invisible
		gui.scorePanel.setVisible(false); // invisible
		gui.InstructionButtonPanel.setVisible(false); // invisible
		gui.backButtonPanel.setVisible(true); // VISIBLE
		gui.ILabel1.setVisible(true); // VISIBLE
		gui.ILabel2.setVisible(true); // VISIBLE
		gui.ILabel3.setVisible(true); // VISIBLE
		gui.ILabel4.setVisible(true); // VISIBLE
		gui.ILabel5.setVisible(true); // VISIBLE
		gui.ILabel6.setVisible(true); // VISIBLE
		gui.ILabel7.setVisible(true); // VISIBLE
		gui.ILabel8.setVisible(true); // VISIBLE
		gui.codeButtonPanel.setVisible(false); // invisible
		gui.highestScorePanel.setVisible(false); // invisible
		gui.pointPanel.setVisible(false); // invisible
		gui.shopButtonPanel.setVisible(false); // invisible
		gui.shopButtonsPanel.setVisible(false); // Invisible
		gui.jtf.setVisible(false); // invisible
		
	}
	
	/*
	 * Same thing as for showTitleScreen but for shop
	 */
	public void showShopScreen() {
		
		gui.titleNamePanel.setVisible(false); // invisible
		gui.startButtonPanel.setVisible(false); // invisible
		gui.c1.setVisible(false); // invisible
		gui.c2.setVisible(false); // invisible
		gui.c3.setVisible(false); // invisible
		gui.c4.setVisible(false); // invisible
		gui.c5.setVisible(false); // invisible
		gui.c6.setVisible(false); // invisible
		gui.GameOverPanel.setVisible(false); // invisible
		gui.cardOne.setVisible(false); // invisible
		gui.cardTwo.setVisible(false); // invisible
		gui.cardThree.setVisible(false); // invisible
		gui.cardFour.setVisible(false); // invisible
		gui.hpPanel.setVisible(false); // invisible
		gui.goButton.setVisible(false); // invisible
		gui.ehpPanel.setVisible(false); // invisible
		gui.gameWinPanel.setVisible(false); // invisible
		gui.retryButtonPanel.setVisible(false); // invisible
		gui.scorePanel.setVisible(false); // invisible
		gui.InstructionButtonPanel.setVisible(false); // invisible
		gui.backButtonPanel.setVisible(true); // invisible
		gui.ILabel1.setVisible(false); // invisible
		gui.ILabel2.setVisible(false); // invisible
		gui.ILabel3.setVisible(false); // invisible
		gui.ILabel4.setVisible(false); // invisible
		gui.ILabel5.setVisible(false); // invisible
		gui.ILabel6.setVisible(false); // invisible
		gui.ILabel7.setVisible(false); // invisible
		gui.ILabel8.setVisible(false); // invisible
		gui.codeButtonPanel.setVisible(false); // invisible
		gui.highestScorePanel.setVisible(false); // invisible
		gui.pointPanel.setVisible(true); // invisible
		gui.shopButtonPanel.setVisible(false); // invisible
		gui.shopButtonsPanel.setVisible(true); // Invisible
		gui.jtf.setVisible(false); // invisible
		
	}
}


