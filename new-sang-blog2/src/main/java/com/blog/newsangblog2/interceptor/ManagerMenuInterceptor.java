package com.blog.newsangblog2.interceptor;

import com.blog.newsangblog2.common.enumeration.UserRoleType;
import com.blog.newsangblog2.common.utils.UserUtils;
import com.blog.newsangblog2.web.manager.menu.domain.ManagerMenu;
import com.blog.newsangblog2.web.manager.menu.repository.ManagerMenuRepository;
import com.blog.newsangblog2.web.manager.menu.repository.ManagerMenuRepositoryCustom;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ManagerMenuInterceptor extends HandlerInterceptorAdapter {


    private final ManagerMenuRepository managerMenuRepository;

    /*@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }*/

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String loginId = UserUtils.getLoginId();

        if (StringUtils.isNotEmpty(loginId) && !UserRoleType.ANONYMOUS.getRole().equals(loginId) && modelAndView != null) {
            List<ManagerMenu> managerMenuList = managerMenuRepository.findAllByFirstLevel();
            modelAndView.addObject("managerMenuList", managerMenuList);
        }
    }

}
