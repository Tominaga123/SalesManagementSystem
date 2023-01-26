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

public class ClerkSearchGUI extends JFrame implements ActionListener{
	
	JLabel filterLabel = new JLabel("絞り込み条件");//絞り込み条件の入力欄であることを示すラベル
	
	//絞り込み条件の入力欄
	JLabel fcLabel = new JLabel("  店員コード"); //商品コードの選択欄であることを示すラベル
	JComboBox codeComboBox = new JComboBox(); //商品コードの選択欄 
	
	JLabel fnLabel = new JLabel("  店員名"); //商品名の選択欄であることを示すラベル
	JComboBox nameComboBox = new JComboBox(); //商品名の選択欄
	
	JLabel fsLabel = new JLabel("  性別"); //性別の選択欄であることを示すラベル
	JComboBox sexComboBox = new JComboBox(); //性別の選択欄
	
	JLabel fbLabel = new JLabel("  生年月日"); //生年月日の選択欄であることを示すラベル
	JLabel fyLabel = new JLabel("年 "); //単位「年」を表示するラベル
	JComboBox yearComboBox = new JComboBox(); //[年]の選択欄
	
	JLabel fmLabel = new JLabel("月 "); //単位「月」を表示するラベル
	JComboBox monthComboBox = new JComboBox(); //「月」の選択欄
	
	JLabel fdLabel = new JLabel("日 "); //単位「日」を表示するラベル
	JComboBox dateComboBox = new JComboBox(); //「日」の選択欄
	
	JComboBox timeRangeComboBox = new JComboBox(); //選択した日時より以前か等を選択するボックス
	
	JLabel ffLabel = new JLabel("  削除フラグ"); //削除フラグの選択欄であることを示すラベル
	JComboBox flagComboBox = new JComboBox(); //削除フラグの選択欄
	
	JButton searchButton = new JButton("絞り込み"); //検索ボタン
	JButton releaseButton = new JButton("絞り込み解除"); //絞り込み解除ボタン
	
	//検索結果の表示場所
	
	JLabel cLabel = new JLabel("店員コード", JLabel.CENTER); //店員コードであることを示すラベル
	JLabel codeLabel1 = new JLabel("", JLabel.CENTER); //店員コードを表示するラベル
	JLabel codeLabel2 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel3 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel4 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel5 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel6 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel7 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel8 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel9 = new JLabel("", JLabel.CENTER);
	JLabel codeLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel nLabel = new JLabel("店員名", JLabel.CENTER); //店員名であることを示すラベル
	JLabel nameLabel1 = new JLabel("", JLabel.CENTER); //店員名を表示するラベル
	JLabel nameLabel2 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel3 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel4 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel5 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel6 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel7 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel8 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel9 = new JLabel("", JLabel.CENTER);
	JLabel nameLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel sLabel = new JLabel("性別", JLabel.CENTER); //性別であることを示すラベル
	JLabel sexLabel1 = new JLabel("", JLabel.CENTER); //性別を表示するラベル
	JLabel sexLabel2 = new JLabel("", JLabel.CENTER);
	JLabel sexLabel3 = new JLabel("", JLabel.CENTER);
	JLabel sexLabel4 = new JLabel("", JLabel.CENTER);
	JLabel sexLabel5 = new JLabel("", JLabel.CENTER);
	JLabel sexLabel6 = new JLabel("", JLabel.CENTER);
	JLabel sexLabel7 = new JLabel("", JLabel.CENTER);
	JLabel sexLabel8 = new JLabel("", JLabel.CENTER);
	JLabel sexLabel9 = new JLabel("", JLabel.CENTER);
	JLabel sexLabel10 = new JLabel("", JLabel.CENTER);
	
	JLabel bLabel = new JLabel("生年月日", JLabel.CENTER); //生年月日であることを示すラベル
	JLabel birthdayLabel1 = new JLabel("", JLabel.CENTER); //生年月日を表示するラベル
	JLabel birthdayLabel2 = new JLabel("", JLabel.CENTER);
	JLabel birthdayLabel3 = new JLabel("", JLabel.CENTER);
	JLabel birthdayLabel4 = new JLabel("", JLabel.CENTER);
	JLabel birthdayLabel5 = new JLabel("", JLabel.CENTER);
	JLabel birthdayLabel6 = new JLabel("", JLabel.CENTER);
	JLabel birthdayLabel7 = new JLabel("", JLabel.CENTER);
	JLabel birthdayLabel8 = new JLabel("", JLabel.CENTER);
	JLabel birthdayLabel9 = new JLabel("", JLabel.CENTER);
	JLabel birthdayLabel10 = new JLabel("", JLabel.CENTER);
	
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
	String selectSQL = "SELECT *"; 
	Connection conn;
	Statement stmt;
	ResultSet rs;
	ResultSet otherRs;
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	
	ClerkSearchGUI(){
		setTitle("商品検索");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//レイアウト設定
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new GridLayout(11, 5, 0, 2));
		panel5.setLayout(new FlowLayout());
		
		//店員コード、店員名を選択するコンボボックスに項目を追加
		codeComboBox.addItem(null);
		nameComboBox.addItem(null);
		try {
			SQL = "SELECT 店員コード, 店員名 FROM 店員マスタ;";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				codeComboBox.addItem(rs.getString("店員コード"));
				nameComboBox.addItem(rs.getString("店員名"));
			}
			codeComboBox.setSelectedItem(null);
			nameComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}

		//日時を選択するコンボボックスに項目を追加
		//現在の日時で初期化
		//「年」
		int y = Integer.valueOf(getYear());
		for(int i = 1950; i <= y; i++) {
			String s = Integer.valueOf(i).toString();
			yearComboBox.addItem(s);
		}
		yearComboBox.setSelectedItem(getYear());
		//「月」
		for(int i = 1; i <= 12; i++) {
		String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		monthComboBox.addItem(s);
		}
		monthComboBox.setSelectedItem(getMonth());
		//「日」
		for(int i = 1; i <= 31; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			dateComboBox.addItem(s);
		}
		dateComboBox.setSelectedItem(getDate());
		timeRangeComboBox.addItem("以前");
		timeRangeComboBox.addItem("以後");
		timeRangeComboBox.addItem("一致");
		
		//削除フラグを選択するコンボボックスに項目を追加
		flagComboBox.addItem(null);
		flagComboBox.addItem("0");
		flagComboBox.addItem("1");
		flagComboBox.setSelectedItem(null);
		
		panel1.add(filterLabel);
		
		panel2.add(fcLabel);
		panel2.add(codeComboBox);
		panel2.add(fnLabel);
		panel2.add(nameComboBox);
		panel2.add(ffLabel);
		panel2.add(flagComboBox);
		panel2.add(fsLabel);
		panel2.add(sexComboBox);
		panel2.add(fbLabel);
		panel2.add(yearComboBox);
		panel2.add(fyLabel);
		panel2.add(monthComboBox);
		panel2.add(fmLabel);
		panel2.add(dateComboBox);
		panel2.add(fdLabel);
		panel2.add(timeRangeComboBox);
		panel2.add(ffLabel);
		panel2.add(flagComboBox);

		panel3.add(searchButton);
		panel3.add(releaseButton);
		
		panel4.add(cLabel);
		panel4.add(nLabel);
		panel4.add(sLabel);
		panel4.add(sLabel);
		panel4.add(fLabel);
		
		panel4.add(codeLabel1);
		panel4.add(nameLabel1);
		panel4.add(sexLabel1);
		panel4.add(birthdayLabel1);
		panel4.add(flagLabel1);
		panel4.add(codeLabel2);
		panel4.add(nameLabel2);
		panel4.add(sexLabel2);
		panel4.add(birthdayLabel2);
		panel4.add(flagLabel2);
		panel4.add(codeLabel3);
		panel4.add(nameLabel3);
		panel4.add(sexLabel3);
		panel4.add(birthdayLabel3);
		panel4.add(flagLabel3);
		panel4.add(codeLabel4);
		panel4.add(nameLabel4);
		panel4.add(sexLabel4);
		panel4.add(birthdayLabel4);
		panel4.add(flagLabel4);
		panel4.add(codeLabel5);
		panel4.add(nameLabel5);
		panel4.add(sexLabel5);
		panel4.add(birthdayLabel5);
		panel4.add(flagLabel5);
		panel4.add(codeLabel6);
		panel4.add(nameLabel6);
		panel4.add(sexLabel6);
		panel4.add(birthdayLabel6);
		panel4.add(flagLabel6);
		panel4.add(codeLabel7);
		panel4.add(nameLabel7);
		panel4.add(sexLabel7);
		panel4.add(birthdayLabel7);
		panel4.add(flagLabel7);
		panel4.add(codeLabel8);
		panel4.add(nameLabel8);
		panel4.add(sexLabel8);
		panel4.add(birthdayLabel8);
		panel4.add(flagLabel8);
		panel4.add(codeLabel9);
		panel4.add(nameLabel9);
		panel4.add(sexLabel9);
		panel4.add(birthdayLabel9);
		panel4.add(flagLabel9);
		panel4.add(codeLabel10);
		panel4.add(nameLabel10);
		panel4.add(sexLabel10);
		panel4.add(birthdayLabel10);
		panel4.add(flagLabel10);
		
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
			sexComboBox.setSelectedItem(null);
			yearComboBox.setSelectedItem(getYear());
			monthComboBox.setSelectedItem(getMonth());
			dateComboBox.setSelectedItem(getDate());
			timeRangeComboBox.setSelectedItem("以前");
			flagComboBox.setSelectedItem(null);
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
				SQL = "SELECT 店員名 FROM 店員マスタ WHERE 店員コード = '" + codeComboBox.getSelectedItem() + "';";
				Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stmt = conn.createStatement();
				otherRs = stmt.executeQuery(SQL);
				while(otherRs.next()){
					nameComboBox.setSelectedItem(otherRs.getString("店員名"));
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		} else if(e.getSource() == nameComboBox) {
			try {
				SQL = "SELECT 店員コード FROM 店員マスタ WHERE 店員名 = '" + nameComboBox.getSelectedItem() + "';";
				Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stmt = conn.createStatement();
				otherRs = stmt.executeQuery(SQL);
				while(otherRs.next()){
					codeComboBox.setSelectedItem(otherRs.getString("店員コード"));
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
		if(flagComboBox.getSelectedItem() != null) {
			filterSQL += " AND 削除フラグ = '" + flagComboBox.getSelectedItem() + "'";
		}
		String str = "SELECT * FROM 商品マスタ WHERE 1" + filterSQL + ";";
		filterSQL = ""; //filterSQLをリセットする
		return str;
	}

	
	
	//それぞれのラベルに検索結果を表示するメソッド
	public void result() {
		try {
			if(rs.next()){
				show(codeLabel1, nameLabel1, priceLabel1, flagLabel1);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel1, nameLabel1, priceLabel1, flagLabel1);
			}
			if(rs.next()){
				show(codeLabel2, nameLabel2, priceLabel2, flagLabel2);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel2, nameLabel2, priceLabel2, flagLabel2);
			}
			if(rs.next()){
				show(codeLabel3, nameLabel3, priceLabel3, flagLabel3);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel3, nameLabel3, priceLabel3, flagLabel3);
			}
			if(rs.next()){
				show(codeLabel4, nameLabel4, priceLabel4, flagLabel4);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel4, nameLabel4, priceLabel4, flagLabel4);
			}
			if(rs.next()){
				show(codeLabel5, nameLabel5, priceLabel5, flagLabel5);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel5, nameLabel5, priceLabel5, flagLabel5);
			}
			if(rs.next()){
				show(codeLabel6, nameLabel6, priceLabel6, flagLabel6);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel6, nameLabel6, priceLabel6, flagLabel6);
			}
			if(rs.next()){
				show(codeLabel7, nameLabel7, priceLabel7, flagLabel7);	
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel7, nameLabel7, priceLabel7, flagLabel7);
			}
			if(rs.next()){
				show(codeLabel8, nameLabel8, priceLabel8, flagLabel8);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel8, nameLabel8, priceLabel8, flagLabel8);
			}
			if(rs.next()){
				show(codeLabel9, nameLabel9, priceLabel9, flagLabel9);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel9, nameLabel9, priceLabel9, flagLabel9);
			}
			if(rs.next()){
				show(codeLabel10, nameLabel10, priceLabel10, flagLabel10);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel10, nameLabel10, priceLabel10, flagLabel10);
			}
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullでなければラベルに内容を表示するメソッド
	public void show(JLabel codeLabel, JLabel nameLabel, JLabel priceLabel, JLabel flagLabel) {
		try {
			codeLabel.setText(rs.getString("商品コード"));
			nameLabel.setText(rs.getString("商品名"));
			priceLabel.setText(rs.getString("単価"));
			flagLabel.setText(rs.getString("削除フラグ"));
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullならばラベルを白紙にするメソッド
	public void reset(JLabel codeLabel, JLabel nameLabel, JLabel priceLabel, JLabel flagLabel) {
		codeLabel.setText(null);
		nameLabel.setText(null);
		priceLabel.setText(null);
		flagLabel.setText(null);
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
