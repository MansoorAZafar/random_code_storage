package fpg;
import java.util.*;

/*
 * Makes the cards that are used in combat
 * 
 * @mansoor zafar
 * @2022-06-12
 */
public class Card {
	//Instance Variables
	int attack, defense, cooldown, hp;
	
	//NOTE: DEF/DEFENSE WAS REMOVED (JUST KEPT IT CUZ LAZY)
	
	/*
	 * Input: integer attack, integer defense, integer cool-down, integer health
	 * Inside: create the respective card
	 * Output: the respective card
	 */
	public void makeCard(int att, int def, int cd, int hp){
		if(hp != 0) {
			createHPCard(hp, cd); // create a health card if the health value is not 0
		}
		
		if(att == 0){
			createDefenseCard(def, cd); // if attack is 0 make a defense card {DROPPED L}
		}
		else{
			createAttackCard(att, cd); // make an attack card if none else
		}
	}
	
	/*
	 * Input: integer x, integer cd
	 * Inside: set attack = to x & set cooldown = cd
	 * Output: the attack and cooldown
	 */
	public void createAttackCard(int x, int cd){
		attack = x;
		cooldown = cd;
	}
	
	/*
	 * Input: integer x
	 * Inside: attack = x
	 * Output: the attack
	 */
	public void setAttack(int x) {
		attack = x;
	}
	
	/*
	 * Input: n/a
	 * Inside: returns the attack
	 * Output: attack
	 */
	public int getAttack(){
		return attack;
	}
	
	/*
	 * Input: n/a
	 * Inside: n/a
	 * Output: cooldown
	 */
	public int getCooldown(){
		return cooldown;
	}
	
	/*
	 * Input: integer def and integer cd       -----            -----
	 * Inside: defense = def and cooldown = cd |     DROPPED L      |
	 * Output: the def and cd                  -----            -----
	 */
	public void createDefenseCard(int def, int cd){
		defense = def;
		cooldown = cd;
	}
	
	/*
	 * Input: n/a
	 * Inside: n/a
	 * Output: defense
	 */
	public int getDefense(){
		return defense;
	}
	
	/*
	 * Input: integer hp & integer cd
	 * Inside: set hp to hp and set the cooldown
	 * Output: the hp and cooldown
	 */
	public void setHp(int hp, int cd) {
		this.hp = hp;
		cooldown = cd;
	}
	
	/*
	 * Input: integer hp &
	 * Inside: set hp to hp 
	 * Output: the hp 
	 */
	public void setHPs(int hp) {
		this.hp = hp;
	}
	
	/*
	 * Input: n/a
	 * Inside: n/a
	 * Output: hp
	 */
	public int getHp() {
		return hp;
	}
	
	/*
	 * Input: integer hp and integer cd
	 * Inside: card hp = hp and card cooldown = cd
	 * Output: card with hp and cooldown
	 */
	public void createHPCard(int hp, int cd) {
		this.hp = hp;
		cooldown = cd;
	}
	
	/*
	 * Input: integer i
	 * Inside: set the cooldown to i
	 * Output: the card cooldown
	 */
	public void setCooldown(int i) {
		cooldown = i;
		
	}	
}



