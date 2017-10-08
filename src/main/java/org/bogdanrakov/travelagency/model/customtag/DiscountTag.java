package org.bogdanrakov.travelagency.model.customtag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class DiscountTag extends TagSupport {

    private int discount;

    private static final Logger LOGGER = Logger.getLogger(DiscountTag.class);

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int doStartTag() throws JspException {
        String priceFormatted = String.format("%.2f%%", discount / 100.);
        try {
            pageContext.getOut().write(priceFormatted);
        } catch (IOException e) {
            LOGGER.error("Error while returning formatted price: ", e);
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
