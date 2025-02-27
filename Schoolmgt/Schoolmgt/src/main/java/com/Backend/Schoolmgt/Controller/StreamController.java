package com.Backend.Schoolmgt.Controller;


import com.Backend.Schoolmgt.Entity.Stream;
import com.Backend.Schoolmgt.Service.StreamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/streams")
public class StreamController {
    private final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @PostMapping("/create")
    public Stream createStream(@RequestBody Stream stream) {
        return streamService.createStream(stream);
    }

    @GetMapping
    public List<Stream> getAllStreams() {
        return streamService.getAllStreams();
    }

    @GetMapping("/{id}")
    public Optional<Stream> getStreamById(@PathVariable Long id) {
        return streamService.getStreamById(id);
    }

    @PutMapping("/update/{id}")
    public Stream updateStream(@PathVariable Long id, @RequestBody Stream stream) {
        return streamService.updateStream(id, stream);
    }

    @DeleteMapping("/{id}")
    public void deleteStream(@PathVariable Long id) {
        streamService.deleteStream(id);
    }
}