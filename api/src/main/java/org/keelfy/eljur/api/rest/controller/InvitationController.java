package org.keelfy.eljur.api.rest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.model.request.CompleteInvitationRequest;
import org.keelfy.eljur.api.model.request.CreateInvitationRequest;
import org.keelfy.eljur.api.service.InvitationService;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class InvitationController implements RootController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/invitation";

    private static final String CREATE_MAPPING = CONTROLLER_MAPPING + "/create";

    public static final String ACTIVATE_MAPPING = CONTROLLER_MAPPING + "/activate";

    private final InvitationService invitationService;

    @PreAuthorize("hasAuthority(T(org.keelfy.eljur.api.util.Authority).ADMINISTRATOR)")
    @PostMapping(value = CREATE_MAPPING,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createInvitation(@Validated @RequestBody CreateInvitationRequest request) {
        invitationService.createInvitation(request);
    }

    @PostMapping(value = ACTIVATE_MAPPING,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public void completeInvitation(@Validated @RequestBody CompleteInvitationRequest request) {
        invitationService.completeInvitation(request);
    }

}
