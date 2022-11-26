package com.example.restaurant.Service;

import com.example.restaurant.dto.WaiterDto;
import com.example.restaurant.models.Role;
import com.example.restaurant.models.User;
import com.example.restaurant.models.Waiter;
import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WaiterServiceImpl implements WaiterService{

    private final WaiterRepository waiterRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public WaiterServiceImpl(WaiterRepository repository, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.waiterRepository = repository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void save(WaiterDto waiterDto) {
        waiterRepository.save(new Waiter(waiterDto.getName(), waiterDto.getPhone()));
        User user = new User(
                waiterDto.getName(),
                waiterDto.getPhone(),
                passwordEncoder.encode(waiterDto.getPassword()),
                Arrays.asList(new Role("ROLE_WAITER"))
        );
        userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        Waiter waiter = waiterRepository.findWaiterById(id);
        waiterRepository.deleteById(id);
        User user = userRepository.findUserByPhone(waiter.getPhone());
        userRepository.deleteById(user.getId());
    }

    @Override
    public List<Waiter> getAll() {
        return waiterRepository.findAll();
    }

    @Override
    public Waiter getWaiterById(long id) {
        return waiterRepository.findWaiterById(id);
    }

}
