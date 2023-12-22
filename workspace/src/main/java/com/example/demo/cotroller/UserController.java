package com.example.demo.cotroller;

import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.service.RegisterMail;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final RegisterMail registerMail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/join")
    public void GETJoin() {

        log.info("회원가입 페이지 진입");
    }

    //회원가입
    @PostMapping("/join")
    @ResponseBody
    public Map<String, String> userJoin(UserDTO userDTO) {
        Map<String, String> result = new HashMap<>();
        try {
            // 비밀번호를 BCrypt로 해싱하여 저장
            String encodedPassword = passwordEncoder.encode(userDTO.getUserPw());
            userDTO.setUserPw(encodedPassword);

            //회원 가입 서비스 호출
            userService.userJoin(userDTO);
            result.put("message", "회원가입이 성공적으로 이루어졌습니다. 로그인 후 이용해주세요.");
            result.put("status", "success");
        } catch (Exception e) {
            result.put("message", "회원가입에 실패하였습니다. 다시 시도해주세요.");
            result.put("status", "fail");
        }
        return result;
    }

    //아이디중복체크
    @PostMapping("/idCheck")
    @ResponseBody
    public Map<String, String> checkUserId(@RequestBody Map<String, String> userIdMap) {
        Map<String, String> result = new HashMap<>();
        String userId = userIdMap.get("userId");
        if (userService.isUserIdExist(userId)) {
            result.put("message", "이미 사용중인 아이디입니다.");
            result.put("status", "fail");
        } else {
            result.put("message", "사용 가능한 아이디입니다.");
            result.put("status", "success");
        }
        return result;
    }




    @PostMapping("/mailConfirm")
    @ResponseBody
    String mailConfirm(@RequestBody Map<String, String> body, HttpSession session) throws Exception {
        String email = body.get("email");
        String code = registerMail.sendSimpleMessage(email);
        session.setAttribute("code", code); //세션에 코드값 저장
        System.out.println("인증코드 : " + code);
        return code;
    }

    @PostMapping("/checkCode")
    @ResponseBody
    boolean checkCode(@RequestBody Map<String, String> body, HttpSession session) {
        String inputCode = body.get("code");
        String sessionCode = (String) session.getAttribute("code"); // session에서 code 가져오기

        if (inputCode.equals(sessionCode)) { // 사용자가 입력한 코드와 session의 코드가 같은지 확인
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("login")
    public void GETLogin(){
        log.info("로그인 페이지 진입");
    }

    @GetMapping(value={"/mypage","/mypagemodi"})
    public UserDTO mypage(Authentication authentication, Principal principal, Model model){
        log.info("mypage진입");
//        System.out.println(principal);
        String username = principal.getName();
        UserDTO userDTO = userService.getUser(username);
        model.addAttribute("userDTO", userDTO);
        return userService.getUser(username);
    }

    //    회원 정보 수정
    @PostMapping("/mypagemodi")
    public RedirectView modify(Authentication authentication, RedirectAttributes redirectAttributes, UserDTO userDTO){
        PrincipalDetails principalDetails = (PrincipalDetails)authentication.getPrincipal();
        userDTO.setUserRole(principalDetails.getUser().getUserRole());
        userService.modify(userDTO);
        principalDetails.setUser(userDTO);
        redirectAttributes.addAttribute("userId", userDTO.getUserId());
        return new RedirectView("mypagemodi");
    }
    //    회원 탈퇴
    @GetMapping("/mypage/remove")
    public RedirectView remove(Principal principal, Model model, HttpServletRequest request, HttpServletResponse response){
        String userId = principal.getName();
        try {
            userService.delete(userId);
            model.addAttribute("successMessage", "탈퇴가 성공적으로 되었습니다.");
            request.getSession().invalidate();
        } catch (Exception e) {
            // 탈퇴 실패 시에 대한 처리
            model.addAttribute("errorMessage", "탈퇴 중 오류가 발생했습니다.");
            return new RedirectView("/mypagemodi");
        }
        return new RedirectView("/main");
    }
}
