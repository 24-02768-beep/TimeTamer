# TimeTamer


Description / Overview
TimeTamer is a Java console-based simulation game that allows players to navigate daily routines by making choices that affect their energy, motivation, and performance. Players decide how to spend their day studying, socializing, exercising, or resting—and each choice impacts their progress and life balance. The program promotes reflection on real-world time management, discipline, productivity, and self-care, combining engaging gameplay with meaningful life lessons.

##OOP Concepts Applied

 Encapsulation
   - The Player and Character classes encapsulate attributes such as energy, motivation, and performance.
   - Getters and setters enforce controlled access to private fields, ensuring values remain within valid ranges.

Inheritance
  - Player class extends the abstract Character class, inheriting properties like name and age.
   - Demonstrates a hierarchical relationship between a general character and a specialized player.

Polymorphism
   - The abstract class Activity allows multiple activity types (StudyActivity, SocializeActivity, RestActivity, ExerciseActivity) to implement the perform() method differently.
  - The program dynamically calls the correct perform() method at runtime depending on the chosen activity.

Abstraction
 - Activity class abstracts the concept of an action a player can take.
 - Players interact with Activity objects without needing to know the underlying implementation details.

Exception Handling
   - Custom exception InvalidChoiceException manages invalid menu inputs, promoting robust and user-friendly interaction.

Program Structure
##Main Classes
Character (Abstract Class)
Defines common attributes such as name and age for all characters.
Player (Subclass of Character)
Adds attributes such as energy, motivation, performance, and day count. Handles updates to the player’s state based on activities.
Activity (Abstract Class)
Represents actions that the player can perform.
StudyActivity
Increases performance while reducing energy.
SocializeActivity
Boosts motivation with a small performance gain.
RestActivity
Restores energy and motivation.
ExerciseActivity
Improves motivation and performance at the cost of energy.
TimeTamer (Main Class)
Contains the main game loop, menu navigation, player input handling, and simulation flow.
Class Relationships:
Character (abstract)
   |
   └── Player

Activity (abstract)
   ├── StudyActivity
   ├── SocializeActivity
   ├── RestActivity
   └── ExerciseActivity

##How to Run the Program

1. Requirements
Java Development Kit (JDK) 11 or higher

Command-line terminal (Command Prompt, Terminal, or IDE terminal)

Optional IDE: IntelliJ IDEA, Eclipse, or Visual Studio Code

2. Save the Source Code
Create a project folder (e.g., TimeTamerProject).

Save the source file as TimeTamer.java inside the folder.

3. Compile the Program
Open a terminal in the project directory and run:
javac TimeTamer.java

4. Run the Program
Execute the program using:
java TimeTamer
Follow the on-screen instructions to enter player details and select daily activities.


Sample Output
=== TimeTamer ===
Enter your name: Lheslly
Enter your age (Enter = 18):

Day 1 - Energy: 70, Motivation: 60, Performance: 0
Choose an activity:
1) Study
2) Socialize
3) Rest
4) Exercise
5) View history
6) End simulation
Choice: 1
You studied. -20 Energy, +17 Performance.

Author and Acknowledgement

##Authors:

Name: Carpio, Ayella Nicole A. 
Course / Program: BS Information Technology
GitHub Email: 24-02124@g.batstate-u.edu.ph

Acknowledgements: I would like to sincerely thank my professor for the guidance and support provided throughout this project. I am also grateful to my groupmates for their cooperation, dedication, and teamwork, which made the completion of this project possible. I appreciate everyone who offered help and encouragement along the way.

Name: Castillo, Lheslly Gale H. 
Course / Program: BS Information Technology
GitHub Email: 24-07638@g.batstate-u.edu.ph

Acknowledgements: I would like to thank everyone who helped me complete the TimeTamer project. First, I thank our instructor for their guidance and support. I am also grateful to my group members for their teamwork and contributions, which made this project enjoyable and educational.

Name: DeVilla, Rhizel Kristine  L. 
Course / Program: BS Information Technology
GitHub Email: 24-02868@g.batstate-u.edu.ph

Acknowledgements: I thank our professor, Mr. Juriel Comia, for guiding and supporting our group throughout this project. I also thank my fellow blockmates for their help and teamwork. Finally, I thank my family for supporting and encouraging me.

Future Enhancements
Add a simple graphical interface (GUI) for a more interactive experience.
Include more activities and random events to make the game more engaging.
Allow saving and loading of game progress.
Introduce goals or achievements to help players track their progress and stay motivated.
References
Java Tutorial Network( Java Programming Tutorial )
 https://javatutorial.net/
W3Schools ( Java Tutorial)
https://www.w3schools.com/java/
GeeksforGeeks – Java programming examples and OOP concepts – https://www.geeksforgeeks.org/java/
Visual Studio Code (VSCode) 
-YouTube Tutorials – Java programming tutorials for beginners and advanced users (various channels).






