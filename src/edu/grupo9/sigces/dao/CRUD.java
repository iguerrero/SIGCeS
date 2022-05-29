package edu.grupo9.sigces.dao;

import java.util.List;

public interface CRUD<T> {
    void crear(T t);
    List<T> leer();
    void modificar(T t);
    void borrar(T t);
}
