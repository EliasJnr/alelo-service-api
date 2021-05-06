package com.eliasjr.aleloserviceapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eliasjr.aleloserviceapi.domain.User;
import com.eliasjr.aleloserviceapi.dto.UserSavedto;
import com.eliasjr.aleloserviceapi.model.PageModel;
import com.eliasjr.aleloserviceapi.model.PageRequestModel;
import com.eliasjr.aleloserviceapi.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "users")
@Api(value = "Users")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Salvar")
	@PostMapping
	public ResponseEntity<User> save(@RequestBody @Valid UserSavedto userdto) {
		User createdUser = userService.save(userdto.transformToUser());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	}

	@ApiOperation(value = "Editar")
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable(name = "id") Long id, @Valid @RequestBody UserSavedto userdto) {
		User user = userdto.transformToUser();
		user.setId(id);
		User updatedUser = userService.update(user);
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}

	@ApiOperation(value = "Busca por id")
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable("id") Long id) {
		User user = userService.getById(id);
		return ResponseEntity.ok(user);
	}

	@ApiOperation(value = "Lista todos com paginação")
	@GetMapping
	public ResponseEntity<PageModel<User>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<User> pm = userService.listAllOnLazyMode(pr);
		return ResponseEntity.ok(pm);
	}

}
