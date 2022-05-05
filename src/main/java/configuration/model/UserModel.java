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
   private Date birthDate;

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
      return  "title " + socialTitle +
              ", firstName: " + firstName + '\'' +
              ", lastName: " + lastName + '\'' +
              ", email: " + email + '\'' +
              ", password: " + password + '\'' +
              ", birthDate: " + birthDate +
              ", receiveOffersConsent: " + receiveOffersConsent +
              ", dataPrivacyConsent: " + dataPrivacyConsent +
              ", newsletterConsent: " + newsletterConsent +
              ", generalConditionsConsent: " + generalConditionsConsent;
   }
}
