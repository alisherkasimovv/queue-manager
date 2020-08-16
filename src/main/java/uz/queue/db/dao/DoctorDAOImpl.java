package uz.queue.db.dao;

import org.springframework.stereotype.Service;
import uz.queue.db.dao.interfaces.DoctorDAO;
import uz.queue.db.dto.DoctorDTO;
import uz.queue.db.dto.DoctorListDTO;
import uz.queue.db.entities.Doctor;
import uz.queue.db.factories.DoctorDTOFactory;
import uz.queue.db.factories.DoctorFactory;
import uz.queue.db.repositories.DoctorRepository;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorDAOImpl implements DoctorDAO {

    private final DoctorRepository repository;

    public DoctorDAOImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public DoctorListDTO getAll() {
        List<Doctor> list = repository.findAllByDeletedFalse();
        DoctorListDTO doctorListDTO = new DoctorListDTO();

        for (Doctor doc : list) {
            doctorListDTO.addDoctor(DoctorDTOFactory.create(doc));
        }

        return doctorListDTO;
    }

    @Override
    public DoctorDTO get(UUID id) {
        Doctor doc = repository.getOne(id);
        return new DoctorDTO(doc.getId(), doc.getSpecialization(), doc.getDescription());
    }

    @Override
    public DoctorDTO save(DoctorDTO doctor) {
        Doctor doc = repository.save(DoctorFactory.create(doctor));
        return DoctorDTOFactory.create(doc);
    }

    @Override
    public DoctorDTO delete(UUID id) {
        Doctor doc = repository.getOne(id);
        doc.setDeleted(true);
        doc = repository.save(doc);
        return DoctorDTOFactory.create(doc);
    }
}
