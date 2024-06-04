package repository;

import model.BaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseRepository<Entity extends BaseModel> {
    private List<Entity> data;

    public BaseRepository() {
        this.data = new ArrayList<>();
    }

    public <S extends Entity> S save(S e) {
        Long nextId = (long) data.size();
        e.setId(nextId);
        data.add(e);
        return e;
    }

    public Optional<Entity> findById(Long id) {
        return data.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public List<Entity> findAll() {
        return this.data;
    }

    public void removeById(Long id) {
        data.removeIf(e -> e.getId().equals(id));
    }

    public int count() {
        return data.size();
    }
}
