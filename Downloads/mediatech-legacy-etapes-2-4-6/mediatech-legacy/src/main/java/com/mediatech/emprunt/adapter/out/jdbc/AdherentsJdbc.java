package com.mediatech.emprunt.adapter.out.jdbc;
import com.mediatech.emprunt.application.port.out.Adherents;
import com.mediatech.emprunt.domaine.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.time.Clock;
import java.time.LocalDate;
import java.util.*;
@Repository
public class AdherentsJdbc implements Adherents {
 private final JdbcTemplate jdbc; private final Clock horloge;
 public AdherentsJdbc(JdbcTemplate jdbc, Clock horloge){this.jdbc=jdbc;this.horloge=horloge;}
 public Optional<Adherent> parId(AdherentId id){
  List<Adherent> rows=jdbc.query("SELECT categorie,suspendu_jusqu_au FROM adherent WHERE id=?", (rs,n)->{
   Date d=rs.getDate("suspendu_jusqu_au");
   List<Emprunt> es=jdbc.query("SELECT exemplaire_id,date_emprunt,date_retour_prevue,date_retour FROM emprunt WHERE adherent_id=?",
    (r,k)->new Emprunt(IdsJdbc.exemplaire(r.getLong("exemplaire_id")),r.getDate("date_emprunt").toLocalDate(),r.getDate("date_retour_prevue").toLocalDate(),r.getDate("date_retour")==null?null:r.getDate("date_retour").toLocalDate()),IdsJdbc.longId(id));
   return new Adherent(id,Categorie.valueOf(rs.getString("categorie")),d==null?null:d.toLocalDate(),es);
  },IdsJdbc.longId(id));
  return rows.stream().findFirst();
 }
 public void enregistrer(Adherent a){
  jdbc.update("UPDATE adherent SET suspendu_jusqu_au=? WHERE id=?",a.suspenduJusquAu(),IdsJdbc.longId(a.id()));
  for(Emprunt e:a.emprunts()){
   List<Long> ids=jdbc.query("SELECT id FROM emprunt WHERE adherent_id=? AND exemplaire_id=? ORDER BY id DESC LIMIT 1",(rs,n)->rs.getLong(1),IdsJdbc.longId(a.id()),IdsJdbc.longId(e.exemplaireId()));
   if(ids.isEmpty()) jdbc.update("INSERT INTO emprunt(adherent_id,exemplaire_id,date_emprunt,date_retour_prevue,date_retour) VALUES(?,?,?,?,?)",IdsJdbc.longId(a.id()),IdsJdbc.longId(e.exemplaireId()),e.dateEmprunt(),e.retourPrevu(),e.dateRetour());
   else if(e.dateRetour()!=null) jdbc.update("UPDATE emprunt SET date_retour=? WHERE id=?",e.dateRetour(),ids.get(0));
  }
 }
 public Optional<Adherent> empruntantActuellement(ExemplaireId e){
  List<Long> ids=jdbc.query("SELECT adherent_id FROM emprunt WHERE exemplaire_id=? AND date_retour IS NULL",(rs,n)->rs.getLong(1),IdsJdbc.longId(e));
  return ids.isEmpty()?Optional.empty():parId(IdsJdbc.adherent(ids.get(0)));
 }
 public String prenom(AdherentId id){return jdbc.queryForObject("SELECT prenom FROM adherent WHERE id=?",String.class,IdsJdbc.longId(id));}
 public String email(AdherentId id){return jdbc.queryForObject("SELECT email FROM adherent WHERE id=?",String.class,IdsJdbc.longId(id));}
}
