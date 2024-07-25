package com.parc_informatique.serviceImpl;

import com.parc_informatique.POJO.User;
import com.parc_informatique.constents.ParcConstents;
import com.parc_informatique.dao.UserDao;
import com.parc_informatique.service.UserService;
import com.parc_informatique.utils.ParcUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
   @Autowired
    UserDao userDao;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
           log.info("Inside signup{}", requestMap);
try  {      if (validateSignUpMap(requestMap)) {
                User user =userDao.findByEmailId(requestMap.get("email"));
                if(Objects.isNull(user)){
                   userDao.save(getUserFromMap(requestMap));
                   return ParcUtils.getResponseEntity( "Successfully Registered",HttpStatus.OK);
                } else {
                    return ParcUtils.getResponseEntity("Email already exists.",HttpStatus.BAD_REQUEST);
                }
           } else{
                    return ParcUtils.getResponseEntity(ParcConstents.INVALID_DATA,HttpStatus.BAD_REQUEST);
                 }
           }
                 catch (Exception ex) {
                 ex.printStackTrace();
                 return ParcUtils.getResponseEntity(ParcConstents.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
}

    }
   private boolean validateSignUpMap(Map<String,String> requestMap){
     if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
        && requestMap.containsKey("email") && requestMap.containsKey("password"))
     {
         return true;
     }
         return false;
    }
private User getUserFromMap(Map<String,String> requestMap){
        User user =new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
    user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
}
}



