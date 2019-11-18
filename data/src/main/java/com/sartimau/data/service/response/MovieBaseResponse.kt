package com.sartimau.data.service.response

class MovieBaseResponse<T>(

        var code: Int,
        var status: String,
        var data: T?
)
