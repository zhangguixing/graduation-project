package com.jefflee.annotation;

import com.jefflee.entity.shiro.TbAdmin;
import com.jefflee.entity.shiro.TbLog;
import com.jefflee.service.shiro.LogService;
import com.jefflee.util.shiro.MyUtil;
import com.jefflee.util.shiro.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * 切点类   
 * @author Mr Du
 */
@Aspect    
@Component    
public  class SystemLogAspect {    
    //注入Service用于把日志保存数据库    
    @Resource    
    private LogService logServiceImp;
    //本地异常日志记录对象    
    private  static  final Logger logger = LoggerFactory.getLogger(SystemLogAspect. class);
    
    //Controller层切点    
    @Pointcut("@annotation(com.jefflee.annotation.SysLog)")
     public  void controllerAspect() {    
    }    
    
    /**  
     * 前置通知 用于拦截Controller层记录用户的操作  
     *  
     * @param joinPoint 切点  
     */    
    @Before("controllerAspect()")    
     public  void doBefore(JoinPoint joinPoint) {    
    
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession();    
        //读取session中的用户    
        TbAdmin user = (TbAdmin)SecurityUtils.getSubject().getPrincipal();
        //请求的IP    
        //String ip = request.getRemoteAddr();
        
        String requestURI=request.getRequestURI();
        
        String ip= WebUtils.getRemoteAddr(request);
        String method = joinPoint.getSignature().getDeclaringTypeName() + 
                "." + joinPoint.getSignature().getName();
        String params = ""; 
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {    
           	 params+=joinPoint.getArgs()[i]+";";   
           }    
       }    
         try {    
            //*========控制台输出=========*//    
            //System.out.println("=====前置通知开始=====");    
            String operation=getControllerMethodDescription(joinPoint);    
            String username=user.getUsername();
            //System.out.println("请求参数:" + params);    
            TbLog log=new TbLog();
            log.setCreateTime(MyUtil.getNowDateStr2());
            log.setIp(ip);
            log.setOperation(operation);
            log.setParams(params);
            log.setUsername(username);
            log.setMethod(requestURI);
			//*========保存数据库日志=========*// 
            //System.out.println(log);
            logServiceImp.insLog(log);
            //保存数据库    
        }  catch (Exception e) {    
            //记录本地异常日志    
            logger.error("==前置通知异常==");    
            logger.error("异常信息:{}", e.getMessage());    
        }    
    }    
    
    /**  
     * 获取注解中对方法的描述信息 用于Controller层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SysLog. class).value();
                     break;    
                }    
            }    
        }    
         return description;    
    }    
}    