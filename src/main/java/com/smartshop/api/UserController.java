package com.smartshop.api;

import com.smartshop.model.Cart;
import com.smartshop.model.CartProduct;
import com.smartshop.model.Product;
import com.smartshop.model.User;
import com.smartshop.service.CartService;
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
import java.util.*;

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

    @Autowired
    private CartService cartService;

    @Value("${server.port}")
    private int serverPort;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public List<User> getUser() {
        return userService.findAll();
    }

    @PostMapping
    public void create(@RequestBody User user) {
        userService.create(user);
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody User user) {
        User theUser = userService.findByUserName(user.getUserName());
        if (theUser != null) {
            theUser.setAddress(user.getAddress());
            theUser.setFullName(user.getFullName());
            theUser.setPhoneNumber(user.getPhoneNumber());
            theUser.setRoles(user.getRoles());
            userService.save(theUser);
            return new ResponseEntity("Success!", HttpStatus.OK);
        }
        return new ResponseEntity("Fail!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/profile")
    public ResponseEntity updateRoleUser(@RequestBody User user) {
        if (user.getUserName() != currentUserService.get().getUserName()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        if (user.getUserName() == currentUserService.get().getUserName()) {
            User theUser = userService.findByUserName(user.getUserName());
            theUser.setAddress(user.getAddress());
            theUser.setFullName(user.getFullName());
            theUser.setPhoneNumber(user.getPhoneNumber());
            userService.save(theUser);
            return new ResponseEntity("Success!", HttpStatus.OK);
        } else {
            return new ResponseEntity("Fail!", HttpStatus.FORBIDDEN);
        }
    }


    @DeleteMapping
    public void deleteUser(@RequestParam String userName) {
        userService.deleteByUserName(userName);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        String username = user.getUserName();
        String password = user.getPassword();
        if (userService.verifyAccount(username, password)) {
            User theUser = userService.findByUserName(username);
            if (theUser.getLastAccess().equals(theUser.getCreateTime())) {
                String content = "<a href=\"" + generateLinkVerify(user) + "\">Click</a> to verify account smartshop";
                try {
                    sendMailVerify.generateAndSendEmail(theUser.getEmail(), content);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return new ResponseEntity("You must verify email! ", HttpStatus.OK);
            }
            return new ResponseEntity<User>(theUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
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
    public ResponseEntity verify(@RequestParam String x, String y, String p) {
        String password = p;
        byte[] decodeBytes = Base64.getDecoder().decode(x);
        String decodeString = new String(decodeBytes);

        Date dateNow = new Date();


        byte[] decodeDateByte = Base64.getDecoder().decode(y);
        String decodeDate = new String(decodeDateByte);
        Long miliseconds = Long.parseLong(decodeDate);

        Date theDate = new Date(miliseconds);
        if (dateNow.compareTo(theDate) > 0) {
            return ResponseEntity.status(HttpStatus.LOCKED).body(null);
        }
        User theUser = checkAuthen(x);
        if (password == null) {
            if (x != null) {
                theUser.setLastAccess(dateNow);
                return new ResponseEntity(theUser, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            userService.setPassword(theUser, password);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @GetMapping("/password")
    public ResponseEntity resetPassword(@RequestParam String username) {
        User user = userService.findByUserName(username);
        String link = generateLinkVerify(user);
        String result = "";
        result = link.substring(0, link.indexOf("verify")) + "password" + link.substring(link.indexOf("verify") + 6);
        String content = "<a href=\"" + result + "\">Click</a> to reset password";
        try {
            sendMailVerify.generateAndSendEmail(user.getEmail(), content);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity("You must verify email! ", HttpStatus.OK);
    }

    public String generateLinkVerify(User theUser) {
        String authenString = theUser.getUserName() + ":" + theUser.getPassword();
        String authenEncode = Base64.getEncoder().encodeToString(authenString.getBytes());

        Date dateNow = new Date();

        Long milisecond = dateNow.getTime() + 86400000; // 86400000 milisecond / day

        String time = milisecond.toString();

        String timeEncode = Base64.getEncoder().encodeToString(time.getBytes());

        return "http://localhost:4200" + "/verify?x=" + authenEncode + "&y=" + timeEncode;
    }

    public User checkAuthen(String authen) {
        byte[] decodeBytes = Base64.getDecoder().decode(authen);
        String decodeString = new String(decodeBytes);

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
            return userService.findByUserName(username);
        }
        return null;
    }
}
