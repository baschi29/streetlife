package infpp.streetlife.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class HelpDialog extends JDialog {

	private final String HELP_PATH = "txt/help.txt";

	private final JPanel contentPanel = new JPanel();

	private final String FROG_PATH = "/frog_sad.png";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			HelpDialog dialog = new HelpDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public HelpDialog() {
		setType(Type.POPUP);
		setBounds(100, 100, 440, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		ImageIcon img = new ImageIcon(this.getClass().getResource(FROG_PATH));
		this.setIconImage(img.getImage());
		
		String labelcontent = this.loadFile(HELP_PATH);
		
		
		//JLabel lblNewLabel = new JLabel(labelcontent);
		JTextArea txtrD = new JTextArea(5, 20);
		txtrD.setText(labelcontent);
		txtrD.setLineWrap(true);
		txtrD.setWrapStyleWord(true);
		txtrD.setEditable(false);
		contentPanel.add(txtrD, BorderLayout.CENTER);
		{
			JLabel imgpanel = new JLabel();
			imgpanel.setIcon(new ImageIcon(img.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT)));
			contentPanel.add(imgpanel, BorderLayout.WEST);
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
//				JButton cancelButton = new JButton("Cancel");
//				cancelButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						dispose();
//					}
//				});
//				cancelButton.setHorizontalAlignment(SwingConstants.RIGHT);
//				cancelButton.setActionCommand("Cancel");
//				buttonPane.add(cancelButton);
			}
		}
	}

	private String loadFile(String filepath) {
		Scanner sc;
		String str = "";
		try {
			
			sc = new Scanner(new File(filepath));
			while(sc.hasNextLine()){
			    str += sc.nextLine();                     
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
