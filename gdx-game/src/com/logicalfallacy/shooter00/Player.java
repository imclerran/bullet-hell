package com.logicalfallacy.shooter00;

import java.util.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Player
{
	Hero _hero;
	ArrayList<Bullet> _bullets;
	int _score;

	public void Player() {
		_hero = new Hero(_bullets);
		_bullets = new ArrayList();
	}
}
