import java.awt.event.ActionEvent;
import javax.swing.*;

public class HelpAction extends AbstractAction{
	private GameComponent component;
	
	public HelpAction(GameComponent comp)
	{
		component = comp;
	}
	
	@Override
	//displays the help menu if not already displayed
	//hides the help menu if already displayed
	public void actionPerformed(ActionEvent e) {
		component.changeMenu();
		component.repaint();
	}
	
}
