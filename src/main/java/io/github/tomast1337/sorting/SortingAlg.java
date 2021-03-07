package io.github.tomast1337.sorting;

import io.github.tomast1337.animador.Acao;
import io.github.tomast1337.animador.Instrucao;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum SortingAlg {
    BUBBLE_SORT,
    INSERTION_SORT,
    SELECTION_SORT,
    QUICK_SORT,
    MERGE_SORT,
    HEAP_SORT,
    RADIX_SORT_BASE_2,
    COCKTAIL_SORT,
    NOT_SO_BOGO_SORT;

    public static ArrayList<Instrucao> bubbleSort(SheepList sl) {
        ArrayList<Instrucao> passosAnimacao = new ArrayList<>();
        Sheep[] array = sl.sheeplist;
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < (array.length - i); j++) {

                if (Integer.parseInt(array[j - 1].getName()) > Integer.parseInt(array[j].getName())) {
                    passosAnimacao.add(new Instrucao(Acao.Som, new int[]{10, 12}, new Entity[]{array[j]}));
                    passosAnimacao.add(new Instrucao(Acao.Particula, new int[]{0}, new Entity[]{array[j]}));
                    passosAnimacao.add(new Instrucao(Acao.Particula, new int[]{1}, new Entity[]{array[j - 1]}));
                    passosAnimacao.add(new Instrucao(Acao.TrocaPulo, new int[]{}, new Entity[]{array[j - 1], array[j]}));

                    Sheep aux = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = aux;
                } else {
                    passosAnimacao.add(new Instrucao(Acao.Particula, new int[]{1}, new Entity[]{array[j]}));
                    passosAnimacao.add(new Instrucao(Acao.Particula, new int[]{1}, new Entity[]{array[j - 1]}));
                }
            }
        }
        return passosAnimacao;
    }

    public static ArrayList<Instrucao> insertionSort(SheepList sl) {
        ArrayList<Instrucao> passosAnimacao = new ArrayList<>();
        Sheep[] array = sl.sheeplist;
        //TODO: adiciona instrucoes de animação
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            Sheep key = array[i];
            int j = i - 1;
            while (j >= 0 && Integer.parseInt(array[j].getName()) > Integer.parseInt(key.getName())) {
                array[j + 1] = array[j];
                j = j - 1;

                passosAnimacao.add(new Instrucao(Acao.TrocaPulo, new int[]{2}, new Entity[]{array[j + 1], array[j]}));
                passosAnimacao.add(new Instrucao(Acao.Particula, new int[]{1}, new Entity[]{array[j + 1]}));
                passosAnimacao.add(new Instrucao(Acao.Particula, new int[]{2}, new Entity[]{array[j]}));
                passosAnimacao.add(new Instrucao(Acao.Som, new int[]{1}, new Entity[]{array[j]}));
            }
            array[j + 1] = key;
        }
        return passosAnimacao;
    }

    public static ArrayList<Instrucao> selectionSort(SheepList sl) {
        ArrayList<Instrucao> passosAnimacao = new ArrayList<>();
        Sheep[] array = sl.sheeplist;
        //TODO: adiciona instrucoes de animação
        for (int i = 0; i < array.length - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < array.length; j++)
                if (Integer.parseInt(array[j].getName()) < Integer.parseInt(array[min_idx].getName())) {
                    min_idx = j;
                }
            Sheep aux = array[min_idx];
            array[min_idx] = array[i];
            array[i] = aux;
        }

        return passosAnimacao;
    }

    public static ArrayList<Instrucao> cocktailSort(SheepList sl) {
        ArrayList<Instrucao> passosAnimacao = new ArrayList<>();
        Sheep[] array = sl.sheeplist;
        ;
        //TODO: adiciona instrucoes de animação
        boolean swapped = true;
        int start = 0;
        int end = array.length;
        while (swapped) {
            swapped = false;
            for (int i = start; i < end - 1; ++i) {
                if (Integer.parseInt(array[i].getName()) > Integer.parseInt(array[i + 1].getName())) {
                    Sheep aux = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = aux;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
            swapped = false;
            end = end - 1;
            for (int i = end - 1; i >= start; i--) {
                if (Integer.parseInt(array[i].getName()) > Integer.parseInt(array[i + 1].getName())) {
                    Sheep aux = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = aux;
                    swapped = true;
                }
            }
            start = start + 1;
        }
        return passosAnimacao;
    }

    public static ArrayList<Instrucao> notSoBogoSort(SheepList sl) {
        ArrayList<Instrucao> passosAnimacao = new ArrayList<>();
        Sheep[] array = sl.sheeplist;
        ;
        //TODO: adiciona instrucoes de animação
        for (int i = 0; i < array.length; i++) {
            Sheep menor = array[i];
            Sheep[] fatia = Arrays.copyOfRange(array, i, array.length - 1);

            for (Sheep sheep : fatia) {
                if (Integer.parseInt(menor.getName()) < Integer.parseInt(sheep.getName()))
                    menor = sheep;
            }

            while (Integer.parseInt(fatia[0].getName()) != Integer.parseInt(menor.getName())) {
                List<Sheep> list = Arrays.asList(fatia);
                Collections.shuffle(list);
                list.toArray(array);
            }

            for (int j = 0; j < fatia.length; j++) {
                array[i + j] = fatia[i];
            }
        }
        return passosAnimacao;
    }

    public static ArrayList<Instrucao> mergeSort(SheepList sl) {
        ArrayList<Instrucao> passosAnimacao = new ArrayList<>();
        Sheep[] array = sl.sheeplist;

        return passosAnimacao;
    }

    private static ArrayList<Instrucao> mergeSortAlg(Sheep[] sheeplist, int inicio, int fim) {
        //TODO: adiciona instrucoes de animação
        ArrayList<Instrucao> passosAnimacao = new ArrayList<>();
        ;
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            //passosAnimação.append(mergeSortAlg(sheeplist, inicio, meio));
            //passosAnimação.append(mergeSortAlg(sheeplist, meio + 1, fim));
            merge(sheeplist, inicio, meio, fim);
        }
        return passosAnimacao;
    }

    private static void merge(Sheep[] sheeplist, int inicio, int meio, int fim) {
        Sheep[] esquerda = new Sheep[meio - inicio + 1];
        Sheep[] direita = new Sheep[meio - fim];

        for (int i = 0; i < esquerda.length; ++i) esquerda[i] = sheeplist[inicio + i];
        for (int j = 0; j < direita.length; ++j) direita[j] = sheeplist[meio + 1 + j];

        int i = 0, j = 0;

        int k = inicio;
        while (i < esquerda.length && j < direita.length) {
            if (Integer.parseInt(esquerda[i].getName()) <= Integer.parseInt(direita[j].getName())) {
                sheeplist[k] = esquerda[i];
                i++;
            } else {
                sheeplist[k] = direita[j];
                j++;
            }
            k++;
        }

        while (i < esquerda.length) {
            sheeplist[k] = esquerda[i];
            i++;
            k++;
        }

        while (j < direita.length) {
            sheeplist[k] = direita[j];
            j++;
            k++;
        }
    }

    public static ArrayList<Instrucao> quickSort(SheepList sl) {
        Sheep[] array = sl.sheeplist;
        ArrayList<Instrucao> passosAnimacao = new ArrayList<>();
        //TODO: a ser implementado

        return passosAnimacao;
    }

    public static ArrayList<Instrucao> RadixSortBase2(SheepList sl) {
        Sheep[] array = sl.sheeplist;
        ArrayList<Instrucao> passosAnimacao = new ArrayList<>();
        //TODO: a ser implementado

        return passosAnimacao;
    }

    public static ArrayList<Instrucao> heapSort(SheepList sl) {
        Sheep[] array = sl.sheeplist;
        ArrayList<Instrucao> passosAnimacao = new ArrayList<>();
        //TODO: a ser implementado

        return passosAnimacao;
    }
}
