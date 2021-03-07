package io.github.tomast1337.animador;

import io.github.tomast1337.App;
import io.github.tomast1337.sorting.SheepList;
import io.github.tomast1337.sorting.SortingAlg;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import static io.github.tomast1337.animador.Acao.executarAcao;
import static io.github.tomast1337.sorting.SortingAlg.*;

public class Animador {
    public static void executarSorting(SortingAlg sortingAlg, App app, SheepList sheeplist) {
        ArrayList<ArrayList<Instrucao>> animacao = new ArrayList<ArrayList<Instrucao>>();
        switch (sortingAlg) {
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
                animacao = notSoBoggoSort(sheeplist);
                break;
            default:
                break;
        }
        Animador.AnimarSheepList(app, sheeplist, animacao);
    }

    public static void AnimarSheepList(App app, SheepList sheepList, ArrayList<ArrayList<Instrucao>> instrucoes) {
        int tempo = 0;
        for (ArrayList<Instrucao> linha : instrucoes) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Instrucao ordem : linha) {
                        executarAcao(ordem);
                    }
                }
            }.runTaskLater(app, tempo);
            tempo += sheepList.getVelocidade();
        }
    }
}
