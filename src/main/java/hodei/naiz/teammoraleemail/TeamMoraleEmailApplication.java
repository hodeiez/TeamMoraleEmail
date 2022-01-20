package hodei.naiz.teammoraleemail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;

@SpringBootApplication(exclude = {
        RedisRepositoriesAutoConfiguration.class})
public class TeamMoraleEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamMoraleEmailApplication.class, args);
    }

}
