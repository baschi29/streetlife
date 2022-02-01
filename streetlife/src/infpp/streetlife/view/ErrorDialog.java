package infpp.streetlife.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
 * The ErrorDialog is a custom PopUp window that notifies the user after encountering an error. Includes a system-notification for extra attention.
 * @author Cornelius, Bastian
 * @version 1.0
 * @since 2022-01-28
 *
 */
public class ErrorDialog extends JDialog {

	// JPanel everything is based upon
	private final JPanel contentPanel = new JPanel();

	//path to the used image
	private final String FROG_PATH = "img/frog_dead.png";
	
	private FileLoader fl = new FileLoader();

	private SystemTray tray;
	private TrayIcon trayIcon;
	
	
	/**
	 * Launch the application. Used for debug
	 */
	public static void main(String[] args) {
		try {
			ErrorDialog dialog = new ErrorDialog(new Exception("Do not worry! This is an example error message, nothing actually went wrong. If you read this as a normal user, please ignore it"));
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JTextArea txtrD = new JTextArea(5, 20);
		txtrD.setText(exc.getLocalizedMessage());
		txtrD.setLineWrap(true);
		txtrD.setWrapStyleWord(true);
		txtrD.setEditable(false);
		contentPanel.add(txtrD, BorderLayout.CENTER);
		
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				
				//If Button "okay" is pressed, the user accepted his/her fate. Dispose the window
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeProgram();
					}
				});
				okButton.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		//dont just dispose when closing, but remove the systemTrayIcon
		this.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		        closeProgram();
		    }
		});
	}
	
	
	/**
	 * Creates an annoying SystemTray-Error notification with the system sound-effect. Used for alerting the user
	 */
	private void createSystemTray() {
		
		try {
			//Obtain only one instance of the SystemTray object
			tray = SystemTray.getSystemTray();
			Image image = fl.loadImage(FROG_PATH); //Toolkit.getDefaultToolkit().createImage("/frog.png");

		    trayIcon = new TrayIcon(image, "Froschsimulator" );
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
	
	/**
	 * used for removing the SystemTrayIcon before disposing, so nothing gets left behind
	 */
	private void closeProgram() {
		this.tray.remove(trayIcon);
		this.dispose();
		
	}
}

