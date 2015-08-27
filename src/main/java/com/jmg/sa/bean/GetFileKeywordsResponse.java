/**
 * 
 */
package com.jmg.sa.bean;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Javier Moreno Garcia
 *
 */
public class GetFileKeywordsResponse extends GenericResponse {

    private List<Transcript> transcript = new ArrayList<>();

    //
    //

    public List<Transcript> getTranscript() {
        return this.transcript;
    }

    public void setTranscript(List<Transcript> transcript) {
        this.transcript = transcript;
    }

    public static class Transcript {

        private Integer p;

        private Double s;

        private Double e;

        private Double c;

        private String w;

        private String m;

        public Integer getP() {
            return this.p;
        }

        public void setP(Integer p) {
            this.p = p;
        }

        public Double getS() {
            return this.s;
        }

        public void setS(Double s) {
            this.s = s;
        }

        public Double getE() {
            return this.e;
        }

        public void setE(Double e) {
            this.e = e;
        }

        public Double getC() {
            return this.c;
        }

        public void setC(Double c) {
            this.c = c;
        }

        public String getW() {
            return this.w;
        }

        public void setW(String w) {
            this.w = w;
        }

        public String getM() {
            return this.m;
        }

        public void setM(String m) {
            this.m = m;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
        }

    }

    //
    //

}
