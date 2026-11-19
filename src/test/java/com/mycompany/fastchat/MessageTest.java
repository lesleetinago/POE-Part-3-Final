package com.mycompany.fastchat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    
    Message msg = new Message();

    @Test
    public void testCheckRecipientCellSuccess() {
        // POE: +27718693002 should pass - your exact message with sucessfully
        assertEquals("Cellphone number sucessfully captured.", msg.checkRecipientCell("+27718693002"));
    }

    @Test
    public void testCheckRecipientCellSuccess2() {
        // POE: 08575975889 should pass
        assertEquals("Cellphone number sucessfully captured.", msg.checkRecipientCell("08575975889"));
    }

    @Test
    public void testCheckRecipientCellFail() {
        assertEquals("Cellphone number is incorrectly formatted or does not contain an international code.", msg.checkRecipientCell("123"));
    }

    @Test
    public void testCheckMessageLengthSuccess() {
        assertEquals("Message ready to send.", msg.checkMessageLength("Hi Mike, can you join us for dinner tonight?"));
    }

    @Test
    public void testCheckMessageLengthFail() {
        // 260 chars = exceeds by 10
        String longMsg = "a".repeat(260);
        assertEquals("Message exceeds 250 characters by 10, please reduce size.", msg.checkMessageLength(longMsg));
    }

    @Test
    public void testCreateMessageIDLength() {
        String id = msg.createMessageID();
        assertEquals(10, id.length());
    }

    @Test
    public void testCreateMessageIDIsNumber() {
        String id = msg.createMessageID();
        // Should be all digits
        assertTrue(id.matches("\\d+"));
    }

    @Test
    public void testMakeMessageHash() {
        // POE example: Hi Mike, can you join us for dinner tonight?
        String hash = msg.makeMessageHash("0012345678", 0, "Hi Mike, can you join us for dinner tonight?");
        // Your code keeps the ? at the end, so we test for that
        assertEquals("00:0:HITONIGHT?", hash);
    }

    @Test
    public void testMakeMessageHash2() {
        // POE example: Hi Keegan, did you receive the payment?
        String hash = msg.makeMessageHash("1234567890", 1, "Hi Keegan, did you receive the payment?");
        assertEquals("12:1:HIPAYMENT?", hash);
    }

    @Test
    public void testMakeMessageHash3() {
        String hash = msg.makeMessageHash("0012345678", 2, "Where are you? You are late! I have waited for you.");
        assertEquals("00:2:WHEREYOU.", hash);
    }
}
