package PFR_GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import Players.*;

public class TMember_GUI extends JFrame {
	private JTextField CPRField;
	private JTextField NickField;
	private JTextField NameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//TMember_GUI frame = new TMember_GUI();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private TMember FMember;
	public TMember_GUI(TMember AMember) {
		FMember = AMember;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Add yes/no verification
				FMember.FName = NameField.getText();
				FMember.FNick = NickField.getText();
			}
			@Override
			public void windowActivated(WindowEvent e) {
				//
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblName);
		
		NameField = new JTextField();
		getContentPane().add(NameField);
		NameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nick");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNewLabel_1);
		
		NickField = new JTextField();
		getContentPane().add(NickField);
		NickField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblNewLabel);
		
		CPRField = new JTextField();
		getContentPane().add(CPRField);
		CPRField.setColumns(10);
		getContentPane().setLayout(new GridLayout(3, 2, 0, 0));
		getContentPane().add(lblName);
		getContentPane().add(NameField);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(NickField);
		getContentPane().add(lblNewLabel);
		getContentPane().add(CPRField);
	}

}
