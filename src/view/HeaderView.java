package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderView extends JFrame {

	public HeaderView() {
		// setSize(1000, 1000);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setLayout(new BorderLayout());
		// JPanel panel = header();
		//
		// add(panel,BorderLayout.NORTH);
		//
		// setVisible(true);
	}

	public JPanel header() {

		JPanel headerView = new JPanel();
		setSize(1000, 200);
		setLayout(new FlowLayout(FlowLayout.LEFT));

		JPanel logoPanel = new JPanel();
		logoPanel.setPreferredSize(new Dimension(350, 150));
		logoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		ImageIcon logoPicture = new ImageIcon(getClass().getResource("mum_logo.jpg"));

		Image img = logoPicture.getImage();
		Image newimg = img.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
		logoPicture = new ImageIcon(newimg);

		logoPanel.add(new JLabel(logoPicture));

		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(800, 200));
		titlePanel.setLayout(new BorderLayout());
		add(titlePanel);

		JLabel title = new JLabel("MAHARISHI UNIVERSITY OF MANAGEMENT");

		title.setFont(new Font("Courier New", Font.BOLD, 30));
		title.setForeground(Color.LIGHT_GRAY);

		titlePanel.add(title, BorderLayout.CENTER);
		logoPanel.setBackground(Color.white);
		titlePanel.setBackground(Color.white);

		headerView.add(logoPanel);
		headerView.add(titlePanel);
		headerView.setBackground(Color.white);
		return headerView;
	}
}
