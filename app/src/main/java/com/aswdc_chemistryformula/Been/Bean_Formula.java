package com.aswdc_chemistryformula.Been;

/**
 * Created by SAGAR on 06-09-2017.
 */

public class Bean_Formula {

    private int FormulaID;
    private int SubTopicID;
    private String Formula;
    private String TopicDescription;
    private String DescTable;

    public String getDescTable() {
        return DescTable;
    }

    public void setDescTable(String descTable) {
        DescTable = descTable;
    }

    public int getFormulaID() {
        return FormulaID;
    }

    public void setFormulaID(int formulaID) {
        FormulaID = formulaID;
    }

    public int getSubTopicID() {
        return SubTopicID;
    }

    public void setSubTopicID(int subTopicID) {
        SubTopicID = subTopicID;
    }

    public String getFormula() {
        return Formula;
    }

    public void setFormula(String formula) {
        Formula = formula;
    }

    public String getTopicDescription() {
        return TopicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        TopicDescription = topicDescription;
    }
}
