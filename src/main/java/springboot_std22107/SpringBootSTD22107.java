package springboot_std22107;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "controller",
        "springboot_std22107",
        "model",
        "repository",
        "Service",
        "connectionDB"
})
public class SpringBootSTD22107 {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootSTD22107.class, args);
    }
}
