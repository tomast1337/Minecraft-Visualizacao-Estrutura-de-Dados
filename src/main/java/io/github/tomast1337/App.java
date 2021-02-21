package io.github.tomast1337;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Sheep;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class App extends JavaPlugin {
    private static Sheep[] sheeplist;
    public static final String[] sortCommandOptions = {"criar", "embaralhar", "destruir", "mover", "bubble", "insertion", "selection"};

    @Override
    public void onEnable() {
        sheeplist = new Sheep[16];
        getLogger().info("Algoritmos de ordenação visualização");
        Objects.requireNonNull(this.getCommand("Sorter")).setTabCompleter(new TabCompleter() {
            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return Arrays.asList(sortCommandOptions);
            }
        });
        Objects.requireNonNull(this.getCommand("Sorter")).setExecutor(new Sorter(sheeplist, this));
    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
        try {
            for (Sheep sheep : sheeplist)
                sheep.damage(99);
        }catch (NullPointerException e) {
            getLogger().info("Nem uma ovelha destruida");
        }
    }


}
