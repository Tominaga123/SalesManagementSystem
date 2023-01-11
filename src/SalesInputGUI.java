import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class SalesInputGUI extends JFrame implements ActionListener{
	
	JLabel timeLabel = new JLabel("販売日時"); //販売日時の入力欄であることを示すラベル
	JComboBox yearComboBox = new JComboBox(); //販売日時の入力欄 
	JLabel yearLabel = new JLabel("年"); //単位「年」を表示するラベル
	JComboBox monthComboBox = new JComboBox();
	JLabel monthLabel = new JLabel("月"); //単位「月」を表示するラベル
	JComboBox dateComboBox = new JComboBox();
	JLabel dateLabel = new JLabel("日"); //単位「日」を表示するラベル
	JComboBox hourComboBox = new JComboBox();
	JLabel hourLabel = new JLabel("時"); //単位「時」を表示するラベル
	JComboBox minuteComboBox = new JComboBox();
	JLabel minuteLabel = new JLabel("分"); //単位「分」を表示するラベル
	
	JLabel clerkCodeLabel = new JLabel("店員コード"); //店員コードの選択欄であることを示すラベル
	JComboBox clerkCodeComboBox = new JComboBox(); //店員コードの選択欄 
	JLabel clerkNameLabel = new JLabel("店員名"); //店員名の選択欄であることを示すラベル
	JComboBox clerkNameComboBox = new JComboBox(); //店員名の選択欄 
	
	JLabel goodsCode1Label = new JLabel("商品コード"); //商品コードの選択欄であることを示すラベル
	JComboBox goodsCode1ComboBox = new JComboBox(); //商品コードの選択欄 
	JLabel goodsName1Label = new JLabel("  商品名"); //商品名の選択欄であることを示すラベル
	JComboBox goodsName1ComboBox = new JComboBox(); //商品名の選択欄
	JLabel p1Label = new JLabel("単価 ￥"); //単価であることを示すラベル
	JLabel price1Label = new JLabel("0"); //単価を表すラベル
	JLabel count1Label = new JLabel("個数"); //商品の個数の入力欄であることを示すラベル
	JTextField count1TextField = new JTextField("0", 3); //個数の入力欄
	JLabel st1Label = new JLabel("小計 ￥"); //小計であることを示すラベル
	JLabel subTotal1Label = new JLabel("0"); //小計を表示するラベル
	
	JLabel goodsCode2Label = new JLabel("商品コード");
	JComboBox goodsCode2ComboBox = new JComboBox();
	JLabel goodsName2Label = new JLabel("  商品名");
	JComboBox goodsName2ComboBox = new JComboBox();
	JLabel p2Label = new JLabel("単価 ￥");
	JLabel price2Label = new JLabel("0");
	JLabel count2Label = new JLabel("個数"); 
	JTextField count2TextField = new JTextField("0", 3);
	JLabel st2Label = new JLabel("小計 ￥");
	JLabel subTotal2Label = new JLabel("0"); 
	
	JLabel goodsCode3Label = new JLabel("商品コード");
	JComboBox goodsCode3ComboBox = new JComboBox();
	JLabel goodsName3Label = new JLabel("  商品名");
	JComboBox goodsName3ComboBox = new JComboBox();
	JLabel p3Label = new JLabel("単価 ￥");
	JLabel price3Label = new JLabel("0");
	JLabel count3Label = new JLabel("個数"); 
	JTextField count3TextField = new JTextField("0", 3);
	JLabel st3Label = new JLabel("小計 ￥");
	JLabel subTotal3Label = new JLabel("0"); 
	
	JLabel tLabel = new JLabel("合計 ￥"); //合計金額であることを示すラベル
	JLabel totalLabel = new JLabel("0"); //合計金額を表示するラベル
	
	JLabel taLabel = new JLabel("(内消費税等 ￥"); //税額であることを示すラベル
	JLabel taxLabel = new JLabel("0"); //消費税額を表示するラベル
	JLabel xLabel = new JLabel(")");  //括弧閉じラベル
	
	JLabel numLabel = new JLabel("伝票番号"); //伝票番号であることを示すラベル
	JLabel numberLabel = new JLabel("0"); //伝票番号の通番を表示するラベル（フレーム生成時にデータベースから取得）
	
	JButton insertButton = new JButton("追加"); //データベースに追加するボタン
	JButton resetButton = new JButton("リセット"); //白紙にするボタン
	
	String URL = "jdbc:mysql://127.0.0.1:3306/販売管理"; //SQLで使用
	String USER = "店員1";
	String PASS = "password";
	String SQL;
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();
	
	SalesInputGUI(){
		setTitle("売上入力");
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
		
		//各コンボボックスに、データベースから情報を取得して項目を追加
		//日時を選択するコンボボックスに項目を追加
		//現在の日時で初期化
		yearComboBox.addItem(2023);
		for(int i = 1; i <= 12; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			monthComboBox.addItem(s);
			if(s.equals(getMonth())){
				monthComboBox.setSelectedIndex(i-1);
			}
		}
		for(int i = 1; i <= 31; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			dateComboBox.addItem(s);
			if(s.equals(getDate())){
				dateComboBox.setSelectedIndex(i-1);
			}
		}
		for(int i = 0; i <= 23; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			hourComboBox.addItem(s);
			if(s.equals(getHour())){
				hourComboBox.setSelectedIndex(i);
			}
		}
		for(int i = 0; i <= 59; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			minuteComboBox.addItem(s);
			if(s.equals(getMinute())){
				minuteComboBox.setSelectedIndex(i);
			}
		}
		
		//店員コードを選択するコンボボックスに項目を追加
		try {
			SQL = "SELECT 店員コード FROM 店員マスタ;";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				clerkCodeComboBox.addItem(rs.getString("店員コード"));
			}
			clerkCodeComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		//店員名を選択するコンボボックスに項目を追加
		try {
			SQL = "SELECT 氏名 FROM 店員マスタ;";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				clerkNameComboBox.addItem(rs.getString("氏名"));
			}
			clerkNameComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		//商品コードを選択するコンボボックスに項目を追加
		try {
			SQL = "SELECT 商品コード FROM 商品マスタ;";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				goodsCode1ComboBox.addItem(rs.getString("商品コード"));
				goodsCode2ComboBox.addItem(rs.getString("商品コード"));
				goodsCode3ComboBox.addItem(rs.getString("商品コード"));
			}
			goodsCode1ComboBox.setSelectedItem(null);
			goodsCode2ComboBox.setSelectedItem(null);
			goodsCode3ComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		//商品名を選択するコンボボックスに項目を追加
		try {
			SQL = "SELECT 商品名 FROM 商品マスタ;";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				goodsName1ComboBox.addItem(rs.getString("商品名"));
				goodsName2ComboBox.addItem(rs.getString("商品名"));
				goodsName3ComboBox.addItem(rs.getString("商品名"));
			}
			goodsName1ComboBox.setSelectedItem(null);
			goodsName2ComboBox.setSelectedItem(null);
			goodsName3ComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		//伝票番号を取得
		numberSQL();
		
		panel1.add(timeLabel);
		panel1.add(yearComboBox);
		panel1.add(yearLabel);
		panel1.add(monthComboBox);
		panel1.add(monthLabel);
		panel1.add(dateComboBox);
		panel1.add(dateLabel);
		panel1.add(hourComboBox);
		panel1.add(hourLabel);
		panel1.add(minuteComboBox);
		panel1.add(minuteLabel);
		
		panel2.add(clerkCodeLabel);
		panel2.add(clerkCodeComboBox);
		panel2.add(clerkNameLabel);
		panel2.add(clerkNameComboBox);
		
		panel3.add(goodsCode1Label);
		panel3.add(goodsCode1ComboBox);
		panel3.add(goodsName1Label);
		panel3.add(goodsName1ComboBox);
		panel3.add(p1Label);
		panel3.add(price1Label);
		panel3.add(count1Label);
		panel3.add(count1TextField);
		panel3.add(st1Label);
		panel3.add(subTotal1Label);
		
		panel4.add(goodsCode2Label);
		panel4.add(goodsCode2ComboBox);
		panel4.add(goodsName2Label);
		panel4.add(goodsName2ComboBox);
		panel4.add(p2Label);
		panel4.add(price2Label);
		panel4.add(count2Label);
		panel4.add(count2TextField);
		panel4.add(st2Label);
		panel4.add(subTotal2Label);
		
		panel5.add(goodsCode3Label);
		panel5.add(goodsCode3ComboBox);
		panel5.add(goodsName3Label);
		panel5.add(goodsName3ComboBox);
		panel5.add(p3Label);
		panel5.add(price3Label);
		panel5.add(count3Label);
		panel5.add(count3TextField);
		panel5.add(st3Label);
		panel5.add(subTotal3Label);
		
		panel6.add(tLabel);
		panel6.add(totalLabel);
		
		panel7.add(taLabel);
		panel7.add(taxLabel);
		panel7.add(xLabel);
		
		panel8.add(numLabel);
		panel8.add(numberLabel);
		
		panel9.add(insertButton);
		panel9.add(resetButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		getContentPane().add(panel4);
		getContentPane().add(panel5);
		getContentPane().add(panel6);
		getContentPane().add(panel7);
		getContentPane().add(panel8);
		getContentPane().add(panel9);
		
		clerkCodeComboBox.addActionListener(this);
		clerkNameComboBox.addActionListener(this);
		goodsCode1ComboBox.addActionListener(this);
		goodsCode2ComboBox.addActionListener(this);
		goodsCode3ComboBox.addActionListener(this);
		goodsName1ComboBox.addActionListener(this);
		goodsName2ComboBox.addActionListener(this);
		goodsName3ComboBox.addActionListener(this);
		count1TextField.addActionListener(this);
		count2TextField.addActionListener(this);
		count3TextField.addActionListener(this);
		insertButton.addActionListener(this);
		resetButton.addActionListener(this);
		this.pack();
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == clerkCodeComboBox){ //店員コードを使用して店員マスタから店員名を取得する
			try {
				SQL = "SELECT * FROM 店員マスタ WHERE 店員コード = '" + clerkCodeComboBox.getSelectedItem() + "';";
				Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				while(rs.next()){
					clerkNameComboBox.setSelectedItem(rs.getString("氏名"));
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}else if(e.getSource() == clerkNameComboBox){ //店員名を使用して店員コードを取得
			try {
				SQL = "SELECT * FROM 店員マスタ WHERE 氏名 = '" + clerkNameComboBox.getSelectedItem() + "';";
				Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				while(rs.next()){
					clerkCodeComboBox.setSelectedItem(rs.getString("店員コード"));
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}else if(e.getSource() == goodsCode1ComboBox){ //商品コードを使用して商品名、単価を取得し、小計、合計を更新
			selectGoodsCodeSQL(goodsCode1ComboBox, goodsName1ComboBox, price1Label, count1TextField, subTotal1Label);
			
		}else if(e.getSource() == goodsCode2ComboBox){ //商品コードを使用して商品名、単価を取得し、小計、合計を更新
			selectGoodsCodeSQL(goodsCode2ComboBox, goodsName2ComboBox, price2Label, count2TextField, subTotal2Label);
			
		}else if(e.getSource() == goodsCode3ComboBox){ //商品コードを使用して商品名、単価を取得し、小計、合計を更新
			selectGoodsCodeSQL(goodsCode3ComboBox, goodsName3ComboBox, price3Label, count3TextField, subTotal3Label);
			
		}else if(e.getSource() == goodsName1ComboBox){ //商品名を使用して商品コード、単価を取得し、小計、合計を更新
			selectGoodsNameSQL(goodsCode1ComboBox, goodsName1ComboBox, price1Label, count1TextField, subTotal1Label);
			
		}else if(e.getSource() == goodsName2ComboBox){ //商品名を使用して商品コード、単価を取得し、小計、合計を更新
			selectGoodsNameSQL(goodsCode2ComboBox, goodsName2ComboBox, price2Label, count2TextField, subTotal2Label);
			
		}else if(e.getSource() == goodsName3ComboBox){ //商品名を使用して商品コード、単価を取得し、小計、合計を更新
			selectGoodsNameSQL(goodsCode3ComboBox, goodsName3ComboBox, price3Label, count3TextField, subTotal3Label);
			
		}else if(e.getSource() == count1TextField){ //入力された個数から小計を計算
			calcSubTotal(price1Label, count1TextField, subTotal1Label);
			calcTotal();
			
		}else if(e.getSource() == count2TextField){ //入力された個数から小計を計算
			calcSubTotal(price2Label, count2TextField, subTotal2Label);
			calcTotal();
			
		}else if(e.getSource() == count3TextField){ //入力された個数から小計を計算
			calcSubTotal(price3Label, count3TextField, subTotal3Label);
			calcTotal();
			
		}else if(e.getSource() == insertButton) { //入力された内容をデータベースに追加
//			if(clerkCodeComboBox.getSelectedItem().equals(null)) {
//				
//			}
			if(Integer.parseInt(subTotal1Label.getText()) > 0) {
				insertSQL(goodsCode1ComboBox, count1TextField, subTotal1Label);
			}
			if(Integer.parseInt(subTotal2Label.getText()) > 0) {
				insertSQL(goodsCode2ComboBox, count2TextField, subTotal2Label);
			}
			if(Integer.parseInt(subTotal3Label.getText()) > 0) {
				insertSQL(goodsCode3ComboBox, count3TextField, subTotal3Label);
			}
			reset();
		}else if(e.getSource() == resetButton) { //入力内容をリセット
			reset();
		}
		this.pack();
	}

	//伝票番号を取得するメソッド
	public void numberSQL() {
		try {
			SQL = "SELECT MAX(伝票番号) FROM 売上マスタ;";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				if(rs.getString("MAX(伝票番号)") == null) {
					numberLabel.setText("1");
				}else {
					int i = Integer.parseInt(rs.getString("MAX(伝票番号)"));
					numberLabel.setText(Integer.toString(i + 1));
				}
			} 
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//商品コードを使用して、商品マスタから商品名、単価を取得し、小計、合計を更新するメソッド
	public void selectGoodsCodeSQL(JComboBox codeBox, JComboBox nameBox, JLabel priceLabel, JTextField textField, JLabel subTotalLabel) {
		try {
			SQL = "SELECT * FROM 商品マスタ WHERE 商品コード = '" + codeBox.getSelectedItem() + "';";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				nameBox.setSelectedItem(rs.getString("商品名"));
				priceLabel.setText(rs.getString("単価"));
			}
			calcSubTotal(priceLabel, textField, subTotalLabel);
			calcTotal();
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
	}
	
	//商品名を使用して、商品マスタから商品コード、単価を取得し、小計、合計を更新するメソッド
	public void selectGoodsNameSQL(JComboBox codeBox, JComboBox nameBox, JLabel priceLabel, JTextField textField, JLabel subTotalLabel) {
		try {
			SQL = "SELECT * FROM 商品マスタ WHERE 商品名 = '" + nameBox.getSelectedItem() + "';";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				codeBox.setSelectedItem(rs.getString("商品コード"));
				priceLabel.setText(rs.getString("単価"));
			}
			calcSubTotal(priceLabel, textField, subTotalLabel);
			calcTotal();
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
	}
	
	//小計を計算するメソッド
	public void calcSubTotal(JLabel priceLabel, JTextField textField, JLabel subTotalLabel) {
		int subTotal = Integer.parseInt(priceLabel.getText()) * Integer.parseInt(textField.getText());
		subTotalLabel.setText(Integer.toString(subTotal));
		
	}
	
	//合計、税を計算するメソッド
	public void calcTotal() {
		int total = Integer.parseInt(subTotal1Label.getText()) + 
				Integer.parseInt(subTotal2Label.getText()) + 
				Integer.parseInt(subTotal3Label.getText());
		totalLabel.setText(Integer.toString(total));
		int tax = (int)Math.floor(total / 11);
		taxLabel.setText(Integer.toString(tax));
	}

	//売上マスタに行を追加するメソッド
	public void insertSQL(JComboBox box, JTextField textField, JLabel subTotalLabel) {
		String SQL = "INSERT INTO 売上マスタ (伝票番号, 販売日時, 店員コード, 商品コード, 個数, 小計) values (" + 
				Integer.parseInt(numberLabel.getText()) + ",cast('" + yearComboBox.getSelectedItem() + "-" + 
				monthComboBox.getSelectedItem() + "-" + dateComboBox.getSelectedItem() + " " + 
				hourComboBox.getSelectedItem() + ":" + minuteComboBox.getSelectedItem() + "' as datetime)," + "'" + 
				clerkCodeComboBox.getSelectedItem() + "'" + "," + "'" + box.getSelectedItem() + "'" + 
				"," + textField.getText() + "," + subTotalLabel.getText() + ")";
		System.out.println(SQL + "を追加しました");
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			stmt.execute(SQL);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//入力欄をリセットするメソッド
	public void reset() {
		clerkCodeComboBox.setSelectedItem(null);
		clerkNameComboBox.setSelectedItem(null);
		goodsCode1ComboBox.setSelectedItem(null);
		goodsName1ComboBox.setSelectedItem(null);
		price1Label.setText("0");
		count1TextField.setText("0");
		subTotal1Label.setText("0");
		goodsCode2ComboBox.setSelectedItem(null);
		goodsName1ComboBox.setSelectedItem(null);
		price2Label.setText("0");
		count2TextField.setText("0");
		subTotal2Label.setText("0");
		goodsCode3ComboBox.setSelectedItem(null);
		goodsName1ComboBox.setSelectedItem(null);
		price3Label.setText("0");
		count3TextField.setText("0");
		subTotal3Label.setText("0");
		totalLabel.setText("0");
		taxLabel.setText("0"); 
		numberSQL();
	}
	
	//コンボボックスを現在の日時で初期化するためのメソッド
	public String getYear() {
		Date date = new Date();
		String str = new SimpleDateFormat("yyyy").format(date);
		return str;
	}
	public String getMonth() {
		Date date = new Date();
		String str = new SimpleDateFormat("MM").format(date);
		return str;
	}
	public String getDate() {
		Date date = new Date();
		String str = new SimpleDateFormat("dd").format(date);
		return str;
	}
	public String getHour() {
		Date date = new Date();
		String str = new SimpleDateFormat("HH").format(date);
		return str;
	}
	public String getMinute() {
		Date date = new Date();
		String str = new SimpleDateFormat("mm").format(date);
		return str;
	}
}
