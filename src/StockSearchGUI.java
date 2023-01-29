import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	JLabel ffLabel = new JLabel("  削除フラグ"); //削除フラグの選択欄であることを示すラベル
	JComboBox flagComboBox = new JComboBox(); //削除フラグの選択欄
	
	JButton searchButton = new JButton("絞り込み"); //検索ボタン
	JButton releaseButton = new JButton("絞り込み解除"); //絞り込み解除ボタン
	
	JButton changeButton = new JButton("詳細表示"); //「全体表示」と「詳細表示」の切り替え
	
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
	
	JLabel fLabel = new JLabel("削除フラグ", JLabel.CENTER); //削除フラグであることを示すラベル
	JLabel flagLabel1 = new JLabel("", JLabel.CENTER); //削除フラグを表示するラベル
	JLabel flagLabel2 = new JLabel("", JLabel.CENTER);
	JLabel flagLabel3 = new JLabel("", JLabel.CENTER);
	JLabel flagLabel4 = new JLabel("", JLabel.CENTER);
	JLabel flagLabel5 = new JLabel("", JLabel.CENTER);
	JLabel flagLabel6 = new JLabel("", JLabel.CENTER);
	JLabel flagLabel7 = new JLabel("", JLabel.CENTER);
	JLabel flagLabel8 = new JLabel("", JLabel.CENTER);
	JLabel flagLabel9 = new JLabel("", JLabel.CENTER);
	JLabel flagLabel10 = new JLabel("", JLabel.CENTER);
	
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
	JButton firstButton = new JButton("最初へ");
	JButton lastButton = new JButton("最後へ"); 
	
	int now, last; //ページをめくる際に使用
	
	//データベースからデータを取得する際に使用
	String SQL;
	String filterSQL = "";
	String selectSQL = "SELECT";
	String subSelectSQL = "SELECT *"; 
	String timeSQL = ""; 
	String groupBySQL = "";
	String whereSQL = "";
	Statement stmt; 
	ResultSet rs; 
	Statement otherStmt; 
	ResultSet otherRs;
	//在庫数量の計算で使用
	int index1;
	int index2;
	int sumQuantity;
	int sumPrice;
	int SUM;
	
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
		setTitle("在庫検索");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//レイアウト設定
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new GridLayout(11, 6, 0, 2));
		panel4_1.setLayout(new GridLayout(2, 1, 0, 0));
		panel4_2.setLayout(new GridLayout(1, 2, 0, 0));
		panel4_3.setLayout(new GridLayout(2, 1, 0, 0));
		panel4_4.setLayout(new GridLayout(1, 2, 0, 0));
		panel4_5.setLayout(new GridLayout(2, 1, 0, 0));
		panel4_6.setLayout(new GridLayout(1, 2, 0, 0));
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
		
		//商品コード、商品名を選択するコンボボックスに項目を追加
		codeComboBox.addItem(null);
		nameComboBox.addItem(null);
		try {
			SQL = "SELECT 商品コード, 商品名 FROM 商品マスタ;";
			stmt = LoginGUI.conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				codeComboBox.addItem(rs.getString("商品コード"));
				nameComboBox.addItem(rs.getString("商品名"));
			}
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		//削除フラグを選択するコンボボックスに項目を追加
		flagComboBox.addItem(null);
		flagComboBox.addItem("0");
		flagComboBox.addItem("1");
		
		panel1.add(filterLabel);
		
		panel2.add(fcLabel);
		panel2.add(codeComboBox);
		panel2.add(fnLabel);
		panel2.add(nameComboBox);
		panel2.add(ffLabel);
		panel2.add(flagComboBox);
		
		panel3.add(searchButton);
		panel3.add(releaseButton);
		panel3.add(changeButton);
		
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
		panel4.add(fLabel);
		panel4.add(panel4_1);
		panel4.add(panel4_3);
		panel4.add(panel4_5);
		panel4.add(codeLabel1);
		panel4.add(nameLabel1);
		panel4.add(flagLabel1);
		panel4.add(panel4_7);
		panel4.add(panel4_17);
		panel4.add(panel4_27);
		panel4.add(codeLabel2);
		panel4.add(nameLabel2);
		panel4.add(flagLabel2);
		panel4.add(panel4_8);
		panel4.add(panel4_18);
		panel4.add(panel4_28);
		panel4.add(codeLabel3);
		panel4.add(nameLabel3);
		panel4.add(flagLabel3);
		panel4.add(panel4_9);
		panel4.add(panel4_19);
		panel4.add(panel4_29);
		panel4.add(codeLabel4);
		panel4.add(nameLabel4);
		panel4.add(flagLabel4);
		panel4.add(panel4_10);
		panel4.add(panel4_20);
		panel4.add(panel4_30);
		panel4.add(codeLabel5);
		panel4.add(nameLabel5);
		panel4.add(flagLabel5);
		panel4.add(panel4_11);
		panel4.add(panel4_21);
		panel4.add(panel4_31);
		panel4.add(codeLabel6);
		panel4.add(nameLabel6);
		panel4.add(flagLabel6);
		panel4.add(panel4_12);
		panel4.add(panel4_22);
		panel4.add(panel4_32);
		panel4.add(codeLabel7);
		panel4.add(nameLabel7);
		panel4.add(flagLabel7);
		panel4.add(panel4_13);
		panel4.add(panel4_23);
		panel4.add(panel4_33);
		panel4.add(codeLabel8);
		panel4.add(nameLabel8);
		panel4.add(flagLabel8);
		panel4.add(panel4_14);
		panel4.add(panel4_24);
		panel4.add(panel4_34);
		panel4.add(codeLabel9);
		panel4.add(nameLabel9);
		panel4.add(flagLabel9);
		panel4.add(panel4_15);
		panel4.add(panel4_25);
		panel4.add(panel4_35);
		panel4.add(codeLabel10);
		panel4.add(nameLabel10);
		panel4.add(flagLabel10);
		panel4.add(panel4_16);
		panel4.add(panel4_26);
		panel4.add(panel4_36);
		
		panel5.add(firstButton);
		panel5.add(previousButton);
		panel5.add(showNumberLabel);
		panel5.add(snLabel);
		panel5.add(totalNumberLabel);
		panel5.add(tnLabel);
		panel5.add(nextButton);
		panel5.add(lastButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		getContentPane().add(panel4);
		getContentPane().add(panel5);
		
		//ラベルを色分けする
		setColor1(cLabel);
		setColor1(nLabel);
		setColor1(fLabel);
		setColor1(inLabel);
		setColor1(inqLabel);
		setColor1(inpLabel);
		setColor1(outLabel);
		setColor1(outqLabel);
		setColor1(outpLabel);
		setColor1(balanceLabel);
		setColor1(balanceqLabel);
		setColor1(balancepLabel);
		setColor2(codeLabel2);
		setColor2(nameLabel2);
		setColor2(flagLabel2);
		setColor2(inQuantityLabel2);
		setColor2(inPriceLabel2);
		setColor2(outQuantityLabel2);
		setColor2(outPriceLabel2);
		setColor2(balanceQuantityLabel2);
		setColor2(balancePriceLabel2);
		setColor2(codeLabel4);
		setColor2(nameLabel4);
		setColor2(flagLabel4);
		setColor2(inQuantityLabel4);
		setColor2(inPriceLabel4);
		setColor2(outQuantityLabel4);
		setColor2(outPriceLabel4);
		setColor2(balanceQuantityLabel4);
		setColor2(balancePriceLabel4);
		setColor2(codeLabel6);
		setColor2(nameLabel6);
		setColor2(flagLabel6);
		setColor2(inQuantityLabel6);
		setColor2(inPriceLabel6);
		setColor2(outQuantityLabel6);
		setColor2(outPriceLabel6);
		setColor2(balanceQuantityLabel6);
		setColor2(balancePriceLabel6);
		setColor2(codeLabel8);
		setColor2(nameLabel8);
		setColor2(flagLabel8);
		setColor2(inQuantityLabel8);
		setColor2(inPriceLabel8);
		setColor2(outQuantityLabel8);
		setColor2(outPriceLabel8);
		setColor2(balanceQuantityLabel8);
		setColor2(balancePriceLabel8);
		setColor2(codeLabel10);
		setColor2(nameLabel10);
		setColor2(flagLabel10);
		setColor2(inQuantityLabel10);
		setColor2(inPriceLabel10);
		setColor2(outQuantityLabel10);
		setColor2(outPriceLabel10);
		setColor2(balanceQuantityLabel10);
		setColor2(balancePriceLabel10);
		
		codeComboBox.addActionListener(this);
		nameComboBox.addActionListener(this);
		searchButton.addActionListener(this);
		releaseButton.addActionListener(this);
		changeButton.addActionListener(this);
		nextButton.addActionListener(this);
		previousButton.addActionListener(this);
		firstButton.addActionListener(this);
		lastButton.addActionListener(this);
		nextButton.setEnabled(false);
		previousButton.setEnabled(false);
		this.pack();
		setVisible(true);
		getData();
	}
	
	public void actionPerformed(ActionEvent e) {
		//「絞り込み」ボタンを押したとき
		if(e.getSource() == searchButton) {
			getData();
		}
		//「絞り込み解除」ボタンを押したとき
		else if(e.getSource() == releaseButton) {
			codeComboBox.setSelectedItem(null);
			nameComboBox.setSelectedItem(null);
			flagComboBox.setSelectedItem(null);
			getData();
		}
		//「次へ」ボタンを押したとき
		else if(e.getSource() == nextButton) {
			result(); //検索結果を表示
		}
		//「前へ」ボタンを押したとき
		else if(e.getSource() == previousButton) {
			now = 10 * (int)Math.floor((now-1)/10) - 10; // 現在行を前ページの先頭のひとつ前に戻す
			try {
				rs.absolute(now);
				result(); //検索結果を表示
			} catch (SQLException e3) {
				e3.printStackTrace();
			} catch(Exception e3) {
				e3.printStackTrace();
			}
		}
		//「最初へ」ボタンを押した場合
		else if(e.getSource() == firstButton) { 
			try {
				rs.beforeFirst(); //先頭行のひとつ前まで戻る
				result(); //1件目から表示
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} 
		//「最後へ」ボタンを押した場合
		else if(e.getSource() == lastButton) { 
			now = 10 * (int)Math.floor((last-1)/10); //現在行を最終ページのひとつ前に戻す
			try {
				rs.absolute(now);
				result();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//商品コードを選択した場合
		else if(e.getSource() == codeComboBox) {
			//商品コードがnullでなければ商品マスタから商品名を取得する
			if(codeComboBox.getSelectedItem() != null) {
				try {
					SQL = "SELECT 商品名 FROM 商品マスタ WHERE 商品コード = '" + codeComboBox.getSelectedItem() + "';";
					otherStmt = LoginGUI.conn.createStatement();
					otherRs = otherStmt.executeQuery(SQL);
					while(otherRs.next()){
						nameComboBox.setSelectedItem(otherRs.getString("商品名"));
					}
				}catch(SQLException e2) {
					e2.printStackTrace();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			//商品コードがnullならば商品名もnullにする
			else {
				nameComboBox.setSelectedItem(null);
			}
		}
		//商品名を選択した場合
		else if(e.getSource() == nameComboBox) {
			//商品名がnullでなければ商品マスタから商品コードを取得する
			if(nameComboBox.getSelectedItem() != null) {
				try {
					SQL = "SELECT 商品コード FROM 商品マスタ WHERE 商品名 = '" + nameComboBox.getSelectedItem() + "';";
					otherStmt = LoginGUI.conn.createStatement();
					otherRs = otherStmt.executeQuery(SQL);
					while(otherRs.next()){
						codeComboBox.setSelectedItem(otherRs.getString("商品コード"));
					}
				}catch(SQLException e2) {
					e2.printStackTrace();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
			//商品名がnullならば商品コードもnullにする
			else {
				codeComboBox.setSelectedItem(null);
			}
		}
		//「詳細表示」または「全体表示」ボタンが押された場合
		else if(e.getSource() == changeButton) {
			//「詳細表示」ボタンが押された場合
			if(changeButton.getText().contains("詳細表示")) {
				changeButton.setText("全体表示");
				searchButton.setText("表示");
				fLabel.setText("日付");
				flagComboBox.setSelectedItem(null);
				flagComboBox.setEnabled(false);
				releaseButton.setEnabled(false);
				getData();
			}
			//「全体表示」ボタンが押された場合
			else {
				changeButton.setText("詳細表示");
				searchButton.setText("絞り込み");
				fLabel.setText("削除フラグ");
				codeComboBox.setSelectedItem(null);
				nameComboBox.setSelectedItem(null);
				flagComboBox.setSelectedItem(null);
				flagComboBox.setEnabled(true);
				releaseButton.setEnabled(true);
				getData();
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
		if(searchButton.getText().equals("絞り込み")) {
			SQL = createSQL();
		}else {
			SQL = createSQL2();
		}
		System.out.println(SQL + " で検索します");
		try {
			stmt = LoginGUI.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(SQL);
			rs.last(); //最後の行に移動し、行番号を取得
			last = rs.getRow();
			rs.beforeFirst(); //初めの行に戻る
			result(); //検索結果を表示
			this.pack(); //フレームのサイズ調整
			totalNumberLabel.setText(Integer.toString(last));
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//「全体表示」のSQLを作成するメソッド
	public String createSQL(){
		if(codeComboBox.getSelectedItem() != null) {
			filterSQL += " AND 商品コード = '" + codeComboBox.getSelectedItem() + "'";
		}
		if(nameComboBox.getSelectedItem() != null) {
			filterSQL += " AND 商品名 = '" + nameComboBox.getSelectedItem() + "'";
		}
		if(flagComboBox.getSelectedItem() != null) {
			filterSQL += " AND 削除フラグ = '" + flagComboBox.getSelectedItem() + "'";
		}
		String str = "SELECT 商品コード, 商品名, 削除フラグ FROM 商品マスタ WHERE 1" + filterSQL + ";";
		filterSQL = ""; //filterSQLをリセットする
		return str;
	}

	//「詳細表示」のSQLを作成するメソッド
	public String createSQL2(){
		index1 = 0;
		index2 = 0;
		sumQuantity = 0;
		sumPrice = 0;
		SUM = 0;
		if(codeComboBox.getSelectedItem() == null && nameComboBox.getSelectedItem() == null) {
			filterSQL += " AND 商品コード = 'AAAA'"; //存在しない商品コードで何も取得しない
		}
		if(codeComboBox.getSelectedItem() != null) {
			filterSQL += " AND 商品コード = '" + codeComboBox.getSelectedItem() + "'";
		}
		String str = "SELECT 仕入日 AS 日付, 商品コード, CONCAT(仕入数,'受') AS 個数"
				+ " FROM 在庫マスタ WHERE 1" + filterSQL
				+ " UNION ALL"
				+ " SELECT DATE_FORMAT(販売日時, '%Y-%m-%d') AS 日付, 商品コード, CONCAT(個数,'払') AS 個数"
				+ " FROM 売上マスタ WHERE 1" + filterSQL + " AND 削除フラグ = 0"
				+ " ORDER BY 日付;";
		filterSQL = ""; //filterSQLをリセットする
		return str;
	}
	
	
	//それぞれのラベルに検索結果を表示するメソッド
	public void result() {
		try {
			if(rs.next()){
				show(codeLabel1, nameLabel1, flagLabel1, inQuantityLabel1, inPriceLabel1, outQuantityLabel1, 
						outPriceLabel1,balanceQuantityLabel1, balancePriceLabel1);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel1, nameLabel1, flagLabel1, inQuantityLabel1, inPriceLabel1, outQuantityLabel1, outPriceLabel1, 
						balanceQuantityLabel1, balancePriceLabel1 );
			}
			if(rs.next()){
				show(codeLabel2, nameLabel2, flagLabel2, inQuantityLabel2, inPriceLabel2, outQuantityLabel2, outPriceLabel2,
						balanceQuantityLabel2, balancePriceLabel2);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel2, nameLabel2, flagLabel2, inQuantityLabel2, inPriceLabel2, outQuantityLabel2, outPriceLabel2,
						balanceQuantityLabel2, balancePriceLabel2);
			}
			if(rs.next()){
				show(codeLabel3, nameLabel3, flagLabel3, inQuantityLabel3, inPriceLabel3, outQuantityLabel3, outPriceLabel3,
						balanceQuantityLabel3, balancePriceLabel3);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel3, nameLabel3, flagLabel3, inQuantityLabel3, inPriceLabel3, outQuantityLabel3, outPriceLabel3,
						balanceQuantityLabel3, balancePriceLabel3);
			}
			if(rs.next()){
				show(codeLabel4, nameLabel4, flagLabel4, inQuantityLabel4, inPriceLabel4, outQuantityLabel4, outPriceLabel4,
						balanceQuantityLabel4, balancePriceLabel4);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel4, nameLabel4, flagLabel4, inQuantityLabel4, inPriceLabel4, outQuantityLabel4, outPriceLabel4,
						balanceQuantityLabel4, balancePriceLabel4);
			}
			if(rs.next()){
				show(codeLabel5, nameLabel5, flagLabel5, inQuantityLabel5, inPriceLabel5, outQuantityLabel5, outPriceLabel5,
						balanceQuantityLabel5, balancePriceLabel5);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel5, nameLabel5, flagLabel5, inQuantityLabel5, inPriceLabel5, outQuantityLabel5, outPriceLabel5,
						balanceQuantityLabel5, balancePriceLabel5);
			}
			if(rs.next()){
				show(codeLabel6, nameLabel6, flagLabel6, inQuantityLabel6, inPriceLabel6, outQuantityLabel6, outPriceLabel6,
						balanceQuantityLabel6, balancePriceLabel6);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel6, nameLabel6, flagLabel6, inQuantityLabel6, inPriceLabel6, outQuantityLabel6, outPriceLabel6,
						balanceQuantityLabel6, balancePriceLabel6);
			}
			if(rs.next()){
				show(codeLabel7, nameLabel7, flagLabel7, inQuantityLabel7, inPriceLabel7, outQuantityLabel7, outPriceLabel7,
						balanceQuantityLabel7, balancePriceLabel7);	
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel7, nameLabel7, flagLabel7, inQuantityLabel7, inPriceLabel7, outQuantityLabel7, outPriceLabel7,
						balanceQuantityLabel7, balancePriceLabel7);
			}
			if(rs.next()){
				show(codeLabel8, nameLabel8, flagLabel8, inQuantityLabel8, inPriceLabel8, outQuantityLabel8, outPriceLabel8,
						balanceQuantityLabel8, balancePriceLabel8);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel8, nameLabel8, flagLabel8, inQuantityLabel8, inPriceLabel8, outQuantityLabel8, outPriceLabel8,
						balanceQuantityLabel8, balancePriceLabel8);
			}
			if(rs.next()){
				show(codeLabel9, nameLabel9, flagLabel9, inQuantityLabel9, inPriceLabel9, outQuantityLabel9, outPriceLabel9,
						balanceQuantityLabel9, balancePriceLabel9);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel9, nameLabel9, flagLabel9, inQuantityLabel9, inPriceLabel9, outQuantityLabel9, outPriceLabel9,
						balanceQuantityLabel9, balancePriceLabel9);
			}
			if(rs.next()){
				show(codeLabel10, nameLabel10, flagLabel10, inQuantityLabel10, inPriceLabel10, outQuantityLabel10, outPriceLabel10,
						balanceQuantityLabel10, balancePriceLabel10);
				now = rs.getRow(); //現在の行番号を取得
				nextButton.setEnabled(true); //一番下の行にデータがあれば「次へ」ボタンをtrueにする
			}else {
				reset(codeLabel10, nameLabel10, flagLabel10, inQuantityLabel10, inPriceLabel10, outQuantityLabel10, outPriceLabel10,
						balanceQuantityLabel10, balancePriceLabel10);
				nextButton.setEnabled(false); //一番下の行が白紙なら「次へ」ボタンをfalseにする
			}
			//現在行が最後の行のとき、「次へ」ボタンをfalseにする
			if(now == last) {
				nextButton.setEnabled(false);
			}
			//11件目以降を表示している場合は「前へ」ボタンをtrueにする
			if(now > 10) {
				previousButton.setEnabled(true);
			}
			//初めの10件目までを表示している場合は「前へ」ボタンをfalseにする
			else { 
				previousButton.setEnabled(false);
			}
			showNumberLabel.setText(Integer.toString(now));
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullでなければラベルに内容を表示するメソッド
	public void show(JLabel codeLabel, JLabel nameLabel, JLabel flagLabel, JLabel inQuantityLabel, JLabel inPriceLabel, 
			JLabel outQuantityLabel, JLabel outPriceLabel, JLabel balanceQuantityLabel, JLabel balancePriceLabel) {
		try {
			//全体表示の場合
			if(searchButton.getText().equals("絞り込み")) {
			
				//まず、商品コードと商品名、削除フラグを表示する
				codeLabel.setText(rs.getString("商品コード"));
				nameLabel.setText(rs.getString("商品名"));
				flagLabel.setText(rs.getString("削除フラグ"));
				//次に、表示した商品コードをもとに受入数量・金額を取得する
				//受入がない場合は0を記す
				SQL = "SELECT 商品コード, SUM(受入数量) AS 受入数量, SUM(受入金額) AS 受入金額"
					+ " FROM (SELECT 商品コード, 仕入単価, SUM(仕入数) AS 受入数量, SUM(仕入数)*仕入単価 AS 受入金額"
					+ " FROM 在庫マスタ GROUP BY 商品コード, 仕入単価) Z"
					+ " WHERE 商品コード = '" + codeLabel.getText() + "' GROUP BY 商品コード;";
				otherStmt = LoginGUI.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				otherRs = otherStmt.executeQuery(SQL);
				if(otherRs.next()) {
					inQuantityLabel.setText(otherRs.getString("受入数量") + "個");
					inPriceLabel.setText("￥" + otherRs.getString("受入金額"));
				} else {
					inQuantityLabel.setText("0個");
					inPriceLabel.setText("￥0");
				}
				//続いて払出数量・金額を取得する
				//まず日別の仕入数と仕入単価を配列に格納する
				SQL = "SELECT * FROM 在庫マスタ WHERE 商品コード = '" + codeLabel.getText() + "' ORDER BY 仕入日 ASC;";
				otherStmt = LoginGUI.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				otherRs = otherStmt.executeQuery(SQL);
				ArrayList<Integer> inQuantity = new ArrayList<Integer>();
				ArrayList<Integer> inPrice = new ArrayList<Integer>();
				//仕入がある場合
				if(otherRs.next()) {
					otherRs.beforeFirst();
					while(otherRs.next()) {
						//配列に仕入数と仕入単価を格納
						inQuantity.add(otherRs.getInt("仕入数"));
						inPrice.add(otherRs.getInt("仕入単価"));
					}
					for(int i = 0; i < inQuantity.size(); i++) {
						System.out.println("商品名:" + nameLabel.getText() + ", 仕入数:" + inQuantity.get(i) + 
								", 仕入単価:" + inPrice.get(i));
					}
					//払出数量が日別の仕入数を超えるたびに仕入単価を更新して払出金額を計算する
					SQL = "SELECT * FROM 売上マスタ"
							+ " WHERE 商品コード = '" + codeLabel.getText() + "' AND 削除フラグ = 0"
							+ " ORDER BY 販売日時 ASC, 伝票番号 ASC;";
					otherRs = otherStmt.executeQuery(SQL);
					int outQuantity = 0; //払出数量の合計（最終的な計算結果を格納する際に使用）
					int sum = 0; //払出数量の合計（計算途中の数値を格納する際に使用）
					int outPrice = 0; //払出金額の合計
					int i = 0;
					while(otherRs.next()) {
						sum += otherRs.getInt("個数");
						//払出数量の合計が日別の仕入数を超えるたびに仕入単価を次の仕入のものに更新
						if(sum > inQuantity.get(i)) {
							outQuantity += inQuantity.get(i);
							outPrice += inQuantity.get(i) * inPrice.get(i);
							sum -= inQuantity.get(i);
							i++;
						}
					}
					outQuantity += sum;
					outPrice += sum * inPrice.get(i);
					outQuantityLabel.setText(Integer.toString(outQuantity) + "個");
					outPriceLabel.setText("￥" + Integer.toString(outPrice));
					//配列をクリア
					inQuantity.clear();
					inPrice.clear();	
				}
				//仕入がない場合
				else {
					outQuantityLabel.setText("0個");
					outPriceLabel.setText("￥0");
				}
				
				//最後に、受入数量・金額から払出数量・金額を差し引いて残高を計算する
				int q = Integer.parseInt(inQuantityLabel.getText().replace("個", "")) - 
							Integer.parseInt(outQuantityLabel.getText().replace("個", ""));
				
				int p = Integer.parseInt(inPriceLabel.getText().replace("￥", "")) - 
							Integer.parseInt(outPriceLabel.getText().replace("￥", ""));
				
				balanceQuantityLabel.setText(Integer.toString(q) + "個");
				balancePriceLabel.setText("￥" + Integer.toString(p));
			}
			//「詳細表示」の場合
			else {
				//まず、取得した商品コードと日付（仕入または払出のあった日）を表示する
				codeLabel.setText(rs.getString("商品コード"));
				flagLabel.setText(rs.getString("日付"));
				//次に日別の仕入数と仕入単価を配列に格納する
				SQL = "SELECT * FROM 在庫マスタ WHERE 商品コード = '" + codeLabel.getText() + "' ORDER BY 仕入日 ASC;";
				otherStmt = LoginGUI.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				otherRs = otherStmt.executeQuery(SQL);
				ArrayList<Integer> inQuantity = new ArrayList<Integer>();
				ArrayList<Integer> inPrice = new ArrayList<Integer>();
				//仕入がある場合
				if(otherRs.next()) {
					otherRs.beforeFirst();
					while(otherRs.next()) {
						//配列に仕入数と仕入単価を格納
						inQuantity.add(otherRs.getInt("仕入数"));
						inPrice.add(otherRs.getInt("仕入単価"));
					}
					for(int i = 0; i < inQuantity.size(); i++) {
						System.out.println("商品名:" + nameLabel.getText() + ", 仕入数:" + inQuantity.get(i) + 
								", 仕入単価:" + inPrice.get(i));
					}
					
					//取得した「個数」が受入の場合
					if(rs.getString("個数").contains("受")) {
						inQuantityLabel.setText(rs.getString("個数").replace("受", "個"));
						inPriceLabel.setText(Integer.toString(inQuantity.get(index1) * inPrice.get(index1)));
						sumQuantity += inQuantity.get(index1); //数量の残高
						sumPrice += inQuantity.get(index1) * inPrice.get(index1); //金額の残高
						index1++; //次の仕入に移す
						balanceQuantityLabel.setText(sumQuantity + "個");
						balancePriceLabel.setText("￥" + sumPrice);
						
					}
					//取得した「個数」が払出の場合
					else {
						outQuantityLabel.setText(rs.getString("個数").replace("払", "個"));
						SUM += Integer.parseInt(rs.getString("個数").replace("払", ""));
						
						if(SUM > inQuantity.get(index2)) {
							//(index2 + 1)回目の仕入の値段で計算
							int price1 = (Integer.parseInt(rs.getString("個数").replace("払", "")) - 
									(SUM - inQuantity.get(index2)) * inPrice.get(index2));
							SUM -= inQuantity.get(index2);
							index2++;//次の仕入に移す
							//(index2 + 2)回目の仕入の値段で計算
							int price2 = SUM * inPrice.get(index2);
							outPriceLabel.setText(Integer.toString(price1 + price2));
						}else {
							outPriceLabel.setText(Integer.toString(Integer.parseInt(rs.getString("個数").replace("払", ""))
									* inPrice.get(index2)));
						}
						sumQuantity -= Integer.parseInt(outQuantityLabel.getText().replace("個", "")); //数量の残高
						sumPrice -= Integer.parseInt(outPriceLabel.getText().replace("￥", "")); //金額の残高
						balanceQuantityLabel.setText(sumQuantity + "個");
						balancePriceLabel.setText("￥" + sumPrice);
					}
					//配列をクリア
					inQuantity.clear();
					inPrice.clear();	
				}
				//仕入がない場合
				else {
					outQuantityLabel.setText("0個");
					outPriceLabel.setText("￥0");
				}
			}
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullならばラベルを白紙にするメソッド
	public void reset(JLabel codeLabel, JLabel nameLabel, JLabel flagLabel, JLabel inQuantityLabel, JLabel inPriceLabel, 
			JLabel outQuantityLabel, JLabel outPriceLabel, JLabel balanceQuantityLabel, JLabel balancePriceLabel) {
		codeLabel.setText(null);
		nameLabel.setText(null);
		flagLabel.setText(null);
		inQuantityLabel.setText(null);
		inPriceLabel.setText(null);
		outQuantityLabel.setText(null);
		outPriceLabel.setText(null);
		balanceQuantityLabel.setText(null);
		balancePriceLabel.setText(null);
	}
}
