package nl.haaientanden.eindopdrachtbackendtandartspraktijk.services;

import nl.haaientanden.eindopdrachtbackendtandartspraktijk.models.User;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.repositories.UserRepository;
import nl.haaientanden.eindopdrachtbackendtandartspraktijk.services.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepos;

    public MyUserDetailsService(UserRepository repos) {
        this.userRepos = repos;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> ou = userRepos.findById(username);
        if (ou.isPresent()) {
            User user = ou.get();
            return new MyUserDetails(user);
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
