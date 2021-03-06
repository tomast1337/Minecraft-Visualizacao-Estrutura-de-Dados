package io.github.tomast1337.sorting;

import io.github.tomast1337.util.Opcoes;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Sheep;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
    private int velocidade = 3;
    private int volume = 12;
    private float[] escalaSom;
    private int indexInstrumento = 4;

    public SheepList(int size) {
        this.sheeplist = new Sheep[size];
        escalaSom = Opcoes.escalaMaior;
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

    public String printArray() {
        StringBuilder result = new StringBuilder("[");
        for (Sheep sheep : sheeplist) {
            result.append(Integer.parseInt(sheep.getName())).append(" ");
        }
        return result.append("]").toString();
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

    public int getIndexInstrumento() {
        return indexInstrumento;
    }

    public void setIndexInstrumento(int indexInstrumento) {
        this.indexInstrumento = indexInstrumento;
    }

    public float[] getEscalaSom() {
        return escalaSom;
    }

    public void setEscalaSom(float[] escalaSom) {
        this.escalaSom = escalaSom;
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