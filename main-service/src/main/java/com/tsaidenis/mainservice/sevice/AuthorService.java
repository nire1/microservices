package com.tsaidenis.mainservice.sevice;

import com.tsaidenis.mainservice.entity.Author;

public interface AuthorService {
    Author findByName(String name);
}
