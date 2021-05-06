package com.eliasjr.aleloserviceapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.eliasjr.aleloserviceapi.domain.User;
import com.eliasjr.aleloserviceapi.exceptions.NotFoundException;
import com.eliasjr.aleloserviceapi.model.PageModel;
import com.eliasjr.aleloserviceapi.model.PageRequestModel;
import com.eliasjr.aleloserviceapi.repository.UserRepository;
import com.eliasjr.aleloserviceapi.service.util.HashUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User save(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);

		return userRepository.save(user);
	}

	public User update(User user) {
		String hash = HashUtil.getSecureHash(user.getPassword());
		user.setPassword(hash);

		return userRepository.save(user);
	}

	public User getById(Long id) {
		Optional<User> result = userRepository.findById(id);
		return result.orElseThrow(() -> new NotFoundException("There are not user with id = " + id));
	}

	public List<User> listAll() {
		return userRepository.findAll();
	}

	public PageModel<User> listAllOnLazyMode(PageRequestModel pr) {
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<User> page = userRepository.findAll(pageable);

		return new PageModel<>((int) page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
	}
	
	public void deleteById(Long id) {
		User user = getById(id);
		userRepository.delete(user);
	}
}
