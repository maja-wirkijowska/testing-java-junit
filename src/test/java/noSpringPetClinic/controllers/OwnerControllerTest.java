package noSpringPetClinic.controllers;

import noSpringPetClinic.fauxspring.BindingResult;
import noSpringPetClinic.fauxspring.Model;
import noSpringPetClinic.model.Owner;
import noSpringPetClinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock(lenient = true)
    static OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    @Mock
    BindingResult bindingResult;

    @Captor
    static ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void beforeEach() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture()))
                .willAnswer(invocation -> {
                    List<Owner> owners = new ArrayList<>();
                    String name = invocation.getArgument(0);
                    if (name.equals("%Buck%")) {
                        owners.add(new Owner(1L, "Joe", "Buck"));
                        return owners;
                    } else if (name.equals("%NotFound%")) {
                        return owners;
                    } else if (name.equals("%FindMe%")) {
                        owners.add(new Owner(1L, "Joe", "Buck"));
                        owners.add(new Owner(2L, "Joe2", "Buck2"));
                        return owners;
                    }
                    throw new RuntimeException("Invalid Argument");
                });
    }

    @Test
    void processFindFormWildCardStringAnnotation() {
        //given
        Owner owner = new Owner(1L, "Joe", "Buck");
        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);
        //then
        assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processFindFormWildCardStringNotFound() {
        //given
        Owner owner = new Owner(1L, "Joe", "NotFound");
        //when
        String viewName = controller.processFindForm(owner, bindingResult, null);
        //then
        assertThat("%NotFound%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualToIgnoringCase(viewName);
    }

    @Test
    void processFindFormWildCardStringFound() {
        //given
        Owner owner = new Owner(1L, "Joe", "FindMe");
        //when
        String viewName = controller.processFindForm(owner, bindingResult, Mockito.mock(Model.class));
        //then
        assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualToIgnoringCase(viewName);
    }

    @Test
    void testProcessCreationFormHasErrors() {
        //given
        Owner owner = new Owner(1L, "John", "Doe");
        given(bindingResult.hasErrors()).willReturn(true);
        //when
        String viewName = controller.processCreationForm(owner, bindingResult);
        //then
        assertThat(viewName).isEqualToIgnoringCase(OWNERS_CREATE_OR_UPDATE_OWNER_FORM);
    }

    @Test
    void testProcessCreationFormHasNoErrors() {
        //given
        Owner owner = new Owner(5L, "John", "Doe");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);
        //when
        String viewName = controller.processCreationForm(owner, bindingResult);
        //then
        assertThat(viewName).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
    }
}