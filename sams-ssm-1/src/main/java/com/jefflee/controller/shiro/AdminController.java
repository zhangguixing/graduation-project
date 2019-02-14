package com.jefflee.controller.shiro;

import com.jefflee.annotation.SysLog;
import com.jefflee.entity.shiro.*;
import com.jefflee.service.shiro.AdminService;
import com.jefflee.util.shiro.RRException;
import com.jefflee.util.shiro.ResultUtil;
import com.jefflee.util.shiro.ShiroUtils;
import com.jefflee.util.shiro.VerifyCode;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.List;

@Controller
@RequestMapping("sys")
public class AdminController {
	@Autowired
	private AdminService adminServiceImpl;
	
	@RequestMapping("/main")
	public String main() {
		return "page/main";
	}
	@RequestMapping("/index")
	public String index(HttpServletRequest req) {
		TbAdmin admin = (TbAdmin)SecurityUtils.getSubject().getPrincipal();
		req.setAttribute("admin", admin);
		return "redirect:/index.jsp";
	}
	@RequestMapping("/refuse")
	public String refuse() {
		return "refuse";
	}

	/**
	 * 管理员登陆
	 * 
	 * @param req
	 * @param username
	 * @param password
	 * @param vcode
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public ResultUtil login(HttpServletRequest req, String username, String password, String vcode) {
		if(StringUtils.isEmpty(vcode)||StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
			throw new RRException("参数不能为空");
		}
		String kaptcha = ShiroUtils.getKaptcha("kaptcha").toLowerCase();
		if(!vcode.toLowerCase().equals(kaptcha)){
			return ResultUtil.error("验证码不正确");
		}
		
		try{
			Subject subject = ShiroUtils.getSubject();
			//md5加密
//			password= DigestUtils.md5DigestAsHex(password.getBytes());
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
		}catch (UnknownAccountException e) {
			return ResultUtil.error(e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return ResultUtil.error(e.getMessage());
		}catch (LockedAccountException e) {
			return ResultUtil.error(e.getMessage());
		}catch (AuthenticationException e) {
			return ResultUtil.error("账户验证失败");
		}
		return ResultUtil.ok();
		/*String vCode = req.getSession().getAttribute("vcode").toString().toLowerCase();
		if (vcode.toLowerCase().equals(vCode)) {
			TbAdmin admin = adminServiceImpl.login(username, password);
			if (admin != null) {
				// 登陆成功
				// 将密码置空
				admin.setPassword("");
				// 设置用户信息到Session作用域
				req.getSession().setAttribute("admin", admin);
				return new ResultUtil(0);
			}
			return new ResultUtil(502, "用户名或密码错误！");
		}
		return new ResultUtil(501, "验证码错误！");*/
	}

	/**
	 * 登出
	 * @param
	 * @return
	 */
	@RequestMapping(value="/loginOut")
	public String loginOut(){
		ShiroUtils.logout();
		return "redirect:/login.jsp";
	}
	
	/**
	 * 验证码
	 * 
	 * @param req
	 * @param resp
	 * @throws Exception
	 */
	@RequestMapping("/vcode")
	public void vcode(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();// 获取一次性验证码图片
		// 该方法必须在getImage()方法之后来调用
		// System.out.println("验证码图片上的文本:"+vc.getText());//获取图片上的文本
		// 把文本保存到session中，为验证做准备
		//req.getSession().setAttribute("vcode", vc.getText());
		//保存到shiro session
        ShiroUtils.setSessionAttribute("kaptcha", vc.getText());
		VerifyCode.output(image, resp.getOutputStream());// 把图片写到指定流中
	}

	/**
	 * 获取用户菜单
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping(value = "/getMenus", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public List<Menu> getMenus(HttpServletRequest req, HttpServletResponse resp) {
	//TbAdmin admin = (TbAdmin) req.getSession().getAttribute("admin");
		TbAdmin admin = (TbAdmin)SecurityUtils.getSubject().getPrincipal();
		List<Menu> menus = null;
		if (admin != null) {
			// 得到用户菜单
			menus = adminServiceImpl.selMenus(admin);
		}
		return menus;
	}
	
	@RequestMapping("/adminList")
	public String adminList() {
		return "page/admin/adminList";
	}
	
	@RequestMapping("/personalData")
	public String personalData(HttpServletRequest req) {
//		TbAdmin admin=(TbAdmin) req.getSession().getAttribute("admin");
		TbAdmin admin = (TbAdmin)SecurityUtils.getSubject().getPrincipal();
		TbAdmin ad = adminServiceImpl.selAdminById(admin.getId());
		List<TbRoles> roles = adminServiceImpl.selRoles();
		req.setAttribute("ad",ad);
		req.setAttribute("roles", roles);
		return "page/admin/personalData";
	}
	
	/**
	 * 管理员列表
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/getAdminList")
	@RequiresPermissions("sys:admin:list")
	@ResponseBody
	public ResultUtil getAdminList(Integer page, Integer limit, AdminSearch adminSearch) {
		ResultUtil admins = adminServiceImpl.selAdmins(page, limit, adminSearch);
		return admins;
	}
	
	@RequestMapping("/roleList")
	@RequiresPermissions("sys:role:list")
	public String roleList() {
		return "page/admin/roleList";
	}

	/**
	 * 角色列表
	 * @return
	 */
	@RequestMapping("/getRoleList")
	@RequiresPermissions("sys:role:list")
	@ResponseBody
	public ResultUtil getRoleList(Integer page,Integer limit) {
		return adminServiceImpl.selRoles(page, limit);
	}

	/**
	 * 获取角色id与名称
	 *
	 */
	@RequestMapping("/getRole")
	@RequiresPermissions("sys:role:list")
	@ResponseBody
	public List<TbRoles> getRoles(){
		return adminServiceImpl.selRoles();
	}

	/**
	 * 跳转编辑角色页面
	 * @param roleId
	 * @param roleName
	 * @param roleRemark
	 * @param req
	 * @return
	 */
	@RequestMapping("/editRole")
	@RequiresPermissions("sys:role:update")
	public String editRole(String roleId,String roleName,String roleRemark,HttpServletRequest req) {
		TbRoles role=new TbRoles();
		role.setRoleId(Long.parseLong(roleId));
		role.setRoleName(roleName);
		role.setRoleRemark(roleRemark);
		req.setAttribute("role", role);
		return "page/admin/editRole";
	}
	
	/**
	 * 得到指定角色权限树
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/xtreedata")
	@ResponseBody
	public List<XtreeData> xtreeData(@RequestParam(value="roleId", defaultValue="-1") Long roleId) {
		TbAdmin admin=new TbAdmin();
		admin.setRoleId(roleId);
		return adminServiceImpl.selXtreeData1(admin);
	}
	
	/**
	 * 更新角色信息
	 * @param
	 * @param m 权限字符串
	 */
	@SysLog(value="更新角色信息")
	@RequestMapping("/updRole")
	@RequiresPermissions("sys:role:update")
	@ResponseBody
	public void updRole(TbRoles role, String m) {
		//角色信息保存
		adminServiceImpl.updRole(role, m);
	}
	
	/**
	 * 添加新角色
	 * @param role
	 * @param m
	 */
	@SysLog(value="添加角色信息")
	@RequestMapping("/insRole")
	@RequiresPermissions("sys:role:save")
	@ResponseBody
	public ResultUtil insRole(TbRoles role, String m) {
		TbRoles r = adminServiceImpl.selRoleByRoleName(role.getRoleName());
		if(r!=null){
			return new ResultUtil(500, "角色名已存在,请重试！");
		}
		//角色信息保存
		adminServiceImpl.insRole(role, m);
		return ResultUtil.ok();
	}
	
	/**
	 * 删除指定角色信息
	 * @param roleId
	 * @return
	 */
	@SysLog(value="删除指定角色信息")
	@RequestMapping("/delRole/{roleId}")
	@RequiresPermissions("sys:role:delete")
	@ResponseBody
	public ResultUtil delRole(@PathVariable("roleId")Long roleId) {
		ResultUtil resultUtil=new ResultUtil();
		try {
			adminServiceImpl.delRole(roleId);
			resultUtil.setCode(0);
		} catch (Exception e) {
			resultUtil.setCode(500);
			e.printStackTrace();
		}finally {
			return resultUtil;
		}
	}
	
	/**
	 * 批量删除指定角色信息
	 * @param rolesId
	 * @return
	 */
	@SysLog(value="批量删除指定角色信息")
	@RequestMapping("/delRoles/{rolesId}")
	@RequiresPermissions("sys:role:delete")
	@ResponseBody
	public ResultUtil delRoles(@PathVariable("rolesId")String rolesId) {
		ResultUtil resultUtil=new ResultUtil();
		try {
			adminServiceImpl.delRoles(rolesId);
			resultUtil.setCode(0);
		} catch (Exception e) {
			resultUtil.setCode(500);
			e.printStackTrace();
		}finally {
			return resultUtil;
		}
	}
	
	@RequestMapping("/addRole")
	@RequiresPermissions("sys:role:save")
	public String addRole() {
		return "page/admin/addRole";
	}
	
	/**
	 * 角色名唯一性检查
	 * @param roleName
	 * @return
	 */
	@RequestMapping("/checkRoleName")
	@ResponseBody
	public ResultUtil checkRoleName(Long roleId, String roleName) {
		TbRoles role = adminServiceImpl.selRoleByRoleName(roleName);
		if(role==null){
			return new ResultUtil(0);
		}else if(role.getRoleId()==roleId){
			return new ResultUtil(0);
		}else{
			return new ResultUtil(500,"角色名已存在！");
		}
	}
	
	/**
	 * 通过id删除管理员
	 * @param id
	 * @return
	 */
	@SysLog(value="删除指定管理员")
	@RequestMapping("/delAdminById/{id}")
	@RequiresPermissions("sys:admin:delete")
	@ResponseBody
	public ResultUtil delAdminById(@PathVariable("id")Long id) {
		if(id==1){
			return ResultUtil.error();
		}
		try {
			adminServiceImpl.delAdminById(id);
			return ResultUtil.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error();
		}
	}
	
	/**
	 * 批量删除指定管理员
	 * @param
	 * @return
	 */
	@SysLog(value="批量删除指定管理员")
	@RequestMapping("/delAdmins/{adminStr}")
	@RequiresPermissions("sys:admin:delete")
	@ResponseBody
	public ResultUtil delAdmins(HttpServletRequest req,@PathVariable("adminStr")String adminStr) {
		String[] strs = adminStr.split(",");
		for (String str : strs) {
			TbAdmin admin = (TbAdmin)SecurityUtils.getSubject().getPrincipal();
			if((admin.getId()==Long.parseLong(str))){
				return ResultUtil.error();
			}
			if("1".equals(str)){
				return ResultUtil.error();
			}
		}
		try {
			adminServiceImpl.delAdmins(adminStr);
			return ResultUtil.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error();
		}
	}
	
	@RequestMapping("/addAdmin")
	@RequiresPermissions("sys:admin:save")
	public String addAdmin(HttpServletRequest req){
		List<TbRoles> roles = adminServiceImpl.selRoles();
		req.setAttribute("roles", roles);
		return "page/admin/addAdmin";
	}
	
	/**
	 * 管理员用户名唯一性检查
	 * @param
	 * @return
	 */
	@RequestMapping("/checkAdminName/{username}")
	@ResponseBody
	public ResultUtil checkAdminName(@PathVariable("username")String username) {
		TbAdmin admin = adminServiceImpl.selAdminByUserName(username);
		if(admin!=null){
			return new ResultUtil(500,"角色名已存在！");
		}
		return new ResultUtil(0);
	}
	
	/**
	 * 增加管理員
	 * 日期类型会导致数据填充失败，请求没反应
	 * @para
	 * @return
	 */
	@SysLog(value="添加管理员")
	@RequestMapping("/insAdmin")
	@RequiresPermissions("sys:admin:save")
	@ResponseBody
	public ResultUtil insAdmin(TbAdmin admin) {
		//防止浏览器提交
		TbAdmin a = adminServiceImpl.selAdminByUserName(admin.getUsername());
		if(a!=null){
			return new ResultUtil(500, "用户名已存在,请重试！");
		}
		adminServiceImpl.insAdmin(admin);
		return ResultUtil.ok();
	}
	
	@RequestMapping("/editAdmin/{id}")
	@RequiresPermissions("sys:admin:update")
	public String editAdmin(HttpServletRequest req,@PathVariable("id")Long id) {
		TbAdmin ad = adminServiceImpl.selAdminById(id);
		List<TbRoles> roles = adminServiceImpl.selRoles();
		req.setAttribute("ad",ad);
		req.setAttribute("roles", roles);
		return "page/admin/editAdmin";
	}
	
	@RequestMapping("/checkAdminByEmail/{eMail}")
	@ResponseBody
	public ResultUtil checkAdminByEmail(@PathVariable("eMail")String eMail,String username) {
		TbAdmin admin=adminServiceImpl.selAdminByEmail(eMail,username);
		if(admin!=null){
			return new ResultUtil(500,"邮箱已被占用！");
		}
		return new ResultUtil(0);
	}
	
	/**
	 * 更新管理员信息
	 * @param
	 */
	@SysLog(value="更新管理员信息")
	@RequestMapping("/updAdmin")
	@RequiresPermissions("sys:admin:update")
	@ResponseBody
	public ResultUtil updAdmin(TbAdmin admin) {
		try {
			adminServiceImpl.updAdmin(admin);
			TbAdmin tbAdmin = (TbAdmin)SecurityUtils.getSubject().getPrincipal();
			tbAdmin.setFullname(admin.getFullname());
			return ResultUtil.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtil.error();
		}
	}
	
	@RequestMapping("/changePwd")
	public String changePwd() {
		return "page/admin/changePwd";
	}
	
	/**
	 * 修改密码
	 * @param req
	 * @param oldPwd
	 * @param newPwd
	 * @return
	 */
	@SysLog(value="修改密码")
	@RequestMapping("/updPwd")
	@ResponseBody
	public ResultUtil updPwd(HttpServletRequest req,String oldPwd,String newPwd) {
		TbAdmin user = (TbAdmin)SecurityUtils.getSubject().getPrincipal();
		if(user!=null){
			//测试账号不支持修改密码
			if("test".equals(user.getUsername())){
				return ResultUtil.error();
			}
			TbAdmin admin = adminServiceImpl.login(user.getUsername(), oldPwd);
			if(admin!=null){
				admin.setPassword(newPwd);
				adminServiceImpl.updAdmin1(admin);
				//修改密码后移除作用域，重新登陆
				SecurityUtils.getSubject().logout();
				return ResultUtil.ok();
			}else{
				return new ResultUtil(501,"旧密码错误，请重新填写！");
			}
		}
		return new ResultUtil(500,"请求错误！");
	}
	
}
