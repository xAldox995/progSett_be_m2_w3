package aldovalzani.progSett_be_m2_w3.dto;

import java.time.LocalDateTime;

public record ErrorResponseDTO(String message, LocalDateTime timestamp) {
}
