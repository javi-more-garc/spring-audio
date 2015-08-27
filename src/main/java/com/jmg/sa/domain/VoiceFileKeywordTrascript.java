/**
 * 
 */
package com.jmg.sa.domain;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Javier Moreno Garcia
 *
 */
@Entity
@Table(name = "voice_file_keyword_trascript")
public class VoiceFileKeywordTrascript extends AbstractVoiceFileKeyword implements Serializable {


    private static final long serialVersionUID = 8795716177823842867L;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    public VoiceFileKeywordTrascript() {

    }

    public VoiceFileKeywordTrascript(String name, List<Double> timeValues, User user) {
        super(name, timeValues, user);

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 
}
