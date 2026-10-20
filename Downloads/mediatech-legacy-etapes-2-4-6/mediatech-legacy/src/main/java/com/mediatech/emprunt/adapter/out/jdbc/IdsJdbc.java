package com.mediatech.emprunt.adapter.out.jdbc;
import com.mediatech.emprunt.domaine.*;
final class IdsJdbc {
 private IdsJdbc(){}
 static long longId(AdherentId id){return Long.parseLong(id.valeur());}
 static long longId(ExemplaireId id){return Long.parseLong(id.valeur());}
 static AdherentId adherent(long id){return AdherentId.de(Long.toString(id));}
 static ExemplaireId exemplaire(long id){return ExemplaireId.de(Long.toString(id));}
}
