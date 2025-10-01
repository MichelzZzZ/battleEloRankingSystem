package berankingsystem;

import java.util.ArrayList;

public class EloRankingSystem 
{
	
	private ArrayList<Player> population = new ArrayList<Player>();
	
	private int expectedValue; //μ
	private int standardDeviation; //σ
	private int learningFactor; //k
	private int ratingPeriod;
	private int initialElo;
	
	private int i;
	
	public EloRankingSystem(int expectedValue, int standardDeviation, int learningFactor, int ratingPeriod, int initialElo)
	{
		
		this.expectedValue = expectedValue;
		this.standardDeviation = standardDeviation;
		this.learningFactor = learningFactor;
		this.ratingPeriod = ratingPeriod;
		this.initialElo = initialElo;
		i =-1;
		
	}
	
	public int addPlayer()
	{
		
		population.add(new Player(initialElo));
		i++;
		
		return i;
		
	}
	
	public void match(int player1, int player2, float result)
	{
		// result = 1 for p1 win, 0 for p2 lost, 0.5 for draw
		match(player1, player2, result, true);
		
	}
	
	private void match(int player1, int player2, float result, boolean repeat)
	{
		
		Player p1 = population.get(player1);
		Player p2 = population.get(player2);
		
		int eloDifference = p2.getElo() - p1.getElo(); // r2 - r1
		
		float Eelo = (float) (1 / (1 + Math.pow(10, eloDifference/ratingPeriod))); //  1 / [1 + 10^(ΔR/p)]
		
		// expected value used by the standard elo system
		// based on elo difference between the two players
		// 0 <= Eelo <= 1
		
		int eloDeviation = p1.getElo() - expectedValue; // r1 - μ
		
		float Enormal = (float) phi(eloDeviation/standardDeviation); // Φ[(r1 - μ) / σ]
		
		// expected percentile in a population where ratings are normally distributed 
		// R - N(μ, σ^2)
		// 0 <= Enormal <= 1
		
		/*
		 * 
		 * for instance if a player is at μ, he is expected to be an average player, thus his expected score
		 * would be Enormal = 0.5
		 * and if a player is at μ + σ he is expected to be in the top 16%
		 * thus his expected score is Enormal = 0.84
		 * which means players will win less points and lose more points here
		 * 
		 */
		
		float Ebattle = (Eelo + Enormal)/2;
		
		// expected score based on both elo difference and desired normal distribution  
		
		
		p1.addElo((int) (learningFactor * (result - Ebattle))); // standard elo formula
		
		if(repeat)
			match(player2, player1, 1 - result, false);
		
	}
	
	public int getElo(int player)
	{
		
		return population.get(player).getElo();
	}
	
	// calculate Φ(z)
	// deepseek did this idk how it works
	
    public static double phi(double z) {
        return 0.5 * (1.0 + erf(z / Math.sqrt(2.0)));
    }
    
	private static double erf(double x) {
        // Constants for the approximation
        double a1 = 0.254829592;
        double a2 = -0.284496736;
        double a3 = 1.421413741;
        double a4 = -1.453152027;
        double a5 = 1.061405429;
        double p = 0.3275911;

        // Save the sign of x
        int sign = 1;
        if (x < 0) {
            sign = -1;
            x = -x;
        }

        // A&S formula 7.1.26
        double t = 1.0 / (1.0 + p * x);
        double y = 1.0 - (((((a5 * t + a4) * t) + a3) * t + a2) * t + a1) * t * Math.exp(-x * x);
        return sign * y;
    }

}
