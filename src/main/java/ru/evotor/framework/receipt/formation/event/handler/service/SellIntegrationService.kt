package ru.evotor.framework.receipt.formation.event.handler.service

import android.os.Bundle
import ru.evotor.framework.common.event.handler.service.IntegrationServiceV2
import ru.evotor.framework.core.RequiresIntentAction
import ru.evotor.framework.receipt.formation.event.ReturnPositionsForBarcodeRequestedEvent
import ru.evotor.framework.receipt.formation.event.ReturnPurchaserRequisitesForPrintGroupRequestedEvent

/**
 * Служба для работы с чеком продажи.
 */
abstract class SellIntegrationService : IntegrationServiceV2() {

    final override fun onEvent(action: String, bundle: Bundle) = when (action) {
        ACTION_BARCODE_RECEIVED -> ReturnPositionsForBarcodeRequestedEvent.from(bundle)?.let { handleEvent(it) }
        ACTION_PURCHASER_REQUISITES -> ReturnPurchaserRequisitesForPrintGroupRequestedEvent.from(bundle)?.let { handleEvent(it) }
        else -> null
    }

    /**
     * Возвращает смарт-терминалу список позиций созданных на основе данных, полученных от сканера штрихкодов.
     * Смарт-терминал добавляет полученный список в чек продажи.
     *
     * Если поступившее событие содержит параметр `creatingNewProduct == true`, метод должен вернуть `null`.
     *
     * @param event Событие сканирования штрихкода.
     * @return Результат обработки события. Содержит список позиций для добавления в чек и параметр `iCanCreateNewProduct`, который сообщает, намерено приложение создать новый товар или нет.
     * @see <a href="https://developer.evotor.ru/docs/doc_java_return_positions_for_barcode_requested.html">Обработка события сканирования штрихкода</a>
     */
    @RequiresIntentAction(ACTION_BARCODE_RECEIVED)
    open fun handleEvent(event: ReturnPositionsForBarcodeRequestedEvent): ReturnPositionsForBarcodeRequestedEvent.Result? = null

    /**
     * Возвращает смарт-терминалу массив печатных групп с соответствующими реквизитами покупателя.
     *
     * @param event Событие, с помощью которого смарт-терминал запрашивает у установленных приложений реквизиты покупателя.
     * @return Результат обработки события, содержащий массив печатных групп и соответствующих им объектов с реквизитами покупателя.
     * @see <a href="https://developer.evotor.ru/docs/doc_java_purchase_requisites_event_processing.html">Добавление реквизитов покупателя в чек</a>
     */
    @RequiresIntentAction(ACTION_PURCHASER_REQUISITES)
    open fun handleEvent(event: ReturnPurchaserRequisitesForPrintGroupRequestedEvent): ReturnPurchaserRequisitesForPrintGroupRequestedEvent.Result? = null

    companion object {

        /**
         * Действие, которое сообщает о получении данных от сканера штрихкодов.
         *
         * Чтобы подписать службу на получение действия, в манифесте приложения, в элементе `action` intent-фильтра службы, укажите значение `ru.evotor.event.sell.BARCODE_RECEIVED`.
         */
        const val ACTION_BARCODE_RECEIVED = "ru.evotor.event.sell.BARCODE_RECEIVED"

        /**
         * Запрос [реквизитов покупателя][ru.evotor.framework.receipt.Purchaser] для добавления в чек продажи.
         *
         * Чтобы подписать службу на получение запроса, в манифесте приложения, в элементе `action` intent-фильтра службы, укажите значение `ru.evotor.event.sell.PURCHASER_REQUISITES`.
         */
        const val ACTION_PURCHASER_REQUISITES = "ru.evotor.event.sell.PURCHASER_REQUISITES"

        /**
         * Разрешение необходимое приложению для работы со службой [ru.evotor.framework.receipt.formation.event.handler.service.SellIntegrationService].
         *
         * Чтобы выдать разрешение, в элементе `uses-permission` манифеста приложения, укажите значение `ru.evotor.permission.SELL_INTEGRATION_SERVICE`.
         */
        const val PERMISSION = "ru.evotor.permission.SELL_INTEGRATION_SERVICE"
    }
}
