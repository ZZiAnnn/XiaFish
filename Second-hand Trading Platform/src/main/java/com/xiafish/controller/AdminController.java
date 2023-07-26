package com.xiafish.controller;

import com.xiafish.pojo.*;
import com.xiafish.service.AdminService;
import com.xiafish.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("admin/user")
    public Result findAllUser(@RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer pageSize, User user) {
        log.info("管理员查询用户条件：page={},pageSize={},User筛选条件={}", page, pageSize, user.toString());
        PageBean pageBean = adminService.findAllUser(page, pageSize, user);
        return Result.success(pageBean);
    }

    @DeleteMapping("admin/user/{ids}")
    public Result deleteUser(@PathVariable List<Integer> ids) {
        log.info("管理员删除用户，id：{}", ids);
        adminService.deleteUser(ids);
        return Result.success();
    }

    @PostMapping("/admin/add")
    public Result addUser(@RequestBody User user)
    {
        log.info("管理员新建用户：{}",user.toString());
        String password=user.getUserPasswd();
        if(password!=null && !password.equals(""))
        {
            // 使用BCryptPasswordEncoder进行密码加密
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setUserPasswd(encoder.encode(password));
        }
        if((user.getUserEmail()!=null)&&!(ValidationUtils.isValidEmail(user.getUserEmail())))
            return Result.error("Invalid email format");
        if ((user.getUserPhoneNum() != null) && !(ValidationUtils.isValidPhoneNumber(user.getUserPhoneNum())))
            return Result.error("Invalid phone number format");
        adminService.addUser(user);
        return Result.success();
    }

    @PatchMapping("admin/update")
    public Result updateUser(@RequestBody User user) {
        log.info("管理员更改用户信息：{}", user.toString());
        //重新加密密码
        if (user.getUserPasswd() != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setUserPasswd(encoder.encode(user.getUserPasswd()));
        }
        if ((user.getUserEmail() != null) && !(ValidationUtils.isValidEmail(user.getUserEmail())))
            return Result.error("Invalid email format");
        if ((user.getUserPhoneNum() != null) && !(ValidationUtils.isValidPhoneNumber(user.getUserPhoneNum())))
            return Result.error("Invalid phone number format");
        adminService.updateUser(user);
        return Result.success();
    }

    @PostMapping("admin/orders")
    public Result getOrders(@RequestBody Map<String, Object> orderBody) {

        PageBean orderList = adminService.getOrder(orderBody);
        return Result.success(orderList);
    }

}
