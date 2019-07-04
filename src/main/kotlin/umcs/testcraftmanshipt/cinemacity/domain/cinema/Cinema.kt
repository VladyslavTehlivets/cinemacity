package umcs.testcraftmanshipt.cinemacity.domain.cinema

import umcs.testcraftmanshipt.cinemacity.domain.DomainObject

class Cinema private constructor(val cinemaName: String, val cityName: String, id: CinemaId) : DomainObject(id) {

    constructor(cinemaName: String, cityName: String) : this(cinemaName, cityName, CinemaId())

    constructor(cinemaId: CinemaId, cinemaName: String, cityName: String) : this(cinemaName, cityName, cinemaId)
}
