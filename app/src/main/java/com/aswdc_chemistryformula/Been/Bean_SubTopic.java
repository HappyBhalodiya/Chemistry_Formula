package com.aswdc_chemistryformula.Been;

/**
 * Created by SAGAR on 25-08-2017.
 */

public class Bean_SubTopic {

    private int SubTopicID;
    private int TopicID;
    private String SubTopicName;
    private int IsFavourite;
    private String SubTopicDisplayName;

    public String getSubTopicDisplayName() {
        return SubTopicDisplayName;
    }

    public void setSubTopicDisplayName(String subTopicDisplayName) {
        SubTopicDisplayName = subTopicDisplayName;
    }

    public int getIsFavourite() {
        return IsFavourite;
    }

    public void setIsFavourite(int isFavourite) {
        IsFavourite = isFavourite;
    }

    public int getSubTopicID() {
        return SubTopicID;
    }

    public void setSubTopicID(int subTopicID) {
        SubTopicID = subTopicID;
    }

    public int getTopicID() {
        return TopicID;
    }

    public void setTopicID(int topicID) {
        TopicID = topicID;
    }

    public String getSubTopicName() {
        return SubTopicName;
    }

    public void setSubTopicName(String subTopicName) {
        SubTopicName = subTopicName;
    }
}
