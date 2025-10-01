package berankingsystem;

class Player 
{
	
	private int elo;
	
	Player(int initialElo)
	{
		this.elo = initialElo;
		
	}
	
	int getElo()
	{
		
		return elo;
		
	}

	void addElo(int reward)
	{
		
		elo += reward;
		
		if(elo < 0)
			elo = 0;
		
	}
}
