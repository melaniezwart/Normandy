package frame;

import game.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by mzwart on 24-11-2016.
 */
public class StartFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public StartFrame() {
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().isEmpty()) {
					textArea.append("Empty input, try again.");
				} else {
					Game.action(textField.getText());
					textField.setText("");
					textField.requestFocus();
				}
			}
		});
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setAutoCreateGaps(true);
		gl_contentPane.setAutoCreateContainerGaps(true);

		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup()
				.addComponent(scrollPane)
				.addComponent(textField)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createSequentialGroup()
				.addComponent(scrollPane)
				.addComponent(textField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
		);

		textArea = new JTextArea();
		textArea.setBackground(Color.DARK_GRAY);
		textArea.setForeground(Color.WHITE);
		textArea.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
}
