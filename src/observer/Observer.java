package observer;

/**
 * Observer class (pattern) defines one-to-many relationship
 * between the Subject (observable) and the Observer class
 * 
 * Responsibility of Observer is to register (and unregister)
 * themselves on a subject to get notified of changes
 * 
 * Once notified, the observer reacts to subject's change by
 * executing their update() method - execution is trigged by
 * the subject
 * 
 * Observers can be added and removed independently at run-time
 */

public interface Observer {
    public void update();
}