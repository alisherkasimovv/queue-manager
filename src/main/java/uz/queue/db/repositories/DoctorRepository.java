package uz.queue.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.queue.db.entities.Doctor;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    List<Doctor> findAllByDeletedFalse();

}
