package uz.queue.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.queue.db.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "db_doctors")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor extends BaseEntity {

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "description")
    private String description;

}
