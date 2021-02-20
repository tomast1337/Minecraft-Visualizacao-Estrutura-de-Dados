package io.github.tomast1337;

import org.bukkit.entity.Sheep;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin{
    private static Sheep[] sheeplist;
    @Override
    public void onEnable() {
        sheeplist = new Sheep[16];
        getLogger().info("Hello, SpigotMC! From The Greater Sorter");
        this.getCommand("Sorter").setExecutor(new Sorter(sheeplist));
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }


}
