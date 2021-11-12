package com.safety.dto;
import java.util.List;
public class FireStationDTOHolder {
    List<FireStationDTO> fireStationDTOSHolder;
    String ageSummaryForChildren;
    String ageSummaryForAdult;
    public List<FireStationDTO> getFireStationDTOSHolder() {
        return fireStationDTOSHolder;
    }
    public void setFireStationDTOSHolder(List<FireStationDTO> fireStationDTOSHolder) {
        this.fireStationDTOSHolder = fireStationDTOSHolder;
    }
    public String getAgeSummaryForChildren() {
        return ageSummaryForChildren;
    }
    public void setAgeSummaryForChildren(String ageSummaryForChildren) {
        this.ageSummaryForChildren = ageSummaryForChildren;
    }
    public String getAgeSummaryForAdult() {
        return ageSummaryForAdult;
    }
    public void setAgeSummaryForAdult(String ageSummaryForAdult) {
        this.ageSummaryForAdult = ageSummaryForAdult;
    }
    @Override
    public String toString() {
        return "FireStationDTOHolder{" +
                "fireStationDTOSHolder=" + fireStationDTOSHolder +
                ", ageSummaryForChildren='" + ageSummaryForChildren + '\'' +
                ", ageSummaryForAdult='" + ageSummaryForAdult + '\'' +
                '}';
    }
}
