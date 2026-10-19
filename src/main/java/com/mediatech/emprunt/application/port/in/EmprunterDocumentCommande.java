package com.mediatech.emprunt.application.port.in;
 
import com.mediatech.emprunt.domaine.AdherentId;
import com.mediatech.emprunt.domaine.ExemplaireId;
 
/** Les données d'entrée du cas d'usage. Immuable. */
public record EmprunterDocumentCommande(AdherentId adherent, ExemplaireId exemplaire) {
 
    public EmprunterDocumentCommande {
        Objects.requireNonNull(adherent, "La commande doit indiquer un adhérent");
        Objects.requireNonNull(exemplaire, "La commande doit indiquer un exemplaire");
    }
}
