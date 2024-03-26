package com.example.notetaking.config;


import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<String> {

	//You can do an implementation with spring security to get the current user.
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("User");
	}

}
