package pl.kamil.wyniki_strzeleckie.security;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import pl.kamil.wyniki_strzeleckie.model.Competitor;
//import pl.kamil.wyniki_strzeleckie.model.CompetitorsRepository;
//import pl.kamil.wyniki_strzeleckie.model.Role;
//
//import java.util.HashSet;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    CompetitorsRepository userRepo;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Competitor user = userRepo.findByEmail(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found!");
//        }
//        return new User(
//                user.getEmail(),
//                user.getPassword(),
//                user.getRoles()
//        );
//    }
//
//}
