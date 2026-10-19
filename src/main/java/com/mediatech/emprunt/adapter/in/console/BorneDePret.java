package com.mediatech.emprunt.adapter.in.console;

import com.mediatech.emprunt.application.port.in.EmprunterDocument;
import com.mediatech.emprunt.application.port.in.EmprunterDocumentCommande;
import com.mediatech.emprunt.domaine.AdherentId;
import com.mediatech.emprunt.domaine.Emprunt;
import com.mediatech.emprunt.domaine.ExemplaireId;
import com.mediatech.emprunt.domaine.exceptions.ErreurMetier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UncheckedIOException;
import java.time.format.DateTimeFormatter;

public class BorneDePret {

    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final EmprunterDocument emprunterDocument;
    private final BufferedReader entree;
    private final PrintStream sortie;

    public BorneDePret(EmprunterDocument emprunterDocument, BufferedReader entree, PrintStream sortie) {
        this.emprunterDocument = emprunterDocument;
        this.entree = entree;
        this.sortie = sortie;
    }


    public void demarrer() {
        try {
            String ligne;
            while ((ligne = lire()) != null) {
                String[] mots = ligne.strip().split("\\s+");
                switch (mots[0].toLowerCase()) {
                    case "emprunter" -> emprunter(mots); // mots[1] = la carte, mots[2] = l'exemplaire
                    case "quitter" -> { return; }
                    case "" -> { }
                    default -> sortie.println("Commande inconnue.");
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String lire() throws IOException {
        sortie.print("borne> ");
        sortie.flush();
        return entree.readLine();
    }


    private void emprunter(String[] mots) {
        if (mots.length < 3) {
            sortie.println("Usage : emprunter <carte> <exemplaire>");
            return;
        }
        try {
            EmprunterDocumentCommande commande = new EmprunterDocumentCommande(
                    AdherentId.de(mots[1]), ExemplaireId.de(mots[2]));
            Emprunt emprunt = emprunterDocument.executer(commande);
            sortie.println("Emprunt enregistré. À rendre avant le "
                    + emprunt.retourPrevu().format(FORMAT_DATE) + ".");
        } catch (ErreurMetier e) {
            sortie.println("X" + e.getMessage());
        }
    }
}