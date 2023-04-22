package bibliotheque.mvp.model;

import bibliotheque.metier.Auteur;

import java.util.List;

public interface DAO<T> {
    T add(T object);

    boolean remove(T object);
    T update(T object);

    T read(T object);

    List<T> getAll();

}
