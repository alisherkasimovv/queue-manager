package uz.queue.db.factories;

import uz.queue.db.dto.DoctorDTO;
import uz.queue.db.entities.Doctor;

/**
 * DoctorDTOFactory final class makes DoctorDTO object from Doctor
 */
public final class DoctorDTOFactory {

    public DoctorDTOFactory() {
    }

    public static DoctorDTO create(Doctor doctor) {
        return new DoctorDTO(
                doctor.getId(),
                doctor.getSpecialization(),
                doctor.getDescription()
        );
    }

}
