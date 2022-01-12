package infpp.streetlife.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import infpp.streetlife.model.StreetObject;

import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
/**
 *  The StreetGUI represents the graphical interface of the street with the displayment area, control buttons etc.
 * @author Cornelius
 *
 */
public class StreetGUI extends JFrame {

	private JPanel contentPane;
	private ArrayList<StreetObject> nonAddedCars;
	private ArrayList<StreetObject> addedCars;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StreetGUI frame = new StreetGUI(new ArrayList<StreetObject>());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StreetGUI(ArrayList<StreetObject> modelState) {
		nonAddedCars = modelState;
		
		setTitle("Froschsimulator 2022");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 460);
		Image img = new ImageIcon(this.getClass().getResource("/frog.png")).getImage();
		this.setIconImage(img);
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmFileMenuItemLoad = new JMenuItem("Load");
		mnFileMenu.add(mntmFileMenuItemLoad);
		
		JMenuItem mntmFileMenuItemSave = new JMenuItem("Save");
		mnFileMenu.add(mntmFileMenuItemSave);
		
		JSeparator separator = new JSeparator();
		mnFileMenu.add(separator);
		
		JMenuItem mntmFileMenuQuit = new JMenuItem("Quit");
		mntmFileMenuQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CloseDialog dia = new CloseDialog();
				dia.setVisible(true);
			}
		});
		mnFileMenu.add(mntmFileMenuQuit);
		
		JMenu mnNewMenu = new JMenu("View");
		menuBar.add(mnNewMenu);
		
		JMenu mnHelpMenu = new JMenu("Help");
		menuBar.add(mnHelpMenu);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[]{0, 0, 0, 0, 101, 230, 229, 0, 0};
		gbl_buttonPanel.rowHeights = new int[]{0, 0, 0};
		gbl_buttonPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		buttonPanel.setLayout(gbl_buttonPanel);
		
		JButton btnLoad = new JButton("Load");
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoad.gridx = 0;
		gbc_btnLoad.gridy = 0;
		buttonPanel.add(btnLoad, gbc_btnLoad);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Start pressed"); //TODO
			}
		});
		GridBagConstraints gbc_btnStart = new GridBagConstraints();
		gbc_btnStart.insets = new Insets(0, 0, 5, 5);
		gbc_btnStart.gridx = 2;
		gbc_btnStart.gridy = 0;
		buttonPanel.add(btnStart, gbc_btnStart);
		
		JButton btnStop = new JButton("Stop");
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.insets = new Insets(0, 0, 5, 5);
		gbc_btnStop.gridx = 3;
		gbc_btnStop.gridy = 0;
		buttonPanel.add(btnStop, gbc_btnStop);
		
		JLabel lblLaneLabel = new JLabel("How many lanes?");
		GridBagConstraints gbc_lblLaneLabel = new GridBagConstraints();
		gbc_lblLaneLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLaneLabel.gridx = 4;
		gbc_lblLaneLabel.gridy = 0;
		buttonPanel.add(lblLaneLabel, gbc_lblLaneLabel);
		
		JLabel lblInsert = new JLabel("Insert Car");
		GridBagConstraints gbc_lblInsert = new GridBagConstraints();
		gbc_lblInsert.insets = new Insets(0, 0, 5, 5);
		gbc_lblInsert.anchor = GridBagConstraints.EAST;
		gbc_lblInsert.gridx = 5;
		gbc_lblInsert.gridy = 0;
		buttonPanel.add(lblInsert, gbc_lblInsert);
		
		JComboBox<StreetObject> comboBoxInsert = new JComboBox<StreetObject>();
			for(StreetObject obj : nonAddedCars) {
				comboBoxInsert.addItem(obj);
			}
		GridBagConstraints gbc_comboBoxInsert = new GridBagConstraints();
		gbc_comboBoxInsert.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxInsert.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxInsert.gridx = 6;
		gbc_comboBoxInsert.gridy = 0;
		buttonPanel.add(comboBoxInsert, gbc_comboBoxInsert);
		
		JButton btnInsert = new JButton("Insert");
		GridBagConstraints gbc_btnInsert = new GridBagConstraints();
		gbc_btnInsert.insets = new Insets(0, 0, 5, 0);
		gbc_btnInsert.gridx = 7;
		gbc_btnInsert.gridy = 0;
		buttonPanel.add(btnInsert, gbc_btnInsert);
		
		JButton btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.insets = new Insets(0, 0, 0, 5);
		gbc_btnSave.gridx = 0;
		gbc_btnSave.gridy = 1;
		buttonPanel.add(btnSave, gbc_btnSave);
		
		JButton btnStep = new JButton("Release the frog!");
		btnStep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("currently no frogs here"); //TODO
			}
		});
		GridBagConstraints gbc_btnStep = new GridBagConstraints();
		gbc_btnStep.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnStep.gridwidth = 2;
		gbc_btnStep.insets = new Insets(0, 0, 0, 5);
		gbc_btnStep.gridx = 2;
		gbc_btnStep.gridy = 1;
		buttonPanel.add(btnStep, gbc_btnStep);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.insets = new Insets(0, 0, 0, 5);
		gbc_spinner.gridx = 4;
		gbc_spinner.gridy = 1;
		buttonPanel.add(spinner, gbc_spinner);
		
		JLabel lblDelete = new JLabel("Delete Car");
		GridBagConstraints gbc_lblDelete = new GridBagConstraints();
		gbc_lblDelete.insets = new Insets(0, 0, 0, 5);
		gbc_lblDelete.anchor = GridBagConstraints.EAST;
		gbc_lblDelete.gridx = 5;
		gbc_lblDelete.gridy = 1;
		buttonPanel.add(lblDelete, gbc_lblDelete);
		
		JComboBox comboBoxDelete = new JComboBox();
		GridBagConstraints gbc_comboBoxDelete = new GridBagConstraints();
		gbc_comboBoxDelete.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDelete.gridx = 6;
		gbc_comboBoxDelete.gridy = 1;
		buttonPanel.add(comboBoxDelete, gbc_comboBoxDelete);
		
		JButton btnDelete = new JButton("Delete");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.gridx = 7;
		gbc_btnDelete.gridy = 1;
		buttonPanel.add(btnDelete, gbc_btnDelete);
		
		JPanel panelDraw = new JPanel();
		contentPane.add(panelDraw, BorderLayout.CENTER);
	}

}
