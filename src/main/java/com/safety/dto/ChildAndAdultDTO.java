package com.safety.dto;
import java.util.List;
public class ChildAndAdultDTO {
    List<ChildAlertDTO> childrenAtAddress;
    List<String>adultAtAddress;
    public List<ChildAlertDTO> getChildrenAtAddress() {
        return childrenAtAddress;
    }
    public void setChildrenAtAddress(List<ChildAlertDTO> childrenAtAddress) {
        this.childrenAtAddress = childrenAtAddress;
    }
    public List<String> getAdultAtAddress() {
        return adultAtAddress;
    }
    public void setAdultAtAddress(List<String> adultAtAddress) {
        this.adultAtAddress = adultAtAddress;
    }

    @Override
    public String toString() {
        return "ChildAndAdultDTO{" +
                "childrenAtAddress=" + childrenAtAddress +
                ", adultAtAddress=" + adultAtAddress +
                '}';
    }
}
