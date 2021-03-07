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

    @Override
    public String toString() {
        StringBuilder resp = new StringBuilder("Acao:" + acao.name() + " Parametro(s):");
        for (int value : param) {
            resp.append(value).append(" ");
        }
        resp.append("Alvo(s):");
        for (Entity alvo : alvos) {
            resp.append(alvo.getName()).append(" ");
        }
        return resp.toString();
    }
}
