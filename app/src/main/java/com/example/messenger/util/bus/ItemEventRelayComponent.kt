/*
 * ItemEventRelayComponent.kt 2020. 8. 5
 *
 * Copyright 2020 BasicBug. All rights Reserved.
 *
 */

package com.example.messenger.util.bus

import com.jakewharton.rxrelay2.Relay

/**
 * @author MyeongKi
 */

interface ItemEventRelayComponent {
    var itemEventRelay: Relay<RxAction>?
}