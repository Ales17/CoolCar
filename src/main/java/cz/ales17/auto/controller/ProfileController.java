package cz.ales17.auto.controller;

import cz.ales17.auto.dto.PasswordChangeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UserDetailsManager userDetailsManager;

    @GetMapping("/profile")
    public String profilePage(Model m) {
        return "profile-detail";
    }

    @GetMapping("/profile/password")
    public String changePasswordPage(Model m) {
        m.addAttribute("passwordDto", new PasswordChangeDto());
        return "profile";
    }


    @PostMapping("/profile/password")
    public String changePassword(Model m, @ModelAttribute("passwordDto") PasswordChangeDto dto) {
        try {
            userDetailsManager.changePassword(dto.getOldPassword(), dto.getNewPassword());
        } catch (Exception e) {
            m.addAttribute("message", "Hesla se neshodují");
            return "profile";
        }
        return "profile";
    }
}
