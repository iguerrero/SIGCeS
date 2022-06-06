package edu.grupo9.sigces.dao;

import java.util.List;

public interface CRUD<T> {
    void cargarNuevo();
    List<T> listarTodos();
    void modificar(T t);
    void borrar(T t);
}
