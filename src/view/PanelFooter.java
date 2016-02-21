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
public class PanelFooter extends JPanel {

	public PanelFooter() throws IOException {

		String strImgPath = "D:/MUM/Projects/FPP/07 Project/CourseRegistrationProject/src/Template/FooterBackground.jpg";
		BufferedImage myPicture = ImageIO.read(new File(strImgPath));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		
		JPanel panelTopBorder = new JPanel();
		panelTopBorder.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTopBorder.setPreferredSize(new Dimension(1900, 200));
		panelTopBorder.add(picLabel);
		add(panelTopBorder);
		
	}
	
}
