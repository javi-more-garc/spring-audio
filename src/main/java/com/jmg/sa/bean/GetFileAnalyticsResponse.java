/**
 * 
 */
package com.jmg.sa.bean;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Javier Moreno Garcia
 *
 */
public class GetFileAnalyticsResponse extends GenericResponse {

    private List<Keyword> keywords = new ArrayList<>();

    public List<Keyword> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    //
    //

    public static class Keyword {

        private String name;

        private Map<String, List<Double>> t = new HashMap<>();

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
        public Map<String, List<Double>> getT() {
            return this.t;
        }

        public void setT(Map<String, List<Double>> t) {
            this.t = t;
        }

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, SHORT_PREFIX_STYLE);
        }

    }

    //
    //

}
