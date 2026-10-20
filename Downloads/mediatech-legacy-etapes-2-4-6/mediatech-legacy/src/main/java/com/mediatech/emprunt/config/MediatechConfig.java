package com.mediatech.emprunt.config;
import com.mediatech.emprunt.application.*;
import com.mediatech.emprunt.application.port.in.*;
import com.mediatech.emprunt.application.port.out.*;
import org.springframework.context.annotation.*;
import org.springframework.transaction.support.TransactionTemplate;
import java.time.Clock;
@Configuration
public class MediatechConfig {
 @Bean Clock horloge(){return Clock.systemDefaultZone();}
 @Bean EmprunterDocument emprunterDocument(Adherents a, Exemplaires e, Notificateur n, Clock h, TransactionTemplate tx){
  var service=new EmprunterDocumentService(a,e,n,h);
  return commande->tx.execute(status->service.executer(commande));
 }
 @Bean RendreDocument rendreDocument(Adherents a, Exemplaires e, Reservations r, Notificateur n, Clock h, TransactionTemplate tx){
  var service=new RendreDocumentService(a,e,r,n,h);
  return commande->{tx.executeWithoutResult(status->service.executer(commande));};
 }
}
