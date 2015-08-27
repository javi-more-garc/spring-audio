/**
 * 
 */
package com.jmg.sa.domain;

import static java.util.Arrays.asList;
import static org.springframework.util.Assert.notNull;
import static org.springframework.util.StringUtils.isEmpty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Javier Moreno Garcia
 *
 */
@MappedSuperclass
public class AbstractVoiceFileKeyword extends AbstractEntity {

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "voice_file_id", nullable = false)
    private VoiceFile voiceFile;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "times", nullable = false)
    private String times;

    public AbstractVoiceFileKeyword() {

    }

    public AbstractVoiceFileKeyword(String name, List<Double> timeValues, User user) {
        // validation
        notNull(name);
        notNull(timeValues);
        notNull(user);

        this.name = name;
        setTimeValues(timeValues);
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VoiceFile getVoiceFile() {
        return this.voiceFile;
    }

    public void setVoiceFile(VoiceFile voiceFile) {
        this.voiceFile = voiceFile;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimes() {
        return this.times;
    }

    public void setUnknowns(String times) {
        this.times = times;
    }

    public List<Double> getTimeValues() {

        if (isEmpty(this.times)) {
            return Collections.emptyList();
        }

        List<Double> result = new ArrayList<>();

        String[] splitValues = this.times.split(",");

        asList(splitValues).forEach(value -> {
            result.add(Double.parseDouble(value.trim()));
        });

        return result;

    }

    public void setTimeValues(List<Double> timeValues) {

        if (CollectionUtils.isEmpty(timeValues)) {
            this.times = null;
        }

        this.times = StringUtils.join(timeValues, ",");
    }

}
