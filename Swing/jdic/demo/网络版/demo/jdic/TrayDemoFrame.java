package demo.jdic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;

import org.jdesktop.jdic.tray.SystemTray;
import org.jdesktop.jdic.tray.TrayIcon;

/**
 * 
 * @author MAbernethy
 */
public class TrayDemoFrame extends JFrame
{
	private javax.swing.JButton btnAlert = null;
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
        this.setContentPane(getBtnAlert());
        this.setSize(243, 104);
			
	}
	public static void main(String args[])
	{
		TrayDemoFrame d = new TrayDemoFrame();
	}
	
	public TrayDemoFrame()
	{
		super();
		initialize();
		init();
		setVisible(true);
	}
	
	protected void init()
	{
		SystemTray tray = SystemTray.getDefaultSystemTray(); 
		final TrayIcon trayIcon = new TrayIcon(getTrayIcon(), "Tray Demo", getPopupMenu());
		trayIcon.setIconAutoSize(true);
		
		trayIcon.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				setVisible(true);
			}
		});
		tray.addTrayIcon(trayIcon);
		getBtnAlert().addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				trayIcon.displayMessage("Alert", "This is an Alert Message", TrayIcon.INFO_MESSAGE_TYPE);
			}
		});
	}
	
	public void windowClosed(WindowEvent e)
	{
		setVisible(false);
	}
	
	public void windowClosing(WindowEvent e)
	{
		setVisible(false);
	}
	
	private ImageIcon getTrayIcon()
	{
		return new ImageIcon(TrayDemoFrame.class.getResource("images/icon.png"));
	}
	
	private JPopupMenu getPopupMenu()
	{
		JPopupMenu pop = new JPopupMenu();
		
		JMenuItem option1 = new JMenuItem("Option 1");
		option1.setMnemonic('o');
		option1.setIcon(new ImageIcon(TrayDemoFrame.class.getResource("images/menu_o.png")));
		pop.add(option1);
		
		JMenu menu = new JMenu("Choices");
		menu.setIcon(new ImageIcon(TrayDemoFrame.class.getResource("images/menu_q.png")));
		JRadioButtonMenuItem radio1 = new JRadioButtonMenuItem("Choice 1");
		JRadioButtonMenuItem radio2 = new JRadioButtonMenuItem("Choice 2");
		JRadioButtonMenuItem radio3 = new JRadioButtonMenuItem("Choice 3");
		menu.add(radio1);
		menu.add(radio2);
		menu.add(radio3);
		pop.add(menu);
		
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setIcon(new ImageIcon(TrayDemoFrame.class.getResource("images/menu_x.png")));
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		}		
		);
		pop.add(new JSeparator());
		pop.add(exit);
		return pop;
	}
	/**
	 * This method initializes btnAlert
	 * 
	 * @return javax.swing.JButton
	 */
	private javax.swing.JButton getBtnAlert() {
		if(btnAlert == null) {
			btnAlert = new javax.swing.JButton();
			btnAlert.setText("Alert");
		}
		return btnAlert;
	}
}  //  @jve:visual-info  decl-index=0 visual-constraint="10,10"
