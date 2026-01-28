package auditoriski.aud1;

abstract class Alien {
    public static final int SNAKE_ALIEN = 0;
    public static final int OGRE_ALIEN = 1;
    public static final int MARSHMALLOW_MAN_ALIEN = 2;

    private int type;
    private int health;
    private String name;
    private int damage;

    public Alien(int health, String name){
        this.health = health;
        this.name = name;
    }

    @Override
    public String toString(){
        return "Name: "+ this.name + "\nHealth: " + this.health + "\nAlien Type: " + this.type + "\n";
    }

    public int getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

class Snake extends Alien{
    public Snake(int health, String name) {
        super(health, name);
        this.setDamage(10);
        this.setType(SNAKE_ALIEN);
    }
}
class OGRE extends Alien{
    public OGRE(int health, String name) {
        super(health, name);
        this.setDamage(6);
        this.setType(OGRE_ALIEN);
    }
}
class MARSHMALLOW_MAN extends Alien{
    public MARSHMALLOW_MAN(int health, String name) {
        super(health, name);
        this.setDamage(1);
        this.setType(MARSHMALLOW_MAN_ALIEN);
    }
}

class AlienPack {
    private Alien[] aliens;

    public AlienPack(int numAliens){
        aliens = new Alien[numAliens];
    }

    public void addAlien(Alien newAlien, int index){
        aliens[index] = newAlien;
    }

    public Alien[] getAliens(){
        return aliens;
    }

    public int calculateDamage(){
        int damage = 0;
        for (int i = 0; i < aliens.length; i++){
            damage += aliens[i].getDamage();
//            if(aliens[i].type == Alien.SNAKE_ALIEN){
//                damage += 10; // snake does 10 damage
//            }else if (aliens[i].type == Alien.OGRE_ALIEN){
//                damage += 6;
//            }else if (aliens[i].type == Alien.MARSHMALLOW_MAN_ALIEN){
//                damage += 1;
//            }
        }
        return damage;
    }

}

public class aud1_2{
    public static void main(String [] args){
        Snake snake1 = new Snake(100, "ekans 1");
        Snake snake2 = new Snake(100, "ekans 2");
        Snake snake3 = new Snake(100, "ekans 3");

        OGRE ogre = new OGRE(100, "big guy");
        AlienPack alienPack = new AlienPack(4);
        alienPack.addAlien(snake1,0);
        alienPack.addAlien(snake2,1);
        alienPack.addAlien(snake3,2);
        alienPack.addAlien(ogre,3);

        System.out.println("Aliens: ");
        for (Alien alien : alienPack.getAliens()){
            System.out.println(alien);
        }
        System.out.println("Total alien damage: "+alienPack.calculateDamage());
    }
}