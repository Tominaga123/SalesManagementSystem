import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginGUI extends JFrame implements ActionListener{
	
	JTextField userNameTextField = new JTextField("店員1", 15); //ユーザー名の入力欄
	JTextField passwordTextField = new JTextField("password",15); //パスワードの入力欄
	JButton loginButton = new JButton("ログイン"); //ログインボタン
	
	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel(); 
	JPanel panel3 = new JPanel();
	
	//データベースへの接続で使用
	public static String URL = "jdbc:mysql://127.0.0.1:3306/販売管理"; 
	public static String USER;
	public static String PASS;
	public static Connection conn;

	LoginGUI(){
		setTitle("販売管理システム");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		panel2.setLayout(new FlowLayout());
		
		panel1.add(userNameTextField);
		panel2.add(passwordTextField);
		panel3.add(loginButton);
		
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(panel3);
		
		loginButton.addActionListener(this);
		setSize(350,140);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new LoginGUI();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton){ 
			//ユーザー名とパスワードが正しければデータベースに接続し、管理画面を開く
			if(userNameTextField.getText().equals("店員1") && passwordTextField.getText().equals("password")) {
				USER = "店員1";
				PASS = "password";
				//データベースに接続
				try {
					conn = DriverManager.getConnection(LoginGUI.URL, LoginGUI.USER, LoginGUI.PASS);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				//管理画面を開く
				new ManagementGUI();
				setVisible(false);	
			}else if(userNameTextField.getText().equals("店員2") && passwordTextField.getText().equals("password2")) {
				USER = "店員2";
				PASS = "password2";
				try {
					conn = DriverManager.getConnection(LoginGUI.URL, LoginGUI.USER, LoginGUI.PASS);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				new ManagementGUI();
				setVisible(false);	
			}else {
				new MessageGUI();
			}
		}
	}
}

//
class MessageGUI extends JFrame  implements ActionListener{ 
	
	JLabel messageLabel = new JLabel("ユーザー名またはパスワードが正しくありません");
	JButton okButton = new JButton("OK");
	
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	
	MessageGUI(){ //文章の変更を保存するか確認する際のコンストラクタ
		setTitle("確認");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		//それぞれのパネルにコンポーネントを配置
		panel1.add(messageLabel);
		panel2.add(okButton);
		//パネルをコンテナに追加
		getContentPane().add(panel1);
		getContentPane().add(panel2);
		//イベント設定
		okButton.addActionListener(this);
		setSize(350,120);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == okButton) {
			setVisible(false);	
		} 
	}
}



