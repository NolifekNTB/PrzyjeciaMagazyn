package com.example.przyjeciamagazyn.UnitTests.Core.di

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.przyjeciamagazyn.Contractors.data.repository.ContractorRepository
import com.example.przyjeciamagazyn.Documents.data.repository.DocumentRepository
import com.example.przyjeciamagazyn.Documents.data.repository.PositionsRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AppModulesTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var documentRepository: DocumentRepository

    @Inject
    lateinit var positionsRepository: PositionsRepository

    @Inject
    lateinit var contractorRepository: ContractorRepository

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun testProvideContextDocumentRepository() {
        assertNotNull(documentRepository)
        assertTrue(documentRepository is DocumentRepository)
    }

    @Test
    fun testProvideContextPositionsRepository() {
        assertNotNull(positionsRepository)
        assertTrue(positionsRepository is PositionsRepository)
    }

    @Test
    fun testProvideContextContractorRepository() {
        assertNotNull(contractorRepository)
        assertTrue(contractorRepository is ContractorRepository)
    }
}