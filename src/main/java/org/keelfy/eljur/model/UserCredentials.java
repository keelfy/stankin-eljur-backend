package org.keelfy.eljur.model;

import org.keelfy.eljur.data.StudentSemester;
import org.keelfy.eljur.util.SecurityUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author Egor Kuzmin
 */
public interface UserCredentials extends UserDetails {

    String getRoles();

    Boolean getLocked();

    Boolean getEnabled();

    List<StudentSemester> getStudentSemesters();

    default Collection<String> getLiteralAuthorities() {
        return SecurityUtil.splitAuthoritiesToString(getRoles());
    }

    @Override
    default Collection<? extends GrantedAuthority> getAuthorities() {
        return SecurityUtil.splitAuthorities(getRoles());
    }

    @Override
    default boolean isAccountNonExpired() {
        return true;
    }

    @Override
    default boolean isAccountNonLocked() {
        return getLocked();
    }

    @Override
    default boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    default boolean isEnabled() {
        return getEnabled();
    }

}
