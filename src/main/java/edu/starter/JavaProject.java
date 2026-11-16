package edu.starter;
import java.util.Scanner;

public class JavaProject {
    public static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        // Test
        Player player = new Player("Hero", 100, 5);

        // Create multiple enemies for the gauntlet
        Enemy goblin = new Enemy("Goblin Minion", 30);
        Enemy orc = new Enemy("Orc Warrior", 45);

        // Pass player and  enemies into the Encounters instances
        Encounter encounter1 = new Encounter(player, s ,goblin);
        Encounter encounter2 = new Encounter(player, s ,orc);

        // Start the sequential fight loop using the instance method
        if (encounter1.fight()){
            rewards(player);
            encounter2.fight();
        }
    }

    public static void rewards(Player player){

        boolean validAction = false;

        while (!validAction){
        System.out.println("Congragulation you hav won you encounter chose your reward:\n1.Recover\n2.power");
        char reward = s.next().charAt(0);

            switch(reward){
                case '1':
                    player.setHp(25);
                    validAction = true;
                    break;
                case '2':
                    player.setDamage(2);
                    validAction = true;
                    break;
                default:
                    System.out.println("Invalid character please try again!");
            }
        }

    }
}


