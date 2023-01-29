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

public class ClerkSearchGUI extends JFrame implements ActionListener{
	
	JLabel filterLabel = new JLabel("絞り込み条件");//絞り込み条件の入力欄であることを示すラベル
	
	//絞り込み条件の入力欄
	JLabel fcLabel = new JLabel("  店員コード"); //店員コードの選択欄であることを示すラベル
	JComboBox codeComboBox = new JComboBox(); //店員コードの選択欄 
	
	JLabel fnLabel = new JLabel("  店員名"); //店員名の選択欄であることを示すラベル
	JComboBox nameComboBox = new JComboBox(); //店員名の選択欄
	
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
	
	//データベースからデータを取得する際に使用
	String SQL;
	String filterSQL = "";
	String selectSQL = "SELECT *"; 
	Statement stmt; 
	ResultSet rs; 
	Statement otherStmt; 
	ResultSet otherRs;
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	
	ClerkSearchGUI(){
		setTitle("店員検索");
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
			SQL = "SELECT 店員コード, 氏名 FROM 店員マスタ;";
			stmt = LoginGUI.conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				codeComboBox.addItem(rs.getString("店員コード"));
				nameComboBox.addItem(rs.getString("氏名"));
			}
			codeComboBox.setSelectedItem(null);
			nameComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}

		//性別を選択するコンボボックスに項目を追加
		sexComboBox.addItem(null);
		sexComboBox.addItem("男性");
		sexComboBox.addItem("女性");
		
		//生年月日を選択するコンボボックスに項目を追加
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
		panel4.add(bLabel);
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
		
		setColor1(cLabel);
		setColor1(nLabel);
		setColor1(sLabel);
		setColor1(bLabel);
		setColor1(fLabel);
		
		setColor2(codeLabel2);
		setColor2(nameLabel2);
		setColor2(sexLabel2);
		setColor2(birthdayLabel2);
		setColor2(flagLabel2);
		setColor2(codeLabel4);
		setColor2(nameLabel4);
		setColor2(sexLabel4);
		setColor2(birthdayLabel4);
		setColor2(flagLabel4);
		setColor2(codeLabel6);
		setColor2(nameLabel6);
		setColor2(sexLabel6);
		setColor2(birthdayLabel6);
		setColor2(flagLabel6);
		setColor2(codeLabel8);
		setColor2(nameLabel8);
		setColor2(sexLabel8);
		setColor2(birthdayLabel8);
		setColor2(flagLabel8);
		setColor2(codeLabel10);
		setColor2(nameLabel10);
		setColor2(sexLabel10);
		setColor2(birthdayLabel10);
		setColor2(flagLabel10);
		
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
		//「絞り込み」ボタンを押したとき
		if(e.getSource() == searchButton) {
			getData();
		} 
		//「絞り込み解除」ボタンを押したとき
		else if(e.getSource() == releaseButton) {
			codeComboBox.setSelectedItem(null);
			nameComboBox.setSelectedItem(null);
			sexComboBox.setSelectedItem(null);
			yearComboBox.setSelectedItem(getYear());
			monthComboBox.setSelectedItem(getMonth());
			dateComboBox.setSelectedItem(getDate());
			timeRangeComboBox.setSelectedItem("以前");
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
		//店員コードを選択したとき、店員マスタから店員名を取得する
		else if(e.getSource() == codeComboBox) {
			try {
				SQL = "SELECT 氏名 FROM 店員マスタ WHERE 店員コード = '" + codeComboBox.getSelectedItem() + "';";
				otherStmt = LoginGUI.conn.createStatement();
				otherRs = otherStmt.executeQuery(SQL);
				while(otherRs.next()){
					nameComboBox.setSelectedItem(otherRs.getString("氏名"));
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		//店員名を選択したとき、店員コードを取得する
		else if(e.getSource() == nameComboBox) {
			try {
				SQL = "SELECT 店員コード FROM 店員マスタ WHERE 氏名 = '" + nameComboBox.getSelectedItem() + "';";
				otherStmt = LoginGUI.conn.createStatement();
				otherRs = otherStmt.executeQuery(SQL);
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
			stmt = LoginGUI.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(SQL);
			rs.last(); //最後の行に移動し、行番号を取得
			last = rs.getRow();
			rs.beforeFirst(); //初めの行に戻る
			result(); //検索結果を表示
			this.pack(); //フレームのサイズ調整
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
			filterSQL += " AND 店員コード = '" + codeComboBox.getSelectedItem() + "'";
		}
		if(nameComboBox.getSelectedItem() != null) {
			filterSQL += " AND 氏名 = '" + nameComboBox.getSelectedItem() + "'";
		}
		if(sexComboBox.getSelectedItem() != null) {
			filterSQL += " AND 性別 = '" + sexComboBox.getSelectedItem() + "'"; 
		}
		if(timeRangeComboBox.getSelectedItem().equals("以前")) {
			filterSQL += " AND 生年月日 <= '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() +
					"-" + dateComboBox.getSelectedItem() + "'";
		}else if(timeRangeComboBox.getSelectedItem().equals("以後")) {
			filterSQL += " AND 生年月日 >= '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() +
					"-" + dateComboBox.getSelectedItem() + "'";
		}else if(timeRangeComboBox.getSelectedItem().equals("一致")) {
			filterSQL += " AND 生年月日 = '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + 
					"-" + dateComboBox.getSelectedItem() + "'";
		}
		if(flagComboBox.getSelectedItem() != null) {
			filterSQL += " AND 削除フラグ = '" + flagComboBox.getSelectedItem() + "'";
		}
		String str = "SELECT * FROM 店員マスタ WHERE 1" + filterSQL + ";";
		filterSQL = ""; //filterSQLをリセットする
		return str;
	}

	
	
	//それぞれのラベルに検索結果を表示するメソッド
	public void result() {
		try {
			if(rs.next()){
				show(codeLabel1, nameLabel1, sexLabel1, birthdayLabel1, flagLabel1);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel1, nameLabel1, sexLabel1, birthdayLabel1, flagLabel1);
			}
			if(rs.next()){
				show(codeLabel2, nameLabel2, sexLabel2, birthdayLabel2, flagLabel2);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel2, nameLabel2, sexLabel2, birthdayLabel2, flagLabel2);
			}
			if(rs.next()){
				show(codeLabel3, nameLabel3,sexLabel3, birthdayLabel3, flagLabel3);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel3, nameLabel3, sexLabel3, birthdayLabel3, flagLabel3);
			}
			if(rs.next()){
				show(codeLabel4, nameLabel4, sexLabel4, birthdayLabel4, flagLabel4);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel4, nameLabel4, sexLabel4, birthdayLabel4, flagLabel4);
			}
			if(rs.next()){
				show(codeLabel5, nameLabel5, sexLabel5, birthdayLabel5, flagLabel5);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel5, nameLabel5, sexLabel5, birthdayLabel5, flagLabel5);
			}
			if(rs.next()){
				show(codeLabel6, nameLabel6, sexLabel6, birthdayLabel6, flagLabel6);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel6, nameLabel6, sexLabel6, birthdayLabel6, flagLabel6);
			}
			if(rs.next()){
				show(codeLabel7, nameLabel7, sexLabel7, birthdayLabel7, flagLabel7);	
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel7, nameLabel7, sexLabel7, birthdayLabel7, flagLabel7);
			}
			if(rs.next()){
				show(codeLabel8, nameLabel8, sexLabel8, birthdayLabel8, flagLabel8);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel8, nameLabel8, sexLabel8, birthdayLabel8, flagLabel8);
			}
			if(rs.next()){
				show(codeLabel9, nameLabel9, sexLabel9, birthdayLabel9, flagLabel9);
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeLabel9, nameLabel9, sexLabel9, birthdayLabel9, flagLabel9);
			}
			if(rs.next()){
				show(codeLabel10, nameLabel10,sexLabel10, birthdayLabel10, flagLabel10);
				now = rs.getRow(); //現在の行番号を取得
				nextButton.setEnabled(true); //一番下の行にデータがあれば「次へ」ボタンをtrueにする
			}else {
				reset(codeLabel10, nameLabel10, sexLabel10, birthdayLabel10, flagLabel10);
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
	public void show(JLabel codeLabel, JLabel nameLabel, JLabel sexLabel, JLabel birthdayLabel, JLabel flagLabel) {
		try {
			codeLabel.setText(rs.getString("店員コード"));
			nameLabel.setText(rs.getString("氏名"));
			sexLabel.setText(rs.getString("性別"));
			birthdayLabel.setText(rs.getString("生年月日"));
			flagLabel.setText(rs.getString("削除フラグ"));
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullならばラベルを白紙にするメソッド
	public void reset(JLabel codeLabel, JLabel nameLabel, JLabel sexLabel, JLabel birthdayLabel, JLabel flagLabel) {
		codeLabel.setText(null);
		nameLabel.setText(null);
		sexLabel.setText(null);
		birthdayLabel.setText(null);
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
