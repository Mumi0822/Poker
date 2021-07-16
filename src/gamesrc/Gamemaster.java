package gamesrc;

import java.util.Random;

import javax.swing.JOptionPane;

public class Gamemaster {
	Random rnd = new Random();
	public boolean flag1 = false;//
	public boolean flag2 = false;//両方trueで約判定開始
	public int[] deck = new int[54];
	public boolean[] changelist = new boolean[5];

	private int dn =0 ;
	public int [][] hands1 = new int[5][3];//
	public int [][]hands2 = new int[5][3];//int[][スート,ナンバー,id]（Jは[4,14,53or54]）
	private int score1;
	private int score2;
	public String hand1;
	public String hand2;
	public String result;
	private int judge;
	boolean[] change = {true,true,true,true,true};


	public void make_deck(){
		int n1;
		int n2;
		int p;
		for(int t=1;t<55;t++){
			deck[t-1]=t;
		}
		for(int t=1;t<50;t++){
			n1 = rnd.nextInt(25);
			n2 = 26+rnd.nextInt(27);
			p = deck[n1];
			deck[n1]=deck[n2];
			deck[n2]=p;
		}
		for(int i=0;i<deck.length;i++){
			System.out.println(deck[i]+"");
		}
	}
	public int handout(){//手札を引く（一枚）
		int id = deck[dn];
		System.out.println(dn+"");//13で割った商がスート（0:ハ,1:ダ,2:ク,3:ス　但し13は-1する）余が番号（13は0）J:53，54
		dn++;
		System.out.println("="+id);
		return id;
	}
	public void hang(boolean i/*true:hands1,false:hands2*/){//handout()で引いた手札をhands[][]に格納
		int hand[] = new int[3];//手札を配列化
		for(int t=0; t<5; t++){
			hand[2]=handout();//nggudge()用
			if(hand[2]%13==0){//各スートの13を識別（）
				hand[0]=hand[2]/13-1;
				hand[1]=13;
			}else if(hand[2]/13==4){//Jの識別
				hand[0]=4;
				hand[1]=14;
			}else{//その他
				hand[0]=hand[2]/13;
				hand[1]=hand[2]%13;
			}
			if(i==true){//hands1[][]に格納
				hands1[t][0]= hand[0];
				hands1[t][1]= hand[1];
				hands1[t][2]= hand[2];
			}else{//hands2に格納
				hands2[t][0]= hand[0];
				hands2[t][1]= hand[1];
				hands2[t][2]= hand[2];
			}
		}
	}
	public int[][] sort(int [][]hands){
		for(int t=0;t<hands.length-1;t++){
		      // 下から上に順番に比較します
			  for(int j=hands.length-1;j>t;j--){
			// 上の方が大きいときは互いに入れ替えます
			if(hands[j][1]<hands[j-1][1]){
			  int[] i=hands[j];
			  hands[j]=hands[j-1];
			  hands[j-1]=i;
			}
		      }
		    }
		return hands;
	}
	public void change(boolean h,int i){
		int[]hand= new int[3];
		hand[2]=handout();
		if(hand[2]%13==0){//各スートの13を識別（）
			hand[0]=hand[2]/13-1;
			hand[1]=13;
		}else if(hand[2]/13==4){//Jの識別
			hand[0]=4;
			hand[1]=14;
		}else{//その他
			hand[0]=hand[2]/13;
			hand[1]=hand[2]%13;
		}

		if(h==true/*true:hands1,false:hands2*/){
			hands1[i][0]= hand[0];
			hands1[i][1]= hand[1];
			hands1[i][2]= hand[2];
		}else{
			hands2[i][0]= hand[0];
			hands2[i][1]= hand[1];
			hands2[i][2]= hand[2];
		}
	}
	public int autochange(){
		int score;
		int cn = 0;
		score = count(hands2);
		if(score>9){
			return cn;
		}else{

			for(int i=0;i<hands2.length;i++){
				if(change[i]==true){
					cn++;
					change(false,i);
				}

			}
			return cn;
		}
	}
	public int count(int[][] hand){
		int point = 0;
		int odds = 0;

		if(hand[0][0]==hand[1][0]&&hand[1][0]==hand[2][0]&&hand[2][0]==hand[3][0]&&hand[3][0]==hand[4][0]){
			point = point + 11;
			if(hand[0][1]+1==hand[1][1]&&hand[1][1]+1==hand[2][1]&&hand[2][1]+1==hand[3][1]&&hand[3][1]+1==hand[4][1]){
				point = point + 10;
				if(hand[0][1]==9&&hand[1][1]==10&&hand[2][1]==11&&hand[3][1]==12&&hand[4][1]==13)
					point = point + 4;
			}
			return point;
		}else{
			if(hand[0][1]+1==hand[1][1]&&hand[1][1]+1==hand[2][1]&&hand[2][1]+1==hand[3][1]&&hand[3][1]+1==hand[4][1]){
				point = point + 10;
				return point;
			}
		}
		for(int i=0;i<4;i++){
			if(hand[i][1]==hand[i+1][1]||hand[i+1][1]==14){
				change[i]=false;
				change[i+1]=false;
				odds++;
				point = point + odds;
			}else{
				odds = 0;
			}
		}
		point = point*3;
		return point;
	}
	public String decision(int point){
		String hand;
		switch(point){
		case 3:
			hand = "One Pair";
			break;
		case 6:
			hand = "Two Pair";
			break;
		case 9:
			hand = "Three of a Kind";
			break;
		case 10:
			hand = "Straight";
			break;
		case 11:
			hand = "Flush";
			break;
		case 12:
			hand = "Full House";
			break;
		case 18:
			hand = "Four of a Kind";
			break;
		case 21:
			hand = "Straight Flush";
			break;
		case 25:
			hand = "Royal Straight Flush";
			break;
		case 30:
			hand = "Five of a Kind";
			break;
		default:
			hand = "No Pair";
			break;
		}
		return hand;
	}
	public void judge(){
		score1 = count(hands1);
		score2 = count(hands2);
		hand1 = decision(score1);
		hand2 = decision(score2);		 
		if(score1>score2){
			result = "You win!!";
		}else if(score1<score2){
			result = "You lose...";
		}else{
			result = "Even";
		}
		String msg = /*score1+*/hand1+"vs"+/*score2+*/hand2+"\n"+result;
		JOptionPane.showMessageDialog(null, msg);

	}

}
