import java.awt.event.ActionEvent;
import javax.swing.*;

public class KeyAction extends AbstractAction{
	private int col;
	private GameComponent comp;
	
	public KeyAction(int column, GameComponent component)
	{
		col = column;
		comp = component;
	}
	
	@Override
	//places a token in the column number typed by the user
	public void actionPerformed(ActionEvent e) {
		Game game = comp.getGame();
		game.putToken(col);
		comp.repaint();
		//Makes sure no tokens can be placed after the game is over
		if(game.getWinner() != 0)
		{
			comp.getInputMap().clear();
		}
	}
	
}
