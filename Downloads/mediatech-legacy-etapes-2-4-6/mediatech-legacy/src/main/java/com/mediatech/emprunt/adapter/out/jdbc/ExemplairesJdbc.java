package com.mediatech.emprunt.adapter.out.jdbc;
import com.mediatech.emprunt.application.port.out.Exemplaires;
import com.mediatech.emprunt.domaine.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class ExemplairesJdbc implements Exemplaires {
 private final JdbcTemplate jdbc;
 public ExemplairesJdbc(JdbcTemplate jdbc){this.jdbc=jdbc;}
 public Optional<Exemplaire> parId(ExemplaireId id){
  var rows=jdbc.query("SELECT isbn,statut FROM exemplaire WHERE id=?",(rs,n)->new Exemplaire(id,rs.getString("isbn"),Statut.valueOf(rs.getString("statut"))),IdsJdbc.longId(id));
  return rows.stream().findFirst();
 }
 public void enregistrer(Exemplaire e){jdbc.update("UPDATE exemplaire SET statut=? WHERE id=?",e.statut().name(),IdsJdbc.longId(e.id()));}
}
