package com.mediatech.emprunt.application.port.out;
import com.mediatech.emprunt.domaine.*;
import java.util.Optional;
public interface Reservations { Optional<AdherentId> premierReservataire(ExemplaireId exemplaire); }
