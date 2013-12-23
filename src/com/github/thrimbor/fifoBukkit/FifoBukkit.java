package com.github.thrimbor.fifoBukkit;

import org.bukkit.plugin.java.JavaPlugin;

/*
 * Copyright (C) 2013  Stefan Schmidt
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class FifoBukkit extends JavaPlugin {
	Reader reader = null;
	static boolean enabled = false;
	
	public void onEnable() {
		
		if (!enabled) {
			reader = new Reader(this);
			reader.start();
			enabled = true;
		}
	}
	
	public void onDisable() {
		
	}
}
