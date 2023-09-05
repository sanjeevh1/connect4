import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Ellipse2D.Double;
import java.awt.event.*;
import javax.swing.*;
public class GameComponent extends JComponent{
	private Game game;
	private boolean showMenu;
	
	public GameComponent()
	{
		game = new Game();
		showMenu = false; 
	}
	
	public Game getGame()
	{
		return game;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.BLUE);
		Rectangle rect = new Rectangle(3, 100, 580, 500);
		g2.fill(rect);
		
		//Highlights the winning row.
		if(game.getWinner() > 0)
		{
			for(int[] spot : game.getWinningRow())
			{
				int x = 18 + 80 * spot[1];
				int y = 115 + 80 * spot[0];
				int diameter = 70;
				Ellipse2D.Double ring = new Ellipse2D.Double(x, y, diameter, diameter);				
				g2.setColor(Color.GREEN);
				g2.fill(ring);

			}
		}
		
		//Fills each circle with the correct color.
		Color[] colors = {Color.WHITE, Color.RED, Color.YELLOW};
		for(int row = 0; row < game.getBoard().length; row++)
		{
			for(int col = 0; col < game.getBoard()[0].length; col++)
			{
				
				int x = 23 + 80 * col;
				int y = 120 + 80 * row;
				int diameter = 60;
				Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter); 
				int token = game.getBoard()[row][col];
				Color c = colors[token];
				g2.setColor(c);
				g2.fill(circle);
			}
		}
		
		//Labels the columns 1-7
		g2.setColor(Color.BLACK);
		FontMetrics metrics = g.getFontMetrics(g2.getFont());
		for(int col = 0; col < game.getBoard()[0].length; col++)
		{
			String num = String.valueOf(col + 1);
			int width = metrics.stringWidth(num);
			int x = 53 - width / 2 + 80 * col;
			int y = 80;
			g2.drawString(num, x, y);
		}
		
		//Displays who's turn it is or the winner/if there is a draw
		String message;
		if(game.getWinner() == 0)
		{
			if(game.getPlayer() == 1)
			{
				message = "Red's turn";
			}
			else
			{
				message = "Yellow's turn";
			}
		}
		else if(game.getWinner() == -1)
		{
			message = "Draw";
		}
		else if(game.getWinner() == 1)
		{
			message = "Red wins!";
		}
		else
		{
			message = "Yellow wins!";
		}
		
		int width = metrics.stringWidth(message);
		int x = 300 - width / 2;
		int y = 50;
		g2.drawString(message, x, y);
		
		//displays the help menu	
		String s = "Press 'h' to see instructions.";
		if(showMenu)
		{
			s = "Press 'h' to hide instructions.";
		}
		g2.drawString(s, 10, 15);
		if(showMenu)
		{
			Rectangle box = new Rectangle(5, 20, 400, 100);
			g2.draw(box);
			g2.setColor(Color.YELLOW);
			g2.fill(box);
			
			g2.setColor(Color.BLACK);
			
			String str1 = "Press the number of the column you want to put your color token into.";
			String str2 = "The token will go to the lowest available spot in that column.";
			String str3 = "The first person to get four of their tokens in a row/column/diagonal wins.";
			
			g2.drawString(str1, 10, 50);
			g2.drawString(str2, 10, 80);
			g2.drawString(str3, 10, 110);
		}
		
	}
	
	//hides the menu if it is already displayed
	//shows the menu if it is already hidden
	public void changeMenu()
	{
		showMenu = !showMenu;
	}
}
	

