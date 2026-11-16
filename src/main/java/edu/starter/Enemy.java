package edu.starter;
public class Enemy {
    private int hp;
    private final String name;

    public  Enemy(String name, int hp){
        this.name = name;
        this.hp = hp;
    }

    public void setHp(int dhp){
        this.hp = Math.max(this.hp  + dhp, 0);
    }

    public int getHp(){
        return this.hp;
    }

    public String getName(){
        return this.name;
    }

    public void attack(Player player){
        int damage = 10;
        if (player.getDefense()){
            damage = 5;
            player.setDefense();
            System.out.println(String.format("%s has succesfuly blocked attack weekaning %s attack dealt only 5 damage", player.getName(), this.name));
        }
        else{
            System.out.println(String.format("%s %s attacked causing lost of 10 damage", this.name, player.getName()));
        }
        player.setHp(-damage);


    }
}
