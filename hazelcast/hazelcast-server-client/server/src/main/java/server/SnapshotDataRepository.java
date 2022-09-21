package server;


import client.notes.SnapshotData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnapshotDataRepository extends JpaRepository<SnapshotData, String> {
}
