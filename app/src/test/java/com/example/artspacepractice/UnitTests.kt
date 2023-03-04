package com.example.artspacepractice

import org.junit.Assert.assertEquals
import org.junit.Test

class UnitTests {
    @Test
    fun nominal_increase_control() {
        val step = 2
        val max = 3
        val expectedStep = 3
        val actualStep = nextStep(step, max)
        assertEquals(expectedStep, actualStep)
    }

    @Test
    fun nominal_decrease_control() {
        val step = 2
        val max = 3
        val expectedStep = 1
        val actualStep = previousStep(step,max)
        assertEquals(expectedStep, actualStep)
    }

    @Test
    fun limit_increase_control() {
        val step = 3
        val max = 3
        val expectedStep = 1
        val actualStep = nextStep(step,max)
        assertEquals(expectedStep, actualStep)
    }

    @Test
    fun limit_decrease_control() {
        val step = 1
        val max = 3
        val expectedStep = 3
        val actualStep = previousStep(step,max)
        assertEquals(expectedStep, actualStep)
    }
}