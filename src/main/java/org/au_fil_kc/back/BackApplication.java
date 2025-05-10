package org.au_fil_kc.back;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;

@SpringBootApplication
public class BackApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackApplication.class, args);
    }

    @PostConstruct
    public void checkMigrationFile() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("db/migration/V1__init_db.sql");
        if (is != null) {
            System.out.println(">>> Fichier de migration trouvé !");
        } else {
            System.out.println(">>> Fichier de migration NON trouvé !");
        }
    }

}
