
package com.mediatech.emprunt.domaine.exceptions;

import com.mediatech.emprunt.domaine.AdherentId;
import java.time.LocalDate;

public class AdherentSuspendu extends ErreurMetier {
    public AdherentSuspendu(AdherentId id, LocalDate suspenduJusquAu) {
        super("L'adhérent " + id + " est suspendu jusqu'au "
                + suspenduJusquAu);
    }
}
