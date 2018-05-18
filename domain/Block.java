/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author maikel
 */
public class Block {

    private int x, y, size;
    private String type;
    private ArrayList<Block> next;

    public Block(int x, int y, int size, String type) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Block> getNext() {
        return next;
    }

    public void setNext(ArrayList<Block> next) {
        this.next = next;
    }
    
    public void draw(GraphicsContext gc) {
        if (this.type.equals("Wall")) {
            gc.setFill(Color.BLACK);
        } else {
            gc.setFill(Color.WHITE);
        }
        if(x==1 && y==2){
            gc.setFill(Color.CHOCOLATE);
        }
        gc.fillRect(x * size, y * size, size, size);
    }
    
    public boolean in(int xMouse, int yMouse) {

        return (xMouse >= this.x*size && xMouse <= this.x*size + this.size) && (yMouse >= this.y*size && yMouse <= this.y*size + this.size);
    } // isClicked: retorna true si el botón fue clickeado y false si no


}
