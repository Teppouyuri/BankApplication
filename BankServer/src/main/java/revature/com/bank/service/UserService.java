package revature.com.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import revature.com.bank.model.User;
import revature.com.bank.repository.UserDao;

import java.util.List;

@Service("userService")
public class UserService {
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<User> getAllUsers(){
        return this.userDao.findAll();}

    public User createUser(User user){
        User tempUser = this.userDao.findUserByUsername(user.getUsername()).orElse(null);
        User tempUserEmail = this.userDao.findUserByEmail(user.getEmail()).orElse(null);
        if(tempUser == null && tempUserEmail == null){
            String bcPass = user.getPassword();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encode = passwordEncoder.encode(bcPass);
            user.setPassword(encode);
            return this.userDao.save(user);
        }
        return null;
    }

    public User getUserByUserName(String username){return this.userDao.findUserByUsername(username).orElse(null);}

    public User getUserByEmail(String email){return this.userDao.findUserByEmail(email).orElse(null);}

    public User getUserById(Integer id){return this.userDao.findUserById(id).orElse(null);}

    public void updatePassword(String password, String email){
        User tempUser = this.userDao.findUserByEmail(email).orElse(null);
        tempUser.setPassword(password);
        this.userDao.save(tempUser);
    }

    public User login(User user){
        User tempUser = getUserByUserName(user.getUsername());
        //checks if the search returns a user object
        if(tempUser != null){
            //Checks to make sure their passwords match
            boolean isPasswordMatch = passwordEncoder.matches(user.getPassword(), tempUser.getPassword());
            if(isPasswordMatch){
                return tempUser;
            }
        }
        return null;
    }

    public User updateUser(User user){
        User tempUser = this.userDao.findById(user.getId()).orElse(null);

        if(tempUser!= null){
            if(!user.getUsername().equals("")){
                tempUser.setUsername(user.getUsername());
            }
            if(!user.getPassword().equals("")){
                String bcPass = user.getPassword();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String encode = passwordEncoder.encode(bcPass);
                tempUser.setPassword(encode);
            }
            if(!user.getFirstname().equals("")){
                tempUser.setFirstname(user.getFirstname());
            }
            if(!user.getLastname().equals("")){
                tempUser.setLastname(user.getLastname());
            }
            if(!user.getEmail().equals("")){
                tempUser.setEmail(user.getEmail());
            }
            if(!user.getUrl().equals("")){
                tempUser.setUrl(user.getUrl());
            }
            return this.userDao.save(tempUser);
        }
        return null;
    }


}
