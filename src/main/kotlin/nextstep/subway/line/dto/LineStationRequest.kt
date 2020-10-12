package nextstep.subway.line.dto

import nextstep.subway.line.domain.LineStation

data class LineStationRequest (
        val stationId: Long,
        val preStationId: Long?,
        val distance: Int?,
        val duration: Int?
) {
    fun toLineStation(): LineStation {
        return LineStation(stationId, preStationId, distance, duration)
    }
}