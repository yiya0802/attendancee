package com.xiao.boot.controller;
import java.util.HashSet;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.xiao.boot.service.SalaryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import com.xiao.boot.bean.dto.AddStaff;
import com.xiao.boot.bean.po.R;
import com.xiao.boot.bean.po.Staff;
import com.xiao.boot.service.StaffService;
import org.thymeleaf.util.StringUtils;
import org.thymeleaf.util.Validate;

@Controller
@RequestMapping("staff")
public class StaffController {
    @Autowired
    StaffService staffService;
    @Autowired
    SalaryService salaryService;

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
            return R.failed("用户名不存在");
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
    @GetMapping("/add_staff")
    @ResponseBody
    /**
     *
     * @description: 添加员工信息
     * @param: [staff]
     * @return: com.xiao.boot.bean.po.R<java.lang.Integer>
     * @date: 2021/8/16
     */

    public R<Integer> addStaff(AddStaff staff,Integer salary) {
        if (staff.getName()==null)
        {
            return R.failed("姓名不能为空");
        }
        if (staff.getSex()==null)
        {
            return R.failed("性别不能为空");
        }
        if (staff.getMobile()==null)
        {
            return R.failed("手机号不能为空");
        }
        if (staff.getPassword()==null)
        {
            return R.failed("密码不能为空");
        }
        if (staff.getPost()==null)
        {
            return R.failed("岗位不能为空");
        }
        if (staff.getRole()==null)
        {
            return R.failed("角色不能为空");
        }
        if (salary==null)
        {
            return R.failed("请输入员工的薪资");
        }
        Integer row = staffService.addStaff(staff);
        Integer row2=salaryService.addStaffSalary(staff,salary);
        if (row == 0 || row2==0) {
            return R.failed("添加失败,用户id已经存在");
        }
        return R.ok(row, "添加成功");
    }
    @GetMapping("/edit_staff")
    @ResponseBody
    /**
     *
     * @description: edit staff
     * @param: [staff, salary]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/20
     */

    public R editStaff(AddStaff staff,Integer salary)
    {
       Staff staff1=new Staff();
       BeanUtils.copyProperties(staff,staff1);
       return staffService.editStaff(staff1,salary);
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
    if (CollectionUtils.isEmpty(list))
    {
        return R.failed("无数据");
    }
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
            return R.ok(staffService.findStaffListByName(name),"两人名字重复");
        }
        return R.ok(staffService.findStaffByName(name));
    }
    @GetMapping("/findPageStaff")
    @ResponseBody
    /**
     *
     * @description: name==null，按照status查询，status=null，按照name查询
     * @param: [name, status]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/19
     */

    public R findPage(String name,Integer status,Long current,Long size)
    {
        if (current==null || size==null)
        {
            return R.failed("current 和 size不能为空");
        }
        //只输入了current和size
        if (name==null&&status==null)
        {
            return  staffService.findPageStaff(current,size);
        }
        //按照status查询
        if (name==null)
        {
         if (status!=0 && status!=1)
         {
             return R.failed("status只能为0和1");
         }
         return staffService.findPageStaffByStatus(status,current,size);
        }
        //按照name查询
        if (status==null)
        {
            if (staffService.findStaffByName(name)==null)
            {
                return CollectionUtils.isEmpty(staffService.findStaffListByName(name))?(R.failed("用户名不存在")):(R.ok(staffService.findPageStaffByName(name,current,size),"两人名字重复"));
            }
            return R.ok(staffService.findPageStaffByName(name,current,size));
        }
        return R.failed("不能同时查询name和status");

    }
    @GetMapping("/updateStatus")
    @ResponseBody
    /**
     *
     * @description: 更新用户状态
     * @param: [jobId, status]
     * @return: com.xiao.boot.bean.po.R
     * @date: 2021/8/19
     */

    public R updateStatus(Integer jobId,Integer status)
    {
        if (status!=0 && status!=1)
        {
            return R.failed("只能为0和1");
        }
        Staff staff=staffService.findStaffById(jobId);
        if (staff==null)
        {
            return R.failed("用户不存在");
        }
        staff.setStatus(status);
        return staffService.updateStaffStatus(staff)==0?R.failed("更新状态失败"):R.ok(staffService.updateStaffStatus(staff),"更新状态成功");
    }

}
