
package com.mediatech.emprunt.domaine.exceptions;

public abstract class ErreurMetier extends RuntimeException {
    protected ErreurMetier(String message) {
        super(message);
    }
}
