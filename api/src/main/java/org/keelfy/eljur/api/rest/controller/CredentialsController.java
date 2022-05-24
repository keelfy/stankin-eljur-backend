package org.keelfy.eljur.api.rest.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keelfy.eljur.api.model.CredentialsDto;
import org.keelfy.eljur.api.model.request.ChangeCredentialsRequest;
import org.keelfy.eljur.api.model.request.ChangeForgottenPasswordRequest;
import org.keelfy.eljur.api.model.request.ChangePasswordRequest;
import org.keelfy.eljur.api.model.request.CreateCredentialsRequest;
import org.keelfy.eljur.api.model.request.ForgotPasswordRequest;
import org.keelfy.eljur.api.service.CredentialsService;
import org.keelfy.eljur.api.service.PasswordService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CredentialsController implements RootController {

    private static final String CONTROLLER_MAPPING = API_V1_MAPPING + "/credentials";

    private static final String UPDATE_MAPPING = CONTROLLER_MAPPING + "/update";

    private static final String INFO_MAPPING = CONTROLLER_MAPPING + "/info";

    private static final String PASSWORD_MAPPING = CONTROLLER_MAPPING + "/password";

    private static final String CHANGE_PASSWORD_MAPPING = PASSWORD_MAPPING + "/change";

    public static final String FORGOT_PASSWORD_MAPPING = PASSWORD_MAPPING + "/forgot";

    public static final String CHANGE_FORGOTTEN_PASSWORD_MAPPING = FORGOT_PASSWORD_MAPPING + "/change";

    public static final String CREATE_MAPPING = CONTROLLER_MAPPING + "/create";

    private final CredentialsService credentialsService;

    private final PasswordService passwordService;

    @GetMapping(value = INFO_MAPPING, produces = MediaType.APPLICATION_JSON_VALUE)
    public CredentialsDto info() {
        return credentialsService.getCredentialsDto();
    }

    @PostMapping(value = UPDATE_MAPPING, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void changeCredentials(@Validated @RequestBody ChangeCredentialsRequest request) throws NumberParseException {
        credentialsService.changeCredentials(request);
    }

    @PostMapping(value = CHANGE_PASSWORD_MAPPING, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void changePassword(@Validated @RequestBody ChangePasswordRequest request) {
        passwordService.changePassword(request);
    }

    @PostMapping(value = FORGOT_PASSWORD_MAPPING, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void forgotPassword(@Validated @RequestBody ForgotPasswordRequest request) {
        passwordService.resetPassword(request);
    }

    @PostMapping(value = CHANGE_FORGOTTEN_PASSWORD_MAPPING, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void changeForgottenPassword(@Validated @RequestBody ChangeForgottenPasswordRequest request) {
        passwordService.changeForgottenPassword(request);
    }

    @PostMapping(value = CREATE_MAPPING, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createCredentials(@Validated @RequestBody CreateCredentialsRequest request) {
        credentialsService.createCredentials(request);
    }

}
