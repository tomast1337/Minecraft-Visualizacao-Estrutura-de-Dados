package io.github.tomast1337.animador;

import io.github.tomast1337.util.Opcoes;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;

import java.util.Objects;

import static io.github.tomast1337.util.Opcoes.notaParaTom;

public enum Acao {
    TrocaPulo,
    Particula,
    Som,
    MoverCima;

    private static void executarTrocaPulo(int[] param, Entity[] alvos) {
        Location pos1 = alvos[0].getLocation();
        Location pos2 = alvos[1].getLocation();
        alvos[0].teleport(pos2);
        alvos[1].teleport(pos1);
    }

    private static void executarParticula(int[] param, Entity[] alvos) {
        Particle particle = Opcoes.particulas[param[0]];
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
                som = Opcoes.instrumentos[param[0]];
            } else {
                som = Opcoes.instrumentos[param[0]];
                volume = param[1];
            }
            Objects.requireNonNull(alvo.getLocation().getWorld()).playSound(alvo.getLocation(), som, volume, notaParaTom(nome - 1));
        }
    }

    public static void executarMoverCima(int[] param, Entity[] alvos) {
        for (Entity alvo : alvos) {
            Location location = alvo.getLocation();
            alvo.teleport(location.add(0, param[0], 0));
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
            case MoverCima:
                executarMoverCima(ordem.param, ordem.alvos);
                break;
            default:
                break;
        }
    }

}
