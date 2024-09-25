package com.xinchao.services;

import com.xinchao.dto.AdminActionBookingDto;
import com.xinchao.models.*;
import com.xinchao.payload.request.BookingRequest;
import com.xinchao.payload.response.BookingResponse;
import com.xinchao.payload.response.ImageDTO;
import com.xinchao.payload.response.UserResponse;
import com.xinchao.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository roomRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ImageRepository imageRepository;
    // Các phương thức khác

    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(this::mapToBookingResponse)
                .collect(Collectors.toList());
    }

    public BookingResponse createBooking(BookingRequest bookingRequest) {
        Optional<User> userOptional = userRepository.findById(bookingRequest.getUserId());
        Optional<Product> roomOptional = roomRepository.findById(bookingRequest.getRoomId());
        Optional<Status> optionalStatus = statusRepository.findByName(StatusEnum.PENDING);

        List<Image> contactQrCode = bookingRequest.getContactQrCode().stream()
                .map(id -> imageRepository.findById(id).orElse(null))
                .collect(Collectors.toList());



        if (userOptional.isPresent() && roomOptional.isPresent()) {
            User user = userOptional.get();
            Product room = roomOptional.get();

            Booking booking = new Booking();
            booking.setUser(user);
            booking.setRoom(room);
            booking.setBookerName(bookingRequest.getBookerName());
            booking.setBookerPhone(bookingRequest.getBookerPhone());
            booking.setContactQrCode(contactQrCode);


            booking.setBookingTime(bookingRequest.getBookingTime());
            booking.setStatus(optionalStatus.orElseThrow(() -> new RuntimeException("Status not found"))); // Trạng thái mặc định là chờ xác nhận

            Booking savedBooking = bookingRepository.save(booking);

            return new BookingResponse(
                    savedBooking.getId(),
                    savedBooking.getUser().getId(),
                    savedBooking.getRoom().getId(),
                    savedBooking.getRoom().getName(),
                    savedBooking.getRoom().getAddress(),
                    savedBooking.getBookerName(),
                    savedBooking.getBookerPhone(),
                    savedBooking.getBookingTime(),
                    savedBooking.getStatus(),
                    null,
                    null,
                    null,
                    null,
                    savedBooking.getContactQrCode()
            );
        } else {
            throw new RuntimeException("User hoặc Room không tồn tại");
        }
    }

    public BookingResponse confirmBooking(AdminActionBookingDto adminActionBookingDto) {
        Optional<Booking> bookingOptional = bookingRepository.findById(adminActionBookingDto.getBookingId());
        Optional<User> adminOptional = userRepository.findById(adminActionBookingDto.getAdminId());
        Optional<Status> optionalStatus = statusRepository.findByName(StatusEnum.CONFIRMED);

        if (bookingOptional.isPresent() && adminOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            User admin = adminOptional.get();

            booking.setStatus(optionalStatus.orElseThrow(() -> new RuntimeException("Status not found")));
            booking.setAdminId(admin);

            Booking savedBooking = bookingRepository.save(booking);

            return new BookingResponse(
                    savedBooking.getId(),
                    savedBooking.getUser().getId(),
                    savedBooking.getRoom().getId(),
                    savedBooking.getRoom().getName(),
                    savedBooking.getRoom().getAddress(),
                    savedBooking.getBookerName(),
                    savedBooking.getBookerPhone(),
                    savedBooking.getBookingTime(),
                    savedBooking.getStatus(),
                    admin.getUserName(),
                    null,
                    null,
                    null,
                    savedBooking.getContactQrCode()
            );
        } else {
            throw new RuntimeException("Booking hoặc Admin không tồn tại");
        }
    }

    private BookingResponse mapToBookingResponse(Booking booking) {
        UserResponse authorResponse = mapToUserResponse(booking.getUser());
        UserResponse adminResponse = null;
        if (booking.getAdminId() != null) {
            adminResponse = mapToUserResponse(booking.getAdminId());
        }

        return new BookingResponse(
                booking.getId(),
                booking.getUser().getId(),
                booking.getRoom().getId(),
                booking.getRoom().getName(),
                booking.getRoom().getAddress(),
                booking.getBookerName(),
                booking.getBookerPhone(),
                booking.getBookingTime(),
                booking.getStatus(),
                adminResponse != null ? adminResponse.getUserName() : null,
                adminResponse != null ? adminResponse.getUserName() : null,
                adminResponse != null ? adminResponse.getUserName() : null,
                booking.getRefuseOrCancelMessage(),
                booking.getContactQrCode()
        );
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getArc(),
                user.getVnId(),
                user.getPassportNumber(),
                user.getRole(),
                user.getLineId(),
                user.getNumberZalo()
        );
    }

    public BookingResponse refuseBooking(AdminActionBookingDto adminActionBookingDto) {
        Optional<Booking> bookingOptional = bookingRepository.findById(adminActionBookingDto.getBookingId());
        Optional<User> adminOptional = userRepository.findById(adminActionBookingDto.getAdminId());
        Optional<Status> optionalStatus = statusRepository.findByName(StatusEnum.REFUSE);

        if (bookingOptional.isPresent() && adminOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            User admin = adminOptional.get();

            booking.setStatus(optionalStatus.orElseThrow(() -> new RuntimeException("Status not found")));
            booking.setAdminId(admin);
            booking.setRefuseOrCancelMessage(adminActionBookingDto.getMessage());

            Booking savedBooking = bookingRepository.save(booking);

            return new BookingResponse(
                    savedBooking.getId(),
                    savedBooking.getUser().getId(),
                    savedBooking.getRoom().getId(),
                    savedBooking.getRoom().getName(),
                    savedBooking.getRoom().getAddress(),
                    savedBooking.getBookerName(),
                    savedBooking.getBookerPhone(),
                    savedBooking.getBookingTime(),
                    savedBooking.getStatus(),
                    null,
                    admin.getUserName(),
                    null,
                    savedBooking.getRefuseOrCancelMessage(),
                    savedBooking.getContactQrCode()
            );
        } else {
            throw new RuntimeException("Booking hoặc Admin không tồn tại");
        }
    }

    public BookingResponse cancelBooking(AdminActionBookingDto adminActionBookingDto) {
        Optional<Booking> bookingOptional = bookingRepository.findById(adminActionBookingDto.getBookingId());
        Optional<User> adminOptional = userRepository.findById(adminActionBookingDto.getAdminId());
        Optional<Status> optionalStatus = statusRepository.findByName(StatusEnum.CANCEL);

        if (bookingOptional.isPresent() && adminOptional.isPresent()) {
            Booking booking = bookingOptional.get();
            User admin = adminOptional.get();

            booking.setStatus(optionalStatus.orElseThrow(() -> new RuntimeException("Status not found")));
            booking.setAdminId(admin);
            booking.setRefuseOrCancelMessage(adminActionBookingDto.getMessage());

            Booking savedBooking = bookingRepository.save(booking);

            return new BookingResponse(
                    savedBooking.getId(),
                    savedBooking.getUser().getId(),
                    savedBooking.getRoom().getId(),
                    savedBooking.getRoom().getName(),
                    savedBooking.getRoom().getAddress(),
                    savedBooking.getBookerName(),
                    savedBooking.getBookerPhone(),
                    savedBooking.getBookingTime(),
                    savedBooking.getStatus(),
                    null,
                    null,
                    admin.getUserName(),
                    savedBooking.getRefuseOrCancelMessage(),
                    savedBooking.getContactQrCode()
            );
        } else {
            throw new RuntimeException("Booking hoặc Admin không tồn tại");
        }
    }

    private ImageDTO mapToImageDTO(Image image) {
        if (image == null) return null;
        ImageDTO dto = new ImageDTO();
        dto.setId(image.getId());
        dto.setImageUrl(image.getImageUrl());
        return dto;
    }
}
