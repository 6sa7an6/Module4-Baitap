package ra.student.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentRequestDto {
        private Integer studentId;
        @NotEmpty(message = "Không được để trống")
        private String studentName;
        private String phoneNumber;
        private Boolean sex;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private Date birthday;
        private MultipartFile file;
        private String address;
}
