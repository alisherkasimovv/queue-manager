package uz.queue.db.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.queue.db.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "queue_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QueueItem extends BaseEntity {

    @Column(name = "chosen_doctor")
    private UUID doctor;

    @Column(name = "full_creation_date", updatable = false)
    private Calendar creationDate;

    @Column(name = "last_order_timestamp")
    private Calendar lastOrderTimestamp;

    @Column(name = "current_order_timestamp")
    private Calendar currentOrderTimestamp;

    @Column(name = "counter")
    private int counter = 0;

    @Column(name = "is_day_passed")
    private boolean dayPassed = false;

}
