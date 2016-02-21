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

import model.Roles;

@SuppressWarnings("serial")
public class PanelHeader extends JPanel {

	public PanelHeader(String role) throws IOException {

		String strImgPath = "";
		
		if (role.equals(String.valueOf(Roles.Professor))) {
			strImgPath = "D:/MUM/02 FPP/07 Project/CourseRegistrationProject/src/Template/ProfessorHeaderBackground.jpg";	
		} else if (role.equals(String.valueOf(Roles.Student))) {
			strImgPath = "D:/MUM/02 FPP/07 Project/CourseRegistrationProject/src/Template/StudentHeaderBackground.jpg";
		} else {
			strImgPath = "D:/MUM/02 FPP/07 Project/CourseRegistrationProject/src/Template/StudentHeaderBackground.jpg";
		}
		
		BufferedImage myPicture = ImageIO.read(new File(strImgPath));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		
		JPanel panelTopBorder = new JPanel();
		panelTopBorder.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTopBorder.setPreferredSize(new Dimension(1900, 200));
		panelTopBorder.add(picLabel);
		add(panelTopBorder);
		
	}
	
}
