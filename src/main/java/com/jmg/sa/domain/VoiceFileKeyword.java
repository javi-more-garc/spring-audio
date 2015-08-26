/**
 * 
 */
package com.jmg.sa.domain;

import static java.util.Arrays.asList;
import static javax.persistence.GenerationType.AUTO;
import static org.springframework.util.Assert.notNull;
import static org.springframework.util.StringUtils.isEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Javier Moreno Garcia
 *
 */
@Entity
@Table(name = "voice_file_keyword")
public class VoiceFileKeyword extends AbstractEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8448923231708595307L;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

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

    @Column(name = "unknowns", nullable = false)
    private String unknowns;

    public VoiceFileKeyword() {

    }

    public VoiceFileKeyword(String name, String unknowns, User user) {
        // validation
        notNull(name);
        notNull(unknowns);
        notNull(user);

        this.name = name;
        this.unknowns = unknowns;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUnknowns() {
        return this.unknowns;
    }

    public void setUnknowns(String unknowns) {
        this.unknowns = unknowns;
    }

    public List<Double> getUnknownValues() {

        if (isEmpty(this.unknowns)) {
            return Collections.emptyList();
        }

        List<Double> result = new ArrayList<>();

        String[] splitValues = this.unknowns.split(",");

        asList(splitValues).forEach(value -> {
            result.add(Double.parseDouble(value.trim()));
        });

        return result;

    }

}
