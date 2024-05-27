package demo.venu.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getEmployee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository EmployeeRepository;

    @GetMapping("/{name}")
    public Employee findByName(@PathVariable String name) {
        return EmployeeRepository.findByName(name);
    }
}
