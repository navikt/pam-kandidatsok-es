package no.nav.arbeid.cv.indexer.config.temp;

import java.util.ArrayList;

import no.nav.arbeid.cv.events.AnsettelsesforholdJobbonsker;
import no.nav.arbeid.cv.events.ArbeidstidsordningJobbonsker;
import no.nav.arbeid.cv.events.CvEvent;
import no.nav.arbeid.cv.events.Forerkort;
import no.nav.arbeid.cv.events.GeografiJobbonsker;
import no.nav.arbeid.cv.events.HeltidDeltidJobbonsker;
import no.nav.arbeid.cv.events.Kompetanse;
import no.nav.arbeid.cv.events.Kurs;
import no.nav.arbeid.cv.events.Omfang;
import no.nav.arbeid.cv.events.Sertifikat;
import no.nav.arbeid.cv.events.Sprak;
import no.nav.arbeid.cv.events.Utdanning;
import no.nav.arbeid.cv.events.Verv;
import no.nav.arbeid.cv.events.YrkeJobbonsker;
import no.nav.arbeid.cv.events.Yrkeserfaring;

public class TempCvEventObjectMother {

  public static CvEvent giveMeCvEvent() {

    Utdanning utdanning = new Utdanning("1988-08-20", "1989-06-20", "Otta vgs. Otta", "355211",
        "Mekaniske fag, grunnkurs", "GK maskin/mekaniker");

    Utdanning utdanning1 = new Utdanning("1988-08-20", "1989-06-20", "Høyskolen i Gjøvik", "786595",
        "Master i sikkerhet", "Master i sikkerhet");


    ArrayList<Utdanning> utdanningsListe = new ArrayList<>();
    utdanningsListe.add(utdanning);
    utdanningsListe.add(utdanning1);
    Yrkeserfaring yrkeserfaring1 = new Yrkeserfaring("2000-01-01", "2000-01-10",
        "Stentransport, Kragerø", "8341.01", "Anleggsmaskindrifter",
        "maskinkjører og maskintransport", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring2 = new Yrkeserfaring("2003-01-01", "2003-02-01",
        "AF-Pihl, Hammerfest", "8342.01", "Anleggsmaskinfører", "maskinkjører og maskintransport",
        "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring3 = new Yrkeserfaring("2003-04-01", "2003-05-01",
        "O.K. Hagalia, Slependen", "8342.01", "Anleggsmaskinfører",
        "maskinkjører og maskintransport", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring4 =
        new Yrkeserfaring("2005-08-01", "2005-09-01", "Vard Group,avd.Brevik", "7233.03",
            "Industrimekaniker", "Industrimekaniker", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring5 =
        new Yrkeserfaring("2016-06-01", "2016-07-01", "MTM anlegg", "8332.03",
            "Lastebil- og trailersjåfør", "Sjåfør kl. 2", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring6 = new Yrkeserfaring("2017-10-01", "2017-12-01", "NLI  Grenland",
        "7233.03", "Industrimekaniker", "Industrimekaniker", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    ArrayList<Yrkeserfaring> yrkeserfaringsListe = new ArrayList<>();
    yrkeserfaringsListe.add(yrkeserfaring1);
    yrkeserfaringsListe.add(yrkeserfaring2);
    yrkeserfaringsListe.add(yrkeserfaring3);
    yrkeserfaringsListe.add(yrkeserfaring4);
    yrkeserfaringsListe.add(yrkeserfaring5);
    yrkeserfaringsListe.add(yrkeserfaring6);

    Kompetanse kompetanse1 =
        new Kompetanse("2016-03-14", "3020813", "Maskin- og kranførerarbeid", null, null);

    Kompetanse kompetanse2 = new Kompetanse("2016-03-14", "3281301", "Mekanisk arbeid generelt",
        "Mekanisk arbeid generelt", null);

    Kompetanse kompetanse3 = new Kompetanse("2016-03-14", "506", "Landtransport generelt",
        "Landtransport generelt", null);

    Kompetanse kompetanse4 = new Kompetanse("2016-03-14", "212", "Industri (bransje)",
        "Mekanisk industri (bransje)", null);

    ArrayList<Kompetanse> kompetanseList = new ArrayList<>();
    kompetanseList.add(kompetanse1);
    kompetanseList.add(kompetanse2);
    kompetanseList.add(kompetanse3);
    kompetanseList.add(kompetanse4);



    Sertifikat sertifikat1 = new Sertifikat("1994-08-01", null, "V1.6050",
        "Førerkort: Kl. A (tung motorsykkel)", null, "");

    Sertifikat sertifikat2 = new Sertifikat("1991-01-01", null, "V1.6070",
        "Førerkort: Kl. BE (personbil/varebil og tilhenger)", null, "");

    Sertifikat sertifikat3 = new Sertifikat("1996-02-01", "2020-12-01", "V1.6110",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 12 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 12 tonn", "");
    Sertifikat sertifikat4 = new Sertifikat("1995-01-01", null, "A1.6820",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");
    Sertifikat sertifikat5 = new Sertifikat("1995-01-01", null, "A1.6820",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");

    ArrayList<Sertifikat> sertifikatListe = new ArrayList<>();
    sertifikatListe.add(sertifikat1);
    sertifikatListe.add(sertifikat2);
    sertifikatListe.add(sertifikat3);
    sertifikatListe.add(sertifikat4);
    sertifikatListe.add(sertifikat5);

    Forerkort forerkort1 = new Forerkort("1994-08-01", null, "V1.6050",
        "Førerkort: Kl. A (tung motorsykkel)", null, "");

    Forerkort forerkort2 = new Forerkort("1991-01-01", null, "V1.6070",
        "Førerkort: Kl. BE (personbil/varebil og tilhenger)", null, "");

    Forerkort forerkort3 = new Forerkort("1996-02-01", "2020-12-01", "V1.6110",
        "Førerkort: Kl. CE (lastebil og tilhenger)", null, "");

    Forerkort forerkort4 = new Forerkort("1996-02-01", "2020-12-01", "V1.6145",
        "Førerkort: Kl. DE (buss og tilhenger)", null, "");

    ArrayList<Forerkort> forerkortListe = new ArrayList<>();
    forerkortListe.add(forerkort1);
    forerkortListe.add(forerkort2);
    forerkortListe.add(forerkort3);
    forerkortListe.add(forerkort4);


    Sprak sprak1 = new Sprak("2012-12-01", "78874", "Norsk(skriftlig)", "Norwegian", "Morsmål");

    Sprak sprak2 = new Sprak("2012-12-01", "78874", "Norsk(muntlig)", "Norwegian", "Morsmål");

    ArrayList<Sprak> sprakListe = new ArrayList<>();
    sprakListe.add(sprak1);
    sprakListe.add(sprak2);

    Kurs kurs1 =
        new Kurs("2012-12-01", null, "Akseloppretting", "Easy-Laser", new Omfang(null, null), null);

    Kurs kurs2 = new Kurs("2015-06-01", null, "Varme arbeider Sertifikat",
        "Norsk brannvernforening", new Omfang(5, "ÅR"), null);

    Kurs kurs3 = new Kurs("2016-02-01", null, "Flensarbeid for Norsk Olje og Gass",
        "Torqlite Europa a/s", new Omfang(4, "ÅR"), null);


    ArrayList<Kurs> kursListe = new ArrayList<>();
    kursListe.add(kurs1);
    kursListe.add(kurs2);
    kursListe.add(kurs3);

    Verv verv = new Verv("2000-01-15", "2001-01-15", "Verv organisasjon", "verv tittel");

    ArrayList<Verv> vervListe = new ArrayList<>();
    vervListe.add(verv);

    GeografiJobbonsker geografiJobbonsker = new GeografiJobbonsker("Hamar", "NO10.1010");

    GeografiJobbonsker geografiJobbonsker1 = new GeografiJobbonsker("Lillehammer", "NO10.1011");

    GeografiJobbonsker geografiJobbonsker2 = new GeografiJobbonsker("Hedmark", "NO10");

    ArrayList<GeografiJobbonsker> geografiJobbonskerListe = new ArrayList<>();
    geografiJobbonskerListe.add(geografiJobbonsker);
    geografiJobbonskerListe.add(geografiJobbonsker1);
    geografiJobbonskerListe.add(geografiJobbonsker2);

    YrkeJobbonsker yrkeJobbonsker =
        new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Yrke jobb ønske Styrk beskrivelse", true);

    ArrayList<YrkeJobbonsker> yrkeJobbonskerListe = new ArrayList<>();
    yrkeJobbonskerListe.add(yrkeJobbonsker);

    HeltidDeltidJobbonsker heltidDeltidJobbonsker =
        new HeltidDeltidJobbonsker("HeltidDeltidKode", "HeltidDeltidKode Tekst");

    ArrayList<HeltidDeltidJobbonsker> heltidDeltidJobbonskerListe = new ArrayList<>();
    heltidDeltidJobbonskerListe.add(heltidDeltidJobbonsker);

    AnsettelsesforholdJobbonsker ansettelsesforholdJobbonsker = new AnsettelsesforholdJobbonsker(
        "Ansettelsesforhold Kode", "Ansettelsesforhold Kode tekst");

    ArrayList<AnsettelsesforholdJobbonsker> ansettelsesforholdJobbonskerListe = new ArrayList<>();
    ansettelsesforholdJobbonskerListe.add(ansettelsesforholdJobbonsker);

    ArbeidstidsordningJobbonsker arbeidstidsordningJobbonsker = new ArbeidstidsordningJobbonsker(
        "Arbeidstidsordning Kode", "Arbeidstidsordning Kode Tekst");

    ArrayList<ArbeidstidsordningJobbonsker> arbeidstidsordningJobbonskerListe = new ArrayList<>();
    arbeidstidsordningJobbonskerListe.add(arbeidstidsordningJobbonsker);

    return new CvEvent("01016012345", "OLA", "NORDMANN", "1960-01-01", false, "ARBS",
        "unnasluntrer@mailinator.com", "NO", 1L, "S221234", // Kan ik
        "", "N", "2016-05-30", "Minvei 1", "", "", "0654", "OSLO", "NO", 301, false, "",
        utdanningsListe, yrkeserfaringsListe, kompetanseList, sertifikatListe, forerkortListe,
        sprakListe, kursListe, vervListe, geografiJobbonskerListe, yrkeJobbonskerListe,
        heltidDeltidJobbonskerListe, ansettelsesforholdJobbonskerListe,
        arbeidstidsordningJobbonskerListe);
  }

  public static CvEvent giveMeCvEvent2() {

    Utdanning utdanning = new Utdanning("1999-08-20", "2002-06-20", "Hamar Katedralskole", "296647",
        "Studiespesialisering", "Studiespesialisering med realfag");

    ArrayList<Utdanning> utdanningsListe = new ArrayList<>();
    utdanningsListe.add(utdanning);
    Yrkeserfaring yrkeserfaring1 =
        new Yrkeserfaring("2000-01-01", "2002-01-01", "Kodesentralen Vardø", "5746.07",
            "Programvareutvikler", "Fullstackutvikler", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring2 = new Yrkeserfaring("2003-01-01", "2003-07-01",
        "Programvarefabrikken Førde", "5746.07", "Systemutvikler", "Utvikling av nytt kandidatsøk",
        "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring3 = new Yrkeserfaring("2003-04-01", "2003-05-01",
        "Tjenestetest Norge", "6859.02", "Systemtester", "Automatiske tester av nytt kandidatsøk",
        "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring4 =
        new Yrkeserfaring("2005-08-01", "2006-07-01", "lagerarbeiderne L. H.", "8659.03",
            "Lagermedarbeider", "Lagermedarbeider", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring5 =
        new Yrkeserfaring("2016-06-01", "2017-04-01", "lagerarbeiderne L. H.", "8659.03",
            "Truckfører lager", "Stortruck", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring6 = new Yrkeserfaring("2017-10-01", null, "Awesome coders AS",
        "5746.07", "Javautvikler", "Javautvikler", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    ArrayList<Yrkeserfaring> yrkeserfaringsListe = new ArrayList<>();
    yrkeserfaringsListe.add(yrkeserfaring1);
    yrkeserfaringsListe.add(yrkeserfaring2);
    yrkeserfaringsListe.add(yrkeserfaring3);
    yrkeserfaringsListe.add(yrkeserfaring4);
    yrkeserfaringsListe.add(yrkeserfaring5);
    yrkeserfaringsListe.add(yrkeserfaring6);

    Kompetanse kompetanse1 = new Kompetanse("2016-03-14", "265478", "Javautvikler", null, null);

    Kompetanse kompetanse2 =
        new Kompetanse("2016-03-14", "265478", "Programvareutvikler", "Programvareutvikler", null);

    Kompetanse kompetanse3 =
        new Kompetanse("2016-03-14", "475136", "Lagermedarbeider", "Lagermedarbeider", null);

    Kompetanse kompetanse4 = new Kompetanse("2016-03-14", "501", "Truckfører", "Truckfører", null);

    ArrayList<Kompetanse> kompetanseList = new ArrayList<>();
    kompetanseList.add(kompetanse1);
    kompetanseList.add(kompetanse2);
    kompetanseList.add(kompetanse3);
    kompetanseList.add(kompetanse4);



    Sertifikat sertifikat1 = new Sertifikat("1994-08-01", null, "V1.6050",
        "Førerkort: Kl. A (tung motorsykkel)", null, "");

    Sertifikat sertifikat2 = new Sertifikat("1991-01-01", null, "V1.6070",
        "Førerkort: Kl. BE (personbil/varebil og tilhenger)", null, "");

    Sertifikat sertifikat3 = new Sertifikat("1996-02-01", "2020-12-01", "V1.6110",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");
    Sertifikat sertifikat4 = new Sertifikat("1995-01-01", null, "A1.6820",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");
    Sertifikat sertifikat5 = new Sertifikat("1995-01-01", null, "A1.6820",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");

    ArrayList<Sertifikat> sertifikatListe = new ArrayList<>();
    sertifikatListe.add(sertifikat1);
    sertifikatListe.add(sertifikat2);
    sertifikatListe.add(sertifikat3);
    sertifikatListe.add(sertifikat4);
    sertifikatListe.add(sertifikat5);

    Forerkort forerkort1 = new Forerkort("1994-08-01", null, "V1.6050",
        "Førerkort: Kl. A (tung motorsykkel)", null, "");

    Forerkort forerkort2 = new Forerkort("1991-01-01", null, "V1.6070",
        "Førerkort: Kl. BE (personbil/varebil og tilhenger)", null, "");

    Forerkort forerkort3 = new Forerkort("1996-02-01", "2020-12-01", "V1.6110",
        "Førerkort: Kl. CE (lastebil og tilhenger)", null, "");

    Forerkort forerkort4 = new Forerkort("1996-02-01", "2020-12-01", "V1.6145",
        "Førerkort: Kl. DE (buss og tilhenger)", null, "");

    ArrayList<Forerkort> forerkortListe = new ArrayList<>();
    forerkortListe.add(forerkort1);
    forerkortListe.add(forerkort2);
    forerkortListe.add(forerkort3);
    forerkortListe.add(forerkort4);


    Sprak sprak = new Sprak("2012-12-01", "19093", "Norsk", "Norwegian", "Flytende");

    ArrayList<Sprak> sprakListe = new ArrayList<>();
    sprakListe.add(sprak);

    Kurs kurs1 =
        new Kurs("2012-12-01", null, "Akseloppretting", "Easy-Laser", new Omfang(null, null), null);

    Kurs kurs2 = new Kurs("2015-06-01", null, "Varme arbeider Sertifikat",
        "Norsk brannvernforening", new Omfang(5, "ÅR"), null);

    Kurs kurs3 = new Kurs("2016-02-01", null, "Flensarbeid for Norsk Olje og Gass",
        "Torqlite Europa a/s", new Omfang(4, "ÅR"), null);


    ArrayList<Kurs> kursListe = new ArrayList<>();
    kursListe.add(kurs1);
    kursListe.add(kurs2);
    kursListe.add(kurs3);

    Verv verv = new Verv("2000-01-15", "2001-01-15", "Verv organisasjon", "verv tittel");

    ArrayList<Verv> vervListe = new ArrayList<>();
    vervListe.add(verv);

    GeografiJobbonsker geografiJobbonsker = new GeografiJobbonsker("Hedmark", "NO10");

    GeografiJobbonsker geografiJobbonsker1 = new GeografiJobbonsker("Stavanger", "NO21.2020");

    GeografiJobbonsker geografiJobbonsker2 = new GeografiJobbonsker("Førde", "NO08.2929");

    GeografiJobbonsker geografiJobbonsker3 =
        new GeografiJobbonsker("Trondheim(Bydel Heimdal)", "NO50.50014");

    ArrayList<GeografiJobbonsker> geografiJobbonskerListe = new ArrayList<>();
    geografiJobbonskerListe.add(geografiJobbonsker);
    geografiJobbonskerListe.add(geografiJobbonsker1);
    geografiJobbonskerListe.add(geografiJobbonsker2);
    geografiJobbonskerListe.add(geografiJobbonsker3);

    YrkeJobbonsker yrkeJobbonsker =
        new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Javaprogrammerer", true);

    YrkeJobbonsker yrkeJobbonsker1 =
        new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Programvareutvikler", true);

    YrkeJobbonsker yrkeJobbonsker2 = new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Bonde", true);

    ArrayList<YrkeJobbonsker> yrkeJobbonskerListe = new ArrayList<>();
    yrkeJobbonskerListe.add(yrkeJobbonsker);
    yrkeJobbonskerListe.add(yrkeJobbonsker1);
    yrkeJobbonskerListe.add(yrkeJobbonsker2);

    HeltidDeltidJobbonsker heltidDeltidJobbonsker =
        new HeltidDeltidJobbonsker("HeltidDeltidKode", "HeltidDeltidKode Tekst");

    ArrayList<HeltidDeltidJobbonsker> heltidDeltidJobbonskerListe = new ArrayList<>();
    heltidDeltidJobbonskerListe.add(heltidDeltidJobbonsker);

    AnsettelsesforholdJobbonsker ansettelsesforholdJobbonsker = new AnsettelsesforholdJobbonsker(
        "Ansettelsesforhold Kode", "Ansettelsesforhold Kode tekst");

    ArrayList<AnsettelsesforholdJobbonsker> ansettelsesforholdJobbonskerListe = new ArrayList<>();
    ansettelsesforholdJobbonskerListe.add(ansettelsesforholdJobbonsker);

    ArbeidstidsordningJobbonsker arbeidstidsordningJobbonsker = new ArbeidstidsordningJobbonsker(
        "Arbeidstidsordning Kode", "Arbeidstidsordning Kode Tekst");

    ArrayList<ArbeidstidsordningJobbonsker> arbeidstidsordningJobbonskerListe = new ArrayList<>();
    arbeidstidsordningJobbonskerListe.add(arbeidstidsordningJobbonsker);

    return new CvEvent("05236984567", "KARI", "NORDMANN", "1980-02-10", false, "ARBS",
        "unnasluntrer2@mailinator.com", "NO", 2L, "H738234", "", "J", "2016-05-30", "Dinvei 2", "",
        "", "1337", "HUSKER IKKE", "NO", 301, false, "", utdanningsListe, yrkeserfaringsListe,
        kompetanseList, sertifikatListe, forerkortListe, sprakListe, kursListe, vervListe,
        geografiJobbonskerListe, yrkeJobbonskerListe, heltidDeltidJobbonskerListe,
        ansettelsesforholdJobbonskerListe, arbeidstidsordningJobbonskerListe);
  }

  public static CvEvent giveMeCvEvent3() {

    Utdanning utdanning = new Utdanning("1988-08-20", "1989-06-20",
        "Norges Naturvitenskapelige Universitet", "456375", "Sosiologi", "Sosiologi");

    ArrayList<Utdanning> utdanningsListe = new ArrayList<>();
    utdanningsListe.add(utdanning);
    Yrkeserfaring yrkeserfaring1 =
        new Yrkeserfaring("2000-01-01", "2000-02-01", "Butikken i nærheten", "1010.01",
            "Butikkmedarbeider", "Butikkmedarbeider i Førde", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring2 = new Yrkeserfaring("2003-01-01", "2003-02-01",
        "Butikken i nærheten", "1010.01", "Butikkmedarbeider(dagligvarer)",
        "Butikkmedarbeider(dagligvarer)", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring3 = new Yrkeserfaring("2003-04-01", "2003-05-01",
        "Butikken langt unna", "1010.01", "Butikkmedarbeider(trevare)",
        "Butikkmedarbeider(trevare)", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring4 = new Yrkeserfaring("2005-08-01", "2005-09-01", "Butikken",
        "4561.03", "Butikkmedarbeider(elektronikk)", "Butikkmedarbeider(elektronikk)", "YRKE_ORGNR",
        "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring5 = new Yrkeserfaring("2016-06-01", "2016-07-01", "Tvkanalen TV?",
        "5684.05", "Presentør", "Presentør", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring6 = new Yrkeserfaring("2017-10-01", null, "NLI  Grenland", "5684.05",
        "Nyhetsanker", "Nyhetsanker", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    ArrayList<Yrkeserfaring> yrkeserfaringsListe = new ArrayList<>();
    yrkeserfaringsListe.add(yrkeserfaring1);
    yrkeserfaringsListe.add(yrkeserfaring2);
    yrkeserfaringsListe.add(yrkeserfaring3);
    yrkeserfaringsListe.add(yrkeserfaring4);
    yrkeserfaringsListe.add(yrkeserfaring5);
    yrkeserfaringsListe.add(yrkeserfaring6);

    Kompetanse kompetanse1 = new Kompetanse("2016-03-14", "152424", "Presentør", null, null);

    Kompetanse kompetanse2 =
        new Kompetanse("2016-03-14", "152424", "Nyhetsanker", "Nyhetsanker", null);

    Kompetanse kompetanse3 =
        new Kompetanse("2016-03-14", "566895", "Butikkmedarbeider", "Butikkmedarbeider", null);

    Kompetanse kompetanse4 = new Kompetanse("2016-03-14", "566895", "Butikkmedarbeider(trevare)",
        "Butikkmedarbeider(trevare)", null);

    ArrayList<Kompetanse> kompetanseList = new ArrayList<>();
    kompetanseList.add(kompetanse1);
    kompetanseList.add(kompetanse2);
    kompetanseList.add(kompetanse3);
    kompetanseList.add(kompetanse4);



    Sertifikat sertifikat1 = new Sertifikat("1994-08-01", null, "V1.6050",
        "Førerkort: Kl. A (tung motorsykkel)", null, "");

    Sertifikat sertifikat2 = new Sertifikat("1991-01-01", null, "V1.6070",
        "Førerkort: Kl. BE (personbil/varebil og tilhenger)", null, "");

    Sertifikat sertifikat3 = new Sertifikat("1996-02-01", "2020-12-01", "V1.6110",
        "Truckførerbevis", "Truckførerbevis", "");
    Sertifikat sertifikat4 = new Sertifikat("1995-01-01", null, "A1.6820",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");
    Sertifikat sertifikat5 = new Sertifikat("1995-01-01", null, "A1.6820",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");

    ArrayList<Sertifikat> sertifikatListe = new ArrayList<>();
    sertifikatListe.add(sertifikat1);
    sertifikatListe.add(sertifikat2);
    sertifikatListe.add(sertifikat3);
    sertifikatListe.add(sertifikat4);
    sertifikatListe.add(sertifikat5);

    Forerkort forerkort1 = new Forerkort("1994-08-01", null, "V1.6050",
        "Førerkort: Kl. A (tung motorsykkel)", null, "");

    Forerkort forerkort2 = new Forerkort("1991-01-01", null, "V1.6070",
        "Førerkort: Kl. BE (personbil/varebil og tilhenger)", null, "");

    Forerkort forerkort3 = new Forerkort("1996-02-01", "2020-12-01", "V1.6110",
        "Førerkort: Kl. CE (lastebil og tilhenger)", null, "");

    Forerkort forerkort4 =
        new Forerkort("1996-02-01", "2020-12-01", "V1.6145", "Traktorlappen", null, "");

    ArrayList<Forerkort> forerkortListe = new ArrayList<>();
    forerkortListe.add(forerkort1);
    forerkortListe.add(forerkort2);
    forerkortListe.add(forerkort3);
    forerkortListe.add(forerkort4);


    Sprak sprak = new Sprak("2012-12-01", "87392", "Engelsk", "English", "Flytende");

    ArrayList<Sprak> sprakListe = new ArrayList<>();
    sprakListe.add(sprak);

    Kurs kurs1 =
        new Kurs("2012-12-01", null, "Akseloppretting", "Easy-Laser", new Omfang(null, null), null);

    Kurs kurs2 = new Kurs("2015-06-01", null, "Varme arbeider Sertifikat",
        "Norsk brannvernforening", new Omfang(5, "ÅR"), null);

    Kurs kurs3 = new Kurs("2016-02-01", null, "Flensarbeid for Norsk Olje og Gass",
        "Torqlite Europa a/s", new Omfang(4, "ÅR"), null);


    ArrayList<Kurs> kursListe = new ArrayList<>();
    kursListe.add(kurs1);
    kursListe.add(kurs2);
    kursListe.add(kurs3);

    Verv verv = new Verv("2000-01-15", "2001-01-15", "Verv organisasjon", "verv tittel");

    ArrayList<Verv> vervListe = new ArrayList<>();
    vervListe.add(verv);

    GeografiJobbonsker geografiJobbonsker = new GeografiJobbonsker("Hedmark", "NO10");

    GeografiJobbonsker geografiJobbonsker1 =
        new GeografiJobbonsker("Bergen(bydel Fana)", "NO34.3434.1");

    GeografiJobbonsker geografiJobbonsker2 = new GeografiJobbonsker("Hordaland", "NO34");

    ArrayList<GeografiJobbonsker> geografiJobbonskerListe = new ArrayList<>();
    geografiJobbonskerListe.add(geografiJobbonsker);
    geografiJobbonskerListe.add(geografiJobbonsker1);
    geografiJobbonskerListe.add(geografiJobbonsker2);

    YrkeJobbonsker yrkeJobbonsker =
        new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Ordfører", true);

    YrkeJobbonsker yrkeJobbonsker1 =
        new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Barnehageassistent", true);

    YrkeJobbonsker yrkeJobbonsker2 =
        new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Tester", true);

    ArrayList<YrkeJobbonsker> yrkeJobbonskerListe = new ArrayList<>();
    yrkeJobbonskerListe.add(yrkeJobbonsker);
    yrkeJobbonskerListe.add(yrkeJobbonsker1);
    yrkeJobbonskerListe.add(yrkeJobbonsker2);

    HeltidDeltidJobbonsker heltidDeltidJobbonsker =
        new HeltidDeltidJobbonsker("HeltidDeltidKode", "HeltidDeltidKode Tekst");

    ArrayList<HeltidDeltidJobbonsker> heltidDeltidJobbonskerListe = new ArrayList<>();
    heltidDeltidJobbonskerListe.add(heltidDeltidJobbonsker);

    AnsettelsesforholdJobbonsker ansettelsesforholdJobbonsker = new AnsettelsesforholdJobbonsker(
        "Ansettelsesforhold Kode", "Ansettelsesforhold Kode tekst");

    ArrayList<AnsettelsesforholdJobbonsker> ansettelsesforholdJobbonskerListe = new ArrayList<>();
    ansettelsesforholdJobbonskerListe.add(ansettelsesforholdJobbonsker);

    ArbeidstidsordningJobbonsker arbeidstidsordningJobbonsker = new ArbeidstidsordningJobbonsker(
        "Arbeidstidsordning Kode", "Arbeidstidsordning Kode Tekst");

    ArrayList<ArbeidstidsordningJobbonsker> arbeidstidsordningJobbonskerListe = new ArrayList<>();
    arbeidstidsordningJobbonskerListe.add(arbeidstidsordningJobbonsker);

    return new CvEvent("04265983651", "HANS", "NORDMANN", "1955-11-04", false, "ARBS",
        "alltidmed@mailinator.com", "NO", 3L, "S738893", "", "J", "2016-05-30", "Minvei 1", "", "",
        "2323", "INGEBERG", "NO", 301, false, "", utdanningsListe, yrkeserfaringsListe,
        kompetanseList, sertifikatListe, forerkortListe, sprakListe, kursListe, vervListe,
        geografiJobbonskerListe, yrkeJobbonskerListe, heltidDeltidJobbonskerListe,
        ansettelsesforholdJobbonskerListe, arbeidstidsordningJobbonskerListe);
  }

  public static CvEvent giveMeCvEvent4() {

    Utdanning utdanning = new Utdanning("1988-08-20", "1989-06-20",
        "Norges Naturvitenskapelige Universitet", "456375", "Bygg og anlegg", "Bygg og anlegg");

    ArrayList<Utdanning> utdanningsListe = new ArrayList<>();
    utdanningsListe.add(utdanning);
    Yrkeserfaring yrkeserfaring1 = new Yrkeserfaring("2000-01-01", "2002-01-01", "Jokah", "1010.01",
        "Butikkmedarbeider", "Butikkmedarbeider", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring2 = new Yrkeserfaring("2003-01-01", "2003-04-01", "Nærbutikkern",
        "1010.01", "Butikkmedarbeider(klesbutikk)", "Butikkmedarbeider(klebutikk)", "YRKE_ORGNR",
        "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring3 = new Yrkeserfaring("2003-04-01", "2003-07-01", "Tv tv tv",
        "5684.05", "Nyhetspresentør", "Nyhetspresentør", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring4 =
        new Yrkeserfaring("2005-08-01", "2016-07-01", "Vard Group,avd.Brevik", "5684.05",
            "Hallovert", "Hallovert", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring5 = new Yrkeserfaring("2016-06-01", "2017-04-01", "DN teater",
        "5124.46", "Skuespiller", "Skuespiller", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring6 = new Yrkeserfaring("2017-10-01", null, "Dukketeateret Rena",
        "5124.46", "Skuespiller(dukketeater)", "Skuespiller(dukketeater)", "YRKE_ORGNR",
        "YRKE_NACEKODE", false);

    ArrayList<Yrkeserfaring> yrkeserfaringsListe = new ArrayList<>();
    yrkeserfaringsListe.add(yrkeserfaring1);
    yrkeserfaringsListe.add(yrkeserfaring2);
    yrkeserfaringsListe.add(yrkeserfaring3);
    yrkeserfaringsListe.add(yrkeserfaring4);
    yrkeserfaringsListe.add(yrkeserfaring5);
    yrkeserfaringsListe.add(yrkeserfaring6);

    Kompetanse kompetanse1 = new Kompetanse("2016-03-14", "152424", "Hallovert", null, null);

    Kompetanse kompetanse2 =
        new Kompetanse("2016-03-14", "566895", "Butikkmedarbeider", "Butikkmedarbeider", null);

    Kompetanse kompetanse3 = new Kompetanse("2016-03-14", "564646", "Butikkmedarbeider(klesbutikk)",
        "Butikkmedarbeider(klesbutikk)", null);

    Kompetanse kompetanse4 =
        new Kompetanse("2016-03-14", "506", "Skuespiller", "Skuespiller", null);

    ArrayList<Kompetanse> kompetanseList = new ArrayList<>();
    kompetanseList.add(kompetanse1);
    kompetanseList.add(kompetanse2);
    kompetanseList.add(kompetanse3);
    kompetanseList.add(kompetanse4);



    Sertifikat sertifikat1 = new Sertifikat("1994-08-01", null, "V1.6050",
        "Førerkort: Kl. A (tung motorsykkel)", null, "");

    Sertifikat sertifikat2 =
        new Sertifikat("1991-01-01", null, "V1.6090", "Førerkort: Kl. M (Moped)", null, "");

    Sertifikat sertifikat3 = new Sertifikat("1996-02-01", "2020-12-01", "V1.6110",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");
    Sertifikat sertifikat4 = new Sertifikat("1995-01-01", null, "A1.6820",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");
    Sertifikat sertifikat5 = new Sertifikat("1995-01-01", null, "A1.6820",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");

    ArrayList<Sertifikat> sertifikatListe = new ArrayList<>();
    sertifikatListe.add(sertifikat1);
    sertifikatListe.add(sertifikat2);
    sertifikatListe.add(sertifikat3);
    sertifikatListe.add(sertifikat4);
    sertifikatListe.add(sertifikat5);

    Forerkort forerkort1 = new Forerkort("1994-08-01", null, "V1.6050",
        "Førerkort: Kl. A (tung motorsykkel)", null, "");

    Forerkort forerkort2 = new Forerkort("1991-01-01", null, "V1.6070",
        "Førerkort: Kl. BE (personbil/varebil og tilhenger)", null, "");

    Forerkort forerkort3 = new Forerkort("1996-02-01", "2020-12-01", "V1.6110",
        "Førerkort: Kl. CE (lastebil og tilhenger)", null, "");

    Forerkort forerkort4 = new Forerkort("1996-02-01", "2020-12-01", "V1.6145",
        "Førerkort: Kl. DE (buss og tilhenger)", null, "");

    ArrayList<Forerkort> forerkortListe = new ArrayList<>();
    forerkortListe.add(forerkort1);
    forerkortListe.add(forerkort2);
    forerkortListe.add(forerkort3);
    forerkortListe.add(forerkort4);


    Sprak sprak = new Sprak("2012-12-01", "78985", "Tysk", "German", "Begynner");

    ArrayList<Sprak> sprakListe = new ArrayList<>();
    sprakListe.add(sprak);

    Kurs kurs1 =
        new Kurs("2012-12-01", null, "Akseloppretting", "Easy-Laser", new Omfang(null, null), null);

    Kurs kurs2 = new Kurs("2015-06-01", null, "Varme arbeider Sertifikat",
        "Norsk brannvernforening", new Omfang(5, "ÅR"), null);

    Kurs kurs3 = new Kurs("2016-02-01", null, "Flensarbeid for Norsk Olje og Gass",
        "Torqlite Europa a/s", new Omfang(4, "ÅR"), null);


    ArrayList<Kurs> kursListe = new ArrayList<>();
    kursListe.add(kurs1);
    kursListe.add(kurs2);
    kursListe.add(kurs3);

    Verv verv = new Verv("2000-01-15", "2001-01-15", "Verv organisasjon", "verv tittel");

    ArrayList<Verv> vervListe = new ArrayList<>();
    vervListe.add(verv);

    GeografiJobbonsker geografiJobbonsker = new GeografiJobbonsker("Oslo", "NO01.1000");

    GeografiJobbonsker geografiJobbonsker1 = new GeografiJobbonsker("Akershus", "NO02.1210");

    GeografiJobbonsker geografiJobbonsker2 = new GeografiJobbonsker("Bærum", "NO02.1212");

    GeografiJobbonsker geografiJobbonsker3 = new GeografiJobbonsker("Norge", "NO");

    ArrayList<GeografiJobbonsker> geografiJobbonskerListe = new ArrayList<>();
    geografiJobbonskerListe.add(geografiJobbonsker);
    geografiJobbonskerListe.add(geografiJobbonsker1);
    geografiJobbonskerListe.add(geografiJobbonsker2);
    geografiJobbonskerListe.add(geografiJobbonsker3);

    YrkeJobbonsker yrkeJobbonsker =
        new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Butikkmedarbeider", true);

    YrkeJobbonsker yrkeJobbonsker1 =
        new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Butikkmedarbeider", true);

    ArrayList<YrkeJobbonsker> yrkeJobbonskerListe = new ArrayList<>();
    yrkeJobbonskerListe.add(yrkeJobbonsker);
    yrkeJobbonskerListe.add(yrkeJobbonsker1);

    HeltidDeltidJobbonsker heltidDeltidJobbonsker =
        new HeltidDeltidJobbonsker("HeltidDeltidKode", "HeltidDeltidKode Tekst");

    ArrayList<HeltidDeltidJobbonsker> heltidDeltidJobbonskerListe = new ArrayList<>();
    heltidDeltidJobbonskerListe.add(heltidDeltidJobbonsker);

    AnsettelsesforholdJobbonsker ansettelsesforholdJobbonsker = new AnsettelsesforholdJobbonsker(
        "Ansettelsesforhold Kode", "Ansettelsesforhold Kode tekst");

    ArrayList<AnsettelsesforholdJobbonsker> ansettelsesforholdJobbonskerListe = new ArrayList<>();
    ansettelsesforholdJobbonskerListe.add(ansettelsesforholdJobbonsker);

    ArbeidstidsordningJobbonsker arbeidstidsordningJobbonsker = new ArbeidstidsordningJobbonsker(
        "Arbeidstidsordning Kode", "Arbeidstidsordning Kode Tekst");

    ArrayList<ArbeidstidsordningJobbonsker> arbeidstidsordningJobbonskerListe = new ArrayList<>();
    arbeidstidsordningJobbonskerListe.add(arbeidstidsordningJobbonsker);

    return new CvEvent("09568410230", "HANNE", "NORDMANN", "2002-06-04", false, "ARBS",
        "erjegmed@mailinator.com", "NO", 4L, "K838829", "", "J", "2016-05-30", "Noensvei 1", "", "",
        "7012", "TRONDHEIM", "NO", 301, false, "", utdanningsListe, yrkeserfaringsListe,
        kompetanseList, sertifikatListe, forerkortListe, sprakListe, kursListe, vervListe,
        geografiJobbonskerListe, yrkeJobbonskerListe, heltidDeltidJobbonskerListe,
        ansettelsesforholdJobbonskerListe, arbeidstidsordningJobbonskerListe);
  }

  public static CvEvent giveMeCvEvent5() {

    ArrayList<Utdanning> utdanningsListe = new ArrayList<>();


    Yrkeserfaring yrkeserfaring1 = new Yrkeserfaring("2000-01-01", "2000-02-01", "Bankhvelvet BBL",
        "4865.75", "Bankhvelvoperatør", "Bankhvelvoperatør", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring2 = new Yrkeserfaring("2003-01-01", "2003-02-01", "Proggehula",
        "5746.07", "Progger", "Progger", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring3 = new Yrkeserfaring("2003-04-01", "2003-05-01", "Test a.a.s",
        "6859.02", "Tester", "Tester", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring4 = new Yrkeserfaring("2005-08-01", "2005-09-01", "K.O. kranservice",
        "8342.01", "Kranoperatør", "Kranoperatør", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring5 =
        new Yrkeserfaring("2016-06-01", "2016-06-02", "Lang transport A.S.", "8332.03",
            "Lastebil- og trailersjåfør", "Sjåfør kl. 3", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    Yrkeserfaring yrkeserfaring6 = new Yrkeserfaring("2017-10-01", "2017-11-01", "Mekken mekk",
        "7233.03", "Industrimekaniker", "Industrimekaniker", "YRKE_ORGNR", "YRKE_NACEKODE", false);

    ArrayList<Yrkeserfaring> yrkeserfaringsListe = new ArrayList<>();
    yrkeserfaringsListe.add(yrkeserfaring1);
    yrkeserfaringsListe.add(yrkeserfaring2);
    yrkeserfaringsListe.add(yrkeserfaring3);
    yrkeserfaringsListe.add(yrkeserfaring4);
    yrkeserfaringsListe.add(yrkeserfaring5);
    yrkeserfaringsListe.add(yrkeserfaring6);

    Kompetanse kompetanse1 = new Kompetanse("2016-03-14", "3020813",
        "Maskin- og kranførerarbeid(type 2 kran)", null, null);

    Kompetanse kompetanse2 = new Kompetanse("2016-03-14", "3281301", "Mekanisk arbeid spesielt",
        "Mekanisk arbeid spesielt", null);

    Kompetanse kompetanse3 = new Kompetanse("2016-03-14", "3220201", "Landtransport generelt",
        "Landtransport generelt", null);

    Kompetanse kompetanse4 = new Kompetanse("2016-03-14", "212", "Industri (bransje)",
        "Mekanisk industri (bransje)", null);

    ArrayList<Kompetanse> kompetanseList = new ArrayList<>();
    kompetanseList.add(kompetanse1);
    kompetanseList.add(kompetanse2);
    kompetanseList.add(kompetanse3);
    kompetanseList.add(kompetanse4);



    Sertifikat sertifikat1 = new Sertifikat("1994-08-01", null, "V1.6050",
        "Førerkort: Kl. A (tung motorsykkel)", null, "");

    Sertifikat sertifikat2 = new Sertifikat("1991-01-01", null, "V1.6070",
        "Førerkort: Kl. BE (personbil/varebil og tilhenger)", null, "");

    Sertifikat sertifikat3 = new Sertifikat("1996-02-01", "2020-12-01", "V1.6110",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");
    Sertifikat sertifikat4 = new Sertifikat("1995-01-01", null, "A1.6820",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");
    Sertifikat sertifikat5 = new Sertifikat("1995-01-01", null, "A1.6820",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn",
        "Yrkesbevis anleggsmaskinførere: Arb.klar maskin over 6 tonn", "");

    ArrayList<Sertifikat> sertifikatListe = new ArrayList<>();
    sertifikatListe.add(sertifikat1);
    sertifikatListe.add(sertifikat2);
    sertifikatListe.add(sertifikat3);
    sertifikatListe.add(sertifikat4);
    sertifikatListe.add(sertifikat5);

    Forerkort forerkort1 = new Forerkort("1994-08-01", null, "V1.6050",
        "Førerkort: Kl. A (tung motorsykkel)", null, "");

    Forerkort forerkort2 = new Forerkort("1991-01-01", null, "V1.6070",
        "Førerkort: Kl. BE (personbil/varebil og tilhenger)", null, "");

    Forerkort forerkort3 = new Forerkort("1996-02-01", "2020-12-01", "V1.6110",
        "Førerkort: Kl. CE (lastebil og tilhenger)", null, "");

    Forerkort forerkort4 = new Forerkort("1996-02-01", "2020-12-01", "V1.6145",
        "Førerkort: Kl. DE (buss og tilhenger)", null, "");

    ArrayList<Forerkort> forerkortListe = new ArrayList<>();
    forerkortListe.add(forerkort1);
    forerkortListe.add(forerkort2);
    forerkortListe.add(forerkort3);
    forerkortListe.add(forerkort4);


    Sprak sprak = new Sprak("2012-12-01", "78983", "Dansk", "Danish", "Uforståelig");

    ArrayList<Sprak> sprakListe = new ArrayList<>();
    sprakListe.add(sprak);

    Kurs kurs1 =
        new Kurs("2012-12-01", null, "Akseloppretting", "Easy-Laser", new Omfang(null, null), null);

    Kurs kurs2 =
        new Kurs("2015-06-01", null, "Spring Boot", "Spring-folkene", new Omfang(5, "ÅR"), null);

    Kurs kurs3 = new Kurs("2016-02-01", null, "Flensarbeid for Norsk Olje og Gass",
        "Torqlite Europa a/s", new Omfang(4, "ÅR"), null);


    ArrayList<Kurs> kursListe = new ArrayList<>();
    kursListe.add(kurs1);
    kursListe.add(kurs2);
    kursListe.add(kurs3);

    Verv verv = new Verv("2000-01-15", "2001-01-15", "Verv organisasjon", "verv tittel");

    ArrayList<Verv> vervListe = new ArrayList<>();
    vervListe.add(verv);

    GeografiJobbonsker geografiJobbonsker = new GeografiJobbonsker("Harstad", "NO18.8740");

    GeografiJobbonsker geografiJobbonsker1 = new GeografiJobbonsker("Nordland", "NO18");

    GeografiJobbonsker geografiJobbonsker2 = new GeografiJobbonsker("Tromsø", "NO19.8809");

    ArrayList<GeografiJobbonsker> geografiJobbonskerListe = new ArrayList<>();
    geografiJobbonskerListe.add(geografiJobbonsker);
    geografiJobbonskerListe.add(geografiJobbonsker1);
    geografiJobbonskerListe.add(geografiJobbonsker2);

    YrkeJobbonsker yrkeJobbonsker =
        new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Industrimekaniker", true);

    YrkeJobbonsker yrkeJobbonsker1 =
        new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Lastebilsjåfør", true);

    YrkeJobbonsker yrkeJobbonsker2 =
        new YrkeJobbonsker("Yrke jobb ønskeStyrk Kode", "Butikkmedarbeider", true);

    ArrayList<YrkeJobbonsker> yrkeJobbonskerListe = new ArrayList<>();
    yrkeJobbonskerListe.add(yrkeJobbonsker);
    yrkeJobbonskerListe.add(yrkeJobbonsker1);
    yrkeJobbonskerListe.add(yrkeJobbonsker2);

    HeltidDeltidJobbonsker heltidDeltidJobbonsker =
        new HeltidDeltidJobbonsker("HeltidDeltidKode", "HeltidDeltidKode Tekst");

    ArrayList<HeltidDeltidJobbonsker> heltidDeltidJobbonskerListe = new ArrayList<>();
    heltidDeltidJobbonskerListe.add(heltidDeltidJobbonsker);

    AnsettelsesforholdJobbonsker ansettelsesforholdJobbonsker = new AnsettelsesforholdJobbonsker(
        "Ansettelsesforhold Kode", "Ansettelsesforhold Kode tekst");

    ArrayList<AnsettelsesforholdJobbonsker> ansettelsesforholdJobbonskerListe = new ArrayList<>();
    ansettelsesforholdJobbonskerListe.add(ansettelsesforholdJobbonsker);

    ArbeidstidsordningJobbonsker arbeidstidsordningJobbonsker = new ArbeidstidsordningJobbonsker(
        "Arbeidstidsordning Kode", "Arbeidstidsordning Kode Tekst");

    ArrayList<ArbeidstidsordningJobbonsker> arbeidstidsordningJobbonskerListe = new ArrayList<>();
    arbeidstidsordningJobbonskerListe.add(arbeidstidsordningJobbonsker);

    return new CvEvent("03050316895", "BOB", "NORDMANN", "1964-09-01", false, "ARBS",
        "bobob@mailinator.com", "NO", 5L, "N883773", "", "J", "2016-05-30", "Minvei 90", "", "",
        "0565", "OSLO", "NO", 301, false, "", utdanningsListe, yrkeserfaringsListe, kompetanseList,
        sertifikatListe, forerkortListe, sprakListe, kursListe, vervListe, geografiJobbonskerListe,
        yrkeJobbonskerListe, heltidDeltidJobbonskerListe, ansettelsesforholdJobbonskerListe,
        arbeidstidsordningJobbonskerListe);
  }

}