package configuration.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

public class UserModel {

   @Setter
   @Getter
   private Enum socialTitle;

   @Setter
   @Getter
   private String firstName;

   @Setter
   private String lastName;

   @Setter
   private String email;

   @Setter
   private String password;

   @Setter
   private Date birthDate;

   @Setter
   private boolean receiveOffersConsent;

   @Setter
   private boolean dataPrivacyConsent;

   @Setter
   private boolean newsletterConsent;

   @Setter
   private boolean generalConditionsConsent;

   @Override
   public String toString() {
      return "UserModel{" +
              "socialTitle=" + socialTitle +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              ", birthDate=" + birthDate +
              ", receiveOffersConsent=" + receiveOffersConsent +
              ", dataPrivacyConsent=" + dataPrivacyConsent +
              ", newsletterConsent=" + newsletterConsent +
              ", generalConditionsConsent=" + generalConditionsConsent +
              '}';
   }
}
