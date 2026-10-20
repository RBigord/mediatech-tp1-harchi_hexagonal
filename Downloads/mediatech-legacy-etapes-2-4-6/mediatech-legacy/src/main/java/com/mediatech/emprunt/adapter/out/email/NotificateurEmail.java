package com.mediatech.emprunt.adapter.out.email;
import com.mediatech.emprunt.application.port.out.*;
import com.mediatech.emprunt.domaine.*;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class NotificateurEmail implements Notificateur {
 private final Adherents adherents; private final JavaMailSender mailSender;
 public NotificateurEmail(Adherents adherents,JavaMailSender mailSender){this.adherents=adherents;this.mailSender=mailSender;}
 public void confirmerEmprunt(AdherentId id, ExemplaireId exemplaire, java.time.LocalDate retour){
  SimpleMailMessage m=new SimpleMailMessage(); m.setTo(adherents.email(id));m.setSubject("Confirmation d'emprunt");
  m.setText("Bonjour "+adherents.prenom(id)+", document à rendre avant le "+retour);mailSender.send(m);
 }
 public void prevenirReservataire(AdherentId id, ExemplaireId exemplaire){
  SimpleMailMessage m=new SimpleMailMessage();m.setTo(adherents.email(id));m.setSubject("Votre réservation est disponible");
  m.setText("Bonjour "+adherents.prenom(id)+", votre document vous attend pendant 3 jours.");mailSender.send(m);
 }
}
