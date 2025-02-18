package com.HMS.HMS.Services;

import com.HMS.HMS.Entities.Promocode;

import java.util.List;
import java.util.Optional;

public interface PromoCodeService {
    Promocode savePromo (Promocode promocode);
    Optional<Promocode> getById(Long id);
    List<Promocode>getAllPromo();
    void DeletePromo(Long id);
}
