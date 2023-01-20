import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ManagementGUI extends JFrame implements ActionListener{
	
	JButton salesManagementButton = new JButton("売上管理"); //売上管理画面を開くボタン
	JButton stockManagementButton = new JButton("在庫管理"); //在庫管理画面を開くボタン
	JButton searchButton = new JButton("データ検索"); //データ検索画面を開くボタン
	JButton editButton = new JButton("データベース編集"); //編集画面を開くボタン
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel(); //コンポーネントを置くパネル

	
	ManagementGUI(){
		setTitle("販売管理システム");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		
		panel1.add(salesManagementButton);
		panel1.add(stockManagementButton);
		panel2.add(searchButton);
		panel2.add(editButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		
		salesManagementButton.addActionListener(this);
		stockManagementButton.addActionListener(this);
		searchButton.addActionListener(this);
		editButton.addActionListener(this);
		setSize(350,120);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new ManagementGUI();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == salesManagementButton){
			new salesManagementGUI();
		}else if(e.getSource() == stockManagementButton){
			
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
	JButton salesSearchButton = new JButton("売上検索、集計"); //売上検索画面を開くボタン
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル

	
	salesManagementGUI(){
		setTitle("売上管理");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		
		panel1.add(salesInputButton);
		panel1.add(salesSearchButton);
		
		getContentPane().add(panel1);

		salesInputButton.addActionListener(this);
		salesSearchButton.addActionListener(this);
		setSize(350,100);
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
	
	JButton salesSearchButton = new JButton("売上検索、集計"); //売上検索画面を開くボタン
	JButton clerkSearchButton = new JButton("売上検索、集計"); //店員検索画面を開くボタン
	JButton goodsSearchButton = new JButton("売上検索、集計"); //商品検索画面を開くボタン
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	
	searchGUI(){
		setTitle("データ検索");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		
		panel1.add(salesSearchButton);
		
		getContentPane().add(panel1);

		salesSearchButton.addActionListener(this);
		setSize(350,100);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == salesSearchButton){
			new SalesSearchGUI();
		}
	}
}



//どのデータベースを編集するか選択する画面を生成するクラス
class editGUI extends JFrame implements ActionListener{
	
	JButton clerkEditButton = new JButton("店員マスタ編集"); //店員マスタ編集システムを開くボタン
	JButton goodsEditButton = new JButton("商品マスタ編集"); //商品マスタ編集システムを開くボタン
	JButton stockEditButton = new JButton("商品マスタ編集"); //商品マスタ編集システムを開くボタン
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル

	
	editGUI(){
		setTitle("データベース編集");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		
		panel1.add(clerkEditButton);
		panel1.add(goodsEditButton);
		panel1.add(stockEditButton);
		
		getContentPane().add(panel1);

		clerkEditButton.addActionListener(this);
		goodsEditButton.addActionListener(this);
		stockEditButton.addActionListener(this);
		setSize(350,100);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == clerkEditButton){
			new clerkEditGUI();
		}else if(e.getSource() == goodsEditButton){
			new goodsEditGUI();
		}else if(e.getSource() == stockEditButton){
			
		}
	}
}