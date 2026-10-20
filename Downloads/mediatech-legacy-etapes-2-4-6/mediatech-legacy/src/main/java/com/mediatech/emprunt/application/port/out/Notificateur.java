package com.mediatech.emprunt.application.port.out;
import com.mediatech.emprunt.domaine.*;
public interface Notificateur {
 void confirmerEmprunt(AdherentId adherent, ExemplaireId exemplaire, java.time.LocalDate retourPrevu);
 void prevenirReservataire(AdherentId reservataire, ExemplaireId exemplaire);
}
