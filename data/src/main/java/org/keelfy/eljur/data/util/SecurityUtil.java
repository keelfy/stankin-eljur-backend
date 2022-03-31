package org.keelfy.eljur.data.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Egor Kuzmin
 */
@UtilityClass
public class SecurityUtil {

    private static final String ROLES_SPLITTER = ",";

    public static Collection<String> splitAuthoritiesToString(String roles) {
        return Optional.ofNullable(roles)
                .map(s -> s.split(ROLES_SPLITTER))
                .map(Arrays::asList)
                .orElseGet(ArrayList::new);
    }

    public static Collection<? extends GrantedAuthority> splitAuthorities(String roles) {
        return Optional.ofNullable(roles)
                .map(s -> s.split(ROLES_SPLITTER))
                .map(strings -> Arrays.stream(strings).map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }

    public static String joinLiteralAuthorities(Collection<String> authorities) {
        return String.join(ROLES_SPLITTER, authorities);
    }

    public static String joinAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(ROLES_SPLITTER));
    }

}
