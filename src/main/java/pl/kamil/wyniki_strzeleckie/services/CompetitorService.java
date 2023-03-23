package pl.kamil.wyniki_strzeleckie.services;

import org.springframework.stereotype.Service;
import pl.kamil.wyniki_strzeleckie.exceptions.CompetitorNotFoundException;
import pl.kamil.wyniki_strzeleckie.exceptions.UserAlreadyExistException;
import pl.kamil.wyniki_strzeleckie.model.*;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompetitorService {
    private final CompetitorsRepository repository;
    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;

    public CompetitorService(CompetitorsRepository repository,
                             RoleRepository roleRepository) {
        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<Competitor> getCompetitors() {
        return repository.findAll();
    }

    public Competitor saveCompetitor(Competitor competitor) {
        return repository.save(competitor);
    }

    private boolean emailExist(String email) {
        return repository.existsByEmail(email);
    }

    @Transactional
    public Competitor registerNewUser(CompetitorDTO competitorDTO) {
        if (emailExist(competitorDTO.getEmail())) {
            throw new UserAlreadyExistException(competitorDTO.getEmail());
        }
        Competitor user = new Competitor();
        user.setName(competitorDTO.getFirstName() + " " + competitorDTO.getLastName());
        user.setPassword(competitorDTO.getPassword());
        user.setEmail(competitorDTO.getEmail());
        user.setLicenseNumber(competitorDTO.getLicenseNumber());
        user.setClubName(competitorDTO.getClubName());
        user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));

        return saveCompetitor(user);
    }

    public void deleteCompetitor(Long id) {
        if (!repository.existsById(id))
            throw new CompetitorNotFoundException(id);
//        startRepository.findByCompetitor_Id(id).forEach(start -> startRepository.deleteById(start.getId()));
        repository.updateActiveById(false, id);
    }

    public int updateCompetitor(
            String name,
            String licenseNumber,
            String clubName,
            Long id) {
        return repository.updateNameAndLicenseNumberAndClubNameById(name, licenseNumber, clubName, id);
    }

    @PostConstruct
    @Transactional
    private void init() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        Role roleManager = new Role("ROLE_MANAGER");
        List<Role> savedRoles = roleRepository.saveAll(Set.of(roleUser, roleManager, roleAdmin));
        HashSet<Role> roles = new HashSet<>();
        Competitor competitor = new Competitor();
        competitor.setEmail("user");
        competitor.setPassword("{bcrypt}$2y$10$8rVq.nIuVpdNdKN7ZdJM.O8okWJnIPVKSTudPfvCM5NWyzxHLQF4O");
        competitor.setName("Kamil B");
        competitor.setClubName("KS AGAT");
        competitor.setLicenseNumber("L-123");
        roles.add(roleRepository.findByName("ROLE_USER"));
        competitor.setRoles(roles);
        repository.save(competitor);

        Competitor competitor2 = new Competitor();
        HashSet<Role> roles2 = new HashSet<>(savedRoles);
        competitor2.setEmail("admin");
        competitor2.setPassword(("{bcrypt}$2y$10$8rVq.nIuVpdNdKN7ZdJM.O8okWJnIPVKSTudPfvCM5NWyzxHLQF4O"));
        competitor2.setRoles(roles2);
        repository.save(competitor2);
        System.out.println(repository.findAll());
    }
}
