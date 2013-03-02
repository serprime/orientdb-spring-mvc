package example.service;

import example.domain.Entity;

import java.util.List;

public interface SomeService {

    Entity saveEntity(String value);

    List<Entity> getEntities();

}
