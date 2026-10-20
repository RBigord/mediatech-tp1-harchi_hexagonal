package com.mediatech.emprunt.application.port.in;
import com.mediatech.emprunt.domaine.*;
public interface RendreDocument {
 void executer(Commande commande);
 record Commande(ExemplaireId exemplaireId) {}
}
