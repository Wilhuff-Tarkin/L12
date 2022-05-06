package configuration.handler;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import configuration.model.SocialTitle;
import configuration.model.UserModel;

import java.util.Date;
import java.util.Locale;

public class UserFactory {


    public static UserModel getRandomUser() {

        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("pl"), new RandomService());
        Faker faker = new Faker();

        UserModel userModel = UserBuilder.builder()
                .socialTitle(SocialTitle.values()[faker.random().nextInt(1, SocialTitle.values().length) - 1])
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(fakeValuesService.bothify("????##@gmail.com"))
                .address(faker.address().streetAddress())
                .city(faker.address().cityName())
                .postalCode(faker.numerify("##-###"))
                .password(fakeValuesService.bothify("?#?#?#?"))
                .birthDate(fakeBirthday(faker))
                .receiveOffersConsent(faker.random().nextBoolean())
                .dataPrivacyConsent(true)
                .newsletterConsent(faker.random().nextBoolean())
                .generalConditionsConsent(true)
                .build();
        return userModel;
    }

    private static String fakeBirthday(Faker faker) {
        StringBuilder birthday = new StringBuilder();
        birthday.append(faker.date().birthday().getMonth()).append("/").append(faker.random().nextInt(1, 28)).append("/")
                .append(faker.random().nextInt(1930, 2001));
        return String.valueOf(birthday);
    }

    public static UserModel getAlreadyRegisteredUser() {
        //todo
        return new UserModel();
    }

    public static final class UserBuilder {
        private Enum socialTitle;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String address;
        private String city;
        private String postalCode;
        private String birthDate;
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

        public UserBuilder address(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder city(String city) {
            this.city = city;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public UserBuilder birthDate(String birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserBuilder receiveOffersConsent(boolean receiveOffersConsent) {
            this.receiveOffersConsent = receiveOffersConsent;
            return this;
        }

        public UserBuilder dataPrivacyConsent(boolean dataPrivacyConsent) {
            this.dataPrivacyConsent = dataPrivacyConsent;
            return this;
        }

        public UserBuilder newsletterConsent(boolean newsletterConsent) {
            this.newsletterConsent = newsletterConsent;
            return this;
        }

        public UserBuilder generalConditionsConsent(boolean generalConditionsConsent) {
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
            user.setAddress(this.address);
            user.setCity(this.city);
            user.setPostalCode(this.postalCode);
            user.setBirthDate(this.birthDate);
            user.setReceiveOffersConsent(this.receiveOffersConsent);
            user.setDataPrivacyConsent(this.dataPrivacyConsent);
            user.setNewsletterConsent(this.newsletterConsent);
            user.setGeneralConditionsConsent(this.generalConditionsConsent);
            return user;
        }
    }
}
