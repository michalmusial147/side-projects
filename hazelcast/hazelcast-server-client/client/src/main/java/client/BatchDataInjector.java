package client;


import client.notes.Note;
import client.notes.SnapshotData;
import client.users.AppUser;
import client.users.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
public class BatchDataInjector implements CommandLineRunner {


    private final AppUserRepository appUserRepository;

    @Autowired
    public BatchDataInjector(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        appUserRepository.save(AppUser.builder().name("martin").snapshotData(

                Arrays.asList((SnapshotData.builder().notes(List.of(Note.builder().price(3f).id("btc").build(), Note.builder().price(3.1f).id("btc").build(), Note.builder().price(3.2f).id("btc").build()))).build(), SnapshotData.builder().notes(List.of(Note.builder().price(3f).id("etc").build(), Note.builder().price(3.1f).id("etc").build(), Note.builder().price(3.2f).id("etc").build())).build())).build());
    }
}
