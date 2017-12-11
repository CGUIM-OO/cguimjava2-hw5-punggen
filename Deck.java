
import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cards;
	public ArrayList<Card> usedCard;//the cards that been used 
	public int nUsed;//count cards that been used
	private ArrayList<Card> openCard;//hw4 updated �s�񦹰ƵP���Ҧ����}���P�A�~�P�ɭn���m
	//TODO: Please implement the constructor (30 points)
	public Deck(int nDeck){
		cards=new ArrayList<Card>();
		usedCard=new ArrayList<Card>();
		openCard=new ArrayList<Card>();
		//1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		//Hint: Use new Card(x,y) and 3 for loops to add card into deck
		//Sample code start
		//Card card=new Card(1,1); ->means new card as clubs ace
		//cards.add(card);
		//Sample code end
		
		for(int i =0;i<nDeck ; i++)  //N of decks
		{
			for (Card.Suit s : Card.Suit.values()) //4colors //update in HW3 enum
			{
				for (int y =1; y<=13 ;y++)  //13�i
				{
					Card card = new Card(s,y);
					cards.add(card);
										
				}
			}
		}

	}	
	//TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck(){
		//Hint: print all items in ArrayList<Card> cards, 
		//TODO: please implement and reuse printCard method in Card class (5 points)
		int DC=1; //�p��ĴX�Ƽ��J�P���ƶq
		for(int i=0;i<cards.size();i++) {
			if(i%52==0) { //print deck number when a new deck start
				System.out.println("Deck-"+DC); //�L�X��DC�ƵP
				DC++;
				}
			cards.get(i).printCard();   //print�Ҧ��P
			}
		}
	public void shuffle() {
		Random rnd = new Random();  
		int j;
		Card a,b,temp=null;
		for(int i =0;i<cards.size();i++) //shuffle every single cards
		{
			j = rnd.nextInt(cards.size());
			a=cards.get(i); //get two cards
			b=cards.get(j); 
			temp=a;  //swap two card
			a=b;
			b=temp;
		}
		 usedCard.clear(); //clear the usedCard arraylist
		 nUsed=0; //reset the count of used card
		 openCard.clear();//reset the cards that opened when shuffle
	}
	public Card getOneCard(boolean isOpened) {
		//����@�i�P�A�ק�즳method�A�[�JisOpened�ѼơA
		//�M�w�o�X�h���P�O�}���٬O�\�_�Ӫ��A�Y�O�}�۪��P�A�[�JopenCard�C (5pt)
		if(nUsed==cards.size()) // if all card had used , shuffle 
			shuffle();
		Random rnd =new Random();
		usedCard=new ArrayList<Card>();
		
		int k =rnd.nextInt(cards.size()); //get a random card number 
		Card draw = cards.get(k);  //get 'that' random card
		
		nUsed++;   //add 1 for count of used card
		usedCard.add(draw);  //add the drawed card in used arraylist
		if(isOpened)	//if the card is opened
		{
			openCard.add(draw); //then add to "openCard" list
		}
		return draw;
	}
	public ArrayList<Card> getAllCards(){
		return cards;
	}
	public ArrayList<Card> getOpenedCard(){
		return openCard;
	}
}
