package vn.kma.hrmactvn.authentication;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.kma.hrmactvn.entity.Staff;
import vn.kma.hrmactvn.repository.StaffRepository;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StaffRepository staffRepository;
    @Override
    @Transactional
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Staff> userOpt = staffRepository.findByUsernameAndActive(username,true);
        if(userOpt.isEmpty()){
            return null;
        }
        Staff user = userOpt.get();
        return UserDetailsImpl.build(user);

    }
}
