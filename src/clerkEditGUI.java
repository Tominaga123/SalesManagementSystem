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
	
	JLabel fdLabel = new JLabel("日 "); //単位「日」を表示するラベル
	JComboBox dateComboBox = new JComboBox(); //「日」の選択欄
	
	JComboBox timeRangeComboBox = new JComboBox(); //選択した日時より以前か等を選択するボックス
	
	JLabel ffLabel = new JLabel("  削除フラグ"); //削除フラグの選択欄であることを示すラベル
	JComboBox flagComboBox = new JComboBox(); //削除フラグの選択欄
	
	JLabel voidLabel1 = new JLabel();
	JLabel voidLabel2 = new JLabel();
	
	JButton searchButton = new JButton("絞り込み"); //検索ボタン
	JButton releaseButton = new JButton("絞り込み解除"); //絞り込み解除ボタン
	
	
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
	
	JButton updateButton1= new JButton("更新");//当該行の編集を反映するボタン
	JButton updateButton2= new JButton("更新");
	JButton updateButton3= new JButton("更新");
	JButton updateButton4= new JButton("更新");
	JButton updateButton5= new JButton("更新");
	JButton updateButton6= new JButton("更新");
	JButton updateButton7= new JButton("更新");
	JButton updateButton8= new JButton("更新");
	JButton updateButton9= new JButton("更新");
	JButton updateButton10= new JButton("更新");
	
	JButton editButton1= new JButton("追加");//当該行を追加・消去するボタン
	JButton editButton2= new JButton("追加");
	JButton editButton3= new JButton("追加");
	JButton editButton4= new JButton("追加");
	JButton editButton5= new JButton("追加");
	JButton editButton6= new JButton("追加");
	JButton editButton7= new JButton("追加");
	JButton editButton8= new JButton("追加");
	JButton editButton9= new JButton("追加");
	JButton editButton10= new JButton("追加");
	
	JLabel totalNumberLabel = new JLabel("0"); //検索結果の総件数を表示するラベル
	JLabel tnLabel = new JLabel("件中");
	JLabel showNumberLabel = new JLabel("0"); //表示件数を表示するラベル
	JLabel snLabel = new JLabel("件目まで表示 /");
	
	JButton nextButton = new JButton("次へ"); //ページをめくるボタン
	JButton previousButton = new JButton("前へ"); 
	JButton firstButton = new JButton("最初へ");
	JButton lastButton = new JButton("最後へ"); 
	
	int now, last; //ページをめくる際に使用
	String newCode; //データの更新、追加後に、当該データがあるページに飛ぶために使用
	int stopFlag; //同上。stopFlagが1になるまでページを表示し続ける
	
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
		panel4.setLayout(new GridLayout(11, 7, 0, 5));
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
		//「日」
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
		timeRangeComboBox.addItem("以前");
		timeRangeComboBox.addItem("以後");
		timeRangeComboBox.addItem("一致");
		//削除フラグを選択するコンボボックスに項目を追加
		flagComboBox.addItem(null);
		flagComboBox.addItem("0");
		flagComboBox.addItem("1");
		flagComboBox.setSelectedItem(null);
		
		//編集で使用する性別と削除フラグのコンボボックスに項目を追加
		sexItem(sexComboBox1);
		sexItem(sexComboBox2);
		sexItem(sexComboBox3);
		sexItem(sexComboBox4);
		sexItem(sexComboBox5);
		sexItem(sexComboBox6);
		sexItem(sexComboBox7);
		sexItem(sexComboBox8);
		sexItem(sexComboBox9);
		sexItem(sexComboBox10);
	
		flagItem(flagComboBox1);
		flagItem(flagComboBox2);
		flagItem(flagComboBox3);
		flagItem(flagComboBox4);
		flagItem(flagComboBox5);
		flagItem(flagComboBox6);
		flagItem(flagComboBox7);
		flagItem(flagComboBox8);
		flagItem(flagComboBox9);
		flagItem(flagComboBox10);
		
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
		panel2.add(fdLabel);
		panel2.add(dateComboBox);
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
		panel4.add(voidLabel1);
		panel4.add(voidLabel2);

		panel4.add(codeTextField1);
		panel4.add(nameTextField1);
		panel4.add(sexComboBox1);
		panel4.add(birthdayTextField1);
		panel4.add(flagComboBox1);
		panel4.add(updateButton1);
		panel4.add(editButton1);

		panel4.add(codeTextField2);
		panel4.add(nameTextField2);
		panel4.add(sexComboBox2);
		panel4.add(birthdayTextField2);
		panel4.add(flagComboBox2);
		panel4.add(updateButton2);
		panel4.add(editButton2);

		panel4.add(codeTextField3);
		panel4.add(nameTextField3);
		panel4.add(sexComboBox3);
		panel4.add(birthdayTextField3);
		panel4.add(flagComboBox3);
		panel4.add(updateButton3);
		panel4.add(editButton3);
		
		panel4.add(codeTextField4);
		panel4.add(nameTextField4);
		panel4.add(sexComboBox4);
		panel4.add(birthdayTextField4);
		panel4.add(flagComboBox4);
		panel4.add(updateButton4);
		panel4.add(editButton4);
		
		panel4.add(codeTextField5);
		panel4.add(nameTextField5);
		panel4.add(sexComboBox5);
		panel4.add(birthdayTextField5);
		panel4.add(flagComboBox5);
		panel4.add(updateButton5);
		panel4.add(editButton5);
		
		panel4.add(codeTextField6);
		panel4.add(nameTextField6);
		panel4.add(sexComboBox6);
		panel4.add(birthdayTextField6);
		panel4.add(flagComboBox6);
		panel4.add(updateButton6);
		panel4.add(editButton6);
		
		panel4.add(codeTextField7);
		panel4.add(nameTextField7);
		panel4.add(sexComboBox7);
		panel4.add(birthdayTextField7);
		panel4.add(flagComboBox7);
		panel4.add(updateButton7);
		panel4.add(editButton7);
		
		panel4.add(codeTextField8);
		panel4.add(nameTextField8);
		panel4.add(sexComboBox8);
		panel4.add(birthdayTextField8);
		panel4.add(flagComboBox8);
		panel4.add(updateButton8);
		panel4.add(editButton8);
		
		panel4.add(codeTextField9);
		panel4.add(nameTextField9);
		panel4.add(sexComboBox9);
		panel4.add(birthdayTextField9);
		panel4.add(flagComboBox9);
		panel4.add(updateButton9);
		panel4.add(editButton9);
		
		panel4.add(codeTextField10);
		panel4.add(nameTextField10);
		panel4.add(sexComboBox10);
		panel4.add(birthdayTextField10);
		panel4.add(flagComboBox10);
		panel4.add(updateButton10);
		panel4.add(editButton10);

		panel15.add(firstButton);
		panel15.add(previousButton);
		panel15.add(showNumberLabel);
		panel15.add(snLabel);
		panel15.add(totalNumberLabel);
		panel15.add(tnLabel);
		panel15.add(nextButton);
		panel15.add(lastButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		getContentPane().add(panel4);
		getContentPane().add(panel5);
		getContentPane().add(panel15);
		
		updateButton1.addActionListener(this);
		updateButton2.addActionListener(this);
		updateButton3.addActionListener(this);
		updateButton4.addActionListener(this);
		updateButton5.addActionListener(this);
		updateButton6.addActionListener(this);
		updateButton7.addActionListener(this);
		updateButton8.addActionListener(this);
		updateButton9.addActionListener(this);
		updateButton10.addActionListener(this);
		
		editButton1.addActionListener(this);
		editButton2.addActionListener(this);
		editButton3.addActionListener(this);
		editButton4.addActionListener(this);
		editButton5.addActionListener(this);
		editButton6.addActionListener(this);
		editButton7.addActionListener(this);
		editButton8.addActionListener(this);
		editButton9.addActionListener(this);
		editButton10.addActionListener(this);
		
		searchButton.addActionListener(this);
		releaseButton.addActionListener(this);
		nextButton.addActionListener(this);
		previousButton.addActionListener(this);
		firstButton.addActionListener(this);
		lastButton.addActionListener(this);
		nextButton.setEnabled(false);
		previousButton.setEnabled(false);
		
		//初めは全件表示した状態にする
		allShow();
		
		this.pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == searchButton) { //「絞り込み」ボタンを押した場合
			allShow();
		} else if(e.getSource() == releaseButton) { //「絞り込み解除」ボタンを押した場合
			//絞り込み条件をリセット
			codeComboBox.setSelectedItem(null);
			nameComboBox.setSelectedItem(null);
			sexComboBox.setSelectedItem(null);
			yearComboBox.setSelectedItem(getYear());
			monthComboBox.setSelectedItem(getMonth());
			dateComboBox.setSelectedItem(getDate());
			timeRangeComboBox.setSelectedItem("以前");
			flagComboBox.setSelectedItem(null);
			allShow();
		}  else if(e.getSource() == nextButton) { //「次へ」ボタンを押した場合
				result(); //検索結果を表示
				previousButton.setEnabled(true);
		} else if(e.getSource() == previousButton) { //「前へ」ボタンを押した場合
			if(editButton1.getText().equals("追加")) { //一番上の行のeditButtonが「追加」（ ＝ 一番上の行が空欄）のとき
				now -= 10; // 現在行を前ページの先頭に戻す
			} else {
				now = 10 * (int)Math.floor((now-1)/10) - 10; // 現在行を前ページの先頭のひとつ前に戻す
				if(now < 0) {
					now = 0;
				}
			}
			try {
				rs.absolute(now);
				result(); //検索結果を表示
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
		}else if(e.getSource() == updateButton1) {
			update(codeTextField1, nameTextField1, sexComboBox1, birthdayTextField1, flagComboBox1, 1);
		} else if(e.getSource() == updateButton2) {
			update(codeTextField2, nameTextField2, sexComboBox2, birthdayTextField2, flagComboBox2, 2);
		} else if(e.getSource() == updateButton3) {
			update(codeTextField3, nameTextField3, sexComboBox3, birthdayTextField3, flagComboBox3, 3);
		} else if(e.getSource() == updateButton4) {
			update(codeTextField4, nameTextField4, sexComboBox4, birthdayTextField4, flagComboBox4, 4);
		} else if(e.getSource() == updateButton5) {
			update(codeTextField5, nameTextField5, sexComboBox5, birthdayTextField5, flagComboBox5, 5);
		} else if(e.getSource() == updateButton6) {
			update(codeTextField6, nameTextField6, sexComboBox6, birthdayTextField6, flagComboBox6, 6);
		} else if(e.getSource() == updateButton7) {
			update(codeTextField7, nameTextField7, sexComboBox7, birthdayTextField7, flagComboBox7, 7);
		} else if(e.getSource() == updateButton8) {
			update(codeTextField8, nameTextField8, sexComboBox8, birthdayTextField8, flagComboBox8, 8);
		} else if(e.getSource() == updateButton9) {
			update(codeTextField9, nameTextField9, sexComboBox9, birthdayTextField9, flagComboBox9, 9);
		} else if(e.getSource() == updateButton10) {
			update(codeTextField10, nameTextField10, sexComboBox10, birthdayTextField10, flagComboBox10, 10);
		} else if(e.getSource() == editButton1) {
			if(editButton1.getText().equals("追加")) {
				insert(codeTextField1, nameTextField1, sexComboBox1, birthdayTextField1, flagComboBox1);
			} else if(editButton1.getText().equals("消去")) {
				delete(codeTextField1, nameTextField1, sexComboBox1, birthdayTextField1, flagComboBox1);
			}
		} else if(e.getSource() == editButton2) {
			if(editButton2.getText().equals("追加")) {
				insert(codeTextField2, nameTextField2, sexComboBox2, birthdayTextField2, flagComboBox2);
			} else if(editButton2.getText().equals("消去")) {
				delete(codeTextField2, nameTextField2, sexComboBox2, birthdayTextField2, flagComboBox2);
			}
		} else if(e.getSource() == editButton3) {
			if(editButton3.getText().equals("追加")) {
				insert(codeTextField3, nameTextField3, sexComboBox3, birthdayTextField3, flagComboBox3);
			} else if(editButton3.getText().equals("消去")) {
				delete(codeTextField3, nameTextField3, sexComboBox3, birthdayTextField3, flagComboBox3);
			}
		} else if(e.getSource() == editButton4) {
			if(editButton4.getText().equals("追加")) {
				insert(codeTextField4, nameTextField4, sexComboBox4, birthdayTextField4, flagComboBox4);
			} else if(editButton4.getText().equals("消去")) {
				delete(codeTextField4, nameTextField4, sexComboBox4, birthdayTextField4, flagComboBox4);
			}
		} else if(e.getSource() == editButton5) {
			if(editButton5.getText().equals("追加")) {
				insert(codeTextField5, nameTextField5, sexComboBox5, birthdayTextField5, flagComboBox5);
			} else if(editButton5.getText().equals("消去")) {
				delete(codeTextField5, nameTextField5, sexComboBox5, birthdayTextField5, flagComboBox5);
			}
		} else if(e.getSource() == editButton6) {
			if(editButton6.getText().equals("追加")) {
				insert(codeTextField6, nameTextField6, sexComboBox6, birthdayTextField6, flagComboBox6);
			} else if(editButton6.getText().equals("消去")) {
				delete(codeTextField6, nameTextField6, sexComboBox6, birthdayTextField6, flagComboBox6);
			}
		} else if(e.getSource() == editButton7) {
			if(editButton7.getText().equals("追加")) {
				insert(codeTextField7, nameTextField7, sexComboBox7, birthdayTextField7, flagComboBox7);
			} else if(editButton7.getText().equals("消去")) {
				delete(codeTextField7, nameTextField7, sexComboBox7, birthdayTextField7, flagComboBox7);
			}
		} else if(e.getSource() == editButton8) {
			if(editButton8.getText().equals("追加")) {
				insert(codeTextField8, nameTextField8, sexComboBox8, birthdayTextField8, flagComboBox8);
			} else if(editButton8.getText().equals("消去")) {
				delete(codeTextField8, nameTextField8, sexComboBox8, birthdayTextField8, flagComboBox8);
			}
		} else if(e.getSource() == editButton9) {
			if(editButton9.getText().equals("追加")) {
				insert(codeTextField9, nameTextField9, sexComboBox9, birthdayTextField9, flagComboBox9);
			} else if(editButton9.getText().equals("消去")) {
				delete(codeTextField9, nameTextField9, sexComboBox9, birthdayTextField9, flagComboBox9);
			}
		} else if(e.getSource() == editButton10) {
			if(editButton10.getText().equals("追加")) {
				insert(codeTextField10, nameTextField10, sexComboBox10, birthdayTextField10, flagComboBox10);
			} else if(editButton10.getText().equals("消去")) {
				delete(codeTextField10, nameTextField10, sexComboBox10, birthdayTextField10, flagComboBox10);
			}
		} 
	}

	//最初とデータの編集（更新、追加、削除）をした際に検索結果を更新するメソッド
	public void allShow() {
		SQL = createSQL();
		System.out.println(SQL + " で表示します");
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(SQL);
			result(); //検索結果を表示
			rs.last(); //最後の行に移動し、行番号を取得
			last = rs.getRow();
			rs.absolute(now); //最後の行番号を取得したのち、元の行に戻る
			totalNumberLabel.setText(Integer.toString(last));
			previousButton.setEnabled(false);
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
			filterSQL += " AND 削除フラグ = " + flagComboBox.getSelectedItem(); 
		}
		String str = "SELECT * "
				+ "FROM 店員マスタ "
				+ "WHERE 1" + filterSQL + 
				" ORDER BY 店員コード ASC;";
		filterSQL = ""; //filterSQLをリセットする
		return str;
	}
	
	//それぞれのラベルに検索結果を表示するメソッド
	public void result() {
		try {
			if(rs.next()){
				show(codeTextField1, nameTextField1, sexComboBox1, birthdayTextField1, flagComboBox1, updateButton1, editButton1); //検索結果表示
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(codeTextField1, nameTextField1, sexComboBox1, birthdayTextField1, flagComboBox1, updateButton1, editButton1); //白紙にする
				//更新または追加されたデータの店員コードと同じものがないまま一行目が空欄になればページ送りをストップ
				stopFlag = 1;
			}
			if(rs.next()){
				show(codeTextField2, nameTextField2, sexComboBox2, birthdayTextField2, flagComboBox2, updateButton2, editButton2);
				now = rs.getRow();
			}else {
				reset(codeTextField2, nameTextField2, sexComboBox2, birthdayTextField2, flagComboBox2, updateButton2, editButton2);
			}
			if(rs.next()){
				show(codeTextField3, nameTextField3, sexComboBox3, birthdayTextField3, flagComboBox3, updateButton3, editButton3);
				now = rs.getRow();
			}else {
				reset(codeTextField3, nameTextField3, sexComboBox3, birthdayTextField3, flagComboBox3, updateButton3, editButton3);
			}
			if(rs.next()){
				show(codeTextField4, nameTextField4, sexComboBox4, birthdayTextField4, flagComboBox4, updateButton4, editButton4);
				now = rs.getRow();
			}else {
				reset(codeTextField4, nameTextField4, sexComboBox4, birthdayTextField4, flagComboBox4, updateButton4, editButton4);
			}
			if(rs.next()){
				show(codeTextField5, nameTextField5, sexComboBox5, birthdayTextField5, flagComboBox5, updateButton5, editButton5);
				now = rs.getRow();
			}else {
				reset(codeTextField5, nameTextField5, sexComboBox5, birthdayTextField5, flagComboBox5, updateButton5, editButton5);
			}
			if(rs.next()){
				show(codeTextField6, nameTextField6, sexComboBox6, birthdayTextField6, flagComboBox6, updateButton6, editButton6);
				now = rs.getRow();
			}else {
				reset(codeTextField6, nameTextField6, sexComboBox6, birthdayTextField6, flagComboBox6, updateButton6, editButton6);
			}
			if(rs.next()){
				show(codeTextField7, nameTextField7, sexComboBox7, birthdayTextField7, flagComboBox7, updateButton7, editButton7);	
				now = rs.getRow();
			}else {
				reset(codeTextField7, nameTextField7, sexComboBox7, birthdayTextField7, flagComboBox7, updateButton7, editButton7);
			}
			if(rs.next()){
				show(codeTextField8, nameTextField8, sexComboBox8, birthdayTextField8, flagComboBox8, updateButton8, editButton8);
				now = rs.getRow();
			}else {
				reset(codeTextField8, nameTextField8, sexComboBox8, birthdayTextField8, flagComboBox8, updateButton8, editButton8);
			}
			if(rs.next()){
				show(codeTextField9, nameTextField9, sexComboBox9, birthdayTextField9, flagComboBox9, updateButton9, editButton9);
				now = rs.getRow();
			}else {
				reset(codeTextField9, nameTextField9, sexComboBox9, birthdayTextField9, flagComboBox9, updateButton9, editButton9);
			}
			if(rs.next()){
				show(codeTextField10, nameTextField10, sexComboBox10, birthdayTextField10, flagComboBox10, updateButton10, editButton10);
				now = rs.getRow();
				nextButton.setEnabled(true); //一番下の行にデータがあれば「次へ」ボタンをtrueにする
			}else {
				reset(codeTextField10, nameTextField10, sexComboBox10, birthdayTextField10, flagComboBox10, updateButton10, editButton10);
				nextButton.setEnabled(false); //一番下の行が白紙なら「次へ」ボタンをfalseにする
			}
			System.out.println("nowは" + now);
			if(now > 10) {
				previousButton.setEnabled(true);
			}
			showNumberLabel.setText(Integer.toString(now));
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullでなければラベルに内容を表示するメソッド
	public void show(JTextField codeText, JTextField nameText, JComboBox sexBox, JTextField birthdayText, 
			JComboBox flagBox, JButton updateButton, JButton editButton) {
		try {
			codeText.setText(rs.getString("店員コード"));
			//更新または追加されたデータの店員コードと同じであればページ送りをストップ
			if(rs.getString("店員コード").equals(newCode)) { 
				stopFlag = 1;
			}
			nameText.setText(rs.getString("氏名"));
			sexBox.setSelectedItem(rs.getString("性別"));
			birthdayText.setText(rs.getString("生年月日"));
			flagBox.setSelectedItem(rs.getString("削除フラグ"));
			updateButton.setEnabled(true);
			editButton.setText("消去");
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullならばラベルを白紙にするメソッド
	public void reset(JTextField codeText, JTextField nameText, JComboBox sexBox, JTextField birthdayText, 
			JComboBox flagBox, JButton updateButton, JButton editButton) {
		codeText.setText("");
		nameText.setText("");
		sexBox.setSelectedItem(null);
		birthdayText.setText("");
		flagBox.setSelectedItem(null);
		updateButton.setEnabled(false);
		editButton.setText("追加");
	}
	
	//データを更新するメソッド
	public void update(JTextField codeText, JTextField nameText, JComboBox sexBox, JTextField birthdayText, 
			JComboBox flagBox, int x) {
		//すべての入力欄に空欄がなければデータを更新する
		if(!codeText.getText().equals("") && !nameText.getText().equals("") && sexBox.getSelectedItem() != null && 
				!birthdayText.getText().equals("") && flagBox.getSelectedItem() != null ) { 
			int row = (int)Math.floor((Integer.parseInt(showNumberLabel.getText()) - 1) / 10) * 10 + x;
			System.out.println(row);
			try {
				rs.absolute(row);
				rs.updateString("店員コード", codeText.getText());
				rs.updateString("氏名", nameText.getText());
				rs.updateString("性別", (String)sexBox.getSelectedItem());
				rs.updateString("生年月日", birthdayText.getText());
				rs.updateString("削除フラグ", (String)flagBox.getSelectedItem());
				rs.updateRow();
				newCode = codeText.getText();
				System.out.println("店員コード:" + codeText.getText() + " 氏名:" + nameText.getText() +
						" 性別:" + sexBox.getSelectedItem() + " 生年月日" + birthdayText.getText() + 
						" 削除フラグ:" + flagBox.getSelectedItem() + "で更新しました");
				//データを更新したのち、表を再取得して更新したデータがあるページへ飛ぶ
				allShow();
				try {
					rs.beforeFirst();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stopFlag = 0;
				while(stopFlag == 0) {
					result();
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
				System.out.println("データを更新できませんでした" + "\r" + "データが正しく入力されているか確認してください" +
				"\r" + "例：店員コードまたは店員名が長すぎる" + "\r" + "例：生年月日が形式通りに入力されていない");
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		} else { //一つでも空欄がある場合、その旨のメッセージを出す
			System.out.println("未入力の項目があります");
		}
	}
	
	//データを追加するメソッド
	public void insert(JTextField codeText, JTextField nameText, JComboBox sexBox, JTextField birthdayText, 
			JComboBox flagBox) {
		//すべての入力欄に空欄がなければデータを追加する
		if(!codeText.getText().equals("") && !nameText.getText().equals("") && sexBox.getSelectedItem() != null && 
				!birthdayText.getText().equals("") && flagBox.getSelectedItem() != null ) { 
			String SQL = "INSERT INTO 店員マスタ (店員コード, 氏名, 性別, 生年月日, 削除フラグ) values ('" + 
					codeText.getText() + "', '" + nameText.getText() + "', '" + sexBox.getSelectedItem() + 
					"', cast('" + birthdayText.getText() + "' as date), " + flagBox.getSelectedItem() + ");";
			try {
				Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stmt = conn.createStatement();
				stmt.execute(SQL);
				newCode = codeText.getText();
				System.out.println(SQL + "を追加しました");
				//データを追加したのち、表を再取得して追加したデータがあるページへ飛ぶ
				allShow();
				try {
					rs.beforeFirst();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				stopFlag = 0;
				while(stopFlag == 0) {
					result();
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
				System.out.println("データを追加できませんでした" + "\r" + "データが正しく入力されているか確認してください" +
				"\r" + "例：店員コードまたは店員名が長すぎる" + "\r" + "例：生年月日が形式通りに入力されていない");
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		} else { //一つでも空欄がある場合、その旨のメッセージを出す
			System.out.println("未入力の項目があります");
		}
	}
	
	//データを消去するメソッド
	public void delete(JTextField codeText, JTextField nameText, JComboBox sexBox, JTextField birthdayText, 
			JComboBox flagBox) {
		//店員コードが空欄でなければデータを消去する
		if(!codeText.getText().equals("")) { 
			String SQL = "delete from 店員マスタ where 店員コード = '" + codeText.getText() + "';";
			try {
				Connection conn = DriverManager.getConnection(URL, USER, PASS);
				Statement stmt = conn.createStatement();
				stmt.execute(SQL);
				System.out.println(SQL + "を削除しました");
				//データを消去したのち、表を再取得して消去したデータがあったページへ飛ぶ
				int n = (int)Math.floor((Integer.parseInt(showNumberLabel.getText()) - 1) / 10) + 1 ;
				System.out.println(n);
				allShow();
				try {
					rs.beforeFirst();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				for(int i = 1; i <= n; i++) {
					result();
				}
				if(now >= 10) {
					previousButton.setEnabled(true);
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		} else {
			System.out.println("店員コードが空欄のため消去できませんでした");
		}
	}
	
	//「年」「月」「日」のコンボボックスを現在の日時で初期化するメソッド
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
	
	//性別のコンボボックスに項目を追加するメソッド
	public void sexItem(JComboBox sexBox) {
		sexBox.addItem(null);
		sexBox.addItem("男性");
		sexBox.addItem("女性");
		sexBox.setSelectedItem(null);
	}
	//削除フラグのコンボボックスに項目を追加するメソッド
	public void flagItem(JComboBox flagBox) {
		flagBox.addItem("0");
		flagBox.addItem("1");
		flagBox.setSelectedItem(null);
	}
}