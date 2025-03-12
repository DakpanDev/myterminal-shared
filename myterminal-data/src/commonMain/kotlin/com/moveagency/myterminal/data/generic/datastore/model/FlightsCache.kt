package com.moveagency.myterminal.data.generic.datastore.model

import com.moveagency.myterminal.domain.generic.model.Flight
import kotlinx.datetime.LocalDate

typealias FlightsCache = Map<LocalDate, List<Flight>>
