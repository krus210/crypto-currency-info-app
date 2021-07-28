package ru.korolevss.detail.domain.models

enum class HistoryInterval {
    MINUTE_ONE {
        override val value: String
            get() = "m1"
    },
    MINUTES_FIVE {
        override val value: String
            get() = "m5"
    },
    MINUTES_FIFTEEN {
        override val value: String
            get() = "m15"
    },
    MINUTES_THIRTY {
        override val value: String
            get() = "m30"
    },
    HOUR_ONE {
        override val value: String
            get() = "h1"
    },
    HOUR_TWO {
        override val value: String
            get() = "h2"
    },
    HOUR_SIX {
        override val value: String
            get() = "h6"
    },
    HOUR_TWELVE {
        override val value: String
            get() = "h12"
    },
    DAY_ONE {
        override val value: String
            get() = "d1"
    };


    abstract val value : String
}