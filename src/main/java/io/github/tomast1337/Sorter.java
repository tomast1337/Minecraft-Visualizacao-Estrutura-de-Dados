package io.github.tomast1337;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Sorter implements CommandExecutor {
    private final DyeColor[] ordemCor = {
            DyeColor.WHITE, DyeColor.LIGHT_GRAY, DyeColor.GRAY, DyeColor.BLACK,
            DyeColor.BROWN, DyeColor.RED, DyeColor.ORANGE, DyeColor.YELLOW,
            DyeColor.LIME, DyeColor.GREEN, DyeColor.CYAN, DyeColor.LIGHT_BLUE,
            DyeColor.BLUE, DyeColor.PURPLE, DyeColor.MAGENTA, DyeColor.PINK};
    private final float[] ordemPich = {0, 2, 3, 5, 7, 8, 11, 12, 14, 15, 17, 19, 20, 23, 24, 24};
    private final Sheep[] sheeplist;
    private final App app;

    private Boolean statusVida = false;
    private Boolean statusParticulas = true;
    private Boolean statusSom = true;

    public Sorter(Sheep[] sheeplist, App app) {
        this.app = app;
        this.sheeplist = sheeplist;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                int speed = 10;
                if (args.length == 2) speed = Integer.parseInt(args[1]);
                switch (args[0].toLowerCase()) {
                    case "criar":
                        criar(sender);
                        break;
                    case "embaralhar":
                        if (chekarStatusVida(sender))
                            embaralhar(sender);
                        break;
                    case "inverter":
                        if (chekarStatusVida(sender))
                            inverter(sender);
                        break;
                    case "destruir":
                        if (chekarStatusVida(sender))
                            destruir(sender);
                        break;
                    case "mover":
                        if (chekarStatusVida(sender))
                            mover(sender);
                        break;
                    case "particula":
                        particulasToggle();
                        break;
                    case "som":
                        somToggle();
                        break;
                    case "bubble":
                        if (chekarStatusVida(sender))
                            bubble(sender, speed);
                        break;
                    case "insertion":
                        if (chekarStatusVida(sender))
                            insertion(sender, speed);
                        break;
                    case "selection":
                        if (chekarStatusVida(sender))
                            selection(sender, speed);
                        break;
                    default:
                        player.sendMessage(ChatColor.RED + "Erro: ultilise /sorter com os criar, embaralhar, destruir, mover, bubble, insertion, selection,");
                        break;
                }
            } else {
                player.sendMessage(ChatColor.RED + "Erro: utilise /sorter com os criar, embaralhar, destruir, mover, bubble, insertion, selection,");
            }
        }
        return true;
    }

    void particulasToggle() {
        statusParticulas = !statusParticulas;
        if (statusSom)
            Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Som Ativado");
        else
            Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "Som Desativado");

    }

    void somToggle() {
        statusSom = !statusSom;
        if (statusParticulas)
            Bukkit.broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Particulas Ativadas");
        else
            Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "Particulas Desativadas");

    }

    private boolean chekarStatusVida(CommandSender sender) {
        final Player player = (Player) sender;
        if (!statusVida) {
            player.sendMessage(ChatColor.YELLOW + "Use /sorter criar primeiro");
        }
        return statusVida;
    }

    void replace(Location original) {
        Location location = original.clone();
        for (Sheep sheep : sheeplist) {
            sheep.teleport(location);
            sheep.setGlowing(false);
            location.add(2, 0, 0);
        }
    }

    String print_order(Sheep[] sheeps) {
        StringBuilder result = new StringBuilder("[");
        for (Sheep sheep : sheeps) {
            result.append(Integer.parseInt(sheep.getName())).append(" ");
        }
        result.append("]");
        return result.toString();
    }

    void createEffects(Sheep sheep, Particle particle, int count) {
        Location location = sheep.getLocation();

        if (statusParticulas)
            Objects.requireNonNull(location.getWorld()).spawnParticle(particle, location.add(0, 1, 0), count);
        if (statusSom) {
            int sheepName = Integer.parseInt(sheep.getName());
            float picht = (float) (0.5 *  Math.pow(2,ordemPich[sheepName - 1]/12));
            Objects.requireNonNull(location.getWorld()).playSound(location, Sound.BLOCK_NOTE_BLOCK_HARP, 12, picht);
        }
    }

    void criar(CommandSender sender) {
        final Player player = (Player) sender;
        World world = player.getWorld();
        if (!statusVida) {
            for (int i = 0, off = 0; i < sheeplist.length; i++, off += 2) {
                sheeplist[i] = (Sheep) world.spawnEntity(player.getLocation().add(off, 0, 0), EntityType.SHEEP);
                sheeplist[i].setCustomName(String.valueOf(i + 1));
                sheeplist[i].setColor(ordemCor[i]);
                sheeplist[i].setAI(false);
                sheeplist[i].setCustomNameVisible(true);
                createEffects(sheeplist[i], Particle.EXPLOSION_HUGE, 4);
            }
            statusVida = true;
            Bukkit.broadcastMessage(ChatColor.BLUE + "Ovelhas Criadas\n" + print_order(sheeplist));
        } else {
            player.sendMessage(ChatColor.YELLOW + "Ovelhas ja criadas!");
        }

    }

    void mover(CommandSender sender) {
        final Player player = (Player) sender;
        Location location = player.getLocation();
        for (Sheep sheep : sheeplist) {
            sheep.teleport(location);
            location.add(2, 0, 0);
        }
        player.sendMessage(ChatColor.BLUE + "Ovelhas movidas para sua localização");
    }

    void destruir(CommandSender sender) {
        final Player player = (Player) sender;
        int tempo = 0;
        for (final Sheep sheep : sheeplist) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    createEffects(sheep, Particle.EXPLOSION_NORMAL, 4);
                    sheep.damage(99);
                }
            }.runTaskLater(app, tempo);
            tempo += 3;
        }
        statusVida = false;
        Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "Ovelhas destruidas\nUse criar para fazer uma nova array");
    }

    private void inverter(CommandSender sender) {
        final Player player = (Player) sender;

        Location location = sheeplist[0].getLocation();
        List<Sheep> list = Arrays.asList(sheeplist);
        Collections.reverse(list);
        list.toArray(sheeplist);
        replace(location);

        Bukkit.broadcastMessage(ChatColor.BLUE + "Ovelhas estão na posição inversa");
    }

    void embaralhar(CommandSender sender) {
        final Player player = (Player) sender;
        int tempo = 0;
        for (int i = 0; i < 3; i++, tempo += 15) {
            final Location l = sheeplist[0].getLocation();
            new BukkitRunnable() {
                @Override
                public void run() {
                    List<Sheep> list = Arrays.asList(sheeplist);
                    Collections.shuffle(list);
                    list.toArray(sheeplist);
                    for (int i = 0; i < 16; i++) {
                        sheeplist[i].teleport(l);
                        createEffects(sheeplist[i], Particle.COMPOSTER, 4);
                        l.add(2, 0, 0);
                    }
                }
            }.runTaskLater(app, tempo);
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.BLUE + "Ovelhas embaralhadas\n" + print_order(sheeplist));
            }
        }.runTaskLater(app, tempo + 5);
    }

    void bubble(CommandSender sender, final int speed) {
        final Player player = (Player) sender;
        Bukkit.broadcastMessage(ChatColor.RED + "Executando Bubble Sort");
        final Location location = sheeplist[0].getLocation();
        final int[] tempo = {20};
        for (int i = 0; i < sheeplist.length; i++) {
            for (int j = 0; j < sheeplist.length - 1; j++) {
                final int finalJ = j;
                final Sheep[] aux = new Sheep[1];
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        sheeplist[finalJ].setGlowing(true);
                        sheeplist[finalJ + 1].setGlowing(true);
                        if (Integer.parseInt(sheeplist[finalJ].getName()) > Integer.parseInt(sheeplist[finalJ + 1].getName())) {
                            createEffects(sheeplist[finalJ], Particle.HEART, 4);
                            createEffects(sheeplist[finalJ + 1], Particle.HEART, 4);

                            aux[0] = sheeplist[finalJ];
                            sheeplist[finalJ] = sheeplist[finalJ + 1];
                            sheeplist[finalJ + 1] = aux[0];
                            replace(location);
                        } else {
                            createEffects(sheeplist[finalJ], Particle.VILLAGER_ANGRY, 4);
                            createEffects(sheeplist[finalJ + 1], Particle.VILLAGER_ANGRY, 4);
                            tempo[0] -= speed;
                        }
                    }
                }.runTaskLater(app, tempo[0]);
                tempo[0] += speed;
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Ovelhas ordenadas\n" + print_order(sheeplist));
                replace(location);
            }
        }.runTaskLater(app, tempo[0] - speed);
    }

    void insertion(CommandSender sender, final int speed) {
        final Player player = (Player) sender;
        Bukkit.broadcastMessage(ChatColor.RED + "Executando Insertion Sort");
        final Sheep[] inserir = new Sheep[1];

        final Location location = sheeplist[0].getLocation();

        int tempo = 20;
        final int[] j = {1};
        for (int i = 1; i < sheeplist.length; i++) {
            final int finalI = i;
            sheeplist[i].setGlowing(true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    inserir[0] = sheeplist[finalI];
                    sheeplist[finalI].setGlowing(true);
                    j[0] = finalI - 1;
                    while (j[0] >= 0 && Integer.parseInt(sheeplist[j[0]].getName()) > Integer.parseInt(inserir[0].getName())) {
                        createEffects(sheeplist[j[0]], Particle.HEART, 4);
                        createEffects(inserir[0], Particle.HEART, 4);

                        sheeplist[j[0] + 1] = sheeplist[j[0]];
                        j[0] = j[0] - 1;
                    }
                    sheeplist[j[0] + 1] = inserir[0];
                    replace(location);
                }
            }.runTaskLater(app, tempo);
            tempo += speed;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Ovelhas ordenadas\n" + print_order(sheeplist));
            }
        }.runTaskLater(app, tempo);
    }

    void selection(CommandSender sender, final int speed) {
        final Player player = (Player) sender;
        Bukkit.broadcastMessage(ChatColor.RED + "Executando Selection Sort");
        final Location location = sheeplist[0].getLocation();
        int tempo = 20;
        for (int i = 0; i < sheeplist.length; ++i) {
            final int finalI = i;
            new BukkitRunnable() {
                @Override
                public void run() {
                    int menorIndex = finalI;
                    for (int j = finalI + 1; j < sheeplist.length; ++j) {
                        if (Integer.parseInt(sheeplist[j].getName()) < Integer.parseInt(sheeplist[menorIndex].getName())) {
                            createEffects(sheeplist[j], Particle.HEART, 4);
                            createEffects(sheeplist[menorIndex], Particle.HEART, 4);
                            menorIndex = j;
                        } else {
                            createEffects(sheeplist[j], Particle.VILLAGER_ANGRY, 4);
                        }
                    }
                    Sheep aux = sheeplist[finalI];
                    sheeplist[finalI] = sheeplist[menorIndex];
                    sheeplist[menorIndex] = aux;
                    replace(location);
                }
            }.runTaskLater(app, tempo);
            tempo += speed;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.GOLD + "Ovelhas ordenadas\n" + print_order(sheeplist));
            }
        }.runTaskLater(app, tempo);
    }
}