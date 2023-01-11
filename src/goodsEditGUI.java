import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class goodsEditGUI extends JFrame implements ActionListener{
	goodsEditGUI(){
		setTitle("商品マスタ編集");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		this.pack();
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
	}
}
