import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ManagementGUI extends JFrame implements ActionListener{
	
	JButton salesInputButton = new JButton("売上入力システム"); //データベースに追加するボタン
	JButton salesSearchButton = new JButton("売上検索システム"); //白紙にするボタン

	JPanel panel1 = new JPanel(); //コンポーネントを置くパネル
	JPanel panel2 = new JPanel();

	
	ManagementGUI(){
		setTitle("売上管理システム");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new FlowLayout());
		
		panel1.add(salesInputButton);
		panel1.add(salesSearchButton);
		
		getContentPane().add(panel1);

		salesInputButton.addActionListener(this);
		salesSearchButton.addActionListener(this);
		this.pack();
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new ManagementGUI();
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == salesInputButton){
			new SalesInputGUI();
		}
	}
}