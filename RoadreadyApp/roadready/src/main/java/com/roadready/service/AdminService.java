package com.roadready.service;

import com.roadready.Mapper.AdminMapper;
import com.roadready.dto.*;
import com.roadready.enums.Role;
import com.roadready.model.*;
import com.roadready.repository.AdminRepository;
import com.roadready.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;
    private final CustomerService customerService;
    private final BookingService bookingService;
    private final CarService carService;

    public void addAdmin(AdminDetailsReqDto dto) {

        //step 1: get the username and password
        String password = dto.password();

        // step2 : encode the password
        String encodedPassword = passwordEncoder.encode(password);

        // step 3: create user object, set the role - CUSTOMER
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(encodedPassword);
        user.setRole(Role.ADMIN);

        // step 4: save the user
        userRepository.save(user);

        // step 5: create the customer and add the details
        Admin admin = new Admin();
        admin.setFirstname(dto.firstName());
        admin.setLastname(dto.lastName());
        admin.setEmail(dto.email());
        admin.setPhoneNumber(dto.phoneNumber());
        admin.setJobTitle(dto.jobTitle());
        admin.setUser(user);

        // step 6: save the customer
        adminRepository.save(admin);


    }

    public AdminDetailsRespDto getByUsername(String username) {
        return adminMapper.mapAdminDetailsToDto(findByUsername(username));

    }


    public Admin findByUsername(String username) {

        return adminRepository.findByUserUsername(username);

    }

    public CombinedStatsRespDto combinedStats() {

        List<Customer> customers = customerService.findAll();

        List<Booking> bookings = bookingService.findAll();
        List<Car> cars = carService.findAll();

        List<String> label = List.of("Total Customers", "Total Bookings", "Total Cars");
        List<Integer> count = List.of(customers.size(), bookings.size(), cars.size());

        return new CombinedStatsRespDto(
                label, count

        );

    }

    public StatsDto carStats() {

        List<StatDto> list = carService.getCarStats();
        List<String> name = list.stream().map(StatDto::name)
                .toList();
        List<Long> count = list.stream().map(StatDto::total)
                .toList();

        return new StatsDto(
                name,
                count
        );


    }

    public StatsDto agentStats() {
        List<StatDto> list = bookingService.getAgentStats();
        List<String> name = list.stream().map(StatDto::name)
                .toList();
        List<Long> count = list.stream().map(StatDto::total)
                .toList();

        return new StatsDto(
                name,
                count
        );

    }

    public void updateAdmin(String name, @Valid UpdateReqDto dto) {
        Admin admin = findByUsername(name);
        admin.setFirstname(dto.firstName());
        admin.setLastname(dto.lastName());
        admin.setEmail(dto.email());
        admin.setPhoneNumber(dto.phoneNumber());

        adminRepository.save(admin);
    }
}
