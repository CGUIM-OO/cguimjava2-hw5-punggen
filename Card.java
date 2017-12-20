

public class Card {
	//private int suit; //Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; //1~13
	/**
	 * @param s suit
	 * @param r rank
	 */
	public enum Suit{Club, Diamond, Heart, Spade};
	private Suit suit; //using enum 
	public Card(Suit s, int value){
		suit=s;
		rank= value;
	}	
	//TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10 for rank)
	public void printCard(){
		//Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as Clubs Ace
		
		switch(suit)//把數字改英文
		{ 
		case Club:
			switch(rank) {
			case 1:
				System.out.println(suit+"-"+rank + " = Ace of Clubs");
				break;
			case 11:
				System.out.println(suit+"-"+rank + " = J of Clubs");
				break;
			case 12:
				System.out.println(suit+"-"+rank + " = Q of Clubs");
				break;
			case 13:
				System.out.println(suit+"-"+rank + " = K of Clubs");
				break;
			default:
				System.out.println(suit+"-"+rank + " is "+ rank +" of Clubs");
				break;
			}
			break;
		case Diamond:
			switch(rank) {
			case 1:
				System.out.println(suit+"-"+rank + " = Ace of Diamonds");
				break;
			case 11:
				System.out.println(suit+"-"+rank + " = J of Diamonds");
				break;
			case 12:
				System.out.println(suit+"-"+rank + " = Q of Diamonds");
				break;
			case 13:
				System.out.println(suit+"-"+rank + " = K of Diamonds");
				break;
			default:
				System.out.println(suit+"-"+rank + " = "+ rank +" of Diamonds");
				break;
			}
			break;
		case Heart:
			switch(rank) {
			case 1:
				System.out.println(suit+"-"+rank + " = Ace of Hearts");
				break;
			case 11:
				System.out.println(suit+"-"+rank + " = J of Hearts");
				break;
			case 12:
				System.out.println(suit+"-"+rank + " = Q of Hearts");
				break;
			case 13:
				System.out.println(suit+"-"+rank + " = K of Hearts");
				break;
			default:
				System.out.println(suit+"-"+rank + " = "+ rank +" of Hearts");
				break;
			}
			break;
		case Spade:
			switch(rank) {
			case 1:
				System.out.println(suit+"-"+rank + " = Ace of Spade");
				break;
			case 11:
				System.out.println(suit+"-"+rank + " = J of Spade");
				break;
			case 12:
				System.out.println(suit+"-"+rank + " = Q of Spade");
				break;
			case 13:
				System.out.println(suit+"-"+rank + " = K of Spade");
				break;
			default:
				System.out.println(suit+"-"+rank + " = "+ rank +" of Spade");
				break;
			}
			break;
			}
	
		}
	public Suit getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
}
