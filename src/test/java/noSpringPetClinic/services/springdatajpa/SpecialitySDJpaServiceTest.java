package noSpringPetClinic.services.springdatajpa;

import noSpringPetClinic.model.Speciality;
import noSpringPetClinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks // tells Mockito to create instance of this and inject it
    SpecialitySDJpaService service;

    @Test
    void deleteByObjectTest() {
        // GIVEN
        Speciality speciality = new Speciality();
        // WHEN
        service.delete(speciality);
        // THEN
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

    @Test
    void findByTest() {
        // GIVEN
        Speciality speciality = new Speciality();
        // WHEN
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));
        Speciality foundSpecialty = service.findById(1L);
        // THEN
        assertThat(foundSpecialty).isNotNull();
        then(specialtyRepository).should().findById(anyLong());
        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteByIdTest() {
        // given - none
        // when
        service.deleteById(1L);
        service.deleteById(1L);
        // then
        then(specialtyRepository).should(times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdTestAtLeast() {
        //given - none
        //when
        service.deleteById(1L);
        service.deleteById(1L);
        //then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
        }

    @Test
    void deleteByIdTestAtMost() {
        //when
        service.deleteById(1L);
        service.deleteById(1L);
        //then
        then(specialtyRepository).should(atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdTestNever() {
        //when
        service.deleteById(1L);
        service.deleteById(1L);
        //then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
        then(specialtyRepository).should(never()).deleteById(5L);
    }

    @Test
    void deleteTest() {
        //when
        service.delete(new Speciality());
        //then
        then(specialtyRepository).should().delete(any());
    }
}