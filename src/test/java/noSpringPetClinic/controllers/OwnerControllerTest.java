package noSpringPetClinic.controllers;

import noSpringPetClinic.fauxspring.BindingResult;
import noSpringPetClinic.model.Owner;
import noSpringPetClinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    @Mock
    BindingResult bindingResult;

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