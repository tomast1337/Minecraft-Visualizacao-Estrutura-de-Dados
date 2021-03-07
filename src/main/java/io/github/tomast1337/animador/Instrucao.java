package io.github.tomast1337.animador;

import org.bukkit.entity.Entity;

public class Instrucao {
    Acao acao;
    int[] param;
    Entity[] alvos;

    public Instrucao(Acao acao, int[] param, Entity[] alvos) {
        this.acao = acao;
        this.param = param;
        this.alvos = alvos;
    }
}
