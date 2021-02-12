package br.com.luancarlos.examplejwt.security;

import br.com.luancarlos.examplejwt.entity.User;
import br.com.luancarlos.examplejwt.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("userDetailsService")
public class DomainUseDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DomainUseDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            User user = Optional.ofNullable(this.userRepository.findByUsername(username))
                    .orElseThrow(() -> new UsernameNotFoundException("User empty"));

            List<GrantedAuthority> authoritiesAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
            List<GrantedAuthority> authoritiesUser = AuthorityUtils.createAuthorityList("ROLE_USER");

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.isAdmin() ? authoritiesAdmin : authoritiesUser);

        }
}
