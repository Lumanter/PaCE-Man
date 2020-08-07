package sprites;

import javax.swing.ImageIcon;

/**
 * Edible fruit sprite. Each can have custom points
 * @author Luis Mariano Ramírez Segura - github/Lumanter
 */
public class Fruit extends Sprite {
    
    // fruit points value
    private Integer points;

    /**
     * Constructor set the position and value of the fruit
     * 
     * @param points fruit points value
     * @param position_x fruit x position
     * @param position_y fruit y position
     */
    public Fruit(Integer points, Integer position_x, Integer position_y) {
        super(new ImageIcon("resources/fruit.png").getImage(), position_x, position_y);
        this.points = points;
    }
    
    /**
     * Constructor used just to display sprite
     * 
     * @param position_x fruit x position
     * @param position_y fruit y position
     */
    public Fruit(Integer position_x, Integer position_y) {
        super(new ImageIcon("resources/fruit.png").getImage(), position_x, position_y);
    }

    public Integer getPoints() {
        return points;
    }
}
