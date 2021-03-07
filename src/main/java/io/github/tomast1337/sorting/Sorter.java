package io.github.tomast1337.sorting;

import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import io.github.tomast1337.App;
import io.github.tomast1337.animador.Animador;
import io.github.tomast1337.util.Opcoes;
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

            int volume = args.length > 1 ? Integer.parseInt(args[1]) : 10;
            int velocidade = args.length > 1 ? Integer.parseInt(args[1]) : 10;

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
                    case "volume":
                        sheeplist.setVolume(volume);
                        Bukkit.broadcastMessage("Volume alterado para " + ChatColor.BOLD + "" + ChatColor.GOLD + sheeplist.getVolume());
                        break;
                    case "velocidade":
                        sheeplist.setVelocidade(velocidade);
                        Bukkit.broadcastMessage("Velocidade alterada para "
                                + ChatColor.GOLD + "" + ChatColor.BOLD + sheeplist.getVelocidade() + ChatColor.RESET + " ticks ou "
                                + ChatColor.GOLD + "" + ChatColor.BOLD + (float) sheeplist.getVelocidade() / 20 + ChatColor.RESET + " segundos entre cada interação");
                        break;
                    default:
                        player.sendMessage(ChatColor.RED + "Erro: ultilise /sorter [menu] [configurar] ou [algoritmos]");
                        break;
                }
            } else {
                player.sendMessage(ChatColor.RED + "Erro: ultilise /sorter [menu] [configurar] ou [algoritmos]");
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
        String[] guiSetup = new String[]{"*1**9**4*", "*2**8**5*", "*3**7**6*"};
        InventoryGui gui = new InventoryGui(app, "Sorter Algoritmos", guiSetup);
        gui.setFiller(new ItemStack(Material.END_STONE, 1));

        gui.addElement(new StaticGuiElement('1', new ItemStack(Material.EMERALD_BLOCK), 1, InventoryClickEvent -> {
            Animador.executarSorting(SortingAlg.BUBBLE_SORT, app, sheeplist);
            Bukkit.broadcastMessage(ChatColor.BOLD + "Executando " + ChatColor.GOLD + " Bubble Sort");
            return true;
        }, ChatColor.GOLD + "Bubble Sort", ChatColor.GOLD + "Executar algoritmo de ordenação Bubble Sort"));
        gui.addElement(new StaticGuiElement('2', new ItemStack(Material.KNOWLEDGE_BOOK), 1, InventoryClickEvent -> {
            Animador.executarSorting(SortingAlg.INSERTION_SORT, app, sheeplist);
            Bukkit.broadcastMessage(ChatColor.BOLD + "Executando " + ChatColor.GOLD + " Insertion Sort");
            return true;
        }, ChatColor.GOLD + "Insertion Sort", ChatColor.GOLD + "Executar algoritmo de ordenação Insertion Sort"));
        gui.addElement(new StaticGuiElement('3', new ItemStack(Material.TOTEM_OF_UNDYING), 1, InventoryClickEvent -> {
            Animador.executarSorting(SortingAlg.COCKTAIL_SORT, app, sheeplist);
            Bukkit.broadcastMessage(ChatColor.BOLD + "Executando " + ChatColor.GOLD + " Cocktail Sort");
            return true;
        }, ChatColor.GOLD + "Cocktail Sort", ChatColor.GOLD + "Executar algoritmo de ordenação Cocktail Sort"));

        gui.addElement(new StaticGuiElement('4', new ItemStack(Material.PAPER), 1, InventoryClickEvent -> {
            Animador.executarSorting(SortingAlg.NOT_SO_BOGO_SORT, app, sheeplist);
            Bukkit.broadcastMessage(ChatColor.BOLD + "Executando " + ChatColor.GOLD + " Not So Bogo Sort");
            return true;
        }, ChatColor.GOLD + "Not So Bogo Sort", ChatColor.GOLD + "Executar algoritmo de ordenação Not So Bogo Sort"));
        gui.addElement(new StaticGuiElement('5', new ItemStack(Material.MOSSY_COBBLESTONE_WALL), 1, InventoryClickEvent -> {
            Animador.executarSorting(SortingAlg.MERGE_SORT, app, sheeplist);
            Bukkit.broadcastMessage(ChatColor.BOLD + "Executando " + ChatColor.GOLD + " Merge Sort");
            return true;
        }, ChatColor.GOLD + "Merge Sort", ChatColor.GOLD + "Executar algoritmo de ordenação Merge Sort"));
        gui.addElement(new StaticGuiElement('6', new ItemStack(Material.SALMON), 1, InventoryClickEvent -> {
            Animador.executarSorting(SortingAlg.SELECTION_SORT, app, sheeplist);
            Bukkit.broadcastMessage(ChatColor.BOLD + "Executando " + ChatColor.GOLD + " Selection Sort");
            return true;
        }, ChatColor.GOLD + "Selection Sort", ChatColor.GOLD + "Executar algoritmo de ordenação Selection Sort"));

        gui.addElement(new StaticGuiElement('7', new ItemStack(Material.RED_MUSHROOM_BLOCK), 1, InventoryClickEvent -> {
            Animador.executarSorting(SortingAlg.QUICK_SORT, app, sheeplist);
            Bukkit.broadcastMessage(ChatColor.BOLD + "Executando " + ChatColor.GOLD + " Quick Sort");
            return true;
        }, ChatColor.GOLD + "Quick Sort", ChatColor.GOLD + "Executar algoritmo de ordenação Quick Sort"));
        gui.addElement(new StaticGuiElement('8', new ItemStack(Material.ICE), 1, InventoryClickEvent -> {
            Animador.executarSorting(SortingAlg.HEAP_SORT, app, sheeplist);
            Bukkit.broadcastMessage(ChatColor.BOLD + "Executando " + ChatColor.GOLD + " Heap Sort");
            return true;
        }, ChatColor.GOLD + "Heap Sort", ChatColor.GOLD + "Executar algoritmo de ordenação Heap Sort"));
        gui.addElement(new StaticGuiElement('9', new ItemStack(Material.END_PORTAL_FRAME), 1, InventoryClickEvent -> {
            Animador.executarSorting(SortingAlg.RADIX_SORT_BASE_2, app, sheeplist);
            Bukkit.broadcastMessage(ChatColor.BOLD + "Executando " + ChatColor.GOLD + " Radix Sort Base 2");
            return true;
        }, ChatColor.GOLD + "Radix Sort Base 2", ChatColor.GOLD + "Executar algoritmo de ordenação Radix Sort Base 2"));

        gui.show(player);
    }

    private void configurar(Player player) {
        String somStatus = sheeplist.getStatusSom() ? ChatColor.GREEN + "Ativado" : ChatColor.RED + "Desativo";
        String particulaStatus = sheeplist.getStatusParticulas() ? ChatColor.GREEN + "Ativado" : ChatColor.RED + "Desativo";


        String[] guiSetup = new String[]{"*********", "**s***p**", "*1234567*"};
        InventoryGui gui = new InventoryGui(app, "Sorter Configurações", guiSetup);
        gui.setFiller(new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1));

        gui.addElement(new StaticGuiElement('s', new ItemStack(Material.JUKEBOX), 1,
                        InventoryClickEvent -> {
                            if (sheeplist.somToggle())
                                Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Som Ativado");
                            else
                                Bukkit.broadcastMessage(ChatColor.DARK_RED + "Som Desativado");
                            player.closeInventory();
                            return true;
                        },
                        "Ativar/Desativar Som",
                        somStatus
                )
        );
        gui.addElement(new StaticGuiElement('p', new ItemStack(Material.CAMPFIRE), 1,
                        InventoryClickEvent -> {
                            if (sheeplist.particulasToggle())
                                Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Particulas Ativadas");
                            else
                                Bukkit.broadcastMessage(ChatColor.DARK_RED + "Particulas  Desativadas");
                            player.closeInventory();
                            return true;
                        },
                        "Ativar/Desativar Particulas",
                        particulaStatus
                )
        );

        gui.addElement(new StaticGuiElement('1', new ItemStack(Material.NOTE_BLOCK), 1,
                InventoryClickEvent -> {
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Selecionado Escala musical Maior");
                    sheeplist.setEscalaSom(Opcoes.escalaMaior);
                    player.closeInventory();
                    return true;
                }, "Selecionar escala Maior"));
        gui.addElement(new StaticGuiElement('2', new ItemStack(Material.NOTE_BLOCK), 1,
                InventoryClickEvent -> {
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Selecionado Escala musical Blues Menor");
                    sheeplist.setEscalaSom(Opcoes.escalaBluesMenor);
                    player.closeInventory();
                    return true;
                }, "Selecionar escala Blues Meno"));
        gui.addElement(new StaticGuiElement('3', new ItemStack(Material.NOTE_BLOCK), 1,
                InventoryClickEvent -> {
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Selecionado Escala musical Menor Harmonica");
                    sheeplist.setEscalaSom(Opcoes.escalaMaior);
                    player.closeInventory();
                    return true;
                }, "Selecionar escala Menor Harmonica"));
        gui.addElement(new StaticGuiElement('4', new ItemStack(Material.NOTE_BLOCK), 1,
                InventoryClickEvent -> {
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Selecionado Escala musical Maior Phrygia");
                    sheeplist.setEscalaSom(Opcoes.escalaPhrygia);
                    player.closeInventory();
                    return true;
                }, "Selecionar escala Phrygia"));
        gui.addElement(new StaticGuiElement('5', new ItemStack(Material.NOTE_BLOCK), 1,
                InventoryClickEvent -> {
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Selecionado Escala musical Maior Cromatica 1");
                    sheeplist.setEscalaSom(Opcoes.escalaCromatica1);
                    player.closeInventory();
                    return true;
                }, "Selecionar escala Cromatica 1"));
        gui.addElement(new StaticGuiElement('6', new ItemStack(Material.NOTE_BLOCK), 1,
                InventoryClickEvent -> {
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Selecionado Escala musical Maior Cromatica 2");
                    sheeplist.setEscalaSom(Opcoes.escalaCromatica2);
                    player.closeInventory();
                    return true;
                }, "Selecionar escala Cromatica 2"));
        gui.addElement(new StaticGuiElement('7', new ItemStack(Material.NOTE_BLOCK), 1,
                InventoryClickEvent -> {
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Selecionado Escala musical Maior Cromatica 3");
                    sheeplist.setEscalaSom(Opcoes.escalaCromatica3);
                    player.closeInventory();
                    return true;
                }, "Selecionar escala Cromatica 3"));

        gui.show(player);
    }
}