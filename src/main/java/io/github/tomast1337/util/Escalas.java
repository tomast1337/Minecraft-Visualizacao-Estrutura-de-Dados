package io.github.tomast1337.util;

import org.bukkit.Sound;

public class Escalas {
    public static final float[] escalaMenorHarmonica = {0, 2, 3, 5, 7, 8, 11, 12, 14, 15, 17, 19, 20, 23, 24, 24};
    public static final float[] escalaMaior = {0, 2, 4, 5, 7, 9, 11, 12, 14, 18, 16, 17, 19, 23, 24, 24};
    public static final float[] escalaPhrygia = {0, 1, 3, 5, 7, 8, 10, 12, 13, 15, 17, 19, 20, 22, 24, 24};
    public static final float[] escalaBluesMenor = {0, 0, 3, 5, 6, 7, 10, 12, 12, 15, 17, 18, 19, 22, 22, 24};
    public static final float[] escalaCromatica1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
    public static final float[] escalaCromatica2 = {9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
    public static final float[] escalaCromatica3 = {0, 1, 2, 4, 6, 8, 10, 12, 14, 14, 16, 18, 20, 22, 24, 24};
    public static final Sound[] instrumentos = {
            /*0*/ Sound.BLOCK_NOTE_BLOCK_BANJO,
            /*1*/ Sound.BLOCK_NOTE_BLOCK_BASEDRUM,
            /*2*/ Sound.BLOCK_NOTE_BLOCK_BASS,
            /*3*/ Sound.BLOCK_NOTE_BLOCK_BELL,
            /*4*/ Sound.BLOCK_NOTE_BLOCK_BIT,
            /*5*/ Sound.BLOCK_NOTE_BLOCK_CHIME,
            /*6*/ Sound.BLOCK_NOTE_BLOCK_COW_BELL,
            /*7*/ Sound.BLOCK_NOTE_BLOCK_DIDGERIDOO,
            /*8*/ Sound.BLOCK_NOTE_BLOCK_FLUTE,
            /*9*/ Sound.BLOCK_NOTE_BLOCK_GUITAR,
            /*10*/Sound.BLOCK_NOTE_BLOCK_HARP,
            /*11*/Sound.BLOCK_NOTE_BLOCK_HAT,
            /*12*/Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE,
            /*13*/Sound.BLOCK_NOTE_BLOCK_PLING,
            /*14*/Sound.BLOCK_NOTE_BLOCK_SNARE,
            /*15*/Sound.BLOCK_NOTE_BLOCK_XYLOPHONE
    };
}
