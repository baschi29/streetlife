package infpp.streetlife.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;

import javax.swing.JTextArea;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class CloseDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private final String FROG_PATH = "/frog_sad.png";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CloseDialog dialog = new CloseDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CloseDialog() {
		setType(Type.POPUP);
		setBounds(100, 100, 440, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		ImageIcon img = new ImageIcon(this.getClass().getResource(FROG_PATH));
		this.setIconImage(img.getImage());
		
		JLabel lblNewLabel = new JLabel("<html>" + "Wait! You are about to leave the fabalous world of Froschsimulator 2022. Do you really wish to proceed, resulting in the execution of your lovely frog?" + "</html>");
		contentPanel.add(lblNewLabel, BorderLayout.CENTER);
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
						System.exit(0);
					}
				});
				okButton.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setHorizontalAlignment(SwingConstants.RIGHT);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
