package com.talex.talex.security.userDetails;


import com.talex.talex.entity.User;
import com.talex.talex.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {


    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

         User user = userRepo
                .findByUsernameOrEmail(login, login)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new AppUserDetails(user);
    }
}
