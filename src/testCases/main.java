package testCases;

import java.util.Random;

import berankingsystem.EloRankingSystem;

public class main {

	public static void main(String[] args) 
	{
		
		EloRankingSystem system = new EloRankingSystem(1200, 600, 32, 400, 400);
		
		int player1 = system.addPlayer();
		int player2 = system.addPlayer();
		int player3 = system.addPlayer();
		int player4 = system.addPlayer();
		

		
		// Test case 1 : all players have the same chance to win
		
		// initial = 1200 for testing purposes
		// generally recommended to start with a number lower than μ to give players a light push in the beginning
		
		/*
		Random r = new Random();
		
		for(int i = 0; i < 100; i++)
		{
			
			int playerA = r.nextInt(4);
			int playerB = r.nextInt(4);
			
			while(playerA == playerB)
			{
				playerB = r.nextInt(4);
			}
			
			int pointsA = r.nextInt(10);
			int pointsB = r.nextInt(10);
			
			if(pointsA > pointsB)
				system.match(playerA, playerB, 1);
			else if(pointsB > pointsA)
				system.match(playerA, playerB, 0);
			else if(pointsB == pointsA)
				system.match(playerA, playerB, 0.5f);
		
		}
		*/
		
		
		/*
		 * 
		 * results:
		 * player1 : 583
		 * player2 : 640
		 * player3 : 668
		 * player4 : 608
		 * 
		 */
		
		// Test case 2: Players with a higher number have a higher chance of winning
		
		// initial = 1200 for testing purposes
		// generally recommended to start with a number lower than μ to give players a light push in the beginning
		
		/*
		Random r = new Random();
		
		for(int i = 0; i < 100; i++)
		{
			
			int playerA = r.nextInt(4);
			int playerB = r.nextInt(4);
			
			while(playerA == playerB)
			{
				playerB = r.nextInt(4);
			}
			
			int pointsA = r.nextInt(playerA + 1);
			int pointsB = r.nextInt(playerB + 1);
			
			if(pointsA > pointsB)
				system.match(playerA, playerB, 1);
			else if(pointsB > pointsA)
				system.match(playerA, playerB, 0);
			else if(pointsB == pointsA)
				system.match(playerA, playerB, 0.5f);
			
		}
		*/
		
		/*
		 * 
		 * results:
		 * player1 : 840
		 * player2 : 1127
		 * player3 : 1280
		 * player4 : 1560
		 * 
		 */
		
		System.out.println("player1: " + system.getElo(player1));
		System.out.println("player2: " + system.getElo(player2));
		System.out.println("player3: " + system.getElo(player3));
		System.out.println("player4: " + system.getElo(player4));
		
		
	}

}
