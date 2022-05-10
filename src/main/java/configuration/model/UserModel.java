package configuration.model;

import lombok.Getter;
import lombok.Setter;

public class UserModel {

    @Setter
    @Getter
    private Enum socialTitle;

    @Setter
    @Getter
    private String firstName;

    @Setter
    @Getter
    private String lastName;

    @Setter
    @Getter
    private String email;

    @Setter
    @Getter
    private String password;

    @Setter
    @Getter
    private String address;

    @Setter
    @Getter
    private String city;

    @Setter
    @Getter
    private String postalCode;

    @Setter
    @Getter
    private String birthDate;

    @Setter
    @Getter
    private boolean receiveOffersConsent;

    @Setter
    @Getter
    private boolean dataPrivacyConsent;

    @Setter
    @Getter
    private boolean newsletterConsent;

    @Setter
    @Getter
    private boolean generalConditionsConsent;

    @Override
    public String toString() {
        return "UserModel{" +
                "socialTitle=" + socialTitle +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", postalCode=" + postalCode +
                ", birthDate=" + birthDate +
                ", receiveOffersConsent=" + receiveOffersConsent +
                ", dataPrivacyConsent=" + dataPrivacyConsent +
                ", newsletterConsent=" + newsletterConsent +
                ", generalConditionsConsent=" + generalConditionsConsent +
                '}';
    }
}
