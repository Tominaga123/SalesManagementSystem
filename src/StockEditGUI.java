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

public class StockEditGUI extends JFrame implements ActionListener{
	
	JLabel filterLabel = new JLabel("絞り込み条件");//絞り込み条件の入力欄であることを示すラベル
	
	//絞り込み条件の入力欄
	JLabel ftLabel = new JLabel("仕入日"); //仕入日の選択欄であることを示すラベル
	JLabel fyLabel = new JLabel("年 "); //単位「年」を表示するラベル
	JComboBox yearComboBox = new JComboBox(); //[年]の選択欄
	
	JLabel fmLabel = new JLabel("月 "); //単位「月」を表示するラベル
	JComboBox monthComboBox = new JComboBox(); //「月」の選択欄
	
	JLabel fdLabel = new JLabel("日 "); //単位「日」を表示するラベル
	JComboBox dateComboBox = new JComboBox(); //「日」の選択欄
	
	JComboBox timeRangeComboBox = new JComboBox(); //選択した日時より以前か等を選択するボックス
	
	JLabel fcLabel = new JLabel("  商品コード"); //商品コードの選択欄であることを示すラベル
	JComboBox fCodeComboBox = new JComboBox(); //商品コードの選択欄
	
	JLabel fnLabel = new JLabel("  商品名"); //商品名の選択欄であることを示すラベル
	JComboBox fNameComboBox = new JComboBox(); //商品名の選択欄
	
	JLabel voidLabel1 = new JLabel();
	JLabel voidLabel2 = new JLabel();
	
	JButton searchButton = new JButton("絞り込み"); //検索ボタン
	JButton releaseButton = new JButton("絞り込み解除"); //絞り込み解除ボタン
	
	JLabel tLabel = new JLabel("仕入日", JLabel.CENTER); //仕入日であることを示すラベル
	JTextField timeTextField1 = new JTextField(); //仕入日を編集するテキストフィールド
	JTextField timeTextField2 = new JTextField();
	JTextField timeTextField3 = new JTextField();
	JTextField timeTextField4 = new JTextField();
	JTextField timeTextField5 = new JTextField();
	JTextField timeTextField6 = new JTextField();
	JTextField timeTextField7 = new JTextField();
	JTextField timeTextField8 = new JTextField();
	JTextField timeTextField9 = new JTextField();
	JTextField timeTextField10 = new JTextField();
	
	JLabel cLabel = new JLabel("商品コード", JLabel.CENTER); //商品コードであることを示すラベル
	JComboBox codeComboBox1 = new JComboBox(); //商品コードの選択欄
	JComboBox codeComboBox2 = new JComboBox();
	JComboBox codeComboBox3 = new JComboBox();
	JComboBox codeComboBox4 = new JComboBox();
	JComboBox codeComboBox5 = new JComboBox();
	JComboBox codeComboBox6 = new JComboBox();
	JComboBox codeComboBox7 = new JComboBox();
	JComboBox codeComboBox8 = new JComboBox();
	JComboBox codeComboBox9 = new JComboBox();
	JComboBox codeComboBox10 = new JComboBox();
	
	
	JLabel nLabel = new JLabel("商品名", JLabel.CENTER); //商品名であることを示すラベル
	JComboBox nameComboBox1 = new JComboBox(); //商品名の選択欄
	JComboBox nameComboBox2 = new JComboBox(); //商品名の選択欄
	JComboBox nameComboBox3 = new JComboBox(); //商品名の選択欄
	JComboBox nameComboBox4 = new JComboBox(); //商品名の選択欄
	JComboBox nameComboBox5 = new JComboBox(); //商品名の選択欄
	JComboBox nameComboBox6 = new JComboBox(); //商品名の選択欄
	JComboBox nameComboBox7 = new JComboBox(); //商品名の選択欄
	JComboBox nameComboBox8 = new JComboBox(); //商品名の選択欄
	JComboBox nameComboBox9 = new JComboBox(); //商品名の選択欄
	JComboBox nameComboBox10 = new JComboBox(); //商品名の選択欄
	
	JLabel qLabel = new JLabel("仕入数", JLabel.CENTER); //仕入数であることを示すラベル
	JTextField quantityTextField1 = new JTextField(); //仕入数を入力するテキストフィールド
	JTextField quantityTextField2 = new JTextField();
	JTextField quantityTextField3 = new JTextField();
	JTextField quantityTextField4 = new JTextField();
	JTextField quantityTextField5 = new JTextField();
	JTextField quantityTextField6 = new JTextField();
	JTextField quantityTextField7 = new JTextField();
	JTextField quantityTextField8 = new JTextField();
	JTextField quantityTextField9 = new JTextField();
	JTextField quantityTextField10 = new JTextField();

	JLabel pLabel = new JLabel("仕入単価", JLabel.CENTER); //仕入単価であることを示すラベル
	JTextField priceTextField1 = new JTextField(); //仕入単価を入力するテキストフィールド
	JTextField priceTextField2 = new JTextField();
	JTextField priceTextField3 = new JTextField();
	JTextField priceTextField4 = new JTextField();
	JTextField priceTextField5 = new JTextField();
	JTextField priceTextField6 = new JTextField();
	JTextField priceTextField7 = new JTextField();
	JTextField priceTextField8 = new JTextField();
	JTextField priceTextField9 = new JTextField();
	JTextField priceTextField10 = new JTextField();
	
	JLabel tpLabel = new JLabel("仕入金額", JLabel.CENTER); //仕入金額であることを示すラベル
	JLabel totalPriceLabel1 = new JLabel("", JLabel.CENTER); //仕入金額を表示するラベル
	JLabel totalPriceLabel2 = new JLabel("", JLabel.CENTER);
	JLabel totalPriceLabel3 = new JLabel("", JLabel.CENTER);
	JLabel totalPriceLabel4 = new JLabel("", JLabel.CENTER);
	JLabel totalPriceLabel5 = new JLabel("", JLabel.CENTER);
	JLabel totalPriceLabel6 = new JLabel("", JLabel.CENTER);
	JLabel totalPriceLabel7 = new JLabel("", JLabel.CENTER);
	JLabel totalPriceLabel8 = new JLabel("", JLabel.CENTER);
	JLabel totalPriceLabel9 = new JLabel("", JLabel.CENTER);
	JLabel totalPriceLabel10 = new JLabel("", JLabel.CENTER);
	
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
	String newTime; //同上
	int stopFlag; //同上。stopFlagが1になるまでページを表示し続ける
	
	//データベースからデータを取得する際に使用
	String SQL;
	String filterSQL = "";
	Statement stmt; 
	ResultSet rs; 
	Statement otherStmt; 
	ResultSet otherRs;
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	
	StockEditGUI(){
		setTitle("在庫マスタ編集");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//レイアウト設定
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new GridLayout(11, 8, 0, 5));
		panel5.setLayout(new FlowLayout());
		
		//絞り込みをする際の条件を設定するコンポーネントの設定
		
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
		
		//商品コードを選択するコンボボックスに項目を追加
		addCode(fCodeComboBox);
		addCode(codeComboBox1);
		addCode(codeComboBox2);
		addCode(codeComboBox3);
		addCode(codeComboBox4);
		addCode(codeComboBox5);
		addCode(codeComboBox6);
		addCode(codeComboBox7);
		addCode(codeComboBox8);
		addCode(codeComboBox9);
		addCode(codeComboBox10);
		//商品名を選択するコンボボックスに項目を追加
		addName(fNameComboBox);
		addName(nameComboBox1);
		addName(nameComboBox2);
		addName(nameComboBox3);
		addName(nameComboBox4);
		addName(nameComboBox5);
		addName(nameComboBox6);
		addName(nameComboBox7);
		addName(nameComboBox8);
		addName(nameComboBox9);
		addName(nameComboBox10);
		
		panel1.add(filterLabel);
		
		panel2.add(ftLabel);
		panel2.add(yearComboBox);
		panel2.add(fyLabel);
		panel2.add(monthComboBox);
		panel2.add(fmLabel);
		panel2.add(dateComboBox);
		panel2.add(fdLabel);
		panel2.add(timeRangeComboBox);
		panel2.add(fcLabel);
		panel2.add(fCodeComboBox);
		panel2.add(fnLabel);
		panel2.add(fNameComboBox);
		
		panel3.add(searchButton);
		panel3.add(releaseButton);
		
		panel4.add(tLabel);
		panel4.add(cLabel);
		panel4.add(nLabel);
		panel4.add(qLabel);
		panel4.add(pLabel);
		panel4.add(tpLabel);
		panel4.add(voidLabel1);
		panel4.add(voidLabel2);

		panel4.add(timeTextField1);
		panel4.add(codeComboBox1);
		panel4.add(nameComboBox1);
		panel4.add(quantityTextField1);
		panel4.add(priceTextField1);
		panel4.add(totalPriceLabel1);
		panel4.add(updateButton1);
		panel4.add(editButton1);
		
		panel4.add(timeTextField2);
		panel4.add(codeComboBox2);
		panel4.add(nameComboBox2);
		panel4.add(quantityTextField2);
		panel4.add(priceTextField2);
		panel4.add(totalPriceLabel2);
		panel4.add(updateButton2);
		panel4.add(editButton2);

		panel4.add(timeTextField3);
		panel4.add(codeComboBox3);
		panel4.add(nameComboBox3);
		panel4.add(quantityTextField3);
		panel4.add(priceTextField3);
		panel4.add(totalPriceLabel3);
		panel4.add(updateButton3);
		panel4.add(editButton3);
		
		panel4.add(timeTextField4);
		panel4.add(codeComboBox4);
		panel4.add(nameComboBox4);
		panel4.add(quantityTextField4);
		panel4.add(priceTextField4);
		panel4.add(totalPriceLabel4);
		panel4.add(updateButton4);
		panel4.add(editButton4);
		
		panel4.add(timeTextField5);
		panel4.add(codeComboBox5);
		panel4.add(nameComboBox5);
		panel4.add(quantityTextField5);
		panel4.add(priceTextField5);
		panel4.add(totalPriceLabel5);
		panel4.add(updateButton5);
		panel4.add(editButton5);
		
		panel4.add(timeTextField6);
		panel4.add(codeComboBox6);
		panel4.add(nameComboBox6);
		panel4.add(quantityTextField6);
		panel4.add(priceTextField6);
		panel4.add(totalPriceLabel6);
		panel4.add(updateButton6);
		panel4.add(editButton6);
		
		panel4.add(timeTextField7);
		panel4.add(codeComboBox7);
		panel4.add(nameComboBox7);
		panel4.add(quantityTextField7);
		panel4.add(priceTextField7);
		panel4.add(totalPriceLabel7);
		panel4.add(updateButton7);
		panel4.add(editButton7);
		
		panel4.add(timeTextField8);
		panel4.add(codeComboBox8);
		panel4.add(nameComboBox8);
		panel4.add(quantityTextField8);
		panel4.add(priceTextField8);
		panel4.add(totalPriceLabel8);
		panel4.add(updateButton8);
		panel4.add(editButton8);
		
		panel4.add(timeTextField9);
		panel4.add(codeComboBox9);
		panel4.add(nameComboBox9);
		panel4.add(quantityTextField9);
		panel4.add(priceTextField9);
		panel4.add(totalPriceLabel9);
		panel4.add(updateButton9);
		panel4.add(editButton9);

		panel4.add(timeTextField10);
		panel4.add(codeComboBox10);
		panel4.add(nameComboBox10);
		panel4.add(quantityTextField10);
		panel4.add(priceTextField10);
		panel4.add(totalPriceLabel10);
		panel4.add(updateButton10);
		panel4.add(editButton10);
		
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
		getContentPane().add(panel5);
		
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
		
		fCodeComboBox.addActionListener(this);
		fNameComboBox.addActionListener(this);
		searchButton.addActionListener(this);
		releaseButton.addActionListener(this);
		
		codeComboBox1.addActionListener(this);
		codeComboBox2.addActionListener(this);
		codeComboBox3.addActionListener(this);
		codeComboBox4.addActionListener(this);
		codeComboBox5.addActionListener(this);
		codeComboBox6.addActionListener(this);
		codeComboBox7.addActionListener(this);
		codeComboBox8.addActionListener(this);
		codeComboBox9.addActionListener(this);		
		codeComboBox10.addActionListener(this);
		
		nameComboBox1.addActionListener(this);
		nameComboBox2.addActionListener(this);
		nameComboBox3.addActionListener(this);
		nameComboBox4.addActionListener(this);
		nameComboBox5.addActionListener(this);
		nameComboBox6.addActionListener(this);
		nameComboBox7.addActionListener(this);
		nameComboBox8.addActionListener(this);
		nameComboBox9.addActionListener(this);
		nameComboBox10.addActionListener(this);
		
		nextButton.addActionListener(this);
		previousButton.addActionListener(this);
		firstButton.addActionListener(this);
		lastButton.addActionListener(this);
		nextButton.setEnabled(false);
		previousButton.setEnabled(false);
		
		//初めは全件表示した状態にする
		getData();
		
		this.pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		//「絞り込み」ボタンを押した場合
		if(e.getSource() == searchButton) {
			getData();
		} 
		//「絞り込み解除」ボタンを押した場合
		else if(e.getSource() == releaseButton) {
			//絞り込み条件をリセット
			yearComboBox.setSelectedItem(getYear());
			monthComboBox.setSelectedItem(getMonth());
			dateComboBox.setSelectedItem(getDate());
			timeRangeComboBox.setSelectedItem("以前");
			fCodeComboBox.setSelectedItem(null);
			fNameComboBox.setSelectedItem(null);
			getData();
		} 
		//絞り込み条件で商品コードを選んだ場合
		else if(e.getSource() == fCodeComboBox) { 
			//商品コードに対応した商品名を選択する
			setName(fCodeComboBox, fNameComboBox);
		} 
		//絞り込み条件で商品名を選んだ場合
		else if(e.getSource() == fNameComboBox) { 
			//商品名に対応した商品コードを選択する
			setCode(fCodeComboBox, fNameComboBox);
		} 
		//結果表示で商品コードを選んだ場合
		else if(e.getSource() == codeComboBox1) { 
			setName(codeComboBox1, nameComboBox1);
		} else if(e.getSource() == codeComboBox2) { 
			setName(codeComboBox2, nameComboBox2);
		} else if(e.getSource() == codeComboBox3) { 
			setName(codeComboBox3, nameComboBox3);
		} else if(e.getSource() == codeComboBox4) { 
			setName(codeComboBox4, nameComboBox4);
		} else if(e.getSource() == codeComboBox5) { 
			setName(codeComboBox5, nameComboBox5);
		} else if(e.getSource() == codeComboBox6) { 
			setName(codeComboBox6, nameComboBox6);
		} else if(e.getSource() == codeComboBox7) { 
			setName(codeComboBox7, nameComboBox7);
		} else if(e.getSource() == codeComboBox8) { 
			setName(codeComboBox8, nameComboBox8);
		} else if(e.getSource() == codeComboBox9) { 
			setName(codeComboBox9, nameComboBox9);
		} else if(e.getSource() == codeComboBox10) { 
			setName(codeComboBox10, nameComboBox10);
		}
		//結果表示で商品名を選んだ場合
		else if(e.getSource() == nameComboBox1) { 
			setCode(codeComboBox1, nameComboBox1);
		} else if(e.getSource() == nameComboBox2) { 
			setCode(codeComboBox2, nameComboBox2);
		} else if(e.getSource() == nameComboBox3) { 
			setCode(codeComboBox3, nameComboBox3);
		} else if(e.getSource() == nameComboBox4) { 
			setCode(codeComboBox4, nameComboBox4);
		} else if(e.getSource() == nameComboBox5) { 
			setCode(codeComboBox5, nameComboBox5);
		} else if(e.getSource() == nameComboBox6) { 
			setCode(codeComboBox6, nameComboBox6);
		} else if(e.getSource() == nameComboBox7) { 
			setCode(codeComboBox7, nameComboBox7);
		} else if(e.getSource() == nameComboBox8) { 
			setCode(codeComboBox8, nameComboBox8);
		} else if(e.getSource() == nameComboBox9) { 
			setCode(codeComboBox9, nameComboBox9);
		} else if(e.getSource() == nameComboBox10) { 
			setCode(codeComboBox10, nameComboBox10);
		}
		//「次へ」ボタンを押した場合
		else if(e.getSource() == nextButton) { 
				result(); //検索結果を表示
		} 
		//「前へ」ボタンを押した場合
		else if(e.getSource() == previousButton) { 
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
		//「更新」ボタンが押された場合
		else if(e.getSource() == updateButton1) {
			update(timeTextField1, codeComboBox1, quantityTextField1, priceTextField1, 1);
		}else if(e.getSource() == updateButton2) {
			update(timeTextField2, codeComboBox2, quantityTextField2, priceTextField2, 2);
		}else if(e.getSource() == updateButton3) {
			update(timeTextField3, codeComboBox3, quantityTextField3, priceTextField3, 3);
		}else if(e.getSource() == updateButton4) {
			update(timeTextField4, codeComboBox4, quantityTextField4, priceTextField4, 4);
		}else if(e.getSource() == updateButton5) {
			update(timeTextField5, codeComboBox5, quantityTextField5, priceTextField5, 5);
		}else if(e.getSource() == updateButton6) {
			update(timeTextField6, codeComboBox6, quantityTextField6, priceTextField6, 6);
		}else if(e.getSource() == updateButton7) {
			update(timeTextField7, codeComboBox7, quantityTextField7, priceTextField7, 7);
		}else if(e.getSource() == updateButton8) {
			update(timeTextField8, codeComboBox8, quantityTextField8, priceTextField8, 8);
		}else if(e.getSource() == updateButton9) {
			update(timeTextField9, codeComboBox9, quantityTextField9, priceTextField9, 9);
		}else if(e.getSource() == updateButton10) {
			update(timeTextField10, codeComboBox10, quantityTextField10, priceTextField10, 10);
		} 
		//「追加」または「消去」ボタンが押された場合
		else if(e.getSource() == editButton1) {
			 //「追加」ボタンが押された場合
			if(editButton1.getText().equals("追加")) {
				insert(timeTextField1, codeComboBox1, quantityTextField1, priceTextField1);
			}
			 //「消去」ボタンが押された場合
			else if(editButton1.getText().equals("消去")) {
				delete(timeTextField1, codeComboBox1, quantityTextField1, priceTextField1);
			}
		}else if(e.getSource() == editButton2) {
			if(editButton2.getText().equals("追加")) {
				insert(timeTextField2, codeComboBox2, quantityTextField2, priceTextField2);
			}else if(editButton2.getText().equals("消去")) {
				delete(timeTextField2, codeComboBox2, quantityTextField2, priceTextField2);
			}
		}else if(e.getSource() == editButton3) {
			if(editButton3.getText().equals("追加")) {
				insert(timeTextField3, codeComboBox3, quantityTextField3, priceTextField3);
			}else if(editButton3.getText().equals("消去")) {
				delete(timeTextField3, codeComboBox3, quantityTextField3, priceTextField3);
			}
		}else if(e.getSource() == editButton4) {
			if(editButton4.getText().equals("追加")) {
				insert(timeTextField4, codeComboBox4, quantityTextField4, priceTextField4);
			}else if(editButton4.getText().equals("消去")) {
				delete(timeTextField4, codeComboBox4, quantityTextField4, priceTextField4);
			}
		}else if(e.getSource() == editButton5) {
			if(editButton5.getText().equals("追加")) {
				insert(timeTextField5, codeComboBox5, quantityTextField5, priceTextField5);
			}else if(editButton5.getText().equals("消去")) {
				delete(timeTextField5, codeComboBox5, quantityTextField5, priceTextField5);
			}
		}else if(e.getSource() == editButton6) {
			if(editButton6.getText().equals("追加")) {
				insert(timeTextField6, codeComboBox6, quantityTextField6, priceTextField6);
			}else if(editButton6.getText().equals("消去")) {
				delete(timeTextField6, codeComboBox6, quantityTextField6, priceTextField6);
			}
		}else if(e.getSource() == editButton7) {
			if(editButton7.getText().equals("追加")) {
				insert(timeTextField7, codeComboBox7, quantityTextField7, priceTextField7);
			}else if(editButton7.getText().equals("消去")) {
				delete(timeTextField7, codeComboBox7, quantityTextField7, priceTextField7);
			}
		}else if(e.getSource() == editButton8) {
			if(editButton8.getText().equals("追加")) {
				insert(timeTextField8, codeComboBox8, quantityTextField8, priceTextField8);
			}else if(editButton8.getText().equals("消去")) {
				delete(timeTextField8, codeComboBox8, quantityTextField8, priceTextField8);
			}
		}else if(e.getSource() == editButton9) {
			if(editButton9.getText().equals("追加")) {
				insert(timeTextField9, codeComboBox9, quantityTextField9, priceTextField9);
			}else if(editButton9.getText().equals("消去")) {
				delete(timeTextField9, codeComboBox9, quantityTextField9, priceTextField9);
			}
		}else if(e.getSource() == editButton10) {
			if(editButton10.getText().equals("追加")) {
				insert(timeTextField10, codeComboBox10, quantityTextField10, priceTextField10);
			}else if(editButton10.getText().equals("消去")) {
				delete(timeTextField10, codeComboBox10, quantityTextField10, priceTextField10);
			}
		} 
	}

	//最初とデータの編集（更新、追加、消去）をした際に検索結果を更新するメソッド
	public void getData(){
		SQL = createSQL();
		System.out.println(SQL + " で表示します");
		try {
			stmt = LoginGUI.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
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
		if(timeRangeComboBox.getSelectedItem().equals("以前")) {
			filterSQL += " AND 仕入日 <= '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() +
					"-" + dateComboBox.getSelectedItem() + "'";
		}else if(timeRangeComboBox.getSelectedItem().equals("以後")) {
			filterSQL += " AND 仕入日 >= '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() +
					"-" + dateComboBox.getSelectedItem() + "'";
		}else if(timeRangeComboBox.getSelectedItem().equals("一致")) {
			filterSQL += " AND 仕入日 = '" + yearComboBox.getSelectedItem() + "-" + monthComboBox.getSelectedItem() + 
					"-" + dateComboBox.getSelectedItem() + "'";
		}
		if(fCodeComboBox.getSelectedItem() != null) {
			filterSQL += " AND 商品コード = '" + fCodeComboBox.getSelectedItem() + "'"; 
		}
		String str = "SELECT * "
				+ "FROM 在庫マスタ "
				+ "WHERE 1" + filterSQL + 
				" ORDER BY 仕入日 ASC, 商品コード ASC;";
		filterSQL = ""; //filterSQLをリセットする
		return str;
	}
	
	//それぞれのラベルに検索結果を表示するメソッド
	public void result() {
		try {
			if(rs.next()){
				show(timeTextField1, codeComboBox1, quantityTextField1, priceTextField1, totalPriceLabel1, 
						updateButton1, editButton1); //検索結果表示
				now = rs.getRow(); //現在の行番号を取得
			}else {
				reset(timeTextField1, codeComboBox1, nameComboBox1, quantityTextField1, priceTextField1, totalPriceLabel1, 
						updateButton1, editButton1); //白紙にする
				//更新または追加されたデータの店員コードと同じものがないまま一行目が空欄になればページ送りをストップ
				//このときページを一つ戻す必要がある
				//よって、stopFlagが2のとき、一ページを戻す処理をするようにする
				stopFlag = 2;
			}
			if(rs.next()){
				show(timeTextField2, codeComboBox2, quantityTextField2, priceTextField2, totalPriceLabel2, 
						updateButton2, editButton2);
				now = rs.getRow();
			}else {
				reset(timeTextField2, codeComboBox2, nameComboBox2, quantityTextField2, priceTextField2, totalPriceLabel2, 
						updateButton2, editButton2);
			}
			if(rs.next()){
				show(timeTextField3, codeComboBox3, quantityTextField3, priceTextField3, totalPriceLabel3, 
						updateButton3, editButton3);
				now = rs.getRow();
			}else {
				reset(timeTextField3, codeComboBox3, nameComboBox3, quantityTextField3, priceTextField3, totalPriceLabel3, 
						updateButton3, editButton3);
			}
			if(rs.next()){
				show(timeTextField4, codeComboBox4, quantityTextField4, priceTextField4, totalPriceLabel4, 
						updateButton4, editButton4);
				now = rs.getRow();
			}else {
				reset(timeTextField4, codeComboBox4, nameComboBox4, quantityTextField4, priceTextField4, totalPriceLabel4, 
						updateButton4, editButton4);
			}
			if(rs.next()){
				show(timeTextField5, codeComboBox5, quantityTextField5, priceTextField5, totalPriceLabel5, 
						updateButton5, editButton5);
				now = rs.getRow();
			}else {
				reset(timeTextField5, codeComboBox5, nameComboBox5, quantityTextField5, priceTextField5, totalPriceLabel5, updateButton5, editButton5);
			}
			
			if(rs.next()){
				show(timeTextField6, codeComboBox6, quantityTextField6, priceTextField6, totalPriceLabel6, 
						updateButton6, editButton6);
				now = rs.getRow();
			}else {
				reset(timeTextField6, codeComboBox6, nameComboBox6, quantityTextField6, priceTextField6, totalPriceLabel6, 
						updateButton6, editButton6);
			}
			if(rs.next()){
				show(timeTextField7, codeComboBox7, quantityTextField7, priceTextField7, totalPriceLabel7, 
						updateButton7, editButton7);	
				now = rs.getRow();
			}else {
				reset(timeTextField7, codeComboBox7, nameComboBox7, quantityTextField7, priceTextField7, totalPriceLabel7, 
						updateButton7, editButton7);
			}
			if(rs.next()){
				show(timeTextField8, codeComboBox8, quantityTextField8, priceTextField8, totalPriceLabel8, 
						updateButton8, editButton8);
				now = rs.getRow();
			}else {
				reset(timeTextField8, codeComboBox8, nameComboBox8, quantityTextField8, priceTextField8, totalPriceLabel8, 
						updateButton8, editButton8);
			}
			if(rs.next()){
				show(timeTextField9, codeComboBox9, quantityTextField9, priceTextField9, totalPriceLabel9, 
						updateButton9, editButton9);
				now = rs.getRow();
			}else {
				reset(timeTextField9, codeComboBox9, nameComboBox9, quantityTextField9, priceTextField9, totalPriceLabel9, 
						updateButton9, editButton9);
			}
			if(rs.next()){
				show(timeTextField10, codeComboBox10, quantityTextField10, priceTextField10, totalPriceLabel10, 
						updateButton10, editButton10);
				now = rs.getRow();
				nextButton.setEnabled(true); //一番下の行にデータがあれば「次へ」ボタンをtrueにする
			}else {
				reset(timeTextField10, codeComboBox10, nameComboBox10, quantityTextField10, priceTextField10, totalPriceLabel10, 
						updateButton10, editButton10);
				nextButton.setEnabled(false); //一番下の行が白紙なら「次へ」ボタンをfalseにする
			}
			System.out.println("nowは" + now);
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
	public void show(JTextField timeText, JComboBox codeBox, JTextField quantityText, JTextField priceText, 
			JLabel totalPriceLabel, JButton updateButton, JButton editButton) {
		try {
			timeText.setText(rs.getString("仕入日"));
			codeBox.setSelectedItem(rs.getString("商品コード"));
			quantityText.setText(rs.getString("仕入数"));
			priceText.setText(rs.getString("仕入単価"));
			totalPriceLabel.setText("￥" + Integer.toString(rs.getInt("仕入数")*rs.getInt("仕入単価")));
			updateButton.setEnabled(true);
			editButton.setText("消去");
			
			//更新または追加されたデータの仕入日と商品コードが同じであればページ送りをストップ
			if(rs.getString("仕入日").equals(newTime) && rs.getString("商品コード").equals(newCode)) {
				stopFlag = 1;
			}
			
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//Resultsetオブジェクトがnullならばラベルを白紙にするメソッド
	public void reset(JTextField timeText, JComboBox codeBox, JComboBox nameBox, JTextField quantityText, 
			JTextField priceText, JLabel totalPriceLabel, JButton updateButton, JButton editButton) {
		timeText.setText("");
		codeBox.setSelectedItem(null);
		nameBox.setSelectedItem(null);
		quantityText.setText("");
		priceText.setText("");
		totalPriceLabel.setText("");
		updateButton.setEnabled(false);
		editButton.setText("追加");
	}
	
	//データを更新するメソッド
	public void update(JTextField timeText, JComboBox codeBox, JTextField quantityText,JTextField priceText, int x) {
		//すべての入力欄に空欄がなければデータを更新する
		if(!timeText.getText().equals("") && codeBox.getSelectedItem() != null && 
				!quantityText.getText().equals("") && !priceText.getText().equals("")) {
			
			int row = (int)Math.floor((Integer.parseInt(showNumberLabel.getText()) - 1) / 10) * 10 + x;
			System.out.println(row);
			try {
				rs.absolute(row);
				rs.updateString("仕入日", timeText.getText());
				rs.updateString("商品コード", (String)codeBox.getSelectedItem());
				rs.updateString("仕入数", quantityText.getText());
				rs.updateString("仕入単価", priceText.getText());
				rs.updateRow();
				newTime = timeText.getText();
				newCode = (String) codeBox.getSelectedItem();
				System.out.println("仕入日:" + timeText.getText() + " 商品コード:" + codeBox.getSelectedItem() +
						" 仕入数" + quantityText.getText() + " 仕入単価:" + priceText.getText() + "で更新しました");
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
				//更新されたデータの店員コードと同じものがないままページ送りをストップした場合
				//このときページを一つ戻す必要がある
				if(stopFlag == 2) {
					System.out.println("2の処理");
					//nowが10で割り切れるとき
					if(now%10 == 0) {
						// 現在行を前ページの先頭に戻す
						now -= 10; 
					} 
					//nowが10で割り切れないとき
					else {
						// 現在行を前ページの先頭のひとつ前に戻す
						now = 10 * (int)Math.floor((now-1)/10) - 10; 
					}
					//nowが0未満になったとき
					if(now < 0) {
						//nowを0にする
						now = 0;
					}
					rs.absolute(now);
					result(); //検索結果を表示
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
				System.out.println("データを更新できませんでした" + "\r" + "データが正しく入力されているか確認してください" +
				"\r" + "例：仕入日が形式通りに入力されていない");
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		} else { //一つでも空欄がある場合、その旨のメッセージを出す
			System.out.println("未入力の項目があります");
		}
	}
	
	//データを追加するメソッド
	public void insert(JTextField timeText, JComboBox codeBox, JTextField quantityText, JTextField priceText) {
		//すべての入力欄に空欄がなければデータを追加する
		if(!timeText.getText().equals("") && codeBox.getSelectedItem() != null && 
				!quantityText.getText().equals("") && !priceText.getText().equals("")) { 
			
			String SQL = "INSERT INTO 在庫マスタ (仕入日, 商品コード, 仕入数, 仕入単価) values ('" + 
					timeText.getText() + "', '" + codeBox.getSelectedItem() + "', " + quantityText.getText() + 
					", " + priceText.getText() + ");";
			try {
				stmt = LoginGUI.conn.createStatement();
				stmt.execute(SQL);
				newTime = timeText.getText();
				newCode = (String) codeBox.getSelectedItem();
				System.out.println(SQL + "で追加しました");
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
				//更新されたデータの店員コードと同じものがないままページ送りをストップした場合
				//このときページを一つ戻す必要がある
				if(stopFlag == 2) {
					System.out.println("2の処理");
					//nowが10で割り切れるとき
					if(now%10 == 0) {
						// 現在行を前ページの先頭に戻す
						now -= 10; 
					} 
					//nowが10で割り切れないとき
					else {
						// 現在行を前ページの先頭のひとつ前に戻す
						now = 10 * (int)Math.floor((now-1)/10) - 10; 
					}
					//nowが0未満になったとき
					if(now < 0) {
						//nowを0にする
						now = 0;
					}
					rs.absolute(now);
					result(); //検索結果を表示
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
				System.out.println("データを追加できませんでした" + "\r" + "データが正しく入力されているか確認してください" +
				"\r" + "例：仕入日が形式通りに入力されていない");
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		} else { //一つでも空欄がある場合、その旨のメッセージを出す
			System.out.println("未入力の項目があります");
		}
	}
	
	//データを消去するメソッド
	public void delete(JTextField timeText, JComboBox codeBox, JTextField quantityText, JTextField priceText) {
		//仕入日と商品コードが空欄でなければデータを消去する
		if(!timeText.getText().equals("") && codeBox.getSelectedItem() != null) { 
			
			String SQL = "delete from 在庫マスタ where 仕入日 = '" + timeText.getText() + 
					"' AND 商品コード = '" + codeBox.getSelectedItem() + "';";
			
			try {
				stmt = LoginGUI.conn.createStatement();
				stmt.execute(SQL);
				System.out.println(SQL + "で削除しました");
				//データを消去したのち、表を再取得して消去したデータがあったページへ飛ぶ
				int n = (int)Math.floor((Integer.parseInt(showNumberLabel.getText()) - 1) / 10) + 1 ;
				allShow();
				rs.beforeFirst();
				for(int i = 1; i <= n; i++) {
					result();
				}
			}catch(SQLException e2) {
				e2.printStackTrace();
				System.out.println("データを削除できませんでした" + "\r" + "データが正しく入力されているか確認してください" +
				"\r" + "例：仕入日が形式通りに入力されていない");
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		} else {
			System.out.println("仕入日または商品コードが空欄のため消去できませんでした");
		}
	}

	//現在の「年」「月」「日」を取得するメソッド
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
	
	//商品コードを選択するコンボボックスに項目を追加するメソッド
	public void addCode(JComboBox codeBox) {
		codeBox.addItem(null);
		try {
			SQL = "SELECT 商品コード FROM 商品マスタ WHERE 削除フラグ = 0;";
			stmt = LoginGUI.conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				codeBox.addItem(rs.getString("商品コード"));
			}
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	//商品名を選択するコンボボックスに項目を追加するメソッド
	public void addName(JComboBox nameBox) {
		nameBox.addItem(null);
		try {
			SQL = "SELECT 商品名 FROM 商品マスタ WHERE 削除フラグ = 0;";
			stmt = LoginGUI.conn.createStatement();
			rs = stmt.executeQuery(SQL);
			while(rs.next()){
				nameBox.addItem(rs.getString("商品名"));
			}
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//商品コードに対応した商品名を選択するメソッド
	public void setName(JComboBox codeBox, JComboBox nameBox) {
		SQL = "SELECT 商品名 FROM 商品マスタ WHERE 商品コード ='" + codeBox.getSelectedItem() + "';";
		try {
			otherStmt = LoginGUI.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			otherRs = otherStmt.executeQuery(SQL);
			while(otherRs.next()){
				nameBox.setSelectedItem(otherRs.getString("商品名"));
			}
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
	//商品名に対応した商品コードを選択するメソッド
	public void setCode(JComboBox codeBox, JComboBox nameBox) {
		SQL = "SELECT 商品コード FROM 商品マスタ WHERE 商品名 ='" + nameBox.getSelectedItem() + "';";
		try {
			otherStmt = LoginGUI.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			otherRs = otherStmt.executeQuery(SQL);
			while(otherRs.next()){
				codeBox.setSelectedItem(otherRs.getString("商品コード"));
			}
		}catch(SQLException e2) {
			e2.printStackTrace();
		}catch(Exception e2) {
			e2.printStackTrace();
		}
	}
}