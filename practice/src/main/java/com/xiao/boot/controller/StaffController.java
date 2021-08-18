package com.xiao.boot.controller;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.xiao.boot.bean.dto.AddStaff;
import com.xiao.boot.bean.po.R;
import com.xiao.boot.bean.po.Staff;
import com.xiao.boot.service.StaffService;
import org.thymeleaf.util.StringUtils;

@Controller
@RequestMapping("staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @GetMapping(value = {"/", "/login"})
    @ResponseBody
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    /**
     *
     * @description: 登录逻辑
     * @param: [jobId, password]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/16
     */

    public R login(@RequestParam("jobId") String jobId,
                   @RequestParam("password") String password) {
        Staff staff = staffService.login(Integer.valueOf(jobId), password);
        if (staff == null) {
            return R.failed("登陆失败");
        }
        if (staff.getPassword() != password) {
            return R.failed("密码不正确");
        }
        return R.ok(staff, "登陆成功");

    }

    @GetMapping(value = {"/", "/index"})
    public String indexPage() {
        return "index";
    }


    @GetMapping("/reset_code_page")
    public String resetCodePage() {
        return "staff/resetCode";
    }


    @ResponseBody
    @GetMapping("/find_staff_by_id")
    /**
     *
     * @description: 按照工号查询
     * @param: [jobId]
     * @return: com.xiao.boot.bean.po.R<com.xiao.boot.bean.po.Staff>
     * @date: 2021/8/16
     */

    public R<Staff> findStaffById(@RequestParam("jobId") String jobId) {
        Staff staff = staffService.findStaffById(Integer.valueOf(jobId));
        return R.ok(staff);
    }

    @GetMapping("/reset_code")
    @ResponseBody
    /**
     *
     * @description: 重置密码
     * @param: [jobId]
     * @return: com.xiao.boot.bean.po.R<java.lang.Integer>
     * @date: 2021/8/16
     */

    public R<Integer> resetCode(@RequestParam("jobId") String jobId) {
        return R.ok(staffService.resetCode(Integer.parseInt(jobId)), "更改成功");
    }

    //返回添加员工页面
    @GetMapping("/add_staff_page")
    public String addStaffPage() {
        return "staff/addStaff";
    }

    // 添加员工
    @PostMapping("/add_staff")
    @ResponseBody
    /**
     *
     * @description: 添加员工信息
     * @param: [staff]
     * @return: com.xiao.boot.bean.po.R<java.lang.Integer>
     * @date: 2021/8/16
     */

    public R<Integer> addStaff(AddStaff staff) {
        Integer row = staffService.addStaff(staff);
        if (row == 0) {
            return R.failed();
        }
        return R.ok(row, "添加成功");
    }

    @GetMapping("/edit-staff-page")
    @ResponseBody
    /**
     *
     * @description: edit staff 
     * @param: staff
     * @return: com.xiao.boot.bean.po.R<java.util.List < com.xiao.boot.bean.po.Staff>>
     * @date: 2021/8/16
     */

    public R editStaff(Staff staff) {
        HashSet<Integer>hashSet=new HashSet<>();

        hashSet.addAll( staffService.findAllStaffID());
        if (!hashSet.contains(staff.getJobId()))
        {
            return R.failed();
        }
       int num= staffService.updateStaff(staff);
        return R.ok(num);

    }

    // 返回修改页面
    @GetMapping("/update_staff_page")
    public String update_staff_page(@RequestParam("id") Integer jobId, Model model) {
        Staff staff = staffService.findStaffById(jobId);
        model.addAttribute("staff", staff);
        return "staff/updateStaffPage";
    }

    // 修改员工信息姓名、地址、电话、密码可以修改
    @GetMapping("/update_staff")
    @ResponseBody
    /**
     *
     * @description: 更新员工信息
     * @param: [staff]
     * @return: com.xiao.boot.bean.po.R<java.lang.Integer>
     * @date: 2021/8/16
     */

    public R<Integer> updateStaff(Staff staff) {
        Integer row = staffService.updateStaff(staff);
        if (row == 0)
        {return R.failed("update failed");}
        return R.ok(row);
    }

    // 删除员工
    @GetMapping("/delete_staff")
    @ResponseBody
    /**
     *
     * @description: 删除员工信息
     * @param: [jobId]
     * @return: com.xiao.boot.bean.po.R<java.lang.Integer>
     * @date: 2021/8/16
     */

    public R<Integer> delete_staff_page(@RequestParam("id") String jobId) {
        Integer row = staffService.deleteStaffById(Integer.parseInt(jobId));
        if (row == 0) return R.failed();
        return R.ok(row, "删除成功");
    }

    // 角色管理页面
    @GetMapping("/role_manage_page")
    public R<List<Staff>> role_manage_page() {
        List<Staff> allStaff = staffService.findAllStaff();
        if (allStaff.size() == 0) {
            return R.failed();
        }
        return R.ok(allStaff);
    }

    // 修改角色
    @GetMapping("/update_staff_role")
    /**
     *
     * @description: 修改员工信息
     * @param: [jobId, role]
     * @return: java.lang.String
     * @date: 2021/8/16
     */

    public String update_staff_role(@RequestParam("jobId") String jobId,
                                    @RequestParam("role") String role) {
        Staff staff = staffService.findStaffById(Integer.parseInt(jobId));
        Integer roleId = Integer.parseInt(role.split("/?")[0]);
        if (roleId == 2) {
            Staff manager = staffService.findManagerByDep(staff.getDepartmentId());
            if (manager != null) {
                return "redirect:/role_manage_page";
            }
        }
        staff.setRole(roleId);
        return "redirect:/role_manage_page";
    }

    // 查看个人信息
    @GetMapping("/self_info_page")
    /**
     *
     * @description: 查看个人信息
     * @param: [jobId]
     * @return: com.xiao.boot.bean.po.R<com.xiao.boot.bean.po.Staff>
     * @date: 2021/8/16
     */

    public R<Staff> self_info_page(Integer jobId) {

        Staff staff = staffService.findStaffById(jobId);
        if (staff == null)
            return R.failed();
        return R.ok(staff);
    }

    // 修改个人信息
    @GetMapping("/update_selfInfo_page")
    @ResponseBody
    /**
     *
     * @description: 修改个人信息
     * @param: [jobId]
     * @return: com.xiao.boot.bean.po.R<com.xiao.boot.bean.po.Staff>
     * @date: 2021/8/16
     */

    public R<Staff> update_selfInfo_page(@RequestParam("id") Integer jobId) {
        Staff staff = staffService.findStaffById(jobId);
        if (staff == null) return R.failed();
        return R.ok(staff);
    }


    @ResponseBody
    @GetMapping("/check_code")
    /**
     *
     * @description: 根据jobId 和 password来查找员工
     * @param: [jobId]
     * @return: com.xiao.boot.bean.po.R<com.xiao.boot.bean.po.Staff>
     * @date: 2021/8/16
     */

    public R<Staff> findStaffByLoginElement(Integer jobId) {
        return R.ok(staffService.findStaffById(jobId));
    }

    @PostMapping("/update_code")
    public String updateCode(@RequestParam("newPassword") String newPassword,
                             HttpSession session,
                             Model model) {
        Integer jobId = ((Staff) session.getAttribute("staff")).getJobId();
        Staff staff = new Staff();

        model.addAttribute("msg", "修改成功");
        return "redirect:/welcome";
    }

    // 考勤页面
    @GetMapping("/kaoqing")
    public String kaoQing() {
        return "attendance/kaoqing";
    }

    /**
     * @description: 通过部门号来查询员工
     * @param: departmentId
     * @return: R
     * @date: 2021/8/12
     */


    @GetMapping("/find_dep_staff")
    public R<List<Staff>> findStaffByDep(String departmentId) {
        List<Staff> allStaff = staffService.findStaffByDep(departmentId);
        if (allStaff.size() == 0) {

            return R.failed();
        }
        return R.ok(allStaff);
    }

    @GetMapping("/findStaffByStatus")
    @ResponseBody
    /**
     *
     * @description: 0是离职的了，1是没离职的
     * @param: [status]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/17
     */

    public R findStaffByStatus(Integer status)
    {
    List<Staff> list=staffService.findStaffByStatus(status);
    return R.ok(list);
    }

    @GetMapping("/findStaffByName")
    @ResponseBody
/**
 *
 * @description: 根据name查询员工
 * @param: [name]
 * @return: com.xiao.boot.bean.po.R
 * @date: 2021/8/18
 */

    public R findStaffByName(String  name)
    {
        if (StringUtils.isEmpty(name))
        {
            return R.failed("用户名不能为空！");
        }
        if (staffService.findStaffByName(name)==null)
        {
            return R.failed("查无此人");
        }
        return R.ok(staffService.findStaffByName(name));
    }
    @GetMapping("/findPageStaff")
    @ResponseBody
    public R findPage()
    {
        return  staffService.findPageStaff();
    }

}
