package configuration.handler;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import configuration.model.SocialTitle;
import configuration.model.UserModel;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class UserFactory {


    public static UserModel getRandomUser() {

        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("pl"), new RandomService());
        Faker faker = new Faker();

        UserModel userModel = UserBuilder.builder()
                .socialTitle(SocialTitle.values()[faker.random().nextInt(1, SocialTitle.values().length)-1])
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(fakeValuesService.bothify("????##@gmail.com"))
                .password(fakeValuesService.letterify("???????"))
                .birthDate(faker.date().birthday())
                .receiveOffersConsent(faker.random().nextBoolean())
                .dataPrivacyConsent(faker.random().nextBoolean())
                .newsletterConsent(faker.random().nextBoolean())
                .generalConditionsConsent(faker.random().nextBoolean())
                .build();
        return userModel;
    }

    public static UserModel getAlreadyRegisteredUser() {
        return new UserModel();
    }

    public static final class UserBuilder {
        private Enum socialTitle;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private Date birthDate;
        private boolean receiveOffersConsent;
        private boolean dataPrivacyConsent;
        private boolean newsletterConsent;
        private boolean generalConditionsConsent;

        public static UserBuilder builder() {
            return new UserBuilder();
        }

        public UserBuilder socialTitle(Enum socialTitle) {
            this.socialTitle = socialTitle;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder birthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }


        public UserBuilder receiveOffersConsent(boolean receiveOffersConsent ) {
            this.receiveOffersConsent = receiveOffersConsent;
            return this;
        }

        public UserBuilder dataPrivacyConsent(boolean dataPrivacyConsent ) {
            this.dataPrivacyConsent = dataPrivacyConsent;
            return this;
        }

        public UserBuilder newsletterConsent(boolean newsletterConsent ) {
            this.newsletterConsent = newsletterConsent;
            return this;
        }

        public UserBuilder generalConditionsConsent(boolean generalConditionsConsent ) {
            this.generalConditionsConsent = generalConditionsConsent;
            return this;
        }


        public UserModel build() {

            UserModel user = new UserModel();

            user.setSocialTitle(this.socialTitle);
            user.setFirstName(this.firstName);
            user.setLastName(this.lastName);
            user.setEmail(this.email);
            user.setPassword(this.password);
            user.setBirthDate(this.birthDate);
            user.setReceiveOffersConsent(this.receiveOffersConsent);
            user.setDataPrivacyConsent(this.dataPrivacyConsent);
            user.setNewsletterConsent(this.newsletterConsent);
            user.setGeneralConditionsConsent(this.generalConditionsConsent);
            return user;
        }
    }

}
