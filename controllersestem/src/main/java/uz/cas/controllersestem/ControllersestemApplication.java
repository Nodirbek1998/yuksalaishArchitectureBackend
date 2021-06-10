package uz.cas.controllersestem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import uz.cas.controllersestem.entity.Project;
import uz.cas.controllersestem.entity.enums.ProjectStatus;
import uz.cas.controllersestem.repository.ProjectRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ControllersestemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControllersestemApplication.class, args);

    }
    @Autowired
    private ProjectRepository projectRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void timeFinished() {
        List<Project> byProjectStatus = projectRepository.findByProjectStatus(ProjectStatus.active);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String date = simpleDateFormat.format(new Date());
        if (byProjectStatus.size() > 0) {
            for (Project projectStatus : byProjectStatus) {
                if (projectStatus.getProjectFinished().toString().equals(date)){
                    projectStatus.setProjectStatus(ProjectStatus.finished);
                    projectRepository.save(projectStatus);
                }
            }
        }
    }

}
