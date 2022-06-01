package noSpringPetClinic.services.springdatajpa;

import noSpringPetClinic.model.Speciality;
import noSpringPetClinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock(lenient = true)
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

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("boom")).when(specialtyRepository).delete(any());
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));
        verify(specialtyRepository).delete(any());
    }

    @Test
    void testFindByIdThrows() {
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("boom"));
        assertThrows(RuntimeException.class, () -> service.findById(1L));
        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void testDeleteBDD() {
        willThrow(new RuntimeException("boom")).given(specialtyRepository).delete(any());
        assertThrows(RuntimeException.class, () -> specialtyRepository.delete(new Speciality()));
        then(specialtyRepository).should().delete(any());
    }

    @Test
    void testSaveLambda() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);
        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);
        // need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);
        //when
        Speciality returnedSpecialty = service.save(speciality);
        //then
        assertThat(returnedSpecialty.getId()).isEqualTo(1L);
    }

    @Test
    void testSaveLambdaNoMatch() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription("Not a match");
        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);
        // need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME)))).willReturn(savedSpecialty);
        //when
        Speciality returnedSpecialty = service.save(speciality);
        //then
        assertNull(returnedSpecialty);
    }
}