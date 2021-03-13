package com.waes.assignment.domain.message;

import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * This class helps on retrieving messages from the messages properties file
 */
@Component
public class MessageHelper {
    private final MessageSourceAccessor messageSourceAccessor;

    public MessageHelper(MessageSource messageSource) {
        this.messageSourceAccessor = new MessageSourceAccessor(messageSource);
    }

    /**
     * Retrieves a message through its key key
     *
     * @param messageCode - Key to retreive the message
     * @return - retrieved message from the properties file
     */
    public String get(final String messageCode) {
        return messageSourceAccessor.getMessage(messageCode);
    }

}
