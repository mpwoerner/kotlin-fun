import DebitCreditIndicator.*
import TCCategory.*
import TCECategory.*
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

class MainKtTest {

    @Test
    fun `converts entity to response`() {
        convertTransactionCategorizationEntityListToResponse(transactionCategorizationEntities)
            .size `should be equal to` 2
        convertTransactionCategorizationEntityListToResponse(transactionCategorizationEntities) `should be equal to` expectedTransactionCategorizationResponse
    }

    private val transactionCategorizationEntities = listOf(
        TransactionCategorizationEntity(
            id = 1L,
            tcCategory = ORIGINAL_FEE,
            tceCategory = ANNUAL_FEE,
            transactionExtensionCode = 2033,
            transactionCode = 4000,
            transactionDescription = "REGULAR OLE' FEE",
            debitCreditIndicator = DR
        ),
        TransactionCategorizationEntity(
            id = 2L,
            tcCategory = ORIGINAL_FEE,
            tceCategory = OVERLIMIT_FEE,
            transactionExtensionCode = 2000,
            transactionCode = 4000,
            transactionDescription = "BIG OVERLIMIT FEE",
            debitCreditIndicator = DR
        ),
        TransactionCategorizationEntity(
            id = 3L,
            tcCategory = CM_ADJ_FEE,
            tceCategory = ANNUAL_FEE,
            transactionExtensionCode = 5432,
            transactionCode = 4002,
            transactionDescription = "REGULAR OLE' FEE",
            debitCreditIndicator = DR
        )
    )

    private val expectedTransactionCategorizationResponse = listOf(
        TransactionCategorizationResponse(
            tcCategory = ORIGINAL_FEE,
            transactionCode = 4000,
            debitCreditIndicator = DR,
            tceCategories = listOf(
                TceCategorizationResponse(
                    tceCategory = ANNUAL_FEE,
                    transactionDescription = "REGULAR OLE' FEE",
                    transactionExtensionCode = 2033
                ),
                TceCategorizationResponse(
                    tceCategory = OVERLIMIT_FEE,
                    transactionDescription = "BIG OVERLIMIT FEE",
                    transactionExtensionCode = 2000
                )
            )
        ),
        TransactionCategorizationResponse(
            tcCategory = CM_ADJ_FEE,
            transactionCode = 4002,
            debitCreditIndicator = DR,
            tceCategories = listOf(
                TceCategorizationResponse(
                    tceCategory = ANNUAL_FEE,
                    transactionDescription = "REGULAR OLE' FEE",
                    transactionExtensionCode = 5432
                )
            )
        )
    )
}