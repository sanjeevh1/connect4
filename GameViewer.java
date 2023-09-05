import javax.swing.*;
public class GameViewer {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(600, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameComponent component = new GameComponent();
		frame.add(component);
		frame.setVisible(true);
		
		for(int i = 0; i < 7; i++)
		{
			char c = (char) ('1' + i);
			KeyStroke k = KeyStroke.getKeyStroke(c);
			component.getInputMap().put(k, i);
			
			KeyAction action = new KeyAction(i, component);
			component.getActionMap().put(i, action);
		}
		
		KeyStroke k = KeyStroke.getKeyStroke('h');
		component.getInputMap().put(k, "Help");
		
		HelpAction h = new HelpAction(component);
		component.getActionMap().put("Help", h);
	}

}
