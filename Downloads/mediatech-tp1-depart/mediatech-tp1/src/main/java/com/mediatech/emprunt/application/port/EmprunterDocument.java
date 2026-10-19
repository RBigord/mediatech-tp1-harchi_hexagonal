package com.mediatech.emprunt.application.port.in;

import com.mediatech.emprunt.domaine.Emprunt;

/**
 * Port primaire — cas d'usage : un adhérent emprunte un exemplaire.
 */
public interface EmprunterDocument {

    Emprunt executer(EmprunterDocumentCommande commande);
}