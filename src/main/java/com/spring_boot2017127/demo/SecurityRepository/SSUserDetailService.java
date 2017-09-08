package com.spring_boot2017127.demo.SecurityRepository;


import com.spring_boot2017127.demo.DaLRepository.UzerRepository;
import com.spring_boot2017127.demo.ModelLayer.Roles;
import com.spring_boot2017127.demo.ModelLayer.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Transactional
@Service
public class SSUserDetailService implements UserDetailsService {


    private UzerRepository userRep;

    public SSUserDetailService(UzerRepository userRep)
    {
        this.userRep=userRep;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        try{
            User user =  userRep.findByUsername(username);
            if(user==null)
            {
                System.out.println("User not found"+ user.toString() );
                return null;
            }
            System.out.println("USER FROM USERNAME" + user.toString());
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),getAuthorities(user));

        }
        catch (Exception e){throw new UsernameNotFoundException("User not found");}
    }


    private Set<GrantedAuthority> getAuthorities(User user){
        Set<GrantedAuthority> authorities =  new HashSet<GrantedAuthority>();
        for(Roles role : user.getRoles())
        {
            GrantedAuthority grantedAuthority= new SimpleGrantedAuthority(role.getRole());
            authorities.add(grantedAuthority);
        }
        System.out.println("user authorities are" + authorities.toString());
        return authorities;
    }
}
