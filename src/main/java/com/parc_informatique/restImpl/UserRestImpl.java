package com.parc_informatique.restImpl;
import java.util.Map;

import com.parc_informatique.constents.ParcConstents;
import com.parc_informatique.rest.UserRest;
import com.parc_informatique.service.UserService;
import com.parc_informatique.utils.ParcUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestImpl implements UserRest {
@Autowired
    UserService userService;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try {
            return userService.signUp(requestMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ParcUtils.getResponseEntity(ParcConstents.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

