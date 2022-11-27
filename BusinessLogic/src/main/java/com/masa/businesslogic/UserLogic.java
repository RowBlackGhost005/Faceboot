package com.masa.businesslogic;

import com.masa.authentication.Authentication;
import com.masa.authentication.IAuthentication;
import com.masa.authentication.IAuthenticationMethod;
import com.masa.domain.User;
import com.masa.persitency.IPersistency;
import com.masa.persitency.Persistency;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Luis Angel Marin
 */
public class UserLogic {

    private IPersistency persistency;
    private IAuthentication authentication;

    public UserLogic() {
        persistency = new Persistency();
        authentication = new Authentication(persistency);
    }

    public User login(User user , String authMetod) throws Exception {
        
        User authUser = authentication.authenticate(user, authMetod);
        
        if(authMetod != "local"){
            authUser = persistency.getUserByEmail(authUser.getEmail());
        }
        
//        User existingUser = validateLogin(user);
        return authUser;
    }

    public User registerUser(User user) throws Exception {
        validateRegister(user);
        persistency.createUser(user);
        return user;
    }
    
     public User registerExternalUser(User user) throws Exception {
        validatePhone(user.getPhone());
        persistency.createUser(user);
        return user;
    }
    
    public User loginUser(IAuthenticationMethod authenticationMethod) throws Exception{
        User user = authenticationMethod.login(new User());
        
        return persistency.getUserByEmail(user.getEmail());
    }

    public User editUser(User user) throws Exception {
        validateEdit(user);
        persistency.editUser(user);
        return user;
    }

    public User get(String userId) {
        return persistency.getUser(userId);
    }

    public List<User> getAll() {
        return persistency.getAllUsers();
    }

    private User validateLogin(User user) throws Exception {
        if (user.getEmail().isBlank()) {
            throw new Exception("Enter an email or phone number");
        } else if (user.getPassword().isBlank()) {
            throw new Exception("Enter a password");
        }

        try {
            Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
            boolean validEmail = pattern.matcher(user.getEmail()).matches();
            if (!validEmail) {
                throw new Exception();
            }
        } catch (Exception ex) {
            try {
                user.setPhone(user.getEmail());
                if (user.getPhone().matches("[0-9]+")) {
                    user.setEmail(null);
                }
            } catch (Exception exception) {
                throw new Exception("Enter a valid email or phone");
            }
        }

        User existingUser = persistency.getUserLogin(user);
        if (existingUser == null) {
            throw new Exception("The email or password are not correct");
        } else {
            return existingUser;
        }
    }

    private void validateRegister(User user) throws Exception {
        validateName(user.getName());
        validateEmail(user.getEmail());
        validatePhone(user.getPhone());
        validateGender(user.getGender());
        validateBirthDate(user.getBirthDate());
        validatePassword(user.getPassword());
    }

    private void validateEdit(User user) throws Exception {
        validateName(user.getName());
        validateGender(user.getGender());
        validateBirthDate(user.getBirthDate());
        validatePassword(user.getPassword());
    }

    private void validateName(String name) throws Exception {
        if (name.isBlank()) {
            throw new Exception("Enter a name");
        } else if (name.length() > 99) {
            throw new Exception("Name must have less than 100 characters");
        }
    }

    private void validateEmail(String email) throws Exception {
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        boolean validEmail = pattern.matcher(email).matches();
        User existingUser = persistency.getUserByEmail(email);

        if (email.isBlank()) {
            throw new Exception("Enter an email");
        } else if (!validEmail) {
            throw new Exception("Enter a valid email");
        } else if (existingUser != null) {
            throw new Exception("Email is already associated to an existing accout");
        }
    }

    private void validatePhone(String phone) throws Exception {
        User existingUser = persistency.getUserByPhone(phone);

        if (phone.isBlank()) {
            throw new Exception("Enter a phone number");
        } else if (!phone.matches("[0-9]+")) {
            throw new Exception("Phone must contain only numbers");
        } else if (phone.length() > 10 || phone.length() < 10) {
            throw new Exception("Phone must contain only 10 numbers");
        } else if (existingUser != null) {
            throw new Exception("Phone is already associated to an existing accout");
        }
    }

    private void validateGender(String gender) throws Exception {
        if (gender == null) {
            throw new Exception("Select a gender");
        } else if (gender.equals("Male")) {
        } else if (gender.equals("Female")) {
        } else if (gender.equals("Other")) {
        } else {
            throw new Exception("Select a valid gender");
        }
    }

    private void validateBirthDate(String date) throws Exception {
        LocalDate dt = null;

        try {
            dt = LocalDate.parse(date);
        } catch (DateTimeParseException ex) {
            throw new Exception("Select a valid birth date (dd/mm/yyyy)");
        }

        if (date.isBlank()) {
            throw new Exception("Select a birth date");
        } else if (dt.isAfter(LocalDate.now())) {
            throw new Exception("Birth date must be a past date");
        }

    }

    private void validatePassword(String password) throws Exception {
        if (password.isBlank()) {
            throw new Exception("Enter a password");
        } else if (password.length() < 8) {
            throw new Exception("Password must have at least 8 characters");
        } else if (password.length() >= 20) {
            throw new Exception("Password must have less than 20 characters");
        }
    }

}
