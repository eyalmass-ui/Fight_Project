package edu.starter;
import java.util.Scanner;

public class Encounter {
    private final Player player;
    private final Enemy[] enemies;
    private int currentEnemyIndex;
    private final Scanner in;

    public Encounter(Player player, Scanner s, Enemy... enemies) {
        this.player = player;
        this.enemies = enemies;
        this.in = s;
        this.currentEnemyIndex = 0;
    }

    public boolean fight() {
        // As long as player is alive and there is an enemy to fight chose next enemy and fight player fights him
        while (!lost() && this.currentEnemyIndex < this.enemies.length) {
            Enemy currentEnemy = this.enemies[currentEnemyIndex];
            System.out.println("\n*** " + currentEnemy.getName() + " appeared! ***");

            //As long as neither enemy or player died than do another round
            while (!lost() && currentEnemy.getHp() > 0) {
                stats(currentEnemy);
                playerMove(currentEnemy);

                //checks if either player or enemy lost if not it is the enemy turn to counter attack
                if (!lost() && currentEnemy.getHp() > 0) { 
                    System.out.println("\n--- Enemy Turn ---");
                    currentEnemy.attack(this.player);
                }
            }

            // Check if the current enemy was defeated
            if (currentEnemy.getHp() <= 0) {
                System.out.println("\n=> You defeated the " + currentEnemy.getName() + "!");

                this.player.resetDefense(); // resets defense to false for the next fight
                this.currentEnemyIndex++; // Move to the next enemy in line
            }
        }

        // Final Battle Results
        System.out.println("\n=== BATTLE OVER ===");
        if (lost()) {
            System.out.println("You Lose! You were defeated.");
            return false;
        } else {
            System.out.println("You won for now until our next encounter!\n");
            return true;
        }
    }

    public void stats(Enemy enemy) {
        System.out.println("\n--- Status ---");
        System.out.println("Player HP: " + this.player.getHp() + " | Resources: " + this.player.getResources() +" | Damage: " + this.player.getDamage());
        System.out.println("Enemy HP: " + enemy.getHp());
    }

    /*
     * @pre enemy != null
     * @post chooses what player move is in his turn
    */
    public void playerMove(Enemy enemy) {
        boolean validChoice = false;
        //As long as the player doesn't give a valid move try again
        while (!validChoice) {
            System.out.print(String.format("Choose what to affect:\n1. %s\n2. %s\nEnter choice: ", this.player.getName(), enemy.getName()));
            char affectTo = in.next().charAt(0);

            switch (affectTo) {
                case '1':
                    affectPlayer();
                    validChoice = true;
                    break;
                case '2':
                    validChoice = true;
                    affectEnemy(enemy);
                    break;
                default:
                    System.out.println("Invalid character please try again!\n");
            }
        }
    }

    public void affectPlayer() {
        boolean validChoice = false;
        //As long as the player doesn't give a valid move try again
        while (!validChoice) {
            System.out.print("\nChoose move:\n1. Defend\n2. Recover\nEnter choice: ");
            char affectTo = in.next().charAt(0);

            switch (affectTo) {
                case '1':
                    if (this.player.getDefense()) {
                        System.out.println("Your defense is already activated! Try a different move.\n");
                    } else {
                        this.player.defend();
                        validChoice = true;
                    }
                    break;
                case '2':
                    this.player.recover();
                    validChoice = true;
                    break;
                default:
                    System.out.println("Invalid character please try again!");
            }
        }
    }

    /*
     * @pre enemy != null
     * @post chooses what player enemy affect will be in his turn
    */
    public void affectEnemy(Enemy enemy) {
        boolean validChoice = false;
        //As long as the player doesn't give a valid move try again
        while (!validChoice) {
            System.out.print("\nChoose move:\n1. Attack\n2. Special (Costs 3 Resources)\nEnter choice: ");
            char affectTo = in.next().charAt(0);

            switch (affectTo) {
                case '1':
                    this.player.attack(enemy);
                    validChoice = true;
                    break;
                case '2':
                    if (this.player.getResources() >= 3) {
                        this.player.special(enemy);
                        validChoice = true;                   
                    } else {
                        System.out.println("Not enough resources for Special! (Requires 3)\n");
                    }
                    break;
                default:
                    System.out.println("Invalid character please try again!");
            }
        }
    }

    public boolean lost() {
        return (this.player.getHp() <= 0);

    }

}