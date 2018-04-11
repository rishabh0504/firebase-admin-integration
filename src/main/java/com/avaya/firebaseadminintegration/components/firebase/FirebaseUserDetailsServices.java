package com.avaya.firebaseadminintegration.components.firebase;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.avaya.firebaseadminintegration.components.user.CurrentUserDetails;
import com.avaya.firebaseadminintegration.services.FirebaseUserService;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

@Service
public class FirebaseUserDetailsServices implements UserDetailsService {

	@Autowired
    private FirebaseUserService firebaseUserService;

    
    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        try {
            UserRecord userRecord = firebaseUserService.findUserByUid(uid);

            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new CurrentUserDetails(userRecord.getUid(), authorities);
        } catch (FirebaseAuthException | InterruptedException | ExecutionException e) {
            throw new UsernameNotFoundException("Usernot found exception");
        }
    }
}