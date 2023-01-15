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
	
	JLabel filterLabel = new JLabel("絞り込み条件");//絞り込み条件の入力欄であることを示すラベル
	
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
	JLabel fcnLabel = new JLabel("  店員名"); //店員名の選択欄であることを示すラベル
	JComboBox clerkNameComboBox = new JComboBox(); //店員名の選択欄
	
	JLabel fgcLabel = new JLabel("  商品コード"); //商品コードの選択欄であることを示すラベル
	JComboBox goodsCodeComboBox = new JComboBox(); //商品コードの選択欄 
	JLabel fgnLabel = new JLabel("  商品名"); //商品名の選択欄であることを示すラベル
	JComboBox goodsNameComboBox = new JComboBox(); //商品名の選択欄
	
	JLabel ftoLabel = new JLabel("  合計額"); //合計額の入力欄であることを示すラベル
	JTextField totalTextField = new JTextField("0", 6); //合計額の入力欄
	JComboBox totalRangeComboBox = new JComboBox(); //入力した合計額以上か等を選択するボックス
	
	JLabel ffLabel = new JLabel("  削除フラグ"); //削除フラグの選択欄であることを示すラベル
	JComboBox flagComboBox = new JComboBox(); //削除フラグの選択欄
	
	JButton searchButton = new JButton("検索"); //検索ボタン
	
	//集計の条件の入力欄
	JLabel vacantLabel1 = new JLabel(" "); //検索と集計の間に空白を設けるためのラベル
	
	JLabel aggregateLabel = new JLabel("集計");//集計の入力欄であることを示すラベル
	
	JComboBox firstYearComboBox = new JComboBox(); //何年からかを選択するボックス
	JLabel yearUnitLabel1 = new JLabel("年 "); //「年」を表示するラベル
	JLabel karaLabel1 = new JLabel("  ～"); //「～」を表示するラベル
	JComboBox lastYearComboBox = new JComboBox(); //何年までかを選択するボックス
	JLabel yearUnitLabel2 = new JLabel("年 "); //「年」を表示するラベル
	JLabel joshiLabel1 = new JLabel("  の"); //「の」を表示するラベル
	
	JComboBox firstMonthComboBox = new JComboBox(); //何月からかを選択するボックス
	JLabel monthUnitLabel1 = new JLabel("月 "); //「月」を表示するラベル
	JComboBox lastMonthComboBox = new JComboBox(); //何月までかを選択するボックス
	JLabel monthUnitLabel2 = new JLabel("月 "); //「月」を表示するラベル
	
	JComboBox firstDateComboBox = new JComboBox(); //何日からかを選択するボックス
	JLabel dateUnitLabel1 = new JLabel("日 "); //「日」を表示するラベル
	JComboBox lastDateComboBox = new JComboBox(); //何日までかを選択するボックス
	JLabel dateUnitLabel2 = new JLabel("日 "); //「日」を表示するラベル
	
	JComboBox firstHourComboBox = new JComboBox(); //何時からかを選択するボックス
	JLabel hourUnitLabel1 = new JLabel("時 "); //「時」を表示するラベル
	JComboBox lastHourComboBox = new JComboBox(); //何時までかを選択するボックス
	JLabel hourUnitLabel2 = new JLabel("時 "); //「時」を表示するラベル
		
	JComboBox firstMinuteComboBox = new JComboBox(); //何分からかを選択するボックス
	JLabel minuteUnitLabel1 = new JLabel("分 "); //「分」を表示するラベル
	JComboBox lastMinuteComboBox = new JComboBox(); //何分までかを選択するボックス
	JLabel minuteUnitLabel2 = new JLabel("分 "); //「分」を表示するラベル
	
	JComboBox rangeComboBox = new JComboBox(); //「すべて」「商品コードA台」「001」等を選ぶ
	JLabel joshiLabel5 = new JLabel("  の"); //「の」を表示するラベル
	
	JComboBox objectComboBox1 = new JComboBox(); //「商品」「伝票」「店員」を選ぶ
	JLabel joshiLabel6 = new JLabel("  の"); //「の」を表示するラベル
	
	JComboBox objectComboBox2 = new JComboBox(); //「売上額」「売上件数」「売上個数」「人数」「種類」を選ぶ
	
	JButton aggregateButton = new JButton("集計"); //集計ボタン
	JLabel vacantLabel2 = new JLabel("      "); //集計ボタンと集計結果の間に空白を設けるためのラベル
	JLabel aggregateResultLabel = new JLabel(""); //集計結果を表示するラベル
	JLabel unitLabel = new JLabel("円"); //単位を表示するラベル
	
	//検索結果の表示場所
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
	
	JLabel fLabel = new JLabel("削除フラグ"); //削除フラグであることを示すラベル
	JLabel flagLabel1 = new JLabel(); //削除フラグを表示するラベル
	JLabel flagLabel2 = new JLabel();
	JLabel flagLabel3 = new JLabel();
	JLabel flagLabel4 = new JLabel();
	JLabel flagLabel5 = new JLabel();
	JLabel flagLabel6 = new JLabel();
	JLabel flagLabel7 = new JLabel();
	JLabel flagLabel8 = new JLabel();
	JLabel flagLabel9 = new JLabel();
	JLabel flagLabel10 = new JLabel();
	
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
	Connection conn;
	Statement stmt;
	ResultSet rs;
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel2_1 = new JPanel();
	JPanel panel2_2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel4_1 = new JPanel();
	JPanel panel4_2 = new JPanel();
	JPanel panel4_3 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel15 = new JPanel();
	
	SalesSearchGUI(){
		setTitle("売上検索");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//レイアウト設定
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2_1.setLayout(new FlowLayout());
		panel2_2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		panel4_1.setLayout(new FlowLayout());
		panel4_2.setLayout(new FlowLayout());
		panel4_3.setLayout(new FlowLayout());
		panel5.setLayout(new GridLayout(11, 9, 0, 2));
		panel15.setLayout(new FlowLayout());
		
		
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
		
		//削除フラグを選択するコンボボックスに項目を追加
		flagComboBox.addItem(null);
		flagComboBox.addItem("0");
		flagComboBox.addItem("1");
		flagComboBox.setSelectedItem(null);
		
		//集計で使用するコンボボックスに項目を追加
		//firstYearComboBoxに項目を追加
		firstYearComboBox.addItem("指定なし");
		try {
			SQL = "SELECT DATE_FORMAT(販売日時, '%Y') AS 時間 FROM 売上マスタ GROUP BY DATE_FORMAT(販売日時, '%Y');";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				firstYearComboBox.addItem(rs.getString("時間"));
			} 
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		firstYearComboBox.setSelectedIndex(1);
		//lastYearComboBoxに項目を追加
		lastYearComboBox.addItem("指定なし");
		try {
			SQL = "SELECT DATE_FORMAT(販売日時, '%Y') AS 時間 FROM 売上マスタ GROUP BY DATE_FORMAT(販売日時, '%Y');";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				lastYearComboBox.addItem(rs.getString("時間"));
			} 
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		lastYearComboBox.setSelectedItem(getYear());
		//firstMonthComboBoxに項目を追加
		firstMonthComboBox.addItem("指定なし");
		for(int i = 1; i <= 12; i++) {
		String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		firstMonthComboBox.addItem(s);
		}		
		firstMonthComboBox.setSelectedItem("01");
		//lastMonthComboBoxに項目を追加
		lastMonthComboBox.addItem("指定なし");
		for(int i = 1; i <= 12; i++) {
		String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		lastMonthComboBox.addItem(s);
		}
		lastMonthComboBox.setSelectedItem(getMonth());
		//firstDateComboBoxに項目を追加
		firstDateComboBox.addItem("指定なし");
		for(int i = 1; i <= 31; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		firstDateComboBox.addItem(s);
		}
		firstDateComboBox.setSelectedItem("01");
		//lastDateComboBoxに項目を追加
		lastDateComboBox.addItem("指定なし");
		for(int i = 1; i <= 31; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		lastDateComboBox.addItem(s);
		}
		lastDateComboBox.setSelectedItem(getDate());
		//firstHourComboBoxに項目を追加
		firstHourComboBox.addItem("指定なし");
		for(int i = 0; i <= 23; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			firstHourComboBox.addItem(s);
		}
		firstHourComboBox.setSelectedItem("00");
		//lastHourComboBoxに項目を追加
		lastHourComboBox.addItem("指定なし");
		for(int i = 0; i <= 23; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		lastHourComboBox.addItem(s);
		}
		lastHourComboBox.setSelectedItem(getHour());
		//firstMinuteComboBoxに項目を追加
		firstMinuteComboBox.addItem("指定なし");
		for(int i = 0; i <= 59; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			firstMinuteComboBox.addItem(s);
		}
		firstMinuteComboBox.setSelectedItem("00");
		//lastMinuteComboBoxに項目を追加
		lastMinuteComboBox.addItem("指定なし");
		for(int i = 0; i <= 59; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		lastMinuteComboBox.addItem(s);
		}
		lastMinuteComboBox.setSelectedItem(getMinute());
		
		//rangeComboBoxに項目を追加
		rangeComboBox.addItem("すべて");
		try {
			SQL = "SELECT CONCAT(LEFT(商品コード, 1), '群') AS 先頭コード FROM 商品マスタ GROUP BY 先頭コード;";
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL);
			while(rs.next()){
				rangeComboBox.addItem(rs.getString("先頭コード"));
			} 
			SQL = "SELECT 商品コード FROM 商品マスタ;";
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				rangeComboBox.addItem(rs.getString("商品コード"));
			} 
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		//objectComboBox1に項目を追加
		objectComboBox1.addItem("商品");
		objectComboBox1.addItem("店員");
		objectComboBox1.addItem("伝票");
		//objectComboBox2に項目を追加
		objectComboBox2.addItem("売上額");
		objectComboBox2.addItem("売上件数");
		objectComboBox2.addItem("売上個数");
		
		//ラベルの色分け設定
		setColor1(nLabel);
		setColor1(dLabel);
		setColor1(ccLabel);
		setColor1(cnLabel);
		setColor1(gcLabel);
		setColor1(gnLabel);
		setColor1(ctLabel);
		setColor1(stLabel);
		setColor1(fLabel);
		
		setColor2(numberLabel2);
		setColor2(dateLabel2);
		setColor2(clerkCodeLabel2);
		setColor2(clerkNameLabel2);
		setColor2(goodsCodeLabel2);
		setColor2(goodsNameLabel2);
		setColor2(countLabel2);
		setColor2(subTotalLabel2);
		setColor2(flagLabel2);
		
		setColor2(numberLabel4);
		setColor2(dateLabel4);
		setColor2(clerkCodeLabel4);
		setColor2(clerkNameLabel4);
		setColor2(goodsCodeLabel4);
		setColor2(goodsNameLabel4);
		setColor2(countLabel4);
		setColor2(subTotalLabel4);
		setColor2(flagLabel4);
		
		setColor2(numberLabel6);
		setColor2(dateLabel6);
		setColor2(clerkCodeLabel6);
		setColor2(clerkNameLabel6);
		setColor2(goodsCodeLabel6);
		setColor2(goodsNameLabel6);
		setColor2(countLabel6);
		setColor2(subTotalLabel6);
		setColor2(flagLabel6);
		
		setColor2(numberLabel8);
		setColor2(dateLabel8);
		setColor2(clerkCodeLabel8);
		setColor2(clerkNameLabel8);
		setColor2(goodsCodeLabel8);
		setColor2(goodsNameLabel8);
		setColor2(countLabel8);
		setColor2(subTotalLabel8);
		setColor2(flagLabel8);
		
		setColor2(numberLabel10);
		setColor2(dateLabel10);
		setColor2(clerkCodeLabel10);
		setColor2(clerkNameLabel10);
		setColor2(goodsCodeLabel10);
		setColor2(goodsNameLabel10);
		setColor2(countLabel10);
		setColor2(subTotalLabel10);
		setColor2(flagLabel10);
		
		panel1.add(filterLabel);
		
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
		panel2_2.add(ffLabel);
		panel2_2.add(flagComboBox);
		
		panel2.add(panel2_1);
		panel2.add(panel2_2);

		panel3.add(searchButton);
		
		panel4_1.add(aggregateLabel);
		
		panel4_2.add(firstYearComboBox);
		panel4_2.add(yearUnitLabel1);
		panel4_2.add(firstMonthComboBox);
		panel4_2.add(monthUnitLabel1);
		panel4_2.add(firstDateComboBox);
		panel4_2.add(dateUnitLabel1);
		panel4_2.add(firstHourComboBox);
		panel4_2.add(hourUnitLabel1);
		panel4_2.add(firstMinuteComboBox);
		panel4_2.add(minuteUnitLabel1);
		
		panel4_2.add(karaLabel1);
		
		panel4_2.add(lastYearComboBox);
		panel4_2.add(yearUnitLabel2);
		panel4_2.add(lastMonthComboBox);
		panel4_2.add(monthUnitLabel2);
		panel4_2.add(lastDateComboBox);
		panel4_2.add(dateUnitLabel2);
		panel4_2.add(lastHourComboBox);
		panel4_2.add(hourUnitLabel2);
		panel4_2.add(lastMinuteComboBox);
		panel4_2.add(minuteUnitLabel2);
		
		panel4_2.add(joshiLabel1);
		
		panel4_2.add(rangeComboBox);
		panel4_2.add(joshiLabel5);
		panel4_2.add(objectComboBox1);
		panel4_2.add(joshiLabel6);
		panel4_2.add(objectComboBox2);
		
		panel4_3.add(aggregateButton);
		panel4_3.add(vacantLabel2);
		panel4_3.add(aggregateResultLabel);
		panel4_3.add(unitLabel);
		
		panel4.add(panel4_1);
		panel4.add(panel4_2);
		panel4.add(panel4_3);
		
		panel5.add(nLabel);
		panel5.add(dLabel);
		panel5.add(ccLabel);
		panel5.add(cnLabel);
		panel5.add(gcLabel);
		panel5.add(gnLabel);
		panel5.add(ctLabel);
		panel5.add(stLabel);
		panel5.add(fLabel);
		
		panel5.add(numberLabel1);
		panel5.add(dateLabel1);
		panel5.add(clerkCodeLabel1);
		panel5.add(clerkNameLabel1);
		panel5.add(goodsCodeLabel1);
		panel5.add(goodsNameLabel1);
		panel5.add(countLabel1);
		panel5.add(subTotalLabel1);
		panel5.add(flagLabel1);
		
		panel5.add(numberLabel2);
		panel5.add(dateLabel2);
		panel5.add(clerkCodeLabel2);
		panel5.add(clerkNameLabel2);
		panel5.add(goodsCodeLabel2);
		panel5.add(goodsNameLabel2);
		panel5.add(countLabel2);
		panel5.add(subTotalLabel2);
		panel5.add(flagLabel2);
		
		panel5.add(numberLabel3);
		panel5.add(dateLabel3);
		panel5.add(clerkCodeLabel3);
		panel5.add(clerkNameLabel3);
		panel5.add(goodsCodeLabel3);
		panel5.add(goodsNameLabel3);
		panel5.add(countLabel3);
		panel5.add(subTotalLabel3);
		panel5.add(flagLabel3);

		panel5.add(numberLabel4);
		panel5.add(dateLabel4);
		panel5.add(clerkCodeLabel4);
		panel5.add(clerkNameLabel4);
		panel5.add(goodsCodeLabel4);
		panel5.add(goodsNameLabel4);
		panel5.add(countLabel4);
		panel5.add(subTotalLabel4);
		panel5.add(flagLabel4);
		
		panel5.add(numberLabel5);
		panel5.add(dateLabel5);
		panel5.add(clerkCodeLabel5);
		panel5.add(clerkNameLabel5);
		panel5.add(goodsCodeLabel5);
		panel5.add(goodsNameLabel5);
		panel5.add(countLabel5);
		panel5.add(subTotalLabel5);
		panel5.add(flagLabel5);
		
		panel5.add(numberLabel6);
		panel5.add(dateLabel6);
		panel5.add(clerkCodeLabel6);
		panel5.add(clerkNameLabel6);
		panel5.add(goodsCodeLabel6);
		panel5.add(goodsNameLabel6);
		panel5.add(countLabel6);
		panel5.add(subTotalLabel6);
		panel5.add(flagLabel6);
		
		panel5.add(numberLabel7);
		panel5.add(dateLabel7);
		panel5.add(clerkCodeLabel7);
		panel5.add(clerkNameLabel7);
		panel5.add(goodsCodeLabel7);
		panel5.add(goodsNameLabel7);
		panel5.add(countLabel7);
		panel5.add(subTotalLabel7);
		panel5.add(flagLabel7);
		
		panel5.add(numberLabel8);
		panel5.add(dateLabel8);
		panel5.add(clerkCodeLabel8);
		panel5.add(clerkNameLabel8);
		panel5.add(goodsCodeLabel8);
		panel5.add(goodsNameLabel8);
		panel5.add(countLabel8);
		panel5.add(subTotalLabel8);
		panel5.add(flagLabel8);
		
		panel5.add(numberLabel9);
		panel5.add(dateLabel9);
		panel5.add(clerkCodeLabel9);
		panel5.add(clerkNameLabel9);
		panel5.add(goodsCodeLabel9);
		panel5.add(goodsNameLabel9);
		panel5.add(countLabel9);
		panel5.add(subTotalLabel9);
		panel5.add(flagLabel9);
		
		panel5.add(numberLabel10);
		panel5.add(dateLabel10);
		panel5.add(clerkCodeLabel10);
		panel5.add(clerkNameLabel10);
		panel5.add(goodsCodeLabel10);
		panel5.add(goodsNameLabel10);
		panel5.add(countLabel10);
		panel5.add(subTotalLabel10);
		panel5.add(flagLabel10);
		
		panel15.add(previousButton);
		panel15.add(showNumberLabel);
		panel15.add(snLabel);
		panel15.add(totalNumberLabel);
		panel15.add(tnLabel);
		panel15.add(nextButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		getContentPane().add(panel5);
		getContentPane().add(panel15);
		getContentPane().add(vacantLabel1);
		getContentPane().add(panel4);
		
		searchButton.addActionListener(this);
		nextButton.addActionListener(this);
		previousButton.addActionListener(this);
		nextButton.setEnabled(false);
		previousButton.setEnabled(false);
		
		firstYearComboBox.addActionListener(this);
		firstMonthComboBox.addActionListener(this);
		firstDateComboBox.addActionListener(this);
		firstHourComboBox.addActionListener(this);
		firstMinuteComboBox.addActionListener(this);
		lastYearComboBox.addActionListener(this);
		lastMonthComboBox.addActionListener(this);
		lastDateComboBox.addActionListener(this);
		lastHourComboBox.addActionListener(this);
		lastMinuteComboBox.addActionListener(this);
		rangeComboBox.addActionListener(this);
		objectComboBox1.addActionListener(this);
		objectComboBox2.addActionListener(this);
		
		aggregateButton.addActionListener(this);
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
		} else if(e.getSource() == aggregateButton) {
			//集計するためのSQLを作成する
			SQL = createAggregateSQL(firstYearComboBox, lastYearComboBox, firstMonthComboBox, lastMonthComboBox, 
					firstDateComboBox, lastDateComboBox, firstHourComboBox, lastHourComboBox, firstMinuteComboBox, 
					lastMinuteComboBox, rangeComboBox, objectComboBox1, objectComboBox2);
			System.out.println(SQL);
			try {
				conn = DriverManager.getConnection(URL, USER, PASS);
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs = stmt.executeQuery(SQL);
				if(rs.next()) {
					System.out.println("rs.getString(\"集計結果\")" + rs.getString("集計結果"));
					if(rs.getString("集計結果") == null) {
						aggregateResultLabel.setText("0");
					} else {
						aggregateResultLabel.setText(rs.getString("集計結果"));
					}
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		} else if(e.getSource() == objectComboBox1) {
			//選んだ項目によってobjectComboBox2とrangeComboBoxの項目を変更
			switch((String)objectComboBox1.getSelectedItem()) {
			case "商品":
				//objectComboBox2の項目を変更
				String item1 = (String) objectComboBox2.getSelectedItem();
				objectComboBox2.removeAllItems();
				objectComboBox2.addItem("売上額");
				objectComboBox2.addItem("売上件数");
				objectComboBox2.addItem("売上個数");
				objectComboBox2.setSelectedItem(item1);
				//rangeComboBoxの項目を変更
				rangeComboBox.removeAllItems();
				rangeComboBox.addItem("すべて");
				try {
					SQL = "SELECT CONCAT(LEFT(商品コード, 1), '群') AS 先頭コード FROM 商品マスタ GROUP BY 先頭コード;";
					Connection conn = DriverManager.getConnection(URL, USER, PASS);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SQL);
					while(rs.next()){
						rangeComboBox.addItem(rs.getString("先頭コード"));
					} 
					SQL = "SELECT 商品コード FROM 商品マスタ;";
					rs = stmt.executeQuery(SQL);
					while(rs.next()){
						rangeComboBox.addItem(rs.getString("商品コード"));
					} 
				}catch(SQLException e2) {
					e2.printStackTrace();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
				break;
			case "店員":
				//objectComboBox2の項目を変更
				String item2 = (String) objectComboBox2.getSelectedItem();
				objectComboBox2.removeAllItems();
				objectComboBox2.addItem("売上額");
				objectComboBox2.addItem("売上件数");
				objectComboBox2.setSelectedItem(item2);
				//rangeComboBoxの項目を変更
				rangeComboBox.removeAllItems();
				rangeComboBox.addItem("すべて");
				try {
					SQL = "SELECT 店員コード FROM 店員マスタ;";
					Connection conn = DriverManager.getConnection(URL, USER, PASS);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SQL);
					rs = stmt.executeQuery(SQL);
					while(rs.next()){
						rangeComboBox.addItem(rs.getString("店員コード"));
					} 
				}catch(SQLException e2) {
					e2.printStackTrace();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
				break;
			case "伝票":
				//objectComboBox2の項目を変更
				String item3 = (String) objectComboBox2.getSelectedItem();
				objectComboBox2.removeAllItems();
				objectComboBox2.addItem("売上額");
				objectComboBox2.addItem("件数");
				objectComboBox2.setSelectedItem(item3);
				//rangeComboBoxの項目を変更
				rangeComboBox.removeAllItems();
				rangeComboBox.addItem("すべて");
				try {
					SQL = "SELECT DISTINCT(伝票番号) AS 伝票番号 FROM 売上マスタ;";
					Connection conn = DriverManager.getConnection(URL, USER, PASS);
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(SQL);
					rs = stmt.executeQuery(SQL);
					while(rs.next()){
						rangeComboBox.addItem(rs.getString("伝票番号"));
					} 
				}catch(SQLException e2) {
					e2.printStackTrace();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
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
			filterSQL += " AND 氏名 = '" + clerkNameComboBox.getSelectedItem() + "'"; 
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
		if(flagComboBox.getSelectedItem() != null) {
			filterSQL += " AND U.削除フラグ = " + flagComboBox.getSelectedItem(); 
		}
		String str = "SELECT 伝票番号, 販売日時, U.店員コード, 氏名, U.商品コード, 商品名, 個数, 小計, U.削除フラグ "
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
						goodsCodeLabel1, goodsNameLabel1, countLabel1, subTotalLabel1, flagLabel1);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel1, dateLabel1, clerkCodeLabel1, clerkNameLabel1, 
						goodsCodeLabel1, goodsNameLabel1, countLabel1, subTotalLabel1, flagLabel1);
			}
			if(rs.next()){
				show(numberLabel2, dateLabel2, clerkCodeLabel2, clerkNameLabel2, 
						goodsCodeLabel2, goodsNameLabel2, countLabel2, subTotalLabel2, flagLabel2);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel2, dateLabel2, clerkCodeLabel2, clerkNameLabel2, 
						goodsCodeLabel2, goodsNameLabel2, countLabel2, subTotalLabel2, flagLabel2);
			}
			if(rs.next()){
				show(numberLabel3, dateLabel3, clerkCodeLabel3, clerkNameLabel3, 
						goodsCodeLabel3, goodsNameLabel3, countLabel3, subTotalLabel3, flagLabel3);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel3, dateLabel3, clerkCodeLabel3, clerkNameLabel3, 
						goodsCodeLabel3, goodsNameLabel3, countLabel3, subTotalLabel3, flagLabel3);
			}
			if(rs.next()){
				show(numberLabel4, dateLabel4, clerkCodeLabel4, clerkNameLabel4, 
						goodsCodeLabel4, goodsNameLabel4, countLabel4, subTotalLabel4, flagLabel4);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel4, dateLabel4, clerkCodeLabel4, clerkNameLabel4, 
						goodsCodeLabel4, goodsNameLabel4, countLabel4, subTotalLabel4, flagLabel4);
			}
			if(rs.next()){
				show(numberLabel5, dateLabel5, clerkCodeLabel5, clerkNameLabel5, 
						goodsCodeLabel5, goodsNameLabel5, countLabel5, subTotalLabel5, flagLabel5);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel5, dateLabel5, clerkCodeLabel5, clerkNameLabel5, 
						goodsCodeLabel5, goodsNameLabel5, countLabel5, subTotalLabel5, flagLabel5);
			}
			if(rs.next()){
				show(numberLabel6, dateLabel6, clerkCodeLabel6, clerkNameLabel6, 
						goodsCodeLabel6, goodsNameLabel6, countLabel6, subTotalLabel6, flagLabel6);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel6, dateLabel6, clerkCodeLabel6, clerkNameLabel6, 
						goodsCodeLabel6, goodsNameLabel6, countLabel6, subTotalLabel6, flagLabel6);
			}
			if(rs.next()){
				show(numberLabel7, dateLabel7, clerkCodeLabel7, clerkNameLabel7, 
						goodsCodeLabel7, goodsNameLabel7, countLabel7, subTotalLabel7, flagLabel7);	
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel7, dateLabel7, clerkCodeLabel7, clerkNameLabel7, 
						goodsCodeLabel7, goodsNameLabel7, countLabel7, subTotalLabel7, flagLabel7);
			}
			if(rs.next()){
				show(numberLabel8, dateLabel8, clerkCodeLabel8, clerkNameLabel8, 
						goodsCodeLabel8, goodsNameLabel8, countLabel8, subTotalLabel8, flagLabel8);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel8, dateLabel8, clerkCodeLabel8, clerkNameLabel8, 
						goodsCodeLabel8, goodsNameLabel8, countLabel8, subTotalLabel8, flagLabel8);
			}
			if(rs.next()){
				show(numberLabel9, dateLabel9, clerkCodeLabel9, clerkNameLabel9, 
						goodsCodeLabel9, goodsNameLabel9, countLabel9, subTotalLabel9, flagLabel9);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset( numberLabel9, dateLabel9, clerkCodeLabel9, clerkNameLabel9, 
						goodsCodeLabel9, goodsNameLabel9, countLabel9, subTotalLabel9, flagLabel9);
			}
			if(rs.next()){
				show(numberLabel10, dateLabel10, clerkCodeLabel10, clerkNameLabel10, 
						goodsCodeLabel10, goodsNameLabel10, countLabel10, subTotalLabel10, flagLabel10);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(numberLabel10, dateLabel10, clerkCodeLabel10, clerkNameLabel10, 
						goodsCodeLabel10, goodsNameLabel10, countLabel10, subTotalLabel10, flagLabel10);
			}
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullでなければラベルに内容を表示するメソッド
	public void show(JLabel numberLabel, JLabel dateLabel, JLabel clerkCodeLabel, JLabel clerkNameLabel, 
			JLabel goodsCodeLabel, JLabel goodsNameLabel, JLabel countLabel, JLabel subTotalLabel, JLabel flagLabel) {
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
			flagLabel.setText(rs.getString("削除フラグ"));
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullならばラベルを白紙にするメソッド
	public void reset(JLabel numberLabel, JLabel dateLabel, JLabel clerkCodeLabel, JLabel clerkNameLabel, 
			JLabel goodsCodeLabel, JLabel goodsNameLabel, JLabel countLabel, JLabel subTotalLabel, JLabel flagLabel) {
		numberLabel.setText("");
		dateLabel.setText(null);
		clerkCodeLabel.setText(null);
		clerkNameLabel.setText(null);
		goodsCodeLabel.setText(null);
		goodsNameLabel.setText(null);
		countLabel.setText(null);
		subTotalLabel.setText(null);
		flagLabel.setText(null);
	}
	
	public String createAggregateSQL(JComboBox fyBox, JComboBox lyBox, JComboBox fmBox, JComboBox lmBox, 
			JComboBox fdBox, JComboBox ldBox, JComboBox fhBox, JComboBox lhBox, JComboBox fmiBox, JComboBox lmiBox,
			JComboBox rBox, JComboBox oBox1, JComboBox oBox2) {
		String str = "";
		
		//objectComboBox1が「商品」「店員」「伝票」のいずれかで場合分け
		switch((String)oBox1.getSelectedItem()) {
		//「商品」の場合
		case "商品": 
			//objectComboBox2が「売上額」「売上件数」「売上個数」のいずれかで場合分け
			switch((String)oBox2.getSelectedItem()) {
			case "売上額":
				if(rBox.getSelectedItem().equals("すべて")) {
					filterSQL += " 1";
					//時間の条件を追加
					filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
							"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
					filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
							"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
					//最終的なSQL文
					str = "SELECT sum(小計) AS 集計結果"
							+ " FROM 売上マスタ"
							+ " WHERE" + filterSQL + ";";
				} else {
					if(((String)rBox.getSelectedItem()).contains("群")) {
						//群の場合
						filterSQL += " 1";
						//時間の条件を追加
						filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
								"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
						filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
								"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
						
						str = "SELECT SUM(小計) AS 集計結果 "
								+ "FROM (SELECT LEFT(商品コード, 1) AS 先頭コード, 小計 FROM 売上マスタ WHERE" + filterSQL + ") T "
								+ "WHERE 先頭コード = '" + ((String)rBox.getSelectedItem()).replace("群", "") + "';";
						
					} else {
						//通常の商品コードの場合
						filterSQL += " 1";
						//時間の条件を追加
						filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
								"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
						filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
								"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
						//商品コードの条件を追加
						filterSQL += " AND 商品コード = '" + rangeComboBox.getSelectedItem() + "'";
						str = "SELECT sum(小計) AS 集計結果 "
								+ "FROM (SELECT * FROM 売上マスタ WHERE" + filterSQL + ") T;";
					}
				}
			
				unitLabel.setText("円");
				break;
			case "売上件数":
				if(rBox.getSelectedItem().equals("すべて")) {
					filterSQL += " 1";
					//時間の条件を追加
					filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
							"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
					filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
							"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
					str = "SELECT count(T.伝票番号) AS 集計結果 "
							+ "FROM (SELECT * FROM 売上マスタ WHERE" + filterSQL + ") T;";
				} else {
					if(((String)rBox.getSelectedItem()).contains("群")) {
						//群が含まれている場合
						filterSQL += " 1";
						//時間の条件を追加
						filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
								"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
						filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
								"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
						
						str = "SELECT count(T.伝票番号) AS 集計結果 "
								+ "FROM (SELECT LEFT(商品コード, 1) AS 先頭コード, 伝票番号 FROM 売上マスタ WHERE" + filterSQL + ") T "
								+ "WHERE 先頭コード = '" + ((String)rBox.getSelectedItem()).replace("群", "") + "';";
					} else {
						//通常の商品コードの場合
						filterSQL += " 1";
						//時間の条件を追加
						filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
								"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
						filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
								"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
						//商品コードの条件を追加
						filterSQL += " AND 商品コード = '" + rangeComboBox.getSelectedItem() + "'";
						str = "SELECT count(T.伝票番号) AS 集計結果 "
								+ "FROM (SELECT * FROM 売上マスタ WHERE" + filterSQL + " GROUP BY 伝票番号) T;";
					}
				}
				unitLabel.setText("件");
				break;
			case "売上個数":
				if(rBox.getSelectedItem().equals("すべて")) {
					filterSQL += " 1";
					//時間の条件を追加
					filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
							"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
					filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
							"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
					str = "SELECT sum(個数) AS 集計結果 "
							+ "FROM 売上マスタ"
							+ " WHERE" + filterSQL + ";";
				} else {
					if(((String)rBox.getSelectedItem()).contains("群")) {
						//群が含まれている場合
						filterSQL += " 1";
						//時間の条件を追加
						filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
								"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
						filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
								"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
						
						str = "SELECT SUM(個数) AS 集計結果 "
								+ "FROM (SELECT LEFT(商品コード, 1) AS 先頭コード, 個数 FROM 売上マスタ WHERE" + filterSQL + ") T "
								+ "WHERE 先頭コード = '" + ((String)rBox.getSelectedItem()).replace("群", "") + "';";
					} else {
						//通常の商品コードの場合
						filterSQL += " 1";
						//時間の条件を追加
						filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
								"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
						filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
								"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
						//商品コードの条件を追加
						filterSQL += " AND 商品コード = '" + rangeComboBox.getSelectedItem() + "'";
						str = "SELECT sum(個数) AS 集計結果 "
								+ "FROM (SELECT * FROM 売上マスタ WHERE" + filterSQL + ") T;";
					}
				}
				unitLabel.setText("個");
			}
			break;
		//「店員」の場合
		case "店員":
			//objectComboBox2が「売上額」「売上件数」のいずれかで場合分け
			switch((String)oBox2.getSelectedItem()) {
			case "売上額":
				if(rBox.getSelectedItem().equals("すべて")) {
					filterSQL += " 1";
					//時間の条件を追加
					filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
							"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
					filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
							"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
					//最終的なSQL文
					str = "SELECT sum(小計) AS 集計結果"
							+ " FROM 売上マスタ"
							+ " WHERE" + filterSQL + ";";
				} else {
					filterSQL += " 1";
					//時間の条件を追加
					filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
							"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
					filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
							"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
					//店員コードの条件を追加
					filterSQL += " AND 店員コード = '" + rangeComboBox.getSelectedItem() + "'";
					str = "SELECT sum(小計) AS 集計結果 "
							+ "FROM (SELECT * FROM 売上マスタ WHERE" + filterSQL + ") T;";
				}
				unitLabel.setText("円");
				break;
			case "売上件数":
				if(rBox.getSelectedItem().equals("すべて")) {
					filterSQL += " 1";
					//時間の条件を追加
					filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
							"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
					filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
							"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
					str = "SELECT count(T.伝票番号) AS 集計結果 "
							+ "FROM (SELECT * FROM 売上マスタ WHERE" + filterSQL + " GROUP BY 伝票番号) T;";
				} else {
					filterSQL += " 1";
					//時間の条件を追加
					filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
							"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
					filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
							"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
					//店員コードの条件を追加
					filterSQL += " AND 店員コード = '" + rangeComboBox.getSelectedItem() + "'";
					str = "SELECT count(T.伝票番号) AS 集計結果 "
							+ "FROM (SELECT * FROM 売上マスタ WHERE" + filterSQL + " GROUP BY 伝票番号) T;";
				}
				unitLabel.setText("件");
			}
			break;
		//「伝票」の場合
		case "伝票":
			//objectComboBox2が「売上額」「件数」のいずれかで場合分け
			switch((String)oBox2.getSelectedItem()) {
			case "売上額":
				if(rBox.getSelectedItem().equals("すべて")) {
					filterSQL += " 1";
					//時間の条件を追加
					filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
							"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
					filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
							"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
					//最終的なSQL文
					str = "SELECT sum(小計) AS 集計結果"
							+ " FROM 売上マスタ"
							+ " WHERE" + filterSQL + ";";
				} else {
					filterSQL += " 1";
					//時間の条件を追加
					filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
							"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
					filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
							"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
					//伝票番号の条件を追加
					filterSQL += " AND 伝票番号 = " + rangeComboBox.getSelectedItem();
					//最終的なSQL文
					str = "SELECT sum(小計) AS 集計結果"
							+ " FROM 売上マスタ"
							+ " WHERE" + filterSQL + ";";
				}
				unitLabel.setText("円");
				break;
			case "件数":
				if(rBox.getSelectedItem().equals("すべて")) {
					filterSQL += " 1";
					//時間の条件を追加
					filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
							"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
					filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
							"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
					str = "SELECT count(T.伝票番号) AS 集計結果 "
							+ "FROM (SELECT * FROM 売上マスタ WHERE" + filterSQL + " GROUP BY 伝票番号) T;";
				} else {
					filterSQL += " 1";
					//時間の条件を追加
					filterSQL += " AND 販売日時 >= '" + fyBox.getSelectedItem() + "-" + fmBox.getSelectedItem() + 
							"-" + fdBox.getSelectedItem() + " " + fhBox.getSelectedItem() + ":" + fmiBox.getSelectedItem() + "'";
					filterSQL += " AND 販売日時 <= '" + lyBox.getSelectedItem() + "-" + lmBox.getSelectedItem() + 
							"-" + ldBox.getSelectedItem() + " " + lhBox.getSelectedItem() + ":" + lmiBox.getSelectedItem() + "'";
					//伝票番号の条件を追加
					filterSQL += " AND 伝票番号 = " + rangeComboBox.getSelectedItem();
					str = "SELECT count(T.伝票番号) AS 集計結果 "
							+ "FROM (SELECT * FROM 売上マスタ WHERE" + filterSQL + " GROUP BY 伝票番号) T;";
				}
				unitLabel.setText("件");
			}
		}
		//filterSQLをリセットする
		filterSQL = ""; 
		return str;
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
