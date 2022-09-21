package server;


import cache.CollectionDataModel;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.MapLoader;
import com.hazelcast.map.MapStore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.entities.CollectionDataEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppMapLoader implements MapLoader<String, CollectionDataModel>, MapStore<String, CollectionDataModel> {

    private SnapshotDataRepository repository;

    private ModelMapper modelMapper;


    @Override
    public synchronized CollectionDataModel load(String key) {
        log.info("Loading data from repository id=[{}]", key);
        CollectionDataEntity collectionDataEntity = repository.findById(key).orElse(null);
        return collectionDataEntity == null ? null : modelMapper.map(collectionDataEntity, CollectionDataModel.class);
    }

    @Override
    public  synchronized Map<String, CollectionDataModel> loadAll(Collection<String> keys) {
        log.info("Loading data from repository ids=[{}]", keys);
        Map<String, CollectionDataEntity> collect = repository.findAllById(keys)
                .stream()
                .collect(Collectors.toMap(CollectionDataEntity::getId, Function.identity()));
        Map<String, CollectionDataModel> collect2 = new HashMap<>();
        collect.forEach((key, value) -> collect2.put(key, modelMapper.map(value, CollectionDataModel.class)));
        return collect2;
    }

    @Override
    public synchronized Iterable<String> loadAllKeys() {
        return repository.findAll().stream().map(CollectionDataEntity::getId).collect(Collectors.toList());
    }

    @Override
    public synchronized void store(String key, CollectionDataModel value) {
        log.info("Saving data to repository id=[{}]", key);
        repository.save(modelMapper.map(value, CollectionDataEntity.class));
    }

    @Override
    public synchronized void storeAll(Map<String, CollectionDataModel> map) {
        log.info("Saving data to repository data=[{}]", map);
        repository.saveAll(map.values().stream().map(item -> modelMapper.map(item, CollectionDataEntity.class))
                .collect(Collectors.toList()));
    }

    @Override
    public synchronized void delete(String key) {
        log.info("Deleting data from repository id=[{}]", key);
        repository.deleteById(key);
    }

    @Override
    public synchronized void deleteAll(Collection<String> keys) {
        log.info("Deleting data from repository data=[{}]", keys);
        repository.deleteAllById(keys);
    }
}
