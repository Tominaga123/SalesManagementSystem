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

public class SalesSearchGUI extends JFrame implements ActionListener{
	
	JLabel fLabel = new JLabel("絞り込み条件");//絞り込み条件の入力欄であることを示すラベル
	
	//絞り込み条件の入力欄
	JLabel fnLabel = new JLabel("伝票番号"); //伝票番号の入力欄であることを示すラベル
	JTextField numberTextField = new JTextField("すべて", 6); //伝票番号の入力欄 
	
	JLabel ftLabel = new JLabel("販売日時"); //販売日時の入力欄であることを示すラベル
	JComboBox yearComboBox = new JComboBox();
	JLabel fyLabel = new JLabel("年"); //単位「年」を表示するラベル
	JComboBox monthComboBox = new JComboBox();
	JLabel fmLabel = new JLabel("月"); //単位「月」を表示するラベル
	JComboBox dateComboBox = new JComboBox();
	JLabel fdLabel = new JLabel("日"); //単位「日」を表示するラベル
	JComboBox hourComboBox = new JComboBox();
	JLabel fhLabel = new JLabel("時"); //単位「時」を表示するラベル
	JComboBox timeRangeComboBox = new JComboBox(); //選択した日時より以前か等を選択するボックス
	
	JLabel fccLabel = new JLabel("店員コード"); //店員コードの選択欄であることを示すラベル
	JComboBox clerkCodeComboBox = new JComboBox(); //店員コードの選択欄
	JLabel fcnLabel = new JLabel("店員名"); //店員コードの選択欄であることを示すラベル
	JComboBox clerkNameComboBox = new JComboBox(); //店員コードの選択欄
	
	JLabel fgcLabel = new JLabel("商品コード"); //商品コードの選択欄であることを示すラベル
	JComboBox goodsCodeComboBox = new JComboBox(); //商品コードの選択欄 
	JLabel fgnLabel = new JLabel("商品名"); //商品名の選択欄であることを示すラベル
	JComboBox goodsNameComboBox = new JComboBox(); //商品名の選択欄
	
	JLabel ftoLabel = new JLabel("合計額"); //合計額の入力欄であることを示すラベル
	JTextField totalTextField = new JTextField("0", 6); //合計額の入力欄
	JComboBox totalRangeComboBox = new JComboBox(); //入力した合計額以上か等を選択するボックス
	
	
	JButton searchButton = new JButton("検索"); //検索ボタン
	
	JLabel nLabel = new JLabel("伝票番号"); //伝票番号であることを示すラベル
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
	
	JLabel dLabel = new JLabel("販売日時"); //日時であることを示すラベル
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
	
	JLabel ccLabel = new JLabel("店員コード"); //店員コードであることを示すラベル
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
	
	JLabel cnLabel = new JLabel("店員名"); //店員名であることを示すラベル
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
	
	JLabel gcLabel = new JLabel("商品コード"); //商品コードであることを示すラベル
	JLabel goodsCodeLabel1 = new JLabel(); //商品コードを表示するラベル
	JLabel goodsCodeLabel2 = new JLabel();
	JLabel goodsCodeLabel3 = new JLabel();
	JLabel goodsCodeLabel4 = new JLabel();
	JLabel goodsCodeLabel5 = new JLabel();
	JLabel goodsCodeLabel6 = new JLabel();
	JLabel goodsCodeLabel7 = new JLabel();
	JLabel goodsCodeLabel8 = new JLabel();
	JLabel goodsCodeLabel9 = new JLabel();
	JLabel goodsCodeLabel10 = new JLabel();
	
	JLabel gnLabel = new JLabel("商品名"); //商品名であることを示すラベル
	JLabel goodsNameLabel1 = new JLabel(); //商品名を表示するラベル
	JLabel goodsNameLabel2 = new JLabel();
	JLabel goodsNameLabel3 = new JLabel();
	JLabel goodsNameLabel4 = new JLabel();
	JLabel goodsNameLabel5 = new JLabel();
	JLabel goodsNameLabel6 = new JLabel();
	JLabel goodsNameLabel7 = new JLabel();
	JLabel goodsNameLabel8 = new JLabel();
	JLabel goodsNameLabel9 = new JLabel();
	JLabel goodsNameLabel10 = new JLabel();
	
	JLabel ctLabel = new JLabel("個数"); //商品の個数であることを示すラベル
	JLabel countLabel1 = new JLabel(); //商品の個数を表示するラベル
	JLabel countLabel2 = new JLabel();
	JLabel countLabel3 = new JLabel();
	JLabel countLabel4 = new JLabel();
	JLabel countLabel5 = new JLabel();
	JLabel countLabel6 = new JLabel();
	JLabel countLabel7 = new JLabel();
	JLabel countLabel8 = new JLabel();
	JLabel countLabel9 = new JLabel();
	JLabel countLabel10 = new JLabel();
	
	JLabel stLabel = new JLabel("小計"); //小計であることを示すラベル
	JLabel subTotalLabel1 = new JLabel(); //小計を表示するラベル
	JLabel subTotalLabel2 = new JLabel();
	JLabel subTotalLabel3 = new JLabel();
	JLabel subTotalLabel4 = new JLabel();
	JLabel subTotalLabel5 = new JLabel();
	JLabel subTotalLabel6 = new JLabel();
	JLabel subTotalLabel7 = new JLabel();
	JLabel subTotalLabel8 = new JLabel();
	JLabel subTotalLabel9 = new JLabel();
	JLabel subTotalLabel10 = new JLabel();
	
	JLabel totalNumberLabel = new JLabel("0"); //売上マスタの総件数を表示するラベル
	JLabel tnLabel = new JLabel("件中");
	JLabel showNumberLabel = new JLabel("0"); //表示件数を表示するラベル
	JLabel snLabel = new JLabel("件表示");
	
	JButton nextButton = new JButton("次へ"); //ページをめくるボタン
	JButton previousButton = new JButton("前へ"); 
	
	String URL = "jdbc:mysql://127.0.0.1:3306/販売管理"; //SQLで使用
	String USER = "店員1";
	String PASS = "password";
	String SQL;
	String filterSQL = "";
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel2_1 = new JPanel();
	JPanel panel2_2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();
	JPanel panel10 = new JPanel();
	JPanel panel11 = new JPanel();
	JPanel panel12 = new JPanel();
	JPanel panel13 = new JPanel();
	JPanel panel14 = new JPanel();
	JPanel panel15 = new JPanel();
	
	SalesSearchGUI(){
		setTitle("売上検索システム");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2_1.setLayout(new FlowLayout());
		panel2_2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new FlowLayout());
		panel5.setLayout(new FlowLayout());
		panel6.setLayout(new FlowLayout());
		panel7.setLayout(new FlowLayout());
		panel8.setLayout(new FlowLayout());
		panel9.setLayout(new FlowLayout());
		panel10.setLayout(new FlowLayout());
		panel11.setLayout(new FlowLayout());
		panel12.setLayout(new FlowLayout());
		panel13.setLayout(new FlowLayout());
		panel14.setLayout(new FlowLayout());
		panel15.setLayout(new FlowLayout());
		
		
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
		timeRangeComboBox.addItem("以前");
		timeRangeComboBox.addItem("以後");
		timeRangeComboBox.addItem("のみ");
		
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
				goodsCodeComboBox.addItem(rs.getString("商品コード"));

			}
			goodsCodeComboBox.setSelectedItem(null);
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
				goodsCodeComboBox.addItem(rs.getString("商品名"));

			}
			goodsCodeComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		totalRangeComboBox.addItem("以上");
		totalRangeComboBox.addItem("以下");
		totalRangeComboBox.addItem("のみ");
		
		panel1.add(fLabel);
		
		panel2_1.add(fnLabel);
		panel2_1.add(numberTextField);
		panel2_1.add(ftLabel);
		panel2_1.add(yearComboBox);
		panel2_1.add(fyLabel);
		panel2_1.add(monthComboBox);
		panel2_1.add(fmLabel);
		panel2_1.add(dateComboBox);
		panel2_1.add(fdLabel);
		panel2_1.add(hourComboBox);
		panel2_1.add(timeRangeComboBox);
		
		panel2_2.add(fccLabel);
		panel2_2.add(clerkCodeComboBox);
		panel2_2.add(fcnLabel);
		panel2_2.add(clerkNameComboBox);
		panel2_2.add(fgcLabel);
		panel2_2.add(goodsCodeComboBox);
		panel2_2.add(fgnLabel);
		panel2_2.add(goodsNameComboBox);
		panel2_2.add(ftoLabel);
		panel2_2.add(totalTextField);
		panel2_2.add(totalRangeComboBox);
		
		panel2.add(panel2_1);
		panel2.add(panel2_2);
		
		panel3.add(searchButton);
		
		panel4.add(nLabel);
		panel4.add(dLabel);
		panel4.add(ccLabel);
		panel4.add(cnLabel);
		panel4.add(gcLabel);
		panel4.add(gnLabel);
		panel4.add(ctLabel);
		panel4.add(stLabel);
		
		panel5.add(numberLabel1);
		panel5.add(dateLabel1);
		panel5.add(clerkCodeLabel1);
		panel5.add(clerkNameLabel1);
		panel5.add(goodsCodeLabel1);
		panel5.add(goodsNameLabel1);
		panel5.add(countLabel1);
		panel5.add(subTotalLabel1);
		
		panel6.add(numberLabel2);
		panel6.add(dateLabel2);
		panel6.add(clerkCodeLabel2);
		panel6.add(clerkNameLabel2);
		panel6.add(goodsCodeLabel2);
		panel6.add(goodsNameLabel2);
		panel6.add(countLabel2);
		panel6.add(subTotalLabel2);
		
		panel7.add(numberLabel3);
		panel7.add(dateLabel3);
		panel7.add(clerkCodeLabel3);
		panel7.add(clerkNameLabel3);
		panel7.add(goodsCodeLabel3);
		panel7.add(goodsNameLabel3);
		panel7.add(countLabel3);
		panel7.add(subTotalLabel3);
		

		panel8.add(numberLabel4);
		panel8.add(dateLabel4);
		panel8.add(clerkCodeLabel4);
		panel8.add(clerkNameLabel4);
		panel8.add(goodsCodeLabel4);
		panel8.add(goodsNameLabel4);
		panel8.add(countLabel4);
		panel8.add(subTotalLabel4);
		
		panel9.add(numberLabel5);
		panel9.add(dateLabel5);
		panel9.add(clerkCodeLabel5);
		panel9.add(clerkNameLabel5);
		panel9.add(goodsCodeLabel5);
		panel9.add(goodsNameLabel5);
		panel9.add(countLabel5);
		panel9.add(subTotalLabel5);
		
		panel10.add(numberLabel6);
		panel10.add(dateLabel6);
		panel10.add(clerkCodeLabel6);
		panel10.add(clerkNameLabel6);
		panel10.add(goodsCodeLabel6);
		panel10.add(goodsNameLabel6);
		panel10.add(countLabel6);
		panel10.add(subTotalLabel6);
		
		panel11.add(numberLabel7);
		panel11.add(dateLabel7);
		panel11.add(clerkCodeLabel7);
		panel11.add(clerkNameLabel7);
		panel11.add(goodsCodeLabel7);
		panel11.add(goodsNameLabel7);
		panel11.add(countLabel7);
		panel11.add(subTotalLabel7);
		
		panel12.add(numberLabel8);
		panel12.add(dateLabel8);
		panel12.add(clerkCodeLabel8);
		panel12.add(clerkNameLabel8);
		panel12.add(goodsCodeLabel8);
		panel12.add(goodsNameLabel8);
		panel12.add(countLabel8);
		panel12.add(subTotalLabel8);
		
		panel13.add(numberLabel9);
		panel13.add(dateLabel9);
		panel13.add(clerkCodeLabel9);
		panel13.add(clerkNameLabel9);
		panel13.add(goodsCodeLabel9);
		panel13.add(goodsNameLabel9);
		panel13.add(countLabel9);
		panel13.add(subTotalLabel9);
		
		panel14.add(numberLabel10);
		panel14.add(dateLabel10);
		panel14.add(clerkCodeLabel10);
		panel14.add(clerkNameLabel10);
		panel14.add(goodsCodeLabel10);
		panel14.add(goodsNameLabel10);
		panel14.add(countLabel10);
		panel14.add(subTotalLabel10);
		
		panel15.add(previousButton);
		panel15.add(totalNumberLabel);
		panel15.add(tnLabel);
		panel15.add(showNumberLabel);
		panel15.add(snLabel);
		panel15.add(nextButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		getContentPane().add(panel4);
		getContentPane().add(panel5);
		getContentPane().add(panel6);
		getContentPane().add(panel7);
		getContentPane().add(panel8);
		getContentPane().add(panel9);
		getContentPane().add(panel10);
		getContentPane().add(panel11);
		getContentPane().add(panel12);
		getContentPane().add(panel13);
		getContentPane().add(panel14);
		getContentPane().add(panel15);
		
		numberTextField.addActionListener(this);
		searchButton.addActionListener(this);
		nextButton.addActionListener(this);
		previousButton.addActionListener(this);
		this.pack();
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == numberTextField) {
			filterSQL += " AND 伝票番号 = " + numberTextField.getText(); 
			System.out.println("伝票番号 = " + numberTextField.getText() + "を検索条件に加えます");
		}else if(e.getSource() == clerkCodeComboBox) {
			filterSQL += " AND 店員コード = " + clerkCodeComboBox.getSelectedItem(); 
			System.out.println("店員コード = " + clerkCodeComboBox.getSelectedItem() + "を検索条件に加えます");
			
		}else if(e.getSource() == searchButton) {
			SQL = "SELECT 伝票番号, 販売日時, U.店員コード, T.氏名, U.商品コード, S.商品名, 個数, 小計 "
					+ "FROM 売上マスタ U, 商品マスタ S, 店員マスタ T "
					+ "WHERE U.店員コード = T.店員コード AND U.商品コード = S.商品コード" + filterSQL + ";";
			System.out.println("SELECT 伝票番号, 販売日時, U.店員コード, T.氏名, U.商品コード, S.商品名, 個数, 小計 "
					+ "FROM 売上マスタ U, 商品マスタ S, 店員マスタ T "
					+ "WHERE U.店員コード = T.店員コード AND U.商品コード = S.商品コード" + filterSQL + ";で検索します");
			try {
				Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL);
				if(rs.next()){
					show(rs, numberLabel1, dateLabel1, clerkCodeLabel1, clerkNameLabel1, 
							goodsCodeLabel1, goodsNameLabel1, countLabel1, subTotalLabel1);
					if(rs.next()){
						show(rs, numberLabel2, dateLabel2, clerkCodeLabel2, clerkNameLabel2, 
								goodsCodeLabel2, goodsNameLabel2, countLabel2, subTotalLabel2);
						if(rs.next()){
							show(rs, numberLabel3, dateLabel3, clerkCodeLabel3, clerkNameLabel3, 
									goodsCodeLabel3, goodsNameLabel3, countLabel3, subTotalLabel3);
							if(rs.next()){
								show(rs, numberLabel4, dateLabel4, clerkCodeLabel4, clerkNameLabel4, 
										goodsCodeLabel4, goodsNameLabel4, countLabel4, subTotalLabel4);
								if(rs.next()){
									show(rs, numberLabel5, dateLabel5, clerkCodeLabel5, clerkNameLabel5, 
											goodsCodeLabel5, goodsNameLabel5, countLabel5, subTotalLabel5);
									if(rs.next()){
										show(rs, numberLabel6, dateLabel6, clerkCodeLabel6, clerkNameLabel6, 
												goodsCodeLabel6, goodsNameLabel6, countLabel6, subTotalLabel6);
										if(rs.next()){
											show(rs, numberLabel7, dateLabel7, clerkCodeLabel7, clerkNameLabel7, 
													goodsCodeLabel7, goodsNameLabel7, countLabel7, subTotalLabel7);
											if(rs.next()){
												show(rs, numberLabel8, dateLabel8, clerkCodeLabel8, clerkNameLabel8, 
														goodsCodeLabel8, goodsNameLabel8, countLabel8, subTotalLabel8);
												if(rs.next()){
													show(rs, numberLabel9, dateLabel9, clerkCodeLabel9, clerkNameLabel9, 
															goodsCodeLabel9, goodsNameLabel9, countLabel9, subTotalLabel9);
													if(rs.next()){
														show(rs, numberLabel10, dateLabel10, clerkCodeLabel10, clerkNameLabel10, 
																goodsCodeLabel10, goodsNameLabel10, countLabel10, subTotalLabel10);
													}	
												}
											}	
										}
									}	
								}
							}	
						}
					}	
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	//検索結果を表示するメソッド
	public void show(ResultSet rs, JLabel numberLabel, JLabel dateLabel, JLabel clerkCodeLabel, JLabel clerkNameLabel, 
			JLabel goodsCodeLabel, JLabel goodsNameLabel, JLabel countLabel, JLabel subTotalLabel) {
		try {
			numberLabel.setText(rs.getString("伝票番号"));
			dateLabel.setText(rs.getString("販売日時"));
			clerkCodeLabel.setText(rs.getString("店員コード"));
			clerkNameLabel.setText(rs.getString("氏名"));
			goodsCodeLabel.setText(rs.getString("商品コード"));
			goodsNameLabel.setText(rs.getString("商品名"));
			countLabel.setText(rs.getString("個数"));
			subTotalLabel.setText(rs.getString("小計"));
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}

	}
	
	//コンボボックスを現在の日時で初期化するメソッド
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
}
