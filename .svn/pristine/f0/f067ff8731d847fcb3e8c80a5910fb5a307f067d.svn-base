package com.system.grid.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
 
import com.system.grid.po.ContributorData;
import com.system.grid.po.LanguageContribution;
 
public class DynamicRendererController extends SelectorComposer<Component> {
    private static final long serialVersionUID = 1L;
     
    ListModel<LanguageContribution> contributors;
    
    private final ListModel<LanguageContribution> langContributors = 
            new ListModelList<LanguageContribution>(new ContributorData().getLanguageContributors());
 
    public ListModel<LanguageContribution> getContributors() {
        return langContributors;
    }
}