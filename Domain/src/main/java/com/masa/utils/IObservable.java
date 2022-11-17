package com.masa.utils;

/**
 *
 * @author Luis Marin
 */
public interface IObservable {
    
    public void addObserver(IObserver observer);
    
    public void removeObserver(IObserver observer);
    
    public void notify(Object o, String type);
}
