package com.me.tiledMapGame.pathing;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.me.tiledMapGame.entities.Enemy;
import com.me.tiledMapGame.entities.Entity;

public class Targeting {

	/**
	 * Seek out any enemies in the tower's or unit's range and return the first one it finds.
	 * @return
	 */
	public static Enemy detectFirstEnemyFrom(Entity entity) {
		Circle c = new Circle(entity.getMidpointX(), entity.getMidpointY(), entity.getStat("Range"));
		for (Enemy e : ObjectGrid.enemyList()) {
			if (Intersector.overlaps(c, e.getBoundingRectangle())) {
				return e;
			}
		}
		return null;
	}

}
