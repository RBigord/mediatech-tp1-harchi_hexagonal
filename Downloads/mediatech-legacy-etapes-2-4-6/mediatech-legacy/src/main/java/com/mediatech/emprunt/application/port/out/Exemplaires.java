package com.mediatech.emprunt.application.port.out;
import com.mediatech.emprunt.domaine.*;
import java.util.Optional;
public interface Exemplaires {
 Optional<Exemplaire> parId(ExemplaireId id);
 void enregistrer(Exemplaire exemplaire);
}
