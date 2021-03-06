package com.moneybook.moneybook.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface StockPersonalRepository extends JpaRepository<StockPersonal, Long>, StockPersonalQueryRepository {

}
