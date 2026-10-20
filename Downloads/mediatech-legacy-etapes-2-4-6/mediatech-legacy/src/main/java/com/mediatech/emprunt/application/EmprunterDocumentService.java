package com.mediatech.emprunt.application;
import com.mediatech.emprunt.application.port.in.EmprunterDocument;
import com.mediatech.emprunt.application.port.out.*;
import com.mediatech.emprunt.domaine.*;
import java.time.Clock;
import java.time.LocalDate;
public class EmprunterDocumentService implements EmprunterDocument {
 private final Adherents adherents; private final Exemplaires exemplaires; private final Notificateur notificateur; private final Clock horloge;
 public EmprunterDocumentService(Adherents a, Exemplaires e, Notificateur n, Clock h){adherents=a;exemplaires=e;notificateur=n;horloge=h;}
 public LocalDate executer(Commande c){
  Adherent a=adherents.parId(c.adherentId()).orElseThrow(ErreursMetier.AdherentInconnu::new);
  Exemplaire e=exemplaires.parId(c.exemplaireId()).orElseThrow(ErreursMetier.ExemplaireInconnu::new);
  Emprunt emprunt=a.emprunter(e,LocalDate.now(horloge));
  adherents.enregistrer(a); exemplaires.enregistrer(e);
  notificateur.confirmerEmprunt(a.id(),e.id(),emprunt.retourPrevu());
  return emprunt.retourPrevu();
 }
}
