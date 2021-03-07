package io.github.tomast1337.animador;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;

import java.util.Objects;

public enum Acao {
    TrocaPulo,
    Particula,
    Som;

    private static void executarTrocaPulo(int[] param, Entity[] alvos) {

    }

    private static void executarParticula(int[] param, Entity[] alvos) {
        Particle particle = Particle.HEART;
        for (Entity alvo : alvos) {
            Objects.requireNonNull(alvo.getLocation().getWorld()).spawnParticle(particle, alvo.getLocation().add(0, 1, 0), 1);
        }
    }

    private static void executarSom(int[] param, Entity[] alvos) {

    }

    public static void executarAcao(final Instrucao ordem) {
        switch (ordem.acao) {
            case TrocaPulo:
                executarTrocaPulo(ordem.param, ordem.alvos);
                break;
            case Particula:
                executarParticula(ordem.param, ordem.alvos);
                break;
            case Som:
                executarSom(ordem.param, ordem.alvos);
                break;
            default:
                break;
        }
    }

}
