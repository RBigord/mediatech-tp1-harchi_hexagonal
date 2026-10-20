package com.mediatech.emprunt.application;
import com.mediatech.emprunt.application.port.in.RendreDocument;
import com.mediatech.emprunt.application.port.out.*;
import com.mediatech.emprunt.domaine.*;
import java.time.Clock;
import java.time.LocalDate;
public class RendreDocumentService implements RendreDocument {
 private final Adherents adherents; private final Exemplaires exemplaires; private final Reservations reservations; private final Notificateur notificateur; private final Clock horloge;
 public RendreDocumentService(Adherents a, Exemplaires e, Reservations r, Notificateur n, Clock h){adherents=a;exemplaires=e;reservations=r;notificateur=n;horloge=h;}
 public void executer(Commande c){
  Exemplaire e=exemplaires.parId(c.exemplaireId()).orElseThrow(ErreursMetier.ExemplaireInconnu::new);
  Adherent a=adherents.empruntantActuellement(e.id()).orElseThrow(ErreursMetier.ExemplaireNonEmprunte::new);
  a.rendre(e,LocalDate.now(horloge));
  var reservataire=reservations.premierReservataire(e.id());
  e.rendre();\n  if(reservataire.isPresent()) e.mettreDeCote();
  adherents.enregistrer(a); exemplaires.enregistrer(e);
  reservataire.ifPresent(r->notificateur.prevenirReservataire(r,e.id()));
 }
}
