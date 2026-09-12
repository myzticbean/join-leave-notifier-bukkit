package com.github.myzticbean.joinleavenotifier.processor;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessageProcessorTest {

    @Test
    void pickAvoidsRecentMessagesUntilAllUsed() {
        List<String> messages = List.of("a", "b", "c");
        Deque<String> recent = new ArrayDeque<>();
        Random random = new Random(42);

        Set<String> picks = Set.of(
                MessageProcessor.pick(messages, recent, random),
                MessageProcessor.pick(messages, recent, random),
                MessageProcessor.pick(messages, recent, random));
        assertEquals(3, picks.size(), "three picks from three messages must all differ");

        // every message is now recent: must fall back to the full list, not crash
        assertTrue(messages.contains(MessageProcessor.pick(messages, recent, random)));
    }

    @Test
    void pickHandlesEmptyList() {
        assertEquals("", MessageProcessor.pick(List.of(), new ArrayDeque<>(), new Random()));
    }
}
