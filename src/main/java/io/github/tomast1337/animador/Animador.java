package io.github.tomast1337.animador;

import io.github.tomast1337.App;
import io.github.tomast1337.sorting.Algoritmos;
import io.github.tomast1337.sorting.SheepList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static io.github.tomast1337.animador.Acao.executarAcao;
import static io.github.tomast1337.sorting.Algoritmos.*;

public class Animador {
    public static void executarSorting(Algoritmos algoritmos, App app, SheepList sheeplist) {
        ArrayList<Instrucao> animacao = new ArrayList<>();
        switch (algoritmos) {
            case BUBBLE_SORT:
                animacao = bubbleSort(sheeplist);
                break;
            case INSERTION_SORT:
                animacao = insertionSort(sheeplist);
                break;
            case SELECTION_SORT:
                animacao = selectionSort(sheeplist);
                break;
            case QUICK_SORT:
                animacao = quickSort(sheeplist);
                break;
            case MERGE_SORT:
                animacao = mergeSort(sheeplist);
                break;
            case HEAP_SORT:
                animacao = heapSort(sheeplist);
                break;
            case RADIX_SORT_BASE_2:
                animacao = RadixSortBase2(sheeplist);
                break;
            case COCKTAIL_SORT:
                animacao = cocktailSort(sheeplist);
                break;
            case NOT_SO_BOGO_SORT:
                animacao = notSoBogoSort(sheeplist);
                break;
            case BINARY_SEARCH:
                animacao = binarySearch(sheeplist);
                break;
            default:
                break;
        }
        AnimarSheepList(app, sheeplist, animacao);
    }

    public static void executarListaInstrucoes(App app, SheepList sheepList, ArrayList<Instrucao> instrucoes, String mensagemFinal) {
        int tempo = 0;
        for (Instrucao instrucao : instrucoes) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    executarAcao(instrucao);
                }
            }.runTaskLater(app, tempo);
            tempo += sheepList.getVelocidade();
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(mensagemFinal);
            }
        }.runTaskLater(app, tempo);
    }

    public static void AnimarSheepList(App app, SheepList sheepList, ArrayList<Instrucao> instrucoes) {
        int tempo = 0;
        for (Instrucao instrucao : instrucoes) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    executarAcao(instrucao);
                }
            }.runTaskLater(app, tempo);
            tempo += sheepList.getVelocidade();
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "Ovelhas ordenadas");
                Bukkit.broadcastMessage(ChatColor.GOLD + sheepList.printArray());
            }
        }.runTaskLater(app, tempo);
    }
}
