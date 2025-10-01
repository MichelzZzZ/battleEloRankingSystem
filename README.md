### BattleElo Ranking System

## Concept:

This is an elo variation that reduces rating inflation and tries to achieve a normal distrubtion
Current Code is in Java. I am open to rewritting in different languages

## Setup:

to setup the system, initiate the EloRankingSystem class. It takes five parameters: (in order)

**int expectedValue (μ)** : The expected Value of the normal distrubtion you are trying to achieve (1200 in test case)
**int standardDeviation (σ)** : The standard deviation of the normal distrubtion you are trying to achieve (600 in test case): keep in mind at around μ + 2σ gaining rating points becomes very difficult
**int learningFactor (k)** : The system's sensetivity (32 in test case)
**int ratingPeriod** : Same as rating period in the standard elo sysstem (400 in test case)
**int initialElo** : How much elo does every player start with (400 in test case)

## Public Methods:

**int addPlayer()** adds a new player and returns their id (ids start at 0)
**void match(int player1, int player2, float result)** counts elo change based on the last match,
insert the id of player1 and player2
result = 1 for player 1 won, result = 0 for player 1 lost, result = 0.5f for draw
it automatically counts the elo change for both player1 and player2 you don't have to call match(player2, player1, 1 - result);
**int getElo(int playaer)** use player id to get their elo
**static phi(double z)** calculate Φ(z), Φ represents the CDF of standard distrubtion N(0, 1)


