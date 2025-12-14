// File: TimeTamer.java
// Simple console game about managing time and energy

import java.util.ArrayList;   // For user input
import java.util.List;    // For random events
import java.util.Random; // For storing history
import java.util.Scanner;

// Custom exception for wrong menu inputs
class InvalidChoiceException extends Exception {
    public InvalidChoiceException(String message) {
        super(message); // Pass message to Exception class
    }
}

// Abstract base class for all characters
abstract class Character {
    private String name; // Player name
    private int age;     // Player age

    public Character(String name, int age) {
        setName(name); // Validate name
        setAge(age);   // Validate age
    }

    public String getName() { return name; }

    // Ensures name is not empty
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "Player"; // Default name
        } else {
            this.name = name.trim();
        }
    }

    public int getAge() { return age; }

    // Ensures age is not negative
    public void setAge(int age) {
        if (age < 0) {
            this.age = 18; // Default age
        } else {
            this.age = age;
        }
    }
}

// Player class inherits from Character
class Player extends Character {
    private int energy;      // 0 to 100
    private int motivation;  // 0 to 100
    private int performance; // Overall score
    private int day;         // Current day

    public Player(String name, int age) {
        super(name, age); // Call parent constructor
        energy = 70;      // Starting energy
        motivation = 60;  // Starting motivation
        performance = 0;  // Starting performance
        day = 1;          // Start at day 1
    }

    public int getEnergy() { return energy; }

    // Keeps energy between 0 and 100
    public void setEnergy(int energy) {
        this.energy = Math.max(0, Math.min(100, energy));
    }

    public int getMotivation() { return motivation; }

    // Keeps motivation between 0 and 100
    public void setMotivation(int motivation) {
        this.motivation = Math.max(0, Math.min(100, motivation));
    }

    public int getPerformance() { return performance; }

    // Adds performance points safely
    public void addPerformance(int delta) {
        performance = Math.max(0, performance + delta);
    }

    public int getDay() { return day; }

    // Move to next day
    public void nextDay() { day++; }

    // Shows player stats
    public String statusString() {
        return String.format(
            "Day %d - Energy: %d, Motivation: %d, Performance: %d",
            day, energy, motivation, performance
        );
    }
}

// Abstract Activity class (parent of all activities)
abstract class Activity {
    private String name;        // Activity name
    private String description; // Short description

    public Activity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }

    // Each activity must define what happens when performed
    public abstract String perform(Player player, Random rng)
            throws InvalidChoiceException;
}

// Study activity
class StudyActivity extends Activity {
    public StudyActivity() {
        super("Study", "Improve performance but lose energy.");
    }

    @Override
    public String perform(Player player, Random rng) {
        int energyCost = 20;           // Energy used
        int motivationChange = -5;     // Motivation loss
        int perfGain = 15 + rng.nextInt(6); // Random gain

        player.setEnergy(player.getEnergy() - energyCost);
        player.setMotivation(player.getMotivation() + motivationChange);
        player.addPerformance(perfGain);

        return "You studied. -20 Energy, +" + perfGain + " Performance.";
    }
}

// Socializing activity
class SocializeActivity extends Activity {
    public SocializeActivity() {
        super("Socialize", "Increase motivation by hanging out.");
    }

    @Override
    public String perform(Player player, Random rng) {
        int energyCost = 10;
        int motivationGain = 12 + rng.nextInt(6);
        int perfGain = 2 + rng.nextInt(4);

        player.setEnergy(player.getEnergy() - energyCost);
        player.setMotivation(player.getMotivation() + motivationGain);
        player.addPerformance(perfGain);

        return "You socialized. -10 Energy, +" + motivationGain + " Motivation.";
    }
}

// Rest activity
class RestActivity extends Activity {
    public RestActivity() {
        super("Rest", "Recover energy and motivation.");
    }

    @Override
    public String perform(Player player, Random rng) {
        int energyGain = 25 + rng.nextInt(11);
        int motivationGain = 3 + rng.nextInt(4);

        player.setEnergy(player.getEnergy() + energyGain);
        player.setMotivation(player.getMotivation() + motivationGain);

        return "You rested. +" + energyGain + " Energy.";
    }
}

// Exercise activity
class ExerciseActivity extends Activity {
    public ExerciseActivity() {
        super("Exercise", "Boost motivation and health.");
    }

    @Override
    public String perform(Player player, Random rng) {
        int energyCost = 12;
        int motivationGain = 8 + rng.nextInt(6);
        int perfGain = 4 + rng.nextInt(3);

        player.setEnergy(player.getEnergy() - energyCost);
        player.setMotivation(player.getMotivation() + motivationGain);
        player.addPerformance(perfGain);

        return "You exercised. -12 Energy, +" + motivationGain + " Motivation.";
    }
}

// Main class
public class TimeTamer {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Random rng = new Random(); // Random generator

        System.out.println("=== TimeTamer ===");

        // Ask for player name
        System.out.print("Enter your name: ");
        String name = safeReadLine();

        int age = 18; // Default age
        try {
            System.out.print("Enter your age (Enter = 18): ");
            String input = safeReadLine();
            if (!input.isBlank()) {
                age = Integer.parseInt(input);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid age. Using 18.");
        }

        Player player = new Player(name, age);

        // Available activities
        Activity[] activities = {
            new StudyActivity(),
            new SocializeActivity(),
            new RestActivity(),
            new ExerciseActivity()
        };

        List<String> history = new ArrayList<>(); // Action log
        boolean playing = true; // Game state

        // Main loop
        while (playing) {
            System.out.println("\n" + player.statusString());
            System.out.println("Choose an activity:");

            for (int i = 0; i < activities.length; i++) {
                System.out.println((i + 1) + ") " + activities[i].getName());
            }

            System.out.println("5) View history");
            System.out.println("6) End simulation");
            System.out.print("Choice: ");

            try {
                int choice = Integer.parseInt(safeReadLine());

                if (choice >= 1 && choice <= activities.length) {
                    Activity act = activities[choice - 1];
                    String result = act.perform(player, rng);
                    history.add("Day " + player.getDay() + ": " + result);
                    System.out.println(result);
                    player.nextDay();

                } else if (choice == 5) {
                    System.out.println("--- History ---");
                    history.forEach(System.out::println);

                } else if (choice == 6) {
                    playing = false; // End game

                } else {
                    throw new InvalidChoiceException("Invalid menu choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Final summary
        System.out.println("Final Stats:");
        System.out.println(player.statusString());
        System.out.println("Thanks for playing TimeTamer!");
    }

    // Safely reads input
    private static String safeReadLine() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            return "";
        }
    }
}
