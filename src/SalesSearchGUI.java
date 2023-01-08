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
import javax.swing.JTextField;

public class SalesSearchGUI extends JFrame implements ActionListener{
	
	JLabel fLabel = new JLabel("絞り込み条件");//絞り込み条件の入力欄であることを示すラベル
	
	//絞り込み条件の入力欄
	JLabel fnLabel = new JLabel("伝票番号"); //伝票番号の入力欄であることを示すラベル
	JTextField numberTextField = new JTextField("", 6); //伝票番号の入力欄 
	
	JLabel ftLabel = new JLabel("販売日時"); //販売日時の入力欄であることを示すラベル
	JComboBox yearComboBox = new JComboBox();
	JLabel fyLabel = new JLabel("年 "); //単位「年」を表示するラベル
	JComboBox monthComboBox = new JComboBox();
	JLabel fmLabel = new JLabel("月 "); //単位「月」を表示するラベル
	JComboBox dateComboBox = new JComboBox();
	JLabel fdLabel = new JLabel("日 "); //単位「日」を表示するラベル
	JComboBox hourComboBox = new JComboBox();
	JLabel fhLabel = new JLabel("時 "); //単位「時」を表示するラベル
	JLabel fmiLabel = new JLabel("分 "); //単位「分」を表示するラベル
	JComboBox minuteComboBox = new JComboBox();
	JComboBox timeRangeComboBox = new JComboBox(); //選択した日時より以前か等を選択するボックス
	
	JLabel fccLabel = new JLabel("店員コード"); //店員コードの選択欄であることを示すラベル
	JComboBox clerkCodeComboBox = new JComboBox(); //店員コードの選択欄
	JLabel fcnLabel = new JLabel("  店員名"); //店員コードの選択欄であることを示すラベル
	JComboBox clerkNameComboBox = new JComboBox(); //店員コードの選択欄
	
	JLabel fgcLabel = new JLabel("  商品コード"); //商品コードの選択欄であることを示すラベル
	JComboBox goodsCodeComboBox = new JComboBox(); //商品コードの選択欄 
	JLabel fgnLabel = new JLabel("  商品名"); //商品名の選択欄であることを示すラベル
	JComboBox goodsNameComboBox = new JComboBox(); //商品名の選択欄
	
	JLabel ftoLabel = new JLabel("  合計額"); //合計額の入力欄であることを示すラベル
	JTextField totalTextField = new JTextField("0", 6); //合計額の入力欄
	JComboBox totalRangeComboBox = new JComboBox(); //入力した合計額以上か等を選択するボックス
	
	
	JButton searchButton = new JButton("検索"); //検索ボタン
	
	JLabel nLabel = new JLabel("伝票番号", JLabel.CENTER); //伝票番号であることを示すラベル
	JLabel numberLabel1 = new JLabel("", JLabel.CENTER); //伝票番号を表示するラベル
	JLabel numberLabel2 = new JLabel("", JLabel.CENTER);
	JLabel numberLabel3 = new JLabel("", JLabel.CENTER);
	JLabel numberLabel4 = new JLabel("", JLabel.CENTER);
	JLabel numberLabel5 = new JLabel("", JLabel.CENTER);
	JLabel numberLabel6 = new JLabel("", JLabel.CENTER);
	JLabel numberLabel7 = new JLabel("", JLabel.CENTER);
	JLabel numberLabel8 = new JLabel("", JLabel.CENTER);
	JLabel numberLabel9 = new JLabel("", JLabel.CENTER);
	JLabel numberLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel dLabel = new JLabel("販売日時", JLabel.CENTER); //日時であることを示すラベル
	JLabel dateLabel1 = new JLabel("", JLabel.CENTER); //日時を表示するラベル
	JLabel dateLabel2 = new JLabel("", JLabel.CENTER);
	JLabel dateLabel3 = new JLabel("", JLabel.CENTER);
	JLabel dateLabel4 = new JLabel("", JLabel.CENTER);
	JLabel dateLabel5 = new JLabel("", JLabel.CENTER);
	JLabel dateLabel6 = new JLabel("", JLabel.CENTER);
	JLabel dateLabel7 = new JLabel("", JLabel.CENTER);
	JLabel dateLabel8 = new JLabel("", JLabel.CENTER);
	JLabel dateLabel9 = new JLabel("", JLabel.CENTER);
	JLabel dateLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel ccLabel = new JLabel("店員コード", JLabel.CENTER); //店員コードであることを示すラベル
	JLabel clerkCodeLabel1 = new JLabel("", JLabel.CENTER); //店員コードを表示するラベル
	JLabel clerkCodeLabel2 = new JLabel("", JLabel.CENTER);
	JLabel clerkCodeLabel3 = new JLabel("", JLabel.CENTER);
	JLabel clerkCodeLabel4 = new JLabel("", JLabel.CENTER);
	JLabel clerkCodeLabel5 = new JLabel("", JLabel.CENTER);
	JLabel clerkCodeLabel6 = new JLabel("", JLabel.CENTER);
	JLabel clerkCodeLabel7 = new JLabel("", JLabel.CENTER);
	JLabel clerkCodeLabel8 = new JLabel("", JLabel.CENTER);
	JLabel clerkCodeLabel9 = new JLabel("", JLabel.CENTER);
	JLabel clerkCodeLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel cnLabel = new JLabel("店員名", JLabel.CENTER); //店員名であることを示すラベル
	JLabel clerkNameLabel1 = new JLabel("", JLabel.CENTER); //店員名を表示するラベル
	JLabel clerkNameLabel2 = new JLabel("", JLabel.CENTER);
	JLabel clerkNameLabel3 = new JLabel("", JLabel.CENTER);
	JLabel clerkNameLabel4 = new JLabel("", JLabel.CENTER);
	JLabel clerkNameLabel5 = new JLabel("", JLabel.CENTER);
	JLabel clerkNameLabel6 = new JLabel("", JLabel.CENTER);
	JLabel clerkNameLabel7 = new JLabel("", JLabel.CENTER);
	JLabel clerkNameLabel8 = new JLabel("", JLabel.CENTER);
	JLabel clerkNameLabel9 = new JLabel("", JLabel.CENTER);
	JLabel clerkNameLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel gcLabel = new JLabel("商品コード", JLabel.CENTER); //商品コードであることを示すラベル
	JLabel goodsCodeLabel1 = new JLabel("", JLabel.CENTER); //商品コードを表示するラベル
	JLabel goodsCodeLabel2 = new JLabel("", JLabel.CENTER);
	JLabel goodsCodeLabel3 = new JLabel("", JLabel.CENTER);
	JLabel goodsCodeLabel4 = new JLabel("", JLabel.CENTER);
	JLabel goodsCodeLabel5 = new JLabel("", JLabel.CENTER);
	JLabel goodsCodeLabel6 = new JLabel("", JLabel.CENTER);
	JLabel goodsCodeLabel7 = new JLabel("", JLabel.CENTER);
	JLabel goodsCodeLabel8 = new JLabel("", JLabel.CENTER);
	JLabel goodsCodeLabel9 = new JLabel("", JLabel.CENTER);
	JLabel goodsCodeLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel gnLabel = new JLabel("商品名", JLabel.CENTER); //商品名であることを示すラベル
	JLabel goodsNameLabel1 = new JLabel("", JLabel.CENTER); //商品名を表示するラベル
	JLabel goodsNameLabel2 = new JLabel("", JLabel.CENTER);
	JLabel goodsNameLabel3 = new JLabel("", JLabel.CENTER);
	JLabel goodsNameLabel4 = new JLabel("", JLabel.CENTER);
	JLabel goodsNameLabel5 = new JLabel("", JLabel.CENTER);
	JLabel goodsNameLabel6 = new JLabel("", JLabel.CENTER);
	JLabel goodsNameLabel7 = new JLabel("", JLabel.CENTER);
	JLabel goodsNameLabel8 = new JLabel("", JLabel.CENTER);
	JLabel goodsNameLabel9 = new JLabel("", JLabel.CENTER);
	JLabel goodsNameLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel ctLabel = new JLabel("個数", JLabel.CENTER); //商品の個数であることを示すラベル
	JLabel countLabel1 = new JLabel("", JLabel.CENTER); //商品の個数を表示するラベル
	JLabel countLabel2 = new JLabel("", JLabel.CENTER);
	JLabel countLabel3 = new JLabel("", JLabel.CENTER);
	JLabel countLabel4 = new JLabel("", JLabel.CENTER);
	JLabel countLabel5 = new JLabel("", JLabel.CENTER);
	JLabel countLabel6 = new JLabel("", JLabel.CENTER);
	JLabel countLabel7 = new JLabel("", JLabel.CENTER);
	JLabel countLabel8 = new JLabel("", JLabel.CENTER);
	JLabel countLabel9 = new JLabel("", JLabel.CENTER);
	JLabel countLabel10 = new JLabel("", JLabel.CENTER);
	
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
	JLabel snLabel = new JLabel("件目まで表示 /");
	
	JButton nextButton = new JButton("次へ"); //ページをめくるボタン
	JButton previousButton = new JButton("前へ"); 
	
	int now, last; //ページをめくる際に使用
	
	String URL = "jdbc:mysql://127.0.0.1:3306/販売管理"; //SQLで使用
	String USER = "店員1";
	String PASS = "password";
	String SQL;
	String filterSQL = "";
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel2_1 = new JPanel();
	JPanel panel2_2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel15 = new JPanel();
	
	SalesSearchGUI(){
		setTitle("売上検索システム");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//レイアウト設定
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2_1.setLayout(new FlowLayout());
		panel2_2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new FlowLayout());
		panel5.setLayout(new GridLayout(11, 8, 0, 2));
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
		timeRangeComboBox.addItem("以前");
		timeRangeComboBox.addItem("以後");
		timeRangeComboBox.addItem("一致");
		
		//店員コードを選択するコンボボックスに項目を追加
		clerkCodeComboBox.addItem(null);
		try {
			SQL = "SELECT 店員コード FROM 店員マスタ;";
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
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
		clerkNameComboBox.addItem(null);
		try {
			SQL = "SELECT 氏名 FROM 店員マスタ;";
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
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
		goodsCodeComboBox.addItem(null);
		try {
			SQL = "SELECT 商品コード FROM 商品マスタ;";
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
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
		goodsNameComboBox.addItem(null);
		try {
			SQL = "SELECT 商品名 FROM 商品マスタ;";
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				goodsNameComboBox.addItem(rs.getString("商品名"));

			}
			goodsNameComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		totalRangeComboBox.addItem("以上");
		totalRangeComboBox.addItem("以下");
		totalRangeComboBox.addItem("一致");
		
		//ラベルの色分け設定
		setColor1(nLabel);
		setColor1(dLabel);
		setColor1(ccLabel);
		setColor1(cnLabel);
		setColor1(gcLabel);
		setColor1(gnLabel);
		setColor1(ctLabel);
		setColor1(stLabel);
		
		setColor2(numberLabel2);
		setColor2(dateLabel2);
		setColor2(clerkCodeLabel2);
		setColor2(clerkNameLabel2);
		setColor2(goodsCodeLabel2);
		setColor2(goodsNameLabel2);
		setColor2(countLabel2);
		setColor2(subTotalLabel2);

		setColor2(numberLabel4);
		setColor2(dateLabel4);
		setColor2(clerkCodeLabel4);
		setColor2(clerkNameLabel4);
		setColor2(goodsCodeLabel4);
		setColor2(goodsNameLabel4);
		setColor2(countLabel4);
		setColor2(subTotalLabel4);
		
		setColor2(numberLabel6);
		setColor2(dateLabel6);
		setColor2(clerkCodeLabel6);
		setColor2(clerkNameLabel6);
		setColor2(goodsCodeLabel6);
		setColor2(goodsNameLabel6);
		setColor2(countLabel6);
		setColor2(subTotalLabel6);
		
		setColor2(numberLabel8);
		setColor2(dateLabel8);
		setColor2(clerkCodeLabel8);
		setColor2(clerkNameLabel8);
		setColor2(goodsCodeLabel8);
		setColor2(goodsNameLabel8);
		setColor2(countLabel8);
		setColor2(subTotalLabel8);
		
		setColor2(numberLabel10);
		setColor2(dateLabel10);
		setColor2(clerkCodeLabel10);
		setColor2(clerkNameLabel10);
		setColor2(goodsCodeLabel10);
		setColor2(goodsNameLabel10);
		setColor2(countLabel10);
		setColor2(subTotalLabel10);
		
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
		panel2_1.add(fhLabel);
		panel2_1.add(minuteComboBox);
		panel2_1.add(fmiLabel);
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
		
		panel5.add(nLabel);
		panel5.add(dLabel);
		panel5.add(ccLabel);
		panel5.add(cnLabel);
		panel5.add(gcLabel);
		panel5.add(gnLabel);
		panel5.add(ctLabel);
		panel5.add(stLabel);
		
		panel5.add(numberLabel1);
		panel5.add(dateLabel1);
		panel5.add(clerkCodeLabel1);
		panel5.add(clerkNameLabel1);
		panel5.add(goodsCodeLabel1);
		panel5.add(goodsNameLabel1);
		panel5.add(countLabel1);
		panel5.add(subTotalLabel1);
		
		panel5.add(numberLabel2);
		panel5.add(dateLabel2);
		panel5.add(clerkCodeLabel2);
		panel5.add(clerkNameLabel2);
		panel5.add(goodsCodeLabel2);
		panel5.add(goodsNameLabel2);
		panel5.add(countLabel2);
		panel5.add(subTotalLabel2);
		
		panel5.add(numberLabel3);
		panel5.add(dateLabel3);
		panel5.add(clerkCodeLabel3);
		panel5.add(clerkNameLabel3);
		panel5.add(goodsCodeLabel3);
		panel5.add(goodsNameLabel3);
		panel5.add(countLabel3);
		panel5.add(subTotalLabel3);
		

		panel5.add(numberLabel4);
		panel5.add(dateLabel4);
		panel5.add(clerkCodeLabel4);
		panel5.add(clerkNameLabel4);
		panel5.add(goodsCodeLabel4);
		panel5.add(goodsNameLabel4);
		panel5.add(countLabel4);
		panel5.add(subTotalLabel4);
		
		panel5.add(numberLabel5);
		panel5.add(dateLabel5);
		panel5.add(clerkCodeLabel5);
		panel5.add(clerkNameLabel5);
		panel5.add(goodsCodeLabel5);
		panel5.add(goodsNameLabel5);
		panel5.add(countLabel5);
		panel5.add(subTotalLabel5);
		
		panel5.add(numberLabel6);
		panel5.add(dateLabel6);
		panel5.add(clerkCodeLabel6);
		panel5.add(clerkNameLabel6);
		panel5.add(goodsCodeLabel6);
		panel5.add(goodsNameLabel6);
		panel5.add(countLabel6);
		panel5.add(subTotalLabel6);
		
		panel5.add(numberLabel7);
		panel5.add(dateLabel7);
		panel5.add(clerkCodeLabel7);
		panel5.add(clerkNameLabel7);
		panel5.add(goodsCodeLabel7);
		panel5.add(goodsNameLabel7);
		panel5.add(countLabel7);
		panel5.add(subTotalLabel7);
		
		panel5.add(numberLabel8);
		panel5.add(dateLabel8);
		panel5.add(clerkCodeLabel8);
		panel5.add(clerkNameLabel8);
		panel5.add(goodsCodeLabel8);
		panel5.add(goodsNameLabel8);
		panel5.add(countLabel8);
		panel5.add(subTotalLabel8);
		
		panel5.add(numberLabel9);
		panel5.add(dateLabel9);
		panel5.add(clerkCodeLabel9);
		panel5.add(clerkNameLabel9);
		panel5.add(goodsCodeLabel9);
		panel5.add(goodsNameLabel9);
		panel5.add(countLabel9);
		panel5.add(subTotalLabel9);
		
		panel5.add(numberLabel10);
		panel5.add(dateLabel10);
		panel5.add(clerkCodeLabel10);
		panel5.add(clerkNameLabel10);
		panel5.add(goodsCodeLabel10);
		panel5.add(goodsNameLabel10);
		panel5.add(countLabel10);
		panel5.add(subTotalLabel10);
		
		panel15.add(previousButton);
		panel15.add(showNumberLabel);
		panel15.add(snLabel);
		panel15.add(totalNumberLabel);
		panel15.add(tnLabel);
		panel15.add(nextButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		getContentPane().add(panel4);
		getContentPane().add(panel5);
		getContentPane().add(panel15);
		
		searchButton.addActionListener(this);
		nextButton.addActionListener(this);
		previousButton.addActionListener(this);
		nextButton.setEnabled(false);
		previousButton.setEnabled(false);
		this.pack();
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchButton) {
			SQL = createSQL();
			System.out.println(SQL + " で検索します");
			try {
				conn = DriverManager.getConnection(URL, USER, PASS);
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery(SQL);
				result(); //検索結果を表示
				rs.last(); //最後の行に移動し、行番号を取得
				last = rs.getRow();
				totalNumberLabel.setText(Integer.toString(last));
				showNumberLabel.setText(Integer.toString(now));
				this.pack(); //フレームのサイズ調整
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
		} else if(e.getSource() == nextButton) {
			try {
				rs.absolute(now);
				result(); //検索結果を表示
				showNumberLabel.setText(Integer.toString(now));
				previousButton.setEnabled(true);
				if(now == last) { //最後の行を表示している場合は「次へ」ボタンをfalseにする
					nextButton.setEnabled(false);
				}
			} catch (SQLException e3) {
				e3.printStackTrace();
			} catch(Exception e3) {
				e3.printStackTrace();
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
	
	//検索条件を指定するSQLを作成するメソッド
	public String createSQL(){
		if(!numberTextField.getText().equals("")) {
			System.out.println("nullでない");
			filterSQL += " AND 伝票番号 = " + numberTextField.getText(); 
		}
		if(timeRangeComboBox.getSelectedItem().equals("以前")) {
			filterSQL += " AND 販売日時 <= '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + 
					"-" + dateComboBox.getSelectedItem() + " " + hourComboBox.getSelectedItem() + ":" + 
					minuteComboBox.getSelectedItem() + "'";
		}else if(timeRangeComboBox.getSelectedItem().equals("以後")) {
			filterSQL += " AND 販売日時 >= '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + 
					"-" + dateComboBox.getSelectedItem() + " " + hourComboBox.getSelectedItem() + ":" + 
					minuteComboBox.getSelectedItem() + "'";
		}else if(timeRangeComboBox.getSelectedItem().equals("一致")) {
			filterSQL += " AND 販売日時 = '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + 
					"-" + dateComboBox.getSelectedItem() + " " + hourComboBox.getSelectedItem() + ":" + 
					minuteComboBox.getSelectedItem() + "'";
		}
		if(clerkCodeComboBox.getSelectedItem() != null) {
			filterSQL += " AND U.店員コード = '" + clerkCodeComboBox.getSelectedItem() + "'"; 
		}
		if(clerkNameComboBox.getSelectedItem() != null) {
			filterSQL += " AND 店員名 = '" + clerkNameComboBox.getSelectedItem() + "'"; 
		}
		if(goodsCodeComboBox.getSelectedItem() != null) {
			filterSQL += " AND U.商品コード = '" + goodsCodeComboBox.getSelectedItem() + "'";
		}
		if(goodsNameComboBox.getSelectedItem() != null) {
			filterSQL += " AND 商品名 = '" + goodsNameComboBox.getSelectedItem() + "'";
		}
		if(!totalTextField.getText().equals("")) {
			if(totalRangeComboBox.getSelectedItem().equals("以上")) {
				filterSQL += " AND 伝票番号 IN (SELECT 伝票番号 FROM 売上マスタ GROUP BY 伝票番号 HAVING sum(小計) >= "
						+ totalTextField.getText() + ")";
			} else if(totalRangeComboBox.getSelectedItem().equals("以下")) {
				filterSQL += " AND 伝票番号 IN (SELECT 伝票番号 FROM 売上マスタ GROUP BY 伝票番号 HAVING sum(小計) <= "
						+ totalTextField.getText() + ")";
			} else if(totalRangeComboBox.getSelectedItem().equals("一致")) {
				filterSQL += " AND 伝票番号 IN (SELECT 伝票番号 FROM 売上マスタ GROUP BY 伝票番号 HAVING sum(小計) = "
						+ totalTextField.getText() + ")";
			}
		}
		String str = "SELECT 伝票番号, 販売日時, U.店員コード, 氏名, U.商品コード, 商品名, 個数, 小計 "
				+ "FROM 売上マスタ U, 商品マスタ S, 店員マスタ T "
				+ "WHERE U.店員コード = T.店員コード AND U.商品コード = S.商品コード" + filterSQL + 
				" ORDER BY 伝票番号 ASC;";
		filterSQL = ""; //filterSQLをリセットする
		return str;
	}
	
	//それぞれのラベルに検索結果を表示するメソッド
	public void result() {
		try {
			if(rs.next()){
				show(numberLabel1, dateLabel1, clerkCodeLabel1, clerkNameLabel1, 
						goodsCodeLabel1, goodsNameLabel1, countLabel1, subTotalLabel1);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel1, dateLabel1, clerkCodeLabel1, clerkNameLabel1, 
						goodsCodeLabel1, goodsNameLabel1, countLabel1, subTotalLabel1);
			}
			if(rs.next()){
				show(numberLabel2, dateLabel2, clerkCodeLabel2, clerkNameLabel2, 
						goodsCodeLabel2, goodsNameLabel2, countLabel2, subTotalLabel2);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel2, dateLabel2, clerkCodeLabel2, clerkNameLabel2, 
						goodsCodeLabel2, goodsNameLabel2, countLabel2, subTotalLabel2);
			}
			if(rs.next()){
				show(numberLabel3, dateLabel3, clerkCodeLabel3, clerkNameLabel3, 
						goodsCodeLabel3, goodsNameLabel3, countLabel3, subTotalLabel3);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel3, dateLabel3, clerkCodeLabel3, clerkNameLabel3, 
						goodsCodeLabel3, goodsNameLabel3, countLabel3, subTotalLabel3);
			}
			if(rs.next()){
				show(numberLabel4, dateLabel4, clerkCodeLabel4, clerkNameLabel4, 
						goodsCodeLabel4, goodsNameLabel4, countLabel4, subTotalLabel4);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel4, dateLabel4, clerkCodeLabel4, clerkNameLabel4, 
						goodsCodeLabel4, goodsNameLabel4, countLabel4, subTotalLabel4);
			}
			if(rs.next()){
				show(numberLabel5, dateLabel5, clerkCodeLabel5, clerkNameLabel5, 
						goodsCodeLabel5, goodsNameLabel5, countLabel5, subTotalLabel5);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel5, dateLabel5, clerkCodeLabel5, clerkNameLabel5, 
						goodsCodeLabel5, goodsNameLabel5, countLabel5, subTotalLabel5);
			}
			if(rs.next()){
				show(numberLabel6, dateLabel6, clerkCodeLabel6, clerkNameLabel6, 
						goodsCodeLabel6, goodsNameLabel6, countLabel6, subTotalLabel6);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel6, dateLabel6, clerkCodeLabel6, clerkNameLabel6, 
						goodsCodeLabel6, goodsNameLabel6, countLabel6, subTotalLabel6);
			}
			if(rs.next()){
				show(numberLabel7, dateLabel7, clerkCodeLabel7, clerkNameLabel7, 
						goodsCodeLabel7, goodsNameLabel7, countLabel7, subTotalLabel7);	
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel7, dateLabel7, clerkCodeLabel7, clerkNameLabel7, 
						goodsCodeLabel7, goodsNameLabel7, countLabel7, subTotalLabel7);
			}
			if(rs.next()){
				show(numberLabel8, dateLabel8, clerkCodeLabel8, clerkNameLabel8, 
						goodsCodeLabel8, goodsNameLabel8, countLabel8, subTotalLabel8);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel8, dateLabel8, clerkCodeLabel8, clerkNameLabel8, 
						goodsCodeLabel8, goodsNameLabel8, countLabel8, subTotalLabel8);
			}
			if(rs.next()){
				show(numberLabel9, dateLabel9, clerkCodeLabel9, clerkNameLabel9, 
						goodsCodeLabel9, goodsNameLabel9, countLabel9, subTotalLabel9);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset( numberLabel9, dateLabel9, clerkCodeLabel9, clerkNameLabel9, 
						goodsCodeLabel9, goodsNameLabel9, countLabel9, subTotalLabel9);
			}
			if(rs.next()){
				show(numberLabel10, dateLabel10, clerkCodeLabel10, clerkNameLabel10, 
						goodsCodeLabel10, goodsNameLabel10, countLabel10, subTotalLabel10);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel10, dateLabel10, clerkCodeLabel10, clerkNameLabel10, 
						goodsCodeLabel10, goodsNameLabel10, countLabel10, subTotalLabel10);
			}
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullでなければラベルに内容を表示するメソッド
	public void show(JLabel numberLabel, JLabel dateLabel, JLabel clerkCodeLabel, JLabel clerkNameLabel, 
			JLabel goodsCodeLabel, JLabel goodsNameLabel, JLabel countLabel, JLabel subTotalLabel) {
		try {
			numberLabel.setText(rs.getString("伝票番号"));
			String str = rs.getString("販売日時") + "0";
			str = str.replace(" ", "   ");
			str = str.replace(":000", "");
			dateLabel.setText(str);
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
	
	//Resultsetオブジェクトがnullならばラベルを白紙にするメソッド
	public void reset(JLabel numberLabel, JLabel dateLabel, JLabel clerkCodeLabel, JLabel clerkNameLabel, 
			JLabel goodsCodeLabel, JLabel goodsNameLabel, JLabel countLabel, JLabel subTotalLabel) {
		numberLabel.setText("");
		dateLabel.setText(null);
		clerkCodeLabel.setText(null);
		clerkNameLabel.setText(null);
		goodsCodeLabel.setText(null);
		goodsNameLabel.setText(null);
		countLabel.setText(null);
		subTotalLabel.setText(null);
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
