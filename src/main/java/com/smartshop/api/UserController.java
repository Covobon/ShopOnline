package com.smartshop.api;

import com.smartshop.model.User;
import com.smartshop.service.CurrentUserService;
import com.smartshop.service.UserService;
import com.smartshop.utils.EncrytedPasswordUtils;
import com.smartshop.utils.SendMailVerify;
import org.hibernate.annotations.Parameter;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EncrytedPasswordUtils encrytedPasswordUtils;

    @Autowired
    private SendMailVerify sendMailVerify;

    @Autowired
    private CurrentUserService currentUserService;

    @Value("${server.port}")
    private int serverPort;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    @CrossOrigin(origins = {"*"})
    public List<User> getUser(){
        return userService.findAll();
    }

    @PostMapping
    public void create(@RequestBody User user){
        userService.create(user);
    }

    @PutMapping()
    public void update(@RequestBody User user) {
        userService.updatePassword(user);
    }

    @PutMapping("/profile")
    public ResponseEntity updateRoleUser(@RequestBody User user){
        if (user.getUserName() != currentUserService.get().getUserName()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping
    public void deleteUser(@RequestParam String userName){
        userService.deleteByUserName(userName);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<User> authenticate(@RequestBody User user){
        String username = user.getUserName();
        String password = user.getPassword();

        if (userService.verifyAccount(username, password)) {
            User theUser = userService.findByUserName(username);
            if (theUser.getLastAccess().equals(theUser.getCreateTime())){
                String content = "<a href=\"" + generateLinkVerify(user) +"\">Click</a> to verify account smartshop";
                try {
                    sendMailVerify.generateAndSendEmail(user.getEmail(), content);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }else{
                return new ResponseEntity<User>(theUser, HttpStatus.OK);
            }
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user){
        if (userService.findByUserName(user.getUserName()) != null) {
            return new ResponseEntity("Accout exists!", HttpStatus.NOT_ACCEPTABLE); /*Account exists*/
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity("Email exists!", HttpStatus.NOT_ACCEPTABLE);
        }
        userService.create(user);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping("/verify")
    public ResponseEntity verify(@RequestParam String x, String y) {
        byte[] decodeBytes = Base64.getDecoder().decode(x);
        String decodeString = new String(decodeBytes);

        Date dateNow = new Date();


        byte[] decodeDateByte = Base64.getDecoder().decode(y);
        String decodeDate = new String(decodeDateByte);
        Long miliseconds = Long.parseLong(decodeDate);

        Date theDate = new Date(miliseconds);

        if (dateNow.compareTo(theDate) > 0){
            return ResponseEntity.status(HttpStatus.LOCKED).body(null);
        }

        System.out.println(dateNow);
        int index = 0;
        for (int i = 0; i < decodeString.length(); i++) {
            if (decodeString.charAt(i) == ':') {
                index = i;
                break;
            }
        }

        String username = decodeString.substring(0, index);
        String password = decodeString.substring(index + 1, decodeString.length());

        if (userService.verifyAccount(username, password)) {
            User theUser = userService.findByUserName(username);
            if (theUser.getCreateTime().equals(theUser.getLastAccess())){
                theUser.setLastAccess(dateNow);
                userService.save(theUser);
                return ResponseEntity.ok().body(theUser);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    public String generateLinkVerify(User theUser) {
        String authenString = theUser.getUserName() + ":" + theUser.getPassword();
        String authenEncode = Base64.getEncoder().encodeToString(authenString.getBytes());

        Date dateNow = new Date();

        Long milisecond = dateNow.getTime() + 86400000;

        String time = milisecond.toString();

        String timeEncode = Base64.getEncoder().encodeToString(time.getBytes());

        return "http://localhost:" + this.serverPort + "/api/user/verify?x=" + authenEncode + "&y=" + timeEncode;
    }

    @GetMapping("/test")
    public  void currentUser(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        logger.info(request.getAuthType());
    }
}
