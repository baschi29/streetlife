package infpp.streetlife.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import infpp.streetlife.FileLoader;


/**
 * The HelpDialog is a custom PopUp window that tells the user some "helpful" information. Sources its information from a local file
 * @author Cornelius, Bastian
 * @version 1.0
 * @since 2022-01-28
 *
 */

@SuppressWarnings("serial")
public class HelpDialog extends JDialog {

	//path used for the information for the JTextArea
	private final String HELP_PATH = "txt/help.txt";
	
	//path used for the image used
	private final String FROG_PATH = "img/frog_sad.png";
	
	//JPanel everything is based upon
	private final JPanel contentPanel = new JPanel();
	
	private FileLoader fl = new FileLoader();



	/**
	 * Launch the application. Used for debug
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
	 * Create and built the dialog.
	 */
	public HelpDialog() {
		setType(Type.NORMAL);
		setBounds(100, 100, 440, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		ImageIcon img;
		try {
			img = fl.loadImageIcon(FROG_PATH);
			this.setIconImage(img.getImage());
			
			{
				JLabel imgpanel = new JLabel();
				imgpanel.setIcon(new ImageIcon(img.getImage().getScaledInstance(128, 128, Image.SCALE_DEFAULT)));
				contentPanel.add(imgpanel, BorderLayout.WEST);
				
				
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		String labelcontent = "File not found";
		try {
			labelcontent = fl.loadFileAsString(HELP_PATH);
		} catch (IOException e1) {
	
			e1.printStackTrace();
		}
		
		JTextArea txtrD = new JTextArea(5, 20);
		txtrD.setText(labelcontent);
		txtrD.setLineWrap(true);
		txtrD.setWrapStyleWord(true);
		txtrD.setEditable(false);
		contentPanel.add(txtrD, BorderLayout.CENTER);
		
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
		}
	}
}
