package server.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NestedItemEntity   {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private double price;

    @ManyToOne
    @JoinColumn(name="collectionDataEntity_id")
    private CollectionDataEntity collectionDataEntity;

}
