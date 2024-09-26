package com.example.springjpa.service;


import com.example.springjpa.entity.User;
import com.example.springjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public boolean addUser(User user)
    {
        try{
            return true;
        }
        catch(Exception e)
        {
            logger.info("error occurred - "+ e);
            throw e;
        }
    }


    public List<User> getUserByTenant(UUID tenantId)
    {
        try{
            return userRepository.findByTenantId(tenantId);
        }catch (Exception e)
        {
            logger.info("error getting users - " + e);
            throw e;
        }
    }

    public void updateUser(String username, UUID userId)
    {
        try{
            Optional<User> userOpt = userRepository.findById(userId);
            if(userOpt.isPresent())
            {
                User user = userOpt.get();
                user.setUsername(username);
                userRepository.save(user);
            }
            else {
                logger.warning("User not found");
            }

        }catch (Exception e)
        {
            logger.info("Error updating user - " + e);
            throw e;
        }


//        public Page<User> findAll(Pageable page)
//        {
//            return userRepository.findAll(page);
//        }
    }
}
