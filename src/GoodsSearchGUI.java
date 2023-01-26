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

public class GoodsSearchGUI extends JFrame implements ActionListener{
	
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
	
	JLabel pLabel = new JLabel("単価", JLabel.CENTER); //単価であることを示すラベル
	JLabel priceLabel1 = new JLabel("", JLabel.CENTER); //単価を表示するラベル
	JLabel priceLabel2 = new JLabel("", JLabel.CENTER);
	JLabel priceLabel3 = new JLabel("", JLabel.CENTER);
	JLabel priceLabel4 = new JLabel("", JLabel.CENTER);
	JLabel priceLabel5 = new JLabel("", JLabel.CENTER);
	JLabel priceLabel6 = new JLabel("", JLabel.CENTER);
	JLabel priceLabel7 = new JLabel("", JLabel.CENTER);
	JLabel priceLabel8 = new JLabel("", JLabel.CENTER);
	JLabel priceLabel9 = new JLabel("", JLabel.CENTER);
	JLabel priceLabel10 = new JLabel("", JLabel.CENTER);
	
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
	JPanel panel5 = new JPanel();
	
	GoodsSearchGUI(){
		setTitle("在庫検索");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//レイアウト設定
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new GridLayout(11, 4, 0, 2));
		panel5.setLayout(new FlowLayout());
		
		//商品コードを選択するコンボボックスに項目を追加
		codeComboBox.addItem(null);
		try {
			SQL = "SELECT CONCAT(LEFT(商品コード, 1), '群') AS 先頭コード FROM 商品マスタ GROUP BY 先頭コード;";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				codeComboBox.addItem(rs.getString("先頭コード"));
			} 
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
		panel3.add(searchButton);
		panel3.add(releaseButton);
		
		panel4.add(cLabel);
		panel4.add(nLabel);
		panel4.add(pLabel);
		panel4.add(fLabel);
		
		panel4.add(codeLabel1);
		panel4.add(nameLabel1);
		panel4.add(priceLabel1);
		panel4.add(flagLabel1);
		panel4.add(codeLabel2);
		panel4.add(nameLabel2);
		panel4.add(priceLabel2);
		panel4.add(flagLabel2);
		panel4.add(codeLabel3);
		panel4.add(nameLabel3);
		panel4.add(priceLabel3);
		panel4.add(flagLabel3);
		panel4.add(codeLabel4);
		panel4.add(nameLabel4);
		panel4.add(priceLabel4);
		panel4.add(flagLabel4);
		panel4.add(codeLabel5);
		panel4.add(nameLabel5);
		panel4.add(priceLabel5);
		panel4.add(flagLabel5);
		panel4.add(codeLabel6);
		panel4.add(nameLabel6);
		panel4.add(priceLabel6);
		panel4.add(flagLabel6);
		panel4.add(codeLabel7);
		panel4.add(nameLabel7);
		panel4.add(priceLabel7);
		panel4.add(flagLabel7);
		panel4.add(codeLabel8);
		panel4.add(nameLabel8);
		panel4.add(priceLabel8);
		panel4.add(flagLabel8);
		panel4.add(codeLabel9);
		panel4.add(nameLabel9);
		panel4.add(priceLabel9);
		panel4.add(flagLabel9);
		panel4.add(codeLabel10);
		panel4.add(nameLabel10);
		panel4.add(priceLabel10);
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
		if(((String)codeComboBox.getSelectedItem()).contains("群")) {
			String str1 = "SELECT *, LEFT(商品コード, 1) AS 先頭コード FROM 商品マスタ WHERE LEFT(商品コード, 1) = 'A'";
		}
		
		
		
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
