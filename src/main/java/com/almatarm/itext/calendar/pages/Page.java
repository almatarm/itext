package com.almatarm.itext.calendar.pages;

import com.almatarm.itext.calendar.PageId;
import com.almatarm.itext.calendar.PageType;
import com.almatarm.itext.calendar.components.Component;

/**
 * Created by almatarm on 25/06/2020.
 */
public interface Page extends Component {
    PageId getPageID();
    PageType getType();

}
