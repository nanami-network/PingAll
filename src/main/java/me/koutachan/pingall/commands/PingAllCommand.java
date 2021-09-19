package me.koutachan.pingall.commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PingAllCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<Integer> pingList = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            try {
                Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
                int ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
                sender.sendMessage(ChatColor.AQUA + "" + player.getName() + "のPing: " + ping + "ms");
                pingList.add(ping);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
                int ping = player.getPing();
                sender.sendMessage(ChatColor.AQUA + "" + player.getName() + "のPing: " + ping + "ms");
                pingList.add(ping);
            }
        }

        sender.sendMessage(ChatColor.GREEN + "--------------------------------");
        sender.sendMessage(ChatColor.RED + "max: " + pingList.stream().mapToInt(i -> i).max().orElse(0));
        sender.sendMessage(ChatColor.BOLD + "average: " + pingList.stream().mapToInt(i -> i).average().orElse(0));
        sender.sendMessage(ChatColor.GREEN + "min: " + pingList.stream().mapToInt(i -> i).min().orElse(0));
        sender.sendMessage(ChatColor.GREEN + "--------------------------------");
    return true;
    }
}
