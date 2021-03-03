package com.danbro.auth.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import com.danbro.auth.controller.vo.MemberLoginParamVo;
import com.danbro.auth.controller.vo.MemberRegisterParamVo;
import com.danbro.auth.rpc.UmsClient;
import com.danbro.auth.service.AuthService;
import com.danbro.common.dto.UmsMemberDto;
import com.danbro.common.entity.ResultBean;
import com.danbro.common.utils.MyStrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Danrbo
 * @Classname LoginController
 * @Description TODO
 * @Date 2021/2/25 22:03
 */
@Controller
public class LoginController {

    @Autowired
    AuthService authService;

    @Autowired
    UmsClient umsClient;

    @Autowired
    StringRedisTemplate redisTemplate;

    private final static String CODE_PREFIX = "sms:code:";
    private final static String LOGIN_USER = "loginUser";

    @ResponseBody
    @GetMapping("/sms/sendCode")
    public ResultBean<Void> sendCode(@RequestParam("phone") String phone) {
        authService.sendCode(phone);
        return ResultBean.ofSuccess();
    }

    @PostMapping("register")
    public String register(@Valid MemberRegisterParamVo registerParamVo,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        // 校验错误
        String phoneCode = CODE_PREFIX + registerParamVo.getPhone();
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().
                    collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            redirectAttributes.addFlashAttribute("errors", errors);
            redisTemplate.delete(phoneCode);
            return "redirect:http://auth.gulimall.com/reg.html";
        }
        // 校验成功
        // 验证码码判断
        String code = redisTemplate.opsForValue().get(phoneCode);
        // 验证码过期
        if (MyStrUtils.isEmpty(code)) {
            HashMap<String, String> errors = new HashMap<>(1);
            errors.put("code", "验证码过期");
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.gulimall.com/reg.html";
        }
        // 校验验证码
        if (registerParamVo.getCode().equals(code)) {
            // 删除验证码
            redisTemplate.delete(phoneCode);
            // rpc 注册用户成功
            ResultBean<UmsMemberDto> resultBean = umsClient.registerMember(registerParamVo);
            if (resultBean.getSuccess()) {
                return "redirect:http://auth.gulimall.com/login.html";
            }
            String msg = resultBean.getMsg();
            HashMap<String, String> errors = new HashMap<>(1);
            errors.put("errors", msg);
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.gulimall.com/reg.html";
        }
        HashMap<String, String> errors = new HashMap<>(1);
        errors.put("code", "验证码错误");
        redirectAttributes.addAttribute("errors", errors);
        return "redirect:http://auth.gulimall.com/reg.html";
    }


    @PostMapping("login")
    public String login(@Valid MemberLoginParamVo memberLoginParamVo,
                        BindingResult result,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {
        // 参数校验
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream().
                    collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.gulimall.com/login.html";
        }
        // 会员登录
        ResultBean<UmsMemberDto> resultBean = umsClient.loginMember(memberLoginParamVo);
        // 成功
        if (resultBean.getSuccess()) {
            session.setAttribute(LOGIN_USER,resultBean.getData());
            return "redirect:http://gulimall.com";
        }
        // 失败
        HashMap<String, String> errors = new HashMap<>(1);
        errors.put("errors", resultBean.getMsg());
        redirectAttributes.addFlashAttribute("errors", errors);
        return "redirect:http://auth.gulimall.com/login.html";
    }
}
