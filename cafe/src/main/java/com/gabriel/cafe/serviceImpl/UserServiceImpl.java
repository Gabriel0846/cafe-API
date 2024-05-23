package com.gabriel.cafe.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.gabriel.cafe.JWT.CustomerUsersDetailsService;
import com.gabriel.cafe.JWT.JwtFilter;
import com.gabriel.cafe.JWT.JwtUtil;
import com.gabriel.cafe.POJO.User;
import com.gabriel.cafe.constents.CafeConstants;
import com.gabriel.cafe.dao.UserDao;
import com.gabriel.cafe.service.UserService;
import com.gabriel.cafe.utils.CafeUtils;
import com.gabriel.cafe.utils.EmailUtils;
import com.gabriel.cafe.wrapper.UserWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUsersDetailsService customerUsersDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    EmailUtils emailUtils;

    @Override
    public ResponseEntity<String> cadastrar(Map<String, String> requestMap) {
        log.info("Inside cadastrar {}", requestMap);
        try {
            if (validateSignUpMap(requestMap)) {
                User user = userDao.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestMap));
                    return CafeUtils.getResponseEntity("Registrado com sucesso.", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponseEntity("E-mail já cadastrado.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            log.error("Erro durante o cadastro:", ex);
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUpMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("nome") && requestMap.containsKey("telefone")
        && requestMap.containsKey("email") && requestMap.containsKey("password")) {
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setNome(requestMap.get("nome"));
        user.setTelefone(requestMap.get("telefone"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setPerfil("user");
        return user;
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            Authentication auth =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password"))
            );
            if (auth.isAuthenticated()) {
                if(customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")){
                    return new ResponseEntity<String>("{\"token\":\""+
                    jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
                    customerUsersDetailsService.getUserDetail().getPerfil()) + "\"}",
                HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\""+"Esperando aprovação do Admin."+"\"}", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<String>("{\"message\":\""+"Credencial inválida."+"\"}", HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<String>("{\"message\":\""+"Erro durante a autenticação."+"\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("{\"message\":\""+"Credencial inválida."+"\"}", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUser() {
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace(); 
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requesMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Optional<User> optional = userDao.findById(Integer.parseInt(requesMap.get("id")));
                if (optional.isEmpty()) {
                    userDao.updateStatus(requesMap.get("status"), Integer.parseInt(requesMap.get("id")));
                    sendMailToAllAdmin(requesMap.get("status"), optional.get().getEmail(), userDao.getAllAdmin());
                    return CafeUtils.getResponseEntity("Usuário atualizado com sucesso.", HttpStatus.OK);
                } else {
                    CafeUtils.getResponseEntity("Id de usuário não existe", HttpStatus.OK);
                }
            } else {
                return CafeUtils.getResponseEntity(CafeConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
        allAdmin.remove(jwtFilter.getCurrentUser());
        if (status!=null && status.equalsIgnoreCase("true")) {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "conta aprovada.", "USUARIO: "+user+" \n está aprovado por \nADMINISTRADOR:"+jwtFilter.getCurrentUser(), allAdmin);
        } else {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "conta desabilitada.", "USUARIO: "+user+" \n está desabilitada por \nADMINISTRADOR:"+jwtFilter.getCurrentUser(), allAdmin);
        }
    }
}
