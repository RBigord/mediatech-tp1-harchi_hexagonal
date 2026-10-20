package com.mediatech.emprunt.application.port.in;
import com.mediatech.emprunt.domaine.*;
import java.time.LocalDate;
public interface EmprunterDocument {
 LocalDate executer(Commande commande);
 record Commande(AdherentId adherentId, ExemplaireId exemplaireId) {}
}
