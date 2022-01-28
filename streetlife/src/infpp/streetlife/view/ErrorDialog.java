package infpp.streetlife.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.TrayIcon.MessageType;
/**
 * The ErrorDialog is a custom PopUp window that notifies the user after encountering an error. Includes a system-notification for extra attention.
 * @author Cornelius
 * @version 1.0
 * @since 2022-01-28
 *
 */
public class ErrorDialog extends JDialog {

	// JPanel everything is based upon
	private final JPanel contentPanel = new JPanel();

	//path to the used image
	private final String FROG_PATH = "/frog_dead.png";

	/**
	 * Launch the application. Used for debug
	 */
	public static void main(String[] args) {
		try {
			ErrorDialog dialog = new ErrorDialog(new Exception("Do not worry! This is an example error message, nothing actually went wrong. If you read this as a normal user, please ignore it"));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create and build the dialog-window.
	 */
	public ErrorDialog(Exception exc) {
		
		this.createSystemTray();
		
		setType(Type.POPUP);
		setBounds(100, 100, 440, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		ImageIcon img = new ImageIcon(this.getClass().getResource(FROG_PATH));
		this.setIconImage(img.getImage());
		
		JTextArea txtrD = new JTextArea(5, 20);
		txtrD.setText(exc.getLocalizedMessage());
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
				
				//If Button "okay" is pressed, the user accepted his/her fate. Dispose the window
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
	
	
	/**
	 * Creates an annoying SystemTray-Error notification with the system sound-effect. Used for alerting the user
	 */
	private void createSystemTray() {
		
		try {
			//Obtain only one instance of the SystemTray object
			SystemTray tray = SystemTray.getSystemTray();
			Image image = Toolkit.getDefaultToolkit().createImage("/frog.png");

			TrayIcon trayIcon = new TrayIcon(image, "Froschsimulator" );
			//Let the system resize the image if needed
			trayIcon.setImageAutoSize(true);
			//Set tooltip text for the tray icon
			trayIcon.setToolTip("Froschsimulator");
			tray.add(trayIcon);
	    
			//print out the notification
			trayIcon.displayMessage("Froschsimulator 2022", "There is a problem with the simulation", MessageType.ERROR);
		}
			catch(Exception exc){
				exc.printStackTrace();
		}
	}
}

