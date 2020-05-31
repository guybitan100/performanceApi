package com.glassboxdigital.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("APPID")
    @Expose
    private List<String> aPPID = null;
    @SerializedName("AVAPROBCOUNT")
    @Expose
    private List<Integer> aVAPROBCOUNT = null;
    @SerializedName("BEACONCOUNT")
    @Expose
    private List<Integer> bEACONCOUNT = null;
    @SerializedName("UABROWSER_")
    @Expose
    private List<String> uABROWSER = null;
    @SerializedName("UABROWSERGROUP")
    @Expose
    private List<String> uABROWSERGROUP = null;
    @SerializedName("UABROWSERVERSION")
    @Expose
    private List<String> uABROWSERVERSION = null;
    @SerializedName("GEOCITY")
    @Expose
    private List<String> gEOCITY = null;
    @SerializedName("IPA")
    @Expose
    private List<String> iPA = null;
    @SerializedName("GEOCOUNTRY")
    @Expose
    private List<String> gEOCOUNTRY = null;
    @SerializedName("UADEVICETYPE")
    @Expose
    private List<String> uADEVICETYPE = null;
    @SerializedName("DOMPAGES")
    @Expose
    private List<Integer> dOMPAGES = null;
    @SerializedName("EUID")
    @Expose
    private List<Integer> eUID = null;
    @SerializedName("HITCOUNT")
    @Expose
    private List<Integer> hITCOUNT = null;
    @SerializedName("GEOISP")
    @Expose
    private List<String> gEOISP = null;
    @SerializedName("UAOS")
    @Expose
    private List<String> uAOS = null;
    @SerializedName("UAOSVENDOR")
    @Expose
    private List<String> uAOSVENDOR = null;
    @SerializedName("PACKETIP")
    @Expose
    private List<String> pACKETIP = null;
    @SerializedName("PAGEVIEWS")
    @Expose
    private List<Integer> pAGEVIEWS = null;
    @SerializedName("REFHOST")
    @Expose
    private List<String> rEFHOST = null;
    @SerializedName("GEOREGION")
    @Expose
    private List<String> gEOREGION = null;
    @SerializedName("RESTOTALLEN")
    @Expose
    private List<Double> rESTOTALLEN = null;
    @SerializedName("SERVERIP")
    @Expose
    private List<String> sERVERIP = null;
    @SerializedName("SERVERTIME")
    @Expose
    private List<Double> sERVERTIME = null;
    @SerializedName("SESSIONDURATION")
    @Expose
    private List<Integer> sESSIONDURATION = null;
    @SerializedName("SESSIONTS")
    @Expose
    private List<Integer> sESSIONTS = null;
    @SerializedName("SLOWEVENTS")
    @Expose
    private List<Integer> sLOWEVENTS = null;
    @SerializedName("SLOWSERVEREVENTS")
    @Expose
    private List<Integer> sLOWSERVEREVENTS = null;
    @SerializedName("SESSIONSTRUGGLESCORE")
    @Expose
    private List<Double> sESSIONSTRUGGLESCORE = null;
    @SerializedName("TOTALTIME")
    @Expose
    private List<Double> tOTALTIME = null;
    @SerializedName("VERYSLOWEVENTS")
    @Expose
    private List<Integer> vERYSLOWEVENTS = null;
    @SerializedName("VERYSLOWSERVEREVENTS")
    @Expose
    private List<Integer> vERYSLOWSERVEREVENTS = null;
    @SerializedName("SESSIONGUID")
    @Expose
    private List<String> sESSIONGUID = null;

    public List<String> getAPPID() {
        return aPPID;
    }

    public void setAPPID(List<String> aPPID) {
        this.aPPID = aPPID;
    }

    public List<Integer> getAVAPROBCOUNT() {
        return aVAPROBCOUNT;
    }

    public void setAVAPROBCOUNT(List<Integer> aVAPROBCOUNT) {
        this.aVAPROBCOUNT = aVAPROBCOUNT;
    }

    public List<Integer> getBEACONCOUNT() {
        return bEACONCOUNT;
    }

    public void setBEACONCOUNT(List<Integer> bEACONCOUNT) {
        this.bEACONCOUNT = bEACONCOUNT;
    }

    public List<String> getUABROWSER() {
        return uABROWSER;
    }

    public void setUABROWSER(List<String> uABROWSER) {
        this.uABROWSER = uABROWSER;
    }

    public List<String> getUABROWSERGROUP() {
        return uABROWSERGROUP;
    }

    public void setUABROWSERGROUP(List<String> uABROWSERGROUP) {
        this.uABROWSERGROUP = uABROWSERGROUP;
    }

    public List<String> getUABROWSERVERSION() {
        return uABROWSERVERSION;
    }

    public void setUABROWSERVERSION(List<String> uABROWSERVERSION) {
        this.uABROWSERVERSION = uABROWSERVERSION;
    }

    public List<String> getGEOCITY() {
        return gEOCITY;
    }

    public void setGEOCITY(List<String> gEOCITY) {
        this.gEOCITY = gEOCITY;
    }

    public List<String> getIPA() {
        return iPA;
    }

    public void setIPA(List<String> iPA) {
        this.iPA = iPA;
    }

    public List<String> getGEOCOUNTRY() {
        return gEOCOUNTRY;
    }

    public void setGEOCOUNTRY(List<String> gEOCOUNTRY) {
        this.gEOCOUNTRY = gEOCOUNTRY;
    }

    public List<String> getUADEVICETYPE() {
        return uADEVICETYPE;
    }

    public void setUADEVICETYPE(List<String> uADEVICETYPE) {
        this.uADEVICETYPE = uADEVICETYPE;
    }

    public List<Integer> getDOMPAGES() {
        return dOMPAGES;
    }

    public void setDOMPAGES(List<Integer> dOMPAGES) {
        this.dOMPAGES = dOMPAGES;
    }

    public List<Integer> getEUID() {
        return eUID;
    }

    public void setEUID(List<Integer> eUID) {
        this.eUID = eUID;
    }

    public List<Integer> getHITCOUNT() {
        return hITCOUNT;
    }

    public void setHITCOUNT(List<Integer> hITCOUNT) {
        this.hITCOUNT = hITCOUNT;
    }

    public List<String> getGEOISP() {
        return gEOISP;
    }

    public void setGEOISP(List<String> gEOISP) {
        this.gEOISP = gEOISP;
    }

    public List<String> getUAOS() {
        return uAOS;
    }

    public void setUAOS(List<String> uAOS) {
        this.uAOS = uAOS;
    }

    public List<String> getUAOSVENDOR() {
        return uAOSVENDOR;
    }

    public void setUAOSVENDOR(List<String> uAOSVENDOR) {
        this.uAOSVENDOR = uAOSVENDOR;
    }

    public List<String> getPACKETIP() {
        return pACKETIP;
    }

    public void setPACKETIP(List<String> pACKETIP) {
        this.pACKETIP = pACKETIP;
    }

    public List<Integer> getPAGEVIEWS() {
        return pAGEVIEWS;
    }

    public void setPAGEVIEWS(List<Integer> pAGEVIEWS) {
        this.pAGEVIEWS = pAGEVIEWS;
    }

    public List<String> getREFHOST() {
        return rEFHOST;
    }

    public void setREFHOST(List<String> rEFHOST) {
        this.rEFHOST = rEFHOST;
    }

    public List<String> getGEOREGION() {
        return gEOREGION;
    }

    public void setGEOREGION(List<String> gEOREGION) {
        this.gEOREGION = gEOREGION;
    }

    public List<Double> getRESTOTALLEN() {
        return rESTOTALLEN;
    }

    public void setRESTOTALLEN(List<Double> rESTOTALLEN) {
        this.rESTOTALLEN = rESTOTALLEN;
    }

    public List<String> getSERVERIP() {
        return sERVERIP;
    }

    public void setSERVERIP(List<String> sERVERIP) {
        this.sERVERIP = sERVERIP;
    }

    public List<Double> getSERVERTIME() {
        return sERVERTIME;
    }

    public void setSERVERTIME(List<Double> sERVERTIME) {
        this.sERVERTIME = sERVERTIME;
    }

    public List<Integer> getSESSIONDURATION() {
        return sESSIONDURATION;
    }

    public void setSESSIONDURATION(List<Integer> sESSIONDURATION) {
        this.sESSIONDURATION = sESSIONDURATION;
    }

    public List<Integer> getSESSIONTS() {
        return sESSIONTS;
    }

    public void setSESSIONTS(List<Integer> sESSIONTS) {
        this.sESSIONTS = sESSIONTS;
    }

    public List<Integer> getSLOWEVENTS() {
        return sLOWEVENTS;
    }

    public void setSLOWEVENTS(List<Integer> sLOWEVENTS) {
        this.sLOWEVENTS = sLOWEVENTS;
    }

    public List<Integer> getSLOWSERVEREVENTS() {
        return sLOWSERVEREVENTS;
    }

    public void setSLOWSERVEREVENTS(List<Integer> sLOWSERVEREVENTS) {
        this.sLOWSERVEREVENTS = sLOWSERVEREVENTS;
    }

    public List<Double> getSESSIONSTRUGGLESCORE() {
        return sESSIONSTRUGGLESCORE;
    }

    public void setSESSIONSTRUGGLESCORE(List<Double> sESSIONSTRUGGLESCORE) {
        this.sESSIONSTRUGGLESCORE = sESSIONSTRUGGLESCORE;
    }

    public List<Double> getTOTALTIME() {
        return tOTALTIME;
    }

    public void setTOTALTIME(List<Double> tOTALTIME) {
        this.tOTALTIME = tOTALTIME;
    }

    public List<Integer> getVERYSLOWEVENTS() {
        return vERYSLOWEVENTS;
    }

    public void setVERYSLOWEVENTS(List<Integer> vERYSLOWEVENTS) {
        this.vERYSLOWEVENTS = vERYSLOWEVENTS;
    }

    public List<Integer> getVERYSLOWSERVEREVENTS() {
        return vERYSLOWSERVEREVENTS;
    }

    public void setVERYSLOWSERVEREVENTS(List<Integer> vERYSLOWSERVEREVENTS) {
        this.vERYSLOWSERVEREVENTS = vERYSLOWSERVEREVENTS;
    }

    public List<String> getSESSIONGUID() {
        return sESSIONGUID;
    }

    public void setSESSIONGUID(List<String> sESSIONGUID) {
        this.sESSIONGUID = sESSIONGUID;
    }

}
