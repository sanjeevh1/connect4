public class Game {
	
	private int[][] board;
	private int player;
	private int winner;
	private int[][] winningRow;
	
	public Game()
	{
		board = new int[6][7];
		player = 1;
		winner = 0;
	}
	
	public int[][] getBoard()
	{
		return board;
	}
	
	public int getWinner()
	{
		return winner;
	}
	
	public int getPlayer()
	{
		return player;
	}
	
	public int[][] getWinningRow()
	{
		return winningRow;
	}
	
	/*
	 * If a spot is available in the column corresponding to the parameter column,
	 * places a token in the lowest available spot, changes players, and checks
	 * if there is a winner or draw.
	 */
	public void putToken(int column)
	{
		if(board[0][column] != 0)
		{
			return;
		}
		int row = board.length - 1;
		while((board[row][column] != 0) && (row > 0))
		{
			row--;
		}
		board[row][column] = player;
		changePlayers();
		updateWinner();
	}
	
	//Changes whose turn it is.
	public void changePlayers()
	{
		if(player == 1)
		{
			player = 2;
		}
		else
		{
			player = 1;
		}
	}
	
	/*
	 * If there is no winner yet and the game is not over, sets winner = 0.
	 * If there is a winner, sets winner equals the integer corresponding to the winning player.
	 * If there is a draw, sets winner = -1.
	 */
	public void updateWinner()
	{
		//Checks if any rows have 4 adjacent pieces of the same color.
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[0].length - 3; c++)
			{
				boolean fourInRow = true;
				for(int i = c; i < c + 3; i++)
				{
					if((board[r][i] == 0) || (board[r][i] != board[r][i + 1]))
					{
						fourInRow = false;
					}
				}
				if(fourInRow)
				{
					winner = board[r][c];
					int[][] arr = {{r, c}, {r, c + 1}, {r, c + 2}, {r, c + 3}};
					winningRow = arr;
					return;
				}
			}
		}
		//Checks if any columns have 4 adjacent pieces of the same color.
		for(int c = 0; c < board[0].length; c++)
		{
			for(int r = 0; r < board.length - 3; r++)
			{
				boolean fourInCol = true;
				for(int i = r; i < r + 3; i++)
				{
					if((board[i][c] == 0) || (board[i][c] != board[i + 1][c]))
					{
						fourInCol = false;
					}
				}
				if(fourInCol)
				{
					winner = board[r][c];
					int[][] arr = {{r, c}, {r + 1, c}, {r + 2, c}, {r + 3, c}};
					winningRow = arr;
					return;
				}
			}
		}
		//Checks if any diagonals have 4 adjacent pieces of the same color.
		for(int i = 0; i < board.length - 3; i++)
		{
			for(int j = 0; j < board[0].length - 3; j++)
			{
				boolean fourInDiagonal = true;
				for(int k = 0; k < 3; k++)
				{
					if((board[i + k][j + k] == 0) || (board[i + k][j + k] != board[i + k + 1][j + k + 1]))
					{
						fourInDiagonal = false;
					}
				}
				if(fourInDiagonal)
				{
					int[][] arr = {{i, j}, {i + 1, j + 1}, {i + 2, j + 2}, {i + 3, j + 3}};
					winningRow = arr;
					winner = board[i][j];
					return;
				}
			}
		}
		for(int i = board.length - 1; i > 2; i--)
		{
			for(int j = 0; j < board[0].length - 3; j++)
			{
				boolean fourInDiagonal = true;
				for(int k = 0; k < 3; k++)
				{
					if((board[i - k][j + k] == 0) || (board[i - k][j + k] != board[i - k - 1][j + k + 1]))
					{
						fourInDiagonal = false;
					}
				}
				if(fourInDiagonal)
				{
					winner = board[i][j];
					int[][] arr = {{i, j}, {i - 1, j + 1}, {i - 2, j + 2}, {i - 3, j + 3}};
					winningRow = arr;
					return;
				}
			}
		}
		//Checks if board has empty spots.
		for(int[] row : board)
		{
			for(int col : row)
			{
				if(col == 0)
				{
					return;
				}
			}
		}
		winner = -1;		
	}	
}
