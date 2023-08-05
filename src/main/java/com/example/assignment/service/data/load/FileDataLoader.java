package com.example.assignment.service.data.load;

import java.io.IOException;
import java.util.List;

public interface FileDataLoader<T> {
    List<T> loadData() throws IOException;
    T loadDataByIde(String id) throws IOException;
}
