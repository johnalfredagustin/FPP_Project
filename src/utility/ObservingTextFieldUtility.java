package utility;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTextField;

public class ObservingTextFieldUtility extends JTextField implements Observer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void update(Observable o, Object arg) {
        Calendar calendar = (Calendar) arg;
        DatePickerUtility dp = (DatePickerUtility) o;
        setText(dp.formatDate(calendar, "yyyy-MM-dd"));
    }
}
