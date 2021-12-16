package com.denesgarda.MOTDs;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main extends JavaPlugin implements Listener {
    public static MOTDs MOTDs;

    @Override
    public void onEnable() {
        getLogger().info("initializing MOTDs");
        this.getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Loading config");
        try {
            MOTDs = com.denesgarda.MOTDs.MOTDs.returnObject("plugins" + File.separator + "MOTDs" + File.separator + "motds.txt");
            MOTDs.reload();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadmotds")) {
            if (sender.isOp()) {
                try {
                    MOTDs.reload();
                    sender.sendMessage(ChatColor.GREEN + "Successfully reloaded MOTDs and config");
                } catch (Exception e) {
                    sender.sendMessage(ChatColor.RED + "Failed to reload MOTDs and config");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to execute this command");
            }
        }
        return true;
    }

    @EventHandler
    public void refresher(ServerListPingEvent event) {
        event.setMotd(MOTDs.MOTDs[new Random(MOTDs.MOTDs.length).nextInt()]);
    }
}
