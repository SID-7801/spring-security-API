package com.example.relationshipJPA.service.serviceImpl;

import com.example.relationshipJPA.entity.Maintenance;
import com.example.relationshipJPA.entity.Member;
import com.example.relationshipJPA.entity.Status;
import com.example.relationshipJPA.repository.MaintenanceRepository;
import com.example.relationshipJPA.repository.MemberRepository;
import com.example.relationshipJPA.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
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
//    @Scheduled(fixedRate = 10000)
    public Maintenance generateMaintenance() {

        LocalDate generateDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        LocalDate dueDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1 + 19);

        List<Long> memId = getAllMemberIds();

        for (Long id : memId) {

            Maintenance maintenance2 = maintenanceRepository.findLastMonthMaintenance(LocalDate.now().getMonth(), id);
            Member member = memberRepository.findById(id).orElseThrow();

            Maintenance newMaintenance = getMaintenanceValue(maintenance2, id);

//            maintenance.setMember(member);
//            maintenance.setAmount(1000.00);
//            maintenance.setMonth(LocalDate.now().getMonth());
//            maintenance.setPenalties(0.00);
//            maintenance.setDueDate(dueDate);
//            maintenance.setDueAmount(1000);
//            maintenance.setStatus(Status.UNPAID);

            maintenanceRepository.save(newMaintenance);

        }
        return null;
    }

    // method that returns all available member IDs from database
    private List<Long> getAllMemberIds() {
        return memberRepository.getAllId();
    }

    public Maintenance getMaintenanceValue(Maintenance generatedMaintenance, Long id) {

        Member member = memberRepository.findById(id).orElseThrow();
//        List<Maintenance> oldMaintenance = maintenanceRepository.findByMemId(id);

        LocalDate generateDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);
        LocalDate dueDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1 + 19);

        double oldMaintenanceAmount;
        double generatedMaintenanceAmount;
        double penalties = 200;

        Maintenance newGeneratedMaintenance = new Maintenance();

        if (generatedMaintenance != null && generatedMaintenance.getStatus().equals(Status.UNPAID)) {

            // amount calculation
            oldMaintenanceAmount = generatedMaintenance.getAmount();
            generatedMaintenanceAmount = 1000;
            generatedMaintenanceAmount += oldMaintenanceAmount + penalties;

            newGeneratedMaintenance.setAmount(generatedMaintenanceAmount);
            newGeneratedMaintenance.setMember(member);
            newGeneratedMaintenance.setPenalties(0.0);
            newGeneratedMaintenance.setMonth(LocalDate.now().getMonth());
            newGeneratedMaintenance.setDueDate(dueDate);
            newGeneratedMaintenance.setStatus(Status.UNPAID);

            return newGeneratedMaintenance;

        } else {

            generatedMaintenance.setAmount(1000.0);
            generatedMaintenance.setMember(member);
            generatedMaintenance.setMonth(LocalDate.now().getMonth());
            generatedMaintenance.setPenalties(0.0);
            generatedMaintenance.setStatus(Status.UNPAID);
            generatedMaintenance.setDueDate(dueDate);

            return generatedMaintenance;

        }
    }

}