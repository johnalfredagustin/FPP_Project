package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelHeader extends JPanel {

	public PanelHeader() throws IOException {

		String strImgPath = "D:/MUM/Projects/FPP_Project/src/Template/HeaderBackground.jpg";
		BufferedImage myPicture = ImageIO.read(new File(strImgPath));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		
		JPanel panelTopBorder = new JPanel();
		panelTopBorder.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTopBorder.setPreferredSize(new Dimension(1900, 200));
		panelTopBorder.add(picLabel);
		add(panelTopBorder);
		
	}
	
}
