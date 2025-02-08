package todoapp.web;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import todoapp.core.user.application.RegisterUser;
import todoapp.core.user.application.VerifyUserPassword;
import todoapp.core.user.domain.User;
import todoapp.core.user.domain.UserNotFoundException;
import todoapp.core.user.domain.UserPasswordNotMatchedException;
import todoapp.security.UserSession;
import todoapp.security.UserSessionHolder;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final VerifyUserPassword verifyUserPassword;
    private final RegisterUser registerUser;
    private final UserSessionHolder userSessionHolder;

    @GetMapping("/login")
    public void loginPage() {
    }

    @PostMapping("/login")
    public String loginProcess(@Valid LoginRecord loginRecord, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("bindingResult", bindingResult);
            model.addAttribute("message", "입력 값이 없거나 올바르지않아요.");
            return "login";
        }

        User user;
        try {
            user = verifyUserPassword.verify(loginRecord.username, loginRecord.password);
        } catch (UserNotFoundException nfe) {
            user = registerUser.register(loginRecord.username, loginRecord.password);
        }
        userSessionHolder.set(new UserSession(user));

        return "redirect:/todos";
    }

    @ExceptionHandler(UserPasswordNotMatchedException.class)
    public String handleUserPasswordNotMatched(UserPasswordNotMatchedException error, Model model) {
        model.addAttribute("message", error.getMessage());
        return "login";
    }

    record LoginRecord(@Size(min = 4, max = 20) String username, String password) {
    }
}
