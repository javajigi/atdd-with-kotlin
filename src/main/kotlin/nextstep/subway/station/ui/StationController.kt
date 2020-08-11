package nextstep.subway.station.ui

import nextstep.subway.station.domain.StationRepository
import nextstep.subway.station.dto.StationCreateRequest
import nextstep.subway.station.dto.StationResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class StationController @Autowired constructor(
        private val stationRepository: StationRepository
) {
    @PostMapping("/stations")
    fun createStation(@RequestBody view: StationCreateRequest): ResponseEntity<*> {
        val station = view.toStation()
        return try {
            val persistStation = stationRepository.save(station)
            ResponseEntity.created(URI.create("/stations/" + persistStation.id)).body(StationResponse.of(persistStation))
        } catch (e: Exception) {
            ResponseEntity.badRequest().build<Void>()
        }
    }

    @GetMapping(value = ["/stations"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun showStations(): ResponseEntity<*> {
        return ResponseEntity.ok().body(stationRepository.findAll())
    }

    @DeleteMapping("/stations/{id}")
    fun deleteStation(@PathVariable id: Long): ResponseEntity<Void> {
        stationRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}
