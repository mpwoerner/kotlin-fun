fun main() {
    println("Kotlin is fun!")
}

fun convertTransactionCategorizationEntityListToResponse(
    transactionCategorizationEntities: List<TransactionCategorizationEntity>?
): List<TransactionCategorizationResponse> {
    return transactionCategorizationEntities?.let { entities ->
        entities.groupBy { entity -> entity.tcCategory }
            .entries.map { (key, values) ->
                TransactionCategorizationResponse(
                    tcCategory = key,
                    transactionCode = values.first().transactionCode,
                    debitCreditIndicator = values.first().debitCreditIndicator,
                    tceCategories = values.map {
                        it.toTceCategorizationResponse()
                    }
                )
            }
    } ?: emptyList()
}

fun TransactionCategorizationEntity.toTceCategorizationResponse(): TceCategorizationResponse {
    return TceCategorizationResponse(
        tceCategory = this.tceCategory,
        transactionExtensionCode = this.transactionExtensionCode,
        transactionDescription = this.transactionDescription
    )
}

data class TransactionCategorizationEntity(
    val id: Long? = 1,
    val tcCategory: TCCategory,
    val tceCategory: TCECategory,
    val transactionExtensionCode: Int,
    val transactionCode: Int,
    val transactionDescription: String,
    val debitCreditIndicator: DebitCreditIndicator
)


data class TransactionCategorizationResponse(
    val tcCategory: TCCategory,
    val transactionCode: Int,
    val debitCreditIndicator: DebitCreditIndicator,
    val tceCategories: List<TceCategorizationResponse>
)

data class TceCategorizationResponse(
    val tceCategory: TCECategory,
    val transactionExtensionCode: Int,
    val transactionDescription: String
)

enum class TCCategory {
    ORIGINAL_FEE, CM_ADJ_FEE
}

enum class TCECategory {
    ANNUAL_FEE, OVERLIMIT_FEE
}


enum class DebitCreditIndicator {
    DR
}