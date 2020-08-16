package uz.queue.db.dto;

import java.util.ArrayList;
import java.util.List;

public class DoctorListDTO {

    private final List<DoctorDTO> doctors;

    public DoctorListDTO() {
        this.doctors = new ArrayList<>();
    }

    public void addDoctor(DoctorDTO doctor) {
        this.doctors.add(doctor);
    }

    public List<DoctorDTO> getDoctors() {
        return doctors;
    }
}
