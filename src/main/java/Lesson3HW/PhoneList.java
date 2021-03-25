package Lesson3HW;

import java.util.Locale;
import java.util.Objects;

public class PhoneList implements Comparable{
    String familyName;
    String phoneNumber;

    public PhoneList(String familyName, String phoneNumber)  {
        this.familyName = familyName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(Object o) {
        PhoneList ph = (PhoneList) o;
        boolean check = (this.familyName == ph.familyName) && (this.phoneNumber == ph.phoneNumber);
        if (check == true) return 0;
        return -1;
    }

    /*@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PhoneList ph = (PhoneList) obj;
        ((PhoneList) obj).familyName = ((PhoneList) obj).familyName.toLowerCase(Locale.ROOT);
        this.familyName = this.familyName.toLowerCase(Locale.ROOT);
        return familyName == ph.familyName && phoneNumber == ph.phoneNumber;
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(familyName,phoneNumber);
    }

    @Override
    public String toString() {
        return "PhoneList{" +
                "familyName='" + familyName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}