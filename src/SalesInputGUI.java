import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



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
	JComboBox count1ComboBox = new JComboBox(); //個数の入力欄
	JLabel st1Label = new JLabel("小計 ￥"); //小計であることを示すラベル
	JLabel subTotal1Label = new JLabel("0"); //小計を表示するラベル
	
	JLabel goodsCode2Label = new JLabel("商品コード");
	JComboBox goodsCode2ComboBox = new JComboBox();
	JLabel goodsName2Label = new JLabel("  商品名");
	JComboBox goodsName2ComboBox = new JComboBox();
	JLabel p2Label = new JLabel("単価 ￥");
	JLabel price2Label = new JLabel("0");
	JLabel count2Label = new JLabel("個数"); 
	JComboBox count2ComboBox = new JComboBox();
	JLabel st2Label = new JLabel("小計 ￥");
	JLabel subTotal2Label = new JLabel("0"); 
	
	JLabel goodsCode3Label = new JLabel("商品コード");
	JComboBox goodsCode3ComboBox = new JComboBox();
	JLabel goodsName3Label = new JLabel("  商品名");
	JComboBox goodsName3ComboBox = new JComboBox();
	JLabel p3Label = new JLabel("単価 ￥");
	JLabel price3Label = new JLabel("0");
	JLabel count3Label = new JLabel("個数"); 
	JComboBox count3ComboBox = new JComboBox();
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
		yearComboBox.addItem(getYear());
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
			SQL = "SELECT 店員コード FROM 店員マスタ WHERE 削除フラグ = 0;";
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
			SQL = "SELECT 氏名 FROM 店員マスタ WHERE 削除フラグ = 0;";
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
		
		//商品コードと商品名を選択するコンボボックスに項目を追加
		//ただし、在庫残高があるもののみ
		addGoodsItem();
		
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
		panel3.add(count1ComboBox);
		panel3.add(st1Label);
		panel3.add(subTotal1Label);
		
		panel4.add(goodsCode2Label);
		panel4.add(goodsCode2ComboBox);
		panel4.add(goodsName2Label);
		panel4.add(goodsName2ComboBox);
		panel4.add(p2Label);
		panel4.add(price2Label);
		panel4.add(count2Label);
		panel4.add(count2ComboBox);
		panel4.add(st2Label);
		panel4.add(subTotal2Label);
		
		panel5.add(goodsCode3Label);
		panel5.add(goodsCode3ComboBox);
		panel5.add(goodsName3Label);
		panel5.add(goodsName3ComboBox);
		panel5.add(p3Label);
		panel5.add(price3Label);
		panel5.add(count3Label);
		panel5.add(count3ComboBox);
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
		count1ComboBox.addActionListener(this);
		count2ComboBox.addActionListener(this);
		count3ComboBox.addActionListener(this);
		insertButton.addActionListener(this);
		resetButton.addActionListener(this);
		goodsCode1ComboBox.setEnabled(false);
		goodsName1ComboBox.setEnabled(false);
		goodsCode2ComboBox.setEnabled(false);
		goodsName2ComboBox.setEnabled(false);
		goodsCode3ComboBox.setEnabled(false);
		goodsName3ComboBox.setEnabled(false);
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
			goodsCode1ComboBox.setEnabled(true);
			goodsName1ComboBox.setEnabled(true);
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
			goodsCode1ComboBox.setEnabled(true);
			goodsName1ComboBox.setEnabled(true);
		}else if(e.getSource() == goodsCode1ComboBox){ //商品コードを使用して商品名、単価を取得し、小計、合計を更新
			selectGoodsCodeSQL(goodsCode1ComboBox, goodsName1ComboBox, price1Label, count1ComboBox, subTotal1Label, goodsCode2ComboBox, goodsName2ComboBox);
			
		}else if(e.getSource() == goodsCode2ComboBox){ //商品コードを使用して商品名、単価を取得し、小計、合計を更新
			selectGoodsCodeSQL(goodsCode2ComboBox, goodsName2ComboBox, price2Label, count2ComboBox, subTotal2Label, goodsCode3ComboBox, goodsName3ComboBox);
			
		}else if(e.getSource() == goodsCode3ComboBox){ //商品コードを使用して商品名、単価を取得し、小計、合計を更新
			selectGoodsCodeSQL(goodsCode3ComboBox, goodsName3ComboBox, price3Label, count3ComboBox, subTotal3Label, goodsCode3ComboBox, goodsName3ComboBox);
			
		}else if(e.getSource() == goodsName1ComboBox){ //商品名を使用して商品コード、単価を取得し、小計、合計を更新
			selectGoodsNameSQL(goodsCode1ComboBox, goodsName1ComboBox, price1Label, count1ComboBox, subTotal1Label, goodsCode2ComboBox, goodsName2ComboBox);
			
		}else if(e.getSource() == goodsName2ComboBox){ //商品名を使用して商品コード、単価を取得し、小計、合計を更新
			selectGoodsNameSQL(goodsCode2ComboBox, goodsName2ComboBox, price2Label, count2ComboBox, subTotal2Label, goodsCode3ComboBox, goodsName3ComboBox);
			
		}else if(e.getSource() == goodsName3ComboBox){ //商品名を使用して商品コード、単価を取得し、小計、合計を更新
			selectGoodsNameSQL(goodsCode3ComboBox, goodsName3ComboBox, price3Label, count3ComboBox, subTotal3Label, goodsCode3ComboBox, goodsName3ComboBox);
			
		}else if(e.getSource() == count1ComboBox){ //入力された個数から小計を計算
			calcSubTotal(price1Label, count1ComboBox, subTotal1Label, goodsCode2ComboBox, goodsName2ComboBox);
			calcTotal();
			
		}else if(e.getSource() == count2ComboBox){ //入力された個数から小計を計算
			calcSubTotal(price2Label, count2ComboBox, subTotal2Label, goodsCode3ComboBox, goodsName3ComboBox);
			calcTotal();
			
		}else if(e.getSource() == count3ComboBox){ //入力された個数から小計を計算
			calcSubTotal(price3Label, count3ComboBox, subTotal3Label, goodsCode3ComboBox, goodsName3ComboBox);
			calcTotal();
			
		}else if(e.getSource() == insertButton) { //入力された内容をデータベースに追加
			String alart = ""; //空欄がある場合のメッセージ
			if(!(clerkCodeComboBox.getSelectedItem() == null)) {
				if(Integer.parseInt(subTotal1Label.getText()) > 0) {
					insertSQL(goodsCode1ComboBox, count1ComboBox, subTotal1Label);
					if(Integer.parseInt(subTotal2Label.getText()) > 0) {
						insertSQL(goodsCode2ComboBox, count1ComboBox, subTotal2Label);
						if(Integer.parseInt(subTotal3Label.getText()) > 0) {
							insertSQL(goodsCode3ComboBox, count1ComboBox, subTotal3Label);
						}
					}
				reset();
				}else {
					alart = "商品情報が正しく入力されていません"; 
				}
			}else {
				alart = "店員情報が空欄です";
			}
			if(!alart.equals("")) {
				System.out.println(alart);
			}
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
	public void selectGoodsCodeSQL(JComboBox codeBox, JComboBox nameBox, JLabel priceLabel, JComboBox countBox, 
			JLabel subTotalLabel, JComboBox nextCodeBox, JComboBox nextNameBox) {
		
		System.out.println("商品コードから商品名表示メソッド");
		
		//商品を選択することによって起動するアクションリスナーをオフにする
		offActionListener();
		System.out.println("オフにしました");
		
		try {
			SQL = "SELECT * FROM 商品マスタ WHERE 商品コード = '" + codeBox.getSelectedItem() + "';";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				nameBox.setSelectedItem(rs.getString("商品名"));
				priceLabel.setText(rs.getString("単価"));
			}
			getCount(codeBox, countBox); //商品の個数を選択するコンボボックスに項目を追加
			calcSubTotal(priceLabel, countBox, subTotalLabel, nextCodeBox, nextNameBox);
			calcTotal();
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		//アクションリスナーをオンにする
		onActionListener();
		System.out.println("オンにしました");
	}
	
	//商品名を使用して、商品マスタから商品コード、単価を取得し、小計、合計を更新するメソッド
	public void selectGoodsNameSQL(JComboBox codeBox, JComboBox nameBox, JLabel priceLabel, JComboBox countBox, 
			JLabel subTotalLabel, JComboBox nextCodeBox, JComboBox nextNameBox) {
		
		System.out.println("商品名から商品コード表示メソッド");
		
		//商品を選択することによって起動するアクションリスナーをオフにする
		offActionListener();
		System.out.println("オフにしました");
		
		try {
			SQL = "SELECT * FROM 商品マスタ WHERE 商品名 = '" + nameBox.getSelectedItem() + "';";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				codeBox.setSelectedItem(rs.getString("商品コード"));
				priceLabel.setText(rs.getString("単価"));
			}
			getCount(codeBox, countBox); //商品の個数を選択するコンボボックスに項目を追加
			calcSubTotal(priceLabel, countBox, subTotalLabel, nextCodeBox, nextNameBox);
			calcTotal();
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}	
		
		//アクションリスナーをオンにする
		onActionListener();
		System.out.println("オンにしました");
	}
	
	//商品の個数を選ぶコンボボックスを更新するメソッド
	public void getCount(JComboBox codeBox, JComboBox countBox){
		System.out.println("残高数量取得メソッド");
		countBox.removeAllItems();
		try {
		SQL = "SELECT SUM(仕入数) AS 受入数量 FROM 在庫マスタ "
			+ "WHERE 商品コード = '" + codeBox.getSelectedItem() + "' GROUP BY 商品コード;";
		Connection conn = DriverManager.getConnection(URL, USER, PASS);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL);
		int inSum = 0;
		if(rs.next()) {
			inSum = rs.getInt("受入数量");
		}
		
		SQL = "SELECT SUM(個数) AS 払出数量 FROM 売上マスタ "
			+ "WHERE 商品コード = '" + codeBox.getSelectedItem() + "' GROUP BY 商品コード;";
		rs = stmt.executeQuery(SQL);
		int outSum = 0;
		if(rs.next()) {
			outSum = rs.getInt("払出数量");
		}
		
		if(inSum - outSum > 0) {
			for(int i = 1; i <= (inSum - outSum); i++) {
				countBox.addItem(i);
				if(i >= 10) { //在庫残高が10個以上あるものは最大でも10個
					break;
				}
			}
		}
			countBox.setSelectedItem(0);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//小計を計算するメソッド
	public void calcSubTotal(JLabel priceLabel, JComboBox countBox, JLabel subTotalLabel, 
			JComboBox nextCodeBox, JComboBox nextNameBox) {
		System.out.println("小計メソッド");
		if(countBox.getItemCount() > 0) {
			int i = Integer.parseInt(countBox.getSelectedItem().toString());
			int subTotal = Integer.parseInt(priceLabel.getText()) * i;
			if(subTotal > 0) {
				nextCodeBox.setEnabled(true);
				nextNameBox.setEnabled(true);
			}
			subTotalLabel.setText(Integer.toString(subTotal));
		}
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
	public void insertSQL(JComboBox box, JComboBox countBox, JLabel subTotalLabel) {
		String SQL = "INSERT INTO 売上マスタ (伝票番号, 販売日時, 店員コード, 商品コード, 個数, 小計, 削除フラグ) "
				+ "values (" + Integer.parseInt(numberLabel.getText()) + ",cast('" + yearComboBox.getSelectedItem() + 
				"-" + monthComboBox.getSelectedItem() + "-" + dateComboBox.getSelectedItem() + " " + 
				hourComboBox.getSelectedItem() + ":" + minuteComboBox.getSelectedItem() + "' as datetime)," + "'" + 
				clerkCodeComboBox.getSelectedItem() + "'" + "," + "'" + box.getSelectedItem() + "'" + 
				"," + countBox.getSelectedItem() + "," + subTotalLabel.getText() + ",0)";
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
		addGoodsItem();
		price1Label.setText("0");
		count1ComboBox.removeAllItems();
		subTotal1Label.setText("0");
		price2Label.setText("0");
		count2ComboBox.removeAllItems();
		subTotal2Label.setText("0");
		price3Label.setText("0");
		count3ComboBox.removeAllItems();
		subTotal3Label.setText("0");
		totalLabel.setText("0");
		taxLabel.setText("0"); 
		goodsCode1ComboBox.setEnabled(false);
		goodsName1ComboBox.setEnabled(false);
		goodsCode2ComboBox.setEnabled(false);
		goodsName2ComboBox.setEnabled(false);
		goodsCode3ComboBox.setEnabled(false);
		goodsName3ComboBox.setEnabled(false);
		
		numberSQL();
	}
	
	//商品コードと商品名を選択するコンボボックスに項目を追加するメソッド
	//ただし、在庫残高があるもののみ
	public void addGoodsItem() {
		goodsCode1ComboBox.removeActionListener(this);
		goodsCode2ComboBox.removeActionListener(this);
		goodsCode3ComboBox.removeActionListener(this);
		goodsName1ComboBox.removeActionListener(this);
		goodsName2ComboBox.removeActionListener(this);
		goodsName3ComboBox.removeActionListener(this);
		
		goodsCode1ComboBox.removeAllItems();
		goodsCode2ComboBox.removeAllItems();
		goodsCode3ComboBox.removeAllItems();
		goodsName1ComboBox.removeAllItems();
		goodsName2ComboBox.removeAllItems();
		goodsName3ComboBox.removeAllItems();
		
		try {
			//まず、仕入されたことのある商品を取得し配列に格納する
			ArrayList<String> goodsCode = new ArrayList<>();
			SQL = "SELECT 商品コード FROM 在庫マスタ GROUP BY 商品コード";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				goodsCode.add(rs.getString("商品コード"));
			}
			for(int i = 0; i < goodsCode.size(); i++) {
				//次に、仕入れた商品ごとに合計受入数量と合計払出数量を求める
				//その結果、残高が0より大きければ、商品コードと商品名を選択するコンボボックスに項目を追加する
				SQL = "SELECT SUM(仕入数) AS 受入数量 FROM 在庫マスタ "
					+ "WHERE 商品コード = '" + goodsCode.get(i) + "' GROUP BY 商品コード;";
				rs = stmt.executeQuery(SQL);
				int inSum = 0;
				if(rs.next()) {
					inSum = rs.getInt("受入数量");
				}
				SQL = "SELECT SUM(個数) AS 払出数量 FROM 売上マスタ "
					+ "WHERE 商品コード = '" + goodsCode.get(i) + "' GROUP BY 商品コード;";
				rs = stmt.executeQuery(SQL);
				int outSum = 0;
				if(rs.next()) {
					outSum = rs.getInt("払出数量");
				}
				if(inSum - outSum > 0) {
					goodsCode1ComboBox.addItem(goodsCode.get(i));
					goodsCode2ComboBox.addItem(goodsCode.get(i));
					goodsCode3ComboBox.addItem(goodsCode.get(i));
					SQL = "SELECT 商品名 FROM 商品マスタ WHERE 商品コード = '" + goodsCode.get(i) + "'";
					rs = stmt.executeQuery(SQL);
					if(rs.next()) {
						goodsName1ComboBox.addItem(rs.getString("商品名"));
						goodsName2ComboBox.addItem(rs.getString("商品名"));
						goodsName3ComboBox.addItem(rs.getString("商品名"));	
					}
				}	
			}
			goodsCode1ComboBox.setSelectedItem(null);
			goodsCode2ComboBox.setSelectedItem(null);
			goodsCode3ComboBox.setSelectedItem(null);
			
			goodsName1ComboBox.setSelectedItem(null);
			goodsName2ComboBox.setSelectedItem(null);
			goodsName3ComboBox.setSelectedItem(null);
			
			goodsCode1ComboBox.addActionListener(this);
			goodsCode2ComboBox.addActionListener(this);
			goodsCode3ComboBox.addActionListener(this);
			goodsName1ComboBox.addActionListener(this);
			goodsName2ComboBox.addActionListener(this);
			goodsName3ComboBox.addActionListener(this);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//商品選択欄と個数欄のActionListenerをオフにするメソッド
	public void offActionListener() {
		System.out.println("オフメソッド");
		goodsCode1ComboBox.removeActionListener(this);
		goodsCode2ComboBox.removeActionListener(this);
		goodsCode3ComboBox.removeActionListener(this);
		goodsName1ComboBox.removeActionListener(this);
		goodsName2ComboBox.removeActionListener(this);
		goodsName3ComboBox.removeActionListener(this);
		count1ComboBox.removeActionListener(this);
		count2ComboBox.removeActionListener(this);
		count3ComboBox.removeActionListener(this);
	}
	
	//商品選択欄のActionListenerをオンにするメソッド
	public void onActionListener() {
		goodsCode1ComboBox.addActionListener(this);
		goodsCode2ComboBox.addActionListener(this);
		goodsCode3ComboBox.addActionListener(this);
		goodsName1ComboBox.addActionListener(this);
		goodsName2ComboBox.addActionListener(this);
		goodsName3ComboBox.addActionListener(this);
		count1ComboBox.addActionListener(this);
		count2ComboBox.addActionListener(this);
		count3ComboBox.addActionListener(this);

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
