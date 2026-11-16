package edu.starter;
public class Player{
    private static final int SPECIAL_DAMAGE = 15;
    private static final int SPECIAL_COST = 3;
    private static final int RECOVER_AMOUNT = 3;
    private static final int MAX_RESOURCES = 10;

    private int damage;
    private int hp;
    private final String name;
    private int resources;
    private boolean defendState;

    public  Player(String name, int hp, int resources){
        this.name = name;
        this.hp = hp;
        this.resources = Math.min(MAX_RESOURCES, Math.max(0, resources));    
        this.damage = 10;    
        this.defendState = false;
    }

    public void setHp(int dHp){
        this.hp = Math.min(Math.max(this.hp + dHp, 0), 100);
    }

    public int getHp(){
        return this.hp;
    }

    public void setResources(int dResources){
        this.resources = Math.min(MAX_RESOURCES, Math.max(0, this.resources + dResources));
    }

    public int getResources(){
        return this.resources;
    }

    /**
     * @pre addadDamage >= 0
     * @ post increases this.damage by addedDamage
    */
    public void setDamage(int addedDamage){
        this.damage += addedDamage;
    }

    public int getDamage(){
        return this.damage;
    }

    public void setDefense(){
        this.defendState = !(this.defendState);
    }

    public void resetDefense(){
        this.defendState = false;
    }

    public boolean getDefense(){
        return this.defendState;
    }

    public String getName(){
        return this.name;
    }

    public void attack(Enemy enemy){
        System.out.println(String.format("%s succesfuly attacked the %s causing lost of %d damage", this.name, enemy.getName(), getDamage()));
        int currentDamage = getDamage();
        enemy.setHp(-currentDamage);
    }

    public void defend(){
        setDefense();
        System.out.println(String.format("%s took a defnsive stance", this.name));
    }

    /**
    * @pre this.resources >= SPECIAL_COST
    * @post attacks enemy and reduces his health by SPECIAL_DAMAGE
    */
    public void special(Enemy enemy){
        //@pre this.resources >= 3
        //@post attacks enemy and reduces his health by 15
        setResources(-SPECIAL_COST);
        enemy.setHp(-SPECIAL_DAMAGE);
        System.out.println(String.format("Special attack used! Dealt 15 damage to %s", enemy.getName()));
    }

    public void recover(){
        setResources(RECOVER_AMOUNT);
        System.out.println(String.format("%s Recovered resources. Current resources: " + this.resources, this.name));
    }
}

