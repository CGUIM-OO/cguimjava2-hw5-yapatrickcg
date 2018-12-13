import java.util.ArrayList;
public class Table {
	final int MaxPlayer=4;
	Deck dd;
  Dealer dr;
	private int[] pos_betArray=new int[MaxPlayer];
	private Player[] pr;
	//�����
	public Table(int tbl) {
		dd = new Deck(tbl);
		pr = new Player[MaxPlayer];//�s�W�̤j���a�ƶq
	}
	//�P���m.�H
	public void set_player(int pos, Player p) {
		pr[pos] = p;
	}
	//�^�ǩҦ��b�P��W��player
	public Player[] get_player(){
		return pr;
	}
	//�NDealer���P��W
	public void set_dealer(Dealer d) {
		dr = new Dealer();
		dr = d;
	}
	//�^��dealer���}�����i�P
	public Card get_face_up_card_of_dealer()
	{
		Card hr = dr.getOneRoundCard().get(1);
		return hr;
	}
	private void ask_each_player_about_bets()
	{
		int i = 0;
		for (Player o :pr) {
			o.sayHello();//���۩I
			pos_betArray[i] = o. makeBet();//�U�`
			i++;
		}
	}
	private void distribute_cards_to_dealer_and_players()
	{
		dd.shuffle();
		for (Player p : pr) {
			ArrayList<Card> pd = new ArrayList<Card>();
			pd.add(dd.getOneCard(true));//��i���}���P
			pd.add(dd.getOneCard(true));
			p.setOneRoundCard(pd);
		}
		ArrayList<Card> DC = new ArrayList<Card>();
		DC.add(dd.getOneCard(true));//�@�i�\�ۡA�@�i���}
		DC.add(dd.getOneCard(false));
		dr.setOneRoundCard(DC);
		System.out.print("Dealer's face up card is ");
		dr.getOneRoundCard().get(1).printCard();
	}
	private void ask_each_player_about_hits()
	{
		for (Player p : pr) {
			boolean hit = false;
			ArrayList<Card> playerCard = new ArrayList<Card>();
			System.out.print(p.getName()+"'s cards now:");
			for(Card c : p.getOneRoundCard())
				c.printCard();
			do {
				hit = p.hit_me(this);
				if (hit) {
					playerCard = p.getOneRoundCard();//���a����@�����d
					playerCard.add(dd.getOneCard(true));
					System.out.print("Hit!" + p.getName() + "'s cards now:");
					for (Card c : playerCard)//�[�J�P��������
						c.printCard();
					p.setOneRoundCard(playerCard);//���s�]�w���a�d��
					if (p.getTotalValue() > 21)
						hit = false;//�z�F���ܪ�������U��
				} else {
					System.out.println("Pass hit!");
				}
			} while (hit);
			System.out.println(p.getName() + "'s hit is over!");
		}
	}
	//�߰ݬO�_�n�P
	private void ask_dealer_about_hits() {
		boolean hit = false;
		ArrayList<Card> dealerCard = new ArrayList<Card>();
		do {
			hit = dr.hit_me(this);//Method �̭��� hit
			if (hit) {
				dealerCard = dr.getOneRoundCard();
				dealerCard.add(dd.getOneCard(true));//�s�W���}���P
				dr.setOneRoundCard(dealerCard);
				if (dr.getTotalValue() > 21)
					hit = false;
			}
		} while (hit);//�P�_�O�_���T
		System.out.println("Dealer's hit is over!");
	}
	private void calculate_chips() {
		int pe, de = dr.getTotalValue();
		char result;
		System.out.print("Dealer's card value is " + de + ", Cards:");//�L�X���a���I�ƩM�P
		dr.printAllCard();
		for (int n = 0; n < pr.length; n++) {
			Player p = pr[n];
			pe = p.getTotalValue();
			System.out.print(p.getName() + "'s Cards:");
			p.printAllCard();
			System.out.print(p.getName() + "'s card value is " + pe);
			//���a��C�@�Ӫ��a���P��
			if (pe > 21) {
				if (de > 21)
					result = 'j';
				else
					result = 'u';
			} else {
				if (de > 21)
					result = 'x';
				else if (de >pe)
					result = 'u';
				else if (de == pe)
					result = 'j';
				else
					result = 'x';
			}
			switch (result) {
			//�P�_�O�����
			//���aĹ
			case 'u':
				p.increase_chips(-pos_betArray[n]);
				System.out.print(
						", Loss " + pos_betArray[n] + " Chips, the Chips now is:" + p.get_current_chips() + "\n");
				break;
				//���a��
			case 'x':
				p.increase_chips(pos_betArray[n]);
				System.out
						.print(", Get " + pos_betArray[n] + " Chips, the Chips now is:" + p.get_current_chips() + "\n");
				break;
				//�M��
			case 'j':
				System.out.print(", Chips have no change! The Chips now is:" + p.get_current_chips() + "\n");
				break;
			}

		}
	}
	
	
	public int[] get_players_bet()
	{
		return pos_betArray;
	}
	
	
	
	public void play(){
		ask_each_player_about_bets();
		distribute_cards_to_dealer_and_players();
		ask_each_player_about_hits();
		ask_dealer_about_hits();
		calculate_chips();
	}
	

	
	

}
