import java.util.ArrayList;
public class Table {
	private Deck deckcard;//存放所有的牌
	private Player[] allP;//存放所有的玩家
	private Dealer dealer;//存放一個莊家
	private int[] pos_betArray=new int[MAXPLAYER]; //存放每個玩家在一局下的注(instance field)
	private int pos,ndeck;
	static final int MAXPLAYER =4 ;
	
	private ArrayList<ArrayList<Card>> pCards = new ArrayList<ArrayList<Card>>();
	private ArrayList<Card> p1=new ArrayList<Card>();
	private ArrayList<Card> p2=new ArrayList<Card>();
	private ArrayList<Card> p3=new ArrayList<Card>();
	private ArrayList<Card> p4=new ArrayList<Card>(); //存玩家排
	private ArrayList<Card> d0=new ArrayList<Card>(); //存dealer排
	
	
	public Table(int nDeck ) {
		ndeck= nDeck;
		deckcard=new Deck(nDeck);
		allP=new Player[MAXPLAYER];
		
		pCards.clear();
		pCards.add(p1);
		pCards.add(p2);
		pCards.add(p3);
		pCards.add(p4);
	}
	public void set_player(int pos, Player p) {
		//try { pos不能多於4
		allP[pos]=p;
		//}catch(Exception ex){}
	}
	public Player[] get_player() {
		return allP;
	}
	public void set_dealer(Dealer d)   {
		dealer=d;
	}
	public Card get_face_up_card_of_dealer() {
		//回傳dealer打開的那張牌，也就是第二張牌
		return d0.get(1);
	}
	private void ask_each_player_about_bets()
	{
		for(int i=0;i<4;i++)
		{
			allP[i].sayHello(); //說哈樓
			allP[i].makeBet();  //下注
			pos_betArray[i] =allP[i].makeBet(); //每個玩家下的注要存在pos_betArray供之後使用
		}
	}
	private void ask_dealer_about_hits() {
		boolean hit;
		if(dealer.getTotalValue()>21)
		{
			System.out.println("dealer's point is:"+dealer.getTotalValue()+"   booooomed");
			System.out.println("Player win!! \n");
		}
		else	//draw
		{
			do{   //draw one and loop asking 
				hit=dealer.hit_me(this); //this table  ; call function which dealer's decsion
				if(hit){
					d0.add(deckcard.getOneCard(true));//get one card and add in alldeckcards and dealer's list
					dealer.setOneRoundCard(d0);
					
					System.out.print("Hit! ");
					System.out.println("Dealer's Cards now:");
					for(Card c : d0){
						c.printCard();
					}
					System.out.println("****");
				}
				else{
					System.out.println("Dealer Pass ,Final Card:");
					for(Card c : d0){
						c.printCard();
					}
					System.out.println("****");
				}
			}while(hit);
		}
	}
	private void calculate_chips() {
		System.out.println("Dealer point :   "+dealer.getTotalValue()+" , Cards:");
		dealer.printAllCard();

	//compute player's points and judge 	
		for(int i=0;i<allP.length;i++) {
			System.out.println(allP[i].getName()+" point : "+allP[i].getTotalValue()+
					", Cards :");
			allP[i].printAllCard();
			if(dealer.getTotalValue()>21) {
				System.out.println("Dealer is Boomed! ");
				if(allP[i].getTotalValue()<=21){            //player win by dealer boomed
					allP[i].increaseChips(pos_betArray[i]);
					System.out.println(allP[i].getName()+" Get "+pos_betArray[i]+
							" Chips, the Chips now is: "+allP[i].getCurrentChips()+"\n---------------");
				}
				else if(allP[i].getTotalValue()>21) {		//player and dealer boomed (even)
					System.out.println(allP[i].getName()+" is Boomed, even with the dealer");
					System.out.println("Current chip: "	+allP[i].getCurrentChips()+"\n---------------");
				}
			}
			else if(dealer.getTotalValue()<=21) {
				if(allP[i].getTotalValue()<=21){ //player > dealer
					if(allP[i].getTotalValue()>dealer.getTotalValue()) 
					{	//player > dealer
						allP[i].increaseChips(pos_betArray[i]);
						System.out.println(allP[i].getName()+" Wins "+pos_betArray[i]+
						" Chips, Current Chip: "+allP[i].getCurrentChips()+"\n---------------");
					}
					else if(allP[i].getTotalValue()==dealer.getTotalValue()) 
					{//player == dealer
						System.out.println(",Even! Current Chip: "+allP[i].getCurrentChips()+"\n---------------");
					}
					else if(allP[i].getTotalValue()<dealer.getTotalValue())
					{	//player < dealer
						allP[i].increaseChips(-pos_betArray[i]);
						System.out.println(allP[i].getName()+" Loss "+
						pos_betArray[i]+" Chips, Current Chip: "+allP[i].getCurrentChips()+"\n---------------");
					}
					
				}
				else if(allP[i].getTotalValue()>21) 
				{//player boomed and dealer do not
					System.out.println(allP[i].getName()+" is Boomed!");
					allP[i].increaseChips(- pos_betArray[i]);
					System.out.println(allP[i].getName()+" Loss "+pos_betArray[i]+
							" Chips, Current Chip: "+allP[i].getCurrentChips()+"\n---------------");
				}
			}
		}
	}
	private void ask_each_player_about_hits() {
		for (int i=0;i<allP.length;i++) {
			if(allP[i].getTotalValue()>21) {//boomed and cant ask for hit
				System.out.println(allP[i].getName()+", is Boomed!");
				System.out.println(allP[i].getName()+", Final Card:");
				for(Card c : pCards.get(i)){
					c.printCard();
				}
				System.out.println("****");
			}
			else {
				boolean hit=false;
				do{ 			//loop for ask card until boomed or hit==false
					hit=allP[i].hit_me(this); 
					if(hit){
						pCards.get(i).add(deckcard.getOneCard(true));//get one open-card
						allP[i].setOneRoundCard(pCards.get(i));//throw cards into arraylist
						System.out.print("Hit! ");
						System.out.println(  allP[i].getName()+"'s Cards now:");
						
						for(Card c : pCards.get(i)){
							c.printCard();
						}
						System.out.println("****");
					}
					else{
						System.out.println(allP[i].getName()+", Pass hit!");
						System.out.println(allP[i].getName()+", Final Card:");
						for(Card c : pCards.get(i)){
							c.printCard();
						}
						System.out.println("****");
					}
				}while(hit);//when true do it again
			}
		}
	}
	private void distribute_cards_to_dealer_and_players() {
		d0.add(deckcard.getOneCard(false));//give 0and1 card for dealer
		d0.add(deckcard.getOneCard(true));
		dealer.setOneRoundCard(d0);
		
		System.out.println("Dealer's face up card is: ");
		get_face_up_card_of_dealer().printCard();
		
		System.out.println("---------------");
				
		for(int i=0;i<allP.length;i++)
		{//give players 2 open cards
			pCards.get(i).add(deckcard.getOneCard(true));
			pCards.get(i).add(deckcard.getOneCard(true));
			allP[i].setOneRoundCard(pCards.get(i)); 
		}
			
	}
	
	public int[] get_players_bet() {
		return pos_betArray;
	}
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	/////////////////////////////
	private int playersNO_more_than_dealer() {
		int count=0;
		for(int i=0;i<4;i++) 
		{
			if(allP[i].getTotalValue()>dealer.getTotalValue())
				count++;			
		}
	    return count;		
	}
	private int players_boomed()
	{
		int count=0;
		for(int i=0;i<4;i++) 
		{
			if(allP[i].getTotalValue()>21)
				count++;			
		}
	    return count;	
	}
	private int playersNO_less_than_dealer() {
		int count=0;
		for(int i=0;i<4;i++) 
		{
			if(allP[i].getTotalValue()<dealer.getTotalValue())
				count++;			
		}
	    return count;		
	}
}
