package com.github.thrimbor.fifoBukkit;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

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

public class Reader extends Thread {
	BufferedReader in = null;
	private Plugin plugin;
	
	public Reader(Plugin plugin) {
		this.plugin = plugin;
	}
	
	public void run() {
		final Logger logger = Logger.getLogger("Minecraft");
		try {
			while (true) {
				in = new BufferedReader(new FileReader("/tmp/bukkit.fifo"));
				
				logger.info("fifo has been opened!");
				
				while (in.ready()) {
					String command = in.readLine();
					new CommandRequest(plugin, command);
				}
				
				in.close();
				logger.info("fifo has been closed!");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class CommandRequest implements Runnable {
		
		private String command;

		public CommandRequest(Plugin plugin, String command) {
			this.command = command;
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, this);
		}

		@Override
		public void run() {
			Logger.getLogger("Minecraft").info("running command");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
		}
		
	}
}
