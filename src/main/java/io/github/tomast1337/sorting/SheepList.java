package io.github.tomast1337.sorting;

import io.github.tomast1337.util.Escalas;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.github.tomast1337.sorting.SortingAlg.*;

public class SheepList {

    public static final DyeColor[] ordemCorMatiz = {
            DyeColor.WHITE, DyeColor.LIGHT_GRAY, DyeColor.GRAY, DyeColor.BLACK,
            DyeColor.BROWN, DyeColor.RED, DyeColor.ORANGE, DyeColor.YELLOW,
            DyeColor.LIME, DyeColor.GREEN, DyeColor.CYAN, DyeColor.LIGHT_BLUE,
            DyeColor.BLUE, DyeColor.PURPLE, DyeColor.MAGENTA, DyeColor.PINK};
    public final Sheep[] sheeplist;
    private Boolean statusVida = false;
    private Boolean statusParticulas = true;
    private Boolean statusSom = true;
    private int velocidade = 10;
    private int volume = 10;
    private float[] escalaSom;


    public SheepList(int size) {
        this.sheeplist = new Sheep[size];
        escalaSom = Escalas.escalaMenorHarmobica;
    }

    public boolean criar(Location location, World world) {
        if (!statusVida) {
            int offset = 2, i = 0;
            for (Sheep sheep : sheeplist) {
                sheeplist[i] = (Sheep) world.spawnEntity(location.add(offset, 0, 0), EntityType.SHEEP);
                sheeplist[i].setCustomName(String.valueOf(i + 1));
                sheeplist[i].setColor(ordemCorMatiz[i]);
                sheeplist[i].setAI(false);
                sheeplist[i].setCustomNameVisible(true);
                i++;
                //TODO: Animar particula
            }
            statusVida = true;


        }
        return statusVida;
    }

    public void destruir() {
        for (final Sheep sheep : sheeplist) {
            //TODO: Animar particula
            sheep.damage(99);
        }
        statusVida = false;
    }

    public void mover(Location location) {
        for (Sheep sheep : sheeplist) {
            sheep.teleport(location);
            //TODO: Animar particula
            location.add(2, 0, 0);
        }
    }

    public void embaralhar() {
        Location location = sheeplist[0].getLocation();
        List<Sheep> list = Arrays.asList(sheeplist);
        Collections.shuffle(list);
        list.toArray(sheeplist);
        mover(location);
    }

    public void inverter() {
        Location location = sheeplist[0].getLocation();
        List<Sheep> list = Arrays.asList(sheeplist);
        Collections.reverse(list);
        list.toArray(sheeplist);
        mover(location);
    }

    public void executarSorting(SortingAlg sortingAlg) {
        String animacao;
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
        //TODO: executar animação
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("[");
        for (int i = 0; i < sheeplist.length; i++) {
            string.append(sheeplist[i].getName());
            if (i != sheeplist.length - 1) string.append(", ");
        }
        string.append("]");
        return string.toString();
    }

    public boolean somToggle() {
        statusSom = !statusSom;
        return statusSom;
    }

    public boolean particulasToggle() {
        statusParticulas = !statusParticulas;
        return statusParticulas;
    }

    public Boolean getStatusVida() {
        return statusVida;
    }

    public Boolean getStatusParticulas() {
        return statusParticulas;
    }

    public Boolean getStatusSom() {
        return statusSom;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
