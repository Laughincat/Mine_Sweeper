package dev.laughingcat27.util.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<IObserver> observers;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    protected void notifyObservers(Object arg) {
        this.observers.forEach(iObserver -> iObserver.update(this, arg));
    }

    protected void notifyObservers() {
        this.notifyObservers(null);
    }
}
