package vn.kma.hrmactvn.authentication;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.kma.hrmactvn.entity.Staff;

public class UserDetailsImpl implements UserDetails {

  @Getter
  private final Long id;

  private final String username;

  @JsonIgnore
  private final String password;

  private final Collection<? extends GrantedAuthority> authorities;

  private final Boolean active;

  public UserDetailsImpl(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities, Boolean active) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.authorities = authorities;
    this.active = active;
  }

  public static UserDetailsImpl build(Staff staff){
    String level = staff.getLevel();
    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(level));
    return new UserDetailsImpl(
      staff.getId(),
      staff.getUsername(),
      staff.getPassword(),
      authorities,
        staff.getActive()
    );
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return active;
  }

  @Override
  public boolean isAccountNonLocked() {
    return active;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return active;
  }

  @Override
  public boolean isEnabled() {
    return this.active;
  }
}
