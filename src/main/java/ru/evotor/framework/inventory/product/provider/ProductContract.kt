package ru.evotor.framework.inventory.product.provider

import ru.evotor.query.FilterBuilder

internal object ProductContract {
    const val PATH = "products"

    const val COLUMN_VARIATION_ID = "VARIATION_ID"
    const val COLUMN_UUID = "UUID"
    const val COLUMN_GROUP_UUID = "GROUP_UUID"
    const val COLUMN_NAME = "NAME"
    const val COLUMN_CODE = "CODE"
    const val COLUMN_VENDOR_CODE = "VENDOR_CODE"
    const val COLUMN_BARCODES = "BARCODES"
    const val COLUMN_PURCHASE_PRICE = "PURCHASE_PRICE"
    const val COLUMN_SELLING_PRICE = "SELLING_PRICE"
    const val COLUMN_VAT_RATE = "VAT_RATE"
    const val COLUMN_QUANTITY = "QUANTITY"
    const val COLUMN_DESCRIPTION = "DESCRIPTION"
    const val COLUMN_ALLOWED_TO_SELL = "ALLOWED_TO_SELL"

    const val VARIATION_ID_UNCLASSIFIED_PRODUCT = 0
    const val VARIATION_ID_WEAK_ALCOHOL = 1
    const val VARIATION_ID_STRONG_ALCOHOL = 2
    const val VARIATION_ID_TOBACCO = 3
    const val VARIATION_ID_PAYABLE_SERVICE = 4

    fun getQueryInitialEditions(variationId: Int) = listOf(FilterBuilder.InitialEdition(
            COLUMN_VARIATION_ID,
            "=",
            variationId,
            "AND"
    ))
}