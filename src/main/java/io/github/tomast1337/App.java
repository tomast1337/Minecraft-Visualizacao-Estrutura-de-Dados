package io.github.tomast1337;

import io.github.tomast1337.sorting.ManuseioOvelhas;
import io.github.tomast1337.sorting.SheepList;
import org.bukkit.ChatColor;
import org.bukkit.entity.Sheep;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Objects;

public class App extends JavaPlugin {
    public static final String[] sortCommandOptions = {"configurar_som", "configurar_particulas", "menu", "ordenar", "buscar", "volume", "velocidade"};
    private static SheepList sheeplist;

    @Override
    public void onEnable() {
        getLogger().info(ChatColor.GOLD + "Algoritmos de ordenação visualização");

        sheeplist = new SheepList(16);
        Objects.requireNonNull(this.getCommand("ovelhas")).setExecutor(new ManuseioOvelhas(sheeplist, this));

        Objects.requireNonNull(this.getCommand("ovelhas")).setTabCompleter(
                (sender, command, alias, args) -> Arrays.asList(sortCommandOptions)
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
