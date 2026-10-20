package com.mediatech.emprunt.adapter.out.jdbc;
import com.mediatech.emprunt.application.port.out.Reservations;
import com.mediatech.emprunt.domaine.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class ReservationsJdbc implements Reservations {
 private final JdbcTemplate jdbc;
 public ReservationsJdbc(JdbcTemplate jdbc){this.jdbc=jdbc;}
 public Optional<AdherentId> premierReservataire(ExemplaireId id){
  List<String> ids=jdbc.query("SELECT r.adherent_id FROM reservation r JOIN exemplaire e ON e.isbn=r.isbn WHERE e.id=? ORDER BY r.date_reservation LIMIT 1",(rs,n)->Long.toString(rs.getLong(1)),IdsJdbc.longId(id));
  return ids.stream().findFirst().map(AdherentId::de);
 }
}
