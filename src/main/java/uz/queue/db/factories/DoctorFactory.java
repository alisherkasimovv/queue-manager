package uz.queue.db.factories;

import uz.queue.db.dto.DoctorDTO;
import uz.queue.db.entities.Doctor;

/**
 * DoctorFactory final class makes Doctor object from DoctorDTO
 */
public final class DoctorFactory {

    public DoctorFactory() {
    }

    public static Doctor create(DoctorDTO dto) {
        return new Doctor(
                dto.getSpecialization(),
                dto.getDescription()
        );
    }
}
