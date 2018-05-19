/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3progra2;

import domain.Block;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author maikel
 */
public class Proyecto3Progra2 extends Application implements Runnable {

    private Block maze[][];
    private Canvas canvas;
    private Pane pane;
    private GraphicsContext gc;
    private domain.Character c1;
    private Thread thread;

    @Override
    public void start(Stage primaryStage) {
        this.maze = new Block[5][5];
        init(primaryStage);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            System.exit(0);
            }
        });
        primaryStage.show();

    }

    public void init(Stage primaryStage) {
        canvas = new Canvas(800, 800);
        pane = new Pane(canvas);
        Scene scene = new Scene(pane, 800, 800);
        this.thread = new Thread(this);
        createMaze();
        gc = canvas.getGraphicsContext2D();

        primaryStage.setScene(scene);
        this.c1 = new domain.Character(30, maze[1][1]);
        this.c1.start();
        this.thread.start();

    }

    public void drawMaze(GraphicsContext gc) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                maze[i][j].draw(gc);
            }
        }
    }

    public void createMaze() {
        for (int i = 0; i < 5; i++) {
            maze[i][0] = new Block(i, 0, 30, "Wall");
            maze[i][4] = new Block(i, 4, 30, "Wall");
        }
        for (int i = 1; i < 5; i++) {
            maze[0][i] = new Block(0, i, 30, "Wall");
            maze[4][i] = new Block(4, i, 30, "Wall");
        }

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                if ((i == 2 && j == 1) || (i == 2 && j == 2)) {
                    maze[i][j] = new Block(i, j, 30, "Wall");
                } else {
                    maze[i][j] = new Block(i, j, 30, "Floor");
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (maze[i][j].getType().equals("Floor")) {
                    maze[i][j].setNext(caminos(i, j));
                }
            }
        }
       System.err.println(maze[3][2].getNext().size());
    }

    public ArrayList<Block> caminos(int x, int y) {
        ArrayList<Block> next = new ArrayList<>();
        if (x + 1 < 5 && maze[x + 1][y].getType().equals("Floor")) {
            System.err.println("E1");
            next.add(maze[x + 1][y]);
        }
        if (x - 1 > 0 && maze[x - 1][y].getType().equals("Floor")) {
            System.err.println("E2");
            next.add(maze[x - 1][y]);
        }
        if (y + 1 < 5 && maze[x][y + 1].getType().equals("Floor")) {
            System.err.println("E3");
            next.add(maze[x][y + 1]);
        }
        if (y - 1 > 0 && maze[x][y - 1].getType().equals("Floor")) {
            System.err.println("E4");
            next.add(maze[x][y - 1]);
        }

        return next;
    }

    @Override
    public void run() {
        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000 / fps;

        while (true) {
            try {
                start = System.nanoTime();
                elapsed = System.nanoTime() - start;
                wait = time - elapsed / 1000000;
                Thread.sleep(wait);
                gc = this.canvas.getGraphicsContext2D();
                gc.clearRect(0, 0, 800, 800);
                drawMaze(gc);
                c1.draw(gc);
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
