package noSpringPetClinic.services.springdatajpa;

import noSpringPetClinic.model.Speciality;
import noSpringPetClinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks // tells Mockito to create instance of this and inject it
    SpecialitySDJpaService service;

    @Test
    void deleteByIdTest() {
        service.deleteById(1L);
    }

    @Test
    void deleteTest() {
        service.delete(new Speciality());
    }
}