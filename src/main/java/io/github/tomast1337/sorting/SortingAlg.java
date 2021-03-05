package io.github.tomast1337.sorting;

import org.bukkit.entity.Sheep;

public class SortingAlg {

    public static void bubbleSort(Sheep[] sheeplist) {

    }

    public static void insertionSort(Sheep[] sheeplist) {

    }

    public static void selectionsort(Sheep[] sheeplist) {

    }

    public static void quickSort(Sheep[] sheeplist) {

    }

    public static void mergeSort(Sheep[] sheeplist) {

    }

    public static void Radix2Sort(Sheep[] sheeplist) {

    }

    public static void cocktailSort(Sheep[] sheeplist) {

    }

    public static void notSoBoggoSort(Sheep[] sheeplist) {

    }

    public void swapByPos(Sheep[] sheeplist, int imdex1, int imdex2) {
        try {
            Sheep aux = sheeplist[imdex1];
            sheeplist[imdex1] = sheeplist[imdex2];
            sheeplist[imdex2] = aux;
        } catch (Exception ignored) {

        }
    }

}
