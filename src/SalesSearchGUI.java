import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	JButton searchButton = new JButton("絞り込み"); //絞り込みボタン
	JButton releaseButton = new JButton("絞り込み解除"); //絞り込み解除ボタン
	
	//集計の条件の入力欄
	JLabel vacantLabel1 = new JLabel(" "); //検索と集計の間に空白を設けるためのラベル
	
	JLabel aggregateLabel = new JLabel("集計");//集計の入力欄であることを示すラベル
	
	//ある期間のみ集計（例：2023/01/01 00:00 ~ 2023/01/10 23:59の売上）
	JLabel choiceLabel1 = new JLabel("ある期間のみ集計"); //ある期間のみ集計することを示すラベル
	JButton choiceButton1 = new JButton("選択中");
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
	
	//繰り返される一定の期間を集計（例：毎日12:00 ~ 12:59の売上）
	JLabel choiceLabel2 = new JLabel("繰り返される一定の期間を集計"); //繰り返される一定の期間を集計することを示すラベル
	JButton choiceButton2 = new JButton("選択");
	JComboBox RfirstYearComboBox = new JComboBox(); //何年からかを選択するボックス
	JLabel RyearUnitLabel1 = new JLabel("年 "); //「年」を表示するラベル
	JLabel RkaraLabel1 = new JLabel("  ～"); //「～」を表示するラベル
	JComboBox RlastYearComboBox = new JComboBox(); //何年までかを選択するボックス
	JLabel RyearUnitLabel2 = new JLabel("年 "); //「年」を表示するラベル
	JLabel RjoshiLabel1 = new JLabel("  の"); //「の」を表示するラベル
	
	JComboBox RfirstMonthComboBox = new JComboBox(); //何月からかを選択するボックス
	JLabel RmonthUnitLabel1 = new JLabel("月 "); //「月」を表示するラベル
	JLabel RkaraLabel2 = new JLabel("  ～"); //「～」を表示するラベル
	JComboBox RlastMonthComboBox = new JComboBox(); //何月までかを選択するボックス
	JLabel RmonthUnitLabel2 = new JLabel("月 "); //「月」を表示するラベル
	JLabel RjoshiLabel2 = new JLabel("  の"); //「の」を表示するラベル
	
	JComboBox RfirstDateComboBox = new JComboBox(); //何日からかを選択するボックス
	JLabel RdateUnitLabel1 = new JLabel("日 "); //「日」を表示するラベル
	JLabel RkaraLabel3 = new JLabel("  ～"); //「～」を表示するラベル
	JComboBox RlastDateComboBox = new JComboBox(); //何日までかを選択するボックス
	JLabel RdateUnitLabel2 = new JLabel("日 "); //「日」を表示するラベル
	JLabel RjoshiLabel3 = new JLabel("  の"); //「の」を表示するラベル
	
	JComboBox RfirstHourComboBox = new JComboBox(); //何時からかを選択するボックス
	JLabel RhourUnitLabel1 = new JLabel("時 "); //「時」を表示するラベル
	JLabel RkaraLabel4 = new JLabel("  ～"); //「～」を表示するラベル
	JComboBox RlastHourComboBox = new JComboBox(); //何時までかを選択するボックス
	JLabel RhourUnitLabel2 = new JLabel("時 "); //「時」を表示するラベル
		
	JComboBox RfirstMinuteComboBox = new JComboBox(); //何分からかを選択するボックス
	JLabel RminuteUnitLabel1 = new JLabel("分 "); //「分」を表示するラベル
	JComboBox RlastMinuteComboBox = new JComboBox(); //何分までかを選択するボックス
	JLabel RminuteUnitLabel2 = new JLabel("分 "); //「分」を表示するラベル
	
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
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel2_1 = new JPanel();
	JPanel panel2_2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel4_1 = new JPanel();
	JPanel panel4_2 = new JPanel();
	JPanel panel4_3 = new JPanel();
	JPanel panel4_4 = new JPanel();
	JPanel panel4_5 = new JPanel();
	JPanel panel4_6 = new JPanel();
	JPanel panel4_7 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	
	SalesSearchGUI(){
		setTitle("売上検索、集計");
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
		panel4_4.setLayout(new FlowLayout());
		panel4_5.setLayout(new FlowLayout());
		panel4_6.setLayout(new FlowLayout());
		panel4_7.setLayout(new FlowLayout());
		panel5.setLayout(new GridLayout(11, 9, 0, 2));
		panel6.setLayout(new FlowLayout());
		
		
		//日時を選択するコンボボックスに項目を追加
		//現在の日時で初期化
		for(int i = 2020; i <= Integer.parseInt(getYear()); i++) {
			yearComboBox.addItem(i);	
		}
		yearComboBox.setSelectedItem(Integer.parseInt(getYear()));
		for(int i = 1; i <= 12; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			monthComboBox.addItem(s);
		}
		monthComboBox.setSelectedItem(getMonth());
		for(int i = 1; i <= 31; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			dateComboBox.addItem(s);
		}
		dateComboBox.setSelectedItem(getDate());
		for(int i = 0; i <= 23; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			hourComboBox.addItem(s);
		}
		hourComboBox.setSelectedItem(getHour());
		for(int i = 0; i <= 59; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			minuteComboBox.addItem(s);
		}
		minuteComboBox.setSelectedItem(getMinute());
		timeRangeComboBox.addItem("以前");
		timeRangeComboBox.addItem("以後");
		timeRangeComboBox.addItem("一致");
		
		//店員コード、店員名を選択するコンボボックスに項目を追加
		try {
			SQL = "SELECT 店員コード, 氏名 FROM 店員マスタ WHERE 削除フラグ = 0;";
			stmt = LoginGUI.conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				clerkCodeComboBox.addItem(rs.getString("店員コード"));
				clerkNameComboBox.addItem(rs.getString("氏名"));
			}
			clerkCodeComboBox.setSelectedItem(null);
			clerkNameComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		
		//商品コード、商品名を選択するコンボボックスに項目を追加
		goodsCodeComboBox.addItem(null);
		goodsNameComboBox.addItem(null);
		try {
			SQL = "SELECT 商品コード, 商品名 FROM 商品マスタ;";
			stmt = LoginGUI.conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				goodsCodeComboBox.addItem(rs.getString("商品コード"));
				goodsNameComboBox.addItem(rs.getString("商品名"));
			}
			goodsCodeComboBox.setSelectedItem(null);
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
		try {
			SQL = "SELECT DATE_FORMAT(販売日時, '%Y') AS 時間 FROM 売上マスタ GROUP BY DATE_FORMAT(販売日時, '%Y');";
			stmt = LoginGUI.conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				firstYearComboBox.addItem(rs.getString("時間"));
			} 
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		firstYearComboBox.setSelectedIndex(0);
		//lastYearComboBoxに項目を追加
		try {
			SQL = "SELECT DATE_FORMAT(販売日時, '%Y') AS 時間 FROM 売上マスタ GROUP BY DATE_FORMAT(販売日時, '%Y');";
			stmt = LoginGUI.conn.createStatement();
			rs = stmt.executeQuery(SQL);
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
		for(int i = 1; i <= 12; i++) {
		String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		firstMonthComboBox.addItem(s);
		}		
		firstMonthComboBox.setSelectedItem("01");
		//lastMonthComboBoxに項目を追加
		for(int i = 1; i <= 12; i++) {
		String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		lastMonthComboBox.addItem(s);
		}
		lastMonthComboBox.setSelectedItem(getMonth());
		//firstDateComboBoxに項目を追加
		for(int i = 1; i <= 31; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		firstDateComboBox.addItem(s);
		}
		firstDateComboBox.setSelectedItem("01");
		//lastDateComboBoxに項目を追加
		for(int i = 1; i <= 31; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		lastDateComboBox.addItem(s);
		}
		lastDateComboBox.setSelectedItem(getDate());
		//firstHourComboBoxに項目を追加
		for(int i = 0; i <= 23; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			firstHourComboBox.addItem(s);
		}
		firstHourComboBox.setSelectedItem("00");
		//lastHourComboBoxに項目を追加
		for(int i = 0; i <= 23; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		lastHourComboBox.addItem(s);
		}
		lastHourComboBox.setSelectedItem(getHour());
		//firstMinuteComboBoxに項目を追加
		for(int i = 0; i <= 59; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			firstMinuteComboBox.addItem(s);
		}
		firstMinuteComboBox.setSelectedItem("00");
		//lastMinuteComboBoxに項目を追加
		for(int i = 0; i <= 59; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		lastMinuteComboBox.addItem(s);
		}
		lastMinuteComboBox.setSelectedItem(getMinute());
		
		//RfirstYearComboBoxに項目を追加
		RfirstYearComboBox.addItem("指定なし");
		try {
			SQL = "SELECT DATE_FORMAT(販売日時, '%Y') AS 時間 FROM 売上マスタ GROUP BY DATE_FORMAT(販売日時, '%Y');";
			stmt = LoginGUI.conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				RfirstYearComboBox.addItem(rs.getString("時間"));
			} 
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		RfirstYearComboBox.setSelectedIndex(1);
		//RlastYearComboBoxに項目を追加
		RlastYearComboBox.addItem("指定なし");
		try {
			SQL = "SELECT DATE_FORMAT(販売日時, '%Y') AS 時間 FROM 売上マスタ GROUP BY DATE_FORMAT(販売日時, '%Y');";
			stmt = LoginGUI.conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				RlastYearComboBox.addItem(rs.getString("時間"));
			} 
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		RlastYearComboBox.setSelectedItem(getYear());
		//RfirstMonthComboBoxに項目を追加
		RfirstMonthComboBox.addItem("指定なし");
		for(int i = 1; i <= 12; i++) {
		String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		RfirstMonthComboBox.addItem(s);
		}		
		RfirstMonthComboBox.setSelectedItem("01");
		//RlastMonthComboBoxに項目を追加
		RlastMonthComboBox.addItem("指定なし");
		for(int i = 1; i <= 12; i++) {
		String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		RlastMonthComboBox.addItem(s);
		}
		RlastMonthComboBox.setSelectedItem(getMonth());
		//RfirstDateComboBoxに項目を追加
		RfirstDateComboBox.addItem("指定なし");
		for(int i = 1; i <= 31; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		RfirstDateComboBox.addItem(s);
		}
		RfirstDateComboBox.setSelectedItem("01");
		//RlastDateComboBoxに項目を追加
		RlastDateComboBox.addItem("指定なし");
		for(int i = 1; i <= 31; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		RlastDateComboBox.addItem(s);
		}
		RlastDateComboBox.setSelectedItem(getDate());
		//RfirstHourComboBoxに項目を追加
		RfirstHourComboBox.addItem("指定なし");
		for(int i = 0; i <= 23; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			RfirstHourComboBox.addItem(s);
		}
		RfirstHourComboBox.setSelectedItem("00");
		//RlastHourComboBoxに項目を追加
		RlastHourComboBox.addItem("指定なし");
		for(int i = 0; i <= 23; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		RlastHourComboBox.addItem(s);
		}
		RlastHourComboBox.setSelectedItem(getHour());
		//RfirstMinuteComboBoxに項目を追加
		RfirstMinuteComboBox.addItem("指定なし");
		for(int i = 0; i <= 59; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
			RfirstMinuteComboBox.addItem(s);
		}
		RfirstMinuteComboBox.setSelectedItem("00");
		//RlastMinuteComboBoxに項目を追加
		RlastMinuteComboBox.addItem("指定なし");
		for(int i = 0; i <= 59; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = 0 + s;
			}
		RlastMinuteComboBox.addItem(s);
		}
		RlastMinuteComboBox.setSelectedItem(getMinute());
		
		//rangeComboBoxに項目を追加
		rangeComboBox.addItem("すべて");
		try {
			SQL = "SELECT CONCAT(LEFT(商品コード, 1), '群') AS 先頭コード FROM 商品マスタ GROUP BY 先頭コード;";
			stmt = LoginGUI.conn.createStatement();
			rs = stmt.executeQuery(SQL);
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
		panel3.add(releaseButton);
		
		panel4_1.add(aggregateLabel);
		
		panel4_2.add(choiceLabel1);
		panel4_2.add(choiceButton1);
		
		panel4_3.add(firstYearComboBox);
		panel4_3.add(yearUnitLabel1);
		panel4_3.add(firstMonthComboBox);
		panel4_3.add(monthUnitLabel1);
		panel4_3.add(firstDateComboBox);
		panel4_3.add(dateUnitLabel1);
		panel4_3.add(firstHourComboBox);
		panel4_3.add(hourUnitLabel1);
		panel4_3.add(firstMinuteComboBox);
		panel4_3.add(minuteUnitLabel1);
		
		panel4_3.add(karaLabel1);
		
		panel4_3.add(lastYearComboBox);
		panel4_3.add(yearUnitLabel2);
		panel4_3.add(lastMonthComboBox);
		panel4_3.add(monthUnitLabel2);
		panel4_3.add(lastDateComboBox);
		panel4_3.add(dateUnitLabel2);
		panel4_3.add(lastHourComboBox);
		panel4_3.add(hourUnitLabel2);
		panel4_3.add(lastMinuteComboBox);
		panel4_3.add(minuteUnitLabel2);
		
		panel4_4.add(choiceLabel2);
		panel4_4.add(choiceButton2);
		
		panel4_5.add(RfirstYearComboBox);
		panel4_5.add(RyearUnitLabel1);
		panel4_5.add(RkaraLabel1);
		panel4_5.add(RlastYearComboBox);
		panel4_5.add(RyearUnitLabel2);
		panel4_5.add(RjoshiLabel1);
		
		panel4_5.add(RfirstMonthComboBox);
		panel4_5.add(RmonthUnitLabel1);
		panel4_5.add(RkaraLabel2);
		panel4_5.add(RlastMonthComboBox);
		panel4_5.add(RmonthUnitLabel2);
		panel4_5.add(RjoshiLabel2);
		
		panel4_5.add(RfirstDateComboBox);
		panel4_5.add(RdateUnitLabel1);
		panel4_5.add(RkaraLabel3);
		panel4_5.add(RlastDateComboBox);
		panel4_5.add(RdateUnitLabel2);
		panel4_5.add(RjoshiLabel3);
		
		panel4_5.add(RfirstHourComboBox);
		panel4_5.add(RhourUnitLabel1);
		panel4_5.add(RfirstMinuteComboBox);
		panel4_5.add(RminuteUnitLabel1);
		panel4_5.add(RkaraLabel4);
		panel4_5.add(RlastHourComboBox);
		panel4_5.add(RhourUnitLabel2);
		panel4_5.add(RlastMinuteComboBox);
		panel4_5.add(RminuteUnitLabel2);
		
		panel4_6.add(rangeComboBox);
		panel4_6.add(joshiLabel5);
		panel4_6.add(objectComboBox1);
		panel4_6.add(joshiLabel6);
		panel4_6.add(objectComboBox2);
		
		panel4_7.add(aggregateButton);
		panel4_7.add(vacantLabel2);
		panel4_7.add(aggregateResultLabel);
		panel4_7.add(unitLabel);
		
		panel4.add(panel4_1);
		panel4.add(panel4_2);
		panel4.add(panel4_3);
		panel4.add(panel4_4);
		panel4.add(panel4_5);
		panel4.add(panel4_6);
		panel4.add(panel4_7);
		
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
		
		panel6.add(firstButton);
		panel6.add(previousButton);
		panel6.add(showNumberLabel);
		panel6.add(snLabel);
		panel6.add(totalNumberLabel);
		panel6.add(tnLabel);
		panel6.add(nextButton);
		panel6.add(lastButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		getContentPane().add(panel5);
		getContentPane().add(panel6);
		getContentPane().add(vacantLabel1);
		getContentPane().add(panel4);
		
		searchButton.addActionListener(this);
		releaseButton.addActionListener(this);
		nextButton.addActionListener(this);
		previousButton.addActionListener(this);
		nextButton.setEnabled(false);
		previousButton.setEnabled(false);
		firstButton.addActionListener(this);
		lastButton.addActionListener(this);
		
		choiceButton1.addActionListener(this);
		choiceButton2.addActionListener(this);
		RfirstYearComboBox.addActionListener(this);
		RfirstMonthComboBox.addActionListener(this);
		RfirstDateComboBox.addActionListener(this);
		RfirstHourComboBox.addActionListener(this);
		RfirstMinuteComboBox.addActionListener(this);
		RlastYearComboBox.addActionListener(this);
		RlastMonthComboBox.addActionListener(this);
		RlastDateComboBox.addActionListener(this);
		RlastHourComboBox.addActionListener(this);
		RlastMinuteComboBox.addActionListener(this);
		rangeComboBox.addActionListener(this);
		objectComboBox1.addActionListener(this);
		objectComboBox2.addActionListener(this);
		
		aggregateButton.addActionListener(this);
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
			numberTextField.setText(null);
			yearComboBox.setSelectedItem(getYear());
			monthComboBox.setSelectedItem(getMonth());
			dateComboBox.setSelectedItem(getDate());
			timeRangeComboBox.setSelectedItem("以前");
			clerkCodeComboBox.setSelectedItem(null);
			clerkNameComboBox.setSelectedItem(null);
			goodsCodeComboBox.setSelectedItem(null);
			goodsNameComboBox.setSelectedItem(null);
			totalTextField.setText("0");
			totalRangeComboBox.setSelectedItem("以上");
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
				rs.absolute(now); //nowへ行を移動する
				result(); //検索結果を表示
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} 
		//「最初へ」ボタンを押した場合
		else if(e.getSource() == firstButton) { 
			try {
				rs.beforeFirst(); //先頭行のひとつ前まで戻る
				result(); //1件目から表示
				previousButton.setEnabled(false);
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
		//「ある期間のみ集計」を選択したとき
		else if(e.getSource() == choiceButton1) {
			if(choiceButton1.getText().equals("選択")) {
				choiceButton1.setText("選択中");
				choiceButton2.setText("選択");
			}
		}
		//「繰り返される一定の期間を集計」を選択したとき
		else if(e.getSource() == choiceButton2) {
			if(choiceButton2.getText().equals("選択")) {
				choiceButton2.setText("選択中");
				choiceButton1.setText("選択");
			}
		}
		//「集計」ボタンを押したとき
		else if(e.getSource() == aggregateButton) {
			//集計するためのSQLを作成する
			SQL = createAggregateSQL(firstYearComboBox, lastYearComboBox, firstMonthComboBox, lastMonthComboBox, 
					firstDateComboBox, lastDateComboBox, firstHourComboBox, lastHourComboBox, firstMinuteComboBox, 
					lastMinuteComboBox, rangeComboBox, objectComboBox1, objectComboBox2);
			System.out.println(SQL);
			try {
				otherStmt = LoginGUI.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				otherRs = otherStmt.executeQuery(SQL);
				if(otherRs.next()) {
					if(otherRs.getString("集計結果") == null) {
						aggregateResultLabel.setText("0");
					} else {
						aggregateResultLabel.setText(otherRs.getString("集計結果"));
					}
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		} 
		//objectComboBox1の選んだ項目によってobjectComboBox2とrangeComboBoxの項目を変更
		else if(e.getSource() == objectComboBox1) {
			switch((String)objectComboBox1.getSelectedItem()) {
			//「商品」で集計を選んだ場合
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
					//商品コードの頭文字を取得し、「〇群」として集計する際に使用
					//例：A001～A003の商品を「A群」としてまとめて集計
					SQL = "SELECT CONCAT(LEFT(商品コード, 1), '群') AS 先頭コード FROM 商品マスタ GROUP BY 先頭コード;";
					otherStmt = LoginGUI.conn.createStatement();
					otherRs = otherStmt.executeQuery(SQL);
					while(otherRs.next()){
						rangeComboBox.addItem(otherRs.getString("先頭コード"));
					}
					//通常の商品コードをコンボボックスに追加
					SQL = "SELECT 商品コード FROM 商品マスタ;";
					otherRs = otherStmt.executeQuery(SQL);
					while(otherRs.next()){
						rangeComboBox.addItem(otherRs.getString("商品コード"));
					} 
				}catch(SQLException e2) {
					e2.printStackTrace();
				}catch(Exception e2) {
					e2.printStackTrace();
				}
				break;
			//「店員」で集計を選んだ場合
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
					//店員コードをコンボボックスに追加
					SQL = "SELECT 店員コード FROM 店員マスタ;";
					otherStmt = LoginGUI.conn.createStatement();
					otherRs = otherStmt.executeQuery(SQL);
					otherRs = otherStmt.executeQuery(SQL);
					while(otherRs.next()){
						rangeComboBox.addItem(otherRs.getString("店員コード"));
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
					//伝票番号をコンボボックスに追加
					SQL = "SELECT DISTINCT(伝票番号) AS 伝票番号 FROM 売上マスタ;";
					otherStmt = LoginGUI.conn.createStatement();
					otherRs = otherStmt.executeQuery(SQL);
					otherRs = otherStmt.executeQuery(SQL);
					while(otherRs.next()){
						rangeComboBox.addItem(otherRs.getString("伝票番号"));
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
	
	//検索条件からデータを取得するメソッド
	public void getData(){
		SQL = createSQL();
		System.out.println(SQL + " で検索します");
		try {
			stmt = LoginGUI.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(SQL);
			rs.last(); //最後の行に移動し、行番号を取得
			last = rs.getRow();
			rs.beforeFirst(); //初めの行に戻る
			result(); //検索結果を表示
			totalNumberLabel.setText(Integer.toString(last)); //総件数を表示
			this.pack(); //フレームのサイズ調整
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
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
				nextButton.setEnabled(true); //一番下の行にデータがあれば「次へ」ボタンをtrueにする
			}else {
				reset(numberLabel10, dateLabel10, clerkCodeLabel10, clerkNameLabel10, 
						goodsCodeLabel10, goodsNameLabel10, countLabel10, subTotalLabel10, flagLabel10);
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
	
	//集計のためのSQLを作成するメソッド
	public String createAggregateSQL(JComboBox fyBox, JComboBox lyBox, JComboBox fmBox, JComboBox lmBox, 
			JComboBox fdBox, JComboBox ldBox, JComboBox fhBox, JComboBox lhBox, JComboBox fmiBox, JComboBox lmiBox,
			JComboBox rBox, JComboBox oBox1, JComboBox oBox2) {
		
		//returnで返す文字列
		String str = ""; 
	
		//時間の条件を指定するSQLを作成する
		timeRangeSQL();
		System.out.println("timeSQL : " + timeSQL);
		
		//objectComboBox1が「商品」「店員」「伝票」のいずれかで場合分け
		switch((String)oBox1.getSelectedItem()) {
		//「商品」の場合
		case "商品": 
			//objectComboBox2が「売上額」「売上件数」「売上個数」のいずれかで場合分け
			switch((String)oBox2.getSelectedItem()) {
			case "売上額":
				if(rBox.getSelectedItem().equals("すべて")) {
					//「すべて」の「商品」の「売上額」に応じた条件を指定するSQL
					selectSQL += " SUM(小計)";
				} else {
					if(((String)rBox.getSelectedItem()).contains("群")) {
						//「〇群」の「商品」の「売上額」に応じた条件を指定するSQL
						selectSQL += " SUM(小計)";
						subSelectSQL += " ,LEFT(商品コード, 1) AS 先頭コード";
						whereSQL += " WHERE 先頭コード = '" + ((String)rBox.getSelectedItem()).replace("群", "") + "'";
					} else {
						//「特定の商品コード」の「商品」の「売上額」に応じた条件を指定するSQL
						selectSQL += " SUM(小計)";
						filterSQL += " AND 商品コード = '" + rangeComboBox.getSelectedItem() + "'";
					}
				}
				unitLabel.setText("円");
				break;
			case "売上件数":
				if(rBox.getSelectedItem().equals("すべて")) {
					//「すべて」の「商品」の「売上件数」に応じた条件を指定するSQL
					selectSQL += " COUNT(伝票番号)";
				} else {
					if(((String)rBox.getSelectedItem()).contains("群")) {
						//「〇群」の「商品」の「売上件数」に応じた条件を指定するSQL
						selectSQL += " COUNT(伝票番号)";
						subSelectSQL += " ,LEFT(商品コード, 1) AS 先頭コード";
						whereSQL += " WHERE 先頭コード = '" + ((String)rBox.getSelectedItem()).replace("群", "") + "'";
					} else {
						//「特定の商品コード」の「商品」の「売上件数」に応じた条件を指定するSQL
						selectSQL += " COUNT(伝票番号)";
						filterSQL += " AND 商品コード = '" + rangeComboBox.getSelectedItem() + "'";
					}
				}
				unitLabel.setText("件");
				break;
			case "売上個数":
				if(rBox.getSelectedItem().equals("すべて")) {
					//「すべて」の「商品」の「売上個数」に応じた条件を指定するSQL
					selectSQL += " SUM(個数)";
				} else {
					if(((String)rBox.getSelectedItem()).contains("群")) {
						//「〇群」の「商品」の「売上個数」に応じた条件を指定するSQL
						selectSQL += " SUM(個数)";
						subSelectSQL += " ,LEFT(商品コード, 1) AS 先頭コード";
						whereSQL += " WHERE 先頭コード = '" + ((String)rBox.getSelectedItem()).replace("群", "") + "'";
					} else {
						//「特定の商品コード」の「商品」の「売上個数」に応じた条件を指定するSQL
						selectSQL += " SUM(個数)";
						filterSQL += " AND 商品コード = '" + rangeComboBox.getSelectedItem() + "'";
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
					//「すべて」の「店員」の「売上額」に応じた条件を指定するSQL
					selectSQL += " SUM(小計)";
				} else {
					//「特定の店員コード」の「店員」の「売上額」に応じた条件を指定するSQL
					selectSQL += " SUM(小計)";
					filterSQL += " AND 店員コード = '" + rangeComboBox.getSelectedItem() + "'";
				}
				unitLabel.setText("円");
				break;
			case "売上件数":
				if(rBox.getSelectedItem().equals("すべて")) {
					//「すべて」の「店員」の「売上件数」に応じた条件を指定するSQL
					selectSQL += " COUNT(伝票番号)";
					groupBySQL += " GROUP BY 伝票番号";
				} else {
					//「特定の店員コード」の「店員」の「売上件数」に応じた条件を指定するSQL
					selectSQL += " COUNT(伝票番号)";
					filterSQL += " AND 店員コード = '" + rangeComboBox.getSelectedItem() + "'";
					groupBySQL += " GROUP BY 伝票番号";
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
					//「すべて」の「伝票」の「売上額」に応じた条件を指定するSQL
					selectSQL += " SUM(小計)";
				} else {
					//「特定の伝票番号」の「伝票」の「売上額」に応じた条件を指定するSQL
					selectSQL += " SUM(小計)";
					filterSQL += " AND 伝票番号 = " + rangeComboBox.getSelectedItem();
				}
				unitLabel.setText("円");
				break;
			case "件数":
				if(rBox.getSelectedItem().equals("すべて")) {
					//「すべて」の「伝票」の「件数」に応じた条件を指定するSQL
					selectSQL += " COUNT(伝票番号)";
					groupBySQL += " GROUP BY 伝票番号";
				} else {
					//「特定の伝票番号」の「伝票」の「件数」に応じた条件を指定するSQL
					selectSQL += " COUNT(伝票番号)";
					filterSQL += " AND 伝票番号 = " + rangeComboBox.getSelectedItem();
					groupBySQL += " GROUP BY 伝票番号";
				}
				unitLabel.setText("件");
			}
		}
		//最終的なSQL文
		str = selectSQL + " AS 集計結果"
			+ " FROM (" + subSelectSQL + " FROM 売上マスタ WHERE 削除フラグ = 0" + timeSQL + filterSQL + groupBySQL + ") T"
			+ whereSQL + ";";  
		//SQLをリセットする
		filterSQL = ""; 
		filterSQL = "";
		selectSQL = "SELECT";
		subSelectSQL = "SELECT *"; 
		timeSQL = ""; 
		groupBySQL = "";
		whereSQL = "";
		return str;
	}
	
	//時間についてのSQL文を作成するメソッド
	public void timeRangeSQL() {
		//ある特定の期間を集計する場合
		if(choiceButton1.getText().equals("選択中")){ 
			timeSQL += " AND 販売日時 >= '" + firstYearComboBox.getSelectedItem() + "-" + 
			firstMonthComboBox.getSelectedItem() + "-" + firstDateComboBox.getSelectedItem() + " " + 
			firstHourComboBox.getSelectedItem() + ":" + firstMinuteComboBox.getSelectedItem() + "'";
			
			timeSQL += " AND 販売日時 <= '" + lastYearComboBox.getSelectedItem() + "-" + 
			lastMonthComboBox.getSelectedItem() + "-" + lastDateComboBox.getSelectedItem() + " " + 
			lastHourComboBox.getSelectedItem() + ":" + lastMinuteComboBox.getSelectedItem() + "'";
		} 
		//繰り返される一定の期間を集計する場合
		else if(choiceButton2.getText().equals("選択中")) { 
			//年で抽出する
			//どちらのコンボボックスも「指定なし」でない場合のみ、条件に追加
			if(!(RfirstYearComboBox.getSelectedItem().equals("指定なし")||
					RlastYearComboBox.getSelectedItem().equals("指定なし"))) {
				timeSQL += " AND DATE_FORMAT(販売日時, '%Y') >= " + RfirstYearComboBox.getSelectedItem() 
				+ " AND DATE_FORMAT(販売日時, '%Y') <= " + RlastYearComboBox.getSelectedItem() ;
			}
			//月で抽出する
			//どちらのコンボボックスも「指定なし」でない場合のみ、条件に追加
			//なお、mをM(大文字)にするとjanuaryという文字列で抽出されるので注意
			if(!(RfirstMonthComboBox.getSelectedItem().equals("指定なし")||
					RlastMonthComboBox.getSelectedItem().equals("指定なし"))) {
				timeSQL += " AND DATE_FORMAT(販売日時, '%m') >= " + RfirstMonthComboBox.getSelectedItem() 
				+ " AND DATE_FORMAT(販売日時, '%m') <= " + RlastMonthComboBox.getSelectedItem() ;
			}
			//日付で抽出する
			//どちらのコンボボックスも「指定なし」でない場合のみ、条件に追加
			if(!(RfirstDateComboBox.getSelectedItem().equals("指定なし")||
					RlastDateComboBox.getSelectedItem().equals("指定なし"))) {
				timeSQL += " AND DATE_FORMAT(販売日時, '%d') >= " + RfirstDateComboBox.getSelectedItem() 
				+ " AND DATE_FORMAT(販売日時, '%d') <= " + RlastDateComboBox.getSelectedItem() ;
			}
			//時刻で抽出する
			//どちらのコンボボックスも「指定なし」でない場合のみ、条件に追加
			//なお、%m or ％Mだと月で抽出される。また、12時から13時だと<=にしている関係から13時台も抽出してしまう
			//よって時と分はセットにする
			if(!(RfirstHourComboBox.getSelectedItem().equals("指定なし")||
					RlastHourComboBox.getSelectedItem().equals("指定なし")||
					RfirstMinuteComboBox.getSelectedItem().equals("指定なし")||
					RlastMinuteComboBox.getSelectedItem().equals("指定なし"))) {
				timeSQL += " AND TIME(販売日時) BETWEEN '" + RfirstHourComboBox.getSelectedItem() + ":" + 
				RfirstMinuteComboBox.getSelectedItem() + "' AND '"+ RlastHourComboBox.getSelectedItem() + ":" + 
				RlastMinuteComboBox.getSelectedItem() + "'";
			}
		}
		System.out.println("timeSQL : " + timeSQL);
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
