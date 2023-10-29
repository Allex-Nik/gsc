# Galactic Salvage Crew
***Please read this README in GitHub or in VS Code using Preview***

> This is our journey in making Galactic Salvage Crew.

When starting out we have decided on these two Topics of Choice:

 1. Version Control
 2. Text-file implementation in Java

*Further down the README we describe how we specifically learned about and used both*

Next we built our backlog, which we most of we completed:

**MUST HAVE**
 - [X] Home Screen
 - [X] Rendering of the Main Game Screen
 - [X] Movement of Space Ship
 - [X] Movement of Debris
 - [X] Shooting Mechanics
 - [X] Hit - Detection
 - [X] Game-Over/Victory Screen

**SHOULD HAVE**
 - [X] Custom sprites for aliens/debris/ship
 - [X] Saving Final Score

**CAN HAVE**
 - [ ] Different aliens/debris, with different point worth
 - [ ] Tractor beam

*Each completed part has its own section describing the implementation*

## How to RUN
Open the folder  **gsc** folder in VS Code and run **HomeScreen.java**

**!!Some things to be aware of!!**

 - Have your resolution set to 1920*1080 and the Windows Scaling set to 100% (right-click on the desktop -> Display settings -> Scale & Layout -> Scale -> 100%). A bug has been found quite late breaking the scaling of the Main Game Screen if one has windows scaled to anything other than 100%.
 - ...

## How to Play
- You control a space ship, at the bottom of the screen using the movement of your mouse. 
- You can shoot projectiles by clicking the left mouse button, but you have limited ammo.
- Collect debris to regain your ammo.

Your **objective** is to kill as many alien space ships within the time limit.

*Note: At any time you can press ESC to exit the game*

## Topics of Choice

  ### 1. Version Control

We chose to learn Version Control as it is a very valuable to when working on a project with multiple other developers. Specifically, we decided to use the Version Control capabilities of GitHub, as they are very intuitive and GitHub provides a GUI where everything from branches to merging can be done.

*Learning Goals*
We wanted to learn how to use Version Control to be able to collaborate on this project effectively, and be able to develop features simultaneously without breaking the one another's work. Then once our features were complete they could be put together and tested.

### Different Approaches 

One approach was using **GitHub Desktop**, an application made by GitHub. 
[Include picture of UI here]
Through the application you can select the specific repository and branch and "pull" and "push" code. Additionally it allows you to view the changes made to the code before making a commit to the branch uploading your updated code. One of the largest benefits was that you have a specified folder where to you can pull code to and can act as "cache" for the branch you are currently working on.

Another approach is using the .git commands... *Alex fill this in*

### Our Process

 1. Created two branches of of main:
	 - samo_homescreen - development of Home Screen
	 - alex_develop - development of the space ship and it's movement together with firing projectiles.
2. Created two branches of of alex_develop (the scratch main game screen was required for the next features):
	- samo_derbis - development of space debris -> movement and collision detection
	- feature/hit_detection_aliens - development of aliens -> movement, firing projectiles and collision detection + health and ammunition counters were added
3. All was merged into GSC_v1
4. Created two branches of of GSC_v1:
	- alternative_collision_detection - Sprites were added as was a proper background. The collision detection had to be reworked so that it worked with sprites and scaled.
	- feature/final_screen - development of the game-over/victory screen
5. All was merged into GSC_v2, where finally the text file implementation of score saving was implemented and the code was cleaned up.
6. GSC_v2 was merged with main

> You can see all this for yourselves in the repo. Merged branches were kept, so that they can be viewed.

### 2. Text-file implementation in Java
///TO BE FILLED IN

## Features from the Backlog
### Home Screen ✔
One of the first features implemented. 
[Picture of home screen]
At first our goal was to have an awesome animated title screen with graphics and all that jazz, but afterwards we decided against it as our time was better spent on developing other features.
If there was time left, we would have added it but as you can see there wasn't.

The code for the title screen is in HomeScreen.java

### Rendering Main Game Screen ✔
Most challenging *finish.................................................*

### Movement of the Space Ship ✔
### Movement of Debris ✔
At first the development of debris would have been quite a challenge if we had gone for the development of the tractor beam, but we decided against it. Therefore the development of the debris was quite straightforward.

Our goal was to have space debris falling from the top of the screen towards the bottom where the player can touch the debris to collect it.
[include image of debris]
Our implementation was:
1. Have the debris spawn at a random point on the line y = 0 (within the bounds of the playable screen).
2. A trajectory is calculated by choosing a random point on the line y=1080 (within the bounds of the playable screen) and using the equation of a straight line.
3. The x and y position of the debris is updated using the straight line trajectory from previous step.

Challenge: The implementation should have insured that the debris would end up somewhere where the spaceship can touch it, but as sometimes division by zero occurred, we had to catch that error and change the gradient slightly that is why some debris flies off screen.

The code can be found in Debris.java
### Shooting Mechanics ✔
### Hit-Detection ✔
### Game-Over/Victory Screen ✔
### Custom sprites for aliens/debris/ship ✔
Our goal was to have a game that didn't consist of only shapes but had proper images for all entities and the background.

The custom sprites for the spaceship, aliens and debris came from the package:
- Space Shooter (Redux, plus fonts and sounds) by Kenney Vleugels (www.kenney.nl)
[Include sprite images]

The background was created by Samo from our team.
[Include background image]

The sprites and background were added to the game using the class ImageIcon from Swing. 
1. source of image was loaded from file
2. image was assigned to variable of type Image
3. If needed the image was scaled to fit inside collision bounds.
4. The image was redrawn in draw() functions when the object moved.

The code for these can be found in Alien.java, Debris,java, SpaceShip.java and MainGameScreen.java
### Saving Final Score (+ Determining the highest score) ✔
This is were our topic of choice #2 came in. Our goal was to use text-files to save each score from each game into a file that will remain after the game is closed. The file will then be used to be able to show the highest score achieved in the game.

Our implementation consisted of:
1. Writing the final score into the file scores.txt. This was done using the FileWriter class from java.io.
2. Reading all the scores into an ArrayList using File class from java.io and the Scanner class from java.util
3. Looping over the ArrayList to determine the highest score, which was then displayed.

[Picture of final screen with highest score and current score differing]

One challenge that arose was that the score was constantly overridden instead of being save on the next line. After a while of research we figured out that we forgot to set the FileWriter to append mode and further more we forgot the "\n" at the of the score.

**More information about this in the Topic of Choice #2 text**
The code for this can be found in FinalScreen.java
### Different aliens/debris, with different point worth ❌
The reason for not implementing this feature, is that we didn't have enough time.
### Tractor beam ❌
The reason for not implementing this feature, is that we didn't have enough time.
