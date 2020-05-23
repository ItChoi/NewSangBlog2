package com.blog.newsangblog2.web.manager.user.service;

import com.blog.newsangblog2.common.enumeration.PreNumber;
import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.repository.ManagerUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ManagerUserServiceImpl implements ManagerUserService {

	private final ManagerUserRepository managerUserRepository;
	private final ModelMapper modelMapper;

	@Override
	public Optional<Manager> findManagerBy(String loginId) {
		return managerUserRepository.findManagerByLoginId(loginId);
	}

	@Override
	public List<Manager> findAll() {

		List<Manager> findManagerList = managerUserRepository.findAll();

		return null;
	}

	@Override
	public void settingBaseInfo(Model model) {
		model.addAttribute("prePhoneNumber", PreNumber.PHONE.getPreNumber());
		model.addAttribute("userRoles", UserRoleType.values());
	}


}
