package io.github.tomast1337.sorting;

import org.bukkit.entity.Sheep;

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

    public static String bubbleSort(Sheep[] sheeplist) {
        StringBuilder passosAnimação = new StringBuilder("");
        for (int i = 0; i < sheeplist.length; i++) {
            for (int j = 1; j < (sheeplist.length - i); j++) {
                if (Integer.parseInt(sheeplist[j - 1].getName()) > Integer.parseInt(sheeplist[j].getName())) {
                    //TODO: adiciona animação
                    Sheep aux = sheeplist[j - 1];
                    sheeplist[j - 1] = sheeplist[j];
                    sheeplist[j] = aux;
                }

            }
        }
        return passosAnimação.toString();
    }

    public static String insertionSort(Sheep[] sheeplist) {
        StringBuilder passosAnimação = new StringBuilder("");
        int n = sheeplist.length;
        for (int i = 1; i < n; ++i) {
            Sheep key = sheeplist[i];
            int j = i - 1;
            //TODO: adiciona animação
            while (j >= 0 && Integer.parseInt(sheeplist[j].getName()) > Integer.parseInt(key.getName())) {
                sheeplist[j + 1] = sheeplist[j];
                j = j - 1;
            }

            sheeplist[j + 1] = key;
        }
        return passosAnimação.toString();
    }

    public static String selectionSort(Sheep[] sheeplist) {
        StringBuilder passosAnimação = new StringBuilder("");

        for (int i = 0; i < sheeplist.length - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < sheeplist.length; j++)
                if (Integer.parseInt(sheeplist[j].getName()) < Integer.parseInt(sheeplist[min_idx].getName())) {
                    min_idx = j;
                }
            //TODO: adiciona animação
            Sheep aux = sheeplist[min_idx];
            sheeplist[min_idx] = sheeplist[i];
            sheeplist[i] = aux;
        }

        return passosAnimação.toString();
    }

    public static String cocktailSort(Sheep[] sheeplist) {
        StringBuilder passosAnimação = new StringBuilder("");
        boolean swapped = true;
        int start = 0;
        int end = sheeplist.length;
        //TODO: adiciona animação
        while (swapped) {
            swapped = false;
            for (int i = start; i < end - 1; ++i) {
                if (Integer.parseInt(sheeplist[i].getName()) > Integer.parseInt(sheeplist[i + 1].getName())) {
                    Sheep aux = sheeplist[i];
                    sheeplist[i] = sheeplist[i + 1];
                    sheeplist[i + 1] = aux;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
            swapped = false;
            end = end - 1;
            for (int i = end - 1; i >= start; i--) {
                if (Integer.parseInt(sheeplist[i].getName()) > Integer.parseInt(sheeplist[i + 1].getName())) {
                    Sheep aux = sheeplist[i];
                    sheeplist[i] = sheeplist[i + 1];
                    sheeplist[i + 1] = aux;
                    swapped = true;
                }
            }
            start = start + 1;
        }
        return passosAnimação.toString();
    }

    public static String notSoBoggoSort(Sheep[] sheeplist) {
        StringBuilder passosAnimação = new StringBuilder("");

        return passosAnimação.toString();
    }

    public static String mergeSort(Sheep[] sheeplist) {
        StringBuilder passosAnimação = new StringBuilder("");

        return passosAnimação.toString();
    }

    public static String quickSort(Sheep[] sheeplist) {
        StringBuilder passosAnimação = new StringBuilder("");

        return passosAnimação.toString();
    }

    public static String RadixSortBase2(Sheep[] sheeplist) {
        StringBuilder passosAnimação = new StringBuilder("");

        return passosAnimação.toString();
    }

    public static String heapSort(Sheep[] sheeplist) {
        StringBuilder passosAnimação = new StringBuilder("");
        return passosAnimação.toString();
    }
}
