
# Galactic Salvage Crew

_**Please read this README on GitHub or in VS Code using Preview**_

> This is our journey in making Galactic Salvage Crew.

When starting out, we decided on these two Topics of Choice:

1.  Version Control
2.  Text-file implementation in Java

_Further down the README, we describe how we specifically learned about and used both._

Next, we built our backlog, most of which we completed:

**MUST-HAVE**
  
- [X] Home Screen
- [X] Rendering of the Main Game Screen
- [X] Movement of Space Ship
- [X] Movement of Debris
- [X] Shooting Mechanics
- [X] Hit-Detection
- [X] Game-Over/Victory Screen

**SHOULD-HAVE**

- [X] Custom sprites for aliens/debris/ship
- [X] Saving Final Score

**CAN-HAVE**

- [ ] Different aliens/debris, with different point worth
- [ ] Tractor beam

_Each completed part has its section describing the implementation._

## How to RUN

Open the folder **gsc** in VS Code and run **HomeScreen.java**.

**!!Some things to be aware of!!**

-   Ensure your resolution is set to 1920*1080, and the Windows Scaling is set to 100% (right-click on the desktop -> Display settings -> Scale & Layout -> Scale -> 100%). A bug has been found quite late, breaking the scaling of the Main Game Screen if Windows is scaled to anything other than 100%.

## How to Play

-   You control a space ship at the bottom of the screen using the movement of your mouse.
-   You can shoot projectiles by clicking the left mouse button, but you have limited ammo.
-   Collect debris to regain your ammo.

Your **objective** is to kill as many alien spaceships within the time limit.

_Note: At any time, you can press ESC to exit the game._

## Topics of Choice

### 1. Version Control

We chose to learn Version Control as it is very valuable when working on a project with multiple other developers. Specifically, we decided to use the Version Control capabilities of GitHub, as they are very intuitive and GitHub provides a GUI where everything from creating/editing branches to merging can be done.

_Learning Goals_ We wanted to learn how to use Version Control to collaborate on this project effectively and develop features simultaneously without breaking each other's work. Once our features were complete, they could be put together and tested.

### Different Approaches

One approach was using **GitHub Desktop**, an application made by GitHub. Through the application, you can select the specific repository and branch and "pull" and "push" code. Additionally, it allows you to view the changes made to the code before making a commit to the branch uploading your updated code. One of the largest benefits was that you have a specified folder where you can pull code and it can act as a "cache" for the branch you are currently working on.

Another approach is using the git commands in the terminal:

1.  git add ., git commit -m "(comment)", git push -u origin (the branch name) (to commit changes and push the code)
2.  git fetch (the branch name) (to get the changes from the remote repository)
3.  git status (to check if there are changes that were not saved)
4.  git checkout (the branch name) (to switch to another branch)
5.  git checkout -b (the branch name) (to create a new branch and switch to it)

We also used the GitHub web interface to merge branches and resolve conflicts.

### Our Process

1.  Created two branches off of main:
    -   samo_homescreen - development of Home Screen
    -   alex_develop - development of the space ship and its movement together with firing projectiles.
2.  Created two branches off of alex_develop (the scratch main game screen was required for the next features):
    -   samo_debris - development of space debris -> movement and collision detection
    -   feature/hit_detection_aliens - development of aliens -> movement, firing projectiles, and collision detection + health and ammunition counters were added
3.  All were merged into GSC_v1
4.  Created two branches off of GSC_v1:
    -   alternative_collision_detection - Sprites were added as a proper background. The collision detection had to be reworked so that it worked with sprites and scaled.
    -   feature/final_screen - development of the game-over/victory screen
5.  All were merged into GSC_v2, where finally the text file implementation of score saving was implemented, and the code was cleaned up.
6.  GSC_v2 was merged in GSC_v3 where final adjustments and bug fixes were made. Plus a detailed README was added.
7.  GSC_v3 was merged with main.

> You can see all this for yourselves in the repo. Merged branches were kept so that they can be viewed.

### 2. Text-file implementation in Java

We chose this topic because it is vital in program design as it allows us to store information after the instance of the program is closed. Furthermore, this topic was a good candidate because it wasn't discussed in class.

_Learning goals_ We wanted to learn how to create, write, and read text-files in Java.

In the end, we implemented two out of the three (specified in FinalScreen.java):

1.  Writing into a text-file Using java.io.FileWriter, one can open the file in write mode with appending being enabled or not (append = write at the end of the file rather than beginning). We implemented it in exception handling in case there was a problem with the file. _Purpose:_ We used it to store the final score after each game.
    
2.  Reading a text-file Using java.io.File, one can open a file and then using java.util.Scanner, read it line by line. We implemented it in exception handling in case the file could not be found. _Purpose:_ We read all the previous scores to determine the highest score achieved.
    

**Note:** We didn't use file creation as it was much easier to just have a file already present in **gsc**.

## Features from the Backlog

### Home Screen ✔

One of the first features implemented. Initially, our goal was to have an awesome animated title screen with graphics and all that jazz, but afterward, we decided against it as our time was better spent on developing other features. If there was time left, we would have added it, but as you can see, there wasn't.

The code for the title screen is in HomeScreen.java.

### Rendering Main Game Screen ✔

This feature was the most time consuming, as it contained:

-   Timer, health, score, and ammo
-   Rendering the background and black bars at the sides of the screen
-   Rendering of the spaceship, debris, aliens, and projectiles.
-   Key and mouse detection for shooting.
-   Shooting mechanics
-   The actual use of hit-detection and removing of objects that collided.

Our goal was to bring everything together on this Main Game Screen, and we think we achieved this. _**One challenge that arose:**_ as we wanted to specify the placement of all objects, the scaling broke. This is demonstrated when one uses a 1080p screen with Windows scaling changed to anything other than 100%. We tried to fix this using ` System.setProperty()`, but it didn't work, and we no longer had time to do more research.

### Movement of the Space Ship ✔

The Space Ship acts as the player's avatar in the game. Our goal was to enable the player to control the Space Ship by moving the mouse to the left and to the right. 

Our approach to the implementation was as follows:

1.  Represent the Space Ship by the "SpaceShip" class, which contains all the information about the Space Ship itself (size, position, image, bounds of the Space Ship for collision detection purposes, and boundaries of its movement).
2.  Make the Space Ship follow the mouse cursor along the x-axis so that the cursor lies at the center of the Space Ship. Two mouse listeners (mouseMoved and mouseDragged) were added to achieve it. These listeners get the mouse's x-coordinate, update the position of the Space Ship accordingly, and refresh the screen to enable smooth movement.

**Challenge:** To make the Space Ship remain within the screen bounds during its movement. We implemented the method setX() that ensures that the Space Ship doesn't go off the screen even if the mouse cursor does. The code can be found in SpaceShip.java and in MainGameScreen.java.

**Another Challenge:** When the game was played, one could observe that if the left-click button was pressed, the ship remained stationary. This was fixed; mouseDragged was added to prevent the Space Ship from stopping when the player shoots and moves the Space Ship.

### Movement of Debris ✔

At first, the development of debris would have been quite a challenge if we had gone for the development of the tractor beam, but we decided against it. Therefore, the development of the debris was quite straightforward.

Our goal was to have space debris falling from the top of the screen towards the bottom where the player can touch the debris to collect it. 

Our implementation was:

1.  Have the debris spawn at a random point on the line y = 0 (within the bounds of the playable screen).
2.  A trajectory is calculated by choosing a random point on the line y=1080 (within the bounds of the playable screen) and using the equation of a straight line.
3.  The x and y position of the debris is updated using the straight-line trajectory from the previous step.

Challenge: The implementation should have ensured that the debris would end up somewhere where the spaceship can touch it, but as sometimes division by zero occurred, we had to catch that error and change the gradient slightly, which is why some debris flies off-screen.

The code can be found in Debris.java.

### Shooting Mechanics ✔

To get points, the Space Ship needs to kill alien spaceships by shooting. Our goal was to implement projectiles in the game and enable the Space Ship and the aliens to fire. Our approach to the implementation was as follows:

1.  Represent projectiles by the "Projectile" class. When a projectile is fired, it is instantiated with its parameters like initial coordinates, direction (up or down), and speed (projectiles of the Space Ship and of the alien spaceships have different speeds).
2.  To enable the Space Ship and the aliens' spaceships to fire projectiles, we added the method fire() to the "SpaceShip" and "Alien" classes. Once this method is called, it creates a new object of the class "Projectile".
3.  To make the projectiles move, we increase (for the Space Ship) or decrease (for the aliens) the projectile's y-coordinate and repeat these operations by updating it in the timer.

Challenge: The main challenge was to ensure that when the Space Ship and/or the aliens fire many projectiles, all of the projectiles were saved and updated on the screen so that the appearance of one didn't make the other disappear. The code can be found in SpaceShip.java, Alien.java, and in MainGameScreen.java.

### Hit-Detection ✔

This feature was one of the most challenging to implement. Our goal was to have complete collision detection between debris and the spaceship, projectiles and the spaceship, and projectiles and aliens. There were two iterations of this feature:

1.  Building our own collision detection: This was done using our loop to check whether the shape coordinates of each object were inside or on the edge of one another (as was in the Assignment 1 for Programming). However, this approach proved to be inefficient and didn't scale well. So we had to redo it when we were adding sprites.
    
2.  Using .awt shapes such as Rectangle and Ellipse2D: After research, we came across the Shapes in .awt, which have the perfect method for collision detection, which was .intersects(). We then implemented Rectangle onto Aliens and the Space Ship and Ellipse2D onto Debris.
    

_**Though one challenge arose**_: the Rectangle shape didn't fit the shape of the spaceship perfectly. We tried to fix this by using the Polygon shape specifying 3 points of the triangular shape of the spaceship, but it created a lot of issues. 
So, in the end, we decided to just leave the collision bounds highlighted for the spaceship so that the user can tell where it is.

Implementation of this feature can be found in Debris.java, SpaceShip.java, Projectile.java, Alien.java, and MainGameScreen.java.

### Game-Over/Victory Screen ✔

The final screen is intended to give the player feedback on the result of the game. Our goal was to create this screen and make it show the result depending on whether the player won or lost and how many points they got. Our approach to the implementation was as follows:

1.  Represent the screen by the "FinalScreen" class. It contains information about the screen resolution, all the colors used, and all the values represented.
2.  Pass to the constructor of the Final Screen the boolean parameter depending on the game's result and determining the feedback to the player.
3.  Pass to the constructor of the Final Screen the score to show it on the screen.
4.  Create a frame, top panel to contain the current score and the highest score in the different corners of the screen, and the main panel.
5.  Create a "Restart" button to let the user play again. If the player presses the button, the game starts again. The button is interactive. Hovering over it changes its color to red, indicating its clickability.
6.  Add a key listener to enable the player to quit the game by pressing "Escape".

Challenge: To make the screen show the feedback depending on the game results. We achieved this by passing the parameters for the score and the result (won or lost) to the screen's constructor.


### Custom Sprites for Aliens/Debris/Ship ✔

Our goal was to create a game that featured proper images for all entities and the background, rather than just basic shapes.

The custom sprites for the spaceship, aliens, and debris were sourced from the package:

-   Space Shooter (Redux, including fonts and sounds) by Kenney Vleugels ([www.kenney.nl](http://www.kenney.nl/))

The background was created by Samo, a member of our team. 

We added the sprites and background to the game using the ImageIcon class from Swing, following these steps:

1.  Loading the image source from the file.
2.  Assigning the image to a variable of type Image.
3.  Scaling the image if necessary to fit within collision bounds.
4.  Redrawing the image in the draw() functions when the object moved.

The code for these functionalities can be found in Alien.java, Debris.java, SpaceShip.java, and MainGameScreen.java.

### Saving Final Score (+ Determining the Highest Score) ✔

This is where our second Topic of Choice came into play. Our objective was to use text files to save each game's score, ensuring that the information remained accessible even after the game was closed. We then used this file to display the highest score achieved in the game.

Our implementation involved the following steps:

1.  Writing the final score into the file scores.txt, utilizing the FileWriter class from java.io.
2.  Reading all the scores into an ArrayList using the File class from java.io and the Scanner class from java.util.
3.  Iterating over the ArrayList to determine the highest score, which was subsequently displayed.

One challenge that arose was that the score was consistently overridden instead of being saved on the next line. After researching it, we realized that we had forgotten to set the FileWriter to append mode and forgot the "\n" at the end of the score.

You can find more details in the text under Topic of Choice #2.

The code for this can be found in FinalScreen.java.

### Different Aliens/Debris, with Different Point Values ❌

The reason for not implementing this feature was our limited timeframe.

### Tractor Beam ❌

The reason for not implementing this feature was our limited timeframe.
