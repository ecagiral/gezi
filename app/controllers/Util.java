package controllers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class Util {
    
	 final static String urlAnchorString = "<a href='%s' target='_blank'>%s</a>&nbsp;";
	 private final static Pattern urlRegex = Pattern.compile("\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]");

	    public static String getStatementText(String st_text){
	    	String textHtml = st_text;
	        if(!textHtml.isEmpty()){            
				Matcher urlMatcher = urlRegex.matcher(textHtml);
				String replaced2 = null;
				while (urlMatcher.find()) {
	                String urlGroup = urlMatcher.group();
	                String anchor = String.format(urlAnchorString, urlGroup.startsWith("http://") ? urlGroup : "http://"+urlGroup,urlGroup);
	                replaced2 = StringUtils.replace(textHtml,urlGroup,anchor);
	                textHtml =  replaced2;
				}
				if(textHtml==null){
	                textHtml = st_text;
				}
	        }
	        return textHtml;
	    }
}
