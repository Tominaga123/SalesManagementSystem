import javax.swing.JFrame;

public class ManagementGUI extends JFrame{
	ManagementGUI(){
		setTitle("電卓");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350,400);
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new ManagementGUI();
	}

}
