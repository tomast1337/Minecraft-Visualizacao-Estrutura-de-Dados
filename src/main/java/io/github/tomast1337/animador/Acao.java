package io.github.tomast1337.animador;

import io.github.tomast1337.util.Escalas;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;

import java.util.Objects;

import static io.github.tomast1337.util.Escalas.notaParaTom;

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
        for (Entity alvo : alvos) {
            int nome = Integer.parseInt(alvo.getName());
            int volume = 12;
            Sound som;
            if (param.length == 1) {
                som = Escalas.instrumentos[param[0]];
            } else {
                som = Escalas.instrumentos[param[0]];
                volume = param[1];
            }
            Objects.requireNonNull(alvo.getLocation().getWorld()).playSound(alvo.getLocation(), som, volume, notaParaTom(nome - 1));
        }
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
