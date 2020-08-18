package uz.queue.db.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QueueItemDTO {

    private DoctorDTO doctor;
    private int queueCounter;
    private Calendar lastOrderDate;
    private Calendar currentOrderDate;

}
