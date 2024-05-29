package org.example;

class DictionaryResult {
    private String definition;
    private String audioLink;

    public DictionaryResult() {
    }

    ;

    public DictionaryResult(String definition, String audioLink) {
        this.definition = definition;
        this.audioLink = audioLink;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }
}
