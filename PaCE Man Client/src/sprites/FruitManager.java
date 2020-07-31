package sprites;

import data.Position;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * Manages the edible fruits
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class FruitManager {
    
    // list of displayed fruits
    private final ArrayList<Fruit> fruits = new ArrayList<>();
    
    /**
     * Adds a fruit in the given position with a value
     * 
     * @param fruit fruit to add
     */
    public void addFruit(Fruit fruit) {
        fruits.add(fruit);
    }
    
    /**
     * Removes the fruit in the given position
     * @param fruitPosition 
     */
    public void removeFruit(Position fruitPosition) {
        for (int i = 0; i < fruits.size(); i++) {
            Fruit fruit = fruits.get(i);
            if (fruit.getPos() == fruitPosition) {
                fruits.remove(i);
                break;
            }
        }
    }
    
    /**
     * Renders all the fruits
     * 
     * @param renderer rendering tool
     */
    public void render(Graphics2D renderer) {
        for(Fruit fruit : fruits)
            fruit.render(renderer, null);
    }
    
    /**
     * Indicates if the given sprite collides with a fruit. 
     * If it does the fruit position is returned, otherwise null is returned
     * 
     * @param sprite sprite to check collision
     * @return collided fruit position
     */
    public Position hasCollision(Sprite sprite) {
        for(Fruit fruit : fruits)
            if(fruit.collides(sprite))
                return fruit.getPos();
        return null;
    }

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }
}
