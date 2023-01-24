package ru.job4j.dreamjob.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.File;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс описывает хранилище файлов в оперативной памяти сервера
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 22.01.2023
 */
@ThreadSafe
@Repository
public class MemoryFileRepository implements FileRepository {
    private final AtomicInteger nextId = new AtomicInteger(0);

    private final Map<Integer, File> files = new ConcurrentHashMap<>();

    @Override
    public File save(File file) {
        file.setId(nextId.incrementAndGet());
        files.put(file.getId(), file);
        return file;
    }

    @Override
    public Optional<File> findById(int id) {
        return Optional.ofNullable(files.get(id));
    }

    @Override
    public boolean deleteById(int id) {
        return files.remove(id) != null;
    }
}
