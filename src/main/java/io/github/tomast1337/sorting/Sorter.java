package io.github.tomast1337.sorting;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import io.github.tomast1337.App;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Sorter implements CommandExecutor {
    private final SheepList sheeplist;
    private final App app;

    public Sorter(SheepList sheeplist, App app) {
        this.app = app;
        this.sheeplist = sheeplist;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                switch (args[0].toLowerCase()) {
                    case "menu":
                        menu(player);
                        break;
                    case "configurar":
                        configurar(player);
                        break;
                    case "algoritmos":
                        ordenar(player);
                        break;
                    default:
                        player.sendMessage(ChatColor.RED + "Erro: ultilise /sorter com os criar, embaralhar, destruir, mover, bubble, insertion, selection,");
                        break;
                }
            }
        }
        return true;
    }

    private void menu(Player player) {
        Location location = player.getLocation();

        String[] guiSetup;
        if (sheeplist.getStatusVida()) {
            guiSetup = new String[]{"    d    ", "   mei   ", "         "};
        } else {
            guiSetup = new String[]{"    c    "};
        }

        InventoryGui gui = new InventoryGui(app, "Sorter Menu", guiSetup);
        gui.setFiller(new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));

        gui.addElement(new StaticGuiElement('c', new ItemStack(Material.WHITE_WOOL), 1,
                InventoryClickEvent -> {

                    if (sheeplist.criar(location, player.getWorld())) {
                        Bukkit.broadcastMessage(ChatColor.RED + "Ovelhas criadas em " + player.getLocation().toVector().toString());
                    } else {
                        Bukkit.broadcastMessage(ChatColor.RED + "Ovelhas ja criadas");
                    }

                    player.closeInventory();
                    return true;
                },
                "Criar",
                ChatColor.GREEN + "Cria uma a array de ovelhas na sua localização"

        ));
        gui.addElement(new StaticGuiElement('m', new ItemStack(Material.SADDLE), 1,
                click -> {
                    Bukkit.broadcastMessage(ChatColor.BLUE + "Ovelhas movidas para " + player.getLocation().toVector().toString());
                    sheeplist.mover(location);
                    gui.close();
                    return true;
                },
                "Mover",
                ChatColor.BLUE + "Move a array de ovelhas para a sua localização"

        ));
        gui.addElement(new StaticGuiElement('e', new ItemStack(Material.MUSIC_DISC_CAT), 1,
                click -> {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "Ovelhas embaralhadas!");
                    sheeplist.embaralhar();
                    gui.close();
                    return true;
                },
                "Embaralhar",
                ChatColor.LIGHT_PURPLE + "Embaralha a array de ovelhas"

        ));
        gui.addElement(new StaticGuiElement('i', new ItemStack(Material.TWISTING_VINES), 1,
                click -> {
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "Ovelhas invertidas!");
                    sheeplist.inverter();
                    gui.close();
                    return true;
                },
                "Inverter",
                ChatColor.YELLOW + "Inverte a array de ovelhas"

        ));
        gui.addElement(new StaticGuiElement('d', new ItemStack(Material.TNT), 1,
                click -> {
                    Bukkit.broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "Ovelhas Destruidas!");
                    sheeplist.destruir();
                    gui.close();
                    return true;
                },
                "Destruir",
                ChatColor.RED + "Destroi a array de ovelhas"

        ));
        gui.show(player);

    }

    private void ordenar(Player player) {
        player.sendMessage("");
    }

    private void configurar(Player player) {
        String somStatus = sheeplist.getStatusSom() ? ChatColor.GREEN + "Ativado" : ChatColor.RED + "Desativo";
        String particulaStatus = sheeplist.getStatusParticulas() ? ChatColor.GREEN + "Ativado" : ChatColor.RED + "Desativo";


        String[] guiSetup = new String[]{"*********", "***s*p***", "*********"};
        InventoryGui gui = new InventoryGui(app, "Sorter Menu", guiSetup);
        gui.setFiller(new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1));

        gui.addElement(new StaticGuiElement('s', new ItemStack(Material.JUKEBOX), 1,
                        InventoryClickEvent -> {
                            if (sheeplist.somToggle()) {
                                Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Som Ativado");
                            } else {
                                Bukkit.broadcastMessage(ChatColor.DARK_RED + "Som Desativado");
                            }

                            player.closeInventory();
                            return true;
                        },
                        "Ativar/Desativar Som",
                        somStatus

                )
        );
        gui.addElement(new StaticGuiElement('p', new ItemStack(Material.CAMPFIRE), 1,
                        InventoryClickEvent -> {
                            if (sheeplist.particulasToggle()) {
                                Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Particulas Ativadas");
                            } else {
                                Bukkit.broadcastMessage(ChatColor.DARK_RED + "Particulas  Desativadas");
                            }

                            player.closeInventory();
                            return true;
                        },
                        "Ativar/Desativar Particulas",
                        particulaStatus

                )
        );
        gui.show(player);
    }
}