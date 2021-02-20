package io.github.tomast1337;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import java.util.*;

public class Sorter implements CommandExecutor {
    private Sheep[] sheeplist;
    private Boolean statusVida = false;

    public Sorter(Sheep[] sheeplist) {
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

    void criar(CommandSender sender) {
        Player player = (Player) sender;
        World world = player.getWorld();
        if (!statusVida) {
            // Poderia ser um for mais esse projeto ta mais pra um hack rapido
            for (int i = 0, off = 0; i < 16; i++, off += 2) {
                sheeplist[i] = (Sheep) world.spawnEntity(player.getLocation().add(off, 0, 0), EntityType.SHEEP);
                sheeplist[i].setCustomName(String.valueOf(i));
                sheeplist[i].setColor(DyeColor.getByWoolData((byte) i));
                sheeplist[i].setAI(false);
            }
            statusVida = true;
            player.sendMessage("Criando Ovelhas...");
        } else {
            player.sendMessage("Ovelhas Ja criadas use mover ou destruir");
        }

    }

    void mover(CommandSender sender) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        for (int i = 0; i < 16; i++) {
            sheeplist[i].teleport(location.add(2, 0, 0));
        }
    }

    void destruir(CommandSender sender) {
        Player player = (Player) sender;
        World world = player.getWorld();
        for (Sheep sheep : sheeplist) {
            sheep.damage(99);
        }
        statusVida = false;
    }

    void embaralhar(CommandSender sender) {
        Player player = (Player) sender;
        Location l = sheeplist[0].getLocation();

        List<Sheep> list = Arrays.asList(sheeplist);
        Collections.shuffle(list);
        list.toArray(sheeplist);

        for (int i = 0; i < 16; i++) {
            sheeplist[i].teleport(l);
            l.add(2, 0, 0);
        }
        player.sendMessage("Embaralhando Ovelhas...");
    }

    void torcar(int idexX, int idexY) {
        Location Localx = sheeplist[idexX].getLocation();
        Location Localy = sheeplist[idexY].getLocation();

        sheeplist[idexX].setGlowing(false);
        sheeplist[idexY].setGlowing(false);

        sheeplist[idexX].teleport(Localy);
        sheeplist[idexY].teleport(Localx);
    }

    void bubble(CommandSender sender) {
        //TODO Implemetar animações
        Player player = (Player) sender;
        player.sendMessage("Executando bubble sort");
        Location location = sheeplist[0].getLocation();
        Sheep aux;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 15; j++) {
                sheeplist[j].setGlowing(true);
                sheeplist[j + 1].setGlowing(true);

                new Timer(true).schedule(new TimerTask() {
                    @Override
                    public void run() {
                    }
                }, 5000);

                if (Integer.parseInt(sheeplist[j].getName()) > Integer.parseInt(sheeplist[j + 1].getName())) {

                    aux = sheeplist[j];
                    sheeplist[j] = sheeplist[j + 1];
                    sheeplist[j + 1] = aux;
                }
            }
        }
    }

    void insertion(CommandSender sender) {
        //TODO Implemetar animações
        Player player = (Player) sender;
        player.sendMessage("Executando insertion sort");
        for (int i = 1; i < 16; ++i) {
            int key = Integer.parseInt(sheeplist[i].getName());
            int j = i - 1;

            while (j >= 0 && Integer.parseInt(sheeplist[j].getName()) > key) {
                sheeplist[j + 1] = sheeplist[j];
                j = j - 1;
            }
            sheeplist[j + 1] = sheeplist[i];
        }
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
    }
}