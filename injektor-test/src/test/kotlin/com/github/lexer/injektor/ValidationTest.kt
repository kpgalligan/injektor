package com.github.lexer.injektor

import com.github.lexer.injektor.coffee.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ValidationTest {
    @Test
    fun checkKontainer_validKontainerWithCompleteGraph_noErrors() {
        val injector = Injector.create(modules = listOf(LoggerModule(), HeaterModule(), CoffeeMakerModule()))
        assertThat(checkInjector(injector)).isEmpty()
    }

    @Test
    fun checkKontainer_kontainerMissingHeaterModule_classesWontBeResolved() {
        val injector = Injector.create(modules = listOf(LoggerModule(), CoffeeMakerModule()))
        val errors = checkInjector(injector)
        assertThat(errors).hasSize(2)
        assertThat(errors.find { it -> it.unresolvedClass == CoffeeMaker::class })
                .isNotNull()
        assertThat(errors.find { it -> it.unresolvedClass == Pump::class })
                .isNotNull()
    }

}