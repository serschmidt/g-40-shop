package de.ait_tr.g_40_shop;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        // to get encrypted password

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "111";
        String encodePassword = encoder.encode(password);

        System.out.println(encodePassword);
    }

}
