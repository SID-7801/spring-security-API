package com.example.relationshipJPA.Service.ServiceImpl;

import com.example.relationshipJPA.Entity.Maintenance;
import com.example.relationshipJPA.Entity.Member;
import com.example.relationshipJPA.Entity.Month;
import com.example.relationshipJPA.Entity.Status;
import com.example.relationshipJPA.Repository.MaintenanceRepository;
import com.example.relationshipJPA.Repository.MemberRepository;
import com.example.relationshipJPA.Service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Component
public class MaintenanceServiceImpl implements MaintenanceService {
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Autowired
    private MemberRepository memberRepository;

    // schedule for 1st date of every month at 10 o clock
    // format : seconds minutes hours date month dayOfWeek year
    //    @Scheduled(cron = "0 0 10 1 * ?")
    @Override
    @Scheduled(fixedRate = 5000)
    public Maintenance generateMaintenance() {

        LocalDate generateDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        LocalDate dueDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1 + 19);

        Maintenance maintenance = new Maintenance();

        maintenance.setAmount(1000.00);
        maintenance.setMonth(LocalDate.now().getMonth());
        maintenance.setPenalties(0.00);
        maintenance.setDueDate(dueDate);
        maintenance.setDueAmount(1000);
        maintenance.setPaidDate(null);
        maintenance.setStatus(Status.UNPAID);

        return maintenance;
    }

    // method that returns all available member IDs from database
    private List<Long> getAllMemberIds() {
        return memberRepository.getAllId();
    }

    public Maintenance checkMaintenanceValue(String email) {
        Member member = memberRepository.findByEmail(email).get();
        return null;
    }

}
