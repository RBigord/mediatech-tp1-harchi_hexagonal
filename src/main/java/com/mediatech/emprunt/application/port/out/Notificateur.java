package com.mediatech.emprunt.application.port.out;

import com.mediatech.emprunt.domaine.Adherent;
import com.mediatech.emprunt.domaine.Emprunt;

public interface Notificateur {
    void confirmerEmprunt(Adherent adherent, Emprunt emprunt);
}
