package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject class provides an interface for attaching (subscribing,
 * registering) and detaching (unsubscribing, unregistering) Observer
 * objects
 * 
 * Responsibility of Subject is to maintain the list of observers
 * and to notify them of state changes by calling the update() method
 * 
 * When Subject changes its state, all registered Observers are notified
 * so that they can update themselves
 */

public class Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    protected Subject() {

    }

    /*
     * Adds / attaches the observer to a Subject class
     * 
     * @param observer
     */
    public void attach(Observer observer) {
        observers.add(observer);
    }

    /*
     * Removes / detaches the observer to a Subject class
     * 
     * @param observer
     */
    public void dettach(Observer observer) {
        observers.remove(observer);
    }

    /*
     * Notifies the observer of a change in Subject class
     */
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}