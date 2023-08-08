package com.kindredgroup.unibetlivetest.api;

import com.kindredgroup.unibetlivetest.entity.Event;
import com.kindredgroup.unibetlivetest.entity.Selection;
import com.kindredgroup.unibetlivetest.service.EventService;
import com.kindredgroup.unibetlivetest.types.Enums.SelectionState;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
@RequestMapping(Urls.BASE_PATH)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EventApi {
     private final EventService eventService;
    /** TODO
     *  @GetMapping(Urls.EVENTS)
     */
    @GetMapping(Urls.EVENTS)
    public ResponseEntity<List<Event>> fetchAllEvents() {
        log.info("Api recuperation des events");
        List<Event> events=eventService.findAllEventsLive();
        if (events.isEmpty()) {
            return new ResponseEntity<>(events,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    /** TODO
     *  @GetMapping(Urls.SELECTIONS)
     */
    @GetMapping(Urls.SELECTIONS)
    public ResponseEntity<List<Selection>> fetchAllSelectionsByEventId(
            @PathVariable("id") Long eventId,@RequestParam(required = false) SelectionState state) {
        log.info("Api recuperation des selections by eventId");
        List<Selection> events=eventService.findAllSelectionsByEventId(eventId, Optional.ofNullable(state));
        if (events.isEmpty()) {
            return new ResponseEntity<>(events,HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}
