<<<<<<< HEAD
package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;

import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Userdto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController extends AbstractController{
    private final ModelMapper modelMapper;
    private UserRepository userRepository;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/users")
    public List<Userdto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, Userdto.class)).collect(Collectors.toList());
    }
}

=======
package Bug.Tracking.System.Application.Bug.Tracking.Application.controller;

import Bug.Tracking.System.Application.Bug.Tracking.Application.dto.Userdto;
import Bug.Tracking.System.Application.Bug.Tracking.Application.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import Bug.Tracking.System.Application.Bug.Tracking.Application.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final ModelMapper modelMapper;
    private UserRepository userRepository;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/users")
    public List<Userdto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, Userdto.class)).collect(Collectors.toList());
    }
}

>>>>>>> 7597e7e0eba66a899deb947e73815869450259fd
