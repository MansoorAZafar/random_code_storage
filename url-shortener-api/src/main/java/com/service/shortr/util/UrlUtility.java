package com.service.shortr.util;

public class UrlUtility {
   
    public static String canonicalizeURL(String URL) {
        final String httpRemoved = URL.replaceFirst("^https?://", "");
        String canonicalURL = "";
        final int httpRemovedLength = httpRemoved.length();
    
        if(httpRemoved.charAt(httpRemovedLength - 1) == '/') {
            // Need to remove the trailing slash
            canonicalURL = httpRemoved.substring(0, httpRemovedLength - 1);
        } else {
            canonicalURL = httpRemoved;
        }

        return canonicalURL;
    } 

}