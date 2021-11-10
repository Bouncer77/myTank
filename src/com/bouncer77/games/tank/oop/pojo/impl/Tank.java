package com.bouncer77.games.tank.oop.pojo.impl;

import com.bouncer77.games.tank.oop.enums.Direction;
import com.bouncer77.games.tank.oop.pojo.abstracts.AbstractMovableObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Asus on 26.10.2020.
 */
public class Tank extends AbstractMovableObject {
    public Tank(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {

    } // implements KeyListener {
/*    public Tank(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {

    }

    //клавиша нажата, но не отпущена
    @Override
    public void keyTyped(KeyEvent event) {
        int keyCode = event.getKeyCode(); //цифровой код нажатой клавиши

        System.out.println("keyCode = " + keyCode);

        try {
            switch (keyCode) {
                case KeyEvent.VK_A -> move(3);
                case KeyEvent.VK_W -> move(1);
                case KeyEvent.VK_D -> move(4);
                case KeyEvent.VK_S -> move(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //клавиша отпущена
    @Override
    public void keyPressed(KeyEvent e) {

    }

    //клавиша нажата и отпущена
    @Override
    public void keyReleased(KeyEvent e) {

    }

    void move(Direction direction) throws Exception {
        this.direction = direction;

        if (dontCanMove()) {
            System.out.println("Can't move!!!!!!!");
            fire();
            return;
        }

        for (int i = 0; i < QUADRANT_SIZE; i++) {

            if (direction == Direction.UP) {
                tankY--;
            } else if (direction == 2) {
                tankY++;
            } else if (direction == 3) {
                tankX--;
            } else if (direction == 4) {
                tankX++;
            }
            Thread.sleep(33);
            repaint();
        }

    }*/

}
