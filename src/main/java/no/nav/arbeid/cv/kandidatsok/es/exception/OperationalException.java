package no.nav.arbeid.cv.kandidatsok.es.exception;

/**
 * OperationalException representerer infrastrukturfeil. Handlingen som forårsaket dette kan
 * rekjøres og vil kanskje lykkes etter hvert.
 */
public class OperationalException extends RuntimeException {

    public OperationalException(String msg) {
        super(msg);
    }

    public OperationalException(Throwable cause) {
        super(cause);
    }

    public OperationalException(String message, Throwable source) {
        super(message, source);
    }
}
