package org.keelfy.eljur.service;

import org.keelfy.eljur.data.Credentials;
import org.keelfy.eljur.model.UserCredentials;
import org.springframework.lang.NonNull;

import java.math.BigInteger;

/**
 * @author Egor Kuzmin
 */
public interface CredentialsService {

    boolean isStudent(@NonNull UserCredentials credentials);

    boolean isTeacher(@NonNull UserCredentials credentials);

    @NonNull
    Credentials getById(@NonNull BigInteger id);

}
