import java.util.*;

class Deck {
	public int a = 0;//�]�w�ܼ�
	public int b = 0;
	private ArrayList<Card> cards;//�{�����P
	ArrayList<Card> useCard = new ArrayList<Card>();
	ArrayList<Card> openCard=new ArrayList<Card>();//�s�񥴶}���P�A�B�b�~�P�ɭn�M��
	public int nUsed = 0;//�����X�i�P	
	// TODO: Please implement the constructor (30 points)
	public Deck(int nDeck) {
		cards = new ArrayList<Card>();//��l��
		// 1 Deck have 52 cards, https://en.wikipedia.org/wiki/Poker
		// Hint: Use new Card(x,y) and 3 for loops to add card into deck
		// Sample code start
		// Card card=new Card(1,1); ->means new card as clubs ace
		// cards.add(card);
		// Sample code end
//total card ����
		for (int i = 0; i < nDeck; i++) {//�������
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
					}//����������A�M��W�[�d
					
					

				}
		}
		}

	}

	public Deck() {//���ثغc�l
		// TODO Auto-generated constructor stub
	}

	public Card getOneCard(boolean isOpened) {
		Card n = new Card(null, 0);//�Ĥ@�Ӧ�m���P
		Random nc = new Random();
		if (nUsed == 52) {//�S�P�N�|�~�P
			shuffle();
		}

		n = cards.get(nc.nextInt(52));//�q52�i�̮�
		for (int j = 0; j < useCard.size(); j++) {//���b�ϥΪ��P
			while (n.equals(useCard.get(j))) {
				n = cards.get(nc.nextInt(52));
			}//
		}
		/*
		 * while(n.equals(usedCard)) { n=cards.get(nc.nextInt(52)); }
		 */
		useCard.add(a, n);//����
		nUsed++;//�h�@�i�P
		a=a+1;
		
		
		if(isOpened==true) {
			openCard.add(n);
		}//�⥴�}���P�[�J
		
		return n;
	}


	public void shuffle() {//�~�P
		Random rnd = new Random();
		int rp = rnd.nextInt(52);//�`�@�P��
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

		useCard.set(b, emp);//���m
		nUsed = 0;
		//�M��openCard�����e
		for(int i=0;i<openCard.size();i++) {
			openCard.set(i, null);
		}
	}

	// TODO: Please implement the method to print all cards on screen (10 points)
	public void printDeck() {//�L�X�Ҧ��P
		// Hint: print all items in ArrayList<Card> cards,
		// TODO: please implement and reuse printCard method in Card class (5 points)
		for (int i = 0; i < 52; i++) {
			//
			// Card n=cards.get(i);
			// //System.out.println(n.getSuit()+","+n.getRank());
			// n.printCard();
			Card n = new Card(null, 0);//�гy�s���P
			n = cards.get(i);
			n.printCard();

		}

	}
	//�^�ǩҦ����P
	public ArrayList<Card> getAllCards() {
	return cards;
	}
	//�^�ǥ��}���P
	public ArrayList<Card> getOpenedCard(){
	return openCard;
	}
}