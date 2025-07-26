package cz.ales17.auto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiResponseData {
    @JsonProperty("DatumPrvniRegistrace")
    private LocalDateTime datumPrvniRegistrace;

    @JsonProperty("DatumPrvniRegistraceVCr")
    private LocalDateTime datumPrvniRegistraceVCr;

    @JsonProperty("CisloTypovehoSchvaleni")
    private String cisloTypovehoSchvaleni;

    @JsonProperty("HomologaceEs")
    private String homologaceEs;

    @JsonProperty("VozidloDruh")
    private String vozidloDruh;

    @JsonProperty("VozidloDruh2")
    private String vozidloDruh2;

    @JsonProperty("Kategorie")
    private String kategorie;

    @JsonProperty("TovarniZnacka")
    private String tovarniZnacka;

    @JsonProperty("Typ")
    private String typ;

    @JsonProperty("Varianta")
    private String varianta;

    @JsonProperty("Verze")
    private String verze;

    @JsonProperty("VIN")
    private String vin;

    @JsonProperty("ObchodniOznaceni")
    private String obchodniOznaceni;

    @JsonProperty("VozidloVyrobce")
    private String vozidloVyrobce;

    @JsonProperty("MotorVyrobce")
    private String motorVyrobce;

    @JsonProperty("MotorTyp")
    private String motorTyp;

    @JsonProperty("MotorMaxVykon")
    private String motorMaxVykon;

    @JsonProperty("Palivo")
    private String palivo;

    @JsonProperty("MotorZdvihObjem")
    private int motorZdvihObjem;

    @JsonProperty("VozidloElektricke")
    private String vozidloElektricke;

    @JsonProperty("VozidloHybridni")
    private String vozidloHybridni;

    @JsonProperty("VozidloHybridniTrida")
    private String vozidloHybridniTrida;

    @JsonProperty("EmiseEHKOSNEHSES")
    private String emiseEHKOSNEHSES;

    @JsonProperty("EmisniUroven")
    private String emisniUroven;

    @JsonProperty("EmiseKSA")
    private int emiseKSA;

    @JsonProperty("EmiseCO2")
    private String emiseCO2;

    @JsonProperty("EmiseCO2Specificke")
    private String emiseCO2Specificke;

    @JsonProperty("EmiseSnizeniNedc")
    private String emiseSnizeniNedc;

    @JsonProperty("EmiseSnizeniWltp")
    private String emiseSnizeniWltp;

    @JsonProperty("SpotrebaMetodika")
    private String spotrebaMetodika;

    @JsonProperty("SpotrebaNa100Km")
    private String spotrebaNa100Km;

    @JsonProperty("Spotreba")
    private String spotreba;

    @JsonProperty("SpotrebaEl")
    private String spotrebaEl;

    @JsonProperty("DojezdZR")
    private String dojezdZR;

    @JsonProperty("VyrobceKaroserie")
    private String vyrobceKaroserie;

    @JsonProperty("KaroserieDruh")
    private String karoserieDruh;

    @JsonProperty("KaroserieVyrobniCislo")
    private String karoserieVyrobniCislo;

    @JsonProperty("VozidloKaroserieBarva")
    private String vozidloKaroserieBarva;

    @JsonProperty("VozidloKaroserieBarvaDoplnkova")
    private String vozidloKaroserieBarvaDoplnkova;

    @JsonProperty("VozidloKaroserieMist")
    private String vozidloKaroserieMist;

    @JsonProperty("Rozmery")
    private String rozmery;

    @JsonProperty("RozmeryRozvor")
    private String rozmeryRozvor;

    @JsonProperty("Rozchod")
    private String rozchod;

    @JsonProperty("HmotnostiProvozni")
    private int hmotnostiProvozni;

    @JsonProperty("HmotnostiPripPov")
    private String hmotnostiPripPov;

    @JsonProperty("HmotnostiPripPovN")
    private String hmotnostiPripPovN;

    @JsonProperty("HmotnostiPripPovBrzdenePV")
    private String hmotnostiPripPovBrzdenePV;

    @JsonProperty("HmotnostiPripPovNebrzdenePV")
    private String hmotnostiPripPovNebrzdenePV;

    @JsonProperty("HmotnostiPripPovJS")
    private String hmotnostiPripPovJS;

    @JsonProperty("HmotnostiTestWltp")
    private String hmotnostiTestWltp;

    @JsonProperty("HmotnostUzitecneZatizeniPrumer")
    private String hmotnostUzitecneZatizeniPrumer;

    @JsonProperty("VozidloSpojZarizNazev")
    private String vozidloSpojZarizNazev;

    @JsonProperty("NapravyPocetDruh")
    private String napravyPocetDruh;

    @JsonProperty("NapravyPneuRafky")
    private String napravyPneuRafky;

    @JsonProperty("HlukStojiciOtacky")
    private String hlukStojiciOtacky;

    @JsonProperty("HlukJizda")
    private double hlukJizda;

    @JsonProperty("NejvyssiRychlost")
    private int nejvyssiRychlost;

    @JsonProperty("PomerVykonHmotnost")
    private int pomerVykonHmotnost;

    @JsonProperty("InovativniTechnologie")
    private String inovativniTechnologie;

    @JsonProperty("StupenDokonceni")
    private String stupenDokonceni;

    @JsonProperty("FaktorOdchylkyDe")
    private String faktorOdchylkyDe;

    @JsonProperty("FaktorVerifikaceVf")
    private String faktorVerifikaceVf;

    @JsonProperty("VozidloUcel")
    private String vozidloUcel;

    @JsonProperty("DalsiZaznamy")
    private String dalsiZaznamy;

    @JsonProperty("AlternativniProvedeni")
    private String alternativniProvedeni;

    @JsonProperty("CisloTp")
    private String cisloTp;

    @JsonProperty("CisloOrv")
    private String cisloOrv;

    @JsonProperty("OrvZadrzeno")
    private boolean orvZadrzeno;

    @JsonProperty("OrvKeSkartaci")
    private String orvKeSkartaci;

    @JsonProperty("OrvOdevzdano")
    private String orvOdevzdano;

    @JsonProperty("RzDruh")
    private String rzDruh;

    @JsonProperty("RzJkVydana")
    private String rzJkVydana;

    @JsonProperty("RzKeSkartaci")
    private String rzKeSkartaci;

    @JsonProperty("RzOdevzdano")
    private String rzOdevzdano;

    @JsonProperty("RzZadrzena")
    private boolean rzZadrzena;

    @JsonProperty("ZarazeniVozidla")
    private String zarazeniVozidla;

    @JsonProperty("PravidelnaTechnickaProhlidkaDo")
    private LocalDateTime pravidelnaTechnickaProhlidkaDo;

    @JsonProperty("PredRegistraciProhlidkaDne")
    private LocalDateTime predRegistraciProhlidkaDne;

    @JsonProperty("PredSchvalenimProhlidkaDne")
    private LocalDateTime predSchvalenimProhlidkaDne;

    @JsonProperty("EvidencniProhlidkaDne")
    private LocalDateTime evidencniProhlidkaDne;

    @JsonProperty("HistorickeVozidloProhlidkaDne")
    private LocalDateTime historickeVozidloProhlidkaDne;

    @JsonProperty("StatusNazev")
    private String statusNazev;

    @JsonProperty("PocetVlastniku")
    private int pocetVlastniku;

    @JsonProperty("PocetProvozovatelu")
    private int pocetProvozovatelu;

    @Override
    public String toString() {
        return "ApiResponseData{" +
                "datumPrvniRegistrace=" + datumPrvniRegistrace +
                ", datumPrvniRegistraceVCr=" + datumPrvniRegistraceVCr +
                ", cisloTypovehoSchvaleni='" + cisloTypovehoSchvaleni + '\'' +
                ", homologaceEs='" + homologaceEs + '\'' +
                ", vozidloDruh='" + vozidloDruh + '\'' +
                ", vozidloDruh2='" + vozidloDruh2 + '\'' +
                ", kategorie='" + kategorie + '\'' +
                ", tovarniZnacka='" + tovarniZnacka + '\'' +
                ", typ='" + typ + '\'' +
                ", varianta='" + varianta + '\'' +
                ", verze='" + verze + '\'' +
                ", vin='" + vin + '\'' +
                ", obchodniOznaceni='" + obchodniOznaceni + '\'' +
                ", vozidloVyrobce='" + vozidloVyrobce + '\'' +
                ", motorVyrobce='" + motorVyrobce + '\'' +
                ", motorTyp='" + motorTyp + '\'' +
                ", motorMaxVykon='" + motorMaxVykon + '\'' +
                ", palivo='" + palivo + '\'' +
                ", motorZdvihObjem=" + motorZdvihObjem +
                ", vozidloElektricke='" + vozidloElektricke + '\'' +
                ", vozidloHybridni='" + vozidloHybridni + '\'' +
                ", vozidloHybridniTrida='" + vozidloHybridniTrida + '\'' +
                ", emiseEHKOSNEHSES='" + emiseEHKOSNEHSES + '\'' +
                ", emisniUroven='" + emisniUroven + '\'' +
                ", emiseKSA=" + emiseKSA +
                ", emiseCO2='" + emiseCO2 + '\'' +
                ", emiseCO2Specificke='" + emiseCO2Specificke + '\'' +
                ", emiseSnizeniNedc='" + emiseSnizeniNedc + '\'' +
                ", emiseSnizeniWltp='" + emiseSnizeniWltp + '\'' +
                ", spotrebaMetodika='" + spotrebaMetodika + '\'' +
                ", spotrebaNa100Km='" + spotrebaNa100Km + '\'' +
                ", spotreba='" + spotreba + '\'' +
                ", spotrebaEl='" + spotrebaEl + '\'' +
                ", dojezdZR='" + dojezdZR + '\'' +
                ", vyrobceKaroserie='" + vyrobceKaroserie + '\'' +
                ", karoserieDruh='" + karoserieDruh + '\'' +
                ", karoserieVyrobniCislo='" + karoserieVyrobniCislo + '\'' +
                ", vozidloKaroserieBarva='" + vozidloKaroserieBarva + '\'' +
                ", vozidloKaroserieBarvaDoplnkova='" + vozidloKaroserieBarvaDoplnkova + '\'' +
                ", vozidloKaroserieMist='" + vozidloKaroserieMist + '\'' +
                ", rozmery='" + rozmery + '\'' +
                ", rozmeryRozvor='" + rozmeryRozvor + '\'' +
                ", rozchod='" + rozchod + '\'' +
                ", hmotnostiProvozni=" + hmotnostiProvozni +
                ", hmotnostiPripPov='" + hmotnostiPripPov + '\'' +
                ", hmotnostiPripPovN='" + hmotnostiPripPovN + '\'' +
                ", hmotnostiPripPovBrzdenePV='" + hmotnostiPripPovBrzdenePV + '\'' +
                ", hmotnostiPripPovNebrzdenePV='" + hmotnostiPripPovNebrzdenePV + '\'' +
                ", hmotnostiPripPovJS='" + hmotnostiPripPovJS + '\'' +
                ", hmotnostiTestWltp='" + hmotnostiTestWltp + '\'' +
                ", hmotnostUzitecneZatizeniPrumer='" + hmotnostUzitecneZatizeniPrumer + '\'' +
                ", vozidloSpojZarizNazev='" + vozidloSpojZarizNazev + '\'' +
                ", napravyPocetDruh='" + napravyPocetDruh + '\'' +
                ", napravyPneuRafky='" + napravyPneuRafky + '\'' +
                ", hlukStojiciOtacky='" + hlukStojiciOtacky + '\'' +
                ", hlukJizda=" + hlukJizda +
                ", nejvyssiRychlost=" + nejvyssiRychlost +
                ", pomerVykonHmotnost=" + pomerVykonHmotnost +
                ", inovativniTechnologie='" + inovativniTechnologie + '\'' +
                ", stupenDokonceni='" + stupenDokonceni + '\'' +
                ", faktorOdchylkyDe='" + faktorOdchylkyDe + '\'' +
                ", faktorVerifikaceVf='" + faktorVerifikaceVf + '\'' +
                ", vozidloUcel='" + vozidloUcel + '\'' +
                ", dalsiZaznamy='" + dalsiZaznamy + '\'' +
                ", alternativniProvedeni='" + alternativniProvedeni + '\'' +
                ", cisloTp='" + cisloTp + '\'' +
                ", cisloOrv='" + cisloOrv + '\'' +
                ", orvZadrzeno=" + orvZadrzeno +
                ", orvKeSkartaci='" + orvKeSkartaci + '\'' +
                ", orvOdevzdano='" + orvOdevzdano + '\'' +
                ", rzDruh='" + rzDruh + '\'' +
                ", rzJkVydana='" + rzJkVydana + '\'' +
                ", rzKeSkartaci='" + rzKeSkartaci + '\'' +
                ", rzOdevzdano='" + rzOdevzdano + '\'' +
                ", rzZadrzena=" + rzZadrzena +
                ", zarazeniVozidla='" + zarazeniVozidla + '\'' +
                ", pravidelnaTechnickaProhlidkaDo='" + pravidelnaTechnickaProhlidkaDo + '\'' +
                ", predRegistraciProhlidkaDne='" + predRegistraciProhlidkaDne + '\'' +
                ", predSchvalenimProhlidkaDne='" + predSchvalenimProhlidkaDne + '\'' +
                ", evidencniProhlidkaDne=" + evidencniProhlidkaDne +
                ", historickeVozidloProhlidkaDne=" + historickeVozidloProhlidkaDne +
                ", statusNazev='" + statusNazev + '\'' +
                ", pocetVlastniku=" + pocetVlastniku +
                ", pocetProvozovatelu=" + pocetProvozovatelu +
                '}';
    }
}
