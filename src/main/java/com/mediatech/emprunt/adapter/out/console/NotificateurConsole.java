package com.mediatech.emprunt.adapter.out.console;

import com.mediatech.emprunt.application.port.out.Notificateur;
import com.mediatech.emprunt.domaine.Adherent;
import com.mediatech.emprunt.domaine.Emprunt;

import java.io.PrintStream;
import java.time.format.DateTimeFormatter;

/** Adapter secondaire : « notifie » l'adhérent en écrivant dans la console. */
public class NotificateurConsole implements Notificateur {

    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final PrintStream sortie;

    public NotificateurConsole(PrintStream sortie) {
        this.sortie = sortie;
    }

    @Override
    public void confirmerEmprunt(Adherent adherent, Emprunt emprunt) {
        sortie.println("📨 [notification à " + adherent.id() + "] Vous avez emprunté "
                + emprunt.exemplaire() + ", à rendre avant le "
                + emprunt.retourPrevu().format(FORMAT_DATE) + ".");
    }
}