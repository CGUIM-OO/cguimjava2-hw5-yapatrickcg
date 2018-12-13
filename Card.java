class Card {
	public enum Suit {Clubs, Diamonds, Hearts, Spades}//所有花色
    Suit suit; // Definition: 1~4, Clubs=1, Diamonds=2, Hearts=3, Spades=4
	private int rank; // 1~13
    /**
	 * @param s suit 花
	 * @param r rank  point
	 */
	public Card(Suit s, int r) {
		suit=s;
		rank = r;
	}//建構子

	// TODO: 1. Please implement the printCard method (20 points, 10 for suit, 10
	// for rank)
	public void printCard() {
		// Hint: print (System.out.println) card as suit,rank, for example: print 1,1 as
		// Clubs Ace
		if(rank==1) {
			System.out.println(getSuit()+","+"Ace");//當值是1時成立
		}else {
		 System.out.println(getSuit() + "," + getRank());//取得花色跟種類
		}
	}

	public Suit getSuit() {//取得所有列舉的項目
	return suit;
	}

	public int getRank() {//取得所有列舉的項目
	return rank;
	}
}