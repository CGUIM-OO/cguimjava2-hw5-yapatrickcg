class Card {
	public enum Suit {Clubs, Diamonds, Hearts, Spades}//�Ҧ����
    Suit suit; // Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; // 1~13
    /**
	 * @param s suit ��
	 * @param r rank  point
	 */
	public Card(Suit s, int r) {
		suit=s;
		rank = r;
	}//�غc�l

	// TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10
	// for rank)
	public void printCard() {
		// Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as
		// Clubs Ace
		if(rank==1) {
			System.out.println(getSuit()+","+"Ace");//��ȬO1�ɦ���
		}else {
		 System.out.println(getSuit() + "," + getRank());//���o�������
		}
	}

	public Suit getSuit() {//���o�Ҧ��C�|������
	return suit;
	}

	public int getRank() {//���o�Ҧ��C�|������
	return rank;
	}
}