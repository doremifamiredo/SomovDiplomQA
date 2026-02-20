package ru.iteco.fmhandroid.ui.data;

import com.github.javafaker.Faker;

public class CredHelper {
    private final static String validLogin = "login2";
    private final static String validPass = "password2";
    public final static Faker faker = new Faker();


    public static class Credentials {
        public String login;
        public String password;

        private Credentials(CredBuilder builder) {
            this.login = builder.login;
            this.password = builder.pass;
        }

        private static class CredBuilder {
            private String login;
            private String pass;

            public CredBuilder setLogin(String login) {
                this.login = login;
                return this;
            }

            public CredBuilder setPass(String pass) {
                this.pass = pass;
                return this;
            }

            public Credentials build() {
                return new Credentials(this);
            }
        }

    }

    public static Credentials getValidCred() {
        return new Credentials.CredBuilder()
                .setLogin(validLogin)
                .setPass(validPass)
                .build();
    }

    public static Credentials getInvalidCred() {
        return new Credentials.CredBuilder()
                .setLogin(faker.name().firstName())
                .setPass(faker.internet().uuid())
                .build();
    }
}
