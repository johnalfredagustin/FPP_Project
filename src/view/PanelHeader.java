package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelHeader extends JPanel {

	public PanelHeader() throws IOException {

		String strImgPath = "D:/MUM/02 FPP/07 Project/CourseRegistrationProject/src/Template/HeaderBackground.jpg";
		BufferedImage myPicture = ImageIO.read(new File(strImgPath));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		
		JPanel panelTopBorder = new JPanel();
		panelTopBorder.setLayout(new BorderLayout());
		panelTopBorder.setPreferredSize(new Dimension(1900, 200));
		panelTopBorder.add(picLabel);
		add(panelTopBorder);
		
	}
	
}
