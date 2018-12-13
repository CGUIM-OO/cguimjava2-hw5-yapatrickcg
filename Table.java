import java.util.ArrayList;
public class Table {
	final int MaxPlayer=4;
	Deck dd;
  Dealer dr;
	private int[] pos_betArray=new int[MaxPlayer];
	private Player[] pr;
	//實體化
	public Table(int tbl) {
		dd = new Deck(tbl);
		pr = new Player[MaxPlayer];//新增最大玩家數量
	}
	//牌桌位置.人
	public void set_player(int pos, Player p) {
		pr[pos] = p;
	}
	//回傳所有在牌桌上的player
	public Player[] get_player(){
		return pr;
	}
	//將Dealer放到牌桌上
	public void set_dealer(Dealer d) {
		dr = new Dealer();
		dr = d;
	}
	//回傳dealer打開的那張牌
	public Card get_face_up_card_of_dealer()
	{
		Card hr = dr.getOneRoundCard().get(1);
		return hr;
	}
	private void ask_each_player_about_bets()
	{
		int i = 0;
		for (Player o :pr) {
			o.sayHello();//打招呼
			pos_betArray[i] = o. makeBet();//下注
			i++;
		}
	}
	private void distribute_cards_to_dealer_and_players()
	{
		dd.shuffle();
		for (Player p : pr) {
			ArrayList<Card> pd = new ArrayList<Card>();
			pd.add(dd.getOneCard(true));//兩張打開的牌
			pd.add(dd.getOneCard(true));
			p.setOneRoundCard(pd);
		}
		ArrayList<Card> DC = new ArrayList<Card>();
		DC.add(dd.getOneCard(true));//一張蓋著，一張打開
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
					playerCard = p.getOneRoundCard();//玩家拿到一輪的卡
					playerCard.add(dd.getOneCard(true));
					System.out.print("Hit!" + p.getName() + "'s cards now:");
					for (Card c : playerCard)//加入牌局的順序
						c.printCard();
					p.setOneRoundCard(playerCard);//重新設定玩家卡片
					if (p.getTotalValue() > 21)
						hit = false;//爆了的話直接跳到下面
				} else {
					System.out.println("Pass hit!");
				}
			} while (hit);
			System.out.println(p.getName() + "'s hit is over!");
		}
	}
	//詢問是否要牌
	private void ask_dealer_about_hits() {
		boolean hit = false;
		ArrayList<Card> dealerCard = new ArrayList<Card>();
		do {
			hit = dr.hit_me(this);//Method 裡面的 hit
			if (hit) {
				dealerCard = dr.getOneRoundCard();
				dealerCard.add(dd.getOneCard(true));//新增打開的牌
				dr.setOneRoundCard(dealerCard);
				if (dr.getTotalValue() > 21)
					hit = false;
			}
		} while (hit);//判斷是否正確
		System.out.println("Dealer's hit is over!");
	}
	private void calculate_chips() {
		int pe, de = dr.getTotalValue();
		char result;
		System.out.print("Dealer's card value is " + de + ", Cards:");//印出莊家的點數和牌
		dr.printAllCard();
		for (int n = 0; n < pr.length; n++) {
			Player p = pr[n];
			pe = p.getTotalValue();
			System.out.print(p.getName() + "'s Cards:");
			p.printAllCard();
			System.out.print(p.getName() + "'s card value is " + pe);
			//莊家跟每一個玩家的牌比
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
			//判斷是誰獲勝
			//莊家贏
			case 'u':
				p.increase_chips(-pos_betArray[n]);
				System.out.print(
						", Loss " + pos_betArray[n] + " Chips, the Chips now is:" + p.get_current_chips() + "\n");
				break;
				//莊家輸
			case 'x':
				p.increase_chips(pos_betArray[n]);
				System.out
						.print(", Get " + pos_betArray[n] + " Chips, the Chips now is:" + p.get_current_chips() + "\n");
				break;
				//和局
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
