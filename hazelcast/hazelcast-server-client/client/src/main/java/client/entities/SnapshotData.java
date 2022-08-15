package client.entities;


import lombok.Data;

import java.util.List;

@Data
public class SnapshotData {
    private List<Note> quotes;
}
