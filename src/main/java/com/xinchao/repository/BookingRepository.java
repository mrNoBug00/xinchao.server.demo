package com.xinchao.repository;

import com.xinchao.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh nếu cần
}
