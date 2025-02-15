package com.attendance.controller;

import com.attendance.model.Attendance;
import com.attendance.model.Employee;
import com.attendance.repository.AttendanceRepository;
import com.attendance.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller  // Ensure it's @Controller (not @RestController)
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public String getAttendance(Model model) {
        List<Attendance> attendanceList = attendanceRepository.findAll();
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("attendanceList", attendanceList);
        model.addAttribute("employees", employees);
        return "attendance"; // Must match attendance.html
    }

    @PostMapping
    public String markAttendance(@ModelAttribute Attendance attendance) {
        attendanceRepository.save(attendance);
        return "redirect:/attendance";
    }
}
