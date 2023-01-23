import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

public class StockSearchGUI extends JFrame implements ActionListener{
	
	JLabel filterLabel = new JLabel("絞り込み条件");//絞り込み条件の入力欄であることを示すラベル
	
	//絞り込み条件の入力欄
	JLabel fcLabel = new JLabel("  商品コード"); //商品コードの選択欄であることを示すラベル
	JComboBox codeComboBox = new JComboBox(); //商品コードの選択欄 
	JLabel fnLabel = new JLabel("  商品名"); //商品名の選択欄であることを示すラベル
	JComboBox nameComboBox = new JComboBox(); //商品名の選択欄
	
	JButton searchButton = new JButton("絞り込み"); //検索ボタン
	JButton releaseButton = new JButton("絞り込み解除"); //絞り込み解除ボタン
	
	//検索結果の表示場所
	
	JLabel cLabel = new JLabel("商品コード", JLabel.CENTER); //商品コードであることを示すラベル
	JLabel codeLabel1 = new JLabel("", JLabel.CENTER); //商品コードを表示するラベル
	JLabel codeLabel2 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel3 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel4 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel5 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel6 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel7 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel8 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel9 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel nLabel = new JLabel("商品名", JLabel.CENTER); //商品名であることを示すラベル
	JLabel nameLabel1 = new JLabel("", JLabel.CENTER); //商品名を表示するラベル
	JLabel nameLabel2 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel3 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel4 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel5 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel6 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel7 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel8 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel9 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel inLabel = new JLabel("受入", JLabel.CENTER); //受入であることを示すラベル
	
	JLabel inqLabel = new JLabel("　合計数量　", JLabel.CENTER); //受入の合計数量であることを示すラベル
	JLabel inQuantityLabel1 = new JLabel("", JLabel.CENTER); //受入の合計数量を表示するラベル
	JLabel inQuantityLabel2 = new JLabel("", JLabel.CENTER);
	JLabel inQuantityLabel3 = new JLabel("", JLabel.CENTER);
	JLabel inQuantityLabel4 = new JLabel("", JLabel.CENTER);
	JLabel inQuantityLabel5 = new JLabel("", JLabel.CENTER);
	JLabel inQuantityLabel6 = new JLabel("", JLabel.CENTER);
	JLabel inQuantityLabel7 = new JLabel("", JLabel.CENTER);
	JLabel inQuantityLabel8 = new JLabel("", JLabel.CENTER);
	JLabel inQuantityLabel9 = new JLabel("", JLabel.CENTER);
	JLabel inQuantityLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel inpLabel = new JLabel("　合計金額　"); //受入の合計金額であることを示すラベル
	JLabel inPriceLabel1 = new JLabel("", JLabel.CENTER); //受入の合計金額を表示するラベル
	JLabel inPriceLabel2 = new JLabel("", JLabel.CENTER);
	JLabel inPriceLabel3 = new JLabel("", JLabel.CENTER);
	JLabel inPriceLabel4 = new JLabel("", JLabel.CENTER);
	JLabel inPriceLabel5 = new JLabel("", JLabel.CENTER);
	JLabel inPriceLabel6 = new JLabel("", JLabel.CENTER);
	JLabel inPriceLabel7 = new JLabel("", JLabel.CENTER);
	JLabel inPriceLabel8 = new JLabel("", JLabel.CENTER);
	JLabel inPriceLabel9 = new JLabel("", JLabel.CENTER);
	JLabel inPriceLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel outLabel = new JLabel("払出", JLabel.CENTER); //払出であることを示すラベル
	
	JLabel outqLabel = new JLabel("　合計数量　", JLabel.CENTER); //払出の合計数量であることを示すラベル
	JLabel outQuantityLabel1 = new JLabel("", JLabel.CENTER); //払出の合計数量を表示するラベル
	JLabel outQuantityLabel2 = new JLabel("", JLabel.CENTER);
	JLabel outQuantityLabel3 = new JLabel("", JLabel.CENTER);
	JLabel outQuantityLabel4 = new JLabel("", JLabel.CENTER);
	JLabel outQuantityLabel5 = new JLabel("", JLabel.CENTER);
	JLabel outQuantityLabel6 = new JLabel("", JLabel.CENTER);
	JLabel outQuantityLabel7 = new JLabel("", JLabel.CENTER);
	JLabel outQuantityLabel8 = new JLabel("", JLabel.CENTER);
	JLabel outQuantityLabel9 = new JLabel("", JLabel.CENTER);
	JLabel outQuantityLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel outpLabel = new JLabel("　合計金額　"); //払出の合計金額であることを示すラベル
	JLabel outPriceLabel1 = new JLabel("", JLabel.CENTER); //払出の合計金額を表示するラベル
	JLabel outPriceLabel2 = new JLabel("", JLabel.CENTER);
	JLabel outPriceLabel3 = new JLabel("", JLabel.CENTER);
	JLabel outPriceLabel4 = new JLabel("", JLabel.CENTER);
	JLabel outPriceLabel5 = new JLabel("", JLabel.CENTER);
	JLabel outPriceLabel6 = new JLabel("", JLabel.CENTER);
	JLabel outPriceLabel7 = new JLabel("", JLabel.CENTER);
	JLabel outPriceLabel8 = new JLabel("", JLabel.CENTER);
	JLabel outPriceLabel9 = new JLabel("", JLabel.CENTER);
	JLabel outPriceLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel balanceLabel = new JLabel("残高", JLabel.CENTER); //残高であることを示すラベル
	
	JLabel balanceqLabel = new JLabel("　合計数量　", JLabel.CENTER); //残高の合計数量であることを示すラベル
	JLabel balanceQuantityLabel1 = new JLabel("", JLabel.CENTER); //残高の合計数量を表示するラベル
	JLabel balanceQuantityLabel2 = new JLabel("", JLabel.CENTER);
	JLabel balanceQuantityLabel3 = new JLabel("", JLabel.CENTER);
	JLabel balanceQuantityLabel4 = new JLabel("", JLabel.CENTER);
	JLabel balanceQuantityLabel5 = new JLabel("", JLabel.CENTER);
	JLabel balanceQuantityLabel6 = new JLabel("", JLabel.CENTER);
	JLabel balanceQuantityLabel7 = new JLabel("", JLabel.CENTER);
	JLabel balanceQuantityLabel8 = new JLabel("", JLabel.CENTER);
	JLabel balanceQuantityLabel9 = new JLabel("", JLabel.CENTER);
	JLabel balanceQuantityLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel balancepLabel = new JLabel("　合計金額　"); //残高の合計金額であることを示すラベル
	JLabel balancePriceLabel1 = new JLabel("", JLabel.CENTER); //残高の合計金額を表示するラベル
	JLabel balancePriceLabel2 = new JLabel("", JLabel.CENTER);
	JLabel balancePriceLabel3 = new JLabel("", JLabel.CENTER);
	JLabel balancePriceLabel4 = new JLabel("", JLabel.CENTER);
	JLabel balancePriceLabel5 = new JLabel("", JLabel.CENTER);
	JLabel balancePriceLabel6 = new JLabel("", JLabel.CENTER);
	JLabel balancePriceLabel7 = new JLabel("", JLabel.CENTER);
	JLabel balancePriceLabel8 = new JLabel("", JLabel.CENTER);
	JLabel balancePriceLabel9 = new JLabel("", JLabel.CENTER);
	JLabel balancePriceLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel totalNumberLabel = new JLabel("0"); //検索結果の総件数を表示するラベル
	JLabel tnLabel = new JLabel("件中");
	JLabel showNumberLabel = new JLabel("0"); //表示件数を表示するラベル
	JLabel snLabel = new JLabel("件目まで表示 /");
	
	JButton nextButton = new JButton("次へ"); //ページをめくるボタン
	JButton previousButton = new JButton("前へ"); 
	
	int now, last; //ページをめくる際に使用
	
	String URL = "jdbc:mysql://127.0.0.1:3306/販売管理"; //SQLで使用
	String USER = "店員1";
	String PASS = "password";
	String SQL;
	String filterSQL = "";
	String selectSQL = "SELECT";
	String subSelectSQL = "SELECT *"; 
	String timeSQL = ""; 
	String groupBySQL = "";
	String whereSQL = "";
	Connection conn;
	Statement stmt;
	ResultSet rs;
	ResultSet otherRs;
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel4_1 = new JPanel();
	JPanel panel4_2 = new JPanel();
	JPanel panel4_3 = new JPanel();
	JPanel panel4_4 = new JPanel();
	JPanel panel4_5 = new JPanel();
	JPanel panel4_6 = new JPanel();
	JPanel panel4_7 = new JPanel();
	JPanel panel4_8 = new JPanel();
	JPanel panel4_9 = new JPanel();
	JPanel panel4_10 = new JPanel();
	JPanel panel4_11 = new JPanel();
	JPanel panel4_12 = new JPanel();
	JPanel panel4_13 = new JPanel();
	JPanel panel4_14 = new JPanel();
	JPanel panel4_15 = new JPanel();
	JPanel panel4_16 = new JPanel();
	JPanel panel4_17 = new JPanel();
	JPanel panel4_18 = new JPanel();
	JPanel panel4_19 = new JPanel();
	JPanel panel4_20 = new JPanel();
	JPanel panel4_21 = new JPanel();
	JPanel panel4_22 = new JPanel();
	JPanel panel4_23 = new JPanel();
	JPanel panel4_24 = new JPanel();
	JPanel panel4_25 = new JPanel();
	JPanel panel4_26 = new JPanel();
	JPanel panel4_27 = new JPanel();
	JPanel panel4_28 = new JPanel();
	JPanel panel4_29 = new JPanel();
	JPanel panel4_30 = new JPanel();
	JPanel panel4_31 = new JPanel();
	JPanel panel4_32 = new JPanel();
	JPanel panel4_33 = new JPanel();
	JPanel panel4_34 = new JPanel();
	JPanel panel4_35 = new JPanel();
	JPanel panel4_36 = new JPanel();
	JPanel panel5 = new JPanel();
	
	StockSearchGUI(){
		setTitle("売上検索、集計");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//レイアウト設定
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new GridLayout(11, 5, 0, 2));
		panel4_1.setLayout(new GridLayout(2, 1, 10, 2));
		panel4_2.setLayout(new GridLayout(1, 2, 10, 2));
		panel4_3.setLayout(new GridLayout(2, 1, 10, 2));
		panel4_4.setLayout(new GridLayout(1, 2, 10, 2));
		panel4_5.setLayout(new GridLayout(2, 1, 10, 2));
		panel4_6.setLayout(new GridLayout(1, 2, 10, 2));
		panel4_7.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_8.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_9.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_10.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_11.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_12.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_13.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_14.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_15.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_16.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_17.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_18.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_19.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_20.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_21.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_22.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_23.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_24.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_25.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_26.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_27.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_28.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_29.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_30.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_31.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_32.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_33.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_34.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_35.setLayout(new GridLayout(1, 2, 0, 2));
		panel4_36.setLayout(new GridLayout(1, 2, 0, 2));
		panel5.setLayout(new FlowLayout());
		
		//商品コードを選択するコンボボックスに項目を追加
		codeComboBox.addItem(null);
		try {
			SQL = "SELECT 商品コード FROM 商品マスタ;";
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				codeComboBox.addItem(rs.getString("商品コード"));

			}
			codeComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		//商品名を選択するコンボボックスに項目を追加
		nameComboBox.addItem(null);
		try {
			SQL = "SELECT 商品名 FROM 商品マスタ;";
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				nameComboBox.addItem(rs.getString("商品名"));

			}
			nameComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		
		panel1.add(filterLabel);
		panel2.add(fcLabel);
		panel2.add(codeComboBox);
		panel2.add(fnLabel);
		panel2.add(nameComboBox);
		panel3.add(searchButton);
		panel3.add(releaseButton);
		panel4_1.add(inLabel);
		panel4_2.add(inqLabel);
		panel4_2.add(inpLabel);
		panel4_1.add(panel4_2);
		
		panel4_3.add(outLabel);
		panel4_4.add(outqLabel);
		panel4_4.add(outpLabel);
		panel4_3.add(panel4_4);
		
		panel4_5.add(balanceLabel);
		panel4_6.add(balanceqLabel);
		panel4_6.add(balancepLabel);
		panel4_5.add(panel4_6);
		
		panel4_7.add(inQuantityLabel1);
		panel4_7.add(inPriceLabel1);
		panel4_8.add(inQuantityLabel2);
		panel4_8.add(inPriceLabel2);
		panel4_9.add(inQuantityLabel3);
		panel4_9.add(inPriceLabel3);
		panel4_10.add(inQuantityLabel4);
		panel4_10.add(inPriceLabel4);
		panel4_11.add(inQuantityLabel5);
		panel4_11.add(inPriceLabel5);
		panel4_12.add(inQuantityLabel6);
		panel4_12.add(inPriceLabel6);
		panel4_13.add(inQuantityLabel7);
		panel4_13.add(inPriceLabel7);
		panel4_14.add(inQuantityLabel8);
		panel4_14.add(inPriceLabel8);
		panel4_15.add(inQuantityLabel9);
		panel4_15.add(inPriceLabel9);
		panel4_16.add(inQuantityLabel10);
		panel4_16.add(inPriceLabel10);
		
		panel4_17.add(outQuantityLabel1);
		panel4_17.add(outPriceLabel1);
		panel4_18.add(outQuantityLabel2);
		panel4_18.add(outPriceLabel2);
		panel4_19.add(outQuantityLabel3);
		panel4_19.add(outPriceLabel3);
		panel4_20.add(outQuantityLabel4);
		panel4_20.add(outPriceLabel4);
		panel4_21.add(outQuantityLabel5);
		panel4_21.add(outPriceLabel5);
		panel4_22.add(outQuantityLabel6);
		panel4_22.add(outPriceLabel6);
		panel4_23.add(outQuantityLabel7);
		panel4_23.add(outPriceLabel7);
		panel4_24.add(outQuantityLabel8);
		panel4_24.add(outPriceLabel8);
		panel4_25.add(outQuantityLabel9);
		panel4_25.add(outPriceLabel9);
		panel4_26.add(outQuantityLabel10);
		panel4_26.add(outPriceLabel10);
		
		panel4_27.add(balanceQuantityLabel1);
		panel4_27.add(balancePriceLabel1);
		panel4_28.add(balanceQuantityLabel2);
		panel4_28.add(balancePriceLabel2);
		panel4_29.add(balanceQuantityLabel3);
		panel4_29.add(balancePriceLabel3);
		panel4_30.add(balanceQuantityLabel4);
		panel4_30.add(balancePriceLabel4);
		panel4_31.add(balanceQuantityLabel5);
		panel4_31.add(balancePriceLabel5);
		panel4_32.add(balanceQuantityLabel6);
		panel4_32.add(balancePriceLabel6);
		panel4_33.add(balanceQuantityLabel7);
		panel4_33.add(balancePriceLabel7);
		panel4_34.add(balanceQuantityLabel8);
		panel4_34.add(balancePriceLabel8);
		panel4_35.add(balanceQuantityLabel9);
		panel4_35.add(balancePriceLabel9);
		panel4_36.add(balanceQuantityLabel10);
		panel4_36.add(balancePriceLabel10);
		
		panel4.add(cLabel);
		panel4.add(nLabel);
		panel4.add(panel4_1);
		panel4.add(panel4_3);
		panel4.add(panel4_5);
		panel4.add(codeLabel1);
		panel4.add(nameLabel1);
		panel4.add(panel4_7);
		panel4.add(panel4_17);
		panel4.add(panel4_27);
		panel4.add(codeLabel2);
		panel4.add(nameLabel2);
		panel4.add(panel4_8);
		panel4.add(panel4_18);
		panel4.add(panel4_28);
		panel4.add(codeLabel3);
		panel4.add(nameLabel3);
		panel4.add(panel4_9);
		panel4.add(panel4_19);
		panel4.add(panel4_29);
		panel4.add(codeLabel4);
		panel4.add(nameLabel4);
		panel4.add(panel4_10);
		panel4.add(panel4_20);
		panel4.add(panel4_30);
		panel4.add(codeLabel5);
		panel4.add(nameLabel5);
		panel4.add(panel4_11);
		panel4.add(panel4_21);
		panel4.add(panel4_31);
		panel4.add(codeLabel6);
		panel4.add(nameLabel6);
		panel4.add(panel4_12);
		panel4.add(panel4_22);
		panel4.add(panel4_32);
		panel4.add(codeLabel7);
		panel4.add(nameLabel7);
		panel4.add(panel4_13);
		panel4.add(panel4_23);
		panel4.add(panel4_33);
		panel4.add(codeLabel8);
		panel4.add(nameLabel8);
		panel4.add(panel4_14);
		panel4.add(panel4_24);
		panel4.add(panel4_34);
		panel4.add(codeLabel9);
		panel4.add(nameLabel9);
		panel4.add(panel4_15);
		panel4.add(panel4_25);
		panel4.add(panel4_35);
		panel4.add(codeLabel10);
		panel4.add(nameLabel10);
		panel4.add(panel4_16);
		panel4.add(panel4_26);
		panel4.add(panel4_36);
		
		panel5.add(previousButton);
		panel5.add(showNumberLabel);
		panel5.add(snLabel);
		panel5.add(totalNumberLabel);
		panel5.add(tnLabel);
		panel5.add(nextButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		getContentPane().add(panel4);
		getContentPane().add(panel5);
		
		codeComboBox.addActionListener(this);
		nameComboBox.addActionListener(this);
		searchButton.addActionListener(this);
		releaseButton.addActionListener(this);
		nextButton.addActionListener(this);
		previousButton.addActionListener(this);
		nextButton.setEnabled(false);
		previousButton.setEnabled(false);
		this.pack();
		setVisible(true);
		getData();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchButton) {
			getData();
		} else if(e.getSource() == releaseButton) {
			codeComboBox.setSelectedItem(null);
			nameComboBox.setSelectedItem(null);
			getData();
		} else if(e.getSource() == nextButton) {
			result(); //検索結果を表示
			showNumberLabel.setText(Integer.toString(now));
			previousButton.setEnabled(true);
			if(now == last) { //最後の行を表示している場合は「次へ」ボタンをfalseにする
				nextButton.setEnabled(false);
				}
		} else if(e.getSource() == previousButton) {
			now = 10 * (int)Math.floor((now-1)/10) - 10; // 現在行を前ページの先頭のひとつ前に戻す
			try {
				rs.absolute(now);
				result(); //検索結果を表示
				showNumberLabel.setText(Integer.toString(now));
				nextButton.setEnabled(true);
				if(now == 10) { //初めの10件を表示している場合は「前へ」ボタンをfalseにする
					previousButton.setEnabled(false);
				}
			} catch (SQLException e3) {
				e3.printStackTrace();
			} catch(Exception e3) {
				e3.printStackTrace();
			}
		} else if(e.getSource() == codeComboBox) {
			try {
				SQL = "SELECT 商品名 FROM 商品マスタ WHERE 商品コード = '" + codeComboBox.getSelectedItem() + "';";
				Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet goodsRs = stmt.executeQuery(SQL);
				while(goodsRs.next()){
					nameComboBox.setSelectedItem(goodsRs.getString("商品名"));
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		} else if(e.getSource() == nameComboBox) {
			try {
				SQL = "SELECT 商品コード FROM 商品マスタ WHERE 商品名 = '" + nameComboBox.getSelectedItem() + "';";
				Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet goodsRs = stmt.executeQuery(SQL);
				while(goodsRs.next()){
					codeComboBox.setSelectedItem(goodsRs.getString("商品コード"));
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//ラベルの色を設定するメソッド
	public void setColor1(JLabel label) {
		label.setOpaque(true);
		label.setBackground(new Color(174,224,228));
	}
	public void setColor2(JLabel label) {
		label.setOpaque(true);
		label.setBackground(new Color(196,206,222));
	}
	
	//検索条件からデータを取得するメソッド
	public void getData(){
		SQL = createSQL();
		System.out.println(SQL + " で検索します");
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(SQL);
			result(); //検索結果を表示
			this.pack(); //フレームのサイズ調整
			rs.last(); //最後の行に移動し、行番号を取得
			last = rs.getRow();
			rs.absolute(now); //元の行に戻る
			totalNumberLabel.setText(Integer.toString(last));
			showNumberLabel.setText(Integer.toString(now));
			if(last > 10) { //取得件数が11件以上ならページをめくるボタンをture、そうでないならfalseにする
				nextButton.setEnabled(true);
			} else {
				nextButton.setEnabled(false);
				previousButton.setEnabled(false);
			}
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//検索条件を指定するSQLを作成するメソッド
	public String createSQL(){
		if(codeComboBox.getSelectedItem() != null) {
			filterSQL += " AND 商品コード = '" + codeComboBox.getSelectedItem() + "'";
		}
		if(nameComboBox.getSelectedItem() != null) {
			filterSQL += " AND 商品名 = '" + nameComboBox.getSelectedItem() + "'";
		}
		String str = "SELECT 商品コード, 商品名 FROM 商品マスタ WHERE 1" + filterSQL + ";";
		filterSQL = ""; //filterSQLをリセットする
		return str;
	}

	
	
	//それぞれのラベルに検索結果を表示するメソッド
	public void result() {
		try {
			if(rs.next()){
				show(codeLabel1, nameLabel1, inQuantityLabel1, inPriceLabel1, outQuantityLabel1, outPriceLabel1,
						balanceQuantityLabel1, balancePriceLabel1);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel1, nameLabel1, inQuantityLabel1, inPriceLabel1, outQuantityLabel1, outPriceLabel1, 
						balanceQuantityLabel1, balancePriceLabel1 );
			}
			if(rs.next()){
				show(codeLabel2, nameLabel2, inQuantityLabel2, inPriceLabel2, outQuantityLabel2, outPriceLabel2,
						balanceQuantityLabel2, balancePriceLabel2);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel2, nameLabel2, inQuantityLabel2, inPriceLabel2, outQuantityLabel2, outPriceLabel2,
						balanceQuantityLabel2, balancePriceLabel2);
			}
			if(rs.next()){
				show(codeLabel3, nameLabel3, inQuantityLabel3, inPriceLabel3, outQuantityLabel3, outPriceLabel3,
						balanceQuantityLabel3, balancePriceLabel3);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel3, nameLabel3, inQuantityLabel3, inPriceLabel3, outQuantityLabel3, outPriceLabel3,
						balanceQuantityLabel3, balancePriceLabel3);
			}
			if(rs.next()){
				show(codeLabel4, nameLabel4, inQuantityLabel4, inPriceLabel4, outQuantityLabel4, outPriceLabel4,
						balanceQuantityLabel4, balancePriceLabel4);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel4, nameLabel4, inQuantityLabel4, inPriceLabel4, outQuantityLabel4, outPriceLabel4,
						balanceQuantityLabel4, balancePriceLabel4);
			}
			if(rs.next()){
				show(codeLabel5, nameLabel5, inQuantityLabel5, inPriceLabel5, outQuantityLabel5, outPriceLabel5,
						balanceQuantityLabel5, balancePriceLabel5);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel5, nameLabel5, inQuantityLabel5, inPriceLabel5, outQuantityLabel5, outPriceLabel5,
						balanceQuantityLabel5, balancePriceLabel5);
			}
			if(rs.next()){
				show(codeLabel6, nameLabel6, inQuantityLabel6, inPriceLabel6, outQuantityLabel6, outPriceLabel6,
						balanceQuantityLabel6, balancePriceLabel6);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel6, nameLabel6, inQuantityLabel6, inPriceLabel6, outQuantityLabel6, outPriceLabel6,
						balanceQuantityLabel6, balancePriceLabel6);
			}
			if(rs.next()){
				show(codeLabel7, nameLabel7, inQuantityLabel7, inPriceLabel7, outQuantityLabel7, outPriceLabel7,
						balanceQuantityLabel7, balancePriceLabel7);	
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel7, nameLabel7, inQuantityLabel7, inPriceLabel7, outQuantityLabel7, outPriceLabel7,
						balanceQuantityLabel7, balancePriceLabel7);
			}
			if(rs.next()){
				show(codeLabel8, nameLabel8, inQuantityLabel8, inPriceLabel8, outQuantityLabel8, outPriceLabel8,
						balanceQuantityLabel8, balancePriceLabel8);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel8, nameLabel8, inQuantityLabel8, inPriceLabel8, outQuantityLabel8, outPriceLabel8,
						balanceQuantityLabel8, balancePriceLabel8);
			}
			if(rs.next()){
				show(codeLabel9, nameLabel9, inQuantityLabel9, inPriceLabel9, outQuantityLabel9, outPriceLabel9,
						balanceQuantityLabel9, balancePriceLabel9);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel9, nameLabel9, inQuantityLabel9, inPriceLabel9, outQuantityLabel9, outPriceLabel9,
						balanceQuantityLabel9, balancePriceLabel9);
			}
			if(rs.next()){
				show(codeLabel10, nameLabel10, inQuantityLabel10, inPriceLabel10, outQuantityLabel10, outPriceLabel10,
						balanceQuantityLabel10, balancePriceLabel10);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel10, nameLabel10, inQuantityLabel10, inPriceLabel10, outQuantityLabel10, outPriceLabel10,
						balanceQuantityLabel10, balancePriceLabel10);
			}
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullでなければラベルに内容を表示するメソッド
	public void show(JLabel codeLabel, JLabel nameLabel, JLabel inQuantityLabel, JLabel inPriceLabel, 
			JLabel outQuantityLabel, JLabel outPriceLabel, JLabel balanceQuantityLabel, JLabel balancePriceLabel) {
		try {
			//まず、商品コードと商品名を表示する
			codeLabel.setText(rs.getString("商品コード"));
			nameLabel.setText(rs.getString("商品名"));
			//次に、表示した商品コードをもとに受入数量・金額を取得する
			//受入がない場合は0を記す
			SQL = "SELECT 商品コード, SUM(受入数量) AS 受入数量, SUM(受入金額) AS 受入金額"
				+ " FROM (SELECT 商品コード, 仕入単価, SUM(仕入数) AS 受入数量, SUM(仕入数)*仕入単価 AS 受入金額"
				+ " FROM 在庫マスタ GROUP BY 商品コード, 仕入単価) Z"
				+ " WHERE 商品コード = '" + codeLabel.getText() + "' GROUP BY 商品コード;";
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			otherRs = stmt.executeQuery(SQL);
			if(otherRs.next()) {
				inQuantityLabel.setText(otherRs.getString("受入数量") + "個");
				inPriceLabel.setText("￥" + otherRs.getString("受入金額"));
			} else {
				inQuantityLabel.setText("0個");
				inPriceLabel.setText("￥0");
			}
			//続いて、表示した商品コードをもとに払出数量・金額を取得する
			//払出がない場合は0を記す
			SQL = "SELECT Z.商品コード, 払出数量, 払出数量*仕入単価 AS 払出金額"
				+ " FROM (SELECT 商品コード, SUM(個数) AS 払出数量 FROM 売上マスタ GROUP BY 商品コード) U,"
				+ " (SELECT 商品コード, 仕入単価, SUM(仕入数) AS 受入数量, SUM(仕入数)*仕入単価 AS 受入金額"
				+ " FROM 在庫マスタ GROUP BY 商品コード, 仕入日, 仕入単価) Z"
				+ " WHERE Z.商品コード = U.商品コード AND Z.商品コード = '" + codeLabel.getText() + "'"
				+ " GROUP BY Z.商品コード;";
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			otherRs = stmt.executeQuery(SQL);
			if(otherRs.next()) {
				outQuantityLabel.setText(otherRs.getString("払出数量") + "個");
				outPriceLabel.setText("￥" + otherRs.getString("払出金額"));
			} else {
				outQuantityLabel.setText("0個");
				outPriceLabel.setText("￥0");
			}
			//最後に、受入数量・金額から払出数量・金額を差し引いて残高を計算する
			int i = Integer.parseInt(inQuantityLabel.getText().replace("個", "")) - 
						Integer.parseInt(outQuantityLabel.getText().replace("個", ""));
			
			int j = Integer.parseInt(inPriceLabel.getText().replace("￥", "")) - 
						Integer.parseInt(outPriceLabel.getText().replace("￥", ""));
			
			balanceQuantityLabel.setText(Integer.toString(i) + "個");
			balancePriceLabel.setText("￥" + Integer.toString(j));
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
//SELECT Z.商品コード, 払出数量, 払出数量*仕入単価 AS 払出金額 FROM (SELECT 商品コード, SUM(個数) AS 払出数量 FROM 売上マスタ GROUP BY 商品コード) U, (SELECT 商品コード, 仕入単価, SUM(仕入数) AS 受入数量, SUM(仕入数)*仕入単価 AS 受入金額 FROM 在庫マスタ GROUP BY 商品コード, 仕入日, 仕入単価) Z WHERE Z.商品コード = U.商品コード AND Z.商品コード = 'A001' GROUP BY Z.商品コード;		

	}
	
	//Resultsetオブジェクトがnullならばラベルを白紙にするメソッド
	public void reset(JLabel codeLabel, JLabel nameLabel, JLabel inQuantityLabel, JLabel inPriceLabel, 
			JLabel outQuantityLabel, JLabel outPriceLabel, JLabel balanceQuantityLabel, JLabel balancePriceLabel) {
		codeLabel.setText(null);
		nameLabel.setText(null);
		inQuantityLabel.setText(null);
		inPriceLabel.setText(null);
		outQuantityLabel.setText(null);
		outPriceLabel.setText(null);
		balanceQuantityLabel.setText(null);
		balancePriceLabel.setText(null);
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
	public String getMinute() {
		Date date = new Date();
		String str = new SimpleDateFormat("mm").format(date);
		return str;
	}
	
}
