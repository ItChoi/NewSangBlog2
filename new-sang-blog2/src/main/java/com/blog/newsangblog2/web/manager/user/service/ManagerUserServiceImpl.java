package com.blog.newsangblog2.web.manager.user.service;

import com.blog.newsangblog2.common.enumeration.PreNumber;
import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.common.exception.DataNotFoundException;
import com.blog.newsangblog2.common.exception.UserNotFoundException;
import com.blog.newsangblog2.common.utils.UserUtils;
import com.blog.newsangblog2.web.manager.user.domain.Manager;
import com.blog.newsangblog2.web.manager.user.repository.ManagerUserRepository;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		model.addAttribute("userRoles", selectBoxUseRoleTypesByAuth());
	}


	private List<UserRoleType> selectBoxUseRoleTypesByAuth() {
		UserRoleType roleCheck = UserRoleType.ANONYMOUS;
		String loginId = UserUtils.getLoginId();
		if (StringUtils.isNotEmpty(loginId) && !loginId.equals(UserRoleType.ANONYMOUS.name())) {
			Manager manager = managerUserRepository.findManagerByLoginId(loginId).orElseThrow(() -> new UserNotFoundException());
			roleCheck = manager.getUserRole().getAuthority();
		}

		UserRoleType myRole = roleCheck;
		List<UserRoleType> userRoleTypes = Stream.of(UserRoleType.values())
				.filter(value -> {
					boolean hasRole = false;

					if (value.getPosition() == UserRoleType.SUPERVISOR.name() || value.getPosition() == UserRoleType.ADMIN.name()) {
						if (myRole.getPosition() == UserRoleType.SUPERVISOR.name()) {
							hasRole = true;
						}
					}

					if ((value.getPosition() == UserRoleType.ADMIN.name() && myRole.getPosition() == UserRoleType.ADMIN.name())) {
						hasRole = true;
					}

					if (value.getPosition() == UserRoleType.MEMBER.name()) {
						hasRole = true;
					}

					return hasRole;
				})
				.collect(Collectors.toList());
		return userRoleTypes;
	}


}
