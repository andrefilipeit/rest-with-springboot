package br.com.andrefilipeos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.andrefilipeos.repository.UserRepository;

//Tells spring that the class is injected into every project to avoid instantiation works with @Autowired
@Service
public class UserServices implements UserDetailsService{

	@Autowired
	UserRepository repository;

	public UserServices(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var user = repository.findByUsername(username);
		
		if(user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
	}

	
	

}
