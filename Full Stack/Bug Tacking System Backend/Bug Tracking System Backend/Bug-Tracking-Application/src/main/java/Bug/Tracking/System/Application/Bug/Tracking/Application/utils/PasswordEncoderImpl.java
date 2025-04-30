package Bug.Tracking.System.Application.Bug.Tracking.Application.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("Gopi"));

        System.out.println(passwordEncoder.encode("admin"));
    }
}
