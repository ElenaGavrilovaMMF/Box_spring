package by.iba.box.repository;

import by.iba.box.entity.ConfigType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends CrudRepository<ConfigType,Integer> {

    ConfigType findByNameTypeJSON(String name);
}
