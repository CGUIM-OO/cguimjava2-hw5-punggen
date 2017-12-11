import java.util.ArrayList;
import java.util.Random;
public class Player extends Person {
	 private String name;
	 private int chips;
	 private int bet;
	 private ArrayList<Card> oneRoundCard;
	 public Player(String name, int chips) {
	//Player constructor穝糤Playerン惠璶﹎局Τ膚絏单ㄢ把计
		 this.name=name;
		 this.chips=chips;
		 
	 }
	 public String getName() {
		 return name;
	 }
	 public int makeBet() {
		 bet = 1; //Default the base bet is 1
		 if( chips==0) //out of money
		 {
			 bet =0;//no money can bet
		 }
		 return bet;
	 }
	 public void setOneRoundCard(ArrayList<Card> cards) {
		 oneRoundCard=cards;//砞﹚礟Ы┮眔 setter
	 }
	 public boolean hitMe() {//琌璶礟
		 if (getTotalValue()<17) //if less than 17 ,get one more card
		 {
			return true; 
		 }
		 else
			 return false;
	 }
	 public int getTotalValue() {
		 int point = 0;//the total point in this game
		 for(int n=0; n<oneRoundCard.size(); n++)
		 {
			 point += oneRoundCard.get(n).getRank();
		 }
		 return point;
	 }
	 public int getCurrentChips() {
		 return chips;
	 }
	 public void increaseChips (int diff) {
		 chips+= diff;
	 }
	 public void sayHello() {
		 System.out.println("Hello, I am " + name + ".");
		 System.out.println("I have " + chips + " chips.");

	 }
	@Override
	public boolean hit_me(Table table) {
		// TODO Auto-generated method stub
		return false;
	}
}

