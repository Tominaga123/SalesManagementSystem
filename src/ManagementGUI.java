import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ManagementGUI extends JFrame implements ActionListener{
	
	JButton salesManagementButton = new JButton("売上管理"); //売上管理画面を開くボタン
	JButton searchButton = new JButton("データ検索"); //データ検索画面を開くボタン
	JButton editButton = new JButton("データベース編集"); //編集画面を開くボタン
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel3 = new JPanel(); //コンポーネントを置くパネル

	ManagementGUI(){
		setTitle("販売管理システム");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		
		panel1.add(salesManagementButton);
		panel2.add(searchButton);
		panel3.add(editButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);

		salesManagementButton.addActionListener(this);
		searchButton.addActionListener(this);
		editButton.addActionListener(this);
		if(LoginGUI.USER.equals("店員2")) {
			editButton.setEnabled(false);
		}
		setSize(350,150);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == salesManagementButton){
			new salesManagementGUI();
		}else if(e.getSource() == searchButton){
			new searchGUI();
		}else if(e.getSource() == editButton){
			new editGUI();
		}
		
	}
}

//売上管理画面を生成するクラス
class salesManagementGUI extends JFrame implements ActionListener{
	
	JButton salesInputButton = new JButton("売上入力"); //売上入力画面を開くボタン
	JButton salesSearchButton = new JButton("売上検索・集計"); //売上検索・集計画面を開くボタン
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel(); 
	
	salesManagementGUI(){
		setTitle("売上管理");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		
		panel1.add(salesInputButton);
		panel2.add(salesSearchButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);

		salesInputButton.addActionListener(this);
		salesSearchButton.addActionListener(this);
		setSize(350,120);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == salesInputButton){
			new SalesInputGUI();
		}else if(e.getSource() == salesSearchButton){
			new SalesSearchGUI();
		}
	}
}

//データ検索画面を生成するクラス
class searchGUI extends JFrame implements ActionListener{
	
	JButton salesSearchButton = new JButton("売上検索・集計"); //売上検索画面を開くボタン
	JButton clerkSearchButton = new JButton("店員検索"); //店員検索画面を開くボタン
	JButton goodsSearchButton = new JButton("商品検索"); //商品検索画面を開くボタン
	JButton stockSearchButton = new JButton("在庫検索"); //在庫検索画面を開くボタン
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	
	searchGUI(){
		setTitle("データ検索");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new FlowLayout());
		
		panel1.add(salesSearchButton);
		panel2.add(clerkSearchButton);
		panel3.add(goodsSearchButton);
		panel4.add(stockSearchButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		getContentPane().add(panel4);
		
		salesSearchButton.addActionListener(this);
		clerkSearchButton.addActionListener(this);
		goodsSearchButton.addActionListener(this);
		stockSearchButton.addActionListener(this);
		setSize(350,180);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == salesSearchButton){
			new SalesSearchGUI();
		}else if(e.getSource() == clerkSearchButton){
			new ClerkSearchGUI();
		}else if(e.getSource() == goodsSearchButton){
			new GoodsSearchGUI();
		}else if(e.getSource() == stockSearchButton){
			new StockSearchGUI();
		}
	}
}



//どのデータベースを編集するか選択する画面を生成するクラス
class editGUI extends JFrame implements ActionListener{
	
	JButton clerkEditButton = new JButton("店員マスタ編集"); //店員マスタ編集システムを開くボタン
	JButton goodsEditButton = new JButton("商品マスタ編集"); //商品マスタ編集システムを開くボタン
	JButton stockEditButton = new JButton("在庫マスタ編集"); //在庫マスタ編集システムを開くボタン
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	
	editGUI(){
		setTitle("データベース編集");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		
		panel1.add(clerkEditButton);
		panel2.add(goodsEditButton);
		panel3.add(stockEditButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		
		clerkEditButton.addActionListener(this);
		goodsEditButton.addActionListener(this);
		stockEditButton.addActionListener(this);
		setSize(350,150);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == clerkEditButton){
			new ClerkEditGUI();
		}else if(e.getSource() == goodsEditButton){
			new GoodsEditGUI();
		}else if(e.getSource() == stockEditButton){
			new StockEditGUI();
		}
	}
}