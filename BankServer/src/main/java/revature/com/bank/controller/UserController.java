package revature.com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import revature.com.bank.model.JsonResponse;
import revature.com.bank.model.User;
import revature.com.bank.service.UserService;
import revature.com.bank.utility.JWTUtility;

import javax.servlet.http.HttpSession;

@RestController("userController")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(value="/user")
public class UserController {
    private UserService userService;
    private JWTUtility jwtUtility;

    @Autowired
    public UserController(UserService userService, JWTUtility jwtUtility){
        this.userService = userService;
        this.jwtUtility  = jwtUtility;
    }

    @GetMapping
    public JsonResponse getAllUsers(){
        return new JsonResponse(true, "listing all users", this.userService.getAllUsers());
    }

    @PostMapping
    public JsonResponse createUser(@RequestBody User user){
        User tempUser = this.userService.createUser(user);
        if(tempUser != null){
            return new JsonResponse(true, jwtUtility.genToken(tempUser.getId()), tempUser.getUsername());
        } else{
            return new JsonResponse(false, "Username or email already exists", null);
        }
    }

    @GetMapping("username/{username}")
    public JsonResponse getUserByUsername(@PathVariable String username){
        JsonResponse jsonResponse;
        User user = this.userService.getUserByUserName(username);

        if (user != null){
            jsonResponse = new JsonResponse(true, "user found", user);
        } else{
            jsonResponse = new JsonResponse(false, "user not found", null);
        }
        return jsonResponse;
    }

    @GetMapping("{id}")
    public JsonResponse getUserById(@PathVariable Integer id) {
        return new JsonResponse(true, "user found", this.userService.getUserById(id));
    }

    @GetMapping("checkEmail/{email}")
    public JsonResponse sendEmail(@PathVariable String email) {
        User tempUser = this.userService.getUserByEmail(email);
        if (tempUser == null)
            return new JsonResponse(false, "Incorrect email", null);

        return new JsonResponse(true, "Email found", email);
    }

    @PatchMapping("updatePassword")
    public JsonResponse updatePassword(@RequestBody User user) {
        this.userService.updatePassword(user.getPassword(), user.getEmail());
        return new JsonResponse(true, "password updated", user.getEmail());
    }

    @PatchMapping
    public JsonResponse updateUser(HttpSession session, @RequestBody User user){
        User tempUser = (User) session.getAttribute("loggedInUser");
        user.setId(tempUser.getId());
        return new JsonResponse(true, "User updated", this.userService.updateUser(user));
    }
    @GetMapping("check-session")
    public JsonResponse checkSession(HttpSession session){
        JsonResponse jsonResponse;
        User user = (User) session.getAttribute("loggedInUser");
        if(user != null){
            User tempUser = userService.getUserById(user.getId());
            jsonResponse = new JsonResponse(true, "session found", tempUser);
        }else{
            jsonResponse = new JsonResponse(false, "no session found", null);
        }

        return jsonResponse;
    }

    @PostMapping("login")
    public JsonResponse login(@RequestBody User user) {
        JsonResponse response;

        User tempUser = this.userService.login(user);
        if (tempUser != null) {
            //session.setAttribute("loggedInUser", tempUser);
            response = new JsonResponse(true, jwtUtility.genToken(tempUser.getId()),tempUser);
        } else {
            response = new JsonResponse(false, "Invalid username or password. (Remember, these are case sensitive!)", null);
        }
        return response;
    }

    @GetMapping("logout")
    public JsonResponse logout(HttpSession session){
        session.setAttribute("loggedInUser",null);

        return new JsonResponse(true, "session terminated",null);
    }
}
