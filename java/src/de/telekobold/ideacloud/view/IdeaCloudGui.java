package de.telekobold.ideacloud.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * The first version of a graphical user interface for IdeaCloud.
 * <p>
 * Developed with the help of the <i>WindowBuilder 1.9.1</i> Eclipse plugin by
 * the Eclipse Foundation.
 * 
 * @author Michael Merz <mail@telekobold.de>
 */
public class IdeaCloudGui {

    private JFrame jFrameIdeaCloudGui;
    private JTextField jTextFieldNewItem;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    IdeaCloudGui window = new IdeaCloudGui();
		    window.jFrameIdeaCloudGui.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public IdeaCloudGui() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	jFrameIdeaCloudGui = new JFrame();
	jFrameIdeaCloudGui.setTitle("IdeaCloud 1.0");
	jFrameIdeaCloudGui.setBounds(50, 50, 800, 520);
	jFrameIdeaCloudGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JLabel lblCurrentlyLoadedIdeaCloud = new JLabel("Currently loaded idea cloud:");

	JButton jButtonViewIdeaCloud = new JButton("View idea cloud");

	JLabel jLabelAddItem = new JLabel("Add item");

	jTextFieldNewItem = new JTextField();
	jTextFieldNewItem.setText("Item name");
	jTextFieldNewItem.setColumns(10);

	JComboBox comboBox = new JComboBox();

	JLabel jLabelPriority = new JLabel("Priority (1 = highest, 15 = lowest)");

	JLabel jLabelDeadline = new JLabel("Deadline");

	JTextArea txtrDescribingText = new JTextArea();
	txtrDescribingText.setText("Describing text");

	JButton jButtonAddItem = new JButton("Add");
	jButtonAddItem.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    }
	});
	GroupLayout groupLayout = new GroupLayout(jFrameIdeaCloudGui.getContentPane());
	groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		.addGroup(groupLayout.createSequentialGroup()
			.addGap(31)
			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(jButtonAddItem)
				.addComponent(txtrDescribingText, GroupLayout.PREFERRED_SIZE, 731,
					GroupLayout.PREFERRED_SIZE)
				.addComponent(jLabelDeadline)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(jLabelPriority)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 66,
						GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
					.addComponent(jLabelAddItem)
					.addComponent(jButtonViewIdeaCloud)
					.addComponent(jTextFieldNewItem, GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
					.addComponent(lblCurrentlyLoadedIdeaCloud, GroupLayout.DEFAULT_SIZE,
						GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
			.addContainerGap(38, Short.MAX_VALUE)));
	groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
		.addGroup(groupLayout.createSequentialGroup()
			.addGap(24)
			.addComponent(lblCurrentlyLoadedIdeaCloud)
			.addGap(18)
			.addComponent(jButtonViewIdeaCloud)
			.addGap(37)
			.addComponent(jLabelAddItem)
			.addGap(18)
			.addComponent(jTextFieldNewItem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
				GroupLayout.PREFERRED_SIZE)
			.addGap(18)
			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(jLabelPriority)
				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
					GroupLayout.PREFERRED_SIZE))
			.addGap(18)
			.addComponent(jLabelDeadline)
			.addGap(18)
			.addComponent(txtrDescribingText, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
			.addGap(18)
			.addComponent(jButtonAddItem)
			.addContainerGap(29, Short.MAX_VALUE)));
	jFrameIdeaCloudGui.getContentPane()
		.setLayout(groupLayout);

	JMenuBar menuBar = new JMenuBar();
	jFrameIdeaCloudGui.setJMenuBar(menuBar);

	JMenu jMenuFile = new JMenu("File");
	menuBar.add(jMenuFile);

	JMenuItem jMenuItemLoadIdeaCloud = new JMenuItem("Load idea cloud");
	jMenuFile.add(jMenuItemLoadIdeaCloud);
	// An idea cloud is also loaded through typing "Ctrl. + L":
	jMenuItemLoadIdeaCloud.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));

	JMenuItem jMenuItemNewIdeaCloud = new JMenuItem("New idea cloud");
	jMenuFile.add(jMenuItemNewIdeaCloud);
	// A new idea cloud is also created through typing "Ctrl. + N":
	jMenuItemNewIdeaCloud.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

	JMenuItem jMenuItemSaveIdeaCloud = new JMenuItem("Save currently loaded idea cloud");
	jMenuFile.add(jMenuItemSaveIdeaCloud);
	// An idea cloud is also saved through typing "Ctrl. + S":
	jMenuItemSaveIdeaCloud.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

	JMenu jMenuHelp = new JMenu("Help");
	menuBar.add(jMenuHelp);

	JMenuItem jMenuItemHelp = new JMenuItem("Help");
	jMenuHelp.add(jMenuItemHelp);
	// The help menu is also invoked through typing "Ctrl. + H":
	jMenuItemHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

	JMenuItem jMenuItemAbout = new JMenuItem("About");
	jMenuHelp.add(jMenuItemAbout);
    }
}
