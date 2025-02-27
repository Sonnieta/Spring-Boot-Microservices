package com.Backend.Schoolmgt.Service;

import com.Backend.Schoolmgt.Entity.Stream;
import com.Backend.Schoolmgt.Repository.StreamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamService {
    private final StreamRepository streamRepository;

    public StreamService(StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }

    public Stream createStream(Stream stream) {
        return streamRepository.save(stream);
    }

    public List<Stream> getAllStreams() {
        return streamRepository.findAll();
    }

    public Optional<Stream> getStreamById(Long id) {
        return streamRepository.findById(id);
    }

    public Stream updateStream(Long id, Stream stream) {
        if (streamRepository.existsById(id)) {
            stream.setId(id);
            return streamRepository.save(stream);
        }
        return null;
    }

    public void deleteStream(Long id) {
        streamRepository.deleteById(id);
    }
}