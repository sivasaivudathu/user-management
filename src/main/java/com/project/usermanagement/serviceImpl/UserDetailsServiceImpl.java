package com.project.usermanagement.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.usermanagement.exceptions.RecordNotFoundException;
import com.project.usermanagement.models.User;
import com.project.usermanagement.models.UserDetailsImpl;
import com.project.usermanagement.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
   
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersRepository.findByEmailId(userName);
        return Optional.ofNullable(optionalUser).orElseThrow(()->new UsernameNotFoundException("Username Not Found"))
               .map(UserDetailsImpl::new).get();
    }
    
    public User getUserById(int userId) {
    	Optional<User> optionalUser = usersRepository.findById(userId);
    	if(!optionalUser.isPresent()) {
    		throw new RecordNotFoundException("User not found with Id :"+userId);
    	}
    	return optionalUser.get();
    }
}
