package com.pongtayot.restapi_hibernet.controllers.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {
    @NotEmpty
    @Size(min = 2, max = 100)
    private String name;
    private MultipartFile image;
    private int price;
    private int stock;
}
