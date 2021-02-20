package io.github.tomast1337;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Sorter implements CommandExecutor {
    private final DyeColor[] ordemCor = {
                     DyeColor.WHITE,DyeColor.LIGHT_GRAY,DyeColor.GRAY,DyeColor.BLACK,
                     DyeColor.BROWN,DyeColor.RED,DyeColor.ORANGE,DyeColor.YELLOW,
                     DyeColor.LIME,DyeColor.GREEN,DyeColor.CYAN,DyeColor.LIGHT_BLUE,
                     DyeColor.BLUE,DyeColor.PURPLE,DyeColor.MAGENTA,DyeColor.PINK};
    private Sheep[] sheeplist;
    private Boolean statusVida = false;
    private App app;

    public Sorter(Sheep[] sheeplist, App app) {
        this.app = app;
        this.sheeplist = sheeplist;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                switch (args[0].toLowerCase()) {
                    case "criar":
                        criar(sender);
                        break;
                    case "embaralhar":
                        embaralhar(sender);
                        break;
                    case "destruir":
                        destruir(sender);
                        break;
                    case "mover":
                        mover(sender);
                        break;
                    case "bubble":
                        bubble(sender);
                        break;
                    case "insertion":
                        insertion(sender);
                        break;
                    case "selection":
                        selection(sender);
                        break;
                    default:
                        player.sendMessage("Erro: ultilize os argumentos criar, ordenar, embaralhar e insertion, bubble para ordenar");
                        break;
                }
            } else {
                player.sendMessage("Erro: ultilize os argumentos criar, ordenar, embaralhar");
            }
        }
        return true;
    }

    String print_order(Sheep[] sheeps) {
        StringBuilder result = new StringBuilder("[");
        for (Sheep sheep : sheeps) {
            result.append(Integer.parseInt(sheep.getName())).append(" ");
        }
        result.append("]");
        return result.toString();
    }

    void criar(CommandSender sender) {
        Player player = (Player) sender;
        World world = player.getWorld();
        if (!statusVida) {
            for (int i = 0, off = 0; i < sheeplist.length; i++, off += 2) {
                sheeplist[i] = (Sheep) world.spawnEntity(player.getLocation().add(off, 0, 0), EntityType.SHEEP);
                sheeplist[i].setCustomName(String.valueOf(i + 1));
                sheeplist[i].setColor(ordemCor[i]);
                sheeplist[i].setAI(false);
            }
            statusVida = true;
            player.sendMessage("Ovelhas Criadas\n" + print_order(sheeplist));
        } else {
            player.sendMessage("Ovelhas ja criadas use mover re posicioná las ou destruir para remover elas");
        }

    }

    void mover(CommandSender sender) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        for (Sheep sheep : sheeplist) {
            sheep.teleport(location);
            location.add(2, 0, 0);
        }
        player.sendMessage("Ovelhas movidas para sua localisação");
    }

    void destruir(CommandSender sender) {
        Player player = (Player) sender;
        int tempo = 0;
        for (final Sheep sheep : sheeplist) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    sheep.damage(99);
                }
            }.runTaskLater(app, tempo);
            tempo += 10;
        }
        statusVida = false;
        player.sendMessage("Ovelhas destruidas\nUse criar para fazer uma nova array");
    }

    void embaralhar(CommandSender sender) {
        final Player player = (Player) sender;
        int tempo = 0;
        for (int i = 0; i < 3; i++,tempo += 15) {
            final Location l = sheeplist[0].getLocation();
            new BukkitRunnable() {
                @Override
                public void run() {
                    List<Sheep> list = Arrays.asList(sheeplist);
                    Collections.shuffle(list);
                    list.toArray(sheeplist);
                    for (int i = 0; i < 16; i++) {
                        sheeplist[i].teleport(l);
                        l.add(2, 0, 0);
                    }
                }
            }.runTaskLater(app, tempo);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("Ovelhas embaralhadas\n" + print_order(sheeplist));
            }
        }.runTaskLater(app, tempo+5);
    }

    void torcar(int idexX, int idexY) {
        Location Localx = sheeplist[idexX].getLocation();
        Location Localy = sheeplist[idexY].getLocation();

        sheeplist[idexX].teleport(Localy);
        sheeplist[idexY].teleport(Localx);
    }

    void bubble(CommandSender sender) {
        final Player player = (Player) sender;
        player.sendMessage("Executando bubble sort");
        final int[] tempo = {20};
        for (int i = 0; i < sheeplist.length; i++) {
            for (int j = 0; j < sheeplist.length - 1; j++) {
                // Variaves para a classe anonima
                final int finalJ = j;
                final Sheep[] aux = new Sheep[1];
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (Integer.parseInt(sheeplist[finalJ].getName()) > Integer.parseInt(sheeplist[finalJ + 1].getName())) {
                            //Torca visual
                            torcar(finalJ, finalJ + 1);
                            //Torca logica
                            aux[0] = sheeplist[finalJ];
                            sheeplist[finalJ] = sheeplist[finalJ + 1];
                            sheeplist[finalJ + 1] = aux[0];
                        }else{
                            tempo[0] -= 10;
                        }
                    }
                }.runTaskLater(app, tempo[0]);
                tempo[0] += 10;
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("Ovelhas ordenadas\n" + print_order(sheeplist));
            }
        }.runTaskLater(app, tempo[0]);
    }

    void replace(Location original){
        Location location = original.clone();
        for (Sheep sheep : sheeplist) {
            sheep.teleport(location);
            location.add(2,0,0);
        }
    }

    void insertion(CommandSender sender) {
        //TODO Implemetar animações
        final Player player = (Player) sender;
        player.sendMessage("Executando insertion sort");
        final Sheep[] inserir = new Sheep[1];

        final Location location = sheeplist[0].getLocation();

        int tempo = 20;
        final int[] j = {1};
        for (int i = 1;i < sheeplist.length; i++) {
            final int finalI = i;
            new BukkitRunnable() {
                @Override
                public void run() {
                    inserir[0] = sheeplist[finalI];
                    j[0] = finalI - 1;
                    while (j[0] >= 0 && Integer.parseInt(sheeplist[j[0]].getName()) > Integer.parseInt(inserir[0].getName())) {
                        sheeplist[j[0] + 1] = sheeplist[j[0]];
                        j[0] = j[0] - 1;
                    }
                    sheeplist[j[0] + 1] = inserir[0];
                    replace(location);
                }}.runTaskLater(app, tempo);
                tempo += 10;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage("Ovelhas ordenadas\n" + print_order(sheeplist));
            }
        }.runTaskLater(app, tempo);
    }

    void selection(CommandSender sender) {
        //TODO Implementar selection sort
        Player player = (Player) sender;
        player.sendMessage("Executando selection sort");
        for (int i = 1; i < 16; ++i) {
            int key = Integer.parseInt(sheeplist[i].getName());
            int j = i - 1;

            while (j >= 0 && Integer.parseInt(sheeplist[j].getName()) > key) {
                sheeplist[j + 1] = sheeplist[j];
                j = j - 1;
            }
            sheeplist[j + 1] = sheeplist[i];
        }
        player.sendMessage("Ovelhas ordenadas\n" + print_order(sheeplist));
    }
}