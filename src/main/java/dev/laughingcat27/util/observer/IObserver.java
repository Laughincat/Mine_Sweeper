package dev.laughingcat27.util.observer;

import javafx.beans.Observable;

public interface IObserver {

    void update(Subject subject, Object arg);
}
