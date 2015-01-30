package com.ht.court.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fx.oss.domain.User;
import com.fx.oss.sys.service.GetOrgList;
import com.fx.oss.sys.service.UserService;
import com.fx.oss.utils.MD5;
import com.fx.oss.utils.PaginateUtil;
import com.ht.court.util.FunctionUtil;
import com.hx.model.TpOrg;
import com.hx.service.TpOrgService;
import com.hx.util.Page;

@Controller
@Transactional
@RequestMapping("/courtPub")
public class UserManageController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserManageController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private GetOrgList getOrgList;
	@Autowired
	private TpOrgService tpOrgService;
	private int privilFlag; 
	private int flag = 0;
	private Date nowTime = new Date();
	
	@RequestMapping(value = "/vod/userList", method = RequestMethod.GET)
	public String userList(Model model, HttpServletRequest request)	  {
		User user = (User) FunctionUtil.getUserDetails();
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 20);
		List<String> orgIds = (List<String>) request.getSession().getAttribute("orgIds");
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute("orgNames");
		if(CollectionUtils.isEmpty(orgIds)||CollectionUtils.isEmpty(orgNames)){
			orgIds = getOrgList.getOrgId(user.getCourtId());
			orgNames = getOrgList.getOrgName(user.getCourtId());
		}
		TpOrg tpOrg = tpOrgService.get(user.getCourtId());
		Page<User> page = userService.findUserByPage(orgIds,startIndex,pageSize);
		
		if(page == null){
			return "/courtPub/user/userManage";
		}
		if(flag==1){
			flag = 0;
		}else{
			privilFlag = 0;
		}
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("courtName", tpOrg.getOrgName());
		model.addAttribute("courtIds", user.getCourtId());
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		model.addAttribute("warning", privilFlag);
		  return "/cpub/user/userList";
	}
	
	@RequestMapping(value = "/vod/toAddUser", method = RequestMethod.GET)
	public String toAddUser(){
		 return "/cpub/user/addUser";
	}
	@RequestMapping(value = "/vod/addUser", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		User user = this.uploadPic(request, response);
		User users = (User) FunctionUtil.getUserDetails();
		user.setCreateTime(nowTime);
		user.setCreateBy(users.getUsername());
		userService.insert(user);
		return "redirect:userList";
	}
	
	@RequestMapping(value = "/vod/toModifyUser", method = RequestMethod.GET)
	public String toModifyUser(@RequestParam String id,Model model){
		Long userId = Long.parseLong(id);
		logger.debug("转换成Long类型的Id为"+userId);
		User user = userService.getUserById(userId);
		User users = (User) FunctionUtil.getUserDetails();
		TpOrg tpOrg = tpOrgService.get(user.getCourtId());
		String courtName = tpOrg.getOrgName();
		privilFlag = userService.privilege(userService.getUserById(userId).getCourtId(), userService.getUserById(userId).getRoles());
		if(users.getCourtId().equals(userService.getUserById(userId).getCourtId())){
			if("ROLE_ADMIN".equals(userService.getUserById(userId).getRoles())){
				if(!users.getUserName().equals(user.getUserName())){
					flag=1;
					return "redirect:userList";
				}
			}
		}
		String roles = null;
		if("ROLE_ADMIN".equals(user.getRoles())){
			roles = "法院管理员";
		}else{
			roles = "合议庭成员";
		}

		model.addAttribute("courtName",courtName );
		model.addAttribute("roles", roles);
		model.addAttribute("user", user);
		  return "/cpub/user/modifyUser";
	}
	
	@RequestMapping(value = "/vod/modifyUser", method = RequestMethod.POST)
	public String modifyUser(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		User users = (User) FunctionUtil.getUserDetails();
		User user = this.uploadPic(request, response);
		user.setUpdateTime(nowTime);
		user.setLastUpdator(users.getUsername());
		userService.update(user);
		return "redirect:userList";
	}
	@RequestMapping(value = "/vod/deleteUser", method = RequestMethod.GET)
	public String deleteUser(@RequestParam String id,Model model)  {
		if(StringUtils.isEmpty(id)){
			return "redirect:userList";
		}
		Long userId = Long.parseLong(id);
		User users = (User) FunctionUtil.getUserDetails();
		privilFlag = userService.privilege(userService.getUserById(userId).getCourtId(), userService.getUserById(userId).getRoles());
		if(users.getCourtId().equals(userService.getUserById(userId).getCourtId())){
				if("ROLE_ADMIN".equals(userService.getUserById(userId).getRoles())){
					flag=1;
					return "redirect:userList";
			}else{
				userService.delete(userId);
			}
		}else{
			userService.delete(userId);
		}
		return "redirect:userList";
	}
	
	private String md5Encrypt(String userPassWord){
		MD5 md5 = new MD5();
		String md5PassWord = md5.getMD5ofStr(userPassWord);
		return md5PassWord;
	}
	
	@RequestMapping(value = "/vod/selectHeYi", method = RequestMethod.GET)
	public String selectHeYi(HttpServletRequest request, Model model)  {
		String roles = "ROLE_ADMIN_HEYI";
		User user = (User) FunctionUtil.getUserDetails();
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 9);
		List<String> orgIds = (List<String>) request.getSession().getAttribute("orgIds");
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute("orgNames");
		if(CollectionUtils.isEmpty(orgIds)||CollectionUtils.isEmpty(orgNames)){
			orgIds = getOrgList.getOrgId(user.getCourtId());
			orgNames = getOrgList.getOrgName(user.getCourtId());
		}
		Page<User> page = userService.findHEYIByPage(orgIds, roles, startIndex,pageSize);
		if(page == null){
			return "/courtPub/user/userManage";
		}
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("courtIds", user.getCourtId());
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		 return "/cpub/plan/heYiDialog";
	}
	
	@RequestMapping(value = "/vod/selectHeYiByCourt", method = RequestMethod.GET)
	public String findSelectHeYi(HttpServletRequest request, Model model)  {
		String roles = "ROLE_ADMIN_HEYI";
		String orgId = request.getParameter("qp_orgId");
		if ("请选择法院".equals(orgId)) {
			orgId = null;
		}
		User user = (User) FunctionUtil.getUserDetails();
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 9);
		List<String> orgIds =null;
		if(!StringUtils.isEmpty(orgId)){
			orgIds = new ArrayList<String>();
			orgIds.add(orgId);
		}
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute("orgNames");
		if(CollectionUtils.isEmpty(orgIds)||CollectionUtils.isEmpty(orgNames)){
			orgNames = getOrgList.getOrgName(user.getCourtId());
		}
		Page<User> page = userService.findHEYIByPage(orgIds, roles, startIndex,pageSize);
		if(page == null){
			return "userManage";
		}
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("courtIds", user.getCourtId());
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		return "/cpub/plan/byCourCollegial";
	}
	
	
	@RequestMapping(value = "/vod/findUserByName", method = RequestMethod.GET)
	public String findUserByOrgName(HttpServletRequest request, Model model){
		String CourtId = request.getParameter("qp_orgId");
		String userName  = request.getParameter("qp_userName");
		String roles = request.getParameter("qp_roles");
		User user = (User) FunctionUtil.getUserDetails();
		if("请选择法院".equals(CourtId)){
			CourtId = null;
		}
		if("请选择角色".equals(roles) ){
			roles =null;
		}
		if("输入你要查询的用户名".equals(userName) ){
			userName =null;
		}
		int startIndex = PaginateUtil.getStartIndex(request);
		int pageSize = PaginateUtil.getPageSize(request, 20);
		List<String> orgIds = null;
		if(!StringUtils.isEmpty(CourtId)){
			orgIds = new ArrayList<String>();
			orgIds.add(CourtId);
		}
		List<TpOrg> orgNames = (List<TpOrg>) request.getSession().getAttribute("orgNames");
		Page<User> page = userService.getUserByPage(CourtId, userName, roles, startIndex, pageSize);
		if(page == null){
			return "/courtPub/user/userManage";
		}
		PaginateUtil.addPager(model, page);
		model.addAttribute("orgNames", orgNames);
		model.addAttribute("courtIds", user.getCourtId());
		model.addAttribute("total", page.getTotalCount());
		model.addAttribute("lis_dev", page.getList());
		 return "/cpub/user/findUserByName";
	}

	
	private User  uploadPic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		response.setContentType("text/html");   
        // 设置字符编码为UTF-8, 这样支持汉字显示   
        response.setCharacterEncoding("UTF-8");
        Writer o = response.getWriter();
		
		/**
		 * 首先判断form的enctype是不是multipart/form-data
		 * 同时也判断了form的提交方式是不是post
		 * 方法：isMultipartContent(request)
		 */
	
		if(ServletFileUpload.isMultipartContent(request)){
			request.setCharacterEncoding("utf-8");
			
			// 实例化一个硬盘文件工厂,用来配置上传组件ServletFileUpload   
			DiskFileItemFactory factory =  new DiskFileItemFactory();
			
			//设置文件存放的临时文件夹，这个文件夹要真实存在
			String contextRealPath = request.getRealPath("public/upload/tmp");
			File fileDir = new File(contextRealPath);
//			File fileDir = new File("../webapps/tmp/");
			if(fileDir.isDirectory() && fileDir.exists()==false){
				fileDir.mkdir();
			}
			factory.setRepository(fileDir);
			
			//设置最大占用的内存
			factory.setSizeThreshold(102400);
			
			//创建ServletFileUpload对象
			ServletFileUpload sfu = new ServletFileUpload(factory);
			sfu.setHeaderEncoding("utf-8");
			
			//设置单个文件最大值byte 
			sfu.setFileSizeMax(10240000);
			
			//所有上传文件的总和最大值byte   
			sfu.setSizeMax(204800000);
			List<FileItem> items =  null;
			try {
				items = sfu.parseRequest(request);
			}catch (SizeLimitExceededException e) {   
                System.out.println("文件大小超过了最大值");   
            } catch(FileUploadException e) {   
                e.printStackTrace();   
            } 
            //取得items的迭代器
            Iterator<FileItem> iter = items==null?null:items.iterator();
            
            //图片上传后存放的路径目录
//            File images = new File("e:/upload/images/");
            File images = new File(contextRealPath);
			if(images.exists()==false){
				images.mkdirs();
			}
            //迭代items
            while(iter!=null && iter.hasNext()){
            	FileItem item = (FileItem) iter.next();
            	
            	//如果传过来的是普通的表单域
            	if(item.isFormField()){
            		System.out.print("普通的表单域:");   
                    System.out.print(new String(item.getFieldName()) + "  ");   
                    System.out.println(new String(item.getString("UTF-8")));  
                    user = this.getUser(item,user);
            	}
            	//文件域
            	else if(!item.isFormField()){
            		System.out.println("源图片:" + item.getName());   
            		String ss = item.getName();
            		user.setPhoto(ss);
//            		String fileName = item.getName().substring(item.getName().lastIndexOf("\\"));
            		BufferedInputStream in = new BufferedInputStream(item.getInputStream());
            		System.out.println(">>>>>>>>>>>>>" + in + item.getInputStream());
            		//文件存储在D:/upload/images目录下,这个目录也得存在 
            		
            		System.out.println(">>>>>>>"+ images.getAbsolutePath());
            		if(!StringUtils.isEmpty(ss)){
            			  BufferedOutputStream out = new BufferedOutputStream(   
                                  new FileOutputStream(new File(images.getAbsolutePath()+ ss))); 
                          Streams.copy(in, out, true);
                          o.write("文件上传成功");
            		}
            	}
            }
		}else {   
            System.out.println("表单的enctype 类型错误");   
        } 
		return user;
	}
	
	private User getUser(FileItem item, User user) throws UnsupportedEncodingException {
	    if (item.getFieldName().equals("id")) {
	      String id = item.getString("UTF-8");
	      if (!StringUtils.isEmpty(id)) {
	        System.out.println("用户的Id是：" + id);
	        long userId = Long.valueOf(id).longValue();
	        user.setId(Long.valueOf(userId));
	      } else {
	        user.setId(Long.valueOf(System.currentTimeMillis()));
	      }
	    } else if (item.getFieldName().equals("courtId")) {
	      user.setCourtId(item.getString());
	    } else if (item.getFieldName().equals("userName")) {
	      user.setUserName(item.getString("UTF-8"));
	    } else if (item.getFieldName().equals("userPassword")) {
	      user.setUserPassword(md5Encrypt(item.getString("UTF-8")));
	    } else if (item.getFieldName().equals("realName")) {
	      String realName = new String(item.getString("UTF-8"));
	      user.setRealName(realName);
	    } else if (item.getFieldName().equals("roles"))
	    {
	      if (("ROLE_ADMIN_HEYI".endsWith(item.getString("UTF-8"))) || ("合议庭成员".equals(item.getString("UTF-8")))) {
	        String roles = "ROLE_ADMIN_HEYI";
	        user.setRoles(roles);
	      }
	      if (("ROLE_ADMIN".endsWith(item.getString("UTF-8"))) || ("法院管理员".equals(item.getString("UTF-8")))) {
	        String roles = "ROLE_ADMIN";
	        user.setRoles(roles);
	      }
	    } else if (item.getFieldName().equals("createTimed")) {
	      String createTime = item.getString("UTF-8");
	      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      Date toCreateTime = null;
	      try {
	        toCreateTime = dateFormat.parse(createTime);
	      } catch (ParseException e) {
	        logger.debug("增加用户,创建时间转换错误");
	        e.printStackTrace();
	      }
	      user.setCreateTime(toCreateTime);
	    } else if (item.getFieldName().equals("updateTime")) {
	      String updateTime = item.getString("UTF-8");
	      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      Date toUpdateTime = null;
	      try {
	        toUpdateTime = dateFormat.parse(updateTime);
	      } catch (ParseException e) {
	        logger.debug("增加用户,创建时间转换错误");
	        e.printStackTrace();
	      }
	      user.setCreateTime(toUpdateTime);
	    }
	    return user;
	  }

}
