package server;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;
import server.entities.CollectionDataEntity;
import server.entities.NestedItemEntity;

import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class BatchDataInjector implements CommandLineRunner {


    private final SnapshotDataRepository snapshotDataRepository;



    @Override
    public void run(String... args) throws Exception {
        snapshotDataRepository.save(
                CollectionDataEntity.builder()
                        .nestedItems(
                                List.of(
                                        NestedItemEntity.builder().price(3f).name("btc").build(),
                                        NestedItemEntity.builder().price(3.1f).name("btc").build(),
                                        NestedItemEntity.builder().price(3.2f).name("btc").build()))
                        .build());
        log.info("DB init state=[{}]", snapshotDataRepository.findAll());
    }

}
