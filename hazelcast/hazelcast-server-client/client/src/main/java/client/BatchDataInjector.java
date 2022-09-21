package client;


import client.notes.Note;
import client.notes.SnapshotData;
import client.notes.SnapshotDataRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
public class BatchDataInjector implements CommandLineRunner {


    private final SnapshotDataRepository snapshotDataRepository;



    @Override
    public void run(String... args) throws Exception {
        snapshotDataRepository.save(
                SnapshotData.builder()
                        .notes(
                                List.of(
                                        Note.builder().price(3f).name("btc").build(),
                                        Note.builder().price(3.1f).name("btc").build(),
                                        Note.builder().price(3.2f).name("btc").build()))
                        .build());
        log.info("DB init state=[{}]", snapshotDataRepository.findAll());
    }

}
