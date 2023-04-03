April 2nd 2023, 11:00 AM - 12:50 PM

* The first change I made in the code was the naming of the paddles because the "paddle" was used multiple times through the code and it was hard to distinguish. Thus, I replaced paddlel and paddler with **leftPaddle** and **rightPaddle** respectively. To do this, I pressed Ctrl+F and replaced everywhere paddlel or paddler was used. The principle is from page 84, and it states that "use intention-revealing names for all variables, methods, and classes...".

* I added a new method, **ballAngle()**, which is solely responsible for computing a random bouncing angle for the ball. This refactor is important because inside the **run()** method, there were multiple conditional statements. In addition, there was code duplication because the same conditions were written for the left and the right paddle. Another new method is **ballDirectionX()** which determines whether the ball will bounce to the left or the right based on the paddle that the ball intersects with. It's important to separate this method from **ballAngle()** because it's easier to debug later. For this refactoring, the applicable principle is from page 87 which states that "a method should do one thing only do it well".

* from the **run()** method, I removed the code that was responsible for keeping the paddles in bound, and created the method, **containPaddle(Rectangle paddle)**, which takes a paddle and executes the code that was in run() for checking the bounds. The reason for this, was to make the code readable because at first, I couldn't figure out its purpose. But now that it's in a method, it's easier to understand its use since the method is self-explanatory.

* From the **moveBall()** method, I removed the conditional statements responsible for checking which player is winning, and added a new method, **keepScore()** which executes the conditional statements that I extracted from **moveBall**. In addition, I removed the code that was responsible for checking the vertical bounds of the ball. I added the **containBall()** method which is solely responsible for containing the ball within the vertical bounds of the game. The importance of this refactor is to **modularize** the code and making sure the method doesn't have to contain multiple computing code.
