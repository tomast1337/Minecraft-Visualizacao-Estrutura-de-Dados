package io.github.tomast1337;

import org.bukkit.entity.Sheep;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class App extends JavaPlugin{
    private static Sheep[] sheeplist;
    @Override
    public void onEnable() {
        sheeplist = new Sheep[16];
        getLogger().info("Hello, SpigotMC! From The Greater Sorter");
        Objects.requireNonNull(this.getCommand("Sorter")).setExecutor(new Sorter(sheeplist,this));
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
        for(Sheep sheep :sheeplist)
            sheep.damage(99);
    }


}
