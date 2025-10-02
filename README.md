### BattleElo Ranking System

## Concept:

This is an elo variation that reduces rating inflation and tries to achieve a normal distrubtion

Current Code is in Java. I am open to rewritting in different languages

## Setup:

to setup the system, initiate the EloRankingSystem class. It takes five parameters: (in order)

**int expectedValue (μ)** : The expected Value of the normal distrubtion you are trying to achieve (1200 in test case)

**int standardDeviation (σ)** : The standard deviation of the normal distrubtion you are trying to achieve (600 in test case): keep in mind at around μ + 2σ gaining rating points becomes very difficult

**int learningFactor (k)** : Same as learning factor in the standard elo system (32 in test case) : learning factor should stay >= 4 at least

**int ratingPeriod** : Same as rating period in the standard elo sysstem (400 in test case)

**int initialElo** : How much elo does every player start with (400 in test case)

## Public Methods:

**int addPlayer()** adds a new player and returns their id (ids start at 0)

**void match(int player1, int player2, float result)** counts elo change based on the last match,

insert the id of player1 and player2

result = 1 for player 1 won, result = 0 for player 1 lost, result = 0.5f for draw

it automatically counts the elo change for both player1 and player2 you don't have to call match(player2, player1, 1 - result);

**int getElo(int player)** use player id to get their elo

**static double phi(double z)** calculate Φ(z), Φ represents the CDF of standard distrubtion N(0, 1)

## Formula:

Rating gain is calculated using the same elo formula. It predicts a score (float between 0 and 1) and then it compares it with the player's score(0 lose, 1 win, 0.5 draw). and multiplies by the learning factor. Keep in mind rating gain is

positive for winning no matter what (can be +0 in very severe cases)

negative for lossing no matter what (can be -0 in very severe cases)

varies in draw

<img width="331" height="73" alt="Screenshot 2025-10-02 025406" src="https://github.com/user-attachments/assets/af99e31d-5433-4905-bbda-873150d1cfe5" />

The system predicts a score (o to 1) using the classic elo formula, and another score (0 to 1) using a new formula and averages them

<img width="233" height="90" alt="Screenshot 2025-10-02 024701" src="https://github.com/user-attachments/assets/178f6bc7-112a-43df-9ea4-0b72f48c2199" />

E<sub>elo</sub> is calculated with the classic elo formula

<img width="427" height="212" alt="Screenshot 2025-10-02 024957" src="https://github.com/user-attachments/assets/a87a8015-2312-4850-9238-05955bab7eee" />

E<sub>normal</sub> is the predicted percentile of the player if ratings were normally distrubited R - N(μ, σ<sup>2</sup>). 

So for instance, if the player was at R = μ, then E<sub>normal</sub> = 0.5, which would mean average point gain and lose. 

If the player was at R = μ + σ, then E<sub>normal</sub> = 0.84, which would mean he'd lose more points and win less.

If the player was at R = μ - σ, then E<sub>normal</sub> = 0.16, which would mean he'd lose less points and win more.

So basically if you are not intentionally losing, it's really hard to stay at the very bottom or the very top, and most players will be around the middle.

<img width="228" height="79" alt="Screenshot 2025-10-02 025135" src="https://github.com/user-attachments/assets/22f52ec0-cb50-4b94-a67e-08418b6ee562" />

Generally if we were in a skill based matchmaking E<sub>elo</sub> ≃ 0.5, which would mean even in severe cases (top ladder: E<sub>normal</sub> ≃ 1 or newly registered: E<sub>normal</sub> ≃ 0) you'd still get resonable points

In the provided test cases, there's no skill based matchmaking which means the system can break a bit. In the bottom, it can get really easy to win points but it's not a signifact problem. On the other hand, in top ladder players may move too slowly if they're continuously facing weaker players.

But even in the worst case scenario (being in the top ladder E<sub>normal</sub> ≃ 1 and facing a new player E<sub>elo</sub> ≃ 1) you won't lose points for winning.

and (being a new player E<sub>normal</sub> ≃ 0 and facing a top player E<sub>elo</sub> ≃ 0) you won't get any points for lossing.




 
