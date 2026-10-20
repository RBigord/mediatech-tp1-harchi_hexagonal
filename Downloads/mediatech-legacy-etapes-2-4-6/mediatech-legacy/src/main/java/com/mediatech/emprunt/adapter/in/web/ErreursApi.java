package com.mediatech.emprunt.adapter.in.web;
import com.mediatech.emprunt.domaine.ErreursMetier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice(assignableTypes = EmpruntRestController.class)
public class ErreursApi {
 @ExceptionHandler(ErreursMetier.AdherentSuspendu.class) public ResponseEntity<String> suspendu(RuntimeException e){return ResponseEntity.status(403).body("Adhérent suspendu");}
 @ExceptionHandler(ErreursMetier.QuotaAtteint.class) public ResponseEntity<String> quota(RuntimeException e){return ResponseEntity.status(409).body("Maximum 3 emprunts");}
 @ExceptionHandler(ErreursMetier.ExemplaireIndisponible.class) public ResponseEntity<String> indisponible(RuntimeException e){return ResponseEntity.status(409).body("Exemplaire indisponible");}
 @ExceptionHandler({ErreursMetier.AdherentInconnu.class,ErreursMetier.ExemplaireInconnu.class,ErreursMetier.ExemplaireNonEmprunte.class})
 public ResponseEntity<String> inconnu(RuntimeException e){return ResponseEntity.internalServerError().body(e.getMessage());}
}
