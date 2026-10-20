package com.mediatech.emprunt.adapter.in.web;
import com.mediatech.emprunt.application.port.in.*;
import com.mediatech.emprunt.domaine.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class EmpruntRestController {
 private final EmprunterDocument emprunter; private final RendreDocument rendre;
 public EmpruntRestController(EmprunterDocument emprunter,RendreDocument rendre){this.emprunter=emprunter;this.rendre=rendre;}
 @PostMapping("/emprunts")
 public ResponseEntity<?> emprunter(@RequestParam Long adherentId,@RequestParam Long exemplaireId){
  var date=emprunter.executer(new EmprunterDocument.Commande(AdherentId.de(adherentId.toString()),ExemplaireId.de(exemplaireId.toString())));
  return ResponseEntity.ok(Map.of("retourPrevu",date.toString()));
 }
 @PostMapping("/retours")
 public ResponseEntity<Void> rendre(@RequestParam Long exemplaireId){
  rendre.executer(new RendreDocument.Commande(ExemplaireId.de(exemplaireId.toString())));
  return ResponseEntity.ok().build();
 }
}
