/**
 * 
 */
package com.jmg.sa.domain;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static org.springframework.util.Assert.notNull;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Javier Moreno Garcia
 *
 */
@Entity
@Table(name = "voice_file_content")
public class VoiceFileContent extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1467789710024154319L;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "voice_file_id", nullable = false)
    private VoiceFile voiceFile;

    @JsonIgnore
    @Lob
    @Basic(fetch = LAZY)
    @Column(name = "bytes", nullable = false)
    private byte[] bytes;
    
    @Lob
    @Basic(fetch = EAGER)
    @Column(name = "analytics", nullable = true)
    private String analytics;
    
    @Lob
    @Basic(fetch = EAGER)
    @Column(name = "keywords", nullable = true)
    private String keywords;

    public VoiceFileContent() {

    }

    public VoiceFileContent(byte[] bytes, User user) {
        // validation
        notNull(bytes);
        notNull(user);

        this.bytes = bytes;
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

    public byte[] getBytes() {
        return this.bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getAnalytics() {
        return this.analytics;
    }

    public void setAnalytics(String analytics) {
        this.analytics = analytics;
    }

    public String getKeywords() {
        return this.keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
