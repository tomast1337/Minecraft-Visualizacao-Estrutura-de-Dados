package io.github.tomast1337;

import io.github.tomast1337.sorting.SheepList;
import io.github.tomast1337.sorting.Sorter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Sheep;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class App extends JavaPlugin {
    public static final String[] sortCommandOptions = {"particula", "som", "bubble", "insertion", "selection"};
    private static SheepList sheeplist;

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GOLD + "Algoritmos de ordenação visualização");

        sheeplist = new SheepList(16);
        Objects.requireNonNull(this.getCommand("Sorter")).setExecutor(new Sorter(sheeplist, this));

        Objects.requireNonNull(this.getCommand("Sorter")).setTabCompleter(
                new TabCompleter() {
                    @Override
                    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                        return Arrays.asList(sortCommandOptions);
                    }
                }
        );
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.GOLD + "Desativando Algoritmos de ordenação visualização");
        if (sheeplist.getStatusVida())
            for (Sheep sheep : sheeplist.sheeplist)
                sheep.damage(99);

    }


}
