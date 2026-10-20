package com.mediatech.emprunt.domaine;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Adherent {
    private final AdherentId id;
    private final Categorie categorie;
    private LocalDate suspenduJusquAu;
    private final List<Emprunt> emprunts;
    public Adherent(AdherentId id, Categorie categorie, LocalDate suspenduJusquAu, List<Emprunt> emprunts) {
        this.id=id; this.categorie=categorie; this.suspenduJusquAu=suspenduJusquAu; this.emprunts=new ArrayList<>(emprunts);
    }
    public AdherentId id(){return id;}
    public Categorie categorie(){return categorie;}
    public LocalDate suspenduJusquAu(){return suspenduJusquAu;}
    public List<Emprunt> emprunts(){return List.copyOf(emprunts);}
    public Emprunt emprunter(Exemplaire exemplaire, LocalDate aujourdHui) {
        if (suspenduJusquAu != null && suspenduJusquAu.isAfter(aujourdHui)) throw new ErreursMetier.AdherentSuspendu();
        if (emprunts.stream().filter(Emprunt::enCours).count() >= 3) throw new ErreursMetier.QuotaAtteint();
        exemplaire.emprunter();
        int jours = categorie == Categorie.ETUDIANT ? 21 : 14;
        Emprunt e = new Emprunt(exemplaire.id(), aujourdHui, aujourdHui.plusDays(jours));
        emprunts.add(e);
        return e;
    }
    public Emprunt rendre(Exemplaire exemplaire, LocalDate aujourdHui) {
        int idx=-1;
        for(int i=0;i<emprunts.size();i++) if(emprunts.get(i).enCours() && emprunts.get(i).exemplaireId().equals(exemplaire.id())) {idx=i;break;}
        if(idx<0) throw new ErreursMetier.ExemplaireNonEmprunte();
        Emprunt old=emprunts.get(idx);
        long retard=java.time.temporal.ChronoUnit.DAYS.between(old.retourPrevu(), aujourdHui);
        if(retard>0) suspenduJusquAu=aujourdHui.plusDays(Math.min(retard,30));
        emprunts.set(idx,old.rendre(aujourdHui));
        return old;
    }
}
