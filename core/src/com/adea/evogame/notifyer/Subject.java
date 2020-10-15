package com.adea.evogame.notifyer;

public interface Subject {

    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();

}
