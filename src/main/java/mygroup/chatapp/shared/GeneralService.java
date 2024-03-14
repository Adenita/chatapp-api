package mygroup.chatapp.shared;

public interface GeneralService<T, K> {
    K getAll();
    T get(Long id);
    T save(T transport);
    T update(Long id, T transport);
    void delete(Long id);
}
