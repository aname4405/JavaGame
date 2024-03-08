package mvc.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; //the listener interface for receiving keyboard events (keystrokes).

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //getKeyCode() Returns the integer keyCode associated with the key in this event.
        //Examples of Associated KeyCode: 8--Backspace, 10--Enter, 16--Shift,
        //18--Alt, 9--Tab, 12--Clear, 17--Ctrl, 65--A, 66--B, ... and more!
        int code = e.getKeyCode();
        //if W key or up arrow is pressed set upPressed to true:
        if(code == KeyEvent.VK_W | code == KeyEvent.VK_UP){
            upPressed = true;
        }
        //if S key or down arrow is pressed set downPressed to true:
        if(code == KeyEvent.VK_S | code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        //if A key or left arrow is pressed set leftPressed to true:
        if(code == KeyEvent.VK_A | code == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
        //if D key or right arrow is pressed set rightPressed to true:
        if(code == KeyEvent.VK_D | code == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        //if W key or up arrow is released set upPressed to false:
        if (code == KeyEvent.VK_W | code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        //if S key or down arrow is released  set downPressed to false:
        if (code == KeyEvent.VK_S | code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        //if A key or left arrow is released  set leftPressed to false:
        if (code == KeyEvent.VK_A | code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        //if D key or right arrow is released  set rightPressed to false:
        if (code == KeyEvent.VK_D | code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
