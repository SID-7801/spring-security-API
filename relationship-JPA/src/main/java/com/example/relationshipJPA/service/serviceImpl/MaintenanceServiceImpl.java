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

            Maintenance maintenance = new Maintenance();
            Member member = memberRepository.findById(id).orElseThrow();

//            Maintenance newMaintenance = getMaintenanceValue(maintenance, id);

            maintenance.setMember(member);
            maintenance.setAmount(1000.00);
            maintenance.setMonth(LocalDate.now().getMonth());
            maintenance.setPenalties(0.00);
            maintenance.setDueDate(dueDate);
            maintenance.setDueAmount(1000);
            maintenance.setStatus(Status.UNPAID);

            maintenanceRepository.save(maintenance);

        }
        return null;
    }

    // method that returns all available member IDs from database
    private List<Long> getAllMemberIds() {
        return memberRepository.getAllId();
    }

/*
    public Maintenance getMaintenanceValue(Maintenance generatedMaintenance, Long id) {

        Member member = memberRepository.findById(id).orElseThrow();
        List<Maintenance> oldMaintenance = maintenanceRepository.findByMemId(id);

        double oldMaintenanceAmount;
        double generatedMaintenanceAmount;
        double maintenanceAmount;

        Maintenance newGeneratedMaintenance = null;
        for (Maintenance maintenance : oldMaintenance) {
            newGeneratedMaintenance = new Maintenance();
            if (maintenance != null && maintenance.getStatus().equals(Status.UNPAID)) {
                // amount calculation

                oldMaintenanceAmount = maintenance.getAmount();
                generatedMaintenanceAmount = generatedMaintenance.getAmount();
                generatedMaintenanceAmount += oldMaintenanceAmount;

                newGeneratedMaintenance.setAmount(generatedMaintenanceAmount);
                newGeneratedMaintenance.setMember(member);
                newGeneratedMaintenance.setMonth(LocalDate.now().getMonth());
                newGeneratedMaintenance.setPenalties(0.0);
                newGeneratedMaintenance.setDueDate(null);
                newGeneratedMaintenance.setStatus(Status.UNPAID);

                System.out.println(newGeneratedMaintenance);
            } else {
                maintenanceAmount = 1000.00;
            }
        }
        return newGeneratedMaintenance;
    }
*/

}