package dev.laughingcat27.util.observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private List<IObserver> observers;

    public Observable() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(IObserver observer) {
        this.observers.add(observer);
    }

    public void removeListener(IObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers(Object arg) {
        this.observers.forEach(iObserver -> iObserver.update(this, arg));
    }

    public void notifyObservers() {
        this.notifyObservers(null);
    }
}
