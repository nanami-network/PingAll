package me.koutachan.pingall;

import me.koutachan.pingall.commands.PingAllCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class PingAll extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("pingall").setExecutor(new PingAllCommand());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
