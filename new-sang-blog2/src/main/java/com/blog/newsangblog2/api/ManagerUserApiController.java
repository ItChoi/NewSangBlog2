package com.blog.newsangblog2.api;

import com.blog.newsangblog2.manager.user.domain.Manager;
import com.blog.newsangblog2.manager.user.repository.ManagerUserRepository;
import com.blog.newsangblog2.manager.user.support.ManagerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/manager/user")
@RestController
public class ManagerUserApiController {

    // TODO:: OAuth2 세팅하고 RestApi 진행하기

    private final ManagerUserRepository managerRepository;

    /*@GetMapping("/list")
    public ResponseEntity managerList(Model model) {
        List<Manager> list = managerRepository.findAll();

        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/create")
    public String createManager() {

        return "";
    }

    @PostMapping("/create")
    public ResponseEntity createManager(@RequestBody ManagerDto managerDto) {
        managerRepository.save(managerDto.toEntity());
        return new ResponseEntity(HttpStatus.OK);
    }*/

    /*@GetMapping("/login")
    public String login(Model model, String error) {

        if (!StringUtils.isEmpty(error)) {
            model.addAttribute("error", error);
        }

        return "/manager/user/login";
    }*/

    // 로그인 결과 페이지
    /*@GetMapping("/login/result")
    public String loginResult() {

        return "/loginSuccess";
    }*/

    // 로그아웃 결과 페이지
    /*@GetMapping("/logout/result")
    public String logoutResult() {
        return "/logout";
    }*/

    // 접근 거부 페이지
    /*@GetMapping("/access-denied")
    public String accessDeny() {
        System.out.println("access-denied page!!!!");
        return "/manager/user/access-denied";
    }*/

    // 내 정보 상세 페이지
    /*@GetMapping("/info")
    public String managerInfo() {
        return "/manager/user/info";
    }*/

}
