import java.util.*;
//the time I put on coding  homework4 is less than before , thanks god so much because I am afraid that I can't hang out the homework on time.
public  class Player extends Person{
	private String n;// 名字
	private int c;// total
	private int b;//籌碼
	private ArrayList<Card> oneRoundCard=new ArrayList<Card>();// 此局的卡

	public Player(String name, int chips) {
	   this.n=name;//避免混淆物件
	   this.c=chips;
	}
	//回傳姓名
	public String getName() {
		return n;
	}
	public int makeBet() {//下籌碼
		b=1;
		if(c<=0) {
		   return 0;
		}//檢查籌碼不能為<0
		return b; 
	}
	public void setOneRoundCard(ArrayList<Card> cards) {
		oneRoundCard=cards;
	}//設定玩家牌組
	//是否要牌
	public boolean hitMe(Table table) {
		int a1=0;
		for(int i=0;i<oneRoundCard.size();i++) {
			Card c=new Card(null,0);
			c=oneRoundCard.get(i);
			a1+=c.getRank();//得到新點數
		}
		//點數<16才要牌
		if(a1<=16) {
			return true;
		}else {
			return false;
		}	
	}
	//得到所有值總和
	public int getTotalValue() {
		int a2=0;
		for(int i=0;i<oneRoundCard.size();i++) {
			Card c=new Card(null,0);
			c=oneRoundCard.get(i);
			a2+=c.getRank();
		}
		return a2;
	}
	public  int get_current_chips() {
		    return c;
	}//回傳籌碼*/
	public void increase_chips(int diff) {
		   c=c+diff;
	}//籌碼的改變
	public void sayHello() {
		System.out.println("Hello , I am "+n+" .");
		System.out.println("I have "+c+" chips");
	}//顯示出玩家打招呼
	@Override
	public boolean hit_me(Table table) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
