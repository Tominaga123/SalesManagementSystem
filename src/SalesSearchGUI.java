import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SalesSearchGUI extends JFrame implements ActionListener{
	
	//絞り込み条件の入力欄
	
	JButton searchButton = new JButton("検索"); //検索ボタン
	
	JLabel numberLabel1 = new JLabel(); //伝票番号を表示するラベル
	JLabel numberLabel2 = new JLabel();
	JLabel numberLabel3 = new JLabel();
	JLabel numberLabel4 = new JLabel();
	JLabel numberLabel5 = new JLabel();
	JLabel numberLabel6 = new JLabel();
	JLabel numberLabel7 = new JLabel();
	JLabel numberLabel8 = new JLabel();
	JLabel numberLabel9 = new JLabel();
	JLabel numberLabel10 = new JLabel();
	
	JLabel dateLabel1 = new JLabel(); //日時を表示するラベル
	JLabel dateLabel2 = new JLabel();
	JLabel dateLabel3 = new JLabel();
	JLabel dateLabel4 = new JLabel();
	JLabel dateLabel5 = new JLabel();
	JLabel dateLabel6 = new JLabel();
	JLabel dateLabel7 = new JLabel();
	JLabel dateLabel8 = new JLabel();
	JLabel dateLabel9 = new JLabel();
	JLabel dateLabel10 = new JLabel();
	
	JLabel clerkCodeLabel1 = new JLabel(); //店員コードを表示するラベル
	JLabel clerkCodeLabel2 = new JLabel();
	JLabel clerkCodeLabel3 = new JLabel();
	JLabel clerkCodeLabel4 = new JLabel();
	JLabel clerkCodeLabel5 = new JLabel();
	JLabel clerkCodeLabel6 = new JLabel();
	JLabel clerkCodeLabel7 = new JLabel();
	JLabel clerkCodeLabel8 = new JLabel();
	JLabel clerkCodeLabel9 = new JLabel();
	JLabel clerkCodeLabel10 = new JLabel();
	
	JLabel clerkNameLabel1 = new JLabel(); //店員名を表示するラベル
	JLabel clerkNameLabel2 = new JLabel();
	JLabel clerkNameLabel3 = new JLabel();
	JLabel clerkNameLabel4 = new JLabel();
	JLabel clerkNameLabel5 = new JLabel();
	JLabel clerkNameLabel6 = new JLabel();
	JLabel clerkNameLabel7 = new JLabel();
	JLabel clerkNameLabel8 = new JLabel();
	JLabel clerkNameLabel9 = new JLabel();
	JLabel clerkNameLabel10 = new JLabel();
	
	JLabel goodsCodeLabel1 = new JLabel(); //商品コードを表示するラベル
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();
	JPanel panel10 = new JPanel();
	JPanel panel11 = new JPanel();
	
	SalesSearchGUI(){
		setTitle("売上検索システム");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new FlowLayout());
		panel5.setLayout(new FlowLayout());
		panel6.setLayout(new FlowLayout());
		panel7.setLayout(new FlowLayout());
		panel8.setLayout(new FlowLayout());
		panel9.setLayout(new FlowLayout());
		panel10.setLayout(new FlowLayout());
		panel11.setLayout(new FlowLayout());
		
		panel1.add(dateLabel1);
		panel2.add(dateLabel1);
		panel3.add(dateLabel1);
		panel4.add(dateLabel1);
		panel5.add(dateLabel1);
		panel6.add(dateLabel1);
		panel7.add(dateLabel1);
		panel8.add(dateLabel1);
		panel9.add(dateLabel1);
		panel10.add(dateLabel1);

	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	

}
