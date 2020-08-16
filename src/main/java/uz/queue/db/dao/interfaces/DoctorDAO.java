package uz.queue.db.dao.interfaces;

import uz.queue.db.dto.DoctorDTO;
import uz.queue.db.dto.DoctorListDTO;
import uz.queue.db.entities.Doctor;

import java.util.UUID;

public interface DoctorDAO {

    DoctorListDTO getAll();
    DoctorDTO get(UUID id);
    DoctorDTO save(DoctorDTO doctor);
    DoctorDTO delete(UUID id);

}
