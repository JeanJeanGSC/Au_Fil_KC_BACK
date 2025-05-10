package org.au_fil_kc.back;

import org.flywaydb.core.Flyway;

public class FlywayTest {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://aws-0-eu-west-3.pooler.supabase.com:6543/postgres";
        String user = "postgres.uxeeyunyauhrdnkckrej";
        String password = "ak?C9N$TCBs?";

        Flyway flyway = Flyway.configure()
                .dataSource(url, user, password)
                .locations("classpath:db/migration")
                .load();

        flyway.migrate();
    }
}
