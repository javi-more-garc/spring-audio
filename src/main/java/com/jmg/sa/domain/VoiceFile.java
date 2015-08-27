/**
 * 
 */
package com.jmg.sa.domain;

import static com.jmg.sa.domain.VoiceFileStatus.UPLOADED_TO_APPLICATION;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;
import static org.springframework.util.Assert.notNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Javier Moreno Garcia
 *
 */
@Entity
@Table(name = "voice_file")
public class VoiceFile extends AbstractEntity implements Serializable {

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
    @OneToOne(mappedBy = "voiceFile", cascade = ALL, fetch = LAZY, optional = false)
    private VoiceFileContent content;

    @JsonIgnore
    @OneToMany(cascade = ALL, fetch = LAZY, orphanRemoval = true)
    @JoinColumn(name = "voice_file_analytics_id")
    private List<VoiceFileKeywordAnalytics> analyticsKeywords = new ArrayList<VoiceFileKeywordAnalytics>();

    @JsonIgnore
    @OneToMany(cascade = ALL, fetch = LAZY, orphanRemoval = true)
    @JoinColumn(name = "voice_file_transcript_id")
    private List<VoiceFileKeywordTrascript> transcriptKeywords = new ArrayList<VoiceFileKeywordTrascript>();

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "status", nullable = false)
    @Enumerated(STRING)
    private VoiceFileStatus status = UPLOADED_TO_APPLICATION;

    @Column(name = "media_id", nullable = true)
    private String mediaId;

    public VoiceFile() {

    }

    public VoiceFile(String filename, User user) {
        // validation
        notNull(filename);
        notNull(user);

        this.filename = filename;
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

    public VoiceFileContent getContent() {
        return this.content;
    }

    public void setContent(VoiceFileContent content) {
        this.content = content;
    }

    public List<VoiceFileKeywordAnalytics> getAnalyticsKeywords() {
        return this.analyticsKeywords;
    }

    public void setAnalyticsKeywords(List<VoiceFileKeywordAnalytics> analyticsKeywords) {
        this.analyticsKeywords = analyticsKeywords;
    }

    public List<VoiceFileKeywordTrascript> getTranscriptKeywords() {
        return this.transcriptKeywords;
    }

    public void setTranscriptKeywords(List<VoiceFileKeywordTrascript> transcriptKeywords) {
        this.transcriptKeywords = transcriptKeywords;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public VoiceFileStatus getStatus() {
        return this.status;
    }

    public void setStatus(VoiceFileStatus status) {
        this.status = status;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
