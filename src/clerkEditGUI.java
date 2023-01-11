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

public class clerkEditGUI extends JFrame implements ActionListener{
	
	JLabel filterLabel = new JLabel("絞り込み条件");//絞り込み条件の入力欄であることを示すラベル
	
	//絞り込み条件の入力欄
	JLabel fcLabel = new JLabel("店員コード"); //店員コードの選択欄であることを示すラベル
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
	
	JComboBox timeRangeComboBox = new JComboBox(); //選択した日時より以前か等を選択するボックス
	
	JLabel ffLabel = new JLabel("  削除フラグ"); //削除フラグの選択欄であることを示すラベル
	JComboBox flagComboBox = new JComboBox(); //削除フラグの選択欄
	
	JLabel voidLabel1 = new JLabel();
	JLabel voidLabel2 = new JLabel();
	
	JButton searchButton = new JButton("絞り込み"); //検索ボタン
	
	JLabel cLabel = new JLabel("店員コード", JLabel.CENTER); //店員コードであることを示すラベル
	JTextField codeTextField1 = new JTextField(); //店員コードを編集するテキストフィールド
	JTextField codeTextField2 = new JTextField();
	JTextField codeTextField3 = new JTextField();
	JTextField codeTextField4 = new JTextField();
	JTextField codeTextField5 = new JTextField();
	JTextField codeTextField6 = new JTextField();
	JTextField codeTextField7 = new JTextField();
	JTextField codeTextField8 = new JTextField();
	JTextField codeTextField9 = new JTextField();
	JTextField codeTextField10 = new JTextField();
	
	JLabel nLabel = new JLabel("店員名", JLabel.CENTER); //店員名であることを示すラベル
	JTextField nameTextField1 = new JTextField(); //店員名を編集するテキストフィールド
	JTextField nameTextField2 = new JTextField();
	JTextField nameTextField3 = new JTextField();
	JTextField nameTextField4 = new JTextField();
	JTextField nameTextField5 = new JTextField();
	JTextField nameTextField6 = new JTextField();
	JTextField nameTextField7 = new JTextField();
	JTextField nameTextField8 = new JTextField();
	JTextField nameTextField9 = new JTextField();
	JTextField nameTextField10 = new JTextField();
	
	JLabel sLabel = new JLabel("性別", JLabel.CENTER); //性別であることを示すラベル
	JComboBox sexComboBox1 = new JComboBox(); //性別を選択するコンボボックス
	JComboBox sexComboBox2 = new JComboBox();
	JComboBox sexComboBox3 = new JComboBox();
	JComboBox sexComboBox4 = new JComboBox();
	JComboBox sexComboBox5 = new JComboBox();
	JComboBox sexComboBox6 = new JComboBox();
	JComboBox sexComboBox7 = new JComboBox();
	JComboBox sexComboBox8 = new JComboBox();
	JComboBox sexComboBox9 = new JComboBox();
	JComboBox sexComboBox10 = new JComboBox();
	
	JLabel bLabel = new JLabel("生年月日", JLabel.CENTER); //生年月日であることを示すラベル
	JTextField birthdayTextField1 = new JTextField(); //生年月日を編集するテキストフィールド
	JTextField birthdayTextField2 = new JTextField();
	JTextField birthdayTextField3 = new JTextField();
	JTextField birthdayTextField4 = new JTextField();
	JTextField birthdayTextField5 = new JTextField();
	JTextField birthdayTextField6 = new JTextField();
	JTextField birthdayTextField7 = new JTextField();
	JTextField birthdayTextField8 = new JTextField();
	JTextField birthdayTextField9 = new JTextField();
	JTextField birthdayTextField10 = new JTextField();
	
	
	JLabel fLabel = new JLabel("削除フラグ", JLabel.CENTER); //削除フラグであることを示すラベル
	JComboBox flagComboBox1 = new JComboBox(); //削除フラグを選択するコンボボックス
	JComboBox flagComboBox2 = new JComboBox();
	JComboBox flagComboBox3 = new JComboBox();
	JComboBox flagComboBox4 = new JComboBox();
	JComboBox flagComboBox5 = new JComboBox();
	JComboBox flagComboBox6 = new JComboBox();
	JComboBox flagComboBox7 = new JComboBox();
	JComboBox flagComboBox8 = new JComboBox();
	JComboBox flagComboBox9 = new JComboBox();
	JComboBox flagComboBox10 = new JComboBox();
	
	JButton editButton1= new JButton("編集");//当該行を編集するボタン
	JButton editButton2= new JButton("編集");
	JButton editButton3= new JButton("編集");
	JButton editButton4= new JButton("編集");
	JButton editButton5= new JButton("編集");
	JButton editButton6= new JButton("編集");
	JButton editButton7= new JButton("編集");
	JButton editButton8= new JButton("編集");
	JButton editButton9= new JButton("編集");
	JButton editButton10= new JButton("編集");
	
	JButton updateButton1= new JButton("更新");//編集を反映するボタン
	JButton updateButton2= new JButton("更新");
	JButton updateButton3= new JButton("更新");
	JButton updateButton4= new JButton("更新");
	JButton updateButton5= new JButton("更新");
	JButton updateButton6= new JButton("更新");
	JButton updateButton7= new JButton("更新");
	JButton updateButton8= new JButton("更新");
	JButton updateButton9= new JButton("更新");
	JButton updateButton10= new JButton("更新");
	
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
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel15 = new JPanel();
	
	
	
	
	clerkEditGUI(){
		setTitle("店員マスタ編集");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//レイアウト設定
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new GridLayout(11, 7, 0, 2));
		panel15.setLayout(new FlowLayout());
		
		//絞り込みをする際の条件を設定するコンポーネントの設定
		//店員コードを選択するコンボボックスに項目を追加
		codeComboBox.addItem(null);
		try {
			SQL = "SELECT 店員コード FROM 店員マスタ;";
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				codeComboBox.addItem(rs.getString("店員コード"));
			}
			codeComboBox.setSelectedItem(null);
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
		//店員名を選択するコンボボックスに項目を追加
		nameComboBox.addItem(null);
		try {
			SQL = "SELECT 氏名 FROM 店員マスタ;";
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				nameComboBox.addItem(rs.getString("氏名"));
			}
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
		codeComboBox.setSelectedItem(null);
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
			if(s.equals(getMonth())){
				monthComboBox.setSelectedIndex(i-1);
			}
		}
		timeRangeComboBox.addItem("以前");
		timeRangeComboBox.addItem("以後");
		timeRangeComboBox.addItem("一致");
		//削除フラグを選択するコンボボックスに項目を追加
		flagComboBox.addItem(null);
		flagComboBox.addItem("0");
		flagComboBox.addItem("1");
		codeComboBox.setSelectedItem(null);
		
		//ラベルの色分け設定
//		setColor1(cLabel);
//		setColor1(nLabel);
//
//		setColor2(codeLabel2);
//		setColor2(nameLabel2);
//
//		setColor2(codeLabel4);
//		setColor2(nameLabel4);
//
//		setColor2(codeLabel6);
//		setColor2(nameLabel6);
//
//		setColor2(codeLabel8);
//		setColor2(nameLabel8);
//		
//		setColor2(codeLabel10);
//		setColor2(nameLabel10);
		
		panel1.add(filterLabel);
		
		panel2.add(fcLabel);
		panel2.add(codeComboBox);
		panel2.add(fnLabel);
		panel2.add(nameComboBox);
		panel2.add(fsLabel);
		panel2.add(sexComboBox);
		panel2.add(fbLabel);
		panel2.add(fyLabel);
		panel2.add(yearComboBox);
		panel2.add(fmLabel);
		panel2.add(monthComboBox);
		panel2.add(timeRangeComboBox);
		panel2.add(ffLabel);
		panel2.add(flagComboBox);
		
		panel3.add(searchButton);
		
		panel4.add(cLabel);
		panel4.add(nLabel);
		panel4.add(sLabel);
		panel4.add(bLabel);
		panel4.add(fLabel);
		panel4.add(voidLabel1);
		panel4.add(voidLabel2);

		panel4.add(codeTextField1);
		panel4.add(nameTextField1);
		panel4.add(sexComboBox1);
		panel4.add(birthdayTextField1);
		panel4.add(flagComboBox1);
		panel4.add(editButton1);
		panel4.add(updateButton1);
		
		panel4.add(codeTextField2);
		panel4.add(nameTextField2);
		panel4.add(sexComboBox2);
		panel4.add(birthdayTextField2);
		panel4.add(flagComboBox2);
		panel4.add(editButton2);
		panel4.add(updateButton2);
		
		panel4.add(codeTextField3);
		panel4.add(nameTextField3);
		panel4.add(sexComboBox3);
		panel4.add(birthdayTextField3);
		panel4.add(flagComboBox3);
		panel4.add(editButton3);
		panel4.add(updateButton3);
		
		panel4.add(codeTextField4);
		panel4.add(nameTextField4);
		panel4.add(sexComboBox4);
		panel4.add(birthdayTextField4);
		panel4.add(flagComboBox4);
		panel4.add(editButton4);
		panel4.add(updateButton4);
		
		panel4.add(codeTextField5);
		panel4.add(nameTextField5);
		panel4.add(sexComboBox5);
		panel4.add(birthdayTextField5);
		panel4.add(flagComboBox5);
		panel4.add(editButton5);
		panel4.add(updateButton5);
		
		panel4.add(codeTextField6);
		panel4.add(nameTextField6);
		panel4.add(sexComboBox6);
		panel4.add(birthdayTextField6);
		panel4.add(flagComboBox6);
		panel4.add(editButton6);
		panel4.add(updateButton6);
		
		panel4.add(codeTextField7);
		panel4.add(nameTextField7);
		panel4.add(sexComboBox7);
		panel4.add(birthdayTextField7);
		panel4.add(flagComboBox7);
		panel4.add(editButton7);
		panel4.add(updateButton7);
		
		panel4.add(codeTextField8);
		panel4.add(nameTextField8);
		panel4.add(sexComboBox8);
		panel4.add(birthdayTextField8);
		panel4.add(flagComboBox8);
		panel4.add(editButton8);
		panel4.add(updateButton8);
		
		panel4.add(codeTextField9);
		panel4.add(nameTextField9);
		panel4.add(sexComboBox9);
		panel4.add(birthdayTextField9);
		panel4.add(flagComboBox9);
		panel4.add(editButton9);
		panel4.add(updateButton9);
		
		panel4.add(codeTextField10);
		panel4.add(nameTextField10);
		panel4.add(sexComboBox10);
		panel4.add(birthdayTextField10);
		panel4.add(flagComboBox10);
		panel4.add(editButton10);
		panel4.add(updateButton10);
		
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
			System.out.println(SQL + " で絞り込みます");
			try {
				conn = DriverManager.getConnection(URL, USER, PASS);
				stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				rs = stmt.executeQuery(SQL);
//				result(); //検索結果を表示
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
//				result(); //検索結果を表示
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
//				result(); //検索結果を表示
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
//	public void setColor1(JLabel label) {
//		label.setOpaque(true);
//		label.setBackground(new Color(174,224,228));
//	}
//	public void setColor2(JLabel label) {
//		label.setOpaque(true);
//		label.setBackground(new Color(196,206,222));
//	}
	
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
			filterSQL += " AND 生年月日 <= '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + "'";
		}else if(timeRangeComboBox.getSelectedItem().equals("以後")) {
			filterSQL += " AND 販売日時 >= '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + "'";
		}else if(timeRangeComboBox.getSelectedItem().equals("一致")) {
			filterSQL += " AND 販売日時 = '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + "'";
		}
		if(flagComboBox.getSelectedItem() != null) {
			filterSQL += " AND 削除フラグ = '" + flagComboBox.getSelectedItem() + "'"; 
		}
		String str = "SELECT * "
				+ "FROM 店員マスタ "
				+ "WHERE 1" + filterSQL + 
				" ORDER BY 店員コード ASC;";
		filterSQL = ""; //filterSQLをリセットする
		return str;
	}
	
	//それぞれのラベルに検索結果を表示するメソッド
//	public void result() {
//		try {
//			if(rs.next()){
//				show(codeTextField1, nameTextField1, sexComboBox1, birthdayTextField1, flagComboBox1);
//				now = rs.getRow(); //現在の行番号を取得
//			}else {
//				reset(codeLabel1, nameLabel1);
//			}
//			if(rs.next()){
//				show(codeLabel2, nameLabel2);
//				now = rs.getRow(); //現在の行番号を取得
//			}else {
//				reset(codeLabel2, nameLabel2);
//			}
//			if(rs.next()){
//				show(codeLabel3, nameLabel3);
//				now = rs.getRow(); //現在の行番号を取得
//			}else {
//				reset(codeLabel3, nameLabel3);
//			}
//			if(rs.next()){
//				show(codeLabel4, nameLabel4);
//				now = rs.getRow(); //現在の行番号を取得
//			}else {
//				reset(codeLabel4, nameLabel4);
//			}
//			if(rs.next()){
//				show(codeLabel5, nameLabel5);
//				now = rs.getRow(); //現在の行番号を取得
//			}else {
//				reset(codeLabel5, nameLabel5);
//			}
//			if(rs.next()){
//				show(codeLabel6, nameLabel6);
//				now = rs.getRow(); //現在の行番号を取得
//			}else {
//				reset(codeLabel6, nameLabel6);
//			}
//			if(rs.next()){
//				show(codeLabel7, nameLabel7);	
//				now = rs.getRow(); //現在の行番号を取得
//			}else {
//				reset(codeLabel7, nameLabel7);
//			}
//			if(rs.next()){
//				show(codeLabel8, nameLabel8);
//				now = rs.getRow(); //現在の行番号を取得
//			}else {
//				reset(codeLabel8, nameLabel8);
//			}
//			if(rs.next()){
//				show(codeLabel9, nameLabel9);
//				now = rs.getRow(); //現在の行番号を取得
//			}else {
//				reset(codeLabel9, nameLabel9);
//			}
//			if(rs.next()){
//				show(codeLabel10, nameLabel10);
//				now = rs.getRow(); //現在の行番号を取得
//			}else {
//				reset(codeLabel10, nameLabel10);
//			}
//		}catch(SQLException e2) {
//			e2.printStackTrace();
//		}catch(Exception e2) {
//			e2.printStackTrace();
//		}
//	}
	
	//Resultsetオブジェクトがnullでなければラベルに内容を表示するメソッド
//	public void show(JTextField codeTextField, JTextField nameTextField, JComboBox sexBox, JTextField birthdayText, JComboBox flagBox) {
//		try {
//			codeTextField.setText(rs.getString("店員コード"));
//			nameTextField.setText(rs.getString("氏名"));
//			codeLabel.setText(rs.getString("店員コード"));
//			nameLabel.setText(rs.getString("氏名"));
//		}catch(SQLException e2) {
//			e2.printStackTrace();
//		}catch(Exception e2) {
//			e2.printStackTrace();
//		}
//	}
	
	//Resultsetオブジェクトがnullならばラベルを白紙にするメソッド
	public void reset(JLabel codeLabel, JLabel nameLabel) {
		codeLabel.setText(null);
		nameLabel.setText(null);
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
}
