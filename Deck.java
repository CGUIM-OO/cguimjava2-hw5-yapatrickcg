import java.util.*;

class Deck {
	public int a = 0;//設定變數
	public int b = 0;
	private ArrayList<Card> cards;//現有的牌
	ArrayList<Card> useCard = new ArrayList<Card>();
	ArrayList<Card> openCard=new ArrayList<Card>();//存放打開的牌，且在洗牌時要清空
	public int nUsed = 0;//紀錄幾張牌	
	// TODO: Please implement the constructor (30 points)
	public Deck(int nDeck) {
		cards = new ArrayList<Card>();//初始化
		// 1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		// Hint: Use new Card(x,y) and 3 for loops to add card into deck
		// Sample code start
		// Card card=new Card(1,1); ->means new card as clubs ace
		// cards.add(card);
		// Sample code end
//total card 產生
		for (int i = 0; i < nDeck; i++) {//全部花色
		for(int x=0;x<4;x++) {
				for (int y = 1; y < 14; y++) {
					if(x==0) {
						Card card = new Card(Card.Suit.Clubs, y);
						cards.add(card);
					}else if(x==1) {
						Card card=new Card(Card.Suit.Diamonds,y);
						cards.add(card);
					}else if(x==2) {
						Card card=new Card(Card.Suit.Hearts,y);
						cards.add(card);
					}else if(x==3) {
						Card card=new Card(Card.Suit.Spades,y);
						cards.add(card);
					}//花色種類分辨，然後增加卡
					
					

				}
		}
		}

	}

	public Deck() {//內建建構子
		// TODO Auto-generated constructor stub
	}

	public Card getOneCard(boolean isOpened) {
		Card n = new Card(null, 0);//第一個位置的牌
		Random nc = new Random();
		if (nUsed == 52) {//沒牌就會洗牌
			shuffle();
		}

		n = cards.get(nc.nextInt(52));//從52張裡拿
		for (int j = 0; j < useCard.size(); j++) {//正在使用的牌
			while (n.equals(useCard.get(j))) {
				n = cards.get(nc.nextInt(52));
			}//
		}
		/*
		 * while(n.equals(usedCard)) { n=cards.get(nc.nextInt(52)); }
		 */
		useCard.add(a, n);//紀錄
		nUsed++;//多一張牌
		a=a+1;
		
		
		if(isOpened==true) {
			openCard.add(n);
		}//把打開的牌加入
		
		return n;
	}


	public void shuffle() {//洗牌
		Random rnd = new Random();
		int rp = rnd.nextInt(52);//總共牌數
		Card rtemp = new Card(null, 0);
		Card emp = new Card(null, 0);

		for (int i = 0; i < 52; i++) {
			while (i == rp) {
				rp = rnd.nextInt(52);
			}
			rtemp = cards.get(rp);
			cards.set(rp, cards.get(i));
			cards.set(i, rtemp);

		}

		useCard.set(b, emp);//重置
		nUsed = 0;
		//清空openCard的內容
		for(int i=0;i<openCard.size();i++) {
			openCard.set(i, null);
		}
	}

	// TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck() {//印出所有牌
		// Hint: print all items in ArrayList<Card> cards,
		// TODO: please implement and reuse printCard method in Card class (5 points)
		for (int i = 0; i < 52; i++) {
			//
			// Card n=cards.get(i);
			// //System.out.println(n.getSuit()+","+n.getRank());
			// n.printCard();
			Card n = new Card(null, 0);//創造新的牌
			n = cards.get(i);
			n.printCard();

		}

	}
	//回傳所有的牌
	public ArrayList<Card> getAllCards() {
	return cards;
	}
	//回傳打開的牌
	public ArrayList<Card> getOpenedCard(){
	return openCard;
	}
}