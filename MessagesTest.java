package com.mycompany.progpart2maven;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessagesTest {

    // Method tests if a message text within the 250 character limit passes validation
    @Test
    public void testMessageLengthSuccess() {
        String messageText = "Hi Mike, can you join us for dinner tonight?";
        boolean isValid = messageText.length() <= 250;
        assertTrue(isValid, "Message should not be more than 250 characters.");
    }

    // Method tests if a message text exceeding 250 characters fails validation
    @Test
    public void testMessageLengthFailure() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 251; i++) {
            sb.append("A");
        }
        String longMessage = sb.toString();
        boolean isValid = longMessage.length() <= 250;
        assertFalse(isValid, "Message exceeding 250 characters should fail.");
    }

    // Method tests if a recipient cellphone number formatted with the +27 prefix passes check
    @Test
    public void testRecipientCellValid() {
        Messages msg = new Messages(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String result = msg.checkRecipientCell();
        assertTrue(result.startsWith("Cell phone number successfully"), "Valid recipient cell number with +27 format should pass.");
    }

    // Method tests if a local recipient cellphone number without the +27 prefix fails check
    @Test
    public void testRecipientCellInvalid() {
        Messages msg = new Messages(2, "08575975889", "Hi Keegan, did you receive the payment?");
        String result = msg.checkRecipientCell();
        assertFalse(result.startsWith("Cell phone number successfully"), "Invalid local recipient cell number should fail.");
    }

    // Method tests if the generated message hash contains colons and ends with uppercase first and last words
    @Test
    public void testMessageHashCreation() {
        Messages msg = new Messages(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = msg.getMessageHash();
        
        assertNotNull(hash, "Message hash should not be null.");
        assertTrue(hash.contains(":"), "Message hash should contain colon separators.");
        assertTrue(hash.endsWith("HITONIGHT"), "Message hash should end with the uppercase first and last words.");
    }

    // Method tests if a non-null message ID is generated within the 10 character limit
    @Test
    public void testMessageIDCreated() {
        Messages msg = new Messages(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertNotNull(msg.getMessageId(), "Message ID should be generated.");
        assertTrue(msg.checkMessageID(), "Message ID should be <= 10 characters.");
    }

    // Method tests if sent message option selections return the correct status messages for options 1, 2, and 3
    @Test
    public void testSentMessageOptions() {
        Messages msg = new Messages(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");

        assertEquals("Message successfully sent", msg.SentMessage(1), "Option 1 should return success message.");
        assertEquals("Press 0 to delete the message", msg.SentMessage(2), "Option 2 should return delete prompt.");
        assertEquals("Message successfully stored", msg.SentMessage(3), "Option 3 should return storage message.");
    }

    // Method tests if the total message accumulator returns the expected count of processed messages
    @Test
    public void testReturnTotalMessages() {
        Messages msg = new Messages(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals(2, msg.returnTotalMessagess(2), "Total messages handled should match expected test count of 2.");
    }
}