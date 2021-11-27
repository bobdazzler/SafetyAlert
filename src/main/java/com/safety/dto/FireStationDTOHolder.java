package com.safety.dto;
import java.util.List;
public class FireStationDTOHolder {
    List<FireStationDTO> fireStationDTOSHolder;
    String ageSummaryForChildren;
    String ageSummaryForAdult;

    public FireStationDTOHolder(List<FireStationDTO> fireStationDTOSHolder, String ageSummaryForChildren, String ageSummaryForAdult) {
        this.fireStationDTOSHolder = fireStationDTOSHolder;
        this.ageSummaryForChildren = ageSummaryForChildren;
        this.ageSummaryForAdult = ageSummaryForAdult;
    }

    public FireStationDTOHolder() {

    }

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
   
}
