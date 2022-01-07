package no.nav.arbeid.cv.kandidatsok.es.helper;

import no.nav.arbeid.kandidatsok.es.helper.KandidatnrHelper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class KandidatnrHelperTest {

    @Test
    public void test1() {
        assertThat(KandidatnrHelper.toKandidatnr(1L)).isEqualTo("AB000001");
    }

    @Test
    public void test2() {
        assertThat(KandidatnrHelper.toKandidatnr(788L)).isEqualTo("AB070808");
    }

    @Test
    public void test3() {
        assertThat(KandidatnrHelper.toKandidatnr(4363582L)).isEqualTo("GE354802");
    }

    @Test
    public void test4() {
        assertThat(KandidatnrHelper.toKandidatnr(1003340L)).isEqualTo("AE031400");
    }

    @Test
    public void test5() {
        assertThat(KandidatnrHelper.toKandidatnr(4363742L)).isEqualTo("GE374402");
    }

    @Test
    public void test6() {
        assertThat(KandidatnrHelper.toKandidatnr(1003340L)).isEqualTo("AE031400");
    }

    @Test
    public void test7() {
        assertThat(KandidatnrHelper.toKandidatnr(1003340L)).isEqualTo("AE031400");
    }

    @Test
    public void test8() {
        assertThat(KandidatnrHelper.toKandidatnr(1003340L)).isEqualTo("AE031400");
    }

    @Test
    public void test9() {
        assertThat(KandidatnrHelper.toKandidatnr(1003340L)).isEqualTo("AE031400");
    }

    @Test
    public void test10() {
        assertThat(KandidatnrHelper.toKandidatnr(1003340L)).isEqualTo("AE031400");
    }

}
