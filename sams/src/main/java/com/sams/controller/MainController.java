package com.sams.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MainController
 */
@Controller
@RequestMapping("main")
public class MainController implements ErrorController {
    @Override
    public String getErrorPath() {
        return null;
    }
//    @Autowired
//    private AuthoritiesService authoritiesService;
//    @Autowired
//    private LoginRecordService loginRecordService;
//
//    /**
//     * 主页
//     */
//    @RequestMapping({"/", "/index"})
//    public String index(Model model) {
//        List<Authorities> authorities = authoritiesService.listByUserId(getLoginUserId());
//        List<Map<String, Object>> menuTree = getMenuTree(authorities, -1);
//        model.addAttribute("menus", menuTree);
//        model.addAttribute("loginUser", getLoginUser());
//        return "index.html";
//    }
//
//    /**
//     * 登录页
//     */
//    @GetMapping("/login")
//    public String login() {
//        if (getLoginUser() != null) {
//            return "redirect:index";
//        }
//        return "login.html";
//    }
//
//    /**
//     * 登录
//     */
//    @ResponseBody
//    @PostMapping("/login")
//    public JsonResult doLogin(String username, String password, String code, HttpServletRequest request) {
//        if (StringUtil.isBlank(username, password)) {
//            return JsonResult.error("账号密码不能为空");
//        }
//        if (!CaptchaUtil.ver(code, request)) {
//            CaptchaUtil.clear(request);
//            return JsonResult.error("验证码不正确");
//        }
//        try {
//            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//            SecurityUtils.getSubject().login(token);
//            addLoginRecord(getLoginUserId(), request);
//            return JsonResult.ok("登录成功");
//        } catch (IncorrectCredentialsException ice) {
//            return JsonResult.error("密码错误");
//        } catch (UnknownAccountException uae) {
//            return JsonResult.error("账号不存在");
//        } catch (LockedAccountException e) {
//            return JsonResult.error("账号被锁定");
//        } catch (ExcessiveAttemptsException eae) {
//            return JsonResult.error("操作频繁，请稍后再试");
//        }
//    }
//
//    /**
//     * 递归转化树形菜单
//     */
//    private List<Map<String, Object>> getMenuTree(List<Authorities> authorities, Integer parentId) {
//        List<Map<String, Object>> list = new ArrayList<>();
//        for (int i = 0; i < authorities.size(); i++) {
//            Authorities temp = authorities.get(i);
//            if (temp.getIsMenu() == 0 && parentId == temp.getParentId()) {
//                Map<String, Object> map = new HashMap<>();
//                map.put("menuName", temp.getAuthorityName());
//                map.put("menuIcon", temp.getMenuIcon());
//                map.put("menuUrl", StringUtil.isBlank(temp.getMenuUrl()) ? "javascript:;" : temp.getMenuUrl());
//                map.put("subMenus", getMenuTree(authorities, authorities.get(i).getAuthorityId()));
//                list.add(map);
//            }
//        }
//        return list;
//    }
//
//    /**
//     * 添加登录日志
//     */
//    private void addLoginRecord(Integer userId, HttpServletRequest request) {
//        UserAgentGetter agentGetter = new UserAgentGetter(request);
//        // 添加到登录日志
//        LoginRecord loginRecord = new LoginRecord();
//        loginRecord.setUserId(userId);
//        loginRecord.setOsName(agentGetter.getOS());
//        loginRecord.setDevice(agentGetter.getDevice());
//        loginRecord.setBrowserType(agentGetter.getBrowser());
//        loginRecord.setIpAddress(agentGetter.getIpAddr());
//        loginRecordService.add(loginRecord);
//    }
//
//    /**
//     * 图形验证码，用assets开头可以排除shiro拦截
//     */
//    @RequestMapping("/assets/captcha")
//    public void captcha(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            CaptchaUtil.out(4, request, response);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 控制台
//     */
//    @RequestMapping("/console")
//    public String console() {
//        return "tpl/console.html";
//    }
//
//    /**
//     * 消息弹窗
//     */
//    @RequestMapping("/tpl/message")
//    public String message() {
//        return "tpl/message.html";
//    }
//
//    /**
//     * 修改密码弹窗
//     */
//    @RequestMapping("/tpl/password")
//    public String password() {
//        return "tpl/password.html";
//    }
//
//    /**
//     * 主题设置弹窗
//     */
//    @RequestMapping("/tpl/theme")
//    public String theme() {
//        return "tpl/theme.html";
//    }
//
//    /**
//     * 错误页
//     */
//    @RequestMapping("/error")
//    public String error(String code) {
//        if ("403".equals(code)) {
//            return "error/403.html";
//        }
//        return "error/404.html";
//    }
//
//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}
