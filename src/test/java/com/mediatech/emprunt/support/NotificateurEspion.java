package com.mediatech.emprunt.support;

import com.mediatech.emprunt.application.port.out.Notificateur;
import com.mediatech.emprunt.domaine.Adherent;
import com.mediatech.emprunt.domaine.AdherentId;
import com.mediatech.emprunt.domaine.Emprunt;

import java.util.ArrayList;
import java.util.List;

/** Espion de test : n'envoie rien, note chaque notification demandée. */
public class NotificateurEspion implements Notificateur {

    /** Une notification demandée : à qui, et pour quel emprunt. */
    public record Notification(AdherentId adherent, Emprunt emprunt) {
    }

    private final List<Notification> notifications = new ArrayList<>();

    @Override
    public void confirmerEmprunt(Adherent adherent, Emprunt emprunt) {
        notifications.add(new Notification(adherent.id(), emprunt));
    }

    /** Ce qu'on lui a demandé d'envoyer, dans l'ordre. */
    public List<Notification> notificationsEnvoyees() {
        return List.copyOf(notifications);
    }
}
