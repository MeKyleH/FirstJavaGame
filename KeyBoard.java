package com.whoiskyleholt.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {

	private boolean[] keys = new boolean[120]; // determines if a particular key is pressed or not
	public boolean up, down, left, right, shift; // creates the directional keys

	// creates method to check if a key is pressed or released
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W]; // UP = 38, W = 87
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]; // DOWN = 40, S = 83;
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		shift = keys[KeyEvent.VK_SHIFT];

	}

	// says key is pressed
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	// says key is not pressed
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}

}
