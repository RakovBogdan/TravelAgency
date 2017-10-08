package org.bogdanrakov.travelagency.model.customtag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PriceTag extends TagSupport {

    private int price;

    private static final Logger LOGGER = Logger.getLogger(PriceTag.class);

    public void setPrice(int price) {
        this.price = price;
    }

    public int doStartTag() throws JspException {
        String priceFormatted = String.format("$%.2f", price / 100.);
        try {
            pageContext.getOut().write(priceFormatted);
        } catch (IOException e) {
            LOGGER.error("Error while returning formatted price: ", e);
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
