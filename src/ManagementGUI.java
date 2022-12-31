import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ManagementGUI extends JFrame implements ActionListener{
	
	JLabel timeLabel = new JLabel("販売日時"); //販売日時の入力欄であることを示すラベル
	JComboBox yearComboBox = new JComboBox(); //販売日時の入力欄 
	JLabel yearLabel = new JLabel("年"); //単位「年」を表示するラベル
	JComboBox monthComboBox = new JComboBox();
	JLabel monthLabel = new JLabel("月"); //単位「月」を表示するラベル
	JComboBox dateComboBox = new JComboBox();
	JLabel dateLabel = new JLabel("日"); //単位「日」を表示するラベル
	JComboBox hourComboBox = new JComboBox();
	JLabel hourLabel = new JLabel("時"); //単位「時」を表示するラベル
	JComboBox minuteComboBox = new JComboBox();
	JLabel minuteLabel = new JLabel("分"); //単位「分」を表示するラベル
	
	JLabel clerkCodeLabel = new JLabel("店員コード"); //店員コードの選択欄であることを示すラベル
	JComboBox clerkCodeComboBox = new JComboBox(); //店員コードの選択欄 
	
	JLabel goodsCode1Label = new JLabel("商品コード"); //商品コードの選択欄であることを示すラベル
	JComboBox goodsCode1ComboBox = new JComboBox(); //商品コードの選択欄 
	JLabel goodsName1Label = new JLabel("商品1"); //商品の名前を表示するラベル
	JLabel count1Label = new JLabel("個数"); //商品の個数の入力欄であることを示すラベル
	JTextField count1TextField = new JTextField(3); //個数の入力欄
	JLabel subTotal1Label = new JLabel("￥0"); //小計を表示するラベル
	
	JLabel goodsCode2Label = new JLabel("商品コード");
	JComboBox goodsCode2ComboBox = new JComboBox();
	JLabel goodsName2Label = new JLabel("商品2");
	JLabel count2Label = new JLabel("個数"); 
	JTextField count2TextField = new JTextField(3);
	JLabel subTotal2Label = new JLabel("￥0"); 
	
	JLabel goodsCode3Label = new JLabel("商品コード");
	JComboBox goodsCode3ComboBox = new JComboBox();
	JLabel goodsName3Label = new JLabel("商品3");
	JLabel count3Label = new JLabel("個数"); 
	JTextField count3TextField = new JTextField(3);
	JLabel subTotal3Label = new JLabel("￥0"); 
	
	JLabel totalLabel = new JLabel("合計 ￥0"); //合計金額を表示するラベル
	JLabel taxLabel = new JLabel("(内消費税等 ￥0)"); //消費税額を表示するラベル
	JLabel numberLabel = new JLabel("0000"); //レシートの通番を表示するラベル（フレーム生成時にデータベースから取得）
	
	JButton insertButton = new JButton("追加"); //データベースに追加するボタン
	JButton resetButton = new JButton("リセット"); //白紙にするボタン
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();
	JPanel panel5 = new JPanel();
	JPanel panel6 = new JPanel();
	JPanel panel7 = new JPanel();
	JPanel panel8 = new JPanel();
	JPanel panel9 = new JPanel();
	
	ManagementGUI(){
		setTitle("売上管理システム");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		panel3.setLayout(new FlowLayout());
		panel4.setLayout(new FlowLayout());
		panel5.setLayout(new FlowLayout());
		panel6.setLayout(new FlowLayout());
		panel7.setLayout(new FlowLayout());
		panel8.setLayout(new FlowLayout());
		panel9.setLayout(new FlowLayout());
		
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
		//店員コードを選択するコンボボックスに項目を追加
		for(int i = 1; i <= 10; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = "00" + s;
			}else {
				s = 0 + s;
			}
			clerkCodeComboBox.addItem(s);
		}
		clerkCodeComboBox.setSelectedItem(null);
		//商品コードを選択するコンボボックスに項目を追加
		for(int i = 1; i <= 20; i++) {
			String s = Integer.valueOf(i).toString();
			if(i <= 9) {
				s = "00" + s;
			}else {
				s = 0 + s;
			}
			goodsCode1ComboBox.addItem(s);
			goodsCode2ComboBox.addItem(s);
			goodsCode3ComboBox.addItem(s);
		}
		goodsCode1ComboBox.setSelectedItem(null);
		goodsCode2ComboBox.setSelectedItem(null);
		goodsCode3ComboBox.setSelectedItem(null);
		
		panel1.add(timeLabel);
		panel1.add(yearComboBox);
		panel1.add(yearLabel);
		panel1.add(monthComboBox);
		panel1.add(monthLabel);
		panel1.add(dateComboBox);
		panel1.add(dateLabel);
		panel1.add(hourComboBox);
		panel1.add(hourLabel);
		panel1.add(minuteComboBox);
		panel1.add(minuteLabel);
		
		panel2.add(clerkCodeLabel);
		panel2.add(clerkCodeComboBox);
		
		panel3.add(goodsCode1Label);
		panel3.add(goodsCode1ComboBox);
		panel3.add(goodsName1Label);
		panel3.add(count1Label);
		panel3.add(count1TextField);
		panel3.add(subTotal1Label);
		
		panel4.add(goodsCode2Label);
		panel4.add(goodsCode2ComboBox);
		panel4.add(goodsName2Label);
		panel4.add(count2Label);
		panel4.add(count2TextField);
		panel4.add(subTotal2Label);
		
		panel5.add(goodsCode3Label);
		panel5.add(goodsCode3ComboBox);
		panel5.add(goodsName3Label);
		panel5.add(count3Label);
		panel5.add(count3TextField);
		panel5.add(subTotal3Label);
		
		panel6.add(totalLabel);
		panel7.add(taxLabel);
		panel8.add(numberLabel);
		
		panel9.add(insertButton);
		panel9.add(resetButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		getContentPane().add(panel4);
		getContentPane().add(panel5);
		getContentPane().add(panel6);
		getContentPane().add(panel7);
		getContentPane().add(panel8);
		getContentPane().add(panel9);
		
		goodsCode1ComboBox.addActionListener(this);
		goodsCode2ComboBox.addActionListener(this);
		goodsCode3ComboBox.addActionListener(this);
		insertButton.addActionListener(this);
		resetButton.addActionListener(this);
		this.pack();
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new ManagementGUI();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == goodsCode1ComboBox){
			goodsName1Label.setText((String) goodsCode1ComboBox.getSelectedItem());
		}if(e.getSource() == goodsCode2ComboBox){
			goodsName3Label.setText((String) goodsCode2ComboBox.getSelectedItem());
		}if(e.getSource() == goodsCode3ComboBox){
			goodsName3Label.setText((String) goodsCode3ComboBox.getSelectedItem());
		}else if(e.getSource() == insertButton) {
			System.out.println("販売日時:" + yearComboBox.getSelectedItem() + "年" + monthComboBox.getSelectedItem() + "月" +
					            dateComboBox.getSelectedItem() + "日" + hourComboBox.getSelectedItem() + "時" +
					            minuteComboBox.getSelectedItem() + "分" + "\n" + "店員コード:"+ clerkCodeComboBox.getSelectedItem() +
					            "\n" + "商品コード:" + goodsCode1ComboBox.getSelectedItem() + " 商品名:" + goodsName1Label.getText() + 
					            " 個数:" + count1TextField.getText() + " 小計:" + subTotal1Label.getText() + "\n" +
					            "商品コード:" + goodsCode2ComboBox.getSelectedItem() + " 商品名:" + goodsName2Label.getText() + 
					            " 個数:" + count2TextField.getText() + " 小計:" + subTotal2Label.getText() + "\n" +
					            "商品コード:" +	 goodsCode3ComboBox.getSelectedItem() + " 商品名:" + goodsName3Label.getText() + 
					            " 個数:" + count3TextField.getText() + " 小計:" + subTotal3Label.getText() + "\n" +
					            "合計:" + totalLabel.getText() + " 内消費税等" + taxLabel.getText() + "\n" + 
					            "レシート通番:" + numberLabel.getText() + "\n" + "をデータベースに追加しました"
					            );
			reset();
		}else if(e.getSource() == resetButton) {
			reset();
		}	
	}
	
	//入力欄をリセットするメソッド
	public void reset() {
		yearComboBox.setSelectedIndex(0);
		monthComboBox.setSelectedIndex(0);
		dateComboBox.setSelectedIndex(0);
		hourComboBox.setSelectedIndex(0);
		minuteComboBox.setSelectedIndex(0);
		clerkCodeComboBox.setSelectedIndex(0);
		goodsCode1ComboBox.setSelectedIndex(0);
		goodsName1Label.setText("商品1");
		count1TextField.setText(null);
		goodsCode2ComboBox.setSelectedIndex(0);
		goodsName2Label.setText("商品2");
		count2TextField.setText(null);
		goodsCode3ComboBox.setSelectedIndex(0);
		goodsName3Label.setText("商品3");
		count3TextField.setText(null);
		totalLabel.setText("合計 ￥0");
		taxLabel.setText("(内消費税等 ￥0)"); 
		numberLabel = new JLabel("0000"); 
	}
	
	//コンボボックスを現在の日時で初期化するためのメソッド
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
