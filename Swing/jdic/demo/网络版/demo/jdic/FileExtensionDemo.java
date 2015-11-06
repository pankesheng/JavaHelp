package demo.jdic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.jdesktop.jdic.desktop.Desktop;
import org.jdesktop.jdic.desktop.DesktopException;
import org.jdesktop.jdic.filetypes.Action;
import org.jdesktop.jdic.filetypes.Association;
import org.jdesktop.jdic.filetypes.AssociationAlreadyRegisteredException;
import org.jdesktop.jdic.filetypes.AssociationService;
import org.jdesktop.jdic.filetypes.RegisterFailedException;

/**
 * 
 * @author MAbernethy
 */
public class FileExtensionDemo extends JFrame implements ActionListener
{

	private javax.swing.JPanel jPanel = null;
	private javax.swing.JPanel jPanel1 = null;
	private javax.swing.JPanel jPanel2 = null;
	private javax.swing.JTextField txtFile = null;
	private javax.swing.JButton browseButton = null;
	private javax.swing.JButton openButton = null;
	private javax.swing.JTextField txtVerb = null;
	private javax.swing.JTextField txtCommand = null;
	private javax.swing.JLabel jLabel = null;
	private javax.swing.JLabel jLabel1 = null;
	private javax.swing.JLabel jLabel2 = null;
	private javax.swing.JTextField txtExt = null;
	private javax.swing.JButton registerButton = null;
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == getBrowseButton())
		{
			JFileChooser chooser = new JFileChooser();
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) 
			{
				String file = chooser.getSelectedFile().getAbsolutePath();
				getTxtFile().setText(file);
			}			
		}
		
		else if (e.getSource() == getOpenButton())
		{
			try
			{
				Desktop.open(new File(getTxtFile().getText()));
			}
			catch (DesktopException ex) 
			{
				ex.printStackTrace();
			}
		}
		
		else if (e.getSource() == getRegisterButton())
		{
			String verb = getTxtVerb().getText();
			String command = getTxtCommand().getText();
			String fileExt = getTxtExt().getText();
			
			Action action = new Action(verb, command);
			Association association = new Association();
			association.addAction(action);
			association.addFileExtension(fileExt);
			AssociationService associationService = new AssociationService();
			try
			{
				associationService.registerSystemAssociation(association);
			}
			catch (RegisterFailedException ex)
			{
				ex.printStackTrace();
			}
			catch (AssociationAlreadyRegisteredException ex)
			{
				ex.printStackTrace();
			}
			
		}
	}
	
	
	
	
	
	
	private void initialize() {
        this.setContentPane(getJPanel());
        this.setSize(528, 249);
        this.setTitle("File Extension Demo");
			
	}
	public static void main(String[] args)
	{
		FileExtensionDemo d = new FileExtensionDemo();
	}
	
	public FileExtensionDemo()
	{
		super("File Extension Demo");
		initialize();
		setVisible(true);
	}

	private javax.swing.JPanel getJPanel() {
		if(jPanel == null) {
			jPanel = new javax.swing.JPanel();
			java.awt.GridBagConstraints consGridBagConstraints20 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints21 = new java.awt.GridBagConstraints();
			consGridBagConstraints20.insets = new java.awt.Insets(7,6,4,8);
			consGridBagConstraints20.fill = java.awt.GridBagConstraints.BOTH;
			consGridBagConstraints20.weighty = 1.0;
			consGridBagConstraints20.weightx = 1.0;
			consGridBagConstraints20.gridy = 0;
			consGridBagConstraints20.gridx = 0;
			consGridBagConstraints21.insets = new java.awt.Insets(4,6,11,8);
			consGridBagConstraints21.fill = java.awt.GridBagConstraints.BOTH;
			consGridBagConstraints21.weighty = 1.0;
			consGridBagConstraints21.weightx = 1.0;
			consGridBagConstraints21.gridy = 1;
			consGridBagConstraints21.gridx = 0;
			jPanel.setLayout(new java.awt.GridBagLayout());
			jPanel.add(getJPanel1(), consGridBagConstraints20);
			jPanel.add(getJPanel2(), consGridBagConstraints21);
		}
		return jPanel;
	}

	private javax.swing.JPanel getJPanel1() {
		if(jPanel1 == null) {
			jPanel1 = new javax.swing.JPanel();
			java.awt.GridBagConstraints consGridBagConstraints9 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints10 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints8 = new java.awt.GridBagConstraints();
			consGridBagConstraints9.insets = new java.awt.Insets(14,4,19,5);
			consGridBagConstraints9.ipady = -5;
			consGridBagConstraints9.ipadx = 3;
			consGridBagConstraints9.gridy = 0;
			consGridBagConstraints9.gridx = 1;
			consGridBagConstraints10.insets = new java.awt.Insets(13,5,19,9);
			consGridBagConstraints10.ipady = -4;
			consGridBagConstraints10.ipadx = 13;
			consGridBagConstraints10.gridy = 0;
			consGridBagConstraints10.gridx = 2;
			consGridBagConstraints8.insets = new java.awt.Insets(14,14,20,4);
			consGridBagConstraints8.ipadx = 293;
			consGridBagConstraints8.fill = java.awt.GridBagConstraints.HORIZONTAL;
			consGridBagConstraints8.weightx = 1.0;
			consGridBagConstraints8.gridy = 0;
			consGridBagConstraints8.gridx = 0;
			jPanel1.setLayout(new java.awt.GridBagLayout());
			jPanel1.add(getTxtFile(), consGridBagConstraints8);
			jPanel1.add(getBrowseButton(), consGridBagConstraints9);
			jPanel1.add(getOpenButton(), consGridBagConstraints10);
			jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Open a File Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
		}
		return jPanel1;
	}

	private javax.swing.JPanel getJPanel2() {
		if(jPanel2 == null) {
			jPanel2 = new javax.swing.JPanel();
			java.awt.GridBagConstraints consGridBagConstraints13 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints14 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints15 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints16 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints17 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints18 = new java.awt.GridBagConstraints();
			java.awt.GridBagConstraints consGridBagConstraints19 = new java.awt.GridBagConstraints();
			consGridBagConstraints16.insets = new java.awt.Insets(10,21,17,10);
			consGridBagConstraints16.ipady = 4;
			consGridBagConstraints16.ipadx = 8;
			consGridBagConstraints16.gridy = 1;
			consGridBagConstraints16.gridx = 0;
			consGridBagConstraints15.insets = new java.awt.Insets(9,33,9,13);
			consGridBagConstraints15.ipady = 2;
			consGridBagConstraints15.ipadx = 24;
			consGridBagConstraints15.gridy = 0;
			consGridBagConstraints15.gridx = 0;
			consGridBagConstraints13.insets = new java.awt.Insets(8,11,8,30);
			consGridBagConstraints13.ipadx = 77;
			consGridBagConstraints13.fill = java.awt.GridBagConstraints.HORIZONTAL;
			consGridBagConstraints13.weightx = 1.0;
			consGridBagConstraints13.gridy = 0;
			consGridBagConstraints13.gridx = 1;
			consGridBagConstraints17.insets = new java.awt.Insets(9,30,9,8);
			consGridBagConstraints17.ipady = 2;
			consGridBagConstraints17.ipadx = 11;
			consGridBagConstraints17.gridy = 0;
			consGridBagConstraints17.gridx = 2;
			consGridBagConstraints14.insets = new java.awt.Insets(10,11,17,18);
			consGridBagConstraints14.ipadx = 216;
			consGridBagConstraints14.fill = java.awt.GridBagConstraints.HORIZONTAL;
			consGridBagConstraints14.weightx = 1.0;
			consGridBagConstraints14.gridwidth = 2;
			consGridBagConstraints14.gridy = 1;
			consGridBagConstraints14.gridx = 1;
			consGridBagConstraints18.insets = new java.awt.Insets(8,9,8,65);
			consGridBagConstraints18.ipadx = 72;
			consGridBagConstraints18.fill = java.awt.GridBagConstraints.HORIZONTAL;
			consGridBagConstraints18.weightx = 1.0;
			consGridBagConstraints18.gridy = 0;
			consGridBagConstraints18.gridx = 3;
			consGridBagConstraints19.insets = new java.awt.Insets(8,25,15,22);
			consGridBagConstraints19.ipady = -2;
			consGridBagConstraints19.ipadx = 21;
			consGridBagConstraints19.gridy = 1;
			consGridBagConstraints19.gridx = 3;
			jPanel2.setLayout(new java.awt.GridBagLayout());
			jPanel2.add(getTxtVerb(), consGridBagConstraints13);
			jPanel2.add(getTxtCommand(), consGridBagConstraints14);
			jPanel2.add(getJLabel(), consGridBagConstraints15);
			jPanel2.add(getJLabel1(), consGridBagConstraints16);
			jPanel2.add(getJLabel2(), consGridBagConstraints17);
			jPanel2.add(getTxtExt(), consGridBagConstraints18);
			jPanel2.add(getRegisterButton(), consGridBagConstraints19);
			jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Register a File Extension", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
		}
		return jPanel2;
	}

	private javax.swing.JTextField getTxtFile() {
		if(txtFile == null) {
			txtFile = new javax.swing.JTextField();
		}
		return txtFile;
	}

	private javax.swing.JButton getBrowseButton() {
		if(browseButton == null) {
			browseButton = new javax.swing.JButton();
			browseButton.setText("Browse");
			browseButton.addActionListener(this);
		}
		return browseButton;
	}

	private javax.swing.JButton getOpenButton() {
		if(openButton == null) {
			openButton = new javax.swing.JButton();
			openButton.setText("Open");
			openButton.addActionListener(this);
		}
		return openButton;
	}

	private javax.swing.JTextField getTxtVerb() {
		if(txtVerb == null) {
			txtVerb = new javax.swing.JTextField();
		}
		return txtVerb;
	}

	private javax.swing.JTextField getTxtCommand() {
		if(txtCommand == null) {
			txtCommand = new javax.swing.JTextField();
		}
		return txtCommand;
	}

	private javax.swing.JLabel getJLabel() {
		if(jLabel == null) {
			jLabel = new javax.swing.JLabel();
			jLabel.setText("Verb");
			jLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		}
		return jLabel;
	}

	private javax.swing.JLabel getJLabel1() {
		if(jLabel1 == null) {
			jLabel1 = new javax.swing.JLabel();
			jLabel1.setText("Command");
			jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		}
		return jLabel1;
	}

	private javax.swing.JLabel getJLabel2() {
		if(jLabel2 == null) {
			jLabel2 = new javax.swing.JLabel();
			jLabel2.setText("File Extension");
			jLabel2.setToolTipText("");
			jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		}
		return jLabel2;
	}

	private javax.swing.JTextField getTxtExt() {
		if(txtExt == null) {
			txtExt = new javax.swing.JTextField();
		}
		return txtExt;
	}

	private javax.swing.JButton getRegisterButton() {
		if(registerButton == null) {
			registerButton = new javax.swing.JButton();
			registerButton.setText("Register");
			registerButton.addActionListener(this);
		}
		return registerButton;
	}
}  //  @jve:visual-info  decl-index=0 visual-constraint="10,10"
